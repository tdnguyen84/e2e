package automationFramework;

import java.awt.AWTException;

import org.testng.annotations.Test;

import pageObjects.Board_Consent;
import pageObjects.Login;
import pageObjects.Toolkit;
import utilities.commonFunctions;
import utilities.constant;


@Test
public class createBoardConsent {

	public static void Execute(boolean isFirstUsage) throws AWTException {
		
		Login.signIn(constant.globalVariables.testCompanyuser, constant.globalVariables.testPass);
		Toolkit.boardConsent().click();
		Board_Consent.createRquest(constant.globalVariables.consentRequestName, "Other", 10, isFirstUsage);
		commonFunctions.logOut(false);
		approveBoardConsent.Execute(false);

	}

}
