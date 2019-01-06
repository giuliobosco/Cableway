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
import cableway.cabin.CabinException;

/**
 * @author giuliobosco
 * @version 1.0
 */
public class CableException extends CabinException {

    /**
     * Create cabin exception with exception message.
     *
     * @param message Exception message.
     */
    public CableException(String message) {
        super(message);
    }

    /**
     * Create the cable speed exception, with the source cable.
     *
     * @param source Cable, exception generator.
     * @return Cable speed exception.
     */
    public static CableException cableSpeedException(Cable source) {
        double difference = source.getSpeed() - Cable.MAX_SPEED;

        if (difference <= 0) {
            return new CableException(CablewayException.OK);
        }

        String message = "";

        if (difference > 2) {
            message += CablewayException.DANGER;
        } else {
            message += CablewayException.DANGER;
        }

        message += "\nThe speed of the cabin exceed of " + difference + "!";

        return new CableException(message);
    }

    /**
     * Create the cable position exception, with the source cable.
     *
     * @param source Cable, exception generator.
     * @return Cable position exception.
     */
    public static CableException cablePositionException(Cable source) {
        double difference = source.getPosition() - source.getLength();

        String message = "";

        if (difference > 0) {
            message += CablewayException.DANGER;
            message += "\n The position of the cabin is " + source.getPosition() + "and the may "
                    + "position is " + source.getLength() + "!";

            return new CableException(message);
        }

        return new CableException(CablewayException.OK);
    }
}
