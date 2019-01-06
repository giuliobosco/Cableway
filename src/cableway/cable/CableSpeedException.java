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

package cableway.cable;

import cableway.CablewayException;

/**
 * @author giuliobosco
 * @version 1.0
 */
public class CableSpeedException extends CablewayException {

    /**
     * Create cable exception with exception message.
     *
     * @param message Exception message.
     */
    public CableSpeedException(String message) {
        super(message);
    }

    /**
     * Create cable exception, generating exception message with the exception object source.
     *
     * @param source Cable source object.
     */
    public CableSpeedException(Cable source) {
        this(fixMessage(source));

        int exceptionStatus = getStatus(source);
        this.setExceptionStatus(exceptionStatus);
    }

    /**
     * Get the difference between the limit and the value.
     *
     * @param value Speed value.
     * @return Difference between the limit and the value.
     */
    private static double getDiffference(double value) {
        if (value > Cable.MAX_SPEED) {
            return value - Cable.MAX_SPEED;
        } else if (value < -Cable.MAX_SPEED) {
            return -value - Cable.MAX_SPEED;
        } else {
            return 0;
        }
    }

    /**
     * Translate the cable object status to the exception status code.
     *
     * @param source Exception object source.
     * @return Exception status code.
     */
    private static int getStatus(Cable source) {
        double difference = getDiffference(source.getSpeed());

        if (difference <= 0) {
            return CablewayException.OK;
        }

        if (difference > 7) {
            return CablewayException.FATAL;
        } else if (difference > 2) {
            return CablewayException.DANGER;
        } else {
            return CablewayException.WARRING;
        }
    }

    /**
     * Parse the message from the cable object.
     *
     * @param source Cabin source object.
     * @return Exception message.
     */
    private static String fixMessage(Cable source) {
        double difference = getDiffference(source.getSpeed());

        int exceptionStuatus = getStatus(source);
        String message = CablewayException.codeToString(exceptionStuatus);

        return message + "\nThe speed of the cabin exceed from the limit of " + difference + "[km/h]";
    }
}
