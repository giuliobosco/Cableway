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

import cableway.CablewayActionManager;
import cableway.CablewayException;
import cableway.cabin.Cabin;
import cableway.cable.Cable;
import cableway.people.PeopleSet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Cableway station class.
 *
 * @author giuliobosco
 * @version 1.1.2
 */
public class Station extends Thread implements ActionListener {
    // ------------------------------------------------------------------------------------ Costants

    /**
     * Cableway upper station identifier.
     */
    public static final int UPPER_STATION = 0;

    /**
     * Cableway lower station identifier.
     */
    public static final int LOWER_STATION = 1;

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
     * Cableway cable.
     */
    private Cable cable;

    /**
     * Platform for cabin 0.
     */
    private Platform platform0;

    /**
     * Platform for cabin 1.
     */
    private Platform platform1;

    /**
     * Cableway action mangaer.
     */
    private CablewayActionManager cablewayActionManager;

    /**
     * List of the gates for this station.
     */
    private List<Gate> gates;

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
        if (this.isInPeopleActive()) {
            return this.inPeople;
        } else {
            return this.readyPeople;
        }
    }

    /**
     * Set the gates of the station.
     *
     * @param gates Gates of the station.
     */
    public void setGates(List<Gate> gates) {
        this.gates = gates;
    }

    /**
     * Get the gates of the station.
     *
     * @return Gates of the station.
     */
    private List<Gate> getGates() {
        return this.gates;
    }

    /**
     * Add a gate to the list of gates.
     *
     * @param gate Gate to add.
     */
    public void addGate(Gate gate) {
        this.getGates().add(gate);
    }

    // -------------------------------------------------------------------------------- Constructors

    /**
     * Init upper platform.
     *
     * @param cabin0 Cableway cabin 0.
     * @param cabin1 Cableway cabin 1.
     * @throws CablewayException Cableway exception.
     */
    private void initUpperStation(Cabin cabin0, Cabin cabin1) throws CablewayException {
        this.platform0 = new Platform(cabin0, cabin0.getCable().getLength(), this.cablewayActionManager);
        this.platform1 = new Platform(cabin1, 0, this.cablewayActionManager);
    }

    /**
     * Init lower platform.
     *
     * @param cabin0 Cableway cabin 0.
     * @param cabin1 Cableway cabin 1.
     * @throws CablewayException Cableway exception.
     */
    private void initLowerStation(Cabin cabin0, Cabin cabin1) throws CablewayException {
        this.platform0 = new Platform(cabin0, 0, this.cablewayActionManager);
        this.platform1 = new Platform(cabin1, cabin1.getCable().getLength(), this.cablewayActionManager);
    }

    /**
     * Create the station with the position of the station and the two cabins.
     *
     * @param position              Position of the cableway station.
     * @param cabin0                Cableway cabin 0.
     * @param cabin1                Cableway cabin 1.
     * @param cablewayActionManager Cableway Action Manager.
     * @throws CablewayException Cableway exception, error with the cabin or the cable.
     */
    public Station(int position, Cabin cabin0, Cabin cabin1, CablewayActionManager cablewayActionManager) throws CablewayException {
        this.cablewayActionManager = cablewayActionManager;
        this.setGates(new ArrayList<>());

        if (cabin0 != cabin1) {
            if (cabin0.getCable() == cabin1.getCable()) {
                if (position == LOWER_STATION) {
                    this.initLowerStation(cabin0, cabin1);
                } else if (position == UPPER_STATION) {
                    this.initUpperStation(cabin0, cabin1);
                } else {
                    throw new CablewayException("Wrong station position", CablewayException.FATAL);
                }
            } else {
                throw new CablewayException("Wrong cable connected to the cabins.", CablewayException.FATAL);
            }
        } else {
            throw new CablewayException("Cabins cant't be the same.", CablewayException.FATAL);
        }
    }

    // -------------------------------------------------------------------------------- Help Methods

    /**
     * Move the people from the ready people to the cabin, from the in people to the ready people.
     *
     * @param cabin Destination cabin of the people.
     * @throws CablewayException Cableway exception, errors on the cableway
     */
    private void movePeople(Cabin cabin) throws CablewayException {
        cabin.setPeople(this.getReadyPeople());

        if (this.isInPeopleActive()) {
            this.setReadyPeople(this.getInPeople());
        }

        this.getInPeople().clear();
    }

    /**
     * Get an free gate.
     *
     * @return Free gate.
     */
    public Gate getFreeGate() {
        while (true) {
            for (Gate gate : this.getGates()) {
                if (gate.isOpen()) {
                    return gate;
                }
            }
        }
    }

    // ----------------------------------------------------------------------------- General Methods

    /**
     * Action performed.
     *
     * @param e Action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource().getClass() == Cabin.class) {
                if (e.getActionCommand().equals(Cabin.ARRIVED)) {
                    if (this.platform0.isCabinHere()) {
                        this.platform0.getCabin().emptyPeople();

                        this.movePeople(this.platform0.getCabin());
                    } else if (this.platform1.isCabinHere()) {
                        this.platform1.getCabin().emptyPeople();

                        this.movePeople(this.platform0.getCabin());
                    }
                }
            }
        } catch (CablewayException cablewayException) {
            this.cablewayActionManager.exceptionThrower(cablewayException);
        }
    }

    @Override
    public void run() {

    }

    // --------------------------------------------------------------------------- Static Components

}
