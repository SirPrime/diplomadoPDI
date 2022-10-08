package colores;

import ij.IJ;
import ij.ImagePlus;
import ij.process.ColorProcessor;


public class Puntuales {

    public static void main(String[] args) {
        ImagePlus im = IJ.openImage("img/explosion.jpg"); // Carga de la imagen
        ColorProcessor cp = (ColorProcessor) im.getProcessor();

        brillo(cp, 60);
        new ImagePlus("Más brillante", cp).show();
    }

    public static void brillo(ColorProcessor cp, int br) {

            int[] pixels = (int[]) cp.getPixels();

            for (int i = 0; i < pixels.length; i++) {
                int c = pixels[i];
                // separación en componentes rgb:
                int r = (c & 0xff0000) >> 16;
                int g = (c & 0x00ff00) >> 8;
                int b = (c & 0x0000ff);
                // modifica colores:
                r = r + br; if (r > 255) r = 255;
                g = g + br; if (g > 255) g = 255;
                b = b + br; if (b > 255) b = 255;
                // reassemble color pixel and insert into pixel array:
                pixels[i] = ((r & 0xff) << 16) | ((g & 0xff) << 8) | b & 0xff;
            }
        }
    }
