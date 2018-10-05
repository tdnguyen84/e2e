package pageObjects;
import utilities.commonFunctions;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


public class Login {
	
		public static WebElement btSignIn() {
			return commonFunctions.getElement("xpath", "//button[@type='submit']");
		}
		
		public static WebElement txtUserName() {
			return commonFunctions.getElement("id", "username");
		}
		
		public static WebElement txtPassWord() {
			return commonFunctions.getElement("id", "password");
		}
		
		public static WebElement separateDeal() {
			return commonFunctions.getElement("xpath", "//p[@class='ml2 fw7'][text()='Separate deals']");
		}
		
		public static WebElement profileAvatar() {
			//return commonFunctions.getElement("xpath", "//button[@data-test-id='HeaderUser-Target']");
			return commonFunctions.getElement("xpath", "//div[@class='w-max']/button/span");
		}
		
		public static void signIn(String userName, String passWord) {		
			txtUserName().sendKeys(userName);
			txtPassWord().sendKeys(passWord);
			btSignIn().click();
			Assert.assertEquals(profileAvatar().isDisplayed(), true);
		}
			
   

}
