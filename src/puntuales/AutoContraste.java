package puntuales;

import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageConverter;
import ij.process.ImageProcessor;

public class AutoContraste {

    public static void main(String[] args) {
        ImagePlus im = IJ.openImage("img/arbol.png");  // carga la imagen
        ImageConverter ic = new ImageConverter(im);  // Para convertir a escala de grises
        ic.convertToGray8();  // Conversión a escala de grises de 8 bits
        ImageProcessor ip = im.getProcessor();  // Para procesar la imagen
        ImageProcessor ip2 = ip.duplicate();  // Duplica la imagen

        int M = ip.getWidth();  // Ancho de la imagen
        int N = ip.getHeight();  // Alto de la imagen

        int a_lo = 255;  // Inicialización del valor mínimo
        int a_hi = 0;  // Inicialización del valor máximo

        // Obtención de los pixels máximo y mínimo de la imagen
        for (int v = 0; v < N; v++) {
            for (int u = 0; u < M; u++) {
                int p = ip.get(u, v);
                if (p < a_lo) {
                    a_lo = p;
                }
                if (p > a_hi) {
                    a_hi = p;
                }
            }
        }

        // Autocontraste
        for (int v = 0; v < N; v++) {
            for (int u = 0; u < M; u++) {
                int a = ip.get(u, v);
                int f = (int) (255.0 * (a - a_lo) / (a_hi - a_lo));
                if (f > 255) {
                    f = 255;
                }
                ip2.set(u, v, f);
            }
        }
        ImagePlus im2 = new ImagePlus("AutoContraste", ip2);  // Genera la imagen
        im2.show();  // Muestra la imagen

    }
}
