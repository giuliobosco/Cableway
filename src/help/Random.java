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
package help;
/**
 * Random values.
 *
 * @author giuliobosco
 * @version 1.0
 */
public class Random {
    // -------------------------------------------------------------------------------------------------------- Costants
    // ------------------------------------------------------------------------------------------------------ Attributes
    // ----------------------------------------------------------------------------------------------- Getters & Setters
    // ---------------------------------------------------------------------------------------------------- Constructors
    // ---------------------------------------------------------------------------------------------------- Help Methods
    // ------------------------------------------------------------------------------------------------- General Methods
    // ----------------------------------------------------------------------------------------------- Static Components

    /**
     * Get a random integer value.
     *
     * @param min Minimum value (not in the range).
     * @param max Maximum value (in the range).
     * @return Integer random value
     */
    public static int getInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    /**
     * Get a random integer value.
     * Minimum value: 0 (not in the range).
     *
     * @param max Maximum value (in the range).
     * @return Integer random value.
     */
    public static int getInt(int max) {
        return getInt(0, max);
    }

    /**
     * Get a random double value.
     *
     * @param min Minimum value (not in the range).
     * @param max Maximum value (in the range).
     * @return Double random value.
     */
    public static double getDouble(double min, double max) {
        return (Math.random() * (max - min + 1)) + min;
    }

    /**
     * Get a random double value with an defined number of decimals.
     *
     * @param min Minimum value (not in the range).
     * @param max Maximum value (in the range).
     * @param decimals Number of decimals.
     * @return Double random value.
     */
    public static double getDouble(double min, double max, int decimals) {
        int n = (int) (getDouble(min, max) * Math.pow(10,decimals));
        return n / Math.pow(10, decimals);
    }
}