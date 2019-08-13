package practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {

	private WebDriver driver;
	//private //logger //log = //loggerHelper.get//logger(WaitHelper.class);
	
	public WaitHelper(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void setImplicitWait(long timeout, TimeUnit unit)
	{
	//	//log.info("the implicit time has been changed to " + timeout + " " + unit.toString());
		driver.manage().timeouts().implicitlyWait(timeout, unit);
	}
	
	public void setPageLoadTimeOut(long timeout, TimeUnit unit)
	{
		//log.info("the page load time has been changed to " + timeout + " " + unit.toString());
		driver.manage().timeouts().pageLoadTimeout(timeout, unit);
	}
	
	private WebDriverWait getWait(long timeOutInSeconds, long pollingDurationInMiliSec)
	{
		//log.info("the explicit time applied is " + timeOutInSeconds + " with polling in every " + pollingDurationInMiliSec);
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.pollingEvery(pollingDurationInMiliSec, TimeUnit.MILLISECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(NoSuchElementException.class);
		return wait;
	}

	
	private Wait<WebDriver> getfluentWait(long timeOutInSeconds, long pollingEveryInMiliSec) {
		Wait<WebDriver> fWait = new FluentWait<WebDriver>(driver)
				.withTimeout(timeOutInSeconds, TimeUnit.SECONDS)
				.pollingEvery(pollingEveryInMiliSec, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);
		return fWait;
	}
	
	
	public void waitForElementVisible(WebElement ele, long timeOutInSeconds, long pollingDurationInMiliSec)
	{
		//log.info("the explicit time applied for element visibility is " + timeOutInSeconds + " with polling in every " + pollingDurationInMiliSec);
		WebDriverWait wait = getWait(timeOutInSeconds, pollingDurationInMiliSec);
		wait.until(ExpectedConditions.visibilityOf(ele));
		//log.info("the element is visible now");
	}
	
	public void waitForURLContainsSpecificText(String text, long timeOutInSeconds, long pollingDurationInMiliSec)
	{
		//log.info("the explicit time applied for URL text search is " + timeOutInSeconds + " with polling in every " + pollingDurationInMiliSec);
		WebDriverWait wait = getWait(timeOutInSeconds, pollingDurationInMiliSec);
		wait.until(ExpectedConditions.urlContains(text));
	}
	
	public void waitForElementClickable(WebElement ele, long timeOutInSeconds, long pollingDurationInMiliSec)
	{
		//log.info("the explicit time applied for element visibility is " + timeOutInSeconds + " with polling in every " + pollingDurationInMiliSec);
		WebDriverWait wait = getWait(timeOutInSeconds, pollingDurationInMiliSec);
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		//log.info("the element is clickable now");
	}
	
	public void waitForElementNotPresent(WebElement ele, long timeOutInSeconds, long pollingDurationInMiliSec)
	{
		//log.info("the explicit time applied for element visibility is " + timeOutInSeconds + " with polling in every " + pollingDurationInMiliSec);
		WebDriverWait wait = getWait(timeOutInSeconds, pollingDurationInMiliSec);
		wait.until(ExpectedConditions.invisibilityOf(ele));
		//log.info("the element is present now");
	}
	
	public WebElement waitForElement(By loc, long timeOutInSeconds, long pollingDurationInMiliSec)
	{
		//log.info("waiting for the element " + element.toString() + "for time : " + timeOutInSeconds );
		Wait<WebDriver> fwait = getfluentWait(timeOutInSeconds, pollingDurationInMiliSec);
		return fwait.until(ExpectedConditions.visibilityOfElementLocated(loc));
	}
	
}
