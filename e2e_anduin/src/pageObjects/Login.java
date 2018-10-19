package pageObjects;
import utilities.commonFunctions;
import utilities.constant;

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
			return commonFunctions.getElement("xpath", "//button[@data-test-id='HeaderUser-Target']");
//			return commonFunctions.getElement("xpath", "//div[@class='w-max']/button/span");
		}
		
		public static WebElement createAccountLink() {
			return  commonFunctions.getElement(constant.How.LINKTEXT, "Create an Account");
		}
		
		public static WebElement inputEmail() {
			return  commonFunctions.getElement(constant.How.ID, "email");
		}
		
		public static WebElement capchaCheckbox() {
			return commonFunctions.getElement(constant.How.ID, "recaptcha-anchor");
		}
		
		public static WebElement btCreateAccount() {
			return commonFunctions.getElement(constant.How.XPATH, "//button[text()='Create account']");
		}
		
		public static WebElement txtWorkEmail(String email) {
			return commonFunctions.getElement(constant.How.XPATH, "//input[@class='text-field disabled'][@value='"+email+"']");
		}
		
		public static WebElement txtSetPassword() {
			return commonFunctions.getElement(constant.How.XPATH, "//div[label='Password *']/input[@type='password']");
		}
		
		public static WebElement txtRetypePassword() {
			return commonFunctions.getElement(constant.How.XPATH, "//div[label='Re-type Password *']/input[@type='password']");
		}
		
		public static WebElement txtJobTitle() {
			return commonFunctions.getElement(constant.How.XPATH, "//input[@name='user.attributes.jobTitle']");
		}
		
		public static WebElement btSignUp() {
			return commonFunctions.getElement(constant.How.XPATH, "//button[text()='Sign up']");
		}
		
		public static WebElement txtFirstName() {
			return commonFunctions.getElement(constant.How.NAME, "firstName");
		}
		
		public static WebElement txtLastName() {
			return commonFunctions.getElement(constant.How.NAME, "lastName");
		}
		
		public static WebElement successMsg() {
			return commonFunctions.getElement(constant.How.XPATH, "//h3[text()='Thank you for registering!']");
		}
		
		public static void signIn(String userName, String passWord) {		
			txtUserName().sendKeys(userName);
			txtPassWord().sendKeys(passWord);
			btSignIn().click();
			Assert.assertEquals(profileAvatar().isDisplayed(), true);
		}
			
   

}
