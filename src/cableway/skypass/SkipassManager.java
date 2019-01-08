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
 
package cableway.skypass;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author giuliobosco
 * @version 1.0
 */
public class SkipassManager {
    // ------------------------------------------------------------------------------------ Costants
    // ---------------------------------------------------------------------------------- Attributes

    /**
     * List of the skipasses.
     */
    private List<Skipass> skipasses;

    // --------------------------------------------------------------------------- Getters & Setters

    /**
     * Add skipass to the skipasses list.
     *
     * @param skipass Skipass to add.
     */
    public void addSkipass(Skipass skipass) {
        this.skipasses.add(skipass);
    }

    /**
     * Remove skipass from the skipasses list.
     *
     * @param skipass Skipass to remove.
     */
    public void removeSkipass(Skipass skipass) {
        this.skipasses.remove(skipass);
    }

    /**
     * Clear the list of skipasses.
     */
    public void clear() {
        this.skipasses.clear();
    }

    // -------------------------------------------------------------------------------- Constructors

    /**
     * Create skipass manager initialize the list of skipasses.
     */
    public SkipassManager() {
        this.skipasses = new ArrayList<>();
    }

    // -------------------------------------------------------------------------------- Help Methods

    /**
     * Is a skipass valid.
     *
     * @param skipass Skipass to check.
     * @return True if the skipass is valid.
     */
    public boolean isValid(Skipass skipass) {
        for (Skipass s : this.skipasses) {
            if (s == skipass && skipass.isValid()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Has the skipass the blue line.
     *
     * @param skipass Skipass to check.
     * @return True if skipass has blue line.
     */
    public boolean isBlueline(Skipass skipass) {
        for (Skipass s : this.skipasses) {
            if (s == skipass && skipass.isBlueline()) {
                return true;
            }
        }

        return false;
    }

    // ----------------------------------------------------------------------------- General Methods
    // --------------------------------------------------------------------------- Static Components
    
}
