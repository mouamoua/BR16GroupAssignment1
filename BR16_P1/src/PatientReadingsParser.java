
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.*;

//ALL print statements you see are simply for testing

public class PatientReadingsParser {
	//this holds the data in an ArrayList of HashMaps
	static ArrayList<HashMap<String, Object>> patient_readings = new ArrayList<HashMap<String, Object>>();
	
	public static void start(String location) throws IOException {
		patient_readings.clear();
		String path = location;
		FileReader fr = new FileReader(path);
		JsonElement data = new JsonParser().parse(fr);
		getReading(data);
		
		
	}
	
	//this reads the json file
	public static void getReading(JsonElement data) throws IOException{	
		//checks if it's an object with class values
		if (data.isJsonObject()) {
	        System.out.println("Is an object");
	        JsonObject jObj = data.getAsJsonObject();
	        java.util.Set<java.util.Map.Entry<String,JsonElement>> entries = jObj.entrySet();
	        java.util.Iterator<java.util.Map.Entry<String,JsonElement>> iter = entries.iterator();
	        
	        //travels through the object 
	        while (iter.hasNext()) {
	            java.util.Map.Entry<String,JsonElement> entry = iter.next();
	            Gson gson = new Gson();
	            HashMap hm = gson.fromJson(data, HashMap.class);
	            System.out.println(hm.toString());

	            if(hm.containsKey("patient_readings")) {
	            	System.out.println("MATCH");
	            	getReading(entry.getValue());
	            }

	            else if(hm.containsKey("patient_id")){
	            	patient_readings.add(hm);
	            	System.out.println("Size: " + patient_readings.size());
	            	System.out.println("Key: " + entry.getKey());
		            System.out.println("Value:");
		            while(iter.hasNext()) {
		            	entry = iter.next();
		            }
		            getReading(entry.getValue());
	            }
	        }

	    } else if (data.isJsonArray()) {
	        JsonArray array = data.getAsJsonArray();
	        System.out.println("Is an array. Number of values: " + array.size());
	        java.util.Iterator<JsonElement> iter = array.iterator();
	        while (iter.hasNext()) {
	            JsonElement entry = iter.next();
	            getReading(entry);
	        }
	    }
		
		patient_readings.getClass();
	}


}
