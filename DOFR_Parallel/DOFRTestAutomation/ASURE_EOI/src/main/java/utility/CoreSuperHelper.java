/**
 * 
 */
package utility;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.anthem.selenium.SuperHelper;
import com.anthem.selenium.constants.ApplicationConstants;
import com.anthem.selenium.logging.AnthemLogger;
import com.anthem.selenium.utility.ExtentReportsUtility;

/**
 * @author shiva.katula
 * 
 *         ##### Do not delete this file. ######
 * 
 *         This class is specifically only for any Project specific Helper
 *         Methods for SuperHelper Extension
 *
 *         Any method defined in this class will be automatically available in
 *         the project like Super Helper methods. Any methods defined here will
 *         need to be called without the Class Name to maintain consistency in
 *         calling If Project defines a helper method here and if that method is
 *         harvested back into Selenium Framework by Architects, then the local
 *         method in this class can be deleted with out changing any of the test
 *         scripts.
 * 
 *         To maintain naming convention across all the projects do not name the
 *         method with the Project Name. Also take the suggestion/Approval for
 *         the method name to avoid discrepancy with the Framework methods.
 * 
 */
public class CoreSuperHelper extends SuperHelper {

	protected static AnthemLogger logger;

	public static boolean aTafContainString(String strString, String strSubString, String strStepName) {

		boolean blnReturnValue = false;

		if (strString.contains(strSubString)) {
			log(PASS, strStepName, "Substring [" + strSubString + "] is found in [" + strString + "]");
			blnReturnValue = true;
		} else {
			log(FAIL, strStepName, "Substring [" + strSubString + "] is NOT found in [" + strString + "]");
		}
		return blnReturnValue;
	}

	public static boolean aTafContainStringIgnoreCase(String strString, String strSubString, String strStepName) {

		boolean blnReturnValue = false;
		String strStringUpper = strString.toUpperCase();
		String strSubStringUpper = strSubString.toUpperCase();

		if (strStringUpper.contains(strSubStringUpper)) {
			log(PASS, strStepName, "Substring [" + strSubString + "] is found in [" + strString + "]");
			blnReturnValue = true;
		} else {
			log(FAIL, strStepName, "Substring [" + strSubString + "] is NOT found in [" + strString + "]");
		}
		return blnReturnValue;
	}

	/**
	 * <p>
	 * Method to select Radio Button
	 * </p>
	 * 
	 * @param radioButton-
	 *            WebElement of Radio Button eg- seClickRadio(radioButton);
	 * @author AF30637 Rajat Mishra
	 * @since Sep 25, 2017
	 */
	public static void seClickRadio(WebElement radioButton, String strRadioButtonName) {

		try {
			if (radioButton != null) {
				radioButton.click();
				log(PASS, "Radio button " + strRadioButtonName + " clicked");
			} else
				log(FAIL, "Radio button " + strRadioButtonName + " not found");
		} catch (Exception excException) {
			processExceptions("Exception occured while clicking on Radio Button", excException);
			throw excException;
		}

	}

	/**
	 * <p>
	 * Method to select CheckBox
	 * </p>
	 * 
	 * @param checkBox-
	 *            WebElement of CheckBox eg- seClickRadio(checkBox);
	 * @author AF30637 Rajat Mishra
	 * @since Sep 25, 2017
	 */
	public static void seClickCheckBox(WebElement checkBox, String strCheckBoxName) {

		try {
			if (checkBox != null) {
				checkBox.click();
				log(PASS, "Radio button " + strCheckBoxName + " clicked");
			} else
				log(FAIL, "Radio button  " + strCheckBoxName + " not found");
		} catch (Exception excException) {
			processExceptions("Exception occured while clicking on CheckBox", excException);
			throw excException;
		}

	}

	/**
	 * <p>
	 * Method to update the RESULT_STATUS flag and update the logger
	 * </p>
	 * 
	 * @param strStep-
	 *            Step at which exception occurred
	 * @param excException-Exception
	 *            occurred eg- catch (Exception excException)
	 *            processExceptions(Exception Details, excException); throw
	 *            excException;
	 * @author AF34794 Usharani Arunachalam
	 * @since 27-July-2017
	 */
	protected static void processExceptions(String strStep, Exception excException) {
		RESULT_STATUS = false;
		logger.error(strStep);
		excException.printStackTrace();
		log(ERROR, strStep, "Exception: " + excException.getLocalizedMessage(), true);

	}

	/**
	 * This method will verifies if there is a pdf file in the target folder and
	 * returns the pdf file name
	 * 
	 * @return: String strPDFFileName pdf file name which is available in the
	 *          target folder
	 */
	public static String verifyPDFFileIsDownloaed(String strTargetFolder) {
		String strPDFFileName = "";
		boolean blnISPDFFileFound = false;

		File folderReportFolder = new File(strTargetFolder);
		File[] arrFiles = folderReportFolder.listFiles();

		for (File file : arrFiles) {
			if (file.getName().contains(".pdf")) {
				blnISPDFFileFound = true;
				strPDFFileName = file.getName();
				break;
			}
		}

		if (blnISPDFFileFound) {
			log(PASS, "Verify if pdf is downloaded", "PDF is downloaded");
		} else {
			log(FAIL, "Verify if pdf is downloaded", "PDF is NOT downloaded");
		}

		return strPDFFileName;
	}

	/**@author AF14733
	 * This method will return the location of the reports folder
	 * 
	 * @return: Reports folder location will be returned
	 */
	public static String getReportPathFolder() {
		String reportsFolder = "";
		try {
			File f = new File(ExtentReportsUtility.reportsPathFolder);
			reportsFolder = f.getCanonicalPath() + File.separator;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return reportsFolder;
	}

	
	
	/**This method will return the path the reports folder
	 * @return
	 */
	public static String getReportFolderPath()
	{
		try
		{
			String reportsPath = ("C:"+ExtentReportsUtility.reportsPathFolder).replaceAll("/", "\\\\");
			return reportsPath;
		}
		catch (Exception e) {
			log(FAIL,"Exception caught in the getreportFolderPath "+e.getLocalizedMessage() );
			return "";
		}
		
	} 
}
	