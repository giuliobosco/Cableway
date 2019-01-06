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

package cableway.cabin;

/**
 * @author giuliobosco
 * @version 1.0.1
 */
public class Cabin extends Thread {
    // ------------------------------------------------------------------------------------ Costants

    /**
     * Max weight in the cabin.
     */
    public final static double MAX_WEIGHT = 7000;

    // ---------------------------------------------------------------------------------- Attributes

    /**
     * Weight in the cabin.
     */
    private double weight;

    // --------------------------------------------------------------------------- Getters & Setters

    /**
     * Get the weight of the cabin.
     *
     * @return Weight of the cabin.
     */
    public double getWeight() {
        return this.weight;
    }

    /**
     * Set the weight of the cabin.
     *
     * @param weight Weight of the cabin.
     * @throws CabinWeightException Cabin weight exception.
     */
    public void setWeight(double weight) throws CabinWeightException {
        checkWeigth(weight);
        this.weight = weight;
    }

    // -------------------------------------------------------------------------------- Constructors
    // -------------------------------------------------------------------------------- Help Methods

    /**
     * Check the weight.
     * Must be a valid weight, so bigger than 0 and smaller than the maximum weight.
     *
     * @param weight Weight.
     * @throws CabinWeightException Cabin weight exception, not valid weight.
     */
    public void checkWeigth(double weight) throws CabinWeightException {
        if (!(weight > 0 && weight < MAX_WEIGHT)) {
            throw new CabinWeightException(this);
        }
    }

    /**
     * Increment the weight of the cabin of the x value.
     *
     * @param x Value increment to the weight.
     * @throws CabinWeightException Error with the weight.
     */
    public void incrementWeight(double x) throws CabinWeightException {
        this.setWeight(this.getWeight() + x);
    }

    /**
     * Decrement the weight of the cabin of the x value.
     *
     * @param x Value to decrement the weight.
     * @throws CabinWeightException Error with the weight.
     */
    public void decrementWeight(double x) throws CabinWeightException {
        this.setWeight(this.getWeight() - x);
    }

    // ----------------------------------------------------------------------------- General Methods
    // --------------------------------------------------------------------------- Static Components

}
