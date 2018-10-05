package pageObjects;

import org.openqa.selenium.WebElement;

import utilities.commonFunctions;
import utilities.constant;

public class Deal_Snapshot {
	
	public static WebElement participantTitle() {
		return commonFunctions.getElement(constant.How.XPATH, "//h4[text()='Participants']");
	}
	public static WebElement btGoToParticipant() {
		return commonFunctions.getElement(constant.How.XPATH, "//div[h4='Participants']/following-sibling::div/a");
	}
}
