package pageObjects;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import utilities.commonFunctions;
import utilities.constant;

public class New_Deal_Page {
	public static WebElement inputCompany() {
		return commonFunctions.getElement(constant.How.CSSSELECTOR, "input[placeholder='Enter company name']");
	}
	
	public static WebElement inputInvestor() {
		return commonFunctions.getElement(constant.How.CSSSELECTOR, "input[placeholder='Enter investor name']");
	}
	
	public static WebElement radioBtINote() {
		return commonFunctions.getElement(constant.How.XPATH, "//label/span[@class='ml2']/span[text()='Note, SAFE, KISS']");
	}
	
	public static WebElement radioBtIPricedRound() {
		return commonFunctions.getElement(constant.How.XPATH, "//label/span[@class='ml2']/span[text()='Priced round']");
	}
	
	public static WebElement radioBtNoteType() {
		return commonFunctions.getElement(constant.How.XPATH, "//label/span[@class='ml2']/span[text()='Convertible Note']");
	}
	
	public static WebElement radioBtSafeType() {
		return commonFunctions.getElement(constant.How.XPATH, "//label/span[@class='ml2']/span[text()='SAFE']");
	}
	
	public static WebElement radioBtKissType() {
		return commonFunctions.getElement(constant.How.XPATH, "//label/span[@class='ml2']/span[text()='KISS']");
	}
	
	public static WebElement radioBtSeedType() {
		return commonFunctions.getElement(constant.How.XPATH, "//label/span[@class='ml2']/span[text()='Seed']");
	}
	
	public static WebElement radioBtSerieAType() {
		return commonFunctions.getElement(constant.How.XPATH, "//label/span[@class='ml2']/span[text()='Series A']");
	}
	
	public static WebElement radioBtOtherType() {
		return commonFunctions.getElement(constant.How.XPATH, "//label/span[@class='ml2']/span[text()='Other']");
	}
	
	public static WebElement txtFundType() {
		return commonFunctions.getElement(constant.How.CSSSELECTOR, "input[placeholder='Enter funding type']");
	}
	
	public static WebElement btStartNewDeal() {
		return commonFunctions.getElement(constant.How.XPATH, "//button[text()='Start a new deal']");
	}
	
	public static WebElement btCancel() {
		return commonFunctions.getElement(constant.How.XPATH, "//button[text()='Cancel']");
	}
	
	public static WebElement radioLeadYes() {
		return commonFunctions.getElement(constant.How.XPATH, "//label/span[@class='ml2']/span[text()='Yes']");
	}
	
	public static WebElement radioLeadNo() {
		return commonFunctions.getElement(constant.How.XPATH, "//label/span[@class='ml2']/span[text()='No']");
	}
	
	public static void enterCompanyName(String companyName) {
		inputCompany().sendKeys(companyName);
	}
	
	public static void enterInvestorName(String investorName) {
		inputInvestor().sendKeys(investorName);
	}
	
	public static void selectInvestType(String typeInvestment) {
		if(typeInvestment == "Note") {
			radioBtINote().click();
		} else {
			radioBtIPricedRound().click();
		}
	}
	
	public static void selectTypeTrxn(String typeTrxn) {
		WebElement selectedType = commonFunctions.getElement(constant.How.XPATH, "//label/span[@class='ml2']/span[text()='"+typeTrxn+"']");
		selectedType.click();
	}
	
	public static void isLeadInvestor(String isLead) {
		WebElement radioLeadInvestor = commonFunctions.getElement(constant.How.XPATH, "//label/span[@class='ml2']/span[text()='"+isLead+"']");
		radioLeadInvestor.click();
	}
	
	public static void createNewDeal(String yourSide, String investType, String typeTrxn, String isLeadInvestor) {
		if(yourSide == "Investor") {
			enterCompanyName(constant.globalVariables.testCompanyName);
		}else {
			enterInvestorName(constant.globalVariables.testLegalName);
		}
		
		selectInvestType(investType);
		selectTypeTrxn(typeTrxn);
		isLeadInvestor(isLeadInvestor);
		btStartNewDeal().click();
		
		//Deal Observer role
		//Assert.assertEquals(commonFunctions.getElement(constant.How.XPATH, "//h2[text()='Deal Snapshot']").isDisplayed(), true);
		
		//Deal Team member role
		Assert.assertEquals(commonFunctions.getElement(constant.How.XPATH, "//h2[text()='Deal Checklist']").isDisplayed(), true);
		
	}

}
