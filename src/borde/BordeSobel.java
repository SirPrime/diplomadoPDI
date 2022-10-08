package borde;

import ij.process.ImageProcessor;

public class BordeSobel {

    public static void fuerzaBorde(ImageProcessor ip) {

        ImageProcessor ipX = ip.duplicate();
        ImageProcessor ipY = ip.duplicate();

        int M = ip.getWidth();
        int N = ip.getHeight();

        float [] sobelX = {-1f/8, 0f, 1f/8, -2f/8, 0f, 2f/8, -1f/8, 0f, 1f/8,};
        float [] sobelY = {-1f/8, -2f/8, -1f/8, 0f, 0f, 0f, 1f/8, 2f/8, 1f/8,};

        ipX.convolve(sobelX, 3, 3);
        ipY.convolve(sobelY, 3, 3);

        for (int v = 0; v < N; v++) {
            for (int u = 0; u < M; u++) {
                int Dx = ipX.getPixel(u, v);
                int Dy = ipY.getPixel(u, v);

                int E = (int) Math.sqrt(Dx*Dx + Dy*Dy);

                ip.set(u, v, E);
            }
        }





    }
}
