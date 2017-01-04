package SeleniumVelocity;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

public class Pratice1 {

	public static void main(String[] args) {

//WebDriver driver = new FirefoxDriver();
		
		String chrome="E:\\Selenium Practice\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chrome);
WebDriver driver = new ChromeDriver();
		driver.get("https://qa.velocity.voyagersopris.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("inputEmail")).sendKeys("5gradejan3a");
		driver.findElement(By.id("inputPassword")).sendKeys("5gradejan3a");
		driver.findElement(By.xpath("//form[@id='k5i-login-form']/input[3]")).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		String value1 =driver.findElement(By.className("loader")).getText();
		if(value1!=null)
		System.out.println("Loading Bar is available");
		else{System.out.println("Loading bar not available");}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String value2 =driver.findElement(By.className("spine-canvas")).getText();
		if(value2!=null)
		System.out.println("Dash is available");
		else{System.out.println("Dash is not available");}
		String value3 =driver.findElement(By.className("vsl_velocity_views_student_dashboard-worlds-label")).getText();
		if(value3!=null)
		System.out.println("World selecor is available");
		else{System.out.println("World selector not available");}
	}

}
