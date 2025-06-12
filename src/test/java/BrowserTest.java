//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//
//
//public class BrowserTest {
//
//	public static void main(String[] args) {
//		WebDriver driver=new FirefoxDriver();
//		driver.get();}
//}

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserTest {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("https://selenium.dev");
       
    }
}
