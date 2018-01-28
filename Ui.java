package myReading;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

public class Ui {
	public static void main( String[] args ) throws IOException, ParseException
    {
        //System.out.println( "Hello World!" );
        Reading reading = new Reading();
        reading.start();
        System.out.println("Full Array List:");
        for(HashMap<String, Object> k : Reading.patient_readings) {
        	System.out.println(k.toString());
        }
    }
}
