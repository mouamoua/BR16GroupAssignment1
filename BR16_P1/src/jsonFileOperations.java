
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.*;

public class jsonFileOperations {
 public static JsonElement getFile(String path) throws FileNotFoundException {
	 
		FileReader fr = new FileReader(path);
		return new JsonParser().parse(fr);
 }
 
 public static void writeFile(String path, Object object) throws IOException {
	 Gson gson = new Gson();
	 gson.toJson(object, new FileWriter(path));
 }

}
