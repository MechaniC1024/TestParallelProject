package Parallel.ParallelTESTNG;

import org.testng.annotations.Test;

public class Test1 extends Base1 {

	@Test()
	public void test() throws InterruptedException {
		driver.get("https://www.google.com");
		//Thread.sleep(20000);
		//a.get(b).setA(1);
	}
}
