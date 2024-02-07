package application.test.fileIO;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import application.fileIO.UserCredentialsIO;
import application.model.profile.credentials.Credential;

public class TestUserCredentialsIO {

	
	/**
	 * Test credential write.
	 */
	@Test
	public void testCredentialWrite() {
		var credential = new Credential("username", "password");
		
		try {
			UserCredentialsIO.writeCredentialsToXMLFile(credential);
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
			assertEquals(1, credentialList.size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
}
