import java.io.*;


public class MainSimulation extends GlobalSimulation{
 
    public static void main(String[] args) throws IOException {
    	Event actEvent;
    	EventList myEventList = new EventList();
    	State actState = new State(myEventList);
        myEventList.InsertEvent(ARRIVALA, 0);
        myEventList.InsertEvent(MEASURE, 500);
    	while (time < 1000){
    		actEvent = myEventList.FetchEvent();
    		time = actEvent.eventTime;
    		actState.TreatEvent(actEvent);
    	}
    	System.out.println("Mean number of customers: " + 1.0*actState.accumulated/actState.noMeasurements);
		System.out.println("Mean number of customers A: " + 1.0*actState.accumulatedA/actState.noMeasurements);
		System.out.println("Mean number of customers B: " + 1.0*actState.accumulatedB/actState.noMeasurements);
    	System.out.println("Number of measurements done: " + actState.noMeasurements);
    }
}