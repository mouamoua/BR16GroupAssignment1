import java.util.ArrayList;
import java.util.HashMap;

public class Trial {
	private ArrayList<Patient> patients;
	
	public Trial(ArrayList<HashMap<String, Object>> patientReadings) {
		patients = new ArrayList<Patient>();
		for (HashMap<String, Object> patientReading : patientReadings) {
			// Instantiate a Patient and add to patients list if the patient does not exist in the list
			String patientId = (String) patientReading.get("patient_id");
			boolean isExistingPatient = false;
			for (Patient patient : patients) {
				if (patient.getId() == patientId) {
					isExistingPatient = true;
					break;
				}
			}
			if (!isExistingPatient) {
				patients.add(new Patient(patientId));
			}
			
			// Add new Reading to the corresponding Patient's readings list
			Reading reading = new Reading(patientReading);
			addReadingToPatient(reading.getPatientId(), reading);
        }
	}
	
	public ArrayList<Patient> getPatients() {
		return patients;
	}

	public void addReadingToPatient(String patientId, Reading reading) {
		Patient patient = findPatientById(patientId);
		if (patient.isPartOfTrial()) {
			patient.addReading(reading);
		}
	}
	
	public void addReadingToPatient(String patientId, String type, String id, Object value, long date) {
		Patient patient = findPatientById(patientId);
		patient.addReading(new Reading(patientId, type, id, value, date));
	}

	public void startPatientTrial(String patientId) {
		findPatientById(patientId).setPartOfTrial(true);
	}
	
	public void endPatientTrial(String patientId) {
		findPatientById(patientId).setPartOfTrial(false);
	}
	
	@Override
	public String toString() {
		String returnString = "{\n" + "\t\"patient_readings\":[\n";
		for (Patient patient : patients) {
			for (Reading reading : patient.getReadings()) {
				returnString += "\t\t{\n";
				returnString += reading.toString();
				
				if (patient.getReadings().indexOf(reading) == (patient.getReadings().size() - 1)) {
					if (getPatients().indexOf(patient) == (getPatients().size() - 1)) {
						returnString += "\t\t}\n";
						break;
					}
					returnString += "\t\t},\n";
				} 
			}
		}
		
		returnString += "\t]\n" + "}";
		return returnString;
	}

	private Patient findPatientById(String patientId) {
		Patient matchingPatient = null;
		for (Patient patient : patients) {
			if (patient.getId().equals(patientId)) {
				matchingPatient = patient;
				break;
			}
		}
		return matchingPatient;
	}
}
