package com.shpp.p2p.cs.ggorokhov.assignment6.hg;

public class HistogramEqualizationLogic {
    private static final int MAX_LUMINANCE = 255;

    /**
     * Given the luminances of the pixels in an image, returns a histogram of the frequencies of
     * those luminances.
     * <p/>
     * You can assume that pixel luminances range from 0 to MAX_LUMINANCE, inclusive.
     *
     * @param luminances The luminances in the picture.
     * @return A histogram of those luminances.
     */
    public static int[] histogramFor(int[][] luminances) {

        int[] result = new int[MAX_LUMINANCE + 1];

        for (int i = 0; i < luminances.length; i++) {
            luminances[i] = sortArray(luminances[i]);
            result = addValueToArray(result, luminances[i]);
        }

        return result;
    }

    /**
     * method for adding data from income array to result array
     * @param result result array
     * @param array income array
     * @return result array
     */
    private static int[] addValueToArray(int[] result, int[] array) {
        for (int i = 0; i < MAX_LUMINANCE + 1; i++) {
            for (int j = 0; j < array.length; j++) {
                if (i == array[j])
                    result[i]++;
            }
        }

        return result;
    }

    /**
     * mathod for sorting array from 0 to n
     * @param luminance income array
     * @return sorted array
     */
    private static int[] sortArray(int[] luminance) {

        for (int i = 0; i < luminance.length; i++) {
            for (int j = 0; j < luminance.length; j++) {
                // exception for first cell
                if (j == 0)
                    continue;

                // Bubble sort
                if (luminance[j] < luminance[j - 1]) {
                    int subVar = luminance[j - 1];
                    luminance[j - 1] = luminance[j];
                    luminance[j] = subVar;
                }
            }
        }

        return luminance;
    }

    /**
     * Given a histogram of the luminances in an image, returns an array of the cumulative
     * frequencies of that image.  Each entry of this array should be equal to the sum of all
     * the array entries up to and including its index in the input histogram array.
     * <p/>
     * For example, given the array [1, 2, 3, 4, 5], the result should be [1, 3, 6, 10, 15].
     *
     * @param histogram The input histogram.
     * @return The cumulative frequency array.
     */
    public static int[] cumulativeSumFor(int[] histogram) {
		int[] result = new int[histogram.length];

		for (int i = 0; i < result.length; i++) {
		    for (int j = 0; j <= i; j++) {
		        result[i] += histogram[j];
            }
        }

        return result;
    }

    /**
     * Returns the total number of pixels in the given image.
     *
     * @param luminances A matrix of the luminances within an image.
     * @return The total number of pixels in that image.
     */
    public static int totalPixelsIn(int[][] luminances) {
        return luminances.length * luminances[0].length;
    }

    /**
     * Applies the histogram equalization algorithm to the given image, represented by a matrix
     * of its luminances.
     * <p/>
     * You are strongly encouraged to use the three methods you have implemented above in order to
     * implement this method.
     *
     * @param luminances The luminances of the input image.
     * @return The luminances of the image formed by applying histogram equalization.
     */
    public static int[][] equalize(int[][] luminances) {
        // make copy for luminances, because in future methods will change original luminances array
        int[][] oldLuminance = copyArray(luminances);
        // calculating cumulative histogram by histogram from luminances
        int[] cumulativeHistogram = cumulativeSumFor(histogramFor(luminances));
        // calculating total pixels
        double totalPixels = totalPixelsIn(luminances);

        for (int i = 0; i < luminances.length; i++) {
            for (int j = 0; j < luminances[i].length; j++) {
                // calculating new brightness
                luminances[i][j] = (int)(cumulativeHistogram[oldLuminance[i][j]] / totalPixels * MAX_LUMINANCE);
            }
        }

        return luminances;
    }

    /**
     * Method for copying arrays.
     * There a lot of reasons for copying arrays
     * First one is changing original one
     * @param luminances income array
     * @return copied array
     */
    private static int[][] copyArray(int[][] luminances) {
        int[][] copyLuminance = new int[luminances.length][luminances[0].length];

        for (int i = 0; i < copyLuminance.length; i++) {
            System.arraycopy(luminances[i], 0, copyLuminance[i], 0, copyLuminance[i].length);
        }

        return copyLuminance;
    }
}
