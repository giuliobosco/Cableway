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
 * Cabin weight exception.
 *
 * @author giuliobosco
 * @version 1.0
 */
public class CabinWeightException extends CablewayException {

    /**
     * Create cabin weight exception generating message with the exception generator.
     *
     * @param source Exception generator.
     */
    public CabinWeightException(Cabin source) {
        super(fixMessage(source));

        int exceptionStatus = getStatus(source);
        this.setExceptionStatus(exceptionStatus);
    }

    /**
     * Create cabin weight exception with exception message.
     *
     * @param message Exception message.
     */
    public CabinWeightException(String message) {
        super(message);
    }

    /**
     * Get the difference between the limit weight and the weight.
     *
     * @param source Exception object source.
     * @return Difference between the limit weight and the weight.
     */
    private static double getDifference(Cabin source) {
        return source.getWeight() - Cabin.MAX_WEIGHT;
    }

    /**
     * Translate the cabin object status to the exception status code.
     *
     * @param source Exception object source.
     * @return Exception status code.
     */
    private static int getStatus(Cabin source) {
        if (source.getWeight() < 0) {
            return FATAL;
        } else if (source.getWeight() > Cabin.MAX_WEIGHT) {
            double difference = getDifference(source);

            return difference>50?(difference>100?FATAL:DANGER):WARRING;
        } else {
            return OK;
        }
    }

    /**
     * Parse the message from the cabin object.
     *
     * @param source Exception object source.
     * @return Exception message.
     */
    private static String fixMessage(Cabin source) {
        int status = getStatus(source);

        String message = CablewayException.codeToString(status);

        if (source.getWeight() < 0) {
            return message + "\nError with the internal cabin weight scale, value: " +
                    source.getWeight() + "[kg].";
        } else if (source.getWeight() > Cabin.MAX_WEIGHT) {
            double difference = source.getWeight() - Cabin.MAX_WEIGHT;

            message += "The weight of the cabin is over the limit of " + difference + "[kg].";

            return message;
        } else {
            return message;
        }
    }
    
}
