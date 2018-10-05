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
	
	public static WebElement getTrxnLink(String trxnName) {
		
		return commonFunctions.getElement(constant.How.XPATH, "//span[@class='c--primary-5 underline-hover cursor-pointer'][text()=\""+trxnName+"\"]");
	}
	

}
