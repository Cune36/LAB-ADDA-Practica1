package ejercicios;

import java.math.BigInteger;

public class Ejercicio5 {

	public static Double ejercicio5ItDouble(Integer n) {
		Double[] acum = new Double[n+1];
		for(int i = 0; i<= n; i++) {
			if(i <= 6)
				acum[i] = 1.;
			else
				acum[i] = 1 + acum[i-1]* log2(i-1);
		}
		return acum[n];
	}
	
	public static Double ejercicio5RecDouble(Integer n) {
		if(n <= 6)
			return 1.;
		else {
			return 1 + ejercicio5RecDouble(n-1)*log2(n-1);
		}
	}
	
	public static BigInteger ejercicio5RecBigInteger(Integer n) {
		if(n<=6)
			return BigInteger.ONE;
		else
			return BigInteger.ONE.add(ejercicio5RecBigInteger(n-1).multiply(BigInteger.valueOf((long) log2(n-1))));
	}
	
	public static BigInteger ejercicio5ItBigInteger(Integer n) {
		BigInteger[] acum = new BigInteger[n+1];
		for(int i = 0; i<= n; i++) {
			if(i <= 6)
				acum[i] = BigInteger.ONE;
			else
				acum[i] = BigInteger.ONE.add(acum[i-1].multiply(BigInteger.valueOf((long) log2(i-1))));
		}
		return acum[n];
	}

	public static int log2(int n){
	    if(n <= 0) throw new IllegalArgumentException();
	    return 31 - Integer.numberOfLeadingZeros(n);
	}
}