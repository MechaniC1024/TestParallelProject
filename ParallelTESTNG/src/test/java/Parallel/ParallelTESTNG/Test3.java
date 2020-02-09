package Parallel.ParallelTESTNG;

import org.testng.annotations.Test;

public class Test3 extends Base1 {

	@Test()
	public void test() throws InterruptedException {
		driver.get("https://www.youtube.ru");
		//Thread.sleep(20000);
		//a.get(b).setA(1);
	}
}
