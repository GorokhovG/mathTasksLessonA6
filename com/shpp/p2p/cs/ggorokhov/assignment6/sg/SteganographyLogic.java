package com.shpp.p2p.cs.ggorokhov.assignment6.sg;

import acm.graphics.*;

import java.awt.*;

public class SteganographyLogic {
    private static final int LAST_COLOR_INDEX = 255;

    /**
     * Given a GImage containing a hidden message, finds the hidden message
     * contained within it and returns a boolean array containing that message.
     * <p/>
     * A message has been hidden in the input image as follows.  For each pixel
     * in the image, if that pixel has a red component that is an even number,
     * the message value at that pixel is false.  If the red component is an odd
     * number, the message value at that pixel is true.
     *
     * @param source The image containing the hidden message.
     * @return The hidden message, expressed as a boolean array.
     */
    public static boolean[][] findMessage(GImage source) {
        boolean[][] result = new boolean[(int)source.getHeight()][(int)source.getWidth()];
        int[][] pixels = source.getPixelArray();

        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels[row].length; col++) {
                // Extract the color components
                int red = GImage.getRed(pixels[row][col]);
                // make association: if pixel is paired set it false/ else true
                result[row][col] = red % 2 != 0;
            }
        }

        return result;
    }

    /**
     * Hides the given message inside the specified image.
     * <p/>
     * The image will be given to you as a GImage of some size, and the message will
     * be specified as a boolean array of pixels, where each white pixel is denoted
     * false and each black pixel is denoted true.
     * <p/>
     * The message should be hidden in the image by adjusting the red channel of all
     * the pixels in the original image.  For each pixel in the original image, you
     * should make the red channel an even number if the message color is white at
     * that position, and odd otherwise.
     * <p/>
     * You can assume that the dimensions of the message and the image are the same.
     * <p/>
     *
     * @param message The message to hide.
     * @param source  The source image.
     * @return A GImage whose pixels have the message hidden within it.
     */
    public static GImage hideMessage(boolean[][] message, GImage source) {
        int[][] pixels = source.getPixelArray();
        int[][] resultPixels = new int[(int)source.getHeight()][(int)source.getWidth()];

        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels[row].length; col++) {
                // Extract the color components
                int red = GImage.getRed(pixels[row][col]);
                int green = GImage.getGreen(pixels[row][col]);
                int blue = GImage.getBlue(pixels[row][col]);

                // creating new color for pixels array
                Color rgb = new Color(changeRed(message[row][col], red), green, blue);
                // filling pixels array
                resultPixels[row][col] = rgb.getRGB();
            }
        }

        return new GImage(resultPixels);
    }

    /**
     * simple method for increase/decrease red part
     * @param isBlack variable which show is it a black color
     * @param red red part in 0 to 255 diapason
     * @return changed red part
     */
    private static int changeRed(boolean isBlack, int red) {
        if (isBlack) {
            if (red % 2 == 0) {
                red++;
            }
        }
        else {
            if (red % 2 != 0 && red != LAST_COLOR_INDEX) {
                red++;
            }
            else if (red == LAST_COLOR_INDEX) // exception if current red part is 255
                red--;
        }

        return red;
    }
}
