package Native;

import io.appium.java_client.MobileElement;
import io.appium.java_client.NetworkConnectionSetting;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.SAXException;

import com.applitools.eyes.Eyes;
import com.google.common.base.Function;


public class DroidMethods {
	
	AndroidDriver<MobileElement> driver;
	
	DroidElements droidData;
	
	DroidMethods genMeth;

//	public DroidMethods (){}

	public void cleanLoginAndroid(DroidMethods genMeth,  DroidElements droidData , String user) throws ParserConfigurationException, SAXException, IOException,InterruptedException {
			
		
		//Check language making sure keyboard is set to English
		
		// Make sure that the English keyboard is open
			
		// Make sure that the email & password fields are empty
		
		
		genMeth.sendId( genMeth, droidData.TEXTFIELDemailID, droidData.User);
		genMeth.sendId( genMeth, droidData.TEXTFIELDpasswordID, droidData.password);
		genMeth.clickId( genMeth, droidData.BTNloginID);
		genMeth.clickId(genMeth, droidData.IconHome_ID);
		

	}
	
	
	public void eyesCheckWindow(Eyes eyes, String testName, Boolean useEye) throws InterruptedException{
		
		if (useEye){
		eyes.setApiKey("Hbh6716cKDCgn8a9bMAREPM105nbW109PQe0993So5GwFpNM110");
		eyes.open(driver, "Droid_SG", testName);
		//eyes.setMatchTimeout(5);
		eyes.checkWindow("Origin Screen");
		eyes.close();
		
		}
		
}	

	
	public void killAppAndroid(AndroidDriver<MobileElement> driver)throws InterruptedException, IOException {

	//	driver.removeApp("com.pogoplug.android");
		driver.resetApp();
		//driver.removeApp(bundleId);
				
		try {
			driver.quit();
		} catch (Exception x) {
			// swallow exception
		}
		//driver = genMeth.setCapabilitiesIos();
	}
	

	public void signOutFromStartup(DroidMethods genMeth, DroidElements droidData) throws InterruptedException, IOException {
		genMeth.clickId(genMeth, droidData.BTNweeklyOperationsID);
		genMeth.clickName(genMeth, droidData.BTNlogoutName);
		
	}
	
	public void scroll(AndroidDriver<?> driver, String direction) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Map<String, String> scrollMap = new HashMap<String, String>();
		scrollMap.put("direction", direction);
		js.executeScript("mobile: scroll", scrollMap);
	}
 
	public void scrollUp(AndroidDriver<?> driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Map<String, String> scrollMap = new HashMap<String, String>();
		scrollMap.put("direction", "up");
		js.executeScript("mobile: scroll", scrollMap);
	}
    
	public void scrollDown(AndroidDriver<?> driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Map<String, String> scrollMap = new HashMap<String, String>();
		scrollMap.put("direction", "down");
		js.executeScript("mobile: scroll", scrollMap);
	}
 
	public AndroidDriver<MobileElement> setCapabilitiesAndroid(DroidMethods genMeth)
			throws IOException {
		
		// Login with an existing account
 
		DesiredCapabilities capabilities =  DesiredCapabilities.android();
		capabilities.setCapability("appium-version", genMeth.getValueFromPropFile("appiumVersion"));
		capabilities.setCapability("platformName", genMeth.getValueFromPropFile("platformName"));
		capabilities.setCapability("platformVersion", genMeth.getValueFromPropFile("platformVersion"));
		capabilities.setCapability("deviceName", genMeth.getValueFromPropFile("deviceName"));
		capabilities.setCapability("app",genMeth.getValueFromPropFile("appPath"));
//		capabilities.setCapability("app",AppPath);

		capabilities.setCapability("appPackage", genMeth.getValueFromPropFile("appPackage"));
		capabilities.setCapability("appActivity", genMeth.getValueFromPropFile("appLauncherActivity"));

		try {

			driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
		}

		catch (MalformedURLException e) {

			genMeth.takeScreenShot(driver, genMeth,"Faliled to open Appium driver");
			org.testng.Assert.fail("WebElement"+ " Faliled to open Appium driver");
		}
		return driver;
	}
	

	
	public String getValueFromPropFile(String key) {
		Properties properties = new Properties();
		String value = "";
		try {
			
			properties.load(getClass().getResourceAsStream("/resources/config.properties"));
			//properties.load(getClass().getResourceAsStream("/resources/webui.properties"));
			{
				value = properties.getProperty(key);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return value;
	}

	public void takeScreenShot(AndroidDriver<MobileElement> driver,
			DroidMethods genMeth, String imageName) throws IOException {

		File scrFile = (driver.getScreenshotAs(OutputType.FILE));
		String currentTime = genMeth.currentTime();
		String currentDate = genMeth.currentDate();

		// Now you can do whatever you need to do with it, for example copy somewhere
		String imagePath = genMeth.getValueFromPropFile("screenshotPath")  + currentDate + "/" + currentTime + "_" + imageName + ".JPG";
		FileUtils.copyFile(scrFile, new File(imagePath));

	}
	
	public void takeScreenShotPositive(DroidMethods genMeth, String imageName) throws IOException {
		String currentTime = genMeth.currentTime();
		File scrFile = (driver.getScreenshotAs(OutputType.FILE));
		String currentDate = genMeth.currentDate();

		// Now you can do whatever you need to do with it, for example copy somewhere
		String imagePath = genMeth.getValueFromPropFile("screenshotPathPositive")  + currentDate +  "/" + currentTime + "_" + imageName + ".JPG";
		FileUtils.copyFile(scrFile, new File(imagePath));

	}




	/*
	 * ***************************************************
	 * Web Element Handling *
	 * ***************************************************
	 */

	// ==================== RETURN ELEMENT

		public WebElement returnCss(AndroidDriver<MobileElement> driver, String cssSelector)
			throws InterruptedException {

		DroidMethods genMeth = new DroidMethods();
		try {

			genMeth.fluentwait(driver, By.cssSelector(cssSelector));

		}

		catch (Exception e) {

			org.testng.Assert.fail("WebElement 'by css' can't be located");

		}

		WebElement myElement = genMeth.fluentwait(driver,
				By.cssSelector(cssSelector));
		return myElement;
	}

	public WebElement returnId(AndroidDriver<MobileElement> driver,DroidMethods genMeth, String id)
			throws InterruptedException {


		try {

			genMeth.fluentwait(driver, By.id(id));

		}

		catch (Exception e) {

			org.testng.Assert.fail(id + " didn't display");

		}

		WebElement myElement = genMeth.fluentwait(driver, By.id(id));
		return myElement;

	}

	public WebElement returnClassName(AndroidDriver<MobileElement> driver, DroidMethods genMeth,  String className)
			throws InterruptedException {


		try {

			genMeth.fluentwait(driver, By.className(className));
		}

		catch (Exception e) {

			org.testng.Assert.fail(className + " didn't display");

		}

		WebElement myElement = genMeth.fluentwait(driver,
				By.className(className));
		return myElement;
	}

	public WebElement returnXpth(AndroidDriver<MobileElement> driver, DroidMethods genMeth, String xpth)
			throws InterruptedException {

		try {

			genMeth.fluentwait(driver, By.xpath(xpth));

		}

		catch (Exception e) {

			org.testng.Assert.fail(xpth + " didn't display");
		}

		WebElement myElement = genMeth.fluentwait(driver, By.xpath(xpth));
		return myElement;

	}

	public WebElement returnName(AndroidDriver<MobileElement> driver, DroidMethods genMeth, String name)
			throws InterruptedException {

		try {

			genMeth.fluentwait(driver, By.name(name));

		}

		catch (Exception e) {

			org.testng.Assert.fail(name + " didn't display");

		}

		WebElement myElement = genMeth.fluentwait(driver, By.name(name));
		return myElement;

	}
	
	public WebElement returnBy(AndroidDriver<MobileElement> driver, DroidMethods genMeth, By by)
			throws InterruptedException {

		try {

			genMeth.fluentwait(driver, by);

		}

		catch (Exception e) {

			org.testng.Assert.fail(by + " didn't display");

		}

		WebElement myElement = genMeth.fluentwait(driver, by);
		return myElement;

	}

	// ========= CLICK an ELEMENT =========================================================================

	public void clickBy(AndroidDriver<MobileElement> driver, DroidMethods genMeth, By by) throws InterruptedException {


		try {

			WebElement myElement = genMeth.fluentwait(driver, by);
			myElement.click();
		}

		catch (Exception e) {

			org.testng.Assert.fail("WebElement can't be located");

		}

	}
	
	
	public void tapBy(AndroidDriver<MobileElement> driver, DroidMethods genMeth, By by) throws InterruptedException {


		try {

			WebElement myElement = genMeth.fluentwait(driver, by);
			driver.tap(1, myElement, 1000);
		}

		catch (Exception e) {

			org.testng.Assert.fail("WebElement can't be located");

		}

	}

	public void clickCss(AndroidDriver<MobileElement> driver, DroidMethods genMeth, String cssSelector)
			throws InterruptedException {

		try {

			WebElement myElement = genMeth.fluentwait(driver,
					By.cssSelector(cssSelector));
			myElement.click();

		}

		catch (Exception e) {

			org.testng.Assert.fail(cssSelector + " didn't display");

		}

	}

	public void clickId( DroidMethods genMeth,
			String id) throws InterruptedException {

		try {

			WebElement myElement = genMeth.fluentwait(driver, By.id(id));
			myElement.click();
		}

		catch (Exception e) {

			org.testng.Assert.fail(id + " didn't display");

		}
	}
	
	public void tapId( DroidMethods genMeth,
			String id) throws InterruptedException {

		try {

			WebElement myElement = genMeth.fluentwait(driver, By.id(id));
			driver.tap(1, myElement, 1000);

		}

		catch (Exception e) {

			org.testng.Assert.fail(id + " didn't display");

		}
	}

	public void clickClassName(AndroidDriver<MobileElement> driver, DroidMethods genMeth, String className)
			throws InterruptedException {

		try {

			genMeth.fluentwait(driver, By.className(className)).click();

		}

		catch (Exception e) {

			org.testng.Assert.fail(className + " didn't display");

		}

	}
	

	public void clickXpth(AndroidDriver<MobileElement> driver, DroidMethods genMeth, String xpth)
			throws InterruptedException, IOException {

		By by = By.xpath(xpth);

		try {

			WebElement myElement = genMeth.fluentwait(driver, by);
			myElement.click();

		}

		catch (Exception e) {
			genMeth.takeScreenShot(driver, genMeth, xpth);
			org.testng.Assert.fail(xpth + " didn't display");

		}

	}
	
	public void tapXpth( DroidMethods genMeth, String xpth)
			throws InterruptedException, IOException {

		By by = By.xpath(xpth);

		try {

			WebElement myElement = genMeth.fluentwait(driver, by);
			driver.tap(1, myElement, 1000);
		}

		catch (Exception e) {
			genMeth.takeScreenShot(driver, genMeth, xpth);
			org.testng.Assert.fail(xpth + " didn't display");

		}

	}

	public void clickName( DroidMethods genMeth, String name )
			throws InterruptedException, IOException {

		try {

			WebElement myElement = genMeth.fluentwait(driver, By.name(name));
			myElement.click();

		}

		catch (Exception e) {
			// String testName = new
			// Object(){}.getClass().getEnclosingMethod().getName();
			genMeth.takeScreenShot(driver, genMeth, name);
			org.testng.Assert.fail(name + " didn't display");

		}

	}
	
	public void tapName(DroidMethods genMeth, String name)
			throws InterruptedException, IOException {

		try {

			WebElement myElement = genMeth.fluentwait(driver, By.name(name));
			driver.tap(1, myElement, 1000);

		}

		catch (Exception e) {
			// String testName = new
			// Object(){}.getClass().getEnclosingMethod().getName();
			genMeth.takeScreenShot(driver, genMeth, name);
			org.testng.Assert.fail(name + " didn't display");

		}

	}

// ======================== SEND ELEMENT =========================================

	public void sendBy(AndroidDriver<MobileElement> driver, DroidMethods genMeth, By by, String send)
			throws InterruptedException, IOException {

		try {

			WebElement myElement = genMeth.fluentwait(driver, by);
			myElement.sendKeys(send);

		}

		catch (Exception e) {

			genMeth.takeScreenShot(driver, genMeth, send);
			org.testng.Assert.fail("WebElement'send by' can't be located");

		}

	}

	public void sendCss(AndroidDriver<MobileElement> driver, DroidMethods genMeth,
			String cssSelector, String send) throws InterruptedException {

		try {

			WebElement myElement = genMeth.fluentwait(driver,
					By.cssSelector(cssSelector));
			myElement.sendKeys(send);
		}

		catch (Exception e) {

			org.testng.Assert.fail("Css selector " + cssSelector
					+ " can't be located");

		}

	}

	public void sendId( DroidMethods genMeth, String id, String send)
			throws InterruptedException, IOException {

		try {

			WebElement myElement = genMeth.fluentwait(driver, By.id(id));
			myElement.sendKeys(send);

		}

		catch (Exception e) {

			genMeth.takeScreenShot(driver, genMeth, send);
			org.testng.Assert.fail(id + "didn't displayed");

		}

	}

	public void sendClassName(AndroidDriver<MobileElement> driver, DroidMethods genMeth, String className, String send)
			throws InterruptedException {

		try {

			genMeth.fluentwait(driver, By.className(className)).sendKeys(send);

		}

		catch (Exception e) {

			org.testng.Assert.fail(className + "didn't displayed");

		}

	}

	public void sendXpth(AndroidDriver<MobileElement> driver, DroidMethods genMeth, String xpth, String send)
			throws IOException {

		try {

			WebElement myElement = genMeth.fluentwait(driver, By.xpath(xpth));
			myElement.sendKeys(send);

		}

		catch (Exception e) {

			genMeth.takeScreenShot(driver, genMeth, xpth);
			org.testng.Assert.fail(xpth + "didn't displayed");

		}

	}

	public void sendName( DroidMethods genMeth, String name, String send)
			throws IOException {


		try {

			WebElement myElement = genMeth.fluentwait(driver, By.xpath(name));
			myElement.sendKeys(send);
		}

		catch (Exception e) {

			genMeth.takeScreenShot(driver, genMeth, name);
			org.testng.Assert.fail(name + "didn't displayed");

		}

	}

	// =========================Clear WebElements=====================================================================

	public void clearXpth(AndroidDriver<MobileElement> driver, DroidMethods genMeth, String xpath)
			throws InterruptedException {

		try {

			WebElement myElement = genMeth.fluentwait(driver, By.xpath(xpath));
			myElement.clear();

		}

		catch (Exception e) {

			org.testng.Assert.fail(xpath + "didn't displayed");

		}

	}

	public void clearClassName(AndroidDriver<MobileElement> driver, DroidMethods genMeth, String className)
			throws InterruptedException {

		try {

			WebElement myElement = genMeth.fluentwait(driver,
					By.className(className));
			myElement.clear();

		}

		catch (Exception e) {

			org.testng.Assert.fail(className + "didn't displayed");

		}

	}

	public void clearId( DroidMethods genMeth, String id)
			throws InterruptedException {

		try {

			WebElement myElement = genMeth.fluentwait(driver, By.id(id));
			myElement.clear();

		}

		catch (Exception e) {

			org.testng.Assert.fail(id + "didn't displayed");

		}

	}

	public void clearCss(AndroidDriver<MobileElement> driver, DroidMethods genMeth, String cssSelector)
			throws InterruptedException {

		try {

			WebElement myElement = genMeth.fluentwait(driver,
					By.cssSelector(cssSelector));
			myElement.clear();

		}

		catch (Exception e) {

			org.testng.Assert.fail(cssSelector + " can't be located");
		}

	}

	/*
	 * ******************************************************************************
	 * Avoid the Element not found exception *
	 * ***********************************
	 * *******************************************
	 */

	// Look for an element in a few tries (with counter)
	public void waitForElementToBeInvisible(AndroidDriver<MobileElement> driver, By byType,
			int numAttempts) throws IOException, ParserConfigurationException,SAXException {

		int count = 0;
		Boolean isInvisible = false;
		while (count < numAttempts) {

			try {
				isInvisible = new FluentWait<AndroidDriver<MobileElement>>(driver)
						.withTimeout(60, TimeUnit.SECONDS)
						.pollingEvery(5, TimeUnit.SECONDS)
						.ignoring(NoSuchElementException.class)
						.until(ExpectedConditions
								.invisibilityOfElementLocated(byType));

				if (isInvisible == true) {

					count = numAttempts;

				}

			}

			catch (Exception e) {
				count++;

			}

		}

		if (isInvisible == false) {
			DroidMethods genMeth = new DroidMethods();
			// str = new genData();
			String imageName = "Element isn't Invisible";
			genMeth.takeScreenShot(driver, genMeth, imageName);
			org.testng.Assert.fail("WebElement" + " is not Invisible");
		}

	}

	public void waitForElementToBeVisible(AndroidDriver<MobileElement> driver, By By,int numAttempts) 
			throws IOException, ParserConfigurationException,SAXException {
		
		DroidMethods genMeth = new DroidMethods();
		int count = 0;
		WebElement elementToBeVisible = null;
		while (count < numAttempts) {
			try {
				elementToBeVisible = new FluentWait<AndroidDriver<MobileElement>>(driver)
						.withTimeout(60, TimeUnit.SECONDS)
						.pollingEvery(5, TimeUnit.SECONDS)
						.ignoring(NoSuchElementException.class)
						.until(ExpectedConditions.elementToBeClickable(By));

				if (elementToBeVisible != null) {

					count = numAttempts;

				}

			}

			catch (Exception e) {
				count++;
//				genMeth.takeScreenShot (driver, genMeth, "Elelement not visible");
			}

		}

		if (elementToBeVisible == null) {
			String imageName = "Element isn't Visible";
			genMeth.takeScreenShot(driver, genMeth, imageName);
			org.testng.Assert.fail("WebElement" + " is not Visible");
		}

	}
	
	@SuppressWarnings("rawtypes")
	public MobileElement fluentwait(AndroidDriver driver, final By byType) {
		Wait<AndroidDriver> wait = new FluentWait<AndroidDriver>(driver)
				.withTimeout(45, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);

		MobileElement foo = (MobileElement) wait.until(new Function<AndroidDriver, WebElement>() {
			public MobileElement apply(AndroidDriver driver) {
				return (MobileElement) driver.findElement(byType);
			}
		});

		wait.until(ExpectedConditions.elementToBeClickable(byType));

		return foo;
	}

	public void isTextPresentAndroid(AndroidDriver<MobileElement> driver, By By, String text)
			throws IOException, ParserConfigurationException, SAXException,
			InterruptedException {

		// boolean isStartUpPageOpenIOS = false;

		try {
			new FluentWait<AndroidDriver<MobileElement>>(driver)
					.withTimeout(45, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class)
					.until(ExpectedConditions.textToBePresentInElementLocated(
							By, text));
		}

		catch (Exception e) {

			DroidMethods genMeth = new DroidMethods();
			// genData str = new genData();
			String imageName = text + " is invisible";
			genMeth.takeScreenShot(driver, genMeth, imageName);
			org.testng.Assert.fail(text + " isn't visible");
		}

		// return isStartUpPageOpenIOS;

	}

	public boolean checkIsTextPresentNative(AndroidDriver<MobileElement> driver, String text,
			By by) throws IOException, ParserConfigurationException,SAXException, InterruptedException {

		boolean isTextPresent = false;

		try {
			isTextPresent = new FluentWait<AndroidDriver<MobileElement>>(driver)
					.withTimeout(5, TimeUnit.SECONDS)
					.pollingEvery(1, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class)
					.until(ExpectedConditions.textToBePresentInElementLocated(by, text));
		}

		catch (Exception e) {

			// nothing to do here
		}

		return isTextPresent;

	}

	// This method checks if a given element is invisible on the screen

	public void isElementInvisible( By By)
			throws ParserConfigurationException, SAXException, IOException {

		try {

			(new WebDriverWait(driver, 45)).until(ExpectedConditions
					.invisibilityOfElementLocated(By));

		}

		catch (Exception e) {

			DroidMethods genMeth = new DroidMethods();
			String imageName = " Element is visible";
			genMeth.takeScreenShot(driver, genMeth, imageName);
			org.testng.Assert.fail("WebElement" + " still visible");

		}

	}

	public void isElementVisible( By By)
			throws ParserConfigurationException, SAXException, IOException {

		try {

			// (new WebDriverWait(driver,
			// 20)).until(ExpectedConditions.visibilityOfElementLocated(by));
			new FluentWait<AndroidDriver<MobileElement>>(driver)
					.withTimeout(30, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class)
					.until(ExpectedConditions.visibilityOfElementLocated(By));

		}

		catch (Exception e) {
			DroidMethods genMeth = new DroidMethods();
			String imageName = "Element is invisible";
			genMeth.takeScreenShot(driver, genMeth, imageName);
			org.testng.Assert.fail("WebElement" + " is not visible");

		}

	}

	public boolean checkIsElementVisible( By By )
			throws ParserConfigurationException, SAXException, IOException {

		boolean isWebElementVisible = false;
		WebElement element = null;
		try {

			// (new WebDriverWait(driver,
			// 20)).until(ExpectedConditions.visibilityOfElementLocated(by));
			element = new FluentWait<AndroidDriver<MobileElement>>(driver)
					.withTimeout(45, TimeUnit.SECONDS)
					.pollingEvery(1, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class)
					.until(ExpectedConditions.visibilityOfElementLocated(By));

		}
		
		

		catch (Exception e) {

			// GenericMethods genMeth = new GenericMethods();
			// genData str = new genData();
			// String imageName = str.screenShotPath + " Element is invisible "
			// + genMeth.currentTime() + ".png";
			// genMeth.takeScreenShotNative(driver, imageName );
			// org.testng.Assert.fail("WebElement" + " is not visible");

		}
		if (element != null) {
			return isWebElementVisible = true;
		}

		else {
			return isWebElementVisible;

		}

	}
	
	public boolean fastCheckIsElementVisible( By By )
			throws ParserConfigurationException, SAXException, IOException {

		boolean isWebElementVisible = false;
		WebElement element = null;
		try {

			// (new WebDriverWait(driver,
			// 20)).until(ExpectedConditions.visibilityOfElementLocated(by));
			element = new FluentWait<AndroidDriver<MobileElement>>(driver)
					.withTimeout(5, TimeUnit.SECONDS)
					.pollingEvery(1, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class)
					.until(ExpectedConditions.visibilityOfElementLocated(By));

		}
		
		

		catch (Exception e) {


		}
		if (element != null) {
			return isWebElementVisible = true;
		}

		else {
			return isWebElementVisible;

		}

	}


	public void isElementInvisibleText( By By, String Text) throws ParserConfigurationException,
			SAXException, IOException {

		try {

			(new WebDriverWait(driver, 45)).until(ExpectedConditions
					.invisibilityOfElementWithText(By, Text));

		}

		catch (Exception e) {

			DroidMethods genMeth = new DroidMethods();
			//String imageName = genMeth.getValueFromPropFile(key) + text + " still visible "
				//	+ genMeth.currentTime() + ".png";
			genMeth.takeScreenShot(driver, genMeth, Text);
			org.testng.Assert.fail(Text + " still visible");

		}

	}

	public final class SessionIdentifierGenerator {
		private SecureRandom random = new SecureRandom();

		public String nextSessionId() {

			return new BigInteger(130, random).toString(32);

		}

	};

	// Creates a Random string
	public String randomString() {

		String text;
		SessionIdentifierGenerator x = new SessionIdentifierGenerator();
		text = x.nextSessionId();
		return text;
	}

	// Create a string with current date
	public String currentDate() {

		String curDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		return curDate;
	}

	public String currentTime() {

		// String curDate = new
		// SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
		String curDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

		return curDate;
	}


	public void backgroundToForeground(AndroidDriver<?> driver, int numOfTimes) {

		for (int count = 0; count < numOfTimes; count++) {

			driver.runAppInBackground(2);

		}

	}

	public void lockUnlock(AndroidDriver<?> driver, int numOfTimes) {

		for (int count = 0; count < numOfTimes; count++) {

			driver.lockScreen(2);

		}

	}
	
	public void longPressElement(AndroidDriver<MobileElement> driver, DroidMethods genMeth, By By){
				TouchAction action;
				WebElement el;
				try {
					action = new TouchAction(driver); 
					el = genMeth.returnBy(driver, genMeth, By);
					action.longPress(el).waitAction(2000).release().perform();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
		
	}
	
//	public void changeConnectionType(String mode) {
//
//		NetworkConnection mobileDriver = (NetworkConnection) driver;
//		if (mode == "AIRPLANE_MODE") {
//
//			mobileDriver.setNetworkConnection(NetworkConnection.ConnectionType.AIRPLANE_MODE);
//		}
//
//		else if (mode == "WIFI") {
//
//			mobileDriver.setNetworkConnection(NetworkConnection.ConnectionType.WIFI);
//
//		}
//
//		else if (mode == "DATA") {
//
//			mobileDriver.setNetworkConnection(NetworkConnection.ConnectionType.DATA);
//
//		}
//
//		else if (mode == "ALL") {
//
//			mobileDriver.setNetworkConnection(NetworkConnection.ConnectionType.ALL);
//
//		}
//		
//	}
//	
	public void setAirplainMode(){
		
		driver.setNetworkConnection(new NetworkConnectionSetting(true,false,false));

	}
	
public void setWifiOn(){
	
		driver.setNetworkConnection(new NetworkConnectionSetting(false,true,false));

	}
	

	public void pressHomeButton(){
		int Home = AndroidKeyCode.HOME;
		//driver.sendKeyEvent(Home);
		driver.pressKeyCode(Home);
		
		
	}
	
	public void backButton(){
		int Back = AndroidKeyCode.BACK;
		//driver.sendKeyEvent(Back);
		driver.pressKeyCode(Back);
		
	}
	
	public void pressEnter(){
		int Enter = AndroidKeyCode.ENTER;
		driver.pressKeyCode(Enter);

	}
	
	
	public void setOrientationLandscape() {

		driver.rotate(ScreenOrientation.LANDSCAPE);

	}
	
	public void setOrientationPortrait() {

		driver.rotate(ScreenOrientation.PORTRAIT);

	}
	
	
}
