package com.offersbyshop.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

public class TestDataProvider {
  @Test(dataProvider = "dp")
  public void f(String user, String pwd) {
	  System.out.println(user);
	  System.out.println(pwd);
	  Assert.assertEquals(user, "user1");
  }
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("before method");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("After method");

  }


  @DataProvider
  public Object[][] dp() {
	  Object[][] td = null;
	  
	  td = new Object [][]{
		  new Object[] { "user1", "pwd1" },
		  new Object[] { "user2", "pwd2" }
	  };
 return td;
  }
 
}
