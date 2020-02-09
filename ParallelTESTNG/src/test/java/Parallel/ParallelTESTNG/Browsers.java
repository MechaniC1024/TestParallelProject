package Parallel.ParallelTESTNG;

import org.openqa.selenium.remote.DesiredCapabilities;
import io.github.bonigarcia.wdm.WebDriverManager;

public enum Browsers {
	
	CHROME {
        public DesiredCapabilities create(){
        	
        	WebDriverManager.chromedriver().setup();
        	DesiredCapabilities cap = DesiredCapabilities.chrome();
        	//cap.setVersion("79.0");
            return cap;
        }
    },
    FIREFOX {
        public DesiredCapabilities create() {
        	
        	WebDriverManager.firefoxdriver().setup();
        	DesiredCapabilities cap = DesiredCapabilities.firefox();
        	//cap.setVersion("71.0");
            return cap;
        }
    };

    public DesiredCapabilities create(){
        return null;
    }
}
