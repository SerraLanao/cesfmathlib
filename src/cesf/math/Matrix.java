package cesf.math;

// classe immutable per a la representació i manipulació de matrius
// de nombres reals. Aporta les funcions bàsiques de càlcul sobre
// matrius M x N.
/**
 * Class to represent and manipulate matrix
 * The function of this class is representing and manipulated matrix made of real numbers 
 * The class have calculation of basic functions in MxN matrix
 * @author alexandre
 *
 */
final public class Matrix {
    private final int M;             // núm. files
    private final int N;             // núm. columnes
    private final double[][] data;   // matriu MxN de dades

    // crea matriu M x N amb zeros
    /**
     * Creates a matix MxN
     * @param M
     * @param N
     */
    public Matrix(int M, int N) {
        this.M = M;
        this.N = N;
        data = new double[M][N];
    }

    // crea matriu a partir d'una matriu 2d d'entrada
    /**
     * 
     * @param data
     */
    public Matrix(double[][] data) {
        M = data.length;
        N = data[0].length;
        this.data = new double[M][N];
        for (int i = 0; i < M; i++)
        	for (int j = 0; j < N; j++)
            	this.data[i][j] = data[i][j];
    }

    // constructor de còpia
    /**
     * 
     * @param A
     */
    private Matrix(Matrix A) {
    	this(A.data); 
    }

    // crea i retorna una matriu MxN aleatòria (valors entre 0 i 1)
    /**
     * 
     * @param M
     * @param N
     * @return
     */
    public static Matrix random(int M, int N) {
        Matrix A = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A.data[i][j] = Math.random();
        return A;
    }

    // crea i retorna una matriu NxN identitat (uns a la diagonal)
    public static Matrix identity(int N) {
        Matrix I = new Matrix(N, N);
        for (int i = 0; i < N; i++)
            I.data[i][i] = 1;
        return I;
    }

    // intercanviar files i i j
    public void swapRows(int i, int j) {
        double[] temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    // intercanviar columnes i i j
    public void swapColumns(int i, int j) {
    	for (int r = 0; r < M; r++) {
    		double temp = data[r][i];
    		data[r][i] = data[r][j];
    		data[r][j] = temp;
    	}
    }

    // crea i retorna la matriu transposada de l'actual
    public Matrix transpose() {
        Matrix A = new Matrix(N, M);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A.data[j][i] = this.data[i][j];
        return A;
    }

    // retorna C = A + B
    public Matrix add(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N)
        	throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.data[i][j] = A.data[i][j] + B.data[i][j];
        return C;
    }

    // retorna C = A - B
    public Matrix substract(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) 
        	throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.data[i][j] = A.data[i][j] - B.data[i][j];
        return C;
    }

    // compara dos matrius a partir dels valors
    public boolean equals(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) 
        	throw new RuntimeException("Illegal matrix dimensions.");
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                if (A.data[i][j] != B.data[i][j]) 
                	return false;
        return true;
    }

    // retorna C = A * B
    public Matrix multiply(Matrix B) {
        Matrix A = this;
        if (A.N != B.M) 
        	throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(A.M, B.N);
        for (int i = 0; i < C.M; i++)
            for (int j = 0; j < C.N; j++)
                for (int k = 0; k < A.N; k++)
                    C.data[i][j] += (A.data[i][k] * B.data[k][j]);
        return C;
    }

    // retorna x = A^-1*b (soluciona el sistema)
    // la matriu subministrada ha de ser d'una sola columna
    // i contenir els resultats de les equacions. La matriu
    // actual ha de contenir els coeficients i tenir el
    // rang adient per ser resoluble (a més de ser quadrada)
    public Matrix solve(Matrix rhs) {
        if (M != N || rhs.M != N || rhs.N != 1)
            throw new RuntimeException("Illegal matrix dimensions.");
        // crear còpies de les dades
        Matrix A = new Matrix(this);
        Matrix b = new Matrix(rhs);
        // eliminació Gaussiana amb pivotat parcial
        for (int i = 0; i < N; i++) {
            // trobar fila sobre la qual pivotar i intercanviar
            int max = i;
            for (int j = i + 1; j < N; j++)
                if (Math.abs(A.data[j][i]) > Math.abs(A.data[max][i]))
                    max = j;
            A.swapRows(i, max);
            b.swapRows(i, max);
            // singular
            if (A.data[i][i] == 0.0) 
            	throw new RuntimeException("Matrix is singular.");
            // pivotar amb b
            for (int j = i + 1; j < N; j++)
                b.data[j][0] -= b.data[i][0] * A.data[j][i] / A.data[i][i];
            // pivotar amb A
            for (int j = i + 1; j < N; j++) {
                double m = A.data[j][i] / A.data[i][i];
                for (int k = i+1; k < N; k++) {
                    A.data[j][k] -= A.data[i][k] * m;
                }
                A.data[j][i] = 0.0;
            }
        }
        // substitució enrera
        Matrix x = new Matrix(N, 1);
        for (int j = N - 1; j >= 0; j--) {
            double t = 0.0;
            for (int k = j + 1; k < N; k++)
                t += A.data[j][k] * x.data[k][0];
            x.data[j][0] = (b.data[j][0] - t) / A.data[j][j];
        }
        return x;
    }

    // retorna una representació en cadena de text
    public String toString() {
    	String res = "";
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) { 
            	res += String.format("%9.4f ", data[i][j]);
            }
            res += "\n";
        }
        return res;
    }
}
