package testScripts;

import com.anthem.selenium.constants.BrowserConstants;
import com.anthem.selenium.utility.EnvHelper;
import utility.CoreSuperHelper;

/*

*/

/**
 * Manual test case: <Write Manual Test Case Name>
 * <p>
 * 
 * @author <Write name of Author>
 * @since <Creation date>
 * @Revision<>
 *
 */

public class TestScript_Template_TS extends CoreSuperHelper {

	static String strBaseUrl = EnvHelper.getValue("url");
	static String strUserProfile = EnvHelper.getValue("user.profile");

	public static void main(String[] args) {

		try {
			MANUAL_TC_EXECUTION_EFFORT="00:35:00";
			initiateTestScript();

			for (iROW = 1; iROW <= getRowCount(); iROW++) {

				try {
					logExtentReport("Template Script");
					if (getCellValue("Run_Flag").equalsIgnoreCase("Yes")) {

						seOpenBrowser(BrowserConstants.InternetExplorer, strBaseUrl);
						// Test Scripts Start Here
						// Test Scripts End Here
						setResult("STATUS", RESULT_STATUS);
						seCloseBrowser();
					}
				} catch (Exception e) {

					e.printStackTrace();
				} finally {
					endTestScript();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
}
