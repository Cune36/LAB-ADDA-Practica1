package tests;

import java.math.BigInteger;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

import ejercicios.Ejercicio4;
import us.lsi.common.Pair;
import us.lsi.common.String2;
import us.lsi.curvefitting.DataFile;
import us.lsi.curvefitting.Fit;
import us.lsi.curvefitting.GenData;
import us.lsi.curvefitting.PowerLog;
import us.lsi.graphics.MatPlotLib;

public class TestEjercicio4 {
		
	private static Integer nMin = 100; // n mínimo para el cálculo de potencia
	private static Integer nMax = 10000; // n máximo para el cálculo de potencia
	private static Integer razon = 3330; // incremento en los valores de n del cálculo de potencia
	private static Integer nIter = 50; // número de iteraciones para cada medición de tiempo
	private static Integer nIterWarmup = 10000; // número de iteraciones para warmup
	
		
	public static void genData (Consumer<Integer> algorithm, String file) {
		Function<Integer,Long> f1 = GenData.time(algorithm);
		GenData.tiemposEjecucionAritmetica(f1,file,nMin,nMax,razon,nIter,nIterWarmup);
	}
	
	public static void show(Fit pl, String file, String label) {
		List<WeightedObservedPoint> data = DataFile.points(file);
		pl.fit(data);
		MatPlotLib.show(file, pl.getFunction(), String.format("%s = %s",label,pl.getExpression()));
	}
	
	public static void showCombined() {
		MatPlotLib.showCombined("Tiempos",
				List.of("ficheros_generados/p4/BIRecursiva.txt","ficheros_generados/p4/DRecursiva.txt","ficheros_generados/p4/BIIterativa.txt"
						,"ficheros_generados/p4/DIterativa.txt"), 
				List.of("BIRecursiva","DRecursiva","BIIterativa", "DIterativa"));
	}
	
	public static void main(String[] args) {
		//genData(t -> Ejercicio4.funcRecBig(t),"ficheros_generados/p4/BIRecursiva.txt");
		//genData(t -> Ejercicio4.funcRecDouble(t),"ficheros_generados/p4/DRecursiva.txt");
		//genData(t -> Ejercicio4.funcItBig(t),"ficheros_generados/p4/BIIterativa.txt");
		//genData(t -> Ejercicio4.funcItDouble(t),"ficheros_generados/p4/DIterativa.txt");

		
		show(PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.))), "ficheros_generados/p4/BIRecursiva.txt","BIRecursiva");
		show(PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.))),"ficheros_generados/p4/DRecursiva.txt","DRecursiva");
		show(PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.))),"ficheros_generados/p4/BIIterativa.txt","BIIterativa");
		show(PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.))),"ficheros_generados/p4/DIterativa.txt","DIterativa");
		
		showCombined();
		
		}

	
}