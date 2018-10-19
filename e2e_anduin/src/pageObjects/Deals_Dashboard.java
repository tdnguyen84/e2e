package pageObjects;

import org.openqa.selenium.WebElement;

import utilities.commonFunctions;
import utilities.constant;


public class Deals_Dashboard {
	public static WebElement activeTab() {
		return commonFunctions.getElement("xpath", "//li[@class='tab-item cursor-pointer  -active']");
	}
	
	public static String getTextActiveTab() {
		WebElement activeTab = commonFunctions.getElement("xpath", "//li[@class='tab-item cursor-pointer  -active']");
		return activeTab.getText();
	}
	
	public static WebElement createDealButton() {
		return commonFunctions.getElement("xpath", "//button/span[@class='ml2'][text()='Start a new deal']");
	}
	
	public static WebElement btStartNewDeal() {
		return commonFunctions.getElement(constant.How.XPATH, "//button[text()='Start a new deal']");
	}
	
	public static WebElement getTrxnLinkInvestorOrg(String trxnName) {
		
		return commonFunctions.getElement(constant.How.XPATH, "//div[h4=\""+trxnName+"\"]/div/a/div[text()='Access deal ']");
	}
	
	public static WebElement getTrxnLinkCompanyOrg(String trxnName) {
		
		return commonFunctions.getElement(constant.How.XPATH, "//span/button[text()=\""+trxnName+"\"]");
	}
	
	public static WebElement linkSettings() {
		
		return commonFunctions.getElement(constant.How.LINKTEXT, "Settings");
	}
	
	public static WebElement linkMyAccount() {
			
		return commonFunctions.getElement(constant.How.LINKTEXT, "My Account");
	}
	
	public static WebElement linkEditSignature() {
		
		return commonFunctions.getElement(constant.How.LINKTEXT, "Edit signature");
	}
	
	
	

}
