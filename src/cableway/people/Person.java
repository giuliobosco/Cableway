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

import cableway.people.skypass.Skipass;
import cableway.station.Station;
import help.Random;

import java.util.Date;

/**
 * Class for represent a person.
 *
 * @author giuliobosco
 * @version 1.0
 */
public class Person extends Thread {
    // ------------------------------------------------------------------------------------ Costants

    /**
     * Minimum person weight.
     */
    private static final double MIN_WEIGHT = 2;

    /**
     * Maximum person weight.
     */
    private static final double MAX_WEIGHT = 200;

    // ---------------------------------------------------------------------------------- Attributes

    /**
     * Person first name.
     */
    private String firstName;

    /**
     * Person last name.
     */
    private String lastName;

    /**
     * Person weight.
     */
    private double weight;

    /**
     * Person born date.
     */
    private Date bornDate;

    /**
     * Cableway person skipass.
     */
    private Skipass skipass;

    /**
     * Station where to take the cableway.
     */
    private Station station;

    /**
     * Person is blocked in People set.
     */
    private boolean blocked;

    // --------------------------------------------------------------------------- Getters & Setters

    /**
     * Get the person first name.
     *
     * @return Person first name.
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Get the person last name.
     *
     * @return Person last name.
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Set the person weight.
     *
     * @param weight Person weight.
     */
    public void setWeight(double weight) {
        if (weight > MIN_WEIGHT && weight < MAX_WEIGHT) {
            this.weight = weight;
        }
    }

    /**
     * Get the person weight.
     *
     * @return Person weight.
     */
    public double getWeight() {
        return this.weight;
    }

    /**
     * Get the person born date.
     *
     * @return Person born date.
     */
    public Date getBornDate() {
        return this.bornDate;
    }

    /**
     * Set the cableway person skipass.
     *
     * @param skipass Cableway person skipass.
     */
    public void setSkipass(Skipass skipass) {
        this.skipass = skipass;
    }

    /**
     * Get the cableway person skipass.
     *
     * @return Cableway person skipass.
     */
    public Skipass getSkipass() {
        return this.skipass;
    }

    /**
     * Set the station where the person take the cableway.
     *
     * @param station Station where the person take the cableway.
     */
    public void setStation(Station station) {
        this.station = station;
    }

    /**
     * Set the person is blocked in People set.
     *
     * @param blocked Person is blocked in People set.
     */
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    /**
     * Get the person is blocked in People set.
     *
     * @return Person is blocked in People set.
     */
    public boolean isBlocked() {
        return this.blocked;
    }

    // -------------------------------------------------------------------------------- Constructors

    /**
     * Create the person with first and last name, weight and born date.
     *
     * @param firstName Person first name.
     * @param lastName  Person last name.
     * @param weight    Person weight.
     * @param bornDate  Person born date.
     */
    public Person(String firstName, String lastName, double weight, Date bornDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.weight = weight;
        this.bornDate = bornDate;
    }

    /**
     * Create the person with first and last name, weight, born date and the person skipass.
     *
     * @param firstName Person first name.
     * @param lastName  Person last name.
     * @param weight    Person weight.
     * @param bornDate  Person born date.
     * @param skipass   Person skipass.
     */
    public Person(String firstName, String lastName, double weight, Date bornDate, Skipass skipass) {
        this(firstName, lastName, weight, bornDate);
        this.skipass = skipass;
    }

    // -------------------------------------------------------------------------------- Help Methods
    // ----------------------------------------------------------------------------- General Methods

    /**
     * Run the person life.
     */
    @Override
    public void run() {
        while (this.isInterrupted()) {
            try {
                if (this.station.getFreeGate().open(this.skipass)) {
                    this.station.getInPeople().addPerson(this);

                    while (!this.isBlocked()) Thread.sleep(300);

                    this.interrupt();
                }
            } catch (FullSetException | InterruptedException ignore) {

            }
        }
    }

    // --------------------------------------------------------------------------- Static Components

    /**
     * Get an random person, no first and last name, no date, only random weight.
     *
     * @return Random person.
     */
    public static Person getRandomPerson() {
        return new Person("", "", Random.getDouble(MIN_WEIGHT, MAX_WEIGHT), new Date());
    }

}
