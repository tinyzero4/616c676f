package w2; /******************************************************************************
 *  Compilation:  javac w2.PrintEnergy.java
 *  Execution:    java w2.PrintEnergy input.png
 *  Dependencies: w2.SeamCarver.java
 *                
 *
 *  Read image from file specified as command line argument. Print energy
 *  of each pixel as calculated by w2.SeamCarver object.
 * 
 ******************************************************************************/

import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;

public class PrintEnergy {

    public static void main(String[] args) {
        Picture picture = new Picture(new File(PrintSeams.class.getResource("3x4.png").getFile()));
        StdOut.printf("image is %d pixels wide by %d pixels high.\n", picture.width(), picture.height());
        
        SeamCarver sc = new SeamCarver(picture);
        
        StdOut.printf("Printing energy calculated for each pixel.\n");        

        for (int row = 0; row < sc.height(); row++) {
            for (int col = 0; col < sc.width(); col++)
                StdOut.printf("%9.0f ", sc.energy(col, row));
            StdOut.println();
        }
    }

}
