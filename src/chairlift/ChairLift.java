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
 * 
 * @author giuliobosco
 * @version 1.0
 */
public class ChairLift {
    // ------------------------------------------------------------------------------------ Costants
    // ---------------------------------------------------------------------------------- Attributes

    /**
     * Id of the Chair Lift.
     */
    private int id;

    /**
     * SkiPasses on the chair lift.
     */
    private List<Thread> skiPasses;

    // --------------------------------------------------------------------------- Getters & Setters

    /**
     * Get the id of the chair lift.
     *
     * @return Id of the chair lift.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Get the SkiPasses on the chair lift.
     *
     * @return SkiPasses on the chair lift.
     */
    public List<Thread> getSkiPasses() {
        return this.skiPasses;
    }

    // -------------------------------------------------------------------------------- Constructors

    /**
     * Create the chair lift.
     *
     * @param id Id of the chair lift.
     */
    public ChairLift(int id) {
        if (id > 0) {
            this.id = id;
        }

        this.skiPasses = new ArrayList<>();
    }

    // -------------------------------------------------------------------------------- Help Methods
    // ----------------------------------------------------------------------------- General Methods
    // --------------------------------------------------------------------------- Static Components
    
}
