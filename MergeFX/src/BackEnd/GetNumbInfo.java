package BackEnd;

//import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class GetNumbInfo {

	public static String runOperation(long num, String occupation, String[] descriptors, Boolean sysAdmin) throws Exception {
		
		//helpful code to check if running correctly
		// System.out.println("RunOperation Active.");
		
		//get all the possible combos using the number and occupation
		//use constructor initially
		GetPossibleCombos k = new GetPossibleCombos((int) num, occupation);
		//then add them to the ArrayList
		ArrayList<String> theCombos = k.getCombos((int) num);

		//for system administrator to see all possible combinations
		if (sysAdmin) {
			String docName = "System Administrator\\combos_for_" + num + ".txt";
			try (FileWriter combos = new FileWriter(docName);
					BufferedWriter b = new BufferedWriter(combos);
					PrintWriter p = new PrintWriter(b);) {

				for (String str : theCombos) {
					combos.write(str + System.lineSeparator());
				}
				combos.close();

			} catch (IOException b) {
				b.printStackTrace();
			}

			System.out.println("The results for " + num + " can be found in the project folder.");
		}
		
		//recommend the most related combination
		String recommend = " ";
		//int b = 0;
		label:
		for(int w=0; w < theCombos.size();w++) {
			String checkThis = theCombos.get(w);
			for(int y=0; y<5; y++) {
				String description = descriptors[y];
				if((description != null)&&(checkThis.contains(description))&&(descriptors[y] != "0")) {
					//System.out.println("The combination " + checkThis + " is recommended.");
					recommend = "The combination " + checkThis + " is recommended. \n";
					break label;
					//this breaks to the outermost loop
				}/*else {
					if(b == 0) {
						recommend = "The combination " + theCombos.get(b) + " is recommended.";
						b++;
					}
				}*/
			}
		}
		if(recommend != " ") {
			System.out.println(recommend);
			//return recommend;
		}else {
			recommend = "The combination " + theCombos.get(theCombos.size()/2) + " is recommended \n";
			//return recommend;
		}
		return recommend;
		

	}

}
