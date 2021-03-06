package Native;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import jdk.internal.org.xml.sax.SAXException;




public class DroidElements {
	
	
	//Buttons
	String BTNloginID;
	String BTNforgotPasswordID;
	String BTNsettingsLoginScreenID;
	String BTNsampleAccountID;
	String BTNweeklyOperationsID;
	String BTNlogoutName;
	String BTNokForErrorPopupID;
	String BTNcancelForgotPasswordID;
	String BTNrecoverPasswordID;
	String BTNresetPasswordID;
	String BTNlogout_Name;
	String BTNclear_Name;
	String BTNdoneName;
	String BTNpriority_Name;
	String BTNseeAll_ID;
	
	
	
	//TextFields	
	String TEXTFIELDemailID;
	String TEXTFIELDpasswordID;
	String TEXTFIELDrecoveryEmailID;
	
	
	
	
	//STRINGS
	String password;
	String badPassword;
	String scrollDown;
	String scrollUp;
	String User;
	String InvalidRecoverEmailName;
	String ConfCodeIncorrectName;
	String Dashboard_Name;
	
	//Checkbox
	String Checkbox_rememberme_ID;
	
	
	//Icons
	String IconHome_ID;
	String IconSlicer_ID;
	
	//General Info
	String ConnectionAIRPLANE_MODE;
	String ConnectionWIFI;
	String CoonectionDATA;
	String ConnectionALL;
	
	

	
	public DroidElements(String langXml, String xmlPath ) throws ParserConfigurationException, SAXException, IOException, InterruptedException, org.xml.sax.SAXException{
		this.BTNloginID = XmlHandel.readAndroidXml("BTNloginID", langXml, xmlPath);
		this.BTNforgotPasswordID = XmlHandel.readAndroidXml("BTNforgotPasswordID", langXml, xmlPath);
		this.BTNsettingsLoginScreenID = XmlHandel.readAndroidXml("BTNsettingsLoginScreenID", langXml, xmlPath);
		this.BTNsampleAccountID = XmlHandel.readAndroidXml("BTNsampleAccountID", langXml, xmlPath);
		this.BTNweeklyOperationsID = XmlHandel.readAndroidXml("BTNweeklyOperationsID", langXml, xmlPath);
		this.BTNlogoutName = XmlHandel.readAndroidXml("BTNlogoutName", langXml, xmlPath);
		this.BTNokForErrorPopupID = XmlHandel.readAndroidXml("BTNokForErrorPopupID", langXml, xmlPath);
		this.BTNcancelForgotPasswordID = XmlHandel.readAndroidXml("BTNcancelForgotPasswordID", langXml, xmlPath);
		this.BTNrecoverPasswordID = XmlHandel.readAndroidXml("BTNrecoverPasswordID", langXml, xmlPath);
		this.BTNresetPasswordID = XmlHandel.readAndroidXml("BTNresetPasswordID", langXml, xmlPath);
		this.BTNlogout_Name = XmlHandel.readAndroidXml("BTNlogout_Name", langXml, xmlPath);
		this.BTNclear_Name = XmlHandel.readAndroidXml("BTNclear_Name", langXml, xmlPath);
		this.BTNdoneName = XmlHandel.readAndroidXml("BTNdoneName", langXml, xmlPath);
		this.BTNpriority_Name = XmlHandel.readAndroidXml("BTNpriority_Name", langXml, xmlPath);
		this.BTNseeAll_ID = XmlHandel.readAndroidXml("BTNseeAll_ID", langXml, xmlPath);

		
		
		

		this.TEXTFIELDemailID = XmlHandel.readAndroidXml("TEXTFIELDemailID", langXml, xmlPath);
		this.TEXTFIELDpasswordID = XmlHandel.readAndroidXml("TEXTFIELDpasswordID", langXml, xmlPath);
		this.TEXTFIELDrecoveryEmailID = XmlHandel.readAndroidXml("TEXTFIELDrecoveryEmailID", langXml, xmlPath);


		
		this.Checkbox_rememberme_ID = XmlHandel.readAndroidXml("Checkbox_rememberme_ID", langXml, xmlPath);

		
		this.password = XmlHandel.readAndroidXml("password", langXml, xmlPath);
		this.scrollDown = XmlHandel.readAndroidXml("scrollDown", langXml, xmlPath);
		this.scrollUp = XmlHandel.readAndroidXml("scrollUp", langXml, xmlPath);		
		this.ConnectionAIRPLANE_MODE = XmlHandel.readAndroidXml("ConnectionAIRPLANE_MODE", langXml, xmlPath);
		this.ConnectionWIFI = XmlHandel.readAndroidXml("ConnectionWIFI", langXml, xmlPath);
		this.CoonectionDATA = XmlHandel.readAndroidXml("CoonectionDATA", langXml, xmlPath);
		this.ConnectionALL = XmlHandel.readAndroidXml("ConnectionALL", langXml, xmlPath);
		this.User = XmlHandel.readAndroidXml("User", langXml, xmlPath);
		this.InvalidRecoverEmailName = XmlHandel.readAndroidXml("InvalidRecoverEmailName", langXml, xmlPath);
		this.ConfCodeIncorrectName = XmlHandel.readAndroidXml("ConfCodeIncorrectName", langXml, xmlPath);
		this.Dashboard_Name = XmlHandel.readAndroidXml("Dashboard_Name", langXml, xmlPath);

		
		
		this.IconHome_ID = XmlHandel.readAndroidXml("IconHome_ID", langXml, xmlPath);
		this.IconSlicer_ID = XmlHandel.readAndroidXml("IconSlicer_ID", langXml, xmlPath);

		


	
		

	}
	
}
