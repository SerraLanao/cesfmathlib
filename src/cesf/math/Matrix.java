package cesf.math;

// classe immutable per a la representació i manipulació de matrius
// de nombres reals. Aporta les funcions bàsiques de càlcul sobre
// matrius M x N.
/**
 * Class to represent and manipulate matrix
 * The function of this class is representing and manipulated matrix made of real numbers 
 * The class have calculation of basic functions in MxN matrix
 * @author alexandre
 * @version 1.0
 *
 */
final public class Matrix {
	/**
	 * number of rows
	 */
    private final int M; 			
    /**
     * number of columns
     */
    private final int N;             
    /**
     * matrix MxN of data
     */
    private final double[][] data;   

    // crea matriu M x N amb zeros
    /**
     * Creates a matix MxN
     * For the matrix MxN made a matrix full of zeros
     * @param M is the parameter of the lines
     * @param N is the parameter od the columns
     */
    public Matrix(int M, int N) {
        this.M = M;
        this.N = N;
        data = new double[M][N];
    }

    // crea matriu a partir d'una matriu 2d d'entrada
    /**
     * made a matix from one given
     * Makes a new matrix from  the one given
     * @param data is the matrix given
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
     * Constructor of the copy
     * Is the constructor of a matrix if you use a matrix
     * @param A is the matrix parameter
     */
    private Matrix(Matrix A) {
    	this(A.data); 
    }

    // crea i retorna una matriu MxN aleatòria (valors entre 0 i 1)
    /**
     * Creates and return a random matrix
     * Creates a random matrix MxN with values between 0 and 1
     * @param M parameter of the rows
     * @param N parameter of the columns
     * @return return the random matrix
     */
    public static Matrix random(int M, int N) {
        Matrix A = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A.data[i][j] = Math.random();
        return A;
    }

    // crea i retorna una matriu NxN identitat (uns a la diagonal)
    /**
     * Creates and retrns a matrix NxN identity
     * <p>Creates a matrix NxN identity that means that have a diagonal 1</p>
     * @param N is the number of columns and 
     * @return the identity matrix
     */
    public static Matrix identity(int N) {
        Matrix I = new Matrix(N, N);
        for (int i = 0; i < N; i++)
            I.data[i][i] = 1;
        return I;
    }

    // intercanviar files i i j
    /**
     * Changes between rows i and j
     * @param i one of the parameters
     * @param j the other parameter
     */
    public void swapRows(int i, int j) {
        double[] temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    // intercanviar columnes i i j
    /**
     *  Switch between the column <code>i</code> and the <code>j</code>
     * @param i one of the parameters
     * @param j the other parameter
     */
    public void swapColumns(int i, int j) {
    	for (int r = 0; r < M; r++) {
    		double temp = data[r][i];
    		data[r][i] = data[r][j];
    		data[r][j] = temp;
    	}
    }

    // crea i retorna la matriu transposada de l'actual
    /**
     * creates and returns the transposed matrix 
     * @return the transposed matrix
     */
    public Matrix transpose() {
        Matrix A = new Matrix(N, M);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A.data[j][i] = this.data[i][j];
        return A;
    }

    // retorna C = A + B
    /**
     * C=A+B
     * matrix C equals matrix A plus matrix B
     * @param B is a parameter that represents the other matrix
     * @return the result of the operation
     * @throw RuntimeException if the matrix is not of the right dimensions
     */
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
    /**
     * C=A-B
     * matrix C equals matrix A minus matrix B
     * @param B is a parameter that represents the other matrix
     * @return the result of the operation
     * @throw RuntimeException if the matrix is not of the right dimensions
     */
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
    /**
     * Compare two matrix
     * Compare two matrix to see if they are equal or not
     * @param B the other matrix to compare
     * @return a boolean saying if they are equals matrix or not
     * @throw RuntimeException if the matrix is not of the right dimensions
     */
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
    /**
     * C=A*B
     * matrix C equals matrix A multiplied matrix B
     * @param B is a parameter that represents the other matrix
     * @return the result of the operation
     * @throw RuntimeException if the matrix is not of the right dimensions
     */
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
    /**
     * returns x = A^-1*b
     * <p><b>returns x = A^-1*b</b> but the matrix entered as a parameter have to <b>contain only a row and 
     * contain the results of the equations</b>. The actual matrix have to contain the coefficients and 
     * the appropriate range for beeing solvable (and have to be a square).</p>
     * The procedure is:
     * <ol>
     *  <il>Creates a copy of the data
     *  <il>Deletes the Gaussian matrix with a parcian pivocity
     *  <il>Then finds the row that has to pivotate and switch
     *  <il>Check if is singular
     *  <il>Pivoted in B
     *  <il>Pivoted in A
     *  <il>Switch to integer
     * </ol> 
     * @param rhs is the oher matrix used to operate
     * @return the end of the operation
     * @throw RuntimeException if the matrix is not of the right dimensions
     * @throw RuntimeException if the matrix is singular
     */
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
    /**
     * returns representation of the matrix in a string text
     * @return res a string chain with the matrix 
     */
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
