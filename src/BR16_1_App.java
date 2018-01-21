import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.DateTime;

public class BR16_1_App {

	protected Shell shell;
	private Text txt_loadfile;
	private Text txt_savefile;
	private Table tblShowPatientReadings;
	private Text txtAddNewPatient;
	private Text txtAddPatientReading_ReadingValue;
	private Text txtAddPatientReading_ReadingID;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BR16_1_App window = new BR16_1_App();
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
		shell.setSize(699, 664);
		shell.setText("Group Project 1 - BR16");
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(38, 20, 612, 318);
		
		Label lblLoadFile = new Label(composite, SWT.NONE);
		lblLoadFile.setBounds(10, 27, 64, 24);
		lblLoadFile.setText("Load File: ");
		
		txt_loadfile = new Text(composite, SWT.BORDER);
		txt_loadfile.setBounds(79, 27, 422, 24);
		
		Button btnLoadFile = new Button(composite, SWT.NONE);
		btnLoadFile.setBounds(519, 27, 75, 25);
		btnLoadFile.setText("Load");
		
		Label lblSaveFile = new Label(composite, SWT.NONE);
		lblSaveFile.setBounds(10, 65, 64, 24);
		lblSaveFile.setText("Save File: ");
		
		txt_savefile = new Text(composite, SWT.BORDER);
		txt_savefile.setBounds(79, 65, 422, 24);
		
		Button btnSaveFile = new Button(composite, SWT.NONE);
		btnSaveFile.setBounds(519, 65, 75, 25);
		btnSaveFile.setText("Save");
		
		Combo cboShowPatientReadings = new Combo(composite, SWT.NONE);
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
		lblPatientManagement.setBounds(38, 364, 165, 22);
		lblPatientManagement.setText("Patient Management");
		
		Label lblAddPatient = new Label(shell, SWT.NONE);
		lblAddPatient.setBounds(55, 408, 67, 15);
		lblAddPatient.setText("Add Patient");
		
		txtAddNewPatient = new Text(shell, SWT.BORDER);
		txtAddNewPatient.setBounds(123, 408, 115, 21);
		
		Button btnSaveNewPatient = new Button(shell, SWT.NONE);
		btnSaveNewPatient.setBounds(244, 408, 75, 25);
		btnSaveNewPatient.setText("Save");
		
		Label lblEditPatient = new Label(shell, SWT.NONE);
		lblEditPatient.setBounds(55, 449, 67, 15);
		lblEditPatient.setText("Edit Patient");
		
		Combo cboPatientStatus = new Combo(shell, SWT.NONE);
		cboPatientStatus.setBounds(128, 449, 121, 23);
		cboPatientStatus.setText("Select Patient");
		
		Button btnPatientStatus = new Button(shell, SWT.CHECK);
		btnPatientStatus.setBounds(277, 456, 93, 16);
		btnPatientStatus.setText("Active");
		
		Label lblAddReading = new Label(shell, SWT.NONE);
		lblAddReading.setBounds(55, 496, 75, 15);
		lblAddReading.setText("Add Reading");
		
		Combo cboAddPatientReading_Patient = new Combo(shell, SWT.NONE);
		cboAddPatientReading_Patient.setBounds(65, 517, 121, 23);
		cboAddPatientReading_Patient.setText("Select Patient");
		
		Combo cboAddPatientReading_Type = new Combo(shell, SWT.NONE);
		cboAddPatientReading_Type.setBounds(192, 517, 140, 23);
		cboAddPatientReading_Type.setText("Select Reading Type");
		
		txtAddPatientReading_ReadingValue = new Text(shell, SWT.BORDER);
		txtAddPatientReading_ReadingValue.setBounds(338, 517, 115, 21);
		
		Label lblReadingValue = new Label(shell, SWT.NONE);
		lblReadingValue.setBounds(354, 496, 82, 15);
		lblReadingValue.setText("Reading Value");
		
		DateTime dtAddPatientReading_ReadingTimestamp = new DateTime(shell, SWT.BORDER);
		dtAddPatientReading_ReadingTimestamp.setBounds(459, 517, 80, 24);
		
		txtAddPatientReading_ReadingID = new Text(shell, SWT.BORDER);
		txtAddPatientReading_ReadingID.setBounds(545, 519, 105, 21);
		
		Label lblReadingDate = new Label(shell, SWT.NONE);
		lblReadingDate.setBounds(459, 496, 80, 15);
		lblReadingDate.setText("Reading Date");
		
		Label lblReadingId = new Label(shell, SWT.NONE);
		lblReadingId.setBounds(556, 496, 67, 15);
		lblReadingId.setText("Reading ID");
		
		Button btnSaveReading = new Button(shell, SWT.NONE);
		btnSaveReading.setBounds(575, 563, 75, 25);
		btnSaveReading.setText("Save");
		
		Button btnCancelReading = new Button(shell, SWT.NONE);
		btnCancelReading.setBounds(494, 563, 75, 25);
		btnCancelReading.setText("Cancel");

	}
}
