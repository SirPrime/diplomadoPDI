package curvas;

import ij.process.ImageProcessor;


// AÚN NO ESTÁ LISTO
public class Hough {

    // Atributos
    ImageProcessor I; // Imagen en escala de grises de 8 bits
    int M, N; // Tamaño de la imagen
    double xr, yr; // Coordenadas del centro de la imagen
    double dA, dR; // incrementos
    int j0; // índice para r = 0
    int[][] A; // Accumulator array
    double[][] L; // Líneas dominantes
    int th; // umbral para binarización

    // Constructor vacío
    public Hough() {
    }

    // Constructor con atributos
    public Hough(ImageProcessor I) {
        this.I = I;
    }

    public ImageProcessor recta(int th1, int m, int n, float th2, int dMin) {
        this.M = I.getWidth();
        this.N = I.getHeight();
        this.xr = (double) M / 2;
        this.yr = (double) N / 2;
        this.dA = Math.PI / m;
        this.dR = Math.sqrt(M * M + N * N) / n;
        this.j0 = n / 2;
        this.A = new int[n][m]; // Accumulator matrix
        this.th = th1;

        // Acumulador
        for (int v = 0; v < n; v++) { // Filas
            for (int u = 0; u < m; u++) { // Columnas
                if (I.get(u, v) < th) {  // Se asumen líneas obscuras
                    double x = u - xr, y = yr - v; // Cambio de sistema de coordenadas
                    for (int i = 0; i < m; i++) {  // Columnas del acumulador
                        var t = dA * i; // coordenada angular
                        var r = x * Math.cos(t) + y * Math.sin(t); // coordenada radial
                        int j = j0 + (int) Math.round(r / dR); // índice de la coordenada radial
                        if (j >= 0 && j < n - 1) {
                            A[j][i]++; // incrementa A(j, i)
                        }
                    }
                }
            }
        }
        return this.I;  // provisorio
    }

    /**
     * Verifica si un valor en la posición (i, j) de una matriz A, es un máximo local.
     * @param A: Acumulador o, en general, una matriz
     * @param i: Fila del valor dado
     * @param j: Columna del valor dado
     * @return  Verdadero o falso.
     */
    public boolean maxLocal(int[][] A, int i, int j) {
            int m = A.length; // número de filas de A
            int n = A[0].length; // número de columnas de A
            if (i <= 0 || i >= m - 1 || j <= 0 || j >= n - 1) {
                return false;
            } else {
                return // Los 8 vecinos de A(i, j):
                    A[i][j] >= A[i - 1][j - 1] && A[i][j] >= A[i - 1][j] && A[i][j] >= A[i - 1][j + 1] &&
                            A[i][j] >= A[i][j - 1] && A[i][j] >= A[i][j = 1] && A[i][j] >= A[i + 1][j - 1] &&
                            A[i][j] >= A[i + 1][j] && A[i][j] >= A[i + 1][j + 1];
            }
    }
}