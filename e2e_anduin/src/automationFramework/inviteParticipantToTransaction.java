package automationFramework;

import static org.testng.Assert.assertEquals;

import java.rmi.UnknownHostException;
import java.util.ArrayList;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import email.EmailMessage;
import email.EmailUtil;
import pageObjects.Deal_Participants;
import pageObjects.Deal_Snapshot;
import pageObjects.Deals_Dashboard;
import pageObjects.Entity_Setup;
import pageObjects.Login;
import utilities.Log;
import utilities.commonFunctions;
import utilities.constant;
import utilities.constant.globalVariables;


public class inviteParticipantToTransaction extends testBase {

	public static void mainInviteParticipant(String yourSide) {
		
		
		if(yourSide == "Investor") {
			Log.startTestCase(yourSide + " invites Company side test");
			Login.signIn(constant.globalVariables.testUser, constant.globalVariables.testPass);
			Deals_Dashboard.getTrxnLinkInvestorOrg(constant.globalVariables.companyTrxnLinkName).click();
		}else {
			Log.startTestCase(yourSide + " invites Investor side test");
			Login.signIn(constant.globalVariables.testCompanyuser, constant.globalVariables.testPass);
			commonFunctions.getElement(constant.How.LINKTEXT, "Deals").click();
			Deals_Dashboard.getTrxnLinkCompanyOrg(constant.globalVariables.investorTrxnLinkName).click();
		}
		
		commonFunctions.waitUntilElementVisible(Deal_Snapshot.participantTitle());
		
		//If deal observer user
//		WebElement manageLink = commonFunctions.getElement(constant.How.XPATH, "//a[text()='Manage']");
//		manageLink.click();
		
		//If deal team user
		
		Deal_Snapshot.btGoToParticipant().click();
		Assert.assertEquals(commonFunctions.getElement(constant.How.XPATH, "//h2[text()='Participants']").isDisplayed(), true);
		
		if(yourSide == "Investor") {
			Deal_Participants.inviteParticipant(yourSide,
												"Investor", 
												"Investor's Law Firm", 
												constant.globalVariables.testDefaultInvestorLFirm, 
												"qaanduin+investorlaw@gmail.com   ", 
												"QA Investor Counsel", 
												"Platform Users");
			
			Deal_Participants.inviteParticipant(yourSide,
												"Company", 
												"Company", 
												constant.globalVariables.testCompanyName, 
												"  qaanduin1+company@gmail.com  ", 
												"QA Company", 
												"Platform Users");
			
			Deal_Participants.inviteParticipant(yourSide,
												"Company",
												"Company's Law Firm", 
												constant.globalVariables.testDefaultCompanyLFirmOposite, 
												"qaanduin1+companylaw@gmail.com", 
												"QA Company Counsel", 
												"Platform Users");
			
			Log.info("###########Finished invitation#########");
			
			String anduinLiteAddress = commonFunctions.getAnduinLiteDealAddress(commonFunctions.getTrxnID());
//			commonFunctions.logOut(true);
//			lfAcceptInvitationFromEmail(anduinLiteAddress);
//			verifyWelcomeModal(yourSide, constant.globalVariables.testLFirmShortName, "Deal Team Member", true);
//			createOrgFromWelcomeModal("Law Firm", constant.globalVariables.testLFirmName, constant.globalVariables.testLFirmShortName, "");
			
			commonFunctions.logOut(true);
			companyAcceptInvitationFromEmail(anduinLiteAddress);
			verifyWelcomeModal(yourSide, constant.globalVariables.testCompanyShortName, "Deal Team Member", false);
			createOrgFromWelcomeModal("Company", constant.globalVariables.testCompanyName, constant.globalVariables.testCompanyShortName, "");
			
			
			
			Log.endTestCase(yourSide + " invites Company side test");
			
		} else {
			
			Deal_Participants.inviteParticipant(yourSide,
												"Company", 
												"Company's Law Firm", 
												constant.globalVariables.testDefaultCompanyLFirm, 
												"qaanduin1+companylaw@gmail.com", 
												"QA Company Counsel", 
												"Platform Users");
			
			Deal_Participants.inviteParticipant(yourSide,
												"Investor", 
												"Lead Investor", 
												constant.globalVariables.testLegalName, 
												"qaanduin+leadinvestor@gmail.com", 
												"QA Investor", 
												"Platform Users");
			
			Deal_Participants.inviteParticipant(yourSide,
												"Investor", 
												"Investor's Law Firm", 
												constant.globalVariables.testDefaultInvestorLFirmOposite, 
												"qaanduin+investorlaw@gmail.com", 
												"QA Investor Counsel", 
												"Platform Users");
			Log.endTestCase(yourSide + " invites Investor side test");
		}
			
	}
	
	public static void lfAcceptInvitationFromEmail(String anduinLiteAddress) {
		String lfUser = globalVariables.testLFirmUser;
		List<String> recipients = new ArrayList<>();
		recipients.add(lfUser);
		String invitationLink = commonFunctions.getInvitationLinkInEmail(anduinLiteAddress, recipients);
		Log.info(invitationLink);
		commonFunctions.gotoUrl(invitationLink);
		Login.signIn(lfUser, globalVariables.testPass);
	}
	
	public static void companyAcceptInvitationFromEmail(String anduinLiteAddress) {
		String companyUser = globalVariables.testCompanyuser;
		List<String> recipients = new ArrayList<>();
		recipients.add(companyUser);
		String invitationLink = commonFunctions.getInvitationLinkInEmail(anduinLiteAddress, recipients);
		Log.info(invitationLink);
		commonFunctions.gotoUrl(invitationLink);
		Login.signIn(companyUser, globalVariables.testPass);
	}
	
	public static void verifyWelcomeModal(String yourSide, String defaultOrg, String verifiedRole, boolean isYourSideJoined) {
		
		String trxnName = getTrxNameInModal(yourSide, isYourSideJoined);
		assertEquals(Deal_Snapshot.modalWelcomeCreateOrg(trxnName).isDisplayed(), true);
		assertEquals(Deal_Snapshot.joiningRoleText(verifiedRole).isDisplayed(), true);
		assertEquals(Deal_Snapshot.btCreateNewOrg().isDisplayed(), true);
		assertEquals(Deal_Snapshot.btGoToDeal().isDisplayed(), true);
		assertEquals(Deal_Snapshot.defaultOrgValueDropdown(defaultOrg).isDisplayed(), true);
	}
	
	public static void createOrgFromWelcomeModal(String orgType, String legalName, String shortName, String domain) {
		Deal_Snapshot.btCreateNewOrg().click();
		Entity_Setup.setupOrg(orgType, legalName, shortName, domain, true);
		Deal_Snapshot.btGoToDeal().click();
	}
	
	public static String getTrxNameInModal(String yourSide, boolean isYourSideJoined) {
		if(isYourSideJoined) {
			return yourSide=="Investor"? constant.globalVariables.welcomeDealMessageInvestorJoined :
										 constant.globalVariables.welcomeDealMessageCompanyJoined;
		}else {
//			return yourSide=="Investor" ? constant.globalVariables.investorTrxnLinkName :
//				 						  constant.globalVariables.companyTrxnLinkName;
			return constant.globalVariables.welcomeDealMessageInvestorJoined;
		}
		
	}
	
	
	@Test(dataProvider="invite_participant_deal")
	public void testInviteParticipants(String yourSide) throws UnknownHostException{
		mainInviteParticipant(yourSide);
	}
	
	
	@DataProvider(name = "invite_participant_deal")
	public Object[][] yourSide() {
	 return new Object[][] {
	   { "Investor"},
//	   { "Company"},
//	   { "Law Firm"}
	 };
	}

}
