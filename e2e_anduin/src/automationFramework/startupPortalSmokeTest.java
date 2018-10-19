package automationFramework;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;

import java.rmi.UnknownHostException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Board_Consent;
import pageObjects.Deals_Dashboard;
import pageObjects.Login;
import pageObjects.Toolkit;
import pageObjects.Toolkit_DilutionCalculator;
import pageObjects.Toolkit_Esignature;
import utilities.Log;
import utilities.commonFunctions;
import utilities.constant;


public class startupPortalSmokeTest extends testBase {
	
	public static void portalSmokeTest(Boolean isNewSignUpUser) throws UnknownHostException{
		
		String testCaseName = "Startup Portal Smoke Test";
		Log.startTestCase(testCaseName);
		
		Login.signIn(constant.globalVariables.testCompanyuser, constant.globalVariables.testPass);
		
		Toolkit.termsheetSimulatorWidget().click();
		AssertJUnit.assertEquals(Toolkit.tsSimulatorTitle().isDisplayed(), true);
		
		commonFunctions.waitUntilElementClickAble(Toolkit.toolkitBreadcrumb());
		Toolkit.toolkitBreadcrumb().click();
		Toolkit.financingTutorial().click();
		AssertJUnit.assertEquals(Toolkit.financingTitleCard().isDisplayed(), true);
		
		commonFunctions.waitUntilElementClickAble(Toolkit.toolkitBreadcrumb());
		Toolkit.toolkitBreadcrumb().click();
		Toolkit.eSignature().click();
		AssertJUnit.assertEquals(Toolkit_Esignature.btSign().isEnabled(), true);
		AssertJUnit.assertEquals(Toolkit_Esignature.btRequest().isEnabled(), true);
		
		commonFunctions.waitUntilElementClickAble(Toolkit.toolkitBreadcrumb());
		Toolkit.toolkitBreadcrumb().click();
		Toolkit.ventureDeals().click();
		AssertJUnit.assertEquals(Deals_Dashboard.createDealButton().isDisplayed(), true);
		
		commonFunctions.waitUntilElementClickAble(Toolkit.toolkitBreadcrumb());
		Toolkit.toolkitBreadcrumb().click();
		Toolkit.dilutionCalculator().click();
		if(isNewSignUpUser) {
			commonFunctions.waitUntilElementVisible(Toolkit_DilutionCalculator.textWelcomeDilution());
			Toolkit_DilutionCalculator.btStartCalculator().click();
			AssertJUnit.assertEquals(Toolkit_DilutionCalculator.dilutionInstructionText().isDisplayed(), true);
		}
		commonFunctions.waitUntilElementClickAble(Toolkit.toolkitBreadcrumb());
		Toolkit.toolkitBreadcrumb().click();
		Toolkit.boardConsent().click();
		
		if(isNewSignUpUser) {
			AssertJUnit.assertEquals(Board_Consent.helloModalTitle().isDisplayed(), true);
			AssertJUnit.assertEquals(Board_Consent.btInviteMember().isEnabled(), true);
			Board_Consent.btInviteMember().click();
			AssertJUnit.assertEquals(Board_Consent.modalInivteMember().isDisplayed(), true);
			Board_Consent.btCancelInviteModal().click();
		}
		AssertJUnit.assertEquals(Board_Consent.linkCreateNewRequest().isDisplayed(), true);
		Log.endTestCase(testCaseName);
	}
	
	@Test
	public void exectPortalSmokeTest() throws UnknownHostException{
		portalSmokeTest(false);
	}
	

}
