package application.test.local_impl.fileIO;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import application.fileIO.UserCredentialsIO;
import application.model.local_impl.profile.credentials.Credential;

/**
 * The Class TestUserCredentialsIO.
 * 
 * @author Jeffrey Gaines
 * @version Sprint 1
 */
public class TestUserCredentialsIO {

	private static final String FAKE_PASSWORD = "password";
	private static final String BASE_USERNAME = "username";

	/**
	 * Reset credential file.
	 */
	@AfterEach
	public void resetCredentialFile() {
		var credential = new Credential("changePassword", FAKE_PASSWORD);
		var credential2 = new Credential("0Gaines0", FAKE_PASSWORD);
		var credential3 = new Credential(BASE_USERNAME, FAKE_PASSWORD);
		var credList = new ArrayList<Credential>();
		credList.add(credential);
		credList.add(credential2);
		credList.add(credential3);
		
		try {
			UserCredentialsIO.writeCredentialsToXMLFile(credList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Test credential write.
	 */
	@Test
	public void testCredentialWrite() {
		var credList = new ArrayList<Credential>();
		var credential = new Credential(BASE_USERNAME, FAKE_PASSWORD);
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
			assertEquals(3, credentialList.size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
