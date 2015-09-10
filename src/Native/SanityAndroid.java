package Native;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;
import org.xml.sax.SAXException;

import com.applitools.eyes.Eyes;

  public class SanityAndroid {
	
	String currentDateFolder;
	String webElementXmlLang;
	String webElementXmlPath;
	String StartServerPath;
	String StopServerPath;
	DroidElements droidData;
	String appPackage;
	public AndroidDriver driver;
	DroidMethods genMeth = new DroidMethods();
	Eyes eyes = new Eyes();
	Boolean useEye = true;

	
	public SanityAndroid() {
		// TODO Auto-generated constructor stub
	}

	@BeforeSuite(alwaysRun = true)
	public void setupBeforeSuite(ITestContext context) throws ParserConfigurationException, SAXException, IOException, InterruptedException, jdk.internal.org.xml.sax.SAXException {
		
        // This is your api key, make sure you use it in all your tests.
		
		//Set the tests configuration
		StartServerPath = genMeth.getValueFromPropFile("StartServerPath");
		StopServerPath = genMeth.getValueFromPropFile("StopServerPath");
		webElementXmlPath = genMeth.getValueFromPropFile("webElementXmlPath");
		webElementXmlLang = genMeth.getValueFromPropFile("webElementXmlLang");
		appPackage = genMeth.getValueFromPropFile("com.skygiraffe.operationaldata"); 
		
		droidData= new DroidElements(webElementXmlLang, webElementXmlPath);
		driver = genMeth.setCapabilitiesAndroid(genMeth);
		genMeth.cleanLoginAndroid(genMeth,droidData, droidData.User); 
		
	}

	@BeforeMethod (alwaysRun = true)
	public void checkHomeScreen() throws InterruptedException, IOException, ParserConfigurationException, SAXException{

//		genMeth.setWifiOn();

		// Check if the client still logged in & in StartUp screen before each test
		if (driver == null) {
			try {
				driver.removeApp(genMeth.getValueFromPropFile("appPackage"));
				driver.quit();
			} catch (Exception e) {
				// swallow if fails
			}
			driver = genMeth.setCapabilitiesAndroid(genMeth);
			genMeth.cleanLoginAndroid( genMeth,droidData, droidData.User );
		}

		else {
			boolean StartUpScreenDisplay = genMeth.checkIsElementVisible( By.id(droidData.BTNweeklyOperationsID));

			if (StartUpScreenDisplay != true) {

				try {
					driver.removeApp(genMeth.getValueFromPropFile("appPackage"));
					driver.quit();
				} catch (Exception e) {
					// swallow if fails
				}

				driver = genMeth.setCapabilitiesAndroid(genMeth);
				genMeth.cleanLoginAndroid( genMeth, droidData, droidData.User);

			}

		}

	}
	
	@AfterMethod(enabled = false, dependsOnMethods = { "connectionLost" })
	public void enabledWifi() {

		genMeth.setWifiOn();
	}

	@Test (enabled = true ,testName = "test the sample application", retryAnalyzer = Retry.class, description = "Test the login via the sample button" ,
			groups= {"Sanity Android"}  /*dependsOnMethods={"testLogin"}*/)	
	public void loginSample() throws ParserConfigurationException,
			SAXException, IOException, InterruptedException {

		driver.scrollToExact(droidData.BTNlogout_Name);
		genMeth.clickName(genMeth, droidData.BTNlogout_Name );
		genMeth.clickId(genMeth, droidData.BTNsampleAccountID);
		genMeth.eyesCheckWindow(eyes, "Droid_loginSample Main Screen", useEye );
		
		// Login to sample app & open Dashboard report
		genMeth.clickId(genMeth, "com.skygiraffe.operationaldata:id/main_report_list_item_icon_tv");
		genMeth.eyesCheckWindow(eyes, "Droid_loginSample Dashboard Tab", useEye);
		driver.scrollToExact("SALES");
		genMeth.clickName(genMeth,  droidData.Dashboard_Name);
		//genMeth.clickId(genMeth, "android:id/action_bar_spinner");
		genMeth.clickName(genMeth, "World wide orders");
		genMeth.eyesCheckWindow(eyes, "Droid_loginSample World wide orders Tab", useEye);
		genMeth.backButton();

		// Open Sales Bar
		genMeth.clickName(genMeth, "Daily Sales");
		genMeth.eyesCheckWindow(eyes, "Droid_SampleApp- Daily Sales Bar- Show All", useEye);
		genMeth.setOrientationLandscape();
		genMeth.eyesCheckWindow(eyes, "Droid_SampleApp- Daily Sales Bar- Show All- Landscape", useEye);
		genMeth.setOrientationPortrait();
		
		genMeth.clickName(genMeth, "Returns");
		genMeth.eyesCheckWindow(eyes, "Droid_SampleApp- Daily Sales Bar- show Sales/Net Sales", useEye);
		
		genMeth.clickName(genMeth, "Sales");
		genMeth.eyesCheckWindow(eyes, "Droid_SampleApp- show Net Sales", useEye);
	
		genMeth.clickName(genMeth, "Returns");
		genMeth.clickName(genMeth, "Sales");
		genMeth.eyesCheckWindow(eyes, "Droid_SampleApp- Daily Sales Bar- Show All", useEye);
		
		//Open Sales Pie
		genMeth.clickName(genMeth, "Daily Sales");
		genMeth.clickName(genMeth, "Daily sales - Pie");
		genMeth.eyesCheckWindow(eyes, "Droid_SampleApp- Daily Sales Pie- Sales", useEye);
		genMeth.clickName(genMeth, "Destiny USA");
		genMeth.eyesCheckWindow(eyes, "Droid_SampleApp- Daily Sales Pie- Sales - Destiny USA", useEye);
		genMeth.clickName(genMeth, "Returns");
		genMeth.eyesCheckWindow(eyes, "Droid_SampleApp- Daily Sales Pie- Returns - Destiny USA", useEye);
		
		genMeth.clickName(genMeth, "Net Sales");
		genMeth.eyesCheckWindow(eyes, "Droid_SampleApp- Daily Sales Pie- Net Sales - Destiny USA", useEye);
		
		
		//Open Sales Sparklines
		genMeth.clickName(genMeth, "Daily Sales");
		genMeth.clickName(genMeth, "Last 12 hours");
		genMeth.eyesCheckWindow(eyes, "Droid_SampleApp- Daily Sales Last 12 Months - Sparklines", useEye);
		Thread.sleep(1000);
		
		// Check slicer in Sparklines
		genMeth.clickId(genMeth, droidData.IconSlicer_ID);
		genMeth.clickName(genMeth, "Branch");
		genMeth.clickName(genMeth, "Destiny USA");
		genMeth.clickName(genMeth, "Slicer");
		genMeth.clickName(genMeth, "Done");
		genMeth.eyesCheckWindow(eyes, "Droid_SampleApp- Sales Last 12 Months - Sparklines / Destiny USA", useEye);
		
		// Check slicer in Sparklines
		genMeth.clickId(genMeth, droidData.IconSlicer_ID);
		genMeth.clickName(genMeth, droidData.BTNclear_Name);
		genMeth.clickName(genMeth, droidData.BTNdoneName);
		genMeth.eyesCheckWindow(eyes, "Droid_SampleApp- Daily Sales Last 12 Months - Sparklines", useEye);
		genMeth.clickId(genMeth, droidData.IconHome_ID);

		/*
				
		//Open Daily Sales from main screen
				genMeth.clickId(genMeth, iosData.DailySalesID);
				genMeth.eyesCheckWindow(eyes, "SampleApp Daily Sales Bar (no back icon)- Show All", useEye);
				genMeth.clickName(genMeth, iosData.BTNBackName);
						
		//OPEN SERVICE CALLS
				genMeth.clickId(genMeth, iosData.ServiceCallsID);
				genMeth.eyesCheckWindow(eyes, "SampleApp Service Calls", useEye);
				*/
		
		
		
		
	}


	
	@Test(enabled = false, groups = { "Sanity Android" }, testName = "Sanity Tests", description = "login with bad/missing credentials", retryAnalyzer = Retry.class)
	public void badCredentials() throws Exception, Throwable {

		genMeth.signOutFromStartup(genMeth, droidData);
		// Login with bad user name
		genMeth.sendId( genMeth, droidData.TEXTFIELDemailID, "bad name");
		genMeth.sendId( genMeth, droidData.TEXTFIELDpasswordID, droidData.password);
		genMeth.clickId( genMeth, droidData.BTNloginID);
		genMeth.isElementVisible(By.name("Login Failed"));
		
		genMeth.clickId(genMeth, droidData.BTNokForErrorPopupID);

		// Login with bad password 
		genMeth.sendId( genMeth, droidData.TEXTFIELDemailID, droidData.User);
		genMeth.sendId( genMeth, droidData.TEXTFIELDpasswordID, "bad password");
		genMeth.clickId( genMeth, droidData.BTNloginID);
		genMeth.isElementVisible(By.name("Login Failed"));
		genMeth.clickId(genMeth, droidData.BTNokForErrorPopupID);

		
		// Login with bad user name & password 
		genMeth.sendId( genMeth, droidData.TEXTFIELDemailID, "bad name");
		genMeth.sendId( genMeth, droidData.TEXTFIELDpasswordID, "bad password");
		genMeth.clickId( genMeth, droidData.BTNloginID);
		genMeth.isElementVisible(By.name("Login Failed"));
		genMeth.clickId(genMeth, droidData.BTNokForErrorPopupID);

		
		// Login with empty Name
		genMeth.clearId(genMeth, droidData.TEXTFIELDemailID);
		genMeth.sendId( genMeth, droidData.TEXTFIELDpasswordID, droidData.password);
		genMeth.clickId( genMeth, droidData.BTNloginID);
		genMeth.isElementVisible(By.name("Bad Request"));
		genMeth.clickId(genMeth, droidData.BTNokForErrorPopupID);
		
		// Login with empty Password
		genMeth.sendId( genMeth, droidData.TEXTFIELDemailID, droidData.User);
		genMeth.clearId(genMeth, droidData.TEXTFIELDpasswordID);
		genMeth.clickId(genMeth, droidData.BTNloginID);
		genMeth.isElementVisible(By.name("Bad Request"));
		genMeth.clickId(genMeth, droidData.BTNokForErrorPopupID);

		// Login with empty Name & password
		genMeth.clearId(genMeth, droidData.TEXTFIELDemailID);
		genMeth.clearId(genMeth, droidData.TEXTFIELDpasswordID);
		genMeth.clickId(genMeth, droidData.BTNloginID);
		genMeth.isElementVisible(By.name("Bad Request"));
		genMeth.clickId(genMeth, droidData.BTNokForErrorPopupID);
		
		// Forgot your password Negative (attempt to restore password with a non
		// existing email)

		// Forgot your password Positive (attempt to restore password with an
		// existing email)
	
	}
	
	@Test(enabled = false, groups = { "Sanity Android1" } , testName = "Sanity Tests", description = "Settings: create & restore a snapshot" )
	public void forgotYourPassword() throws Exception, Throwable {
		
		genMeth.signOutFromStartup(genMeth, droidData);
		
		//Cancel forgot password
		genMeth.clickId(genMeth, droidData.BTNforgotPasswordID);
		genMeth.isElementVisible(By.id(droidData.BTNcancelForgotPasswordID));
		genMeth.backButton();
		genMeth.eyesCheckWindow(eyes, "Recover Password", useEye);
		genMeth.clickId(genMeth, droidData.BTNcancelForgotPasswordID);
		
		//recover with invalid mail
		genMeth.clickId(genMeth, droidData.BTNforgotPasswordID);
		genMeth.clearId(genMeth, droidData.TEXTFIELDrecoveryEmailID);

		genMeth.clickId(genMeth, droidData.BTNrecoverPasswordID);
		genMeth.isElementVisible(By.name(droidData.InvalidRecoverEmailName));
		genMeth.backButton();
		genMeth.eyesCheckWindow(eyes, "Recover Password Invalid Mail", useEye);
		
		
		//recover with a valid mail
		genMeth.sendId(genMeth, droidData.TEXTFIELDrecoveryEmailID, droidData.User);
		genMeth.clickId(genMeth, droidData.BTNrecoverPasswordID);
		genMeth.isElementVisible(By.id(droidData.BTNresetPasswordID));
		genMeth.eyesCheckWindow(eyes, "Recover Password valid mail", useEye);
			
		//Attempt to reset password with incorrect confirmation code 
		genMeth.clickId(genMeth, droidData.BTNresetPasswordID);
		genMeth.isElementVisible(By.name(droidData.ConfCodeIncorrectName));
		genMeth.eyesCheckWindow(eyes, "Recover Password Incorrect code", useEye);
		genMeth.clickId(genMeth, droidData.BTNokForErrorPopupID);
		//in order to be able to fully test the confirmation process i will need a generic code that will pass (need to ask DEV)
		
		
		//Re-Send confirmation code
		
		
	}
	
	
	@Test(enabled = false, retryAnalyzer = Retry.class, testName = "Sanity Tests", description = "Switching from Foreground to Background and vice versa use cases",
			groups = { "Sanity Android" })
	public void foregroundBackgroundSwitch() throws Exception, Throwable {

		//Take the app to background & foreground x times
		
		
		//Take the app to sleep/lock  x times
	

	}
	
	@Test(enabled = false, retryAnalyzer = Retry.class, testName = "connection lost handling", description = "Checking how the app owrks while connection is lost & back again", dependsOnGroups = { "Sanity*" }, groups = { "Sanity Android" })
	public void connectionLost() throws InterruptedException, IOException,
			ParserConfigurationException, SAXException {

	}
	
	@AfterSuite(alwaysRun = true)
	public void tearDown() throws Exception {
		// driver.removeApp("com.pogoplug.android");

		try {

			boolean isAppInstalled = driver.isAppInstalled(appPackage);
			if (isAppInstalled) {
				driver.removeApp(appPackage);
			}
			driver.quit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SendResults sr = new SendResults("elicherni444@gmail.com",
				"meny@skygiraffe.com", "TestNG results", "Test Results");
		// sr.sendTestNGResult();
		sr.sendRegularEmail();

	}
}


