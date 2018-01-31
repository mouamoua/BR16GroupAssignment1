import java.util.HashMap;

public class Reading {
	private String patientId;
	private String type;
	private String id;
	private Object value;
	private double date;
	
	public Reading(HashMap<String, Object> reading) {
		this.patientId = (String) reading.get("patient_id");
		this.type      = (String) reading.get("reading_type");
		this.id        = (String) reading.get("reading_id");
		this.value     = reading.get("reading_value");
		this.date      = (double) reading.get("reading_date");
	}
	
	public Reading(String patientId, String type, String id, Object value, double date) {
		this.patientId = patientId;
		this.type = type;
		this.id = id;
		this.value = value;
		this.date = date;
	}
	
	public String getPatientId() {
		return patientId;
	}

	public String getType() {
		return type;
	}

	public String getId() {
		return id;
	}

	public Object getValue() {
		return value;
	}

	public double getDate() {
		return date;
	}

	@Override
	public String toString() {
		return	"\t\t\t\"patient_id\" : \""   + patientId  + "\",\n" +
				"\t\t\t\"reading_type\" : \"" + type       + "\",\n" +
				"\t\t\t\"reading_id\" : \""   + id         + "\",\n" +
				"\t\t\t\"reading_value\" : "  + value      + ",\n"   +
				"\t\t\t\"reading_date\" : "   + date       + "\n";
	}
}
