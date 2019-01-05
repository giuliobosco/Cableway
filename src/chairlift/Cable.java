/*
 * The MIT License
 *
 * Copyright 2018 giuliobosco.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package chairlift;

import java.util.ArrayList;
import java.util.List;

/**
 * @author giuliobosco
 * @version 1.0
 */
public class Cable extends Thread {
    // ------------------------------------------------------------------------------------ Costants
    // ---------------------------------------------------------------------------------- Attributes

    /**
     * Length of the cable, in meters.
     */
    private double length;

    /**
     * Diameter of the cable, in millimeters.
     */
    private double diameter;

    /**
     * Number of chair on the cable.
     */
    private double numberOfChairLifts;

    /**
     * Chair lift on the cable.
     */
    private List<Thread> chairLifts;

    // --------------------------------------------------------------------------- Getters & Setters

    /**
     * Get the length of the cable, in meters.
     *
     * @return Length of the cable, in meters.
     */
    public double getLength() {
        return this.length;
    }

    /**
     * Get the diameter of the cable, in millimeters.
     *
     * @return Diameter of the cable, in millimeters.
     */
    public double getDiameter() {
        return this.diameter;
    }

    /**
     * Get the number of chair on the cable.
     *
     * @return Number of chair on the cable.
     */
    public double getNumberOfChairLifts() {
        return this.numberOfChairLifts;
    }

    // -------------------------------------------------------------------------------- Constructors

    /**
     * Create the cable with the length and the diameter of the cable and with the number of
     * chairLifts on the cable.
     *
     * @param length             Length of the cable, in meters.
     * @param diameter           Diameter of the cable, in millimeters.
     * @param numberOfChairLifts Number of chair on the cable.
     */
    public Cable(double length, double diameter, double numberOfChairLifts) {
        if (length > 0 && diameter > 0 && numberOfChairLifts > 0) {
            this.length = length;
            this.diameter = diameter;
            this.numberOfChairLifts = numberOfChairLifts;

            this.chairLifts = new ArrayList<>();
        }
    }

    // -------------------------------------------------------------------------------- Help Methods
    // ----------------------------------------------------------------------------- General Methods

    /**
     * Add an chair lift on Id.
     *
     * @param id        Id of the chair lift.
     * @param chairLift Chair lift to add.
     * @return Added chair lift.
     * @throws ChairLiftOnWrongPlaceException If the id is not valid throws exception.
     */
    public synchronized Thread addChairLift(int id, Thread chairLift) throws ChairLiftOnWrongPlaceException {
        synchronized (this) {
            if (id > 0) {
                if (id < this.getNumberOfChairLifts()) {
                    this.chairLifts.add(id, chairLift);

                    return chairLift;
                }

                throw new ChairLiftOnWrongPlaceException("Too large id of the chair lift");
            }

            throw new ChairLiftOnWrongPlaceException("The small id of the chair lift");
        }
    }

    /**
     * Append to the cable a chair lift.
     *
     * @param chairLift Chair lift to append.
     * @return Appended chair lift.
     * @throws ChairLiftOnWrongPlaceException If the cable is full do not append chair lift.
     */
    public synchronized Thread appendChairLift(Thread chairLift) throws ChairLiftOnWrongPlaceException {
        synchronized (this) {
            if (this.chairLifts.size() < this.getNumberOfChairLifts()) {
                this.chairLifts.add(chairLift);

                return chairLift;
            }

            throw new ChairLiftOnWrongPlaceException("The cable is full, no more space for others chair lifts.");
        }
    }

    /**
     * Remove the chair lift from the cable, using the chair lift.
     *
     * @param chairLift Chair lift to remove from the cable.
     * @return Removed chair lift.
     */
    public synchronized Thread removeChairLift(Thread chairLift) {
        synchronized (this) {
            this.chairLifts.remove(chairLift);

            return chairLift;
        }
    }

    /**
     * Remove the chair lift from the cable, using the index.
     *
     * @param index Index of the chair lift to remove.
     * @return Removed chair lift.
     */
    public synchronized Thread removeChairLift(int index) {
        synchronized (this) {
            Thread removed = this.chairLifts.get(index);
            this.removeChairLift(removed);

            return removed;
        }
    }

    // --------------------------------------------------------------------------- Static Components

}
