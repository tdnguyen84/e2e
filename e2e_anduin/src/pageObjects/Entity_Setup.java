package pageObjects;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


import utilities.commonFunctions;
import utilities.constant;
import pageObjects.Deals_Dashboard;

public class Entity_Setup {
	public static WebElement trxnType() {
		return commonFunctions.getElement(constant.How.XPATH, "//p[@class='fw6'][text()=('Law Firm')]");
	}
	
	public static WebElement txtLegalName() {
		return commonFunctions.getElement(constant.How.CSSSELECTOR, "input[placeholder='Enter the legal name for your organization']");
	}
	
	public static WebElement txtShortName() {
		return commonFunctions.getElement(constant.How.CSSSELECTOR, "input[placeholder='Enter a short name here']");
	}
	
	public static WebElement txtDomain() {
		return commonFunctions.getElement(constant.How.CSSSELECTOR, "input[placeholder='Enter email domain here']");
	}
	
	public static WebElement btCreateOrg() {
		return commonFunctions.getElement(constant.How.XPATH, "//button[@type='submit'][text()='Create organization']");
	}
	
	public static WebElement linkCreateNewOrg() {
		return commonFunctions.getElement(constant.How.XPATH, "//span[@class='ml2'][text()='Create new organization']");
	}
	
	public static WebElement linkSetupNewOrg() {
		return commonFunctions.getElement(constant.How.XPATH, "//span[@class='ml2'][text()='Set up my organization']");
	}
	
	public static void createNewOrg(boolean hasNoExistingOrg) {
		if(hasNoExistingOrg) {
			linkSetupNewOrg().click();
		} else {
			Login.profileAvatar().click();
			linkCreateNewOrg().click();
			WebElement orgSetupContainer = commonFunctions.getElement(constant.How.CLASSNAME, "org-setup-form-container");
			commonFunctions.waitUntilElementVisible(orgSetupContainer);
		}
	}
	
	public static void setupOrg(String trxnType, String legalName, String shortName, String domain ) {
		WebElement ttype = null;
		if(trxnType == "Investor") {
			ttype = commonFunctions.getElement(constant.How.XPATH, "//div/div/p[@class='fw6'][text()='Investor']");
		} else if(trxnType == "Law Firm") {
			ttype = commonFunctions.getElement(constant.How.XPATH, "//div/div/p[@class='fw6'][text()='Law Firm']");
		} else {
			ttype = commonFunctions.getElement(constant.How.XPATH, "//div/div/p[@class='fw6'][text()='Company']");
		}
		
		ttype.click();
		txtLegalName().sendKeys(legalName);
		txtShortName().sendKeys(shortName);
		txtDomain().sendKeys(domain);
		commonFunctions.jsClick(btCreateOrg());
		WebElement orgNameTitle = commonFunctions.getElement(constant.How.CSSSELECTOR, "p.fw7.c--white"); 
		String verifiedName = orgNameTitle.getText();
		Assert.assertEquals(verifiedName, shortName);
		
		if(trxnType != "Company") {
			Assert.assertEquals(Deals_Dashboard.getTextActiveTab(), "Deals");
		} else {
			Assert.assertEquals(Deals_Dashboard.getTextActiveTab(), "Toolkit");
		}
		
	}
	

}
