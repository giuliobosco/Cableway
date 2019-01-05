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
/**
 * 
 * @author giuliobosco
 * @version 1.0
 */
public class SkiPass implements Runnable {
    // ------------------------------------------------------------------------------------ Costants
    // ---------------------------------------------------------------------------------- Attributes

    /**
     * Id of the SkiPass.
     */
    private int id;

    /**
     * Vertical meters of the SkiPass.
     */
    private double verticalMeters;

    /**
     * Name of the SkiPass.
     */
    private String name;

    // --------------------------------------------------------------------------- Getters & Setters

    /**
     * Set the id of the SkiPass.
     *
     * @param id Id of the SkiPass.
     */
    public void setId(int id) {
        if (id >= 0) {
            this.id = id;
        }
    }

    /**
     * Get the id of the SkiPass.
     *
     * @return Id of the SkiPass.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Set the Vertical Meters of the SkiPass.
     * @param verticalMeters Vertical Meters of the SkiPass.
     */
    public void setVerticalMeters(double verticalMeters) {
        this.verticalMeters = verticalMeters;
    }

    /**
     * Get the Vertical Meters of the SkiPass.
     *
     * @return Vertical Meters of the SkiPass.
     */
    public double getVerticalMeters() {
        return this.verticalMeters;
    }

    /**
     * Set the name of the SkiPass.
     *
     * @param name Name of the SkiPass.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the name of the SkiPass.
     *
     * @return Name of the SkiPass.
     */
    public String getName() {
        return this.name;
    }

    // -------------------------------------------------------------------------------- Constructors

    /**
     * Initialize a SkiPass.
     *
     * @param id Id of the SkiPass.
     */
    public SkiPass(int id) {
        this.setId(id);
        this.setVerticalMeters(0);
    }

    // -------------------------------------------------------------------------------- Help Methods

    /**
     * Increase the Vertical Meters of the SkiPass.
     *
     * @param verticalMeters Vertical Meters to increase to the SkiPass.
     */
    public void increaseVerticalMeters(double verticalMeters) {
        this.verticalMeters += verticalMeters;
    }

    // ----------------------------------------------------------------------------- General Methods

    @Override
    public void run() {

    }
    
    // --------------------------------------------------------------------------- Static Components
    
}
