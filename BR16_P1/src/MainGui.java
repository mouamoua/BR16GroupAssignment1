import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.eclipse.swt.widgets.DateTime;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class MainGui {

	protected Shell shell;
	private Text txt_loadfile;
	private Text txt_savefile;
	private Table tblShowPatientReadings;
	private Text txtAddPatientReading_ReadingValue;
	private Text txtAddPatientReading_ReadingID;
	private Combo cboAddPatientReading_Patient;
	private Combo cboAddPatientReading_Type;
	private Combo cboShowPatientReadings;
	private Combo cboPatientStatus;
	private DateTime dtAddPatientReading_ReadingTimestamp;
	private Button btnPatientStatus;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainGui window = new MainGui();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(699, 576);
		shell.setText("Group Project 1 - BR16");
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(38, 20, 612, 318);
		
		Label lblLoadFile = new Label(composite, SWT.NONE);
		lblLoadFile.setBounds(10, 27, 64, 24);
		lblLoadFile.setText("Load File: ");
		
		txt_loadfile = new Text(composite, SWT.BORDER);
		txt_loadfile.setText("example.json");
		txt_loadfile.setBounds(79, 27, 422, 24);
		
		Button btnLoadFile = new Button(composite, SWT.NONE);
		btnLoadFile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				loadFile(txt_loadfile.getText());
			}

		});
		btnLoadFile.setBounds(519, 27, 75, 25);
		btnLoadFile.setText("Load");
		
		Label lblSaveFile = new Label(composite, SWT.NONE);
		lblSaveFile.setBounds(10, 65, 64, 24);
		lblSaveFile.setText("Save File: ");
		
		txt_savefile = new Text(composite, SWT.BORDER);
		txt_savefile.setBounds(79, 65, 422, 24);
		
		Button btnSaveFile = new Button(composite, SWT.NONE);
		btnSaveFile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveFile();
			}
		});
		btnSaveFile.setBounds(519, 65, 75, 25);
		btnSaveFile.setText("Save");
		
		cboShowPatientReadings = new Combo(composite, SWT.NONE);
		cboShowPatientReadings.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				loadTable(cboShowPatientReadings.getText());
			}

		});
		cboShowPatientReadings.setItems(new String[] {});
		cboShowPatientReadings.setBounds(104, 95, 155, 23);
		cboShowPatientReadings.setText("Select Patient");
		
		Label lblShowReadings = new Label(composite, SWT.NONE);
		lblShowReadings.setBounds(7, 95, 91, 15);
		lblShowReadings.setText("Show Readings: ");
		
		tblShowPatientReadings = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		tblShowPatientReadings.setBounds(10, 132, 518, 176);
		tblShowPatientReadings.setHeaderVisible(true);
		tblShowPatientReadings.setLinesVisible(true);
		
		TableColumn tblclmnPatientID = new TableColumn(tblShowPatientReadings, SWT.NONE);
		tblclmnPatientID.setWidth(112);
		tblclmnPatientID.setText("Patient ID");
		
		TableColumn tblclmnReadingID = new TableColumn(tblShowPatientReadings, SWT.NONE);
		tblclmnReadingID.setWidth(100);
		tblclmnReadingID.setText("Reading ID");
		
		TableColumn tblclmnReadingType = new TableColumn(tblShowPatientReadings, SWT.NONE);
		tblclmnReadingType.setWidth(100);
		tblclmnReadingType.setText("Reading Type");
		
		TableColumn tblclmnReadingValue = new TableColumn(tblShowPatientReadings, SWT.NONE);
		tblclmnReadingValue.setWidth(100);
		tblclmnReadingValue.setText("Value");
		
		TableColumn tblclmnReadingDate = new TableColumn(tblShowPatientReadings, SWT.NONE);
		tblclmnReadingDate.setWidth(100);
		tblclmnReadingDate.setText("Date");
		
		Label lblPatientManagement = new Label(shell, SWT.NONE);
		lblPatientManagement.setFont(SWTResourceManager.getFont("Arial", 15, SWT.BOLD));
		lblPatientManagement.setBounds(38, 364, 235, 22);
		lblPatientManagement.setText("Patient Management");
		
		Label lblEditPatient = new Label(shell, SWT.NONE);
		lblEditPatient.setBounds(48, 392, 67, 15);
		lblEditPatient.setText("Edit Patient");
		
		cboPatientStatus = new Combo(shell, SWT.NONE);
		cboPatientStatus.setBounds(121, 392, 121, 23);
		cboPatientStatus.setText("Select Patient");
		cboPatientStatus.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setStatus(cboPatientStatus.getText());
			}
		});
		
		btnPatientStatus = new Button(shell, SWT.CHECK);
		btnPatientStatus.setBounds(270, 399, 93, 16);
		btnPatientStatus.setText("Active");
		btnPatientStatus.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				changeStatus(e);
			}
		});
		
		Label lblAddReading = new Label(shell, SWT.NONE);
		lblAddReading.setBounds(48, 432, 75, 15);
		lblAddReading.setText("Add Reading");
		
		cboAddPatientReading_Patient = new Combo(shell, SWT.NONE);
		cboAddPatientReading_Patient.setBounds(42, 453, 121, 23);
		cboAddPatientReading_Patient.setText("Select Patient");
		
		cboAddPatientReading_Type = new Combo(shell, SWT.NONE);
		cboAddPatientReading_Type.setItems(new String[] {"weight", "steps", "temp", "blood_press"});
		cboAddPatientReading_Type.setBounds(169, 453, 140, 23);
		cboAddPatientReading_Type.setText("Select Reading Type\r");
		
		txtAddPatientReading_ReadingValue = new Text(shell, SWT.BORDER);
		txtAddPatientReading_ReadingValue.setBounds(315, 455, 115, 21);
		
		Label lblReadingValue = new Label(shell, SWT.NONE);
		lblReadingValue.setBounds(339, 432, 82, 15);
		lblReadingValue.setText("Reading Value");
		
		dtAddPatientReading_ReadingTimestamp = new DateTime(shell, SWT.BORDER);
		dtAddPatientReading_ReadingTimestamp.setBounds(436, 455, 80, 24);
		
		txtAddPatientReading_ReadingID = new Text(shell, SWT.BORDER);
		txtAddPatientReading_ReadingID.setBounds(522, 457, 105, 21);
		
		Label lblReadingDate = new Label(shell, SWT.NONE);
		lblReadingDate.setBounds(444, 432, 80, 15);
		lblReadingDate.setText("Reading Date");
		
		Label lblReadingId = new Label(shell, SWT.NONE);
		lblReadingId.setBounds(541, 432, 67, 15);
		lblReadingId.setText("Reading ID");
		
		Button btnSaveReading = new Button(shell, SWT.NONE);
		btnSaveReading.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addReading();
			}
		});
		btnSaveReading.setBounds(560, 499, 75, 25);
		btnSaveReading.setText("Save");
		
		Button btnCancelReading = new Button(shell, SWT.NONE);
		btnCancelReading.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cancelReading();
			}
		});
		btnCancelReading.setBounds(479, 499, 75, 25);
		btnCancelReading.setText("Cancel");

	}
	
	protected void setStatus(String text) {
		for(Patient p: patientList){
			String patientID = p.getId();
			if(patientID.equals(text)) {
				this.btnPatientStatus.setSelection(p.isPartOfTrial());
				break;
			}
		}
	}

	protected void changeStatus(SelectionEvent e) {
		String patientID = this.cboPatientStatus.getItem(this.cboPatientStatus.getSelectionIndex());
		Button btn = (Button)e.getSource();
		boolean value = btn.getSelection();
		for(Patient p: patientList){
			if(p.getId().equals(patientID)) {
				if(!(value == p.isPartOfTrial())) {
					p.setPartOfTrial(value);
					if(value) {
						this.cboAddPatientReading_Patient.add(patientID);
						this.cboShowPatientReadings.add(patientID);
					}else {
						this.cboAddPatientReading_Patient.remove(patientID);
						this.cboShowPatientReadings.remove(patientID);
					}
				}
				break;
			}
		}
	}

	protected void saveFile() {
		//NOT IMPLEMENTED CORRECTLY
		Gson gson = new Gson();
		try {
			jsonFileOperations.writeFile(txt_savefile.getText(), PatientReadingsParser.patient_readings);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	protected void cancelReading() {
		this.txtAddPatientReading_ReadingID.setText("");
		this.txtAddPatientReading_ReadingValue.setText("");
		this.cboAddPatientReading_Patient.clearSelection();
		this.cboAddPatientReading_Type.clearSelection();
	}

	protected void addReading() {
		HashMap<String, Object> newReading = new HashMap<String,Object>();
		newReading.put("patient_id", this.cboAddPatientReading_Patient.getItem(this.cboAddPatientReading_Patient.getSelectionIndex()));
		newReading.put("reading_type", this.cboAddPatientReading_Type.getItem(this.cboAddPatientReading_Type.getSelectionIndex()));
		newReading.put("reading_id", this.txtAddPatientReading_ReadingID.getText());
		newReading.put("reading_value", this.txtAddPatientReading_ReadingValue.getText());
		newReading.put("reading_date", LocalDateTime.of(
				this.dtAddPatientReading_ReadingTimestamp.getYear(),
				this.dtAddPatientReading_ReadingTimestamp.getMonth()+1,
				this.dtAddPatientReading_ReadingTimestamp.getDay(),0,0).toEpochSecond(ZoneOffset.ofHours(0))*1000);
		PatientReadingsParser.patient_readings.add(newReading);
		this.cancelReading();
	}

	private ArrayList<Patient> patientList = new ArrayList<Patient>();
	private void loadFile(String text) {
		try {
			PatientReadingsParser.start(text);
			
			//loadTable();
			loadLists();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void loadLists() {
		Iterator<HashMap<String, Object>> iter = PatientReadingsParser.patient_readings.iterator();
		while(iter.hasNext()) {
			HashMap<String, Object> reading = (HashMap<String, Object>)iter.next();
			if(cboShowPatientReadings.indexOf(reading.get("patient_id").toString())==-1)
			{
				patientList.add(new Patient(reading.get("patient_id").toString()));
				cboShowPatientReadings.add(reading.get("patient_id").toString());
				cboAddPatientReading_Patient.add(reading.get("patient_id").toString());
				cboPatientStatus.add(reading.get("patient_id").toString());
				
			}
		}		
	}


	private void addTableItem(HashMap reading, Table target) {
		Object[] keys = reading.keySet().toArray();
		TableItem item = new TableItem(target, SWT.NONE);
		for(int i=0; i<keys.length; i++){
			String key = (String)keys[i];
			switch (key) {
			case "patient_id":
				item.setText(0, reading.get(keys[i]).toString());
				break;
			case "reading_id":
				item.setText(1, reading.get(keys[i]).toString());
				break;
			case "reading_type":
				item.setText(2, reading.get(keys[i]).toString());
				break;
			case "reading_value":
				item.setText(3, reading.get(keys[i]).toString());
				break;
			case "reading_date":
				Long dateValue;
				if(reading.get(keys[i]).getClass()==Double.class)
					dateValue = ((Double)reading.get(keys[i])).longValue();
				else
					dateValue = (Long)reading.get(keys[i]);
				Date dt = new Date(dateValue);
				item.setText(4, dt.toString());
				break;
			}
			
		}
	}
	private void loadTable(String text) {
		tblShowPatientReadings.removeAll();
		Iterator iter = PatientReadingsParser.patient_readings.iterator();
		while(iter.hasNext()) {
			
			HashMap reading = (HashMap)iter.next();
			if(reading.containsValue(text))
				addTableItem(reading,tblShowPatientReadings);			
		}		
	}
}