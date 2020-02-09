package Parallel.ParallelTESTNG;

import org.testng.annotations.Test;

public class Test2 extends Base1 {

	@Test()
	public void test() throws InterruptedException {
		driver.get("https://www.yandex.ru");
		//Thread.sleep(15000);
		//a.get(b).setA(1);
	}
}
