import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;


public class TestPhantomJS {

	
	public static void main(String[] args) throws IOException {


		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setJavascriptEnabled(true);
		caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "C:\\phantomjs-2.0.0-windows\\bin\\phantomjs.exe");
		
		WebDriver driver = new PhantomJSDriver(caps);
		
		driver.get("http://www.hdfcbank.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		
		System.out.println("-----Generating window ids from first window-------");
		
		Set<String> winids = driver.getWindowHandles();
		Iterator<String> itrate = winids.iterator();
		
		String first_window = itrate.next(); 
		System.out.println(first_window);
		
		
		
		driver.findElement(By.xpath("//*[@id='loginsubmit']")).click();
		
	
	System.out.println("-----Generating window ids from second window-------");
		
		winids = driver.getWindowHandles();
		itrate = winids.iterator();
		
		System.out.println(itrate.next()); //first
		String second_window = itrate.next(); //second
		System.out.println(second_window);
		
		driver.switchTo().window(second_window);
		
		driver.findElement(By.xpath("//*[@id='wrapper']/div[2]/div[1]/ul/li[2]/a")).click();
		
		
		System.out.println(driver.findElements(By.partialLinkText("Apply Now")).size());

		
		System.out.println("-----Generating window ids from third window-------");
			
			winids = driver.getWindowHandles();
			itrate = winids.iterator();
	/*		
			while(itrate.hasNext()){
				
				System.out.println(itrate.next());
			}
			*/
			System.out.println(itrate.next()); //first
			System.out.println(itrate.next());
			String third_window = itrate.next(); //third_window
			System.out.println(third_window);
			
			driver.switchTo().window(third_window);
			System.out.println(driver.getTitle());
		
			Select select = new Select(driver.findElement(By.xpath("//*[@id='eForm_form_propertyCity_value']")));
			select.selectByVisibleText("Noida");
			
			List<WebElement> options = select.getOptions();
			
			for(WebElement dropoptions: options){
				
				System.out.println(dropoptions.getText());
				
			}
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("c:\\screenshot\\sample.jpeg"),true);  
		
		/*
		driver.close(); //current window
		driver.switchTo().window(second_window);*/
		
		driver.quit();

	}

}
