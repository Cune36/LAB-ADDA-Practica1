package ejercicios;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Ejercicio2 {
	
	public static List<Integer> f_RNF (Integer a, Integer b) {
		List<Integer> lista;
		if(a<2 || b<2) {
			lista = new ArrayList<>();
			lista.add(a*b);
		}else if (a>b) {
			lista = f_RNF(a%b, b-1);
			lista.add(a);
		}else {
			lista = f_RNF(a-2, b/2);
			lista.add(b);
		}
		return lista;
	}

	public static List<Integer> f_it (Integer a, Integer b) {
		List<Integer> ls = new ArrayList<>();
		Integer c = a;
		Integer d = b;
		while(!(c<2 || d<2)) {
			if(c>d) {
				ls.add(0,c);
				c = c%d;
				d = d-1;
			}else {
				ls.add(0,d);
				c -= 2;
				d /= 2;
			}
		}
		ls.add(0,c*d);
		return ls;
	}
	
	public static List<Integer> f_RF (Integer a, Integer b) {
		List<Integer> lista = new ArrayList<>();
		lista = f_RF(a,b,lista);
		return lista;
	}
	
	private static List<Integer> f_RF(Integer a, Integer b, List<Integer> lista) {
		if(a<2 || b<2) {
			lista.add(0,a*b);
		}else if (a>b) {
			lista.add(0,a);
			lista = f_RF(a%b, b-1, lista);
			
		}else {
			lista.add(0,b);
			lista = f_RF(a-2, b/2, lista);
			
		}
		return lista;
	}
	public record Tupla(List<Integer> ac, Integer a, Integer b) {
		public static Tupla of(List<Integer> ac0, Integer a0, Integer b0) {
			return new Tupla(ac0,a0,b0);
		}
		public static Tupla first(Integer a0, Integer b0) {
			return new Tupla(new ArrayList<Integer>(), a0,b0);
		}
		public Tupla next() {
			List<Integer> ls = ac;
			if(a>b) {
				ls.add(0,a);
				return Tupla.of(ls, a%b, b-1);
			}else {
				ls.add(0,b);
				return Tupla.of(ls, a-2, b/2);
			}
		}
	}
	
	public static List<Integer> f_funcional (Integer a, Integer b) {
		Tupla t = Stream.iterate(Tupla.first(a, b), x-> x.next()).filter(x-> (x.a()<2 || x.b() < 2 )).findFirst().get();
		List<Integer> ls = t.ac();
		ls.add(0,t.a()*t.b());
		return ls;
	}
}
