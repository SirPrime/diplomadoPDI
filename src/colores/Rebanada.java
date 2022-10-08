package colores;

import ij.IJ;
import ij.ImagePlus;
import ij.process.ColorProcessor;
import ij.process.FloatProcessor;
import ij.process.ImageProcessor;

import java.awt.*;

public class Rebanada {

    public static void main(String[] args) {
        ImagePlus im = IJ.openImage("img/girl.jpg");
        Rectangle r = new Rectangle(0,0, 200, 300);

        ImageProcessor ip = new ColorProcessor(r.width, r.height);

        for (int v = 0; v < r.height; v++) {
            for (int u = 0; u < r.width; u++) {
                int[] p = im.getPixel(u+r.x, v+r.y);
                ip.putPixel(u, v, p);
            }
        }
        new ImagePlus("Rebanada", ip).show();
    }
}
