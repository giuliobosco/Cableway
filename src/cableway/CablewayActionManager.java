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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.ExceptionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Cableway Action manager, store all the action and exception listeners of the cableway system.
 *
 * @author giuliobosco
 * @version 1.0
 */
public class CablewayActionManager {
    // ------------------------------------------------------------------------------------ Costants
    // ---------------------------------------------------------------------------------- Attributes

    /**
     * Cableway action listeners list.
     */
    private List<ActionListener> actionListeners;

    /**
     * Cableway exception listeners list.
     */
    private List<ExceptionListener> exceptionListeners;

    // --------------------------------------------------------------------------- Getters & Setters

    /**
     * Set the cableway action listeners list.
     *
     * @param actionListeners Cableway action listeners list.
     */
    public void setActionListeners(List<ActionListener> actionListeners) {
        this.actionListeners = actionListeners;
    }

    /**
     * Get the cableway action listeners list.
     *
     * @return Cableway action listeners list.
     */
    public List<ActionListener> getActionListeners() {
        return this.actionListeners;
    }

    /**
     * Set the cableway exception listeners list.
     *
     * @param exceptionListeners Cableway exception listeners list.
     */
    public void setExceptionListeners(List<ExceptionListener> exceptionListeners) {
        this.exceptionListeners = exceptionListeners;
    }

    /**
     * Get the cableway exception listeners list.
     *
     * @return Cableway exception listeners list.
     */
    public List<ExceptionListener> getExceptionListeners() {
        return this.exceptionListeners;
    }

    // -------------------------------------------------------------------------------- Constructors

    /**
     * Create the action and exception manager, initialize the action listeners list and the
     * exception listeners list.
     */
    public CablewayActionManager() {
        this.setActionListeners(new ArrayList<>());
        this.setExceptionListeners(new ArrayList<>());
    }

    // -------------------------------------------------------------------------------- Help Methods

    /**
     * Perform action to all action listeners.
     *
     * @param e Action Event to perform.
     */
    public void actionPerformer(ActionEvent e) {
        for (ActionListener actionListener : this.getActionListeners()) {
            actionListener.actionPerformed(e);
        }
    }

    /**
     * Throw exception to all exception listeners.
     *
     * @param e Exception to throw.
     */
    public void exceptionThrower(Exception e) {
        for (ExceptionListener exceptionListener : this.getExceptionListeners()) {
            exceptionListener.exceptionThrown(e);
        }
    }

    // ----------------------------------------------------------------------------- General Methods
    // --------------------------------------------------------------------------- Static Components

}