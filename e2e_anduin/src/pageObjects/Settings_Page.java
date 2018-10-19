package pageObjects;

import org.openqa.selenium.WebElement;

import utilities.commonFunctions;
import utilities.constant;

public class Settings_Page {
	public static WebElement defaultRole(String roleName) {
		return commonFunctions.getElement(constant.How.XPATH, "//button/span/span[text()='"+roleName+"']");
	}

}
