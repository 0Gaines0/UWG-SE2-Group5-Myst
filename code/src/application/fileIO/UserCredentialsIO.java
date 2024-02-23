package application.fileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import application.model.profile.credentials.Credential;

/**
 * The Class UserCredentialsIO.
 * @author Jeffrey Gaines
 * @version Sprint 1
 */
public class UserCredentialsIO {

	private static final String FILENAME = "NotUserCredentials.txt";

	
	/**
	 * Write credentials to XML file.
	 *
	 * @param credentials the credentials
	 * @throws FileNotFoundException the file not found exception
	 */
	public static void writeCredentialsToXMLFile(List<Credential> credentials) throws FileNotFoundException {
		try (var writer = new BufferedWriter(new FileWriter(FILENAME))) {
			for (var credential : credentials) {
				writer.write(credential.getUsername() + "," + credential.getPassword());
				writer.newLine();
			}
		} catch (IOException e) {
			throw new FileNotFoundException(e.getMessage());
		}
	}

	/**
	 * Read credentials from file.
	 *
	 * @return the list
	 * @throws FileNotFoundException the file not found exception
	 */
	public static List<Credential> readCredentialsFromFile() throws FileNotFoundException {
		var credentialList = new ArrayList<Credential>();
		
		var usernameIdx = 0;
		var passwordIdx = 1;
		try {
			var fileStream = new FileInputStream(FILENAME);
			var input = new DataInputStream(fileStream);
			var bufferReader = new BufferedReader(new InputStreamReader(input));

			String currentLine;
			while ((currentLine = bufferReader.readLine()) != null) {
				var credentialArray = currentLine.split(",");
				var credential = new Credential(credentialArray[usernameIdx], credentialArray[passwordIdx]);
				credentialList.add(credential);
			}
			fileStream.close();
			input.close();
			bufferReader.close();

		} catch (FileNotFoundException e) {
			throw new FileNotFoundException(e.getMessage());
		} catch (IOException e) {
			throw new FileNotFoundException(e.getMessage());

		}
		return credentialList;
	}
}
