package ejercicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Ejercicio1 {
	public record EnteroCadena(Integer a, String s) {
		public static EnteroCadena of(Integer a0, String s0){
			return new EnteroCadena(a0,s0);
		}
	}
	
	// Del enunciado:
	public static Map<Integer,List<String>> solucionFuncional(Integer varA, String varB, Integer varC, String varD, Integer varE) {
		UnaryOperator<EnteroCadena> nx = elem -> {
			return EnteroCadena.of(elem.a()+2,
				elem.a()%3==0?
					elem.s()+elem.a().toString():
					elem.s().substring(elem.a()%elem.s().length()));
		};					
		return Stream.iterate(EnteroCadena.of(varA,varB), elem -> elem.a() < varC, nx)
					.map(elem -> elem.s() + varD)
					.filter(nom -> nom.length() < varE)
					.collect(Collectors.groupingBy(String::length));
	}
	
	public static Map<Integer,List<String>> solucionIterativa(Integer varA, String varB, Integer varC, String varD, Integer varE) {
		Map<Integer,List<String>> ac = new HashMap<>();
		EnteroCadena elem = EnteroCadena.of(varA, varB);
		while(elem.a()< varC) {
			String t = elem.s();
			t += varD;
			if(t.length() < varE) {
				List<String> ls;
				if(ac.containsKey(t.length())) 
					ls = ac.get(t.length());
				else 
					ls = new ArrayList<>();
				ls.add(t);
				ac.put(t.length(), ls);
			}
			String s = elem.a()%3 == 0?
						elem.s() + elem.a().toString():
						elem.s().substring(elem.a()%elem.s().length());
			elem = EnteroCadena.of(elem.a()+2, s);
		}
		return ac;
	}
	
	public static Map<Integer,List<String>> solucionRecursivaFinal(Integer varA, String varB, Integer varC, String varD, Integer varE) {
		Map<Integer,List<String>> ac = new HashMap<>();
		EnteroCadena elem = EnteroCadena.of(varA, varB);
		ac = solucionRecursivaFinal(ac, elem, varA,  varB,  varC,  varD,  varE);
		return ac;
	}

	private static Map<Integer, List<String>> solucionRecursivaFinal(Map<Integer, List<String>> ac, EnteroCadena elem,
			Integer varA, String varB, Integer varC, String varD, Integer varE) {
		if(elem.a()<varC) {
			String t = elem.s();
			t += varD;
			//Mapeo del enteroCadena para guardarlo en la lista del diccionario
			if(t.length() < varE) {
				List<String> ls;
				if(ac.containsKey(t.length())) 
					ls = ac.get(t.length());
				else 
					ls = new ArrayList<>();
				ls.add(t);
				ac.put(t.length(), ls);
			}
			//Calculo del siguiente elemento
			String s = elem.a()%3 == 0?
					elem.s() + elem.a().toString():
					elem.s().substring(elem.a()%elem.s().length());
			ac = solucionRecursivaFinal(ac, EnteroCadena.of(elem.a()+2, s), varA,  varB,  varC,  varD,  varE);
		}
		return ac;
	}
}
