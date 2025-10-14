package ejercicios;
import java.util.HashMap;
import java.util.Map;

public class Ejercicio3 {

	public static Long iterativo(Integer a, Integer b, Integer c) {
		Long[][][] m = new Long[a+1][b+1][c+1];
		for(int i = 0; i<= a; i++) {
			for(int j = 0; j<=b; j++) {
				for(int k  =0; k<=c; k++) {
					if(i<3 || j<3 || k<3) {
						m[i][j][k] = (long) (i + j*j + 2*k);
					}else if(i%j == 0) {
						m[i][j][k] = (long) (m[i-1][j/2][k/2] + m[i-3][j/3][k/3]);
					}else
						m[i][j][k] = (long) (m[i/3][j-3][k-3] + m[i/2][j-2][k-2]);
				}
			}
		}
		return m[a][b][c];
	}

	public static Long recursivo_sin_memoria(Integer a, Integer b, Integer c) {
		if(a <3 || b<3 || c<3) 
			return (long) (a + b*b + 2*c);
		else if(a%b == 0) {
			return (long) recursivo_sin_memoria(a -1, b/2, c/2) + recursivo_sin_memoria(a-3, b/3, c/3);
		}else
			return (long) recursivo_sin_memoria(a/3, b-3, c-3) + recursivo_sin_memoria(a/2, b-2, c-2);
	}

	private record Tupla(Integer a, Integer b, Integer c) {
		public static Tupla of(Integer a0, Integer b0, Integer c0) {
			return new Tupla(a0,b0,c0);
		}
	}
	
	public static Long recursivo_con_memoria(Integer a, Integer b, Integer c) {
		Map<Tupla,Long> m = new HashMap<>();
		return rcm(a,b,c,m);
	}

	private static Long rcm(Integer a, Integer b, Integer c, Map<Tupla, Long> m) {
		Tupla t = Tupla.of(a, b, c);
		Long r;
		if(m.containsKey(t))
			r = m.get(t);
		else if(a <3 || b<3 || c<3) {
			r = (long) a + b*b + 2*c;
			m.put(t, r);
		}else if(a%b == 0) {
			r = (long) rcm(a -1, b/2, c/2, m) + rcm(a-3, b/3, c/3,m);
			m.put(t, r);
		}else {
			r = (long) rcm(a/3, b-3, c-3, m) + rcm(a/2, b-2, c-2,m);
			m.put(t, r);
		}
		return r;
	}

}
