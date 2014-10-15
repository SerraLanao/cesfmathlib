package cesf.math;

// classe immutable per a representar fraccions enteres
/**
 * Represents a fraction
 * Represents and resolve fractions
 * @author alexandre
 * @version 1.0
 *
 */
public class Fraction {

	private final int num;
	private final int den;
	
	// constructor per defecte
	/**
	 * Default constructor
	 */
	public Fraction() { 
		num=0; 
		den=1;
	}
	// constructor amb paràmetres 
	/**
	 * Constructor with parameters
	 * @param num constructor of the denominator
	 * @param den constructor of the numerator
	 */
	public Fraction(int num, int den) { 
		if (den == 0) 
			throw new IllegalArgumentException("Denominator can not be zero!"); 
		this.num = num;
		this.den = den; 
	}

	// getters
	/**
	 * The getters
	 * @return the getters
	 */
	public int getNum() { return this.num; }
	public int getDen() { return this.den; }
	
	// retorna el valor numèric de la fracció
	/**
	 * Returns the numeric number of the fraccion
	 * @return a double 
	 */
	public double valueOf() {
		double x = (double)(this.num) / this.den; 
		return x;
	}
	
	// retorna el valor numèric d'una fracció (versió estàtica)
	/**
	 * Return a numeric number of a fraction
	 * @param a is the fraction parameter
	 * @return the numeric of a fraction
	 * @deprecated is better to use @see {@link #getNum()} and {@link #getDen()}
	 */
	public static double valueOf(Fraction a) {
		double x = (double)(a.num) / a.den; 
		return x;		
	}
	
	// retorna una representació en cadena de text
	/**
	 * returns a repesentation in a string chain
	 * @return a result in a sttring chain
	 */
	public String toString() {
		return this.num + "/" + this.den;
	}
	
	// simplifica la fracció al màxim
	/**
	 * Simplifies the fraction
	 * @return the fraction simplified
	 */
	public Fraction reduce() {
		int mcd = mcd(this.num, this.den);
		int n = this.num / mcd;
		int d = this.den / mcd;
		if (d < 0) { n = -n; d = -d; }
		return new Fraction(n, d);
	}
	
	// retorna F1 + F2
	/**
	 * f1+f1
	 * fraction one plus fraction two
	 * @param b the fraction to sum
	 * @return the result
	 */
	public Fraction add(Fraction b) {
		int n = this.num * b.den + this.den * b.num;
		int d = this.den * b.den;
		return new Fraction(n, d).reduce();
	}
	
	// retorna F1 - F2
	/**
	 * f1-f1
	 * fraction one minus fraction two 
	 * @param b the fraction to rest
	 * @return the result
	 */
	public Fraction substract(Fraction b) {
		int n = this.num * b.den - this.den * b.num;
		int d = this.den * b.den;
		return new Fraction(n, d).reduce();
	}
	
	// retorna F1 * F2
	/**
	 * f1*f1
	 * fraction one multiplied fraction two 
	 * @param b the fraction to multiply
	 * @return the result
	 */
	public Fraction multiply(Fraction b) {
		int n = this.num * b.num;
		int d = this.den * b.den;
		return new Fraction(n, d).reduce();
	}
	
	// retorna F1 * x
	/**
	 * f1*X
	 * fraction one multiplied a number 
	 * @param b the integer to multiply
	 * @return the result
	 */
	public Fraction multiply(int x) {
		int n = this.num * x;
		int d = this.den;
		return new Fraction(n, d).reduce();
	}
	
	// retorna F1 / F2
	/**
	 * f1/f1
	 * fraction one divided fraction two 
	 * @param b the fraction to divide
	 * @return the result
	 */
	public Fraction divide(Fraction b) {
		return this.multiply(b.reciprocal());
	}

	// retorna 1 / F
	/**
	 * 1/F
	 * @return the result
	 */
	public Fraction reciprocal() {
		return new Fraction(this.den, this.num).reduce();
	}
	
	// troba el MCD del numerador i denominador
	/**
	 * founds the mcd 
	 * founds the mcd of the numerator and the denominator
	 * @param a is the numerator
	 * @param b is the denominator
	 * @return the mcd
	 */
	protected int mcd(int a, int b) {
		int d; 
		while (b != 0) {
			d = b;
			b = a % b;
			a = d;
		}
		return a;
	}

	// compara dos fraccions
	/**
	 * compare two fractions
	 * @param b the fraction to compare
	 * @return a bolean of <code>true</code> or <code>false</code> if is biger or not
	 */
	public boolean equals(Fraction b) {
		Fraction f1 = this.reduce();
		Fraction f2 = b.reduce();
		if (f1.num != f2.num) return false;
		if (f1.den != f2.den) return false; 
		return true;
	}
}
