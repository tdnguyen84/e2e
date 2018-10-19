package automationFramework;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.Test;

import email.EmailMessage;
import email.EmailUtil;
import email.testEmail;
import pageObjects.Login;
import utilities.Log;
import utilities.commonFunctions;

public class TestSignUpFlow extends testBase {
	public static void mainSignUpFlow () {
		String testCase = "Signup Flow Test";
		Log.startTestCase(testCase);
		Login.createAccountLink().click();
		String generatedEmail = "qaanduin+" + commonFunctions.getCurrentTimeStamp() + "@gmail.com";
		Login.inputEmail().sendKeys(generatedEmail);
		Login.capchaCheckbox().click();
		Login.btCreateAccount().click();
		commonFunctions.waitUntilElementVisible(Login.txtWorkEmail(generatedEmail));
		
		String pass = "anduin1808";
		Login.txtPassWord().sendKeys(pass);
		Login.txtRetypePassword().sendKeys(pass);
		Login.txtFirstName().sendKeys("QA");
		Login.txtLastName().sendKeys("Anduin");
		Login.txtJobTitle().sendKeys("CEO");
		Login.btSignUp().click();
		assertEquals(Login.successMsg().isDisplayed(), true);
		
	}
	
	public static void getInviteLink(String anduinLiteAddress) {
		List<EmailMessage> matchedEmails = EmailUtil.waitForEmail("qaanduin@gmail.com", "anduin1808", anduinLiteAddress, "Invitation to join Test Anduin Transaction's Note from Test Vy Cap", null, null);
		Log.info(matchedEmails.get(0).toString());
	}
	
	@Test
	public static void testSignupFlow() {
		mainSignUpFlow();
	}
}
