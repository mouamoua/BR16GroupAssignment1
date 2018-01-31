
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

public class Test {
	public static void main( String[] args ) throws IOException, ParseException
    {
        PatientReadingsParser trialFileData = new PatientReadingsParser();
        trialFileData.start();
        // Print the example data
        System.out.println("Full Array List:");
        for (HashMap<String, Object> k : PatientReadingsParser.patient_readings) {
        	System.out.println(k.toString());
        }
        
        // Example of current data after it has been organized
        Trial trial = new Trial(trialFileData.patient_readings);
        System.out.println(trial);
    }
}
