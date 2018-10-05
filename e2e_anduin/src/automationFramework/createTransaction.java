package automationFramework;

import pageObjects.*;
import org.testng.Assert;

//import utilities.setup;
//import org.testng.Assert;
import utilities.*;

public class createTransaction {

	public static void Execute() {
		setup.setUp();
		Login.signIn(constant.globalVariables.testUser, constant.globalVariables.testPass);
		
		Deals_Dashboard.createDealButton().click();
		
	
		Assert.assertEquals(Deals_Dashboard.btStartNewDeal().isEnabled(), true);
		
		New_Deal_Page.createNewDeal(constant.globalVariables.testCompanyName,
									constant.globalVariables.trxnNoteType,
									constant.globalVariables.trxnConvertibleNote, "Yes");
				
		setup.tearDown();

	}

}