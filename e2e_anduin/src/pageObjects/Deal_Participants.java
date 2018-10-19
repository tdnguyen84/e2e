package pageObjects;



import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import utilities.commonFunctions;
import utilities.constant;

public class Deal_Participants {
	public static WebElement btInviteInvestorSide_SameSide() {
		return commonFunctions.getElement(constant.How.XPATH, "//div[@class='mr3'][1]/button/div[text()='Invite Investor Side']");
	}
	
	public static WebElement btInviteInvestorSide_OposideSide() {
		return commonFunctions.getElement(constant.How.XPATH, "//div[@class='mr3'][2]/button/div[text()='Invite Investor Side']");
	}
	
	public static WebElement btInviteCompanySide_SameSide() {
		return commonFunctions.getElement(constant.How.XPATH, "//div[@class='mr3'][1]/button/div[text()='Invite Company Side']");
	}
	
	public static WebElement btInviteCompanySide_OposideSide() {
		return commonFunctions.getElement(constant.How.XPATH, "//div[@class='mr3'][2]/button/div[text()='Invite Company Side']");
	}
	
	public static WebElement btInviteCoinvestorSide() {
		return commonFunctions.getElement(constant.How.XPATH, "//div[@class='mr3'][3]/button/div[text()='Invite Co-Investors']");
	}
	
	public static WebElement inviteInvestorSideModalTitle() {
		return commonFunctions.getElement(constant.How.XPATH, "//h3/div[text()='Add new participants for Investor Side']");
	}
	
	public static WebElement inviteCompanySideModalTitle() {
		return commonFunctions.getElement(constant.How.XPATH, "//h3/div[text()='Add new participants for Company Side']");
	}
	
	public static WebElement inviteCoinvestorSideModalTitle() {
		return commonFunctions.getElement(constant.How.XPATH, "//h3/div[text()='Add new participants for Co-Investors']");
	}
	
	public static WebElement btSelectLeadInvestorSide() {
		return commonFunctions.getElement(constant.How.XPATH, "(//button[@type='button'][text()='Select'])[1]");
	}
	
	public static WebElement btSelectInvestorLFirmSide() {
		return commonFunctions.getElement(constant.How.XPATH, "(//button[@type='button'][text()='Select'])[2]");
	}
	
	public static WebElement btSelectCompanySide() {
		return commonFunctions.getElement(constant.How.XPATH, "(//button[@type='button'][text()='Select'])[1]");
	}
	
	public static WebElement btSelectCompanyLFirmSide() {
		return commonFunctions.getElement(constant.How.XPATH, "(//button[@type='button'][text()='Select'])[2]");
	}
	
	public static WebElement btSelectBoarMember() {
		return commonFunctions.getElement(constant.How.XPATH, "(//button[@type='button'][text()='Select'])[3]");
	}
	
	public static WebElement btSelectShareHolder() {
		return commonFunctions.getElement(constant.How.XPATH, "(//button[@type='button'][text()='Select'])[4]");
	}
	
	public static WebElement addNewParticipantTitle(String partyName) {
		String addParticipantTitle = "Add new participants for " + partyName;
		return commonFunctions.getElement(constant.How.XPATH, "//h3/div[text()=\""+addParticipantTitle+"\"]");
	}
	
	public static WebElement platformUserField() {
		return commonFunctions.getElement(constant.How.XPATH, "//label/span[@class='ml2']/strong[text()='Platform Users']");
	}
	
	public static WebElement emailFollowerField() {
		return commonFunctions.getElement(constant.How.XPATH, "//label/span[@class='ml2']/strong[text()='Email Followers']");
	}
	
	public static WebElement inputEmail() {
		return commonFunctions.getElement(constant.How.CSSSELECTOR, "input[placeholder='Add more people...']");
	}
	
	public static WebElement inputMessage() {
		return commonFunctions.getElement(constant.How.CSSSELECTOR, "textarea[placeholder='Message (optional)']");
	}
	
	public static WebElement checkboxInviteMyOrg() {
		WebElement inviteMyOrgText = commonFunctions.getElement(constant.How.XPATH, "//label/span[@class='ml2']/span[text()='Also invite new members to my organization: ']");
		WebElement parentInviteMyOrg = inviteMyOrgText.findElement(By.xpath(".."));
		return parentInviteMyOrg.findElement(By.xpath(".//input[@type='checkbox']"));
	}
	
	public static WebElement btAddParticipant() {
		return commonFunctions.getElement(constant.How.XPATH, "//button[@type='button'][text()='Add participant']");
	}
	
	public static WebElement btBack() {
		return commonFunctions.getElement(constant.How.XPATH, "//button[@type='button'][text()='Back']");
	}
	
	public static void verifyPendingUser(String verifiedParty, String verifiedEmail) {
		WebElement eleVerifiedEmail = commonFunctions.getElement(constant.How.XPATH, "//span[@class='truncate fw4 c--gray-7']/span[text()='"+verifiedEmail+"']");
//		WebElement parentPendingEle = eleVerifiedEmail.findElement(By.xpath(".."));
//		WebElement pendingLabelEle = parentPendingEle.findElement(By.xpath("//div[2]/div[text()='Invitation Pending']"));
		Assert.assertEquals(eleVerifiedEmail.isDisplayed(), true);
	}
	
	public static void inviteParticipant(String sideIsInviting,
										 String sideName, 
										 String partyName, 
										 String legalName, 
										 String email, 
										 String fullName, 
										 String userType) {
		
		
		if(sideIsInviting == "Investor") {
			if(sideName == "Investor" || sideName == "Investor Law Firm") {
				commonFunctions.waitUntilElementVisible(btInviteInvestorSide_SameSide());
				btInviteInvestorSide_SameSide().click();
				commonFunctions.waitUntilElementVisible(inviteInvestorSideModalTitle());
			} else {
				commonFunctions.waitUntilElementVisible(btInviteCompanySide_OposideSide());
				btInviteCompanySide_OposideSide().click();
				commonFunctions.waitUntilElementVisible(inviteCompanySideModalTitle());
			}
		} else {
			if(sideName == "Company" || sideName == "Company Law Firm") {
				commonFunctions.waitUntilElementVisible(btInviteCompanySide_SameSide());
				btInviteCompanySide_SameSide().click();
				commonFunctions.waitUntilElementVisible(inviteCompanySideModalTitle());
			} else {
				commonFunctions.waitUntilElementVisible(btInviteInvestorSide_OposideSide());
				btInviteInvestorSide_OposideSide().click();
				commonFunctions.waitUntilElementVisible(inviteInvestorSideModalTitle());
			}
		}
		
		selectPartyToInvite(partyName);
		commonFunctions.waitUntilElementVisible(addNewParticipantTitle(legalName));
		
		if(userType == "Platform Users") {
			platformUserField().click();
		} else {
			emailFollowerField().click();
		}
		
		inputEmail().sendKeys(email);
		inputMessage().sendKeys("Let's join with us");
		btAddParticipant().click();
		assertEquals(commonFunctions.getElement(constant.How.XPATH, "//h5[text()='"+fullName+"']").isDisplayed(), true);
		verifyPendingUser(partyName, email.trim());
		
	}
	
	public static void selectPartyToInvite(String partyName) {
		switch(partyName) {
		case "Lead Investor":
			btSelectLeadInvestorSide().click();
			break;
		case "Investor's Law Firm":
			btSelectInvestorLFirmSide().click();
			break;
		case "Company":
			btSelectCompanySide().click();
			break;
		case "Company's Law Firm":
			btSelectCompanyLFirmSide().click();
			break;
		case "Board members":
			btSelectBoarMember().click();
			break;
		case "Shareholders":
			btSelectShareHolder().click();
		}
	}

}
