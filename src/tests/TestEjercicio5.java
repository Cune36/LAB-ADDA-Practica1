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
	private static Integer nMax = 5000; 
	private static Integer razon = 2; 
	private static Integer nIter = 40; 
	private static Integer nIterWarmup = 10000;
	
	public static void genDataGeometrico(Consumer<Integer> algorithm,String file,Integer tMin,Integer tMax,Integer razon,Integer numIter,Integer numIterWarmup) {
		Function<Integer,Long> f1 = GenData.time(algorithm);
		GenData.tiemposEjecucionGeometrica(f1,file,tMin,tMax,razon,numIter,numIterWarmup);
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
		genDataGeometrico(t-> Ejercicio5.ejercicio5RecBigInteger(t),"ficheros_generados/p5/recBI.txt",nMin,nMax,razon,nIter,nIterWarmup);
		genDataGeometrico(Ejercicio5::ejercicio5RecDouble,"ficheros_generados/p5/recD.txt",nMin, nMax, razon, nIter, nIterWarmup);
		genDataGeometrico(Ejercicio5::ejercicio5ItBigInteger,"ficheros_generados/p5/iteBI.txt",nMin, nMax, razon, nIter, nIterWarmup);
		genDataGeometrico(Ejercicio5::ejercicio5ItDouble,"ficheros_generados/p5/iteD.txt",nMin, nMax, razon, nIter, nIterWarmup);
		
		show(PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.))), "ficheros_generados/p5/recBI.txt","rec_mem_d");
		show(PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.))), "ficheros_generados/p5/recD.txt","rec_mem_d");
		show(PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.))), "ficheros_generados/p5/iteBI.txt","iter_d");
		show(PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.))), "ficheros_generados/p5/iteD.txt","pm_d");
		
		showCombined();
	}
}