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

package cableway;

import cableway.cabin.Cabin;
import cableway.cable.Cable;
import cableway.station.Station;
import gui.CabinPanel;

/**
 * Cableway manager, manage all the function of the cableway.
 *
 * @author giuliobosco
 * @version 1.0
 */
public class CablewayManager {

    // ------------------------------------------------------------------------------------ Costants

    /**
     * Default cable length.
     */
    public static final double CABLE_LENGTH = 10000;

    // ---------------------------------------------------------------------------------- Attributes

    /**
     * Cable way cabin 0.
     */
    private Cabin cabin0;

    /**
     * Cableway cabin 1.
     */
    private Cabin cabin1;

    /**
     * Cableway upper station.
     */
    private Station upperStation;

    /**
     * Cableway lower station.
     */
    private Station lowerStation;

    /**
     * Cableway action manager.
     */
    private CablewayActionManager cablewayActionManager;

    // --------------------------------------------------------------------------- Getters & Setters

    /**
     * Get the cableway action manager.
     *
     * @return Cableway action manager.
     */
    public CablewayActionManager getCablewayActionManager() {
        return this.cablewayActionManager;
    }

    /**
     * Get the cabin 0 panel.
     *
     * @return Cabin 0 panel.
     */
    public CabinPanel getCabin0Panel() {
        return new CabinPanel(this.cabin0);
    }

    /**
     * Get the cabin 1 panel.
     *
     * @return Cabin 1 panel.
     */
    public CabinPanel getCabin1Panel() {
        return new CabinPanel(this.cabin1);
    }

    // -------------------------------------------------------------------------------- Constructors

    /**
     * Create and initialize the cableway.
     */
    public CablewayManager() {
        try {
            Cable cable = new Cable(CABLE_LENGTH);
            this.cablewayActionManager = new CablewayActionManager();

            this.cabin0 = new Cabin(cable, cablewayActionManager);
            this.cabin1 = new Cabin(cable, cablewayActionManager);

            this.lowerStation = new Station(
                    Station.LOWER_STATION,
                    this.cabin0,
                    this.cabin1,
                    this.cablewayActionManager
            );

            this.upperStation = new Station(
                    Station.UPPER_STATION,
                    this.cabin0,
                    this.cabin1,
                    this.cablewayActionManager
            );

            this.cabin0.start();
            this.cabin1.start();
        } catch (CablewayException ce) {
            this.cablewayActionManager.exceptionThrower(ce);
        }
    }

    // -------------------------------------------------------------------------------- Help Methods
    // ----------------------------------------------------------------------------- General Methods
    // --------------------------------------------------------------------------- Static Components

}
