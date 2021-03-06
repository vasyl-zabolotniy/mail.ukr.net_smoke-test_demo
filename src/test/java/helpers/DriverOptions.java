package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.gargoylesoftware.htmlunit.WebClient;

public abstract class DriverOptions {
	
	public static WebDriver getDriver(String string){		
		WebDriver driver;
		switch(string.toLowerCase()){
			case "firefox":
				driver = newFirefoxDriver();
				break;
			case "chrome":
				driver = newChromeDriver();
				break;
			case "htmlunit":
				driver = newHtmlUnitDriver();
				break;
			case "phantomjs":
				driver = newPhantomJSDriver();
				break;		
			default:
				driver = newFirefoxDriver();		
		}		
		return driver;		
	}
	
	private static WebDriver newHtmlUnitDriver() {
		WebDriver driver = new HtmlUnitDriver(true){
		@Override
        protected WebClient getWebClient() {
            WebClient c = super.getWebClient();
            c.getCache().setMaxSize(0);
            return c;
			}
		};
		return driver;
	}
	
	private static PhantomJSDriver newPhantomJSDriver() {		
		DesiredCapabilities phantomCap = DesiredCapabilities.phantomjs();
		phantomCap.setCapability("phantomjs.binary.path", "Q:\\eclipse\\phantomjs.exe");
		return new PhantomJSDriver(phantomCap);
	}

	//if Firefox installed to non-default location:
	private static FirefoxDriver newFirefoxDriver() {		
		System.setProperty("webdriver.firefox.bin", "Q:\\Mozilla Firefox\\firefox.exe");
		return new FirefoxDriver();
	}
	
	private static ChromeDriver newChromeDriver() {
		System.setProperty("webdriver.chrome.driver", "Q:/eclipse/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		String chromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";		
		options.setBinary(chromePath);
		return new ChromeDriver(options);
	}	
}
