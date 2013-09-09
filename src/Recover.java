/*
 * Author: Zachary McHenry, zmchenry2011@my.fit.edu
 * Course: CSE 4051, Section 01, Fall 2013
 * Project: proj03, Crime Scene Investigation
 */

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Recover {
    private static final int BEGIN_MARKER = 0xD8;
    private static final int PRELIMINARY_MARKER = 0xFF;
    private static final int BLOCK_OF_MEMORY_SIZE = 512;

    public Recover(final String url) {
        try {
            final URL fileFromWeb = new URL(url);
            // Open URL so that data can be transfered.
            final InputStream streamer = fileFromWeb.openStream();
            // Stream array
            final byte[] imageData = new byte[BLOCK_OF_MEMORY_SIZE];
            // All used in interpreting image, cannot make final.
            ArrayList<Byte> picture = new ArrayList<Byte>();
            int fileCount = 0;
            boolean imageBeginFound = false;
            // Read from URL to stream.
            while ((streamer.read(imageData)) != -1) {
                // Check for beginning marker.
                if ((imageData[0] == (byte) PRELIMINARY_MARKER)
                        && (imageData[1] == (byte) BEGIN_MARKER)) {
                    if (imageBeginFound) {
                        savePicture(picture, fileCount);
                        fileCount++;
                        picture = new ArrayList<Byte>();
                    }
                    imageBeginFound = true;
                }
                // Continue to add bytes if currently in an image.
                if (imageBeginFound) {
                    picture = addToList(picture, imageData);
                }
            }
            // Save picture that is stored in the buffer after input stops.
            savePicture(picture, fileCount);
            streamer.close();
        } catch (final MalformedURLException e) {
            System.out.println("Invalid URL");
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    // Convert the Byte list to a byte array.
    public final byte[] convertToArray (final ArrayList<Byte> picture) {
        final byte[] data = new byte[picture.size()];
        for (int i = 0; i < data.length; i++) {
            data[i] = picture.get(i);
        }
        return data;
    }

    // Add block of data to the picture list.
    public final ArrayList<Byte> addToList (final ArrayList<Byte> picture,
            final byte[] byteArray) {
        for (int i = 0; i < byteArray.length; i++) {
            picture.add(byteArray[i]);
        }
        return picture;
    }

    // Save the picture to a file.
    public final void savePicture (final ArrayList<Byte> picture,
            final int fileCount) throws IOException {
        // Convert array list to byte array.
        final byte[] byteArray = convertToArray(picture);
        // Create buffer for byte array.
        final FileOutputStream fileWriter = new FileOutputStream(String.format(
                "image%02d.jpeg", fileCount));
        fileWriter.write(byteArray);
        // Clear contents of buffer.
        fileWriter.flush();
        fileWriter.close();
    }

    public static void main (final String[] args) {
        new Recover(args[0]);
    }
}
