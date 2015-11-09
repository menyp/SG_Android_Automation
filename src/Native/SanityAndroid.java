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
import org.openqa.selenium.interactions.Keyboard;
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
	public AndroidDriver<MobileElement> driver;
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
			boolean StartUpScreenDisplay1 = genMeth.checkIsElementVisible( By.name("Applications"));
			genMeth.swipedownNexus4(500);
			genMeth.swipedownNexus4(500);
			boolean StartUpScreenDisplay2 = genMeth.checkIsElementVisible( By.name("Settings"));

			if (StartUpScreenDisplay1 != true && StartUpScreenDisplay2  != true ) {

				try {
				//	driver.removeApp(genMeth.getValueFromPropFile("appPackage"));
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

	
	@Test (enabled = true ,testName = "test the sample Account - Dashboard & Daily Sales", retryAnalyzer = Retry.class, description = "test the sample Account - Dashboard & Daily Sales" ,
			groups= {"Sanity Android"}  /*dependsOnMethods={"testLogin"}*/)	
	public void sampleAccountDashboardDailySales() throws ParserConfigurationException,
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
		genMeth.backButton();
		genMeth.clickId(genMeth, droidData.IconHome_ID);
	}
	
	
	@Test (enabled = true ,testName = "test the sample application", retryAnalyzer = Retry.class, description = "Test the login via the sample button" ,
			groups= {"Sanity Android1"}  /*dependsOnMethods={"testLogin"}*/)	
	public void sampleAccountServiceCalls() throws ParserConfigurationException,
			SAXException, IOException, InterruptedException {

		driver.scrollToExact(droidData.BTNlogout_Name);
		genMeth.clickName(genMeth, droidData.BTNlogout_Name );
		genMeth.clickId(genMeth, droidData.BTNsampleAccountID);
		genMeth.eyesCheckWindow(eyes, "Droid_loginSample Main Screen", useEye );
		
		//OPEN SERVICE CALLS
		genMeth.clickName(genMeth, "Service Calls");
		genMeth.eyesCheckWindow(eyes, "Droid_SampleApp- service calls", useEye);
		// InGrid Action- First layer
		genMeth.clickName(genMeth, droidData.BTNpriority_Name);
		genMeth.clickName(genMeth, "1");
		genMeth.clickName(genMeth, droidData.BTNpriority_Name);
		genMeth.clickName(genMeth, "3");
		Thread.sleep(4000);
		genMeth.eyesCheckWindow(eyes, "Droid_SampleApp- service calls- priority = 3, success", useEye);	
		
		//Set the Slicer to Mall of America
		genMeth.clickId(genMeth, droidData.IconSlicer_ID);
		genMeth.clickName(genMeth, "Branch");
		genMeth.clickName(genMeth, "Mall of America");
		genMeth.clickName(genMeth, "Slicer");
		genMeth.clickName(genMeth, droidData.BTNdoneName);
		//Open the See All
		genMeth.clickId(genMeth, droidData.BTNseeAll_ID);
		genMeth.eyesCheckWindow(eyes, "Droid_SampleApp- service calls- priority = 4", useEye);
		genMeth.clickId(genMeth, droidData.IconHome_ID);
//		genMeth.clickId(genMeth, droidData.IconHome_ID);
		genMeth.backButton();
		
		//Open service calls map (Maps are not supported for debugg apk)
		//genMeth.clickName(genMeth, "Service Calls Map");
		
		//Create new service call
		genMeth.clickName(genMeth, "New Service Call");
		Thread.sleep(2000);
		genMeth.eyesCheckWindow(eyes, "Droid_SampleApp- New Service Call", useEye);
		genMeth.clickName(genMeth, "Branch");
		genMeth.clickName(genMeth, "Mall of America");
		genMeth.clickName(genMeth, "Assigned To");
		genMeth.clickName(genMeth, "Jessica Blue");
		genMeth.clickName(genMeth, "Category");
		genMeth.clickName(genMeth, "Computer");
		genMeth.clickName(genMeth, "Item");
		genMeth.clickName(genMeth, "Memory card");
		genMeth.clickName(genMeth, "Description");
		genMeth.sendId(genMeth, "com.skygiraffe.operationaldata:id/action_free_text_ed", "Meny The Best");
		genMeth.clickName(genMeth, "OK");
		genMeth.clickName(genMeth, "Priority");
		genMeth.clickName(genMeth, "1");
		genMeth.eyesCheckWindow(eyes, "Droid_SampleApp- New service call with parameters", useEye);
		Thread.sleep(2000);
		genMeth.clickName(genMeth, "Submit");
		Thread.sleep(2000);
		genMeth.eyesCheckWindow(eyes, "Droid_SampleApp- New service call Actions collections +", useEye);
		//Back to home
		genMeth.backButton();
		genMeth.clickId(genMeth, droidData.IconHome_ID);
		}
	
	
	@Test (enabled = true ,testName = "test the sample application", retryAnalyzer = Retry.class, description = "Test the login via the sample button" ,
			groups= {"Sanity Android"}  /*dependsOnMethods={"testLogin"}*/)	
	public void sampleAccountOrderLookup() throws ParserConfigurationException,
			SAXException, IOException, InterruptedException {

		driver.scrollToExact(droidData.BTNlogout_Name);
		genMeth.clickName(genMeth, droidData.BTNlogout_Name );
		genMeth.clickId(genMeth, droidData.BTNsampleAccountID);
		genMeth.eyesCheckWindow(eyes, "Droid_loginSample Main Screen", useEye );
		
		// Order lookup
		genMeth.clickName(genMeth, "Order Lookup");
		Thread.sleep(3000);
		genMeth.eyesCheckWindow(eyes, "Droid_SampleApp- Order Lookup parameters", useEye);
		
		
		genMeth.clickName(genMeth, "Start Date");
		MobileElement UIAPickerWheel = driver.findElementById("android:id/pickers");
		UIAPickerWheel.sendKeys("Jan");
		genMeth.pressEnter();	
		genMeth.clickName(genMeth, "OK");
		genMeth.clickId(genMeth, "com.skygiraffe.operationaldata:id/parameterized_fragment_submit_button");
		Thread.sleep(3000);
		genMeth.eyesCheckWindow(eyes, "Droid_SampleApp- List of Orders", useEye);
		genMeth.backButton();
		genMeth.clickId(genMeth, droidData.IconHome_ID);
		
	}
	
	@Test (enabled = true ,testName = "test the sample application", retryAnalyzer = Retry.class, description = "Test the login via the sample button" ,
			groups= {"Sanity Android"}  /*dependsOnMethods={"testLogin"}*/)	
	public void sampleAccountOperations() throws ParserConfigurationException,
			SAXException, IOException, InterruptedException {

		driver.scrollToExact(droidData.BTNlogout_Name);
		genMeth.clickName(genMeth, droidData.BTNlogout_Name );
		genMeth.clickId(genMeth, droidData.BTNsampleAccountID);
		genMeth.eyesCheckWindow(eyes, "Droid_loginSample Main Screen", useEye );
		
		//Operations
		driver.scrollTo("Operations");
		genMeth.clickXpth(
				driver,genMeth,
				"//android.view.View[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.view.View[1]/android.widget.ListView[1]/android.widget.RelativeLayout[7]/android.widget.RelativeLayout[1]");
		Thread.sleep(4000);
		genMeth.eyesCheckWindow(eyes, "Droid_SampleApp- Inventory", useEye);
		
		//Open grid second layer
		genMeth.clickName(genMeth, "Mall of America");
		genMeth.eyesCheckWindow(eyes, "Droid_SampleApp- Inventory second layer", useEye);
		genMeth.backButton();
		
		//Open Orders tab
		genMeth.clickId(genMeth, "android:id/action_bar_spinner");
		genMeth.clickName(genMeth, "Orders");
		genMeth.eyesCheckWindow(eyes, "Droid_SampleApp- Orders", useEye);
		genMeth.clickId(genMeth, "android:id/action_bar_spinner");
		genMeth.clickName(genMeth, "Place New Order");
		
		//Open the place new order
		genMeth.clickId(genMeth, "com.skygiraffe.operationaldata:id/action_collection_template_button");
		genMeth.eyesCheckWindow(eyes, "Droid_SampleApp- Place new order parameters", useEye);
		genMeth.clickName(genMeth, "Submit");
		genMeth.eyesCheckWindow(eyes, "Droid_SampleApp- Place new order parameters missing", useEye);
		genMeth.clickId(genMeth, droidData.BTNokForErrorPopupID);
		
		// Fill the parameters
		genMeth.clickName(genMeth, "Branch");
		genMeth.clickName(genMeth, "Mall of America");
		genMeth.clickName(genMeth, "ProductID");
		genMeth.clickId(genMeth,"com.skygiraffe.operationaldata:id/qr_scan_input_edit");
		genMeth.sendId(genMeth,"com.skygiraffe.operationaldata:id/qr_manual_input_edit_text","1");
		Thread.sleep(1000);
		driver.hideKeyboard();
		genMeth.clickName(genMeth, "USE");

		genMeth.clickName(genMeth, "Quantity");
		genMeth.sendId(genMeth,
				"com.skygiraffe.operationaldata:id/action_free_text_ed", "1");
		//genMeth.pressEnter();
		genMeth.clickName(genMeth, "OK");
		genMeth.eyesCheckWindow(eyes,
				"Droid_SampleApp- Place new order All parameters", useEye);
		genMeth.clickName(genMeth, "Submit");
		genMeth.eyesCheckWindow(eyes, "Droid_SampleApp- Place New Order",
				useEye);
		Thread.sleep(1000);
		genMeth.backButton();
		genMeth.clickId(genMeth, droidData.IconHome_ID);

	}
	
	@Test (enabled = true ,testName = "test the sample application", retryAnalyzer = Retry.class, description = "Test the login via the sample button" ,
			groups= {"Sanity Android"}  /*dependsOnMethods={"testLogin"}*/)	
	public void sampleAccountTechnicians() throws ParserConfigurationException,
			SAXException, IOException, InterruptedException {

		driver.scrollToExact(droidData.BTNlogout_Name);
		genMeth.clickName(genMeth, droidData.BTNlogout_Name );
		genMeth.clickId(genMeth, droidData.BTNsampleAccountID);
		genMeth.eyesCheckWindow(eyes, "Droid_loginSample Main Screen", useEye );
				
// Technicians
		//driver.scrollToExact("Technicians");
		genMeth.swipedownNexus4(500);
		genMeth.clickName(genMeth, "Technicians");
		genMeth.eyesCheckWindow(eyes, "Droid_SampleApp - Technicians", useEye);
		
// 	Phone Icon
		genMeth.clickName(genMeth, "Phone");
		Thread.sleep(1000);
		genMeth.eyesCheckWindow(eyes, "Droid_SampleApp Technicians- Phone", useEye);
		genMeth.clickName(genMeth, "Cancel");

		
// Mail Icon
		genMeth.clickName(genMeth, "Email");
		genMeth.eyesCheckWindow(eyes, "Droid_SampleApp Technicians- Email screen", useEye);
		genMeth.clickName(genMeth, "Cancel");

// Map Icon
		genMeth.clickName(genMeth, "Address");
		genMeth.eyesCheckWindow(eyes, "Droid_SampleApp Technicians- Address screen", useEye);
		genMeth.clickName(genMeth, "Cancel");
		genMeth.clickId	(genMeth, droidData.IconHome_ID);
		genMeth.clickId	(genMeth, droidData.IconHome_ID);
		
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

			/*
			boolean isAppInstalled = driver.isAppInstalled(appPackage);
			if (isAppInstalled) {
				driver.removeApp(appPackage);
			}
			*/
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


