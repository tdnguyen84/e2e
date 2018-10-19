package automationFramework;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.rmi.UnknownHostException;

import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



import pageObjects.Board_Consent;
import pageObjects.Login;
import pageObjects.Toolkit;
import pageObjects.Board_Consent.BMView;
import utilities.Log;
import utilities.commonFunctions;
import utilities.constant;



public class boardConsentTest extends testBase {


	public static void createBoardConsent(boolean isFirstUsage) throws AWTException, InterruptedException {
		Log.startTestCase("Create Board Consent Test");
		Login.signIn(constant.globalVariables.testCompanyuser, constant.globalVariables.testPass);
		Toolkit.boardConsent().click();
		Board_Consent.createRquest(constant.globalVariables.consentRequestName, "Other", 15, isFirstUsage);
//		Board_Consent.createRquest(constant.globalVariables.consentRequestName, "Option Grants to Employees or Advisors", 15, false);
		Log.endTestCase("Create Board Consent Test");
	}
	

	public static void approveBoardConsent(boolean isFirstTime) throws AWTException {
		Log.startTestCase("Approve Board Consent Test");
		Login.signIn(constant.globalVariables.testBMuser, constant.globalVariables.testPass);
		BMView.btView(constant.globalVariables.consentRequestName);
		if(isFirstTime) {
			commonFunctions.waitUntilElementClickAble(Board_Consent.btInviteMember());
			Board_Consent.btInviteMember().click();
			assertEquals(BMView.titleModalInviteMember().isDisplayed(), true);
			BMView.btCancelModalInvite().click();
		} else {
			WebElement btReview = commonFunctions.getElement(constant.How.XPATH, "//tr[td/div='"+constant.globalVariables.consentRequestName+"']//*[a='Review']");
			btReview.click();
		}
		assertEquals(commonFunctions.getElement(constant.How.XPATH, "//h3[text()='"+constant.globalVariables.consentRequestName+"']").isDisplayed(), true);
		assertEquals(Board_Consent.BMView.attachedDocName(constant.globalVariables.uploadConsentFileName).isDisplayed(), true);
		assertEquals(Board_Consent.BMView.labelSignatureRequired().isDisplayed(), true);
//		assertEquals(BMView.generalMsgText().isDisplayed(), true);
//		assertEquals(BMView.privateMsgText().isDisplayed(), true);
		assertEquals(commonFunctions.getElement(constant.How.XPATH, "//span[text()='QA BM']").isDisplayed(), true);
		assertEquals(commonFunctions.getElement(constant.How.XPATH, "//div[text()='Reviewing']").isDisplayed(), true);
		
		if(isFirstTime) {
			BMView.btInviteTeamMember().click();
			assertEquals(BMView.titleModalInviteMember().isDisplayed(), true);
			BMView.inputFirstName().sendKeys("QA");
			BMView.inputLastName().sendKeys("Team Member");
			BMView.inputEmail().sendKeys("qaanduin+teammember@gmail.com");
			BMView.btInvite().click();
		}
		
		
		assertEquals(commonFunctions.getElement(constant.How.XPATH, "//span[@class='ml2'][text()='QA Team Member']").isDisplayed(), true);
		
		BMView.btApprove().click();
		assertEquals(BMView.toastApproved().isDisplayed(), true);
		assertEquals(BMView.labelApproved().isDisplayed(), true);
		assertEquals(BMView.btDiableFinishSharing().isDisplayed(), true);
		assertEquals(BMView.btUndoApproval().isEnabled(), true);
		assertEquals(BMView.btSendMsgTo().isEnabled(), true);
		
		BMView.btEsign().click();
		if(isFirstTime) {
			commonFunctions.getElement(constant.How.XPATH, "//button[text()='Agree']").click();
		}
		commonFunctions.getElement(constant.How.XPATH, "//div/span[text()='Signature']").click();
		commonFunctions.getElement(constant.How.XPATH, "//div[@class='doc-text']").click();
		if(isFirstTime) {
			commonFunctions.getElement(constant.How.XPATH, "//a[text()='Type signature']").click();
			commonFunctions.getElement(constant.How.XPATH, "(//div[@style='font-size: 26px; font-family: LaBelleAurore;']/span[text()='QA BM'])").click();
			commonFunctions.getElement(constant.How.XPATH, "//button[text()='Save changes']").click();
		}
		
		commonFunctions.waitUntilElementVisible(BMView.btFinishSigning());
		BMView.btFinishSigning().click();
		commonFunctions.waitUntilElementVisible(BMView.btFinishSigningSharing());
		BMView.btFinishSigningSharing().click();
		assertEquals(commonFunctions.getElement(constant.How.XPATH, "//h3/div[text()='Finish signing and share?']").isDisplayed(), true);
		commonFunctions.getElement(constant.How.XPATH, "//button[text()='Finish']").click();
		
		assertEquals(BMView.labelDocSigned().isDisplayed(), true);
		assertEquals(BMView.labelSignedStatus().isDisplayed(), true);
		assertEquals(BMView.wellMsg().isDisplayed(), true);
		Log.endTestCase("Approve Board Consent Test");
			
	}
	
	
	@Test(priority=1)
	public void testCreateBoardConsent() throws AWTException, InterruptedException {
		createBoardConsent(false);
	}
	
	@Test(priority=2)
	public void testApproveBoardConsent() throws AWTException {
		approveBoardConsent(false);
	}

}
