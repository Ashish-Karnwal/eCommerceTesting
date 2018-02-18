package com.offersbyshop.tests;

import java.io.File;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestExtent {
	ExtentReports er;
	ExtentTest et,et1;

	@Test
	public void method1() {
		// start tests
		et = er.startTest("DemoTest1");
		et1 = er.startTest("DemoTest2");
		// mark category to tests
		et.assignCategory("DemoCategory1");
		et1.assignCategory("DemoCategory2");

		// steps in test1
		et.log(LogStatus.INFO, "Report log for Info");
		et.log(LogStatus.SKIP, "Report log for Skip");
		et.log(LogStatus.PASS, "Report log for Pass");
		et.log(LogStatus.FAIL, "Report log for Fail");
		et.log(LogStatus.FATAL,"Report log for Fatal");
		
		// steps in test2
		et1.log(LogStatus.INFO, "Report log for Info");
		et1.log(LogStatus.SKIP, "Report log for Skip");
		et1.log(LogStatus.PASS, "Report log for Pass");
		et1.log(LogStatus.FAIL, "Report log for Fail");
		et1.log(LogStatus.FATAL,"Report log for Fatal");
		
		
		// end tests
		er.endTest(et);
		er.endTest(et1);
	}

	@AfterSuite
	public void aftersuite() {
		// close and write status to report
		er.flush();
		er.close();
	}
	
	@BeforeSuite
	public void beforeSuite() {
		er = new ExtentReports(System.getProperty("user.dir")+"\\extentReport\\testReport.html", true);
		er.addSystemInfo("environment", "QA").addSystemInfo("Owner", "DucatTestTeam");
		// loading the external xml file (i.e., extent-config.xml) which was placed
		// under the base directory
		// You could find the xml file below. Create xml file in your project and copy
		// past the code mentioned below
		er.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
	}

}