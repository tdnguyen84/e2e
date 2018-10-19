package automationFramework;


import org.testng.annotations.BeforeMethod;

import java.rmi.UnknownHostException;

import org.testng.annotations.AfterMethod;



import utilities.setup;



public class testBase {
  
  @BeforeMethod(alwaysRun = true)
  public void beforeMethod() {
	  setup.setUp();
  }

  @AfterMethod(alwaysRun = true)
  public void afterMethod() throws UnknownHostException {
	  setup.tearDown();
  }

}
