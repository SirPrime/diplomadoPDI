package puntuales;

import ij.ImagePlus;
import ij.process.ByteProcessor;
import ij.process.ImageProcessor;

import java.util.Objects;

public class ProcHist {

    ImageProcessor ip;

    public ProcHist(ImageProcessor ip) {
        this.ip = ip;
    }

    /**
     * Devuelve el histograma acumulado lineal de la imagen
     * @return  valores del histograma acumulado en forma de array
     */
    public int[] cumulative() {
        int[] h = this.ip.getHistogram();
        int[] H = new int[h.length];
        H[0] = h[0];
        for (int j = 1; j < H.length; j++) {
            H[j] = H[j - 1] + h[j];
        }
        return H;
    }

    /**
     * Imagen del histograma acumulado
     * @param a altura en pixels de la imagen para el histograma acumulado
     * @param b = "sqrt" para el histograma acumulado de las raíces cuadradas
     *          del histograma lineal y cualquier otro valor para el histograma
     *          acumulado lineal.
     * @return  imagen de tipo ImagePlus
     */
    public ImagePlus plotCumulative(int a, String b) {
        // create the histogram image:
        int K = (int) this.ip.getMax();
        int w = this.ip.getWidth();
        int h = this.ip.getHeight();
        var M = (double) w * h; // total number of pixels
        ImageProcessor hip = new ByteProcessor(K, a);
        hip.setValue(255); // white = 255
        hip.fill(); // fill the image with white

        // draw the histogram values as black bars
        int[] H = new int[K];
        String histTitle = "Cumulative Histogram";
        if (Objects.equals(b, "sqrt")) {
            H = cumulativeSqrt();
            histTitle = "Sqrt Cumulative Histogram";
            M = H[K - 1];
        }
        else {
            H = cumulative();
        }
        double f = (double) a / M; // Vertical scale factor
        double v0 = 0.0; // Initialization
        for (int u = 0; u < K; u++) {
            v0 = a - H[u] * f;
            for (int v = (int) v0; v < a; v++) {
                hip.putPixel(u, v, 0); // Put a black = 0 pixel in (u, v)
            }
        }
        return new ImagePlus(histTitle, hip);
    }
    /**
     * Devuelve el histograma acumulado de las raíces cuadradas del histograma
     * de la imagen
     * @return raíces cuadradas acumuladas del histograma en forma de array
     */
    public int[] cumulativeSqrt() {
        int[] H_int = this.ip.getHistogram();
        int K = H_int.length;
        double[] H_double = new double[K];
        H_double[0] = Math.sqrt(H_int[0]);
        for (int j = 1; j < K; j++) {
            H_double[j] = H_double[j - 1] + Math.sqrt(H_int[j]);
            H_int[j-1] = (int) H_double[j-1];
        }
        H_int[K - 1] = (int) H_double[K - 1];
        return H_int;
    }
}
