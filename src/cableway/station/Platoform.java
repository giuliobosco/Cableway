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

package cableway.station;

import cableway.CablewayException;
import cableway.cabin.Cabin;

/**
 * Cableway station platform.
 *
 * @author giuliobosco
 * @version 1.0.5
 */
public class Platoform {
    // ------------------------------------------------------------------------------------ Costants
    // ---------------------------------------------------------------------------------- Attributes

    /**
     * Platform left door for the cabin.
     */
    private boolean leftDoor;

    /**
     * Platform right door for the cabin 0.
     */
    private boolean rightDoor;

    /**
     * Platform cabin ready to move.
     */
    private boolean cabinReady;

    /**
     * Platform cabin.
     */
    private Cabin cabin;

    /**
     * Platform cable position.
     */
    private double cablePosition;

    // --------------------------------------------------------------------------- Getters & Setters

    /**
     * Set the platform cabin ready to move.
     *
     * @param cabinReady Platform cabin ready to move.
     */
    public void setCabinReady(boolean cabinReady) {
        if (cabinReady) {
            this.closeLeftDoor();
            this.closeRightDoor();
        }
        this.cabinReady = cabinReady;
    }

    /**
     * Is the platform cabin ready to move.
     *
     * @return Platform cabin ready to move.
     */
    public boolean isCabinReady() {
        return this.cabinReady;
    }

    // -------------------------------------------------------------------------------- Constructors

    /**
     * Create the platform with the platform cabin.
     *
     * @param cabin Platform cabin.
     */
    public Platoform(Cabin cabin, double cablePosition) {
        this.cabin = cabin;
        this.cablePosition = cablePosition;
        this.leftDoor = false;
        this.rightDoor = false;
    }

    // -------------------------------------------------------------------------------- Help Methods

    /**
     * Open platform left door.
     *
     * @throws CablewayException Cableway cable in wrong position or cabin ready.
     */
    public void openLeftDoor() throws CablewayException {
        this.leftDoor = false;

        if (this.cabin.getCable().getPosition() != this.cablePosition) {
            throw new CablewayException("Cable wrong position, can't open doors", CablewayException.DANGER);
        } else if (this.isCabinReady()) {
            throw new CablewayException("Cabin ready to move, can't open doors", CablewayException.DANGER);
        } else {
            this.leftDoor = true;
        }
    }

    /**
     * Close platform left door.
     */
    public void closeLeftDoor() {
        this.leftDoor = false;
    }

    /**
     * Open platform right door.
     *
     * @throws CablewayException Cableway cable in wrong position or cabin ready.
     */
    public void openRightDoor() throws CablewayException {
        this.rightDoor = false;

        if (this.cabin.getCable().getPosition() != this.cablePosition) {
            throw new CablewayException("Cable wrong position, can't open doors", CablewayException.DANGER);
        } else if (this.isCabinReady()) {
            throw new CablewayException("Cabin ready to move, can't open doors", CablewayException.DANGER);
        } else {
            this.rightDoor = true;
        }
    }

    /**
     * Close platform right door.
     */
    public void closeRightDoor() {
        this.rightDoor = false;
    }

    /**
     * Check the cable way platform.
     *
     * @throws CablewayException Door open while moving.
     */
    public void check() throws CablewayException {
        if (this.cabin.getCable().getPosition() != this.cablePosition) {
            if (this.leftDoor || this.rightDoor) {
                throw new CablewayException("Door open while moving.", CablewayException.FATAL);
            }

            if (!this.isCabinReady()) {
                throw new CablewayException("Cabin no ready and moving", CablewayException.FATAL);
            }
        }
    }

    // ----------------------------------------------------------------------------- General Methods
    // --------------------------------------------------------------------------- Static Components

}
