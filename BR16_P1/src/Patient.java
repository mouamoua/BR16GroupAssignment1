import java.util.ArrayList;

public class Patient {
	
	private String id;
	private ArrayList<Reading> readings;
	private boolean isPartOfTrial;
	
	public Patient(String id) {
		this.id = id;
		readings = new ArrayList<Reading>();
		isPartOfTrial = true;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public ArrayList<Reading> getReadings() {
		return readings;
	}

	public void addReading(Reading reading) {
		if (isPartOfTrial()) {
			readings.add(reading);
		}
	}
	
	public boolean isPartOfTrial() {
		return isPartOfTrial;
	}
	
	public void setPartOfTrial(boolean isPartOfTrial) {
		this.isPartOfTrial = isPartOfTrial;
	}

	@Override
	public String toString() {
		String returnString = "";
		for (Reading reading : readings) {
			returnString += "{\n";
			returnString += reading.toString();
			returnString += "}";
		}
		return returnString;
	}
}
