package Parallel.ParallelTESTNG;

import org.openqa.selenium.remote.RemoteWebDriver;

class Driver{
	private boolean flag;
	private RemoteWebDriver drivers;
	public boolean getFlag() {
		return flag;
	}
	public Driver setFlag(boolean flag) {
		this.flag = flag;
		return this;
	}
	public RemoteWebDriver getDriver() {
		return drivers;
	}
	public Driver setDriver(RemoteWebDriver driver) {
		this.drivers = driver;
		return this;
	}		
}
