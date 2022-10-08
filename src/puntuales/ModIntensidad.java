package puntuales;

import ij.IJ;
import ij.ImagePlus;
import ij.io.FileSaver;
import ij.process.ImageConverter;
import ij.process.ImageProcessor;

public class ModIntensidad {

    public static void main(String[] args) {
        ImagePlus im = IJ.openImage("img/arbol.png");
        ImageConverter ic = new ImageConverter(im);
        ic.convertToGray8();
        ImageProcessor ip = im.getProcessor();

        // Modifica contraste y brillo
        double f = 1.5;  // Factor para modificar contraste
        int s = 20;  // Sumando para modificar brillo
        ImagePlus im2 = miModificador(ip, f, s);
        im2.show();

        // Para guardar la imagen
        new FileSaver(im2).saveAsPng("img/nuevaImagen.png");

        // Algunos métodos de ImageJ
//        ImageProcessor ip2 = ip.duplicate();
//        ip2.multiply(f);  // Modifica contraste
//        ip2.add(s);  // Modifica brillo
//        ip2.invert();  // Invierte la imagen (claro <-> obscuro)
//        ip2.threshold(150);  // Convierte a imagen binaria según un valor umbral

    }


    /**
     * Modifica contraste y brillo
     * @param ip: Imagen
     * @param factor: Modifica contraste
     * @param sumando: Modifica brillo
     * @return Imagen modificada
     */
    public static ImagePlus miModificador(ImageProcessor ip, double factor, int sumando) {
        ImageProcessor ip2 = ip.duplicate();  // Duplica la imagen
        int M = ip.getWidth(); // Ancho de la imagen
        int N = ip.getHeight();  // Alto de la imagen

        for (int v = 0; v < N; v++) {  // Recorre las filas
            for (int u = 0; u < M; u++) {  // Recorre las columnas
                int a = ip.get(u, v);  // Valor del pixel en las coordenadas (u, v)
                int a1 = (int) (factor * a + sumando);
                if (a1 > 255) {
                    a1 = 255;
                }
                ip2.putPixel(u, v, a1);
            }
        }
        return new ImagePlus("Imagen modificada", ip2);
    }
}
