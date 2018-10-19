package automationFramework;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import pageObjects.*;

import java.rmi.UnknownHostException;

import org.testng.Assert;
import org.testng.annotations.Test;

//import utilities.setup;
//import org.testng.Assert;
import utilities.*;

public class createTransaction extends testBase {

	public static void mainCreateTransaction(String yourSide) throws UnknownHostException {
		
		Log.startTestCase(yourSide + " Creates Deal Test");
		if(yourSide == "Investor") {
			Login.signIn(constant.globalVariables.testUser, constant.globalVariables.testPass);
		} else {
			Login.signIn(constant.globalVariables.testCompanyuser, constant.globalVariables.testPass);
			commonFunctions.getElement(constant.How.LINKTEXT, "Deals").click();
		}
		
		Deals_Dashboard.createDealButton().click();
		AssertJUnit.assertEquals(Deals_Dashboard.btStartNewDeal().isEnabled(), true);
		
		New_Deal_Page.createNewDeal(yourSide,
									constant.globalVariables.trxnNoteType,
									constant.globalVariables.trxnConvertibleNote, "Yes");
		Log.endTestCase(yourSide + " Creates Deal Test");

	}
	
	@Test(dataProvider = "create_org_test")
	public void testCreateDeal(String yourSide) throws UnknownHostException {
		mainCreateTransaction(yourSide);
	}
	
	
	@DataProvider(name = "create_org_test")
	public Object[][] yourSide() {
	 return new Object[][] {
	   { "Investor"},
//	   { "Company"},
//	   { "Law Firm"}
	 };
	}

}