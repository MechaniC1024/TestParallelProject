package Parallel.ParallelTESTNG;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class Base{

	//static List<Driver> a;
	
	static volatile Map<String, List<Driver>> map = new HashMap<>();
	
	RemoteWebDriver driver;
	String brow;
	
	
	String b;
	
	List<Driver> driversPool(int ab, DesiredCapabilities cap) throws MalformedURLException {
		
		List<Driver> abc = new ArrayList<>();
		
		for(int i = 0; abc.size() < ab; i++) {
			
			abc.add(i, new Driver().setFlag(true).setDriver(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub/"), cap)));
		}
		
		return abc;
	}
	
	@Parameters({"Browser"})
	@BeforeSuite
	public void beforeSuite(String browser, ITestContext context) throws MalformedURLException {
		b = browser;
		System.out.println("BROWSER = " + browser + "  BEFORE SUITE CURRENT THREAD = " + Thread.currentThread() + " THREAD ID = " + Thread.currentThread().getId());
		DesiredCapabilities cap = Browsers.valueOf(browser.toUpperCase()).create();			
		//cap.setCapability("enableVNC", true);
		//cap.setCapability("enableLog", true);
		//cap.setCapability("logName", "my-cool-log.log");
		//cap.setCapability("enableVideo", true);
		//cap.setCapability("videoName", browser+".mp4");
		
//		if(a == null)
//			try {
//				a = driversPool(2, cap); //context.getSuite().getXmlSuite().getThreadCount()
//			} catch (NumberFormatException | MalformedURLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		
		map.put(browser, driversPool(2, cap));
		System.out.println();
		System.out.println(map);
		System.out.println();
	}	
	
	
	@BeforeTest
	public void beforeTest(ITestContext context) {

		//System.out.println("BEFORE DRIVER " + driver);
		String bro = context.getCurrentXmlTest().getParameter("Browser");
		System.out.println("BROWSER = " + bro + "  BEFORE TEST CURRENT THREAD = " + Thread.currentThread() + " THREAD ID = " + Thread.currentThread().getId());
		f: while (true) {
			fa:for (int i = 0; i < map.get(bro).size(); i++) {
				if (map.get(bro).get(i).getFlag() == true) {
					map.get(bro).get(i).setFlag(false);
					driver = map.get(bro).get(i).getDriver();
					//driver.manage().window().maximize();
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
		if(context.getCurrentXmlTest().getName().equals(brow)) {
			
			for(int i = 0; i < map.get(bro).size(); i++) {
				if(driver == map.get(bro).get(i).getDriver())
					map.get(bro).get(i).setFlag(true);
			}
		}
	}
	
	@AfterSuite
	public void afterSuite() {
		System.out.println("AFTER SUITE BROWSER = " + b);
		for(int i = 0; i < map.get(b).size(); i++) {
			map.get(b).get(i).getDriver().quit();

		}
		
	}

}