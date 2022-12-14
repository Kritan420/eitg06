import java.util.*;
import java.io.*;


public class MainSimulation extends Global{

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
		
		Gen Generator = new Gen();
		Generator.lambda = 45;
		
		int queuesNbr = 5;

    		Signal actSignal;
    		new SignalList();

		List<QS> qList = new ArrayList<>(queuesNbr);
		SignalList.SendSignal(READY, Generator, time);

		for (int i = 0; i < queuesNbr; i++) {
			qList.add(i, new QS());
			SignalList.SendSignal(MEASURE, qList.get(i), time);
		}

		while (time < 100000){
		Generator.sendTo = qList.get(0);
    		actSignal = SignalList.FetchSignal();
    		time = actSignal.arrivalTime;
    		actSignal.destination.TreatSignal(actSignal);
		qList.sort((q1, q2) -> (q1.numberInQueue - q2.numberInQueue));
    		} 
		
		for (int i = 0; i < queuesNbr; i++) {
			System.out.println("Medelantalet kunder i kösystem " + (i+1) + ": " + 1.0*qList.get(i).accumulated/qList.get(i).noMeasurements);
		}
		


	



		/** UPPGIFT 1e) 
		 * 		Generator.lambda = 8;
		 *		int queuesNbr = 3;
		Generator.sendTo = qList.get(0);
		qList.get(0).sendTo = qList.get(1);
		qList.get(1).sendTo = qList.get(2);
				while (time < 100000){

    		} 

		*/

		/** SLUMP
		Random rand = new Random();
		while (time < 100000){
			Generator.sendTo = qList.get(rand.nextInt(5));

    		} */

		/** EN EFTER EN
		int q = 0;
		while (time < 100000){
			Generator.sendTo = qList.get(q);
			q++;
			if (q > 4) {
				q = 0;
			}
    		}  */

		/** EFTER DEN SOM HAR MINST JOBB
		while (time < 100000){
			Generator.sendTo = qList.get(0);

			qList.sort((q1, q2) -> (q1.numberInQueue - q2.numberInQueue));
    		} */



	}
}