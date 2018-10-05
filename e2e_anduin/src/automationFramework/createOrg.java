package automationFramework;

import pageObjects.Entity_Setup;
import pageObjects.Login;
import utilities.constant;
import utilities.setup;

public class createOrg {

	public static void Execute(Boolean isNewSignUpUser, String orgType) {
		setup.setUp();
		
		if(orgType=="Lead Investor") {
			Login.signIn(constant.globalVariables.testUser, constant.globalVariables.testPass);
		}else {
			Login.signIn(constant.globalVariables.testCompanyuser, constant.globalVariables.testPass);
		}
		
		if(isNewSignUpUser) {
			Entity_Setup.createNewOrg(true);
		} else {
			Entity_Setup.createNewOrg(false);
		}
		
		switch(orgType){
			case "Lead Investor":
				Entity_Setup.setupOrg(constant.globalVariables.orgInvestorType,
									  constant.globalVariables.testLegalName,
									  constant.globalVariables.testShortName, "");
				break;
			case "Company":
				Entity_Setup.setupOrg(constant.globalVariables.orgCompanyType,
									  constant.globalVariables.testCompanyName,
									  constant.globalVariables.testCompanyShortName, "");
				break;
			case "Law Firm":
				Entity_Setup.setupOrg(constant.globalVariables.orgLFirmType,
									  constant.globalVariables.testLFirmName,
									  constant.globalVariables.testLFirmShortName, "");
				break;
		}

		
		
		
		setup.tearDown();
	}
}