package BackEnd;

import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class GetPossibleCombos {
	// mainInfo information;
	// int phoneNumber = information.getNumber();
	ArrayList<String> possibleCombos = new ArrayList<String>();
	String occupation;
	int phoneNumber;
	String phoneNumbString;

	GetPossibleCombos(int n, String occupation) {
		this.phoneNumber = n;
		this.occupation = occupation;
		phoneNumbString = String.valueOf(phoneNumber);
	}

	//so this below turns off the formatter, which
	//is helpful to read the code better
	// @formatter:off
	static String[][] alphabet = new String[10][4];
	{
	alphabet[0][0] = "o";   alphabet[0][1] = "_";   alphabet[0][2] = " ";    alphabet[0][3] = " ";
	alphabet[1][0] = "/";	alphabet[1][1] = ".";   alphabet[1][2] = "@";    alphabet[1][3] = " ";	
	alphabet[2][0] = "a";   alphabet[2][1] = "b";	alphabet[2][2] = "c"; 	 alphabet[2][3] = " ";
	alphabet[3][0] = "d";	alphabet[3][1] = "e";	alphabet[3][2] = "f";	 alphabet[3][3] = " ";
	alphabet[4][0] = "g";	alphabet[4][1] = "h";	alphabet[4][2] = "i";	 alphabet[4][3] = " ";
	alphabet[5][0] = "j";	alphabet[5][1] = "k";	alphabet[5][2] = "l";	 alphabet[5][3] = " ";
	alphabet[6][0] = "m";	alphabet[6][1] = "n";	alphabet[6][2] = "o";	 alphabet[6][3] = " ";
	alphabet[7][0] = "p";	alphabet[7][1] = "q";	alphabet[7][2] = "r"; 	 alphabet[7][3] = "s";
	alphabet[8][0] = "t";	alphabet[8][1] = "u";	alphabet[8][2] = "v";	 alphabet[8][3] = "8";
	alphabet[9][0] = "w";	alphabet[9][1] = "x";	alphabet[9][2] = "y";    alphabet[9][3] = "z";
	}
	// @formatter:on
	//this turns the formatter back on, so
	//everything can look a bit nicer
	

	public ArrayList<String> getCombos(int d) {
		int[] phoneArray = getArrayOfNumber(d);
		String holdWordForward = " ";
		//System.out.println(phoneNumbString);

		// first number of phone
		for (int i = 0; i < 4; i++) {
			holdWordForward = alphabet[phoneArray[0]][i];

			// second number of phone
			for (int j = 0; j < 4; j++) {
				holdWordForward += alphabet[phoneArray[1]][j];

				// third number of phone
				for (int k = 0; k < 4; k++) {
					holdWordForward += alphabet[phoneArray[2]][k];

					// fourth number of phone
					for (int l = 0; l < 4; l++) {
						holdWordForward += alphabet[phoneArray[3]][l];
						

						// fifth number of phone
						for (int m = 0; m < 4; m++) {
							holdWordForward += alphabet[phoneArray[4]][m];
							

							// sixth number of phone
							for (int n = 0; n < 4; n++) {
								holdWordForward += alphabet[phoneArray[5]][n];
								

								// seventh number of phone
								for (int o = 0; o < 4; o++) {
									holdWordForward += alphabet[phoneArray[6]][o];
									findWord(holdWordForward);
									holdWordForward = holdWordForward.substring(0, (holdWordForward.length() - 1));
								}
								holdWordForward = holdWordForward.substring(0, (holdWordForward.length() - 1));
							}
							holdWordForward = holdWordForward.substring(0, (holdWordForward.length() - 1));
						}
						holdWordForward = holdWordForward.substring(0, (holdWordForward.length() - 1));
					}
					holdWordForward = holdWordForward.substring(0, (holdWordForward.length() - 1));
				}
				holdWordForward = holdWordForward.substring(0, (holdWordForward.length() - 1));
			}
			holdWordForward = holdWordForward.substring(0, (holdWordForward.length() - 1));
		}

		return possibleCombos;
	}

	public int[] getArrayOfNumber(int n) {
		int[] phone = new int[7];
		for (int i = 0; i < 7; i++) {
			int k = n % 10;
			n /= 10;
			//helpful code to check if running correctly
			// System.out.println(n);
			phone[6 - i] = k;
			//helpful code to check if running correctly
			// System.out.println(phone[6-i]);
		}
		return phone;

	}

	public void findWord(String word) {
		try {
			File dictionary = new File("7andlowerwords.txt");
			Scanner readDictionary = new Scanner(dictionary);

			String nextLn;
			String holdTheWord;
			while (readDictionary.hasNextLine()) {
				nextLn = readDictionary.nextLine();
				if (word.contains(nextLn)) {
					int n = word.indexOf(nextLn);
					holdTheWord = getFullComboWord(nextLn, n);
					
					if (!possibleCombos.contains(holdTheWord)) {
						possibleCombos.add(holdTheWord);
						//helpful code to check if running correctly
						System.out.println("Found one");
					}
				}
			}
			readDictionary.close();

		} catch (FileNotFoundException e) {
			System.out.println("Make sure the dictionary text file is in the right location");
			e.printStackTrace();
		}
		try {
			File occupationDictionary = new File("Occupation/" + occupation + ".txt");
			Scanner readOccDictionary = new Scanner(occupationDictionary);

			String nextLn;
			String holdTheWord;
			while (readOccDictionary.hasNextLine()) {
				nextLn = readOccDictionary.nextLine();
				if (word.contains(nextLn)) {
					int n = word.indexOf(nextLn);
					holdTheWord = getFullComboWord(nextLn, n);
					if (!possibleCombos.contains(holdTheWord)) {
						possibleCombos.add(holdTheWord);
						//helpful code to check if running correctly
						System.out.println("Found one Occ");
					}
				}
			}
			readOccDictionary.close();

		} catch (FileNotFoundException e) {
			// System.out.println("Make sure the dictionary text file is in the right
			// location");
			// e.printStackTrace();
		}

	}

	String getFullComboWord(String word, int location) {
		String combo = " ";
		int length = word.length();
		if (length != 7) {
			if ((location + (length - 1) != 6) && (location != 0)) {
				combo = phoneNumbString.substring(0, location) + word + phoneNumbString.substring(location + length, 7);
			} else if (location + length == 7) {
				combo = phoneNumbString.substring(0, location) + word;
			} else if (location == 0) {
				combo = word + phoneNumbString.substring(length, 7);
			}
		} else {
			combo = word;
		}
		return combo;
	}

}
