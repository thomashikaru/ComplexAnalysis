/*
 * Simple Complex Number class with basic functionality: 
 * add, multiply, magnitude, toString
 */

public class Complex {
  public double a;
  public double b;

  // constructor takes two doubles for real and imaginary parts
  public Complex(double a, double b) {
    this.a = a;
    this.b = b;
  }

  // returns String representation of the complex number
  public static String toString(Complex p) {
    return p.a + " + " + p.b + "i";
  }
  
  // multiply two complex numbers p and q
  public static Complex mult(Complex p, Complex q) {
	    return new Complex(p.a*q.a-p.b*q.b, p.b*q.a+p.a*q.b);
  }
  
  // add two complex numbers p and q
  public static Complex add(Complex p, Complex q) {
	    return new Complex(p.a+q.a, p.b+q.b);
  }
  
  // returns magnitude of the complex number p (its distance from 0)
  public static double mag(Complex p) {
	    return Math.sqrt(p.a*p.a + p.b*p.b);
  }
  
  public static double theta(Complex p) {
	  return Math.atan2(p.b, p.a);
  }
  
  public static Complex conj(Complex p) {
	  return new Complex(p.a, -1*p.b);
  }
  
  public static Complex divide(Complex p, Complex q) {
	  Complex denom_conj = Complex.conj(q);
	  Complex new_num = Complex.mult(p, denom_conj);
	  Complex new_den = Complex.mult(q, denom_conj);
	  return new Complex(new_num.a/new_den.a, new_num.b/new_den.a);
  }
}
