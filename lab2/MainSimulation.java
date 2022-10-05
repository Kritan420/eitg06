import java.util.*;
import java.io.*;


public class MainSimulation extends Global{

    public static void main(String[] args) throws IOException {
		Gen Generator = new Gen();
		
		Generator.lambda = 45;
		int queuesNbr = 5;

    	Signal actSignal;
    	new SignalList();

		Map<String, QS> qMap = new TreeMap<String, QS>();
		for (int i = 0; i < queuesNbr; i++) {
			qMap.put("Q"+(1+i), new QS());
		}

		List<Map.Entry<String, QS>> qList = new ArrayList<>(qMap.entrySet());

		SignalList.SendSignal(READY, Generator, time);
		for (int i = 0; i < queuesNbr; i++) {
			SignalList.SendSignal(MEASURE, qList.get(i).getValue(), time);
		}

		while (time < 100000){
			Generator.sendTo = qList.get(0).getValue();
    		actSignal = SignalList.FetchSignal();
    		time = actSignal.arrivalTime;
    		actSignal.destination.TreatSignal(actSignal);
			qList.sort((q1, q2) -> (q1.getValue().numberInQueue - q2.getValue().numberInQueue));
    	} 

		System.out.println();
		for (int i = 0; i < queuesNbr; i++) {
			System.out.println("Medelantalet kunder i kösystem " + (i+1) + ": " + 1.0*qList.get(i).getValue().accumulated/qList.get(i).getValue().noMeasurements);
		}
		System.out.println();


		/** UPPGIFT 1e) 
		 * 		Generator.lambda = 8;
		 *		int queuesNbr = 3;
		Generator.sendTo = qList.get(0).getValue();
		qList.get(0).getValue().sendTo = qList.get(1).getValue();
		qList.get(1).getValue().sendTo = qList.get(2).getValue();

		*/

		/** KÖSYSTEM VÄLJS PÅ SLUMP
		Random rand = new Random();
		while (time < 100000){
			Generator.sendTo = qList.get(rand.nextInt(5)).getValue();
    		actSignal = SignalList.FetchSignal();
    		time = actSignal.arrivalTime;
    		actSignal.destination.TreatSignal(actSignal);
    	}  */

		/** KÖSYSTEM VÄLJS EN EFTER EN
		int q = 0;
		while (time < 100000){
			Generator.sendTo = qList.get(q).getValue();
    		actSignal = SignalList.FetchSignal();
    		time = actSignal.arrivalTime;
    		actSignal.destination.TreatSignal(actSignal);
			q++;
			if (q > 4) {
				q = 0;
			}
    	}  */

		
		/** KÖSYSTEM VÄLJS EFTER DEN SOM HAR MINST JOBB
		while (time < 100000){
			Generator.sendTo = qList.get(0).getValue();
    		actSignal = SignalList.FetchSignal();
    		time = actSignal.arrivalTime;
    		actSignal.destination.TreatSignal(actSignal);
			qList.sort((q1, q2) -> (q1.getValue().numberInQueue - q2.getValue().numberInQueue));
    	} */



	}
}