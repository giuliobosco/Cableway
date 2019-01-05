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

import java.util.List;

/**
 * @author giuliobosco
 * @version 1.0
 */
public class ChairLiftHouse extends Thread {
    // ------------------------------------------------------------------------------------ Costants
    // ---------------------------------------------------------------------------------- Attributes

    /**
     * Cable in the chair lift house.
     */
    private Cable cable;

    /**
     * Cable index in the chair lift house.
     */
    private int cableIndex;

    /**
     * Meter above the sea level of the chair lift house.
     */
    private double meterAboveSeaLevel;

    /**
     * Gate on.
     */
    private boolean gateOn;

    /**
     * Chair lifts in the garage.
     */
    private List<ChairLift> chairGarage;

    /**
     * Loading chair lift, is the chair lift where the people are sitting up at the moment.
     */
    private ChairLift loadingChairLift;

    // --------------------------------------------------------------------------- Getters & Setters

    /**
     * Get the cable in the chair house.
     *
     * @return Cable in the chair house.
     */
    public Cable getCable() {
        return this.cable;
    }

    /**
     * Set the cable index in the chair lift house.
     *
     * @param cableIndex Cable index in the chair lift house.
     * @throws ChairLiftOnWrongPlaceException Wrong cable index.
     */
    public void setCableIndex(int cableIndex) throws ChairLiftOnWrongPlaceException {
        if (cableIndex > 0 && cableIndex < this.cable.getNumberOfChairLifts()) {
            this.cableIndex = cableIndex;
        }

        throw new ChairLiftOnWrongPlaceException("Wrong cable index.");
    }

    /**
     * Get the cable index in the chair lift house.
     *
     * @return Cable index in the chair lift house.
     */
    public int getCableIndex() {
        return this.cableIndex;
    }

    /**
     * Get the meter above the sea level of the chair lift house.
     *
     * @return Meter above the sea level of the chair lift house.
     */
    public double getMeterAboveSeaLevel() {
        return this.meterAboveSeaLevel;
    }

    /**
     * Is the gate on.
     *
     * @return Gate on.
     */
    public boolean isGateOn() {
        return this.gateOn;
    }

    /**
     * Se the gate on.
     *
     * @param gateOn Gate on.
     */
    public void setGateOn(boolean gateOn) {
        this.gateOn = gateOn;
    }

    /**
     * Get the chair lifts in the garage.
     *
     * @return Chair lifts in the garage.
     */
    public List<ChairLift> getChairGarage() {
        return this.chairGarage;
    }

    // -------------------------------------------------------------------------------- Constructors

    /**
     * Create the chair lift house.
     *
     * @param name               Name of the chair lift house.
     * @param meterAboveSeaLevel Meters on the sea level.
     * @param cable              Cable in the lift house.
     */
    public ChairLiftHouse(String name, double meterAboveSeaLevel, Cable cable) {
        this.setName(name);
        this.meterAboveSeaLevel = meterAboveSeaLevel;
        this.cable = cable;
    }

    // -------------------------------------------------------------------------------- Help Methods
    // ----------------------------------------------------------------------------- General Methods
    // --------------------------------------------------------------------------- Static Components

}
