package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import getLicense.CheckLicenseId;
import getLicense.VrConnectException;

class CheckLicenseIdTest {

	@Test
	void testCheckLicenseId() {

		Path checkfile= Path.of("C:\\poltech_6\\requestpayload_forCheck.properties");
		CheckLicenseId cli = new CheckLicenseId();

		try {
			assertTrue(cli.checkLicenseId(checkfile));
		} catch (IOException | VrConnectException e) {
			e.printStackTrace();
		}
	}

}
