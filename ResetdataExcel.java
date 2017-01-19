package SeleniumVelocity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ResetdataExcel {
	private static XSSFSheet Sheet1;

	
	private static XSSFRow Row;
	public static final String Path_TestData = "C:\\Users\\yugandhar\\workspace\\SeleniumPractice\\src\\testdata\\";
	public static final String File_TestData = "TestData.xlsx";


	private static XSSFWorkbook workbook;
	static WebDriver driver=null;

	/*	public static void highlightElement(WebElement element) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: red; border: 10px solid red;");
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
		//		driver.manage().timeouts().Explicitwait(100, TimeUnit.SECONDS);
	}
	 */
	public static void main(String[] args) throws Exception {

		//WebDriver driver = new FirefoxDriver();
		workbook = new XSSFWorkbook();
		Sheet1=workbook.createSheet("Sheet123");

		String chrome="E:\\Selenium Practice\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chrome);
		driver = new ChromeDriver();
		//Login into application
		driver.get("https://velocity.voyagersopris.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Resetfunctionality.highlightElement(driver.findElement(By.id("inputEmail")));
		driver.findElement(By.id("inputEmail")).sendKeys("5gradejan16a");
		driver.findElement(By.id("inputPassword")).sendKeys("5gradejan16a");
		driver.findElement(By.xpath("//form[@id='k5i-login-form']/input[3]")).click();

		//verification of loading bars
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		String value1 =driver.findElement(By.className("loader")).getText();
		if(value1!=null)
			System.out.println("Loading Bar is available");
		else{System.out.println("Loading bar not available");}

		//verification of dash character
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String value2 =driver.findElement(By.className("spine-canvas")).getText();
		if(value2!=null)
			System.out.println("Dash is available");
		else{System.out.println("Dash is not available");}

		/*	//verification of World selector
		String value3 =driver.findElement(By.className("vsl_velocity_views_student_dashboard-worlds-label")).getText();
		if(value3!=null)
			System.out.println("World selecor is available");
		else{System.out.println("World selector not available");}*/
		driver.get("https://velocity.voyagersopris.com/scene");

		Select LessontypeDP=new Select(driver.findElement(By.id("lessonType")));
		List<WebElement> ProblemTypes = LessontypeDP.getOptions();
		System.out.println(ProblemTypes.size());
		int x=0;
		for(int i=0;i<3;i++)
		{
			int j;
			LessontypeDP.selectByIndex(i);
			Row = Sheet1.createRow(x);
			Cell Cell1=Row.createCell(0);
			Cell1.setCellValue(ProblemTypes.get(i).getText());
			System.out.println(ProblemTypes.get(i).getText());
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			Select LessonDP=new Select(driver.findElement(By.id("lesson")));
			List<WebElement> Lessonnames=LessonDP.getOptions();
			for(j=0;j<Lessonnames.size();j++)	
			{
				System.out.println(Lessonnames.get(j).getText());
				Row = Sheet1.createRow(x);
				Cell Cell2=Row.createCell(1);
				Cell2.setCellValue(Lessonnames.get(j).getText());
				x++;
			}

		}
		FileOutputStream out= new FileOutputStream("C:\\Users\\yugandhar\\Desktop\\workbook.xlsx");
		workbook.write(out);
		out.close();
		System.out.println("Succsess");
		driver.quit();
	}

}
