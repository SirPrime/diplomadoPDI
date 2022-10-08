package puntuales;

import ij.process.ImageProcessor;

public class EqHistograma {

    public static void run(ImageProcessor ip) {
        int M = ip.getWidth();
        int N = ip.getHeight();
        int K = (int) (ip.getMax() + 1);
        int[] H = histAcumulado(ip);

        for (int v = 0; v < N; v++) {
            for (int u = 0; u < M; u++) {
                int a = ip.get(u, v);
                int b = H[a] * (K - 1) / (M * N);
                ip.set(u, v, b);
            }
        }
    }

    public static int[] histAcumulado (ImageProcessor ip) {
        // Histograma acumulado
        int[] H = ip.getHistogram();
        for (int i = 1; i < H.length; i++) {
            H[i] = H[i - 1] + H[i];
        }
        return H;
    }

}
