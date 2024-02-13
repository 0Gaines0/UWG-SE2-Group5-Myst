package application.test.fileIO;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import application.fileIO.UserCredentialsIO;
import application.model.profile.credentials.Credential;

public class TestUserCredentialsIO {

	
	/**
	 * Test credential write.
	 */
	@Test
	public void testCredentialWrite() {
		var credList = new ArrayList<Credential>();
		var credential = new Credential("username", "password");
		var credential2 = new Credential("username1", "password1");
		
		credList.add(credential);
		credList.add(credential2);
		
		try {
			UserCredentialsIO.writeCredentialsToXMLFile(credList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test credential read.
	 */
	@Test
	public void testCredentialRead() {
		try {
			var credentialList = UserCredentialsIO.readCredentialsFromFile();
			assertEquals(2, credentialList.size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
}
