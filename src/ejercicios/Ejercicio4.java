package ejercicios;

import java.math.BigInteger;

public class Ejercicio4 {
	
	public static Double funcRecDouble(Integer a) {
		if(a<10)
			return 5.;
		else {
			return Math.sqrt(3*a)*funcRecDouble(a-2);
		}
	}
	
	public static BigInteger funcRecBig(Integer a) {
		if(a<10)
			return BigInteger.valueOf(5L);
		else
			return BigInteger.valueOf((long) Math.sqrt(a)).multiply(funcRecBig(a-2));
		
	}
	
	public static Double funcItDouble(Integer a) {
		
		double[] acum = new double[a+1];
		for(int i = 0; i<=a; i++) {
			if(i<10)
				acum[i] = 5.;
			else {
				acum[i] = acum[i-2]*Math.sqrt(3*i); 
			}
		}
		return acum[a];
	}
	
	public static BigInteger funcItBig(Integer a) {
		BigInteger[] acum = new BigInteger[a+1];
		for(int i = 0; i<=a; i++) {
			if(i<10)
				acum[i] = BigInteger.valueOf(5L);
			else {
				acum[i] = acum[i-2].multiply(BigInteger.valueOf((long) Math.sqrt(3*i))); 
			}
		}
		return acum[a];
	}

}