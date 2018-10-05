package pageObjects;

import org.openqa.selenium.WebElement;

import utilities.commonFunctions;
import utilities.constant;

public class Toolkit {
	public static WebElement termsheetSimulatorWidget() {
		return commonFunctions.getElement(constant.How.XPATH, "//h3[text()='Term sheet simulator']");
	}
	
	public static WebElement financingTutorial() {
		return commonFunctions.getElement(constant.How.XPATH, "//h3[text()='Financing tutorial']");
	}
	
	public static WebElement eSignature() {
		return commonFunctions.getElement(constant.How.XPATH, "//h3[text()='E-signature']");
	}
	
	public static WebElement dilutionCalculator() {
		return commonFunctions.getElement(constant.How.XPATH, "//h3[text()='Dilution calculator']");
	}
	
	public static WebElement ventureDeals() {
		return commonFunctions.getElement(constant.How.XPATH, "//h3[text()='Venture deals']");
	}
	
	public static WebElement boardConsent() {
		return commonFunctions.getElement(constant.How.XPATH, "//h3[text()='Board consents']");
	}
	
	public static WebElement tsSimulatorTitle() {
		return commonFunctions.getElement(constant.How.XPATH, "//h3[text()='Term Sheet Simulator']");
	}
	
	public static WebElement investorNameInput() {
		return commonFunctions.getElement(constant.How.XPATH, "//input[@placeholder='Enter investor name']");
	}
	
	public static WebElement toolkitBreadcrumb() {
		return commonFunctions.getElement(constant.How.LINKTEXT, "Toolkit");
	}
	
	public static WebElement financingTitleCard() {
		return commonFunctions.getElement(constant.How.LINKTEXT, "Why would I use debt financing?");
	}
	

}
