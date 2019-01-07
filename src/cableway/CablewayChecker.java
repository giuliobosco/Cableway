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
 * @author giuliobosco
 * @version 1.0
 */
public class CablewayChecker extends Thread {
    // ------------------------------------------------------------------------------------ Costants
    // ---------------------------------------------------------------------------------- Attributes

    private Cabin cabin0;

    private Cabin cabin1;

    private Cable cable;

    private Station lowerStation;

    private Station upperStation;

    private List<ExceptionListener> exceptionListeners;

    // --------------------------------------------------------------------------- Getters & Setters

    public List<ExceptionListener> getExceptionListeners() {
        return this.exceptionListeners;
    }

    public void addExceptionListener(ExceptionListener exceptionListener) {
        this.getExceptionListeners().add(exceptionListener);
    }

    public void removeExceptionListener(ExceptionListener exceptionListener) {
        this.getExceptionListeners().remove(exceptionListener);
    }

    public void exceptionThrower(Exception e) {
        for (ExceptionListener exceptionListener : this.getExceptionListeners()) {
            exceptionListener.exceptionThrown(e);
        }
    }
    
    // -------------------------------------------------------------------------------- Constructors
    // -------------------------------------------------------------------------------- Help Methods

    public void checkCabins() throws CablewayException {
        if (this.cabin0 == this.cabin1) {
            throw new CablewayException(
                    CablewayException.FATAL_TEXT + "\nRequired different 2 cabins",
                    CablewayException.FATAL);
        }
    }

    public void checkCabin1() throws CablewayException {
        if (this.cabin1.getCable() != this.cable) {
            throw new CableException(
                    CablewayException.FATAL_TEXT + "\nCabin 1 connected to wrong cable.",
                    CablewayException.FATAL);
        }
    }

    public void checkCabin0() throws CablewayException {
        if (this.cabin0.getCable() != this.cable) {
            throw new CableException(
                    CablewayException.FATAL_TEXT + "\nCabin 0 connected to wrong cable.",
                    CablewayException.FATAL);
        }
    }

    // ----------------------------------------------------------------------------- General Methods

    public void run() {
    }

    // --------------------------------------------------------------------------- Static Components

}
