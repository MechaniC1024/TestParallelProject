package Parallel.ParallelTESTNG;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SingleDriver {

	private static volatile SingleDriver instance;
	private static volatile Map<String, List<Driver>> mapOfDrivers = new HashMap<>();

	private SingleDriver() {
	}

	public void addBrowser(String browser, int counterOfBrowser, DesiredCapabilities capabilities)
			throws MalformedURLException {

		List<Driver> listOfDrivers = new ArrayList<>();

		for (int i = 0; listOfDrivers.size() < counterOfBrowser; i++) {

			listOfDrivers.add(i, new Driver().setFlag(true)
					.setDriver(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub/"), capabilities)));
		}

		mapOfDrivers.put(browser, listOfDrivers);

	}

	public static SingleDriver getInstance() {
		if (instance == null)
			synchronized (SingleDriver.class) {
				if (instance == null)
					instance = new SingleDriver();
			}
		return instance;
	}

	public static Map<String, List<Driver>> getDriver() {
		return mapOfDrivers;
	}

}
