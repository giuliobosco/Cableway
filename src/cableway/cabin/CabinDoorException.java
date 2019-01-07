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

/**
 * Cabin door exception.
 *
 * @author giuliobosco
 * @version 1.0.1
 */
public class CabinDoorException extends CabinException {

    /**
     * Create cabin door exception with exception message.
     *
     * @param message Exception message.
     */
    public CabinDoorException(String message) {
        super(message);
    }

    /**
     * Create cabin door exception, generating exception with the exception object source.
     *
     * @param source Exception object source.
     */
    public CabinDoorException(Cabin source) {
        this(fixMessage(source));

        int exceptionStatus = getStatus(source);
        this.setExceptionStatus(exceptionStatus);
    }

    /**
     * Translate the cabin object to the exception status code.
     *
     * @param source Exception object source.
     * @return Exception status code.
     */
    private static int getStatus(Cabin source) {
        if ((source.isLeftDoorOpen() || source.isRightDoorOpen()) && source.getCable().getSpeed() != 0) {
            return DANGER;
        }

        return OK;
    }

    /**
     * Parse the message from the cabin object.
     *
     * @param source Exception object source.
     * @return Exception message.
     */
    private static String fixMessage(Cabin source) {
        int exceptionStatus = getStatus(source);
        if (exceptionStatus == DANGER) {
            String message = CablewayException.codeToString(exceptionStatus);

            if (source.isLeftDoorOpen() && source.getCable().getSpeed() != 0) {
                message += "\nLeft door is open!";
            }

            if (source.isRightDoorOpen() && source.getCable().getSpeed() != 0) {
                message += "\nRight door is open.";
            }

            return message;
        } else {
            return CablewayException.codeToString(exceptionStatus);
        }
    }
}
