package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import BackEnd.GetNumbInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class EnterInfoCtrller implements Initializable {

	Long PhoneNumber;
	String Occ = "";
	String[] descriptors = { "0", "0", "0", "0", "0" };
	Boolean isSysAdmin = false;

	File test;
	FileChooser fileChooser = new FileChooser();
	@FXML
	private TextField TestLocation;

	private Stage stage;
	private Scene scene;
	private Parent root;

	String Result;

	// all the stuff in the GUI
	@FXML
	TextField phoneNumber;
	@FXML
	TextField descriptor1;
	@FXML
	TextField descriptor2;
	@FXML
	TextField descriptor3;
	@FXML
	TextField descriptor4;
	@FXML
	TextField descriptor5;
	@FXML
	RadioButton systemAdmin;

	// easy to tell user what to do
	@FXML
	public Label Warning;

	// getting the phone number
	public void getNumb(ActionEvent e) throws IOException {
		try {
			PhoneNumber = Long.valueOf(phoneNumber.getText());

			if ((PhoneNumber < 1000000) || (PhoneNumber > 9999999)) {
				Warning.setText("Please Enter Exactly 7 Digits!");
			} else {
				Warning.setText("");
			}

		} catch (NumberFormatException j) {
			Warning.setText("Please Enter Numbers!");
		} catch (Exception h) {

		}
	}

	// for the occupation
	@FXML
	private ChoiceBox<String> Occupation;
	private String[] Occupations = { "Arts", "Business", "Designer", "Education", "Home-Services", "Law", "Medical",
			"Technology" };

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Occupation.getItems().addAll(Occupations);
		Occupation.setOnAction(this::getOcc);

		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
	}

	public void getOcc(ActionEvent e) {
		Occ = Occupation.getValue();
		System.out.println(Occ + " is selected.");
	}

	// for sysAdmin
	public void sysAdmin(ActionEvent e) {
		isSysAdmin = systemAdmin.isSelected();
	}

	// for the descriptors
	public void addDescriptor1(ActionEvent e) {
		descriptors[0] = descriptor1.getText();
		System.out.println(descriptors[0] + " has been added.");
	}

	public void addDescriptor2(ActionEvent e) {
		descriptors[1] = descriptor2.getText();
	}

	public void addDescriptor3(ActionEvent e) {
		descriptors[2] = descriptor3.getText();
	}

	public void addDescriptor4(ActionEvent e) {
		descriptors[3] = descriptor4.getText();
	}

	public void addDescriptor5(ActionEvent e) {
		descriptors[4] = descriptor5.getText();
	}

	// submit
	public void submit(ActionEvent e) throws IOException {

		if ((PhoneNumber != null) && !((PhoneNumber < 1000000) || (PhoneNumber > 9999999))) {
			Warning.setText("Loading.......");
			System.out.println("Info Collected Successfully");
			System.out.println("Passing information to GetNumbInfo");

			try {
				Warning.setText("Loading..");
				Result = GetNumbInfo.runOperation(PhoneNumber, Occ, descriptors, isSysAdmin);
			} catch (Exception e1) {
				System.out.println("Dang it, almost there!");
				e1.printStackTrace();
			}

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/DisplayResult.fxml"));
			root = loader.load();

			DisplayResultsCtrller resultsDisplayed = loader.getController();
			resultsDisplayed.displayResult(Result, isSysAdmin);

			// root = FXMLLoader.load(getClass().getResource("/UI/DisplayResult.fxml"));
			stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		} else if (PhoneNumber == null) {
			Warning.setText("Must Enter a Phone Number!");
		} else {
			Warning.setText("Must Enter 7 Digits for Phone Number");
		}

	}

	public void submitTest(ActionEvent e) throws IOException {
		test = fileChooser.showOpenDialog(new Stage());
		doResults(test, e);
	}

	public void doResults(File file, ActionEvent e) throws IOException {
		String result = "";
		try {
			Scanner scanFile = new Scanner(file);
			while (scanFile.hasNext()) {
				// get 7 digit phone number
				PhoneNumber = scanFile.nextLong();

				// entering occupation
				Occ = scanFile.next();

				// entering any other descriptors
				for (int i = 0; i < 5; i++) {
					descriptors[i] = scanFile.next();
				}

				// option to be system administrator
				String sysAdminStr = scanFile.next();
				isSysAdmin = (sysAdminStr != "0");

				if (result == "") {
					try {
						result = GetNumbInfo.runOperation(PhoneNumber, Occ, descriptors, isSysAdmin);
					} catch (Exception d) {
						System.out.println("Dannng itt!");
						d.printStackTrace();
					}
				} else {
					try {
						result += GetNumbInfo.runOperation(PhoneNumber, Occ, descriptors, isSysAdmin);
					} catch (Exception d) {
						System.out.println("dang");
						d.printStackTrace();
					}
				}
			}

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/DisplayResult.fxml"));
			root = loader.load();

			DisplayResultsCtrller resultsDisplayed = loader.getController();
			resultsDisplayed.displayResult(result, isSysAdmin);

			// root = FXMLLoader.load(getClass().getResource("/UI/DisplayResult.fxml"));
			stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

			scanFile.close();
		} catch (FileNotFoundException q) {
			Warning.setText("File not found, search again");
			q.printStackTrace();
		}

	}

}
