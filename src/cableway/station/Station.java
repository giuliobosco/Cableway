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

import cableway.people.PeopleSet;

/**
 * Cableway station class.
 *
 * @author giuliobosco
 * @version 1.0
 */
public class Station extends Thread {
    // ------------------------------------------------------------------------------------ Costants
    // ---------------------------------------------------------------------------------- Attributes

    /**
     * People ready to step up on the cabin.
     */
    private PeopleSet readyPeople;

    /**
     * Is the in people area active, if is active will be used 2 people sets, else will be used only
     * one (readyPeople).
     */
    private boolean inPeopleActive;

    /**
     * People ready to go in the ready people space.
     */
    private PeopleSet inPeople;

    /**
     * Station left door for the cabin 0.
     */
    private boolean cabin0LeftDoor;

    /**
     * Station right door for the cabin 0.
     */
    private boolean cabin0RightDoor;

    /**
     * Station left door for the cabin 1.
     */
    private boolean cabin1LeftDoor;

    /**
     * Station right door for the cabin 1.
     */
    private boolean cabin1RightDoor;

    /**
     * Station cabin 0 ready to move.
     */
    private boolean cabin0ready;

    /**
     * Station cabin 1 ready to move.
     */
    private boolean cabin1ready;

    // --------------------------------------------------------------------------- Getters & Setters

    /**
     * Set the people ready to step up on the cabin.
     *
     * @param readyPeople People ready to step up on the cabin.
     */
    public void setReadyPeople(PeopleSet readyPeople) {
        this.readyPeople = readyPeople;
    }

    /**
     * Get the people ready to step up on the cabin.
     *
     * @return People ready to step up on the cabin.
     */
    public PeopleSet getReadyPeople() {
        return this.readyPeople;
    }

    /**
     * Set the is the in people area active.
     *
     * @param inPeopleActive Is the in people area active.
     */
    public void setInPeopleActive(boolean inPeopleActive) {
        this.inPeopleActive = inPeopleActive;
    }

    /**
     * Get the is the in people area active.
     *
     * @return Is the in people area active.
     */
    public boolean isInPeopleActive() {
        return this.inPeopleActive;
    }

    /**
     * Set the people ready to go in the ready people space.
     *
     * @param inPeople People ready to go in the ready people space.
     */
    public void setInPeople(PeopleSet inPeople) {
        this.inPeople = inPeople;
    }

    /**
     * Get the people ready to go in the ready people space.
     *
     * @return People ready to go in the ready people space.
     */
    public PeopleSet getInPeople() {
        return this.inPeople;
    }

    // -------------------------------------------------------------------------------- Constructors
    // -------------------------------------------------------------------------------- Help Methods
    // ----------------------------------------------------------------------------- General Methods
    // --------------------------------------------------------------------------- Static Components

}
