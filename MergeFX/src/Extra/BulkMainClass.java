package Extra;
import BackEnd.GetNumbInfo;
import java.util.Scanner;
import java.io.File;
public class BulkMainClass {

	
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		//System.out.println("Please enter the name of the file:");
		String infoFile = scan.next();
		File file = new File(infoFile);	
		Scanner input = new Scanner(file);
		
		while (input.hasNext()) {
			//get 7 digit phone number
			long phone = input.nextLong();
			long trimNumb = phone % 10000000;
			int phoneNumb = (int)trimNumb;
			
			//entering occupation
			String occupation = input.next();
			
			//entering any other descriptors
			String[] descriptors = new String[5];
			for(int i=0; i<5; i++) {
				descriptors[i] = input.next();
			}
			
			//option to be system administrator
			Boolean sysAdmin;
			String sysAdminStr = input.next();
			sysAdmin = (sysAdminStr != "0");
			
			
			scan.close();
			
			//helpful code to check if running correctly
			//System.out.println("runOperation Begining...");
			
			//pass the information to the GetNumbInfo class
			GetNumbInfo.runOperation(phoneNumb, occupation, descriptors, sysAdmin);
		
		}
		input.close();
	}
}