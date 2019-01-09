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

import cableway.CablewayActionManager;
import cableway.CablewayException;
import cableway.cable.Cable;
import cableway.people.FullSetException;
import cableway.people.PeopleSet;
import cableway.people.Person;

import java.awt.event.ActionEvent;

/**
 * Cableway cabin.
 *
 * @author giuliobosco
 * @version 1.1.1
 */
public class Cabin extends Thread {
    // ------------------------------------------------------------------------------------ Costants

    /**
     * Cabin ready to start command.
     */
    public final static String READY = "CABIN_READY";

    /**
     * Cabin moved command.
     */
    public final static String MOVED = "CABIN_MOVED";

    /**
     * Cabin arrived to platform command.
     */
    public final static String ARRIVED = "CABIN_ARRIVED";

    /**
     * Max weight in the cabin.
     */
    public final static double MAX_WEIGHT = 7000;

    /**
     * Time between cabin checks, 200 milliseconds.
     */
    public final static long CABIN_CHECK = 200;

    /**
     * Max people in the cabin.
     */
    public final static int MAX_PEOPLE = 85;

    // ---------------------------------------------------------------------------------- Attributes

    /**
     * Weight in the cabin.
     */
    private double weight;

    /**
     * Cable to the cabin.
     */
    private Cable cable;

    /**
     * Status of the internal door of the cabin.
     */
    private boolean internalDoorOpen;

    /**
     * Status of the external door of the cabin.
     */
    private boolean externalDoorOpen;

    /**
     * Cabin ready to start.
     */
    private boolean ready;

    /**
     * Cableway action manager.
     */
    private CablewayActionManager cablewayActionManager;

    /**
     * People in the cabin.
     */
    private PeopleSet people;

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

    /**
     * Get the cable of the cabin.
     *
     * @return Cable of the cabin.
     */
    public Cable getCable() {
        return this.cable;
    }

    /**
     * Get the Status of the internal door of the cabin.
     *
     * @return Status of the internal door of the cabin.
     */
    public boolean isInternalDoorOpen() {
        return this.internalDoorOpen;
    }

    /**
     * Get the status of the right door of the cabin.
     *
     * @return Status of the right door of the cabin.
     */
    public boolean isExternalDoorOpen() {
        return this.externalDoorOpen;
    }

    /**
     * Set the cabin to ready to start.
     *
     * @param ready Cabin ready to start.
     * @throws CablewayException Exception in the cable way, cabin or cable problems.
     */
    public void setReady(boolean ready) throws CablewayException {
        if (ready) {
            this.checkCabin();

            this.cablewayActionManager.actionPerformer(
                    new ActionEvent(this, ActionEvent.ACTION_PERFORMED, READY)
            );
        }

        this.ready = ready;
    }

    /**
     * Get the cabin ready to start.
     *
     * @return Cabin ready to start.
     */
    public boolean isReady() {
        return this.ready && (this.getCable().getSpeed() == 0);
    }

    /**
     * Set the people in the cabin.
     *
     * @param people People in the cabin.
     * @throws CablewayException Cableway exception, error with the weight of the cabin or too many
     *                           people in the cabin.
     */
    public void setPeople(PeopleSet people) throws CablewayException {
        this.people = people;
        this.setWeight(people.getTotalWeight());
    }

    /**
     * Get the people in the cabin.
     *
     * @return People in the cabin.
     */
    public PeopleSet getPeople() {
        return this.people;
    }

    /**
     * Add person to the cabin.
     *
     * @param person Person to add.
     * @throws CablewayException Cableway exception, problem with the weight or too many people in
     *                           the cabin.
     */
    public void addPerson(Person person) throws CablewayException {
        this.incrementWeight(person.getWeight());
        this.getPeople().addPerson(person);
    }

    /**
     * Remove person from the cabin.
     *
     * @param person Person to remove.
     * @throws CabinWeightException Cabin weight exception, problem with the weight of the cabin.
     */
    public void removePerson(Person person) throws CabinWeightException {
        this.decrementWeight(person.getWeight());
        this.getPeople().removePerson(person);
    }

    /**
     * Empty the people in the cabin.
     *
     * @throws CabinWeightException Cabin weight exception, problem with the weight of the cabin.
     */
    public void emptyPeople() throws CabinWeightException {
        this.decrementWeight(this.getPeople().getTotalWeight());

        for (Person person : this.getPeople().getPeople()) {
            person.setBlocked(false);
        }
        this.getPeople().clear();
    }

    // -------------------------------------------------------------------------------- Constructors

    /**
     * Create the cabin with the cable.
     *
     * @param cable                 Cable of the cabin.
     * @param cablewayActionManager Cableway action manager.
     */
    public Cabin(Cable cable, CablewayActionManager cablewayActionManager) throws FullSetException {
        this.cable = cable;
        this.cablewayActionManager = cablewayActionManager;
        this.people = new PeopleSet(MAX_PEOPLE);
    }

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
     * check the weight.
     * Must be a valid weight, bigger than 0 and smaller than the maximum weight.
     *
     * @throws CabinWeightException Cabin weight exception, not valid weight.
     */
    public void checkWeigth() throws CabinWeightException {
        checkWeigth(this.weight);
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

    /**
     * Check the doors.
     * If the cabin is moving the door must be closed.
     *
     * @throws CabinDoorException Doors open while  moving.
     */
    public void checkDoors() throws CabinDoorException {
        if (this.getCable().getSpeed() != 0) {
            if (this.isInternalDoorOpen() || this.isExternalDoorOpen()) {
                throw new CabinDoorException(this);
            }
        }
    }

    /**
     * Open the internal door of the cabin. Sets internalDoorOpen to true.
     */
    public void openInternalDoor() {
        if (!this.isReady()) {
            this.internalDoorOpen = true;
        }
    }

    /**
     * Open the right door of the cabin. Sets externalDoorOpen to true.
     */
    public void openExternallDoor() {
        if (!this.isReady()) {
            this.externalDoorOpen = true;
        }
    }

    /**
     * Close the left door of the cabin. Sets internalDoorOpen to false.
     */
    public void closeInternalDoor() {
        this.internalDoorOpen = false;
    }

    /**
     * Close the right door of the cabin. Sets externalDoorOpen to false.
     */
    public void closeExternalDoor() {
        this.externalDoorOpen = false;
    }

    /**
     * Check the cabin.
     *
     * @throws CablewayException Cableway exception, the cableway got some error.
     */
    public void checkCabin() throws CablewayException {
        this.checkWeigth();
        this.checkDoors();
        this.getCable().checkCable();
    }

    // ----------------------------------------------------------------------------- General Methods

    /**
     * Start the cabin.
     *
     * @throws InterruptedException Interrupt the thread.
     * @throws CablewayException    Cableway exception, cabin or cable exception.
     */
    public void startCabin() throws InterruptedException, CablewayException {
        this.closeExternalDoor();
        Thread.sleep(50);
        this.closeInternalDoor();
        Thread.sleep(50);

        this.setReady(true);
    }

    /**
     * Stop the cabin.
     *
     * @throws InterruptedException Interrupt the thread.
     * @throws CablewayException    Cableway exception, cabin or cable exception.
     */
    public void stopCabin() throws InterruptedException, CablewayException {
        this.setReady(false);

        this.openExternallDoor();
        Thread.sleep(50);
        this.openInternalDoor();
        Thread.sleep(50);
    }

    /**
     * Run method of the class.
     */
    @Override
    public void run() {
        double position = this.cable.getPosition();

        boolean flag = true;
        while (flag) {
            try {
                if (position != this.cable.getPosition()) {
                    this.cablewayActionManager.actionPerformer(
                            new ActionEvent(this, ActionEvent.ACTION_PERFORMED, MOVED)
                    );

                    if (this.getCable().isArrived()) {
                        this.cablewayActionManager.actionPerformer(
                                new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ARRIVED)
                        );
                    }

                    position = this.cable.getPosition();
                }

                this.checkCabin();

                Thread.sleep(CABIN_CHECK);
                if (this.isInterrupted()) {
                    flag = false;
                }
            } catch (InterruptedException | CablewayException e) {
                this.cablewayActionManager.exceptionThrower(e);
            }
        }
    }

    // --------------------------------------------------------------------------- Static Components

}
