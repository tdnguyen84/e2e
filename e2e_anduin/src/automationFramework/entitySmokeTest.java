package automationFramework;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.Login;
import utilities.Log;
import utilities.commonFunctions;
import utilities.constant;
import utilities.setup;

public class entitySmokeTest extends testBase {

	public static void mainEntitySmoke(String entityType) {
		String testCaseName = "Smoke Test For " + entityType + " Org";
		Log.startTestCase(testCaseName);;
		String homeFileMangerName = "";
		String testUser = "";
		if(entityType=="Investor" || entityType=="Law Firm") {
			testUser = entityType=="Investor"? constant.globalVariables.testUser : constant.globalVariables.testLFirmUser;
			Login.signIn(testUser, constant.globalVariables.testPass);
//			commonFunctions.getElement(constant.How.LINKTEXT, "Fund Manager").click();
//			Assert.assertEquals(commonFunctions.getElement(constant.How.XPATH, "//h2[text()='Manage Funds']").isDisplayed(), true);
//			Assert.assertEquals(commonFunctions.getElement(constant.How.XPATH, "//button/span[text()='Add new fund']").isDisplayed(), true);
			homeFileMangerName = constant.globalVariables.testLegalName;
		}else {
			testUser = constant.globalVariables.testCompanyuser;
			Login.signIn(testUser, constant.globalVariables.testPass);
			Assert.assertEquals(commonFunctions.getElement(constant.How.XPATH, "//h3[text()='Term sheet simulator']").isDisplayed(), true);
			Assert.assertEquals(commonFunctions.getElement(constant.How.XPATH, "//h3[text()='E-signature']").isDisplayed(), true);
			Assert.assertEquals(commonFunctions.getElement(constant.How.XPATH, "//h3[text()='Venture deals']").isDisplayed(), true);
			
			commonFunctions.getElement(constant.How.LINKTEXT, "Deals").click();
			Assert.assertEquals(commonFunctions.getElement(constant.How.XPATH, "//button/span[text()='Start a new deal']").isDisplayed(), true);
			
			commonFunctions.getElement(constant.How.LINKTEXT, "E-signature").click();
			Assert.assertEquals(commonFunctions.getElement(constant.How.XPATH, "//button/span[text()='Sign']").isDisplayed(), true);
			Assert.assertEquals(commonFunctions.getElement(constant.How.XPATH, "//button/div[text()='Request']").isDisplayed(), true);
			homeFileMangerName = constant.globalVariables.testCompanyName;
		}
		
		
		commonFunctions.getElement(constant.How.LINKTEXT, "Members").click();
		Assert.assertEquals(commonFunctions.getElement(constant.How.XPATH, "//h2[text()='Members']").isDisplayed(), true);
		Assert.assertEquals(commonFunctions.getElement(constant.How.XPATH, "//button/span[text()='Invite new member']").isDisplayed(), true);
		Assert.assertEquals(commonFunctions.getElement(constant.How.XPATH, "//td[text()='"+testUser+"']").isDisplayed(), true);
		
		commonFunctions.getElement(constant.How.LINKTEXT, "File Manager").click();
		Assert.assertEquals(commonFunctions.getElement(constant.How.XPATH, "//span[text()='"+homeFileMangerName+"']").isDisplayed(), true);
		
		commonFunctions.getElement(constant.How.LINKTEXT, "Settings").click();
		Assert.assertEquals(commonFunctions.getElement(constant.How.XPATH, "//h2[text()='Organization Settings']").isDisplayed(), true);
		Assert.assertEquals(commonFunctions.getElement(constant.How.XPATH, "//h2[text()='Organization logo']").isDisplayed(), true);
	
		Log.endTestCase(testCaseName);
	}
	
	@Test(dataProvider="entity_smoke_test")
	public static void testEntitySmoke(String entityType) {
		mainEntitySmoke(entityType);
	}
	
	@DataProvider(name = "entity_smoke_test")
	public Object[][] enityType() {
	 return new Object[][] {
	   { "Investor"},
//	   { "Company"},
	   { "Law Firm"}
	 };
	}

}
