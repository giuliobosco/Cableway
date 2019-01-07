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
 * Cable Position Exception.
 *
 * @author giuliobosco
 * @version 1.0.2
 */
public class CablePositionException extends CableException {

    /**
     * Create cable position exception with exception message.
     *
     * @param message Exception message.
     */
    public CablePositionException(String message) {
        super(message);
    }

    /**
     * Create cable position exception, generating exception message with the exception object
     * source.
     *
     * @param source Exception object source.
     */
    public CablePositionException(Cable source) {
        this(fixMessage(source));

        int exceptionStatus = getStatus(source);
        this.setExceptionStatus(exceptionStatus);
    }

    /**
     * Get the difference between the length and the position.
     *
     * @param source Exception object source.
     * @return Difference between the length and the position.
     */
    private static double getDiffference(Cable source) {
        return source.getPosition() - source.getLength();
    }

    /**
     * Translate the cable object status to the exception status code.
     *
     * @param source Exception object source.
     * @return Exception status code.
     */
    private static int getStatus(Cable source) {
        double difference = getDiffference(source);

        if (difference > 2) {
            return CablewayException.FATAL;
        } else if (difference > 0) {
            return CablewayException.DANGER;
        } else {
            return CablewayException.OK;
        }
    }

    /**
     * Parse the message from the cable object.
     *
     * @param source Exception object source.
     * @return Exception message.
     */
    private static String fixMessage(Cable source) {
        double difference = getDiffference(source);

        int exceptionStuatus = getStatus(source);
        String message = CablewayException.codeToString(exceptionStuatus);

        return message + "\nThe position of the cabin exceed from the length of " + difference + "[m] from the length of the cable.";
    }
}
