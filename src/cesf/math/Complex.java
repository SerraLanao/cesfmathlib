package cesf.math;

// Classe per disposar de nombres complexos
// Els nombres complexos tenen la forma a+bi (ex. 3+4i),
// on i és l'arrel quadrada de -1 i base dels nombres
// imaginaris.
// Les parts d'un nombre complex s'anomenen:
// * a = part real
// * b = part imaginària
// Es poden calcular moltes coses amb nombres complexos
// i accepten diverses operacions. Aquesta classe 
// implementa les més destacables i necessàries.
/**
 * 
 * @author alexandre
 * @version 1.0
 */
public class Complex {
	private final double re;  // part real
	private final double im;  // part imaginària
	
	// Constructor per defecte
	/**
	 * Default constructor
	 */
	public Complex() { 
		re = 0; 
		im = 0; 
	}
	// Constructor amb paràmetres
	/**
	 * Constructor with parameters
	 * a constructor that have double parameters
	 * @param re the fist parameter
	 * @param im the second parameter
	 */
	public Complex(double re, double im) {
		this.re = re;
		this.im = im;
	}
	
    // Retorna una representació en cadena de text
	/**
	 * Returns a representation in a string text
	 * 
	 */
    public String toString() {
        if (im == 0) return re + "";
        if (re == 0) return im + "i";
        if (im <  0) return re + " - " + (-im) + "i";
        return re + " + " + im + "i";
    }

    // Retorna el mòdul del complex
    /**
     * Returns the module of the complex
     * @return the module
     */
    public double abs()   { return Math.hypot(re, im); }
    // Retorna la fase (angle) del complex
    /**
     * returns the angle of the complex
     * @return the angle 
     */
    public double phase() { return Math.atan2(im, re); }

    // Suma un segon complex i retorna el resultat
    /**
     * Sums the second complex and returns the result
     * @param b the parameter of the sum
     * @return the result 
     */
    public Complex add(Complex b) {
        double real = this.re + b.re;
        double imag = this.im + b.im;
        return new Complex(real, imag);
    }

    // Resta un segon complex i retorna el resultat
    /**
     * Subtraction the second complex and returns the result
     * @param b the parameter of the subtraction
     * @return the result of the subtraction
     */
    public Complex substract(Complex b) {
        double real = this.re - b.re;
        double imag = this.im - b.im;
        return new Complex(real, imag);
    }

    // Multiplica per un segon complex i retorna el resultat
    /**
     * Multiply the second complex and returns the result
     * @param b the parameter of the multiplication
     * @return the result of the multiplication
     */
    public Complex multiply(Complex b) {
        double real = this.re * b.re - this.im * b.im;
        double imag = this.re * b.im + this.im * b.re;
        return new Complex(real, imag);
    }

    // Multiplica per un real i retorna el resultat
    /**
     * Multiply a real and return the result
     * @param x the real
     * @return return the result of the operation
     */
    public Complex multiply(double x) {
    	double real = x * this.re;
    	double imag = x * this.im;
        return new Complex(real, imag);
    }

    // Retorna la divisió per un segon complex
    /**
     * Divide the second complex and returns the result
     * @param b the parameter of the division
     * @return the result of the division
     */
    public Complex divide(Complex b) {
        return this.multiply(b.reciprocal());
    }

    // Retorna el complex conjugat de l'actual
    /**
     * Returns the complex conjugate of the actual
     * @return the conjugation 
     */
    public Complex conjugate() {
    	return new Complex(this.re, -this.im); 
    }

    // Retorna el recíproc d'aquest complex
    /**
     * returns the reciprocal of the complex
     * @return the reciprocal
     */
    public Complex reciprocal() {
        double x = this.re * this.re + this.im * this.im;
        return new Complex(this.re / x, -this.im / x);
    }

    // getters
    /**
     * the getters
     * @return the getters
     */
    public double getReal() { return this.re; }
    public double getImag() { return this.im; }

    // Retorna l'exponenciació del complex
    /**
     * return a power of the complex
     * @return the power
     */
    public Complex exp() {
    	double real = Math.exp(this.re) * Math.cos(this.im);
    	double imag = Math.exp(this.re) * Math.sin(this.im);
        return new Complex(real, imag);
    }

    // Retorna el sinus del complex
    /**
     * Return the sinus of the comples
     * @return the sinus
     */
    public Complex sin() {
    	double real = Math.sin(re) * Math.cosh(im);
    	double imag = Math.cos(re) * Math.sinh(im);
        return new Complex(real, imag);
    }

    // Retorna el cosinus del complex
    /**
     * return the cosinus of the complex
     * @return the cosinus
     */
    public Complex cos() {
    	double real = Math.cos(re) * Math.cosh(im);
    	double imag = -Math.sin(re) * Math.sinh(im);
        return new Complex(real, imag);
    }

    // Retorna la tangent del complex
    /**
     * Return the tangent of the complex
     * @return the tangent
     */
    public Complex tan() {
        return sin().divide(cos());
    }
    
    // versió estàtica de la funció suma
    /**
     * Static version of the sum
     * @param a the fist paramameter, is a integer
     * @param b the second paramameter, is a integer
     * @return a new complex of the sum
     */
    public static Complex add(Complex a, Complex b) {
        double real = a.re + b.re;
        double imag = a.im + b.im;
        return new Complex(real, imag);
    }


}
