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

package cableway.people.skypass;

/**
 * Cableway skipass.
 *
 * @author giuliobosco
 * @version 1.0
 */
public class Skipass {
    // ------------------------------------------------------------------------------------ Costants

    /**
     * Default the skipass is valid.
     */
    private static final boolean DEFAULT_VALID = true;

    /**
     * Default the skipass has no blue line.
     */
    private static final boolean DEFAULT_BLUELINE = false;

    // ---------------------------------------------------------------------------------- Attributes

    /**
     * Skipass valid.
     */
    private boolean valid;

    /**
     * Skipass has blueline.
     */
    private boolean blueline;

    // --------------------------------------------------------------------------- Getters & Setters

    /**
     * Set the skipass valid.
     *
     * @param valid Skipass valid
     */
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    /**
     * Is Skipass valid.
     *
     * @return Skipass valid.
     */
    public boolean isValid() {
        return this.valid;
    }

    /**
     * Set the blue line.
     *
     * @param blueline Blue line.
     */
    public void setBlueline(boolean blueline) {
        this.blueline = blueline;
    }

    /**
     * Is the skipass valid.
     *
     * @return Skipass valid.
     */
    public boolean isBlueline() {
        return this.blueline;
    }

    // -------------------------------------------------------------------------------- Constructors

    /**
     * Create the skipass with the valid and the blueline.
     *
     * @param valid Skipass valid.
     * @param blueline Skipass has blue line.
     */
    public Skipass(boolean valid, boolean blueline) {
        this.valid = valid;
        this.blueline = blueline;
    }

    /**
     * Create the skipass with the valid, blue line default value.
     *
     * @param valid Skipass valid.
     */
    public Skipass(boolean valid) {
        this(valid, DEFAULT_BLUELINE);
    }

    /**
     * Create the skipass with all the defaul value.
     */
    public Skipass() {
        this(DEFAULT_VALID);
    }

    // -------------------------------------------------------------------------------- Help Methods
    // ----------------------------------------------------------------------------- General Methods
    // --------------------------------------------------------------------------- Static Components

}
