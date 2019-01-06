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

import java.awt.event.ActionEvent;

/**
 * Cabin action event, Action event with the handle for exceptions.
 *
 * @author giuliobosco
 * @version 1.0
 */
public class CabinActionEvent extends ActionEvent {
    // ------------------------------------------------------------------------------------ Costants

    /**
     * Cabin action event exception command.
     */
    public static final String EXCEPTION_COMMAND = "EXCEPTION";

    // ---------------------------------------------------------------------------------- Attributes

    /**
     * Cabin action exception.
     */
    private Exception exception;

    // --------------------------------------------------------------------------- Getters & Setters

    /**
     * Get the cabin action exception.
     *
     * @return Cabin action exception.
     */
    public Exception getException() {
        return this.exception;
    }

    // -------------------------------------------------------------------------------- Constructors

    /**
     * Create cabin action event with object source and exception.
     *
     * @param source Action event object source.
     * @param exception Action event exception.
     */
    public CabinActionEvent(Object source, Exception exception) {
        this(source, EXCEPTION_COMMAND);
        this.exception = exception;

    }

    /**
     * Create cabin action event with object source and action event command.
     *
     * @param source  Action event object source.
     * @param command Action event exception.
     */
    public CabinActionEvent(Object source, String command) {
        super(source, ActionEvent.ACTION_PERFORMED, command);
    }

    // -------------------------------------------------------------------------------- Help Methods
    // ----------------------------------------------------------------------------- General Methods
    // --------------------------------------------------------------------------- Static Components
    
}
