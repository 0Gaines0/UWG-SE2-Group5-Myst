package application.fileIO;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import application.model.profile.UserProfile;

public class UserCredentialsIO {
	
	private static final String FILENAME = "NotUserCredentials.txt";
	
	
	/**
	 * Write credentials to XML file.
	 *
	 * @param user the user
	 * @throws FileNotFoundException the file not found exception
	 */
	public static void writeCredentialsToXMLFile(UserProfile user) throws FileNotFoundException {
		var encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(FILENAME)));
		encoder.writeObject(user);
		encoder.close();
	}
	
	/**
	 * Read credentials from file.
	 *
	 * @return the string[]
	 */
	public static String[] readCredentialsFromFile() {
		var userCredentials = new String[2];
		
		return userCredentials;
	}
}
