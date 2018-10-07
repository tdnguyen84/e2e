package automationFramework;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;



import pageObjects.Login;
import pageObjects.Board_Consent;
import pageObjects.Board_Consent.BMView;
import utilities.commonFunctions;
import utilities.constant;

public class approveBoardConsent {
	public static void Execute(boolean isFirstTime) throws AWTException {
		Login.signIn(constant.globalVariables.testBMuser, constant.globalVariables.testPass);
		BMView.btView(constant.globalVariables.consentRequestName);
		if(isFirstTime) {
			commonFunctions.waitUntilElementClickAble(Board_Consent.btInviteMember());
			Board_Consent.btInviteMember().click();
			assertEquals(BMView.titleModalInviteMember().isDisplayed(), true);
			BMView.btCancelModalInvite().click();
		}	
		assertEquals(commonFunctions.getElement(constant.How.XPATH, "//h3[text()='"+constant.globalVariables.consentRequestName+"']").isDisplayed(), true);
		assertEquals(Board_Consent.attachedDocName(constant.globalVariables.uploadConsentFileName).isDisplayed(), true);
		assertEquals(Board_Consent.labelSignatureRequired().isDisplayed(), true);
		assertEquals(BMView.generalMsgText().isDisplayed(), true);
		assertEquals(BMView.privateMsgText().isDisplayed(), true);
		assertEquals(commonFunctions.getElement(constant.How.XPATH, "//span[text()='QA BM']").isDisplayed(), true);
		assertEquals(commonFunctions.getElement(constant.How.XPATH, "//div[text()='Reviewing']").isDisplayed(), true);
		
		BMView.btInviteTeamMember().click();
		assertEquals(BMView.titleModalInviteMember().isDisplayed(), true);
		BMView.inputFirstName().sendKeys("QA");
		BMView.inputLastName().sendKeys("Team Member");
		BMView.inputEmail().sendKeys("qaanduin+teammember@gmail.com");
		
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
		commonFunctions.getElement(constant.How.LINKTEXT, "Type signature']").click();
		commonFunctions.getElement(constant.How.XPATH, "(//div/span[text()='QA BM'][2])").click();
		commonFunctions.getElement(constant.How.XPATH, "//button[text()='Save changes']").click();
		commonFunctions.waitUntilElementClickAble(BMView.btFinishSigning());
		BMView.btFinishSigning().click();
		commonFunctions.waitUntilElementClickAble(BMView.btFinishSigningSharing());
		BMView.btFinishSigningSharing().click();
		assertEquals(commonFunctions.getElement(constant.How.XPATH, "//h3/div[text()='Finish signing and share?']").isDisplayed(), true);
		commonFunctions.getElement(constant.How.XPATH, "//button[text()='Finish']").click();
		
		assertEquals(BMView.labelDocSigned().isDisplayed(), true);
		assertEquals(BMView.labelSignedStatus().isDisplayed(), true);
		assertEquals(BMView.wellMsg().isDisplayed(), true);
			
	}


}
