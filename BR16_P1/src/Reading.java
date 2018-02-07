
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.gson.*;

//ALL print statements you see are simply for testing

public class Reading {
	//this holds the datas in an ArrayList
	static ArrayList<HashMap<String, Object>> patient_readings = new ArrayList<HashMap<String, Object>>();
	static int pr_size = 0;
	
	public static void start(String fileLocation) throws IOException {
		//PLEASE READ: I am currently working on this
		//				Ideally, I want it to check if there is such a json file
		//				if NOT, it will create one
		//				if TRUE, it will LOAD data from it
		//				this will allow the user to store new readings into the file
		//				and will create a default database when you FIRST run it.
		//				However, I'm still trying to figure out how to do RELATIVE file pathing.
		//				any help would be great.
		String path = fileLocation;
		JsonElement data = jsonFileOperations.getFile(path);
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
	            //this if-statement was made to bypass the initial array
	            //because of how the json file was created
	            //what happens is patient_readings is a key the other 4 HashMaps
	            //become its value
	            //so this is a messy way to bypass such a thing
	            //however, if you've figured out a cleaner way to do this please feel free to edit this
	            if(hm.containsKey("patient_readings")) {
	            	System.out.println("MATCH");
	            	getReading(entry.getValue());
	            }
	            //inside EVERY reading, stored HashMap, there 4 key&values and is a patient_id
	            //what this does is skips the OTHER jsonObject so that 4 copies of the SAME HashMap
	            //are not put into the static ArrayList database
	            //Otherwise, every time it hits patient_id it creates a copy, then reading_value creates a copy,
	            //then  reading_date creates a copy, etc.
	            //so it skips the rest and goes onto the next jsonArray value
	            else if(hm.containsKey("patient_id")){
	            	patient_readings.add(hm);
	            	pr_size = patient_readings.size();
	            	System.out.println("pr_size = "+pr_size);
	            	System.out.println("Key: " + entry.getKey());
		            System.out.println("Value:");
		            while(iter.hasNext()) {
		            	entry = iter.next();
		            }
		            getReading(entry.getValue());
	            }
	        }
	    //this applies to patient_readings only in the example.json
	    //since patient_readings is an array
	    //this will continue to go through the jsonArray until it is done.
	    } else if (data.isJsonArray()) {
	        JsonArray array = data.getAsJsonArray();
	        System.out.println("Is an array. Number of values: " + array.size());
	        java.util.Iterator<JsonElement> iter = array.iterator();
	        while (iter.hasNext()) {
	            JsonElement entry = iter.next();
	            getReading(entry);
	        }
	    }
	}
}
