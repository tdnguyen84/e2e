package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class setup {
	//The WEB DRIVER
	public static WebDriver _webdriver;
	
//	public static String _testsite = "http://localhost:8080";
	public static String _testsite = "https://canary.anduintransact.com";
//	public static String _testsite = "https://gondor-internal.anduintransact.com";
//	public static String _testsite = "https://deals.anduintransact.com";
	
	//Default value for local testing
	public static String _browserName = browser.Firefox.Name;

	
	public static void initWebDriver () {
		switch(_browserName) {
			case browser.Firefox.Name:
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/geckodriver");
				_webdriver = new FirefoxDriver();
				break;
			case browser.Chrome.Name:
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/chromedriver");
				_webdriver = new ChromeDriver();
		}
	}
	
	public static void setUp() {
		initWebDriver();
		String currentWindowHandle = _webdriver.getWindowHandle();
		((JavascriptExecutor)_webdriver).executeScript("alert('Test')"); 
		_webdriver.switchTo().alert().accept();
		_webdriver.switchTo().window(currentWindowHandle);
		_webdriver.manage().window().maximize();
		commonFunctions.gotoUrl(_testsite);
	}
	
	public static void tearDown() {
		_webdriver.quit();
	}
	
}
