package tests;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

import ejercicios.Ejercicio5;
import us.lsi.common.Pair;
import us.lsi.common.String2;
import us.lsi.curvefitting.DataFile;
import us.lsi.curvefitting.Fit;
import us.lsi.curvefitting.GenData;
import us.lsi.curvefitting.PowerLog;
import us.lsi.graphics.MatPlotLib;

public class TestEjercicio5 {
		
	private static Integer nMin = 32;
	private static Integer nMax = 10000; 
	private static Integer razon = 3300; 
	private static Integer nIter = 40; 
	private static Integer nIterWarmup = 5000;
	
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
				List.of("ficheros_generados/p5/recBI.txt","ficheros_generados/p5/recD.txt","ficheros_generados/p5/iteBI.txt","ficheros_generados/p5/iteD.txt"), 
				List.of("Recursivo_BI","Recursivo_Double","Iterativa_BI","Iterativa_Double"));
	}
	
	public static void main(String[] args) {
		//genData(t-> Ejercicio5.ejercicio5RecBigInteger(t),"ficheros_generados/p5/recBI.txt");
		//genData(Ejercicio5::ejercicio5RecDouble,"ficheros_generados/p5/recD.txt");
		//genData(Ejercicio5::ejercicio5ItBigInteger,"ficheros_generados/p5/iteBI.txt");
		//genData(Ejercicio5::ejercicio5ItDouble,"ficheros_generados/p5/iteD.txt");
		
		show(PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.))), "ficheros_generados/p5/recBI.txt","rec_bi");
		show(PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.))), "ficheros_generados/p5/recD.txt","rec_d");
		show(PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.))), "ficheros_generados/p5/iteBI.txt","iter_bi");
		show(PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.))), "ficheros_generados/p5/iteD.txt","iter_d");
		
		showCombined();
	}
}