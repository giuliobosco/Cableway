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

import cableway.CablewayActionManager;
import cableway.CablewayException;
import cableway.cabin.Cabin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Cableway station platform.
 *
 * @author giuliobosco
 * @version 1.1.2
 */
public class Platform extends Thread implements ActionListener {
    // ------------------------------------------------------------------------------------ Costants

    /**
     * Time for exit the cabin.
     */
    private final long PEOPLE_EXIT_TIME = 10000;

    // ---------------------------------------------------------------------------------- Attributes

    /**
     * Platform internal door is open for the cabin.
     */
    private boolean internalDoorOpen;

    /**
     * Platform external door is open for the cabin 0.
     */
    private boolean externalDoorOpen;

    /**
     * Platform ready to move.
     */
    private boolean ready;

    /**
     * Platform cabin.
     */
    private Cabin cabin;

    /**
     * Platform cable position.
     */
    private double cablePosition;

    /**
     * Cableway action manager.
     */
    private CablewayActionManager cablewayActionManager;

    // --------------------------------------------------------------------------- Getters & Setters

    /**
     * Set the platform ready to move.
     *
     * @param ready Platform ready to move.
     */
    public void setReady(boolean ready) throws CablewayException {
        if (ready) {
            this.closeExternalDoor();
            this.closeExternalDoor();
        }
        this.ready = ready;
    }

    /**
     * Is the platform ready to move.
     *
     * @return Platform ready to move.
     */
    public boolean isReady() {
        return this.ready;
    }

    /**
     * Get the platform cabin.
     *
     * @return Platform cabin.
     */
    public Cabin getCabin() {
        return this.cabin;
    }

    /**
     * Is the cabin in this platform.
     *
     * @return True if the cabin is in this platform.
     */
    public boolean isCabinHere() {
        return this.cabin.getCable().getPosition() == this.cablePosition && this.cabin.getCable().getSpeed() == 0;
    }

    // -------------------------------------------------------------------------------- Constructors

    /**
     * Create the platform with the platform cabin.
     *
     * @param cabin Platform cabin.
     * @param cablePosition Platform cable position.
     * @param cablewayActionManager Cableway action mangaer.
     */
    public Platform(Cabin cabin, double cablePosition, CablewayActionManager cablewayActionManager) {
        this.cabin = cabin;
        this.cablePosition = cablePosition;
        this.cablewayActionManager = cablewayActionManager;
        this.internalDoorOpen = false;
        this.externalDoorOpen = false;
    }

    // -------------------------------------------------------------------------------- Help Methods

    /**
     * Open platform left door.
     *
     * @throws CablewayException Cableway cable in wrong position or cabin ready.
     */
    public void openInternalDoor() throws CablewayException {
        this.internalDoorOpen = false;

        if (this.cabin.getCable().getPosition() != this.cablePosition) {
            throw new CablewayException("Cable wrong position, can't open doors", CablewayException.DANGER);
        } else if (this.isReady()) {
            throw new CablewayException("Cabin ready to move, can't open doors", CablewayException.DANGER);
        } else {
            this.internalDoorOpen = true;
        }
    }

    /**
     * Close platform left door.
     */
    public void closeInternalDoor() {
        this.internalDoorOpen = false;
    }

    /**
     * Open platform right door.
     *
     * @throws CablewayException Cableway cable in wrong position or cabin ready.
     */
    public void openExternalDoor() throws CablewayException {
        this.externalDoorOpen = false;

        if (this.cabin.getCable().getPosition() != this.cablePosition) {
            throw new CablewayException("Cable wrong position, can't open doors", CablewayException.DANGER);
        } else if (this.isReady()) {
            throw new CablewayException("Cabin ready to move, can't open doors", CablewayException.DANGER);
        } else {
            this.externalDoorOpen = true;
        }
    }

    /**
     * Close platform right door.
     */
    public void closeExternalDoor() {
        this.externalDoorOpen = false;
    }

    /**
     * Check the cable way platform.
     *
     * @throws CablewayException Door open while moving.
     */
    public void check() throws CablewayException {
        if (this.cabin.getCable().getPosition() != this.cablePosition) {
            if (this.internalDoorOpen || this.externalDoorOpen) {
                throw new CablewayException("Door open while moving.", CablewayException.FATAL);
            }

            if (!this.isReady()) {
                throw new CablewayException("Cabin no ready and moving", CablewayException.FATAL);
            }
        }
    }

    // ----------------------------------------------------------------------------- General Methods

    /**
     * Arrived cabin to the platform.
     *
     * @throws CablewayException Cabin errors.
     */
    private void cabinArrived() throws CablewayException {
        try {
            this.setReady(false);
            this.cabin.setReady(false);

            this.openExternalDoor();
            this.cabin.openExternallDoor();

            Thread.sleep(PEOPLE_EXIT_TIME);

            this.openInternalDoor();
            this.cabin.openInternalDoor();

            Thread.sleep(500);

            this.closeExternalDoor();
            this.cabin.closeExternalDoor();

            Thread.sleep(1);
        } catch (InterruptedException ie) {
            this.cablewayActionManager.exceptionThrower(ie);
        }
    }

    /**
     * Prepare the cabin to start.
     *
     * @throws CablewayException Cableway exception.
     */
    private void prepareCabin() throws CablewayException {

        this.cabin.closeExternalDoor();
        this.closeExternalDoor();
        this.cabin.closeInternalDoor();
        this.closeInternalDoor();

        this.setReady(true);
    }

    /**
     * Action performed, listener used.
     *
     * @param e Action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource().getClass() == Cabin.class) {
                if (e.getActionCommand().equals(Cabin.ARRIVED)) {
                    this.cabinArrived();
                }

                if (e.getActionCommand().equals(Cabin.READY)) {
                    this.prepareCabin();
                }
            }
        } catch (CablewayException ce) {
            this.cablewayActionManager.exceptionThrower(ce);
        }
    }

    // --------------------------------------------------------------------------- Static Components

}
