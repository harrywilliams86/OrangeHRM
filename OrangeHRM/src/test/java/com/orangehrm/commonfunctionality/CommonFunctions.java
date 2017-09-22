package com.orangehrm.commonfunctionality;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.orangehrm.utilities.PropertiesUtil;

public class CommonFunctions
{
	public static WebDriver startBrowser(WebDriver driver) throws Exception, Exception
	{
		if(PropertiesUtil.getValueForKey("Browser").equalsIgnoreCase("Chrome"))
		{
			System.out.println(PropertiesUtil.getValueForKey("Browser"));
			System.setProperty("webdriver.chrome.driver", "//Users//williamsharry//Documents//workspace//chromedriver");
			driver = new ChromeDriver();
		}
		return driver;
	}
	public static void openApplication(WebDriver driver) throws Exception, Exception
	{
		driver.manage().window().maximize();
		driver.get(PropertiesUtil.getValueForKey("URL"));
	}
	public static void clickAction(WebDriver driver, String locatorType, String locatorValue)
	{
		if(locatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorValue)).click();
		}
		else 
			if(locatorType.equalsIgnoreCase("xpath"))
				{
					driver.findElement(By.xpath(locatorValue)).click();
				}
		else 
			if(locatorType.equalsIgnoreCase("name"))
				{
					driver.findElement(By.name(locatorValue)).click();
				}
	}
	public static void typeAction(WebDriver driver, String locatorType, String locatorValue, String data)
	{
		if(locatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorValue)).clear();
			driver.findElement(By.id(locatorValue)).sendKeys(data);
		}
		else
			if(locatorType.equalsIgnoreCase("name"))
			{
				driver.findElement(By.name(locatorValue)).clear();
				driver.findElement(By.name(locatorValue)).sendKeys(data);
			}
		else
			if(locatorType.equalsIgnoreCase("xpath"))
			{
				driver.findElement(By.xpath(locatorValue)).clear();
				driver.findElement(By.xpath(locatorValue)).sendKeys(data);
			}
	}
	public static void waitForElement(WebDriver driver, String locatorType, String locatorValue, String time)
	{
		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(time));
		if(locatorType.equalsIgnoreCase("id"))
		{
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(locatorValue))));
		}
		else 
			if(locatorType.equalsIgnoreCase("name"))
			{
				wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name(locatorValue))));
			}
		else
			if(locatorType.equalsIgnoreCase("xpath"))
			{
				wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(locatorValue))));
			}
	}
	
	public static void titleValidation(WebDriver driver, String expdata)
	{
		String acttitle = driver.getTitle();
		String exptitle = expdata;
		Assert.assertEquals(acttitle, exptitle);
	}
	
	public static void clickLogout(WebDriver driver) throws InterruptedException
	{
		driver.findElement(By.linkText("Welcome Rasool")).click();
		Actions act = new Actions(driver);
		Thread.sleep(2000);
		act.click(driver.findElement(By.xpath(".//*[@id='welcome-menu']/ul/li[3]/a"))).build().perform();
	}
	public static void closeApplication(WebDriver driver)
	{
		driver.manage().deleteAllCookies();
		driver.quit();
	}
}
