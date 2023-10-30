package com.shpp.p2p.cs.ggorokhov.assignment6.tm;

public class ToneMatrixLogic {
    /**
     * Given the contents of the tone matrix, returns a string of notes that should be played
     * to represent that matrix.
     *
     * @param toneMatrix The contents of the tone matrix.
     * @param column     The column number that is currently being played.
     * @param samples    The sound samples associated with each row.
     * @return A sound sample corresponding to all notes currently being played.
     */
    public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {
        double[] result = new double[ToneMatrixConstants.sampleSize()];

		for (int row = 0; row < toneMatrix.length; row++) {
		    // if square is enable
            if (toneMatrix[row][column]) {
                // remember sound column
                for (int i = 0; i < samples[row].length; ++i) {
                    result[i] += samples[row][i];
                }
            }
        }

		// returning alignment sounds array
        return setNormalizeArray(result);
    }

    /**
     * method for sound alignment
     * @param array income array
     * @return alignment sounds array
     */
    private static double[] setNormalizeArray(double[] array) {
        double maxValue = getMax(array);

        // if square is enable
        if (maxValue != 0) {
            for (int i = 0; i < array.length; i++) {
                array[i] /= maxValue;
            }
        }

        return array;
    }

    /**
     * mathod for finding the biggest or smallest element
     * @param array income array
     * @return max value
     */
    private static double getMax(double[] array) {
        double max = Math.abs(array[0]);

        for (int i = 1; i < array.length; ++i) {
            if (max < Math.abs(array[i]))
               max = Math.abs(array[i]);
        }

        return max;
    }
}
