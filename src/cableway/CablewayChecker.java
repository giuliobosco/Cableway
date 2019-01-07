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

package cableway;

import cableway.cabin.Cabin;
import cableway.cable.Cable;
import cableway.cable.CableException;
import cableway.station.Station;

import java.beans.ExceptionListener;
import java.util.List;

/**
 * Cableway checker, check all the cableway, check that everything works correctly.
 *
 * @author giuliobosco
 * @version 1.0
 */
public class CablewayChecker extends Thread {
    // ------------------------------------------------------------------------------------ Costants

    /**
     * Time between checks.
     */
    public static final long TIME_CHECK = 100;

    // ---------------------------------------------------------------------------------- Attributes

    /**
     * Cabin 0 of the cableway.
     */
    private Cabin cabin0;

    /**
     * Cabin 1 of the cableway.
     */
    private Cabin cabin1;

    /**
     * Cable of the cableway.
     */
    private Cable cable;

    /**
     * Lower cableway station.
     */
    private Station lowerStation;

    /**
     * Upper cableway station.
     */
    private Station upperStation;

    /**
     * List of exception listeners.
     */
    private List<ExceptionListener> exceptionListeners;

    // --------------------------------------------------------------------------- Getters & Setters

    /**
     * Get the exception listeners list.
     *
     * @return Exception listeners list.
     */
    public List<ExceptionListener> getExceptionListeners() {
        return this.exceptionListeners;
    }

    /**
     * Add an exception listener to the exception listeners list.
     *
     * @param exceptionListener Exception listener to add.
     */
    public void addExceptionListener(ExceptionListener exceptionListener) {
        this.getExceptionListeners().add(exceptionListener);
    }

    /**
     * Remove an exception listener from the exception listeners list.
     *
     * @param exceptionListener Exception listener to remove.
     */
    public void removeExceptionListener(ExceptionListener exceptionListener) {
        this.getExceptionListeners().remove(exceptionListener);
    }

    /**
     * Throw exception to all exception listeners, in the exception listeners list.
     *
     * @param e Exception to throw.
     */
    public void exceptionThrower(Exception e) {
        for (ExceptionListener exceptionListener : this.getExceptionListeners()) {
            exceptionListener.exceptionThrown(e);
        }
    }

    // -------------------------------------------------------------------------------- Constructors
    // -------------------------------------------------------------------------------- Help Methods

    /**
     * Check the cabins.
     *
     * @throws CablewayException Cableway exception, error on the cabin.
     */
    public void checkCabins() throws CablewayException {
        if (this.cabin0 == this.cabin1) {
            throw new CablewayException(
                    CablewayException.FATAL_TEXT + "\nRequired different 2 cabins",
                    CablewayException.FATAL);
        }
    }

    /**
     * Check the cabin 0.
     *
     * @throws CablewayException Cableway exception, error on the cabin 0.
     */
    public void checkCabin0() throws CablewayException {
        this.cabin0.checkCabin();

        if (this.cabin0.getCable() != this.cable) {
            throw new CableException(
                    CablewayException.FATAL_TEXT + "\nCabin 0 connected to wrong cable.",
                    CablewayException.FATAL);
        }
    }

    /**
     * Check the cabin 1.
     *
     * @throws CablewayException Cableway exception, error on the cabin 1.
     */
    public void checkCabin1() throws CablewayException {
        this.cabin1.checkCabin();

        if (this.cabin1.getCable() != this.cable) {
            throw new CableException(
                    CablewayException.FATAL_TEXT + "\nCabin 1 connected to wrong cable.",
                    CablewayException.FATAL);
        }
    }

    /**
     * Check the cable.
     *
     * @throws CablewayException Cableway exception, error on the cabin.
     */
    public void checkCable() throws CablewayException {
        this.cable.checkCable();
    }

    // ----------------------------------------------------------------------------- General Methods

    /**
     * Run the cableway checker.
     */
    public void run() {
        while (!this.isInterrupted()) {
            try {
                this.checkCabins();
                this.checkCabin0();
                this.checkCabin1();

                this.checkCable();

                Thread.sleep(TIME_CHECK);
            } catch (InterruptedException | CablewayException e) {
                this.exceptionThrower(e);
            }
        }
    }

    // --------------------------------------------------------------------------- Static Components

}
