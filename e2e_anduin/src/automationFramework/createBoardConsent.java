package automationFramework;

import java.awt.AWTException;

import pageObjects.Board_Consent;
import pageObjects.Login;
import pageObjects.Toolkit;
import utilities.commonFunctions;
import utilities.constant;
import utilities.setup;

public class createBoardConsent {

	public static void Execute() throws AWTException {
		// TODO Auto-generated method stub
		setup.setUp();
		
		Login.signIn(constant.globalVariables.testCompanyuser, constant.globalVariables.testPass);
		Toolkit.boardConsent().click();
		String requestName = "Employee Option Pool" + commonFunctions.getCurrentTimeStamp();
		Board_Consent.createRquest(requestName, "Other", 5);

	}

}
