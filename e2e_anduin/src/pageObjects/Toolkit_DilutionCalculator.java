package pageObjects;

import org.openqa.selenium.WebElement;

import utilities.commonFunctions;
import utilities.constant;

public class Toolkit_DilutionCalculator {
	public static WebElement dilutionInstructionText() {
		return commonFunctions.getElement(constant.How.XPATH, "//h3[text()='A simple two-step calculator']");
	}
	
	public static WebElement textWelcomeDilution() {
		return commonFunctions.getElement(constant.How.XPATH, "//h3[text()='Welcome to the Dilution Calculator']");
	}
	
	public static WebElement btStartCalculator() {
		return commonFunctions.getElement(constant.How.XPATH, "//button[text()='Start calculator']");
	}
	
	public static WebElement inputTotalShares() {
		return commonFunctions.getElement(constant.How.XPATH, "//div[span='Total outstanding shares']/following-sibling::div/div/div/div/input");
	}
	
	public static WebElement inputFounder() {
		return commonFunctions.getElement(constant.How.XPATH, "//div[span='Founder 1']/following-sibling::div/div/div/div/div/input");
	}
	
	public static WebElement txtFounder() {
		return commonFunctions.getElement(constant.How.XPATH, "//td[span='Founder 1']/following-sibling::td/span");
	}
	
	public static WebElement txtFounderPercentage() {
		return commonFunctions.getElement(constant.How.XPATH, "//td[span='Founder 1']/following-sibling::td/div/span");
	}
	
	public static WebElement txtInvestor() {
		return commonFunctions.getElement(constant.How.XPATH, "//td[span='Investor']/following-sibling::td/span");
	}
	
	public static WebElement txtInvestorPercentage() {
		return commonFunctions.getElement(constant.How.XPATH, "//td[span='Investor']/following-sibling::td/div/span");
	}
	
	public static WebElement txtEOP() {
		return commonFunctions.getElement(constant.How.XPATH, "//td[span='Employee stock option pool']/following-sibling::td/span");
	}
	
	public static WebElement txtEOPPercentage() {
		return commonFunctions.getElement(constant.How.XPATH, "//td[span='Employee stock option pool']/following-sibling::td/div/span");
	}
	
	public static WebElement txtTotal() {
		return commonFunctions.getElement(constant.How.XPATH, "//td[span='Total']/following-sibling::td/span");
	}
	
	public static WebElement txtTotalPercentage() {
		return commonFunctions.getElement(constant.How.XPATH, "//td[span='Total']/following-sibling::td/div/span");
	}
	
	public static WebElement txtFounderDiluted() {
		return commonFunctions.getElement(constant.How.XPATH, "//td[span='Founder 1']/following-sibling::td/div/span/div");
	}
	
	public static WebElement inputPreMoney() {
		return commonFunctions.getElement(constant.How.XPATH, "//div/span[text()='Pre-money valuation']/../following-sibling::div/div/div/div/input");
	}
	
	public static WebElement inputProposed() {
		return commonFunctions.getElement(constant.How.XPATH, "//div/span[text()='Proposed investment']/../following-sibling::div/div/div/div/input");
	}
	
	public static WebElement ownershipPercent(String percentage) {
		return commonFunctions.getElement(constant.How.XPATH, "//div/span[text()='Ownership percentage asked']/../following-sibling::div/div[text()='"+percentage+"']");
	}
	
	public static WebElement inputStockOptionPool() {
		return commonFunctions.getElement(constant.How.XPATH, "//div/span[text()='Employee stock option pool']/../following-sibling::div/div/div/div/div/input");
	}
	
	public static WebElement btCalculate() {
		return commonFunctions.getElement(constant.How.XPATH, "//button[text()='Calculate']");
	}
	
	public static WebElement txtPreMoney() {
		return commonFunctions.getElement(constant.How.XPATH, "//div/span[text()='Pre-money valuation']/../following-sibling::div[@class='pa3']");
	}
	
	public static WebElement txtEffectivePreMoney() {
		return commonFunctions.getElement(constant.How.XPATH, "//div/span[text()='The effective pre-money']/../following-sibling::div");
	}
	
	public static WebElement txtPostMoney() {
		return commonFunctions.getElement(constant.How.XPATH, "//div/span[text()='Post-money']/../following-sibling::div");
	}
	
	public static WebElement txtSeries() {
		return commonFunctions.getElement(constant.How.XPATH, "//div/span[text()=\"Investor's price per share\"]/../following-sibling::div");
	}

}
