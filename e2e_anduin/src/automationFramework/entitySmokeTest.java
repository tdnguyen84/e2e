package automationFramework;


import org.testng.Assert;


import pageObjects.Login;
import utilities.commonFunctions;
import utilities.constant;
import utilities.setup;

public class entitySmokeTest {

	public static void Execute(String entityType) {
		setup.setUp();
		Login.signIn(constant.globalVariables.testUser, constant.globalVariables.testPass);
		String homeFileMangerName = "";
		if(entityType=="Investor") {
			commonFunctions.getElement(constant.How.LINKTEXT, "Fund Manager").click();
			Assert.assertEquals(commonFunctions.getElement(constant.How.XPATH, "//h2[text()='Manage Funds']").isDisplayed(), true);
			Assert.assertEquals(commonFunctions.getElement(constant.How.XPATH, "//button/span[text()='Add new fund']").isDisplayed(), true);
			homeFileMangerName = constant.globalVariables.testLegalName;
		}else {
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
		Assert.assertEquals(commonFunctions.getElement(constant.How.XPATH, "//td[text()='"+constant.globalVariables.testUser+"']").isDisplayed(), true);
		
		commonFunctions.getElement(constant.How.LINKTEXT, "File Manager").click();
		Assert.assertEquals(commonFunctions.getElement(constant.How.XPATH, "//span[text()='"+homeFileMangerName+"']").isDisplayed(), true);
		
		commonFunctions.getElement(constant.How.LINKTEXT, "Settings").click();
		Assert.assertEquals(commonFunctions.getElement(constant.How.XPATH, "//h2[text()='Organization Settings']").isDisplayed(), true);
		Assert.assertEquals(commonFunctions.getElement(constant.How.XPATH, "//h2[text()='Organization logo']").isDisplayed(), true);
	
		setup.tearDown();
	}

}
