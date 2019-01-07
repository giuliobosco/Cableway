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

package cableway.cable;

import cableway.CablewayException;
import cableway.cabin.Cabin;

/**
 * Cableway cable, keep the length, the position and the speed of the cableway cable.
 *
 * @author giuliobosco
 * @version 1.1
 */
public class Cable {

    // ------------------------------------------------------------------------------------ Costants

    /**
     * Change the cabin speed of the percentage to the current speed.
     */
    public final static double SPEED_EDIT = 0.05;

    /**
     * Maximum speed of the cabin, in kilometer per hour [km/h].
     */
    public final static double MAX_SPEED = 23.2;

    // ---------------------------------------------------------------------------------- Attributes

    /**
     * Length of the cable.
     */
    private double lenght;

    /**
     * Position of the cable.
     */
    private double position;

    /**
     * Speed of the cable.
     */
    private double speed;

    /**
     * Cabin 0, connected to one end of the cable, opposite of cabin 1.
     */
    private Cabin cabin0;

    /**
     * Cabin 1, connected to on end of the cable, opposite of cabin 0.
     */
    private Cabin cabin1;

    // --------------------------------------------------------------------------- Getters & Setters

    /**
     * Set the length of the cable.
     * Checks that the length is a real value, so bigger than 0.
     *
     * @param length Length of the cable.
     */
    private void setLength(double length) throws CableLengthException {
        this.checkLength(length);
        this.lenght = length;
    }

    /**
     * Get the length of the cable.
     *
     * @return Length of the cable.
     */
    public double getLength() {
        return this.lenght;
    }

    /**
     * Set the position of the cable.
     * Check that the position has a valid value, so bigger than 0 and smaller than the length of
     * the cable.
     *
     * @param position Position of the cable.
     */
    public void setPosition(double position) throws CablePositionException {
        this.checkPosition(position);
        this.position = position;
    }

    /**
     * Get the position of the cable.
     *
     * @return Position of the cable.
     */
    public double getPosition() {
        return this.position;
    }

    /**
     * Set the speed of the cable.
     * Check that the speed has a valid value, so bigger than maximum speed and smaller than the
     * maximum speed.
     *
     * @param speed Speed of the cable.
     */
    public void setSpeed(double speed) throws CableSpeedException {
        this.checkSpeed(speed);
        this.speed = speed;
    }

    /**
     * Get the speed of the cable.
     *
     * @return Speed of the cable.
     */
    public double getSpeed() {
        return this.speed;
    }

    // -------------------------------------------------------------------------------- Constructors

    /**
     * Create the cable, with the length of the cable.
     * Sets position to 0 and speed too.
     *
     * @param length Length of the cable.
     * @throws CablewayException Cableway exception,
     */
    public Cable(double length, Cabin cabin0, Cabin cabin1) throws CablewayException {
        if (cabin0 != cabin1) {
            this.setLength(length);
            this.cabin0 = cabin0;
            this.cabin1 = cabin1;
            this.setPosition(0);
            this.setSpeed(0);
        } else {
            String message = CablewayException.FATAL_TEXT + "Cabins cant't be the same.";
            throw new CablewayException(message, CablewayException.FATAL);
        }
    }

    // -------------------------------------------------------------------------------- Help Methods

    /**
     * Check the position of the cable with the cabins, the cable have to be at position 0 or length
     * if one of the cabin is not ready.
     *
     * @throws CablewayException Cableway exception, cabins can't move.
     */
    private void checkCabinPosition() throws CablewayException {
        if (this.getPosition() != 0 && this.getPosition() != this.getLength()) {
            String message = CablewayException.FATAL + "\nCabin 0: can't move, is not ready";

            if (!this.cabin0.isReady()) {
                throw new CablewayException(message, CablewayException.FATAL);
            }

            if (!this.cabin1.isReady()) {
                throw new CablewayException(message, CablewayException.FATAL);
            }
        }
    }

    /**
     * Check the length of the cable.
     *
     * @param length Length of the cable.
     * @throws CableLengthException Cable length exception, not valid length.
     */
    private void checkLength(double length) throws CableLengthException {
        if (length <= 0) {
            String message = CablewayException.FATAL_TEXT + "\nThe cable must longer than 0";
            throw new CableLengthException(message);
        }
    }

    /**
     * Check the length attribute of the cable.
     *
     * @throws CableLengthException Cable length exception, not valid length.
     */
    private void checkLength() throws CableLengthException {
        this.checkLength(this.getLength());
    }

    /**
     * Check the position of the cable.
     * Must be bigger or equal to 0 and smaller or equal to the length of the cable.
     *
     * @param position Position of the cable.
     * @throws CablePositionException Cable position exception, not valid position.
     */
    private void checkPosition(double position) throws CablePositionException {
        if (position < 0 || position > this.getLength()) {
            throw new CablePositionException(this);
        }
    }

    /**
     * Check the position of the cable.
     *
     * @throws CablePositionException Cable position exception, not valid position.
     */
    private void checkPosition() throws CablePositionException {
        this.checkPosition(this.getPosition());
    }

    /**
     * Check the speed of the cable.
     * Must be bigger than minus max speed and smaller than max speed.
     *
     * @param speed Cabin speed.
     * @throws CableSpeedException Cabin speed exception, not valid speed.
     */
    private void checkSpeed(double speed) throws CableSpeedException {
        if (speed > MAX_SPEED || speed < -MAX_SPEED) {
            throw new CableSpeedException(this);
        }
    }

    /**
     * Check the speed of the cable.
     *
     * @throws CableSpeedException Cabin speed exception, not valid speed.
     */
    private void checkSpeed() throws CableSpeedException {
        this.checkSpeed(this.getSpeed());
    }

    /**
     * Execute the check on the cable.
     *
     * @throws CablewayException Cableway exception, errors in the cableway.
     */
    public void checkCable() throws CablewayException {
        this.checkLength();
        this.checkPosition();
        this.checkSpeed();
    }

    /**
     * Increment the position of the cable.
     *
     * @param x Value to increment.
     */
    public void incrementPosition(double x) throws CablePositionException {
        this.setPosition(this.getPosition() + x);
    }

    /**
     * Decrement the position of the cable.
     *
     * @param x Value to decrement.
     */
    public void decrementPosition(double x) throws CablePositionException {
        this.setPosition(this.getPosition() - x);
    }

    /**
     * Increment the speed of the cable.
     */
    public void incrementSpeed() throws CableSpeedException {
        if (this.getSpeed() == 0) {
            this.speed++;
        }

        if (this.getSpeed() > 0) {
            this.setSpeed(this.getSpeed() + this.getSpeed() * SPEED_EDIT);
        } else {
            this.setSpeed(this.getSpeed() - this.getSpeed() * SPEED_EDIT);
        }
    }

    /**
     * Decrement the speed of the cable.
     */
    public void decrementSpeed() throws CableSpeedException {
        if (this.getSpeed() == 0) {
            this.speed--;
        }

        if (this.getSpeed() > 0) {
            this.setSpeed(this.getSpeed() - this.getSpeed() * SPEED_EDIT);
        } else {
            this.setSpeed(this.getSpeed() + this.getSpeed() * SPEED_EDIT);
        }
    }

    // ----------------------------------------------------------------------------- General Methods
    // --------------------------------------------------------------------------- Static Components
}
