package Native;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
	public AndroidDriver driver;
	DroidMethods genMeth = new DroidMethods();
	
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
			boolean StartUpScreenDisplay = genMeth.checkIsElementVisible( By.name("need to check the startup screen"));

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
	
	@AfterMethod(enabled = true, dependsOnMethods = { "connectionLost" })
	public void enabledWifi() {

		genMeth.setWifiOn();
	}

	@Test (enabled = true ,testName = "createfolder1", retryAnalyzer = Retry.class, description = "Test the Create folder with Android" ,
			groups= {"Sanity Android"}  /*dependsOnMethods={"testLogin"}*/)	
	public void createfolder() throws ParserConfigurationException, SAXException, IOException, InterruptedException{
		

		
		  }

	
	@Test (enabled = true , retryAnalyzer = Retry.class, testName="Sanity Tests", description = "Test the Upload utility with Android" ,
			groups= {"Sanity Android"})	
	public void uploadImage() throws ParserConfigurationException, SAXException, IOException, InterruptedException{
			}
	
	
	@Test(enabled = true, retryAnalyzer = Retry.class, testName = "Sanity Tests", description = "Test TOUR for New accounts and for upgrade accounts",
			groups = { "Sanity Android" })
	public void tour() throws Exception, Throwable {

			}
	
	@Test(enabled = true, retryAnalyzer = Retry.class, testName = "Sanity Tests", description = "Sign up- Create new user (Negetive positive test), Privacy Policy, TRUSTe",
			groups = { "Sanity Android" })
	public void createNewUser() throws Exception, Throwable {
			
	}
	
	@Test (enabled = true, retryAnalyzer = Retry.class, testName = "Terms of service & TrusTe", description = "Test the TOS & TrusTe Links" ,
			groups = {"Sanity Android"})
	public void termsOfServiceAndTruste () throws InterruptedException, IOException, ParserConfigurationException, SAXException{
		
				
	}
	
	@SuppressWarnings("static-access")
	@Test(enabled = true,  testName = "Sanity Tests", description = "login with bad/missing credentials , forgot password (negative & positive)",
			groups = { "Sanity Android1" })
	public void badCredentials() throws Exception, Throwable {
		
		String currentTime = genMeth.currentTime();

		Eyes eyes = new Eyes();
		eyes.setApiKey("Hbh6716cKDCgn8a9bMAREPM105nbW109PQe0993So5GwFpNM110");
		eyes.open(driver, "pp", "test1");
		eyes.checkWindow("Initial screen");
		eyes.close();

		genMeth.signOutFromStartupAndroid(genMeth, droidData);
		// Login with bad credentials

		// Forgot your password Negative (attempt to restore password with a non
		// existing email)

		// Forgot your password Positive (attempt to restore password with an
		// existing email)
	
	}
	
	@Test(enabled = true, retryAnalyzer = Retry.class, testName = "Sanity Tests", description = "Search functionality & filter",
			groups = { "Sanity Android" })
	public void search() throws Exception, Throwable {
		
			}
	
	@Test(enabled = true, retryAnalyzer = Retry.class, testName = "Sanity Tests", description = "Settings: create & restore a snapshot",
			groups = { "Sanity Android" })
	public void createRestoreSnapShot() throws Exception, Throwable {
		
		
				}
	
	
	@Test (enabled = false, retryAnalyzer = Retry.class, testName = "Sanity Tests", description = "Settings: Save Login",
			groups = { "Sanity Android1" })
			
	public void settingsKeepMeSignedIn() throws Exception, Throwable {

			}

	@Test(enabled = false, retryAnalyzer = Retry.class, testName = "Sanity Tests", description = "Settings: Backup Enable/disable without upload in the background",
			groups = { "Regression Droid" })
	public void settingsBackupEnableDisable() throws Exception, Throwable {

		// Disable the Backup from tour
		

		// "Enable" the backup form LSM (Left Side Menu) & cancel it
		

		// Enable the Backup form LSM (Left Side Menu)
	

		// Enable the backup from settings (first enable it & then disable it from backup screen)
		

	}
	
	@Test(enabled = false, retryAnalyzer = Retry.class, testName = "Sanity Tests", description = "Settings: Backup Enable/disable *with upload in the background",
			groups = { "Regression iOS" })// , dependsOnMethods={"successTest"})
	public void settingsBackupEnableDisableDuringUpload() throws Exception,Throwable {

			}

	@Test(enabled = false, retryAnalyzer = Retry.class, testName = "Sanity Tests", description = " Backup running in background -> make sure process keeps alive and completes its queue even in background AND if taking new shots they are automatically backed up",
			groups = { "Sanity Android" })
	public void backupInBackground() throws Exception, Throwable {

			}

	@Test(enabled = false, retryAnalyzer = Retry.class, testName = "Sanity Tests", description = "Switching from Foreground to Background and vice versa use cases",
			groups = { "Sanity Android" })
	public void foregroundBackgroundSwitch() throws Exception, Throwable {

		//Take the app to background & foreground x times
		
		
		//Take the app to sleep/lock  x times
	

	}
	
		@Test(enabled = false , retryAnalyzer = Retry.class, testName = "Sanity Tests" , description = " Add remove files from favorites" ,
				groups = { "Sanity Android"} )
	public void Favorites() throws InterruptedException, IOException, ParserConfigurationException, SAXException{
				}
		
	@Test(enabled = false, retryAnalyzer = Retry.class, testName = "Sanity Tests", description = "Adding & removing team folders",
			groups = { "Sanity Android" })
	public void addRemoveTeamFolders() throws Exception, Throwable {
		
				
	}
	@Test(enabled = false, retryAnalyzer = Retry.class,  testName = "connection lost handling", description = "Checking how the app owrks while connection is lost & back again",
			dependsOnGroups = {"Sanity*"}, groups={ "Sanity Android"})
			
	public void connectionLost() throws InterruptedException, IOException, ParserConfigurationException, SAXException{
		
			} 
	
	
	
	@Test(enabled = false, retryAnalyzer = Retry.class,  testName = "connection lost handling",
			description = "Checking how the app owrks while connection is lost & back again",
			groups={ "Sanity Android1"} )
	public void temp() throws IOException, ParserConfigurationException, SAXException, InterruptedException{
		
		
		
		
	}
	
	@AfterSuite (alwaysRun=true)

		public void tearDown() throws Exception {
//			driver.removeApp("com.pogoplug.android");
		
			try {
				genMeth.setWifiOn();
				driver.quit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			SendResults sr = new SendResults("elicherni444@gmail.com", "meny@cloudengines.com", "TestNG results", "Test Results");
			sr.sendTestNGResult();
		
		}




	}



