package introduction;



import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;
public class harishAssignment {

	
	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
	WebDriver driver = null;	
	Properties prop = new Properties();	
	FileInputStream fso = new FileInputStream(System.getProperty("user.dir")+"\\src\\introduction\\config.properties");
	prop.load(fso);
	
	
	String HomePageTitle ="Welcome: Mercury Tours";
	String BrowserName = prop.getProperty("browser");
	String url = prop.getProperty("url");
	System.out.println(BrowserName);
	if (BrowserName.equals("chrome"))
	{
		System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\suhail\\\\Music\\\\chromedriver.exe");
		 driver = new ChromeDriver();
		 driver.get(url);
	}
	else if (BrowserName.equals("firefox"))
	{
		//System.setProperty("webdriver.gecko.driver", "C:\\Users\\suhail\\Music\\geckodriver.exe");
		 System.setProperty("webdriver.gecko.driver", "C:\\Users\\suhail\\Music\\New folder\\geckodriver.exe");
	        driver = new FirefoxDriver();	

		 driver.get(url);
	}
	else if(BrowserName.equals("IE"))
	{
		System.setProperty("webdriver.ie.driver", "C:\\\\Users\\\\suhail\\\\Music\\\\New folder\\\\IEDriverServer.exe");  
		driver=new InternetExplorerDriver();  
		driver.get(url);
	}
	
	
	JavascriptExecutor js = (JavascriptExecutor) driver;
	driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//*********Title Validation***************************
		String PageTitle= driver.getTitle();
		if(PageTitle.equalsIgnoreCase(HomePageTitle))
		{
			System.out.println(PageTitle);
		}
		
		//*********************validate the date******************
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		SimpleDateFormat simpleformat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");
		int dat= cal.get(cal.DAY_OF_MONTH);
		String date = Integer.toString(dat);
		simpleformat = new SimpleDateFormat("MMM");
	      String month= simpleformat.format(new Date());
		int yr = cal.get(cal.YEAR);
		String Year =Integer.toString(yr);
		String todayDate ="";
		todayDate =month+" "+date+","+" "+Year;
		
		System.out.println(todayDate);
		
		String pageDate=
		//driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td[3]/form/table/tbody/tr[1]/td/font/b")).getText();
		driver.findElement(By.xpath("//tr[@align='right']/td/font/b")).getText();
		if(todayDate.equalsIgnoreCase(pageDate))
		{
			System.out.println("The page date and today  is  same:" + pageDate);
		}
		else
		{
			System.out.println("Date error");
		}
	
		driver.findElement(By.name("userName")).sendKeys("mercury");
		driver.findElement(By.name("password")).sendKeys("mercury");
		driver.findElement(By.name("login")).click();
//****************************Side bar name******************************
	WebElement element =
		driver.findElement(By.xpath("//table[@border='2']/tbody"));
	List<WebElement> norows = element.findElements(By.tagName("tr"));
	int size = norows.size();
	
	System.out.println("The size is:"+size);
	ArrayList<String> value = new ArrayList<String>();
			
		//	driver.findElement(By.xpath("//*[text()='Home']")).getText();
	value.add(driver.findElement(By.xpath("//table/tbody/tr[1]/td[2]/font/a")).getText());
	value.add(driver.findElement(By.xpath("//table/tbody/tr[2]/td[2]/a")).getText());
		
	value.add(driver.findElement(By.xpath("//table/tbody/tr[3]/td[2]/a")).getText());
	
	value.add(driver.findElement(By.xpath("//table/tbody/tr[4]/td[2]/font/a")).getText());
	
	value.add( driver.findElement(By.xpath("//table/tbody/tr[5]/td[2]/a")).getText());
	value.add(driver.findElement(By.xpath("//table/tbody/tr[6]/td[2]/a")).getText());
	value.add(driver.findElement(By.xpath("//table/tbody/tr[7]/td[2]/a")).getText());
	
	for(int i=0;i<size;i++)
	{
	System.out.println("The Table values are:"+value.get(i));
	}	
		
		
		//********************validate select flight screen****************
		//WebElement validation1 =driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[1]/td/img"));
		WebElement validation1 = driver.findElement(By.xpath("//img[@src='/images/masts/mast_flightfinder.gif']"));
	//String ff =	validation1.getText();
		//System.out.println(ff);
		WebDriverWait w = new WebDriverWait(driver,5);	
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='/images/masts/mast_flightfinder.gif']")));		
		if(validation1.isEnabled())
		{
			System.out.println("Flight finder screen appears");
		}
		else
		{
			System.out.println("Flight finder screen not appears");
		}

		driver.findElement(By.xpath("//input[@value='oneway']")).click();
		Select dep = new Select(driver.findElement(By.name("fromPort")));
		dep.selectByIndex(2);
		//Thread.sleep(2000L);
		Select mon = new Select(driver.findElement(By.name("fromMonth")));
		//Thread.sleep(2000L);
		mon.selectByValue("4");
		//Thread.sleep(2000L);
		Select arr = new Select(driver.findElement(By.name("toPort")));
		arr.selectByValue("New York");
		Select toda = new Select(driver.findElement(By.name("toMonth")));
		toda.selectByValue("5");
		//Thread.sleep(2000L);
		driver.findElement(By.xpath("//input[@value='Business']")).click();
		//Thread.sleep(2000L);
		Select arrli = new Select(driver.findElement(By.name("airline")));
		arrli.selectByIndex(2);
		driver.findElement(By.name("findFlights")).click();
		System.out.println(driver.getTitle());
		//Thread.sleep(2000L);
		
		//************Select flight screen validation*******************
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);
		
		WebElement validation2 = driver.findElement(By.xpath("//img[@src='/images/masts/mast_selectflight.gif']"));
		WebElement foo = wait.until(new Function<WebDriver,WebElement>()
		{public WebElement apply(WebDriver driver)
		{
			if(driver.findElement(By.xpath("//img[@src='/images/masts/mast_selectflight.gif']")).isDisplayed())
			{
			return driver.findElement(By.xpath("//img[@src='/images/masts/mast_selectflight.gif']")); 
			}
			else
			{
				return null;
			}
			}});
		
		if(validation2.isDisplayed())
		{
			System.out.println("Select Flight  screen appears");
		}
		else
		{
			System.out.println("Select Flight  screen not appears");
		}
		
		List<WebElement> options1 = driver.findElements(By.xpath("//td[@class='frame_action_xrows']//input[@name='outFlight']"));	
		Random random = new Random();
		int index1 = random.nextInt(options1.size());
		
	    options1.get(index1).click(); 
	    System.out.println("The index value is :"+ index1);
	    
	    List<WebElement> options2 = driver.findElements(By.xpath("//td[@class='frame_action_xrows']//input[@name='inFlight']"));	
	
		int index2 = random.nextInt(options1.size());
		
	    options1.get(index2).click(); 
	    System.out.println("The index value is :"+ index2);
	    
		WebElement Element = driver.findElement(By.name("reserveFlights"));
		js.executeScript("arguments[0].scrollIntoView();", Element);
		Element.click();
		//Thread.sleep(2000L);
		//***********Book a flight screen ***********************
		WebElement validation3=driver.findElement(By.xpath("//*[@src='/images/masts/mast_book.gif']"));
		if(validation3.isEnabled())
		{
			System.out.println("Book a  Flight screen appears");
		}
		else
		{
			System.out.println("Book a  Flight screen not appears");
		}
		driver.findElement(By.name("passFirst0")).sendKeys("John");
		
		driver.findElement(By.name("passLast0")).sendKeys("Peter");
		
		
		driver.findElement(By.name("creditnumber")).sendKeys("123456");
		WebElement Element1 = driver.findElement(By.name("buyFlights"));
		js.executeScript("arguments[0].scrollIntoView();", Element1);
		Element1.click();
		//Thread.sleep(2000L);
		driver.close();
		
		
		
		
		
	}

}
