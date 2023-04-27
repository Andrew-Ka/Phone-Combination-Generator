package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DisplayResultsCtrller {
	
	@FXML
	Label Recommended;
	@FXML
	Label sysAdminTxt;
	
	public void displayResult(String result, Boolean sysAdmin) {
		/*String previous = Recommended.getText();
		if(previous == null) {
			Recommended.setText(result);
		}else {
			result += previous;
			Recommended.setText(result);
		}*/
		Recommended.setText(result);
		
		if(sysAdmin) {
			sysAdminTxt.setText("For System Admin, other possible \n" 
					+ "combinations can be found in the \n"
					+ "project folder");
		}
	}
	
	
	

}
