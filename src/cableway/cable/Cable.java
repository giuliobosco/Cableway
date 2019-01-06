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

package cableway.cable;

/**
 * Cableway cable, used as lock for synchronization.
 *
 * @author giuliobosco
 * @version 1.0
 */
public class Cable {

    // ------------------------------------------------------------------------------------ Costants

    /**
     * change the cabin speed of the percentage to the current speed.
     */
    public final static double SPEED_EDIT = 0.05;

    /**
     * Maximum speed of the cabin, in kilometer per hour [km/h].
     */
    public final static double MAX_SPEED = 23.2;

    // ---------------------------------------------------------------------------------- Attributes

    /**
     * Length of the cable.
     */
    private double lenght;

    /**
     * Position of the cable, all methods reported this attribute are synchronized, for keep a
     * coherent value.
     */
    private double position;

    /**
     * Speed of the cable.
     */
    private double speed;

    // --------------------------------------------------------------------------- Getters & Setters

    /**
     * Set the length of the cable.
     *
     * @param length Length of the cable.
     */
    private void setLength(double length) {
        if (length > 0) {
            this.lenght = length;
        }
    }

    /**
     * Get the length of the cable.
     *
     * @return Length of the cable.
     */
    public double getLength() {
        return this.lenght;
    }

    /**
     * Set the position of the cable, synchronized me.
     *
     * @param position Position of the cable.
     */
    public void setPosition(double position) {
        if (position >= 0 && position <= this.lenght) {
            this.position = position;
        }
    }

    /**
     * Get the position of the cable.
     *
     * @return Position of the cable.
     */
    public double getPosition() {
        return this.position;
    }

    /**
     * Set the speed of the cable.
     *
     * @param speed Speed of the cable.
     */
    public void setSpeed(double speed) {
        if (speed >= -MAX_SPEED && speed <= MAX_SPEED) {
            this.speed = speed;
        }
    }

    /**
     * Get the speed of the cable.
     *
     * @return Speed of the cable.
     */
    public double getSpeed() {
        return this.speed;
    }

    // -------------------------------------------------------------------------------- Constructors

    /**
     * Create the cable, with the length of the cable.
     *
     * @param length Length of the cable.
     */
    public Cable(double length) {
        this.setLength(length);
        this.setPosition(0);
        this.setSpeed(0);
    }

    // -------------------------------------------------------------------------------- Help Methods

    /**
     * Execute the check on the cable.
     *
     * @throws CableSpeedException Cable speed or position errors.
     */
    public void checkCable() throws CablePositionException, CableSpeedException {
        if (this.getPosition() > this.getLength() || this.getPosition() < 0) {
            throw new CablePositionException(this);
        }

        if (this.getSpeed() > MAX_SPEED || this.getSpeed() < -MAX_SPEED) {
            throw new CableSpeedException(this);
        }
    }

    // ----------------------------------------------------------------------------- General Methods

    /**
     * Increment the position of the cable.
     *
     * @param x Value to increment.
     */
    public void incrementPosition(double x) {
        this.position += x;
    }

    /**
     * Decrement the position of the cable.
     *
     * @param x Value to decrement.
     */
    public void decrementPosition(double x) {
        this.position -= x;
    }

    /**
     * Increment the speed of the cable.
     */
    public void incrementSpeed() {
        if (this.getSpeed() == 0) {
            this.speed++;
        }

        if (this.getSpeed() > 0) {
            this.setSpeed(this.getSpeed() + this.getSpeed() * SPEED_EDIT);
        } else {
            this.setSpeed(this.getSpeed() - this.getSpeed() * SPEED_EDIT);
        }
    }

    /**
     * Decrement the speed of the cable.
     */
    public void decrementSpeed() {
        if (this.getSpeed() == 0) {
            this.speed--;
        }

        if (this.getSpeed() > 0) {
            this.setSpeed(this.getSpeed() - this.getSpeed() * SPEED_EDIT);
        } else {
            this.setSpeed(this.getSpeed() + this.getSpeed() * SPEED_EDIT);
        }
    }

    // --------------------------------------------------------------------------- Static Components
}
