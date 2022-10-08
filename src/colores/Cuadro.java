package colores;

import ij.ImagePlus;
import ij.process.ColorProcessor;
import ij.process.ImageProcessor;

import java.awt.*;
import java.util.Random;

public class Cuadro {
    int ancho;  // ancho
    int alto;  // alto

    public Cuadro() {
        ancho = 200;
        alto = 100;
    }

    public Cuadro(int M, int N) {
        this.ancho = M;
        this.alto = N;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }
    public ImagePlus colorUnico(Color c) {

        ImageProcessor ip = new ColorProcessor(ancho, alto);
        ip.setColor(c);
        ip.fill();

        return new ImagePlus("Color único", ip);
    }

    public void pimienta(ImagePlus im, double th) {
        ImageProcessor ip = im.getProcessor();
        Random rand = new Random();
        for (int v = 0; v < alto; v++) {
            for (int u = 0; u < ancho; u++) {
                if (rand.nextFloat() < th) {
                    ip.putPixel(u, v, 0);
                }
            }
        }
    }

    public void sal(ImagePlus im, double th) {
        ImageProcessor ip = im.getProcessor();
        Random rand = new Random();
        for (int v = 0; v < alto; v++) {
            for (int u = 0; u < ancho; u++) {
                if (rand.nextFloat() < th) {
                    ip.putPixel(u, v, 16777215);
                }
            }
        }
    }
}
