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

import cableway.people.skypass.Skipass;
import cableway.people.skypass.SkipassManager;

/**
 * Cableway gate.
 *
 * @author giuliobosco
 * @version 1.0
 */
public class Gate {
    // ------------------------------------------------------------------------------------ Costants

    /**
     * Default value of blue line.
     * Value: false.
     */
    private static final boolean DEFAUL_BLUE_LINE = false;

    // ---------------------------------------------------------------------------------- Attributes

    /**
     * Blue line gate.
     */
    private boolean blueline;

    /**
     * Gate is open.
     */
    private boolean open;

    /**
     * Cableway skipass manager.
     */
    private SkipassManager skipassManager;

    // --------------------------------------------------------------------------- Getters & Setters

    /**
     * Is the blue line gate.
     *
     * @return Blue line gate.
     */
    public boolean isBlueline() {
        return this.blueline;
    }

    /**
     * Is the gate open.
     *
     * @return Gate open.
     */
    public boolean isOpen() {
        return this.open;
    }

    // -------------------------------------------------------------------------------- Constructors

    /**
     * Create the gate with the blue line and the skipass manager.
     *
     * @param skipassManager Skipass manager.
     * @param blueline       Blue line.
     */
    public Gate(SkipassManager skipassManager, boolean blueline) {
        this.skipassManager = skipassManager;
        this.blueline = blueline;
    }

    /**
     * Create the gate skipass manager and the default blue line.
     *
     * @param skipassManager Skipass manager.
     */
    public Gate(SkipassManager skipassManager) {
        this(skipassManager, DEFAUL_BLUE_LINE);
    }

    // -------------------------------------------------------------------------------- Help Methods
    // ----------------------------------------------------------------------------- General Methods

    /**
     * Check the skipass.
     *
     * @param skipass Skipass to check.
     * @return True if the skipass is valid.
     */
    public boolean open(Skipass skipass) {
        if (this.isBlueline()) {
            return this.skipassManager.isValid(skipass) && this.skipassManager.isBlueline(skipass);
        } else {
            if (!this.isOpen()) {
                return this.skipassManager.isValid(skipass);
            } else {
                return true;
            }
        }
    }

    // --------------------------------------------------------------------------- Static Components

}
