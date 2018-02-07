
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.*;

public class jsonFileOperations {
 public static void getFile(String path) throws IOException {
	 try {
			PatientReadingsParser.start(path);
	 } catch (IOException e) {
			e.printStackTrace();
	 }
 }
 
 public static void writeFile(String path, Object object) throws IOException {
	 Gson gson = new Gson();

     try (FileWriter writer = new FileWriter(path)) {
         gson.toJson(object, writer);
     } catch (IOException e) {
         e.printStackTrace();
     }
 }
 
}
