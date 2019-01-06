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

import cableway.CablewayException;
import cableway.cable.Cable;
import cableway.cable.CablePositionException;
import cableway.cable.CableSpeedException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author giuliobosco
 * @version 1.0.7
 */
public class Cabin extends Thread {
    // ------------------------------------------------------------------------------------ Costants

    /**
     * Cabin ready to start command.
     */
    public final static String READY = "READY";

    /**
     * Cabin moved command.
     */
    public final static String MOVED = "MOVED";

    /**
     * Interrupted exception command.
     */
    public final static String INTERRUPTED_EXCEPTION = "IE";

    /**
     * Cableway exception command.
     */
    public final static String CABLEWAY_EXCEPTION = "CB";

    /**
     * Max weight in the cabin.
     */
    public final static double MAX_WEIGHT = 7000;

    /**
     * Time between cabin checks, 200 milliseconds.
     */
    public final static long CABIN_CHECK = 200;

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
     * Status of the left door of the cabin.
     */
    private boolean leftDoorOpen;

    /**
     * Status of the right door of the cabin.
     */
    private boolean rightDoorOpen;

    /**
     * Cabin ready to start.
     */
    private boolean ready;

    /**
     * Action listeners list.
     */
    private List<ActionListener> actionListeners;

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
     * Get the Status of the left door of the cabin.
     *
     * @return Status of the left door of the cabin.
     */
    public boolean isLeftDoorOpen() {
        return this.leftDoorOpen;
    }

    /**
     * Get the status of the right door of the cabin.
     *
     * @return Status of the right door of the cabin.
     */
    public boolean isRightDoorOpen() {
        return this.rightDoorOpen;
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

            this.actionPerformer(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, READY));
        }

        this.ready = ready;
    }

    /**
     * Get the cabin ready to start.
     *
     * @return Cabin ready to start.
     */
    public boolean isReady() {
        return this.ready;
    }

    /**
     * Get the action listeners list.
     *
     * @return Action listeners list.
     */
    public List<ActionListener> getActionListeners() {
        return this.actionListeners;
    }

    /**
     * Add an action listener to the list of action listeners.
     *
     * @param actionListener Action listener to add to the list of action listeners.
     */
    public void addActionListener(ActionListener actionListener) {
        this.getActionListeners().add(actionListener);
    }

    /**
     * Remove an action listener from the list of action listeners.
     *
     * @param actionListener Action listener to remove from the list of action listeners.
     */
    public void removeActionListener(ActionListener actionListener) {
        this.getActionListeners().remove(actionListener);
    }

    /**
     * Perform action to all action listeners.
     *
     * @param e Action event to perform.
     */
    public void actionPerformer(ActionEvent e) {
        for (ActionListener actionListener : this.getActionListeners()) {
            actionListener.actionPerformed(e);
        }
    }

    // -------------------------------------------------------------------------------- Constructors

    /**
     * Create the cabin with the cable.
     *
     * @param cable Cable of the cabin.
     */
    public Cabin(Cable cable) {
        this.cable = cable;
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
            if (this.isLeftDoorOpen() || this.isRightDoorOpen()) {
                throw new CabinDoorException(this);
            }
        }
    }

    /**
     * Open the left door of the cabin. Sets leftDoorOpen to true.
     */
    public void openLeftDoor() {
        if (this.isReady()) {
            this.leftDoorOpen = true;
        }
    }

    /**
     * Open the right door of the cabin. Sets rightDoorOpen to true.
     */
    public void openRightDoor() {
        if (this.isReady()) {
            this.rightDoorOpen = true;
        }
    }

    /**
     * Close the left door of the cabin. Sets leftDoorOpen to false.
     */
    public void closeLeftDoor() {
        this.leftDoorOpen = false;
    }

    /**
     * Close the right door of the cabin. Sets rightDoorOpen to false.
     */
    public void closeRightDoor() {
        this.rightDoorOpen = false;
    }

    /**
     * Check the cabin.
     *
     * @throws CabinWeightException   Cabin weight exception, not valid weight.
     * @throws CabinDoorException     Cabin door exception, doors open while moving.
     * @throws CablePositionException Cable position exception, the cable has a wrong position.
     * @throws CableSpeedException    Cable speed exception, the speed is out of the bounds.
     */
    private void checkCabin() throws CabinWeightException, CabinDoorException, CablePositionException, CableSpeedException {
        this.checkWeigth();
        this.checkDoors();
        this.getCable().checkCable();
    }

    // ----------------------------------------------------------------------------- General Methods

    /**
     * Start the cabin.
     *
     * @throws InterruptedException Interrupt the thread.
     * @throws CablewayException Cableway exception, cabin or cable exception.
     */
    public void startCabin() throws InterruptedException, CablewayException {
        this.closeLeftDoor();
        Thread.sleep(50);
        this.closeRightDoor();
        Thread.sleep(50);

        this.setReady(true);
    }

    /**
     * Stop the cabin.
     *
     * @throws InterruptedException Interrupt the thread.
     * @throws CablewayException Cableway exception, cabin or cable exception.
     */
    public void stopCabin() throws InterruptedException, CablewayException {
        this.setReady(false);

        this.openLeftDoor();
        Thread.sleep(50);
        this.openRightDoor();
        Thread.sleep(50);
    }

    /**
     * Run method of the class.
     */
    @Override
    public void run() {
        long lastTick = System.currentTimeMillis();
        double position = this.cable.getPosition();

        boolean flag = true;
        try {
            while (flag) {
                if (!(position == this.cable.getPosition())) {
                    this.actionPerformer(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, MOVED));
                    position = this.cable.getPosition();
                }

                this.checkCabin();

                Thread.sleep(CABIN_CHECK);
                if (this.isInterrupted()) {
                    flag = false;
                }
            }
        } catch (InterruptedException ie) {
            this.actionPerformer(new CabinActionEvent(this, ie));
        } catch (CablewayException cb) {
            this.actionPerformer(new CabinActionEvent(this, cb));
        }
    }

    // --------------------------------------------------------------------------- Static Components

}
