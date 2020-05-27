package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.anthem.selenium.SuperHelper;

/*
'Revision History
'#############################################################################
'@rev.On	@rev.No		@rev.By				  @rev.Comments
'										
'#############################################################################
*/

/**
 * Page: SamplePage<p>
 * Page Object Model Sample<p>
 * Please refer this page while creating other page object models
 * 
 * @author TP&E 
 * @since 14-July-2017
 *
 */
public class ExamplePage extends SuperHelper {

	private static ExamplePage thisIsTestObj;

	// So that there only one object accesses this class at any moment
	public synchronized static ExamplePage get() {
		thisIsTestObj = PageFactory.initElements(driver, ExamplePage.class);
		return thisIsTestObj;
	}

	// Recommended model for all objects
	@FindBy(how = How.NAME, using = "USER")
	@CacheLookup
	public WebElement userNameField;


	// If property of an object is dynamic in nature
	WebElement pwd;
	public WebElement password(String strNameValue) {
		pwd = driver.findElement(By.name(strNameValue));
		return pwd;

	}

	// in case you want to use XPATH
	@FindBy(how = How.XPATH, using = "//input[@type='submit']")
	@CacheLookup
	public WebElement submit;


	/**
	 * Logs into the application
	 * @param userProfile
	 * @author TP&E
	 */
	public void loginApplication(String userProfile) {

		//getLoginInfo function provides the user id and password from the user profile
		String[] userInfo = getLoginInfo(userProfile);

		setUserName(userInfo[0]);

		setPassword(userInfo[1]);

		clickSubmit();
	}

	/**
	 * Sets user name
	 * @param strUserID User ID
	 * @author TP&E
	 */
	public void setUserName(String strUserID) {
		seSetUserId(userNameField, strUserID, "User Name");
	}

	
	/**
	 * Sets password
	 * @param strPasword encrypted password
	 * @author TP&E
	 */
	public void setPassword(String strPasword) {
		seSetPassword(pwd, strPasword, "Enter Password");
	}

	
	/**
	 * Clicks on Submit button
	 * @author TP&E
	 */
	public void clickSubmit() {
		seClick(submit, "Click Submit button");
	}

}
