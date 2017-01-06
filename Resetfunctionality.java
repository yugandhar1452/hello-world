package SeleniumVelocity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Resetfunctionality {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	public static final String Path_TestData = "C://Users//yugandhar//workspace//SeleniumPractice//src//testdata//";
	public static final String File_TestData = "TestData.xlsx";



	public static void setExcelFile(String Path,String SheetName) throws Exception {

		try {
			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(Path);
			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
		} catch (Exception e){
			throw (e);
		}
	}
	//This method is to write in the Excel cell, Row num and Col num are the parameters

	public static void setCellData(String Result,  int RowNum, int ColNum) throws Exception
	{
		try{
			Row  = ExcelWSheet.getRow(RowNum);
			Cell = Row.getCell(ColNum);
			if (Cell == null) {
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(Result);
			} 
			else {
				Cell.setCellValue(Result);
			}
			FileOutputStream fileOut = new FileOutputStream(Path_TestData+File_TestData);
			ExcelWBook.write(fileOut);
			//fileOut.flush();
			//fileOut.close();
		}
		catch(Exception e){
			throw(e);
		}
	}
	static WebDriver driver=null;

	public static void highlightElement(WebElement element) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: red; border: 10px solid red;");
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
		//		driver.manage().timeouts().Explicitwait(100, TimeUnit.SECONDS);
	}

	public static void main(String[] args) throws Exception {

		//WebDriver driver = new FirefoxDriver();
	

		String chrome="E:\\Selenium Practice\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chrome);
		driver = new ChromeDriver();
		//Login into application
		driver.get("https://qa.velocity.voyagersopris.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Resetfunctionality.highlightElement(driver.findElement(By.id("inputEmail")));
		driver.findElement(By.id("inputEmail")).sendKeys("zengrade4z3");
		driver.findElement(By.id("inputPassword")).sendKeys("zengrade4z3");
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
		driver.get("https://qa.velocity.voyagersopris.com/scene");

		Select LessontypeDP=new Select(driver.findElement(By.id("lessonType")));
		List<WebElement> ProblemTypes = LessontypeDP.getOptions();
		System.out.println(ProblemTypes.size());
		for(int i=0;i<=ProblemTypes.size();i++)
		{

			LessontypeDP.selectByIndex(i);
			System.out.println(ProblemTypes.get(i).getText());
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			Select LessonDP=new Select(driver.findElement(By.id("lesson")));
			List<WebElement> Lessonnames=LessonDP.getOptions();
		for(int j=0;j<=Lessonnames.size();j++)
			{
			System.out.println(Lessonnames.get(0).getText());
			setExcelFile(Path_TestData+File_TestData,"TestData");
			System.out.println("file is opened");
			setCellData(Lessonnames.get(j).getText(),j+1,2);
			System.out.println("data is entered");
			}
		}

	}
}