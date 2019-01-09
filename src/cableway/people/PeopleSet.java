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
 
package cableway.people;

import java.util.ArrayList;
import java.util.List;

/**
 * People set.
 *
 * @author giuliobosco
 * @version 1.0.2
 */
public class PeopleSet {
    // ------------------------------------------------------------------------------------ Costants
    // ---------------------------------------------------------------------------------- Attributes

    /**
     * People of the people set.
     */
    private List<Person> people;

    /**
     * Maximum people in the people set.
     */
    private int maxPeople;

    // --------------------------------------------------------------------------- Getters & Setters

    /**
     * Get the people of the people set.
     *
     * @return People of the people set.
     */
    public List<Person> getPeople() {
        return this.people;
    }


    // -------------------------------------------------------------------------------- Constructors

    /**
     * Create the people set with the maximum people in the people set and initialize the people
     * list.
     *
     * @param maxPeople Maximum people in the people set.
     * @throws FullSetException Not enough people for the maximum people set.
     */
    public PeopleSet(int maxPeople) throws FullSetException {
        if (maxPeople > 0) {
            this.maxPeople = maxPeople;
            this.people = new ArrayList<>();
        } else {
            throw new FullSetException("Not enough people for maximum people.");
        }
    }

    // -------------------------------------------------------------------------------- Help Methods

    /**
     * Add person to the people set.
     *
     * @param person Person to add.
     * @throws FullSetException No more people can be added to the people set.
     */
    public void addPerson(Person person) throws FullSetException {
        if (this.people.size() < this.maxPeople) {
            this.people.add(person);
        } else {
            throw new FullSetException("No more people can be added to the people set.");
        }
    }

    /**
     * Remove person from the people set.
     *
     * @param person Person to remove
     */
    public void removePerson(Person person) {
        this.people.remove(person);
    }

    /**
     * Is full people set.
     *
     * @return People set is full, true if the people set is full else false.
     */
    public boolean isFull() {
        return this.people.size()==this.maxPeople;
    }

    /**
     * Is empty people set.
     *
     * @return True if people set is empty, else false.
     */
    public boolean isEmpty() {
        return this.people.size()==0;
    }

    /**
     * Empty the people list.
     */
    public void clear() {
        this.people.clear();
    }

    // ----------------------------------------------------------------------------- General Methods

    /**
     * Get the sum of the weight of all people in the people set.
     *
     * @return Sum of the weight of all people in the people set.
     */
    public double getTotalWeight() {
        double weight = 0;

        for (Person person : this.people) {
            weight += person.getWeight();
        }

        return weight;
    }

    // --------------------------------------------------------------------------- Static Components
    
}
