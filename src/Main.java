import colores.Cuadro;
import ij.IJ;
import ij.ImagePlus;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        ImagePlus im = IJ.openImage("img/flores.jpeg");

        // Generación de colores
        Color c1 = new Color(100, 120, 200);
        Color c2 = new Color(16777215);
        Color rojo = new Color(0xFF0000);
        Color verde = new Color(0x00FF00);
        Color azul = new Color(0x0000FF);
        Color blanco = new Color(255, 255, 255);
        Color B = Color.BLUE;

        Cuadro cc = new Cuadro(1000, 750);
//        cc.colorUnico(c1).show();
        cc.pimienta(im, .01);
        cc.sal(im, 0.01);
        im.show();
    }
}
