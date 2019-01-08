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

/**
 * Cableway general exception, must to stop the cable and the cabins.
 *
 * @author giuliobosco
 * @version 1.0
 */
public class CablewayException extends Exception {
    // ------------------------------------------------------------------------------------ Costants

    /**
     * Fatal exception status.
     */
    public static final int FATAL = 0;

    /**
     * Fatal exception status string.
     */
    public static final String FATAL_TEXT = "FATAL!";

    /**
     * Danger exception status.
     */
    public static final int DANGER = 1;

    /**
     * Danger exception status string.
     */
    public static final String DANGER_TEXT = "DANGER! ";

    /**
     * Warring exception status.
     */
    public static final int WARRING = 2;

    /**
     * Warring exception status string.
     */
    public static final String WARRING_TEXT = "WARRING! ";

    /**
     * Info exception status.
     */
    public static final int INFO = 3;

    /**
     * Info exception status string.
     */
    public static final String INFO_TEXT = "INFO!";

    /**
     * Ok exception status.
     */
    public static final int OK = 4;

    /**
     * Ok exception status string.
     */
    public static final String OK_TEXT = "OK! ";

    // ---------------------------------------------------------------------------------- Attributes

    /**
     * Exception status.
     * Available statuses.
     * <ul>
     * <li>OK: 4</li>
     * <li>INFO: 3</li>
     * <li>WARRING: 2</li>
     * <li>DANGER: 1</li>
     * <li>FATAL: 0</li>
     * </ul>
     */
    private int exceptionStatus;

    // --------------------------------------------------------------------------- Getters & Setters

    /**
     * Get the exception status.
     *
     * @return Exception status.
     */
    public int getExceptionStatus() {
        return this.exceptionStatus;
    }

    /**
     * Set the exception status and check that is a real status.
     *
     * @param exceptionStatus Exception status.
     */
    protected void setExceptionStatus(int exceptionStatus) {
        if (exceptionStatus == OK || exceptionStatus == INFO || exceptionStatus == WARRING || exceptionStatus == DANGER || exceptionStatus == FATAL) {
            this.exceptionStatus = exceptionStatus;
        } else {
            this.exceptionStatus = FATAL;
        }
    }

    // -------------------------------------------------------------------------------- Constructors

    /**
     * Create cableway exception with exception message.
     *
     * @param message Exception message.
     */
    public CablewayException(String message) {
        super(message);
    }

    /**
     * Create cableway exception with the exception message.
     *
     * @param message         Exception message.
     * @param exceptionStatus Exception status.
     */
    public CablewayException(String message, int exceptionStatus) {
        this(codeToString(exceptionStatus) + "\n" + message);
        this.setExceptionStatus(exceptionStatus);
    }

    // -------------------------------------------------------------------------------- Help Methods

    /**
     * Translate the exception status code in status string. If the code not exists returns always
     * FATAL.
     *
     * @param code Exception status code.
     * @return Exception status string.
     */
    public static String codeToString(int code) {
        if (code == FATAL) {
            return FATAL_TEXT;
        } else if (code == DANGER) {
            return DANGER_TEXT;
        } else if (code == WARRING) {
            return WARRING_TEXT;
        } else if (code == INFO) {
            return INFO_TEXT;
        } else if (code == OK) {
            return OK_TEXT;
        } else {
            return FATAL_TEXT;
        }
    }

    // ----------------------------------------------------------------------------- General Methods
    // --------------------------------------------------------------------------- Static Components

}
