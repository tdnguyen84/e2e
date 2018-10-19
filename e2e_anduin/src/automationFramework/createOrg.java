package automationFramework;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.Entity_Setup;
import pageObjects.Login;
import utilities.Log;
import utilities.constant.*;

public class createOrg extends testBase{

	public static void mainCreateOrg(Boolean isNewSignUpUser, String orgType) {
		String testCaseName = "Create " + orgType + " Org Test";
		Log.startTestCase(testCaseName);
		if (orgType=="Lead Investor") {
			Login.signIn(globalVariables.testUser, globalVariables.testPass);
		} else if(orgType == "Company") {
			Login.signIn(globalVariables.testCompanyuser, globalVariables.testPass);
		} else {
			Login.signIn(globalVariables.testLFirmUser, globalVariables.testPass);
		}
		
		if(isNewSignUpUser) {
			Entity_Setup.createNewOrg(true);
		} else {
			Entity_Setup.createNewOrg(false);
		}
		
		switch(orgType){
			case "Lead Investor":
				Entity_Setup.setupOrg(globalVariables.orgInvestorType,
									  globalVariables.testLegalName,
									  globalVariables.testShortName, "", false);
				break;
			case "Company":
				Entity_Setup.setupOrg(globalVariables.orgCompanyType,
									  globalVariables.testCompanyName,
									  globalVariables.testCompanyShortName, "", false);
				break;
			case "Law Firm":
				Entity_Setup.setupOrg(globalVariables.orgLFirmType,
									  globalVariables.testLFirmName,
									  globalVariables.testLFirmShortName, "", false);
				break;
		}
		Log.endTestCase(testCaseName);

	}
	
	
	@Test(dataProvider = "create_org_test")
	public void CreateOrgTest(boolean isNewSignedUpUser, String orgType) {
		mainCreateOrg(isNewSignedUpUser, orgType);
	}
	
	@DataProvider(name = "create_org_test")
	public Object[][] createData1() {
	 return new Object[][] {
	   { true, "Lead Investor"},
	   { true, "Company"},
	   { true, "Law Firm"},
	   { false, "Lead Investor"},
	   { false, "Company"},
	   { false, "Law Firm"},
	 };
	}
}