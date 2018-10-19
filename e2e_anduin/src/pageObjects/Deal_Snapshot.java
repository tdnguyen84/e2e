package pageObjects;

import org.openqa.selenium.WebElement;

import utilities.commonFunctions;
import utilities.constant;

public class Deal_Snapshot {
	
	public static WebElement participantTitle() {
		return commonFunctions.getElement(constant.How.XPATH, "//h4[text()='Participants']");
	}
	public static WebElement btGoToParticipant() {
		return commonFunctions.getElement(constant.How.XPATH, "//div[h4='Participants']/following-sibling::div/a");
	}
	public static WebElement modalWelcomeCreateOrg(String trxnName) {
		String welcomeMsg = "Welcome to " + trxnName;
		return commonFunctions.getElement(constant.How.XPATH, "//h3[@title=\""+welcomeMsg+"\"]");
	}
	public static WebElement joiningRoleText(String verifiedRole) {
		String message = "You're joining this deal as a ";
		return commonFunctions.getElement(constant.How.XPATH, "//div[text()=\""+message+"\"][b='"+verifiedRole+"']");
	}
	public static WebElement btCreateNewOrg() {
		return commonFunctions.getElement(constant.How.XPATH, "//button[text()='+ Create new organization']");
	}
	public static WebElement btGoToDeal() {
		return commonFunctions.getElement(constant.How.XPATH, "//button[text()='Go to deal']");
	}
	public static WebElement defaultOrgValueDropdown(String orgName) {
		return commonFunctions.getElement(constant.How.XPATH, "//button/span/span[text()='"+orgName+"']");
	}
	
}
