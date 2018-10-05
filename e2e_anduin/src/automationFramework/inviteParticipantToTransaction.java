package automationFramework;

import org.testng.Assert;

import pageObjects.Deal_Participants;
import pageObjects.Deal_Snapshot;
import pageObjects.Deals_Dashboard;
import pageObjects.Login;
import utilities.commonFunctions;
import utilities.constant;
import utilities.setup;

public class inviteParticipantToTransaction {

	public static void Execute() {
		setup.setUp();
		Login.signIn(constant.globalVariables.testUser, constant.globalVariables.testPass);
		
		Deals_Dashboard.getTrxnLink(constant.globalVariables.trxnLinkName).click();
		commonFunctions.waitUntilElementVisible(Deal_Snapshot.participantTitle());
		
		//If deal observer user
//		WebElement manageLink = commonFunctions.getElement(constant.How.XPATH, "//a[text()='Manage']");
//		manageLink.click();
		
		//If deal team user
		
		Deal_Snapshot.btGoToParticipant().click();
		Assert.assertEquals(commonFunctions.getElement(constant.How.XPATH, "//h2[text()='Participants']").isDisplayed(), true);
		
		Deal_Participants.inviteParticipant("Investor", "Investor's Law Firm", constant.globalVariables.testDefaultInvestorLFirm, "qaanduin+investorlaw@gmail.com", "Platform Users","Let's join with us");
		Deal_Participants.inviteParticipant("Company", "Company", constant.globalVariables.testCompanyName, "qaanduin1+company@gmail.com", "Platform Users","Let's join with us");
		
		Deal_Participants.inviteParticipant("Company", "Company's Law Firm", constant.globalVariables.testDefaultCompanyLFirm, "qaanduin1+companylaw@gmail.com", "Platform Users","Let's join with us");
		
		setup.tearDown();
	}

}
