package Extra;
import BackEnd.GetNumbInfo;
import java.util.Scanner;
public class GeneralMainClass {

	public static void main(String[] args) throws Exception{
		Scanner input = new Scanner(System.in);
		
		//get 7 digit phone number
		System.out.println("Enter a 7 digit phone number: ");
		long phone = input.nextInt();
		while ((phone < 1000000) || (phone > 9999999)) {
			System.out.println("Please enter 7 digits");
			phone = input.nextLong();
		}
		int phoneNumb = (int)phone;
		
		//get occupation
		String[] occupations = {"law", "business", "medical", "designer",
				"education", "technology", "arts", " home-services"};
		
		System.out.println("Occupations to pick from are:");
		for(int i=0; i<occupations.length;i++) {
			System.out.print(occupations[i] + ", ");
		}
		System.out.println();
		System.out.println("Enter Occupation Sector, enter 0 to skip");
		String occupation = input.next();
		
		//get descriptors
		System.out.println("Enter any additional information (type 0 for nothing, max 5): ");
		String[] descriptors = new String[5];
		for(int i=0; i<5; i++) {
			descriptors[i] = input.next();
		}
		
		//option for system administrator
		Boolean sysAdmin;
		System.out.println("Do you want to be the system Administrator? Enter 0 for no, 1 for yes: ");
		String sysAdminStr = input.next();
		sysAdmin = (sysAdminStr != "0");
		
		
		input.close();
		
		//pass information to the GetNumbInfo class
		GetNumbInfo.runOperation(phoneNumb, occupation, descriptors, sysAdmin);
		
		
		
	}

}

