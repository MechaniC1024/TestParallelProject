package Parallel.ParallelTESTNG;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;

public class Base1 {

static volatile Map<String, List<Driver>> map;
	
	RemoteWebDriver driver;
	String brow;	
	String b;

	
	@Parameters({"Browser"})
	@BeforeSuite
	public void beforeSuite(String browser, ITestContext context) throws MalformedURLException {
		b = browser;
		System.out.println("BROWSER = " + browser + "  BEFORE SUITE CURRENT THREAD = " + Thread.currentThread() + " THREAD ID = " + Thread.currentThread().getId());
		DesiredCapabilities cap = Browsers.valueOf(browser.toUpperCase()).create();			
		cap.setCapability("enableVNC", true);
		cap.setCapability("enableLog", true);
		cap.setCapability("logName", "my-cool-log.log");
		cap.setCapability("enableVideo", true);
		cap.setCapability("videoName", browser+".mp4");
		
		SingleDriver.getInstance().addBrowser(browser, 1, cap);
		map = SingleDriver.getDriver();

		System.out.println();
		System.out.println(map);
		System.out.println();
	}	
	
	
	@BeforeTest
	public void beforeTest(ITestContext context) {

		//System.out.println("BEFORE DRIVER " + driver);
		String bro = context.getCurrentXmlTest().getParameter("Browser");
		System.out.println("BROWSER = " + bro + "  BEFORE TEST CURRENT THREAD = " + Thread.currentThread() + " THREAD ID = " + Thread.currentThread().getId());
		
		List<Driver> list = map.get(bro);
		
		f: while (true) {
			fa:for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getFlag() == true) {
					list.get(i).setFlag(false);
					driver = list.get(i).getDriver();
					driver.manage().window().maximize();
					break f;
				}
				else
					continue fa;
			}
		}
	
	brow = context.getCurrentXmlTest().getName();
	}

	@AfterTest
	public void afterTest(ITestContext context) {
		String bro = context.getCurrentXmlTest().getParameter("Browser");
		
		List<Driver> list = map.get(bro);
		
		if(context.getCurrentXmlTest().getName().equals(brow)) {
			
			for(int i = 0; i < list.size(); i++) {
				if(driver == list.get(i).getDriver())
					list.get(i).setFlag(true);
			}
		}
	}
	
	@AfterSuite
	public void afterSuite() {
		System.out.println("AFTER SUITE BROWSER = " + b);
		
		List<Driver> list = map.get(b);
		
		for(int i = 0; i < list.size(); i++) {
			list.get(i).getDriver().quit();

		}
		
	}

}
