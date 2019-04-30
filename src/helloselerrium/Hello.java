package helloselerrium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hello {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:/driver/chromedriver.exe");
		WebDriver driver =new ChromeDriver();
		driver.get("http://localhost:8080/mycode/login.html");
//		String title =driver.getTitle();
//		System.out.println(title);
		WebElement name=driver.findElement(By.id("name"));
		WebElement pwd = driver.findElement(By.id("pwd"));
        WebElement sub = driver.findElement(By.id("submit"));
        
        name.sendKeys("Admin1");
        Thread.sleep(2000);
        pwd.sendKeys("Admin1");
        Thread.sleep(2000);
        sub.click();
	     driver.close();
		
	}

}
