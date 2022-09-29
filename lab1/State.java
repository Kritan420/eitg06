import java.util.*;

class State extends GlobalSimulation{
	
	public int numberInQueueA = 0, numberInQueueB = 0, accumulated = 0, accumulatedA = 0, accumulatedB = 0, noMeasurements = 0;
	
	private EventList myEventList;

	Random slump = new Random();
	
	State(EventList x){
		myEventList = x;
	}
	
	private void InsertEvent(int event, double timeOfEvent){
		myEventList.InsertEvent(event, timeOfEvent);
	}
	
	
	public void TreatEvent(Event x){
		switch (x.eventType){
			case ARRIVALA:
				arrivalA();
				break;
			case ARRIVALB:
				arrivalB();
				break;
			case READYA:
				readyA();
				break;
			case READYB:
				readyB();
				break;
			case MEASURE:
				measure();
				break;
		}
	}
	
	private double generateMean(double mean){
		return 2*mean*slump.nextDouble();
	}
	
	/**private void arrival(){
		if (numberInQueue == 0)
			InsertEvent(READY, time + generateMean(1));
		numberInQueue++;
		InsertEvent(ARRIVAL, time + generateMean(1/0.98));
	}**/

	private void arrivalA(){

		if (numberInQueueA + numberInQueueB == 0) {
			InsertEvent(READYA, time + 0.002);
		}
			
		numberInQueueA++;
		InsertEvent(ARRIVALA, time + generateMean(1.0/150));
	}

	private void arrivalB(){
		if (numberInQueueA + numberInQueueB == 0) {
			InsertEvent(READYB, time + 0.004); }
		numberInQueueB++;
	}

    private void readyA()
    {
        numberInQueueA--;
		accumulatedA ++;
        if(numberInQueueB > 0) {
            InsertEvent(READYB, time + 0.004);   } 
        else if(numberInQueueA > 0) {
            InsertEvent(READYA, time + 0.002); }
       
        InsertEvent(ARRIVALB, time + 1/**generateMean(1)*/);
    }
	private void readyB(){
		numberInQueueB--;
		accumulatedB++;
        if(numberInQueueB > 0) {
            InsertEvent(READYB, time + 0.004);   }
        else if(numberInQueueA > 0) {
            InsertEvent(READYA, time + 0.002); }
		}
		
	/*private void ready(){
		numberInQueue--;
		if (numberInQueue > 0)
			InsertEvent(READY, time + 1/**generateMean(1));
			
	}*/
	
	private void measure(){
		if (noMeasurements < 1000) {
			accumulated = accumulated + numberInQueueA + numberInQueueB;
			noMeasurements++;
		}
		InsertEvent(MEASURE, time + 0.1);
	}
	}