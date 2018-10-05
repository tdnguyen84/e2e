package automationFramework;

import static org.testng.Assert.assertEquals;


import org.testng.Assert;

import pageObjects.Board_Consent;
import pageObjects.Deals_Dashboard;
import pageObjects.Login;
import pageObjects.Toolkit;
import pageObjects.Toolkit_DilutionCalculator;
import pageObjects.Toolkit_Esignature;
import utilities.commonFunctions;
import utilities.constant;
import utilities.setup;

public class startupPortalSmokeTest {
	public static void Execute(Boolean isNewSignUpUser) {
		setup.setUp();
		Login.signIn(constant.globalVariables.testCompanyuser, constant.globalVariables.testPass);
		
		Toolkit.termsheetSimulatorWidget().click();
		assertEquals(Toolkit.tsSimulatorTitle().isDisplayed(), true);
		
		commonFunctions.waitUntilElementClickAble(Toolkit.toolkitBreadcrumb());
		Toolkit.toolkitBreadcrumb().click();
		Toolkit.financingTutorial().click();
		assertEquals(Toolkit.financingTitleCard().isDisplayed(), true);
		
		commonFunctions.waitUntilElementClickAble(Toolkit.toolkitBreadcrumb());
		Toolkit.toolkitBreadcrumb().click();
		Toolkit.eSignature().click();
		Assert.assertEquals(Toolkit_Esignature.btSign().isEnabled(), true);
		Assert.assertEquals(Toolkit_Esignature.btRequest().isEnabled(), true);
		
		commonFunctions.waitUntilElementClickAble(Toolkit.toolkitBreadcrumb());
		Toolkit.toolkitBreadcrumb().click();
		Toolkit.ventureDeals().click();
		assertEquals(Deals_Dashboard.createDealButton().isDisplayed(), true);
		
		commonFunctions.waitUntilElementClickAble(Toolkit.toolkitBreadcrumb());
		Toolkit.toolkitBreadcrumb().click();
		Toolkit.dilutionCalculator().click();
		if(isNewSignUpUser) {
			commonFunctions.waitUntilElementVisible(Toolkit_DilutionCalculator.textWelcomeDilution());
			Toolkit_DilutionCalculator.btStartCalculator().click();
			assertEquals(Toolkit_DilutionCalculator.dilutionInstructionText().isDisplayed(), true);
		}
		commonFunctions.waitUntilElementClickAble(Toolkit.toolkitBreadcrumb());
		Toolkit.toolkitBreadcrumb().click();
		Toolkit.boardConsent().click();
		
		if(isNewSignUpUser) {
			assertEquals(Board_Consent.helloModalTitle().isDisplayed(), true);
			assertEquals(Board_Consent.btInviteMember().isEnabled(), true);
			Board_Consent.btInviteMember().click();
			assertEquals(Board_Consent.modalInivteMember().isDisplayed(), true);
			Board_Consent.btCancelInviteModal().click();
		}
		assertEquals(Board_Consent.linkCreateNewRequest().isDisplayed(), true);
		setup.tearDown();
		
	}

}
