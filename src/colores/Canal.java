package colores;

import ij.IJ;
import ij.ImagePlus;
import ij.process.ByteProcessor;
import ij.process.ColorProcessor;

public class Canal {
    ImagePlus im;  // atributo

    public Canal(ImagePlus im){
        this.im = im;
    }

    public void separaEnCanalesGrises() {
        ColorProcessor cp = (ColorProcessor) this.im.getProcessor();
        int M = cp.getWidth();
        int N = cp.getHeight();

        // Separación en canales RGB
        byte[] cR = cp.getChannel(1);
        byte[] cG = cp.getChannel(2);
        byte[] cB = cp.getChannel(3);

        // Generación de imágenes con canales separados
        ByteProcessor bpr = new ByteProcessor(M, N, cR);
        ByteProcessor bpg = new ByteProcessor(M, N, cG);
        ByteProcessor bpb = new ByteProcessor(M, N, cB);

        // Visualización de canales en escala de grises
        im.show();  // imagen original
        new ImagePlus("Rojos", bpr).show();
        new ImagePlus("Verdes", bpg).show();
        new ImagePlus("Azules", bpb).show();

    }

    public void separaEnCanalesRGB() {
        // Visualización en graduación del propio canal
        // haciendo cero los otros canales
        ColorProcessor cp = (ColorProcessor) this.im.getProcessor();
        int M = cp.getWidth();
        int N = cp.getHeight();

        // Separación en canales RGB
        byte[] cR = cp.getChannel(1);
        byte[] cG = cp.getChannel(2);
        byte[] cB = cp.getChannel(3);

        int L = cR.length;
        byte[] c0 = new byte[L];
        ColorProcessor cpr = new ColorProcessor(M, N);
        ColorProcessor cpg = new ColorProcessor(M, N);
        ColorProcessor cpb = new ColorProcessor(M, N);
        cpr.setRGB(cR, c0, c0);
        cpg.setRGB(c0, cG, c0);
        cpb.setRGB(c0, c0, cB);
        new ImagePlus("Escala de rojos", cpr).show();
        new ImagePlus("Escala de verdes", cpg).show();
        new ImagePlus("Escala de azules", cpb).show();

    }

    public static void main(String[] args) {
        // Imagen de prueba
//        ImagePlus im = IJ.openImage("img/rana_small.jpeg");
        ImagePlus im = IJ.openImage("img/explosion.jpg");

        Canal c = new Canal(im);
//        c.separaEnCanalesGrises();
        c.separaEnCanalesRGB();
    }
}
