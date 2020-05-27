import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.anthem.selenium.constants.EnvConstants;
import com.anthem.selenium.utility.EnvHelper;
import com.anthem.selenium.utility.FileUtility;

import utility.CoreSuperHelper;


/**
 * 
 * This file executes all the scripts from testScripts package and all its sub-packages.
 * 
 * ##### Do not modify this file. #####
 * 
 * @author shiva.katula
 *
 */
public class Driver extends CoreSuperHelper {
	
	static String testScriptsFolder = EnvHelper.getValue(EnvConstants.testScripts);
	static List<File> testScriptFiles = null;
	static Map<String, String> filesMap = null;
	
	/**
	 * @param args
	 *
	 */
	public static void main(String[] args) {

		try{
			GLOBAL_EXECUTION=true;
			initiateData();
			startGlobalExtentReports();
			loadTestScripts();
			
			for (String scriptName : getRunOrderScripts()) {
				currentScript = getTestScripts().get(scriptName).get(0);
				logGlobalExtentReport(currentScript.getTestScriptName());
				
				Class<?> c = Class.forName(testScriptsFolder + filesMap.get(currentScript.getTestScriptName()));
		        Method meth = c.getMethod("main", String[].class);
		        String[] params = null; // init params accordingly
		        meth.invoke(null, (Object) params); // static method doesn't have an instance
		        RESULT_STATUS = true;
		        endGlobalExtentReport();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			
			closeExcelFile();
			clearMemoryObjects();
		}
	}
	
	/**
	 * This method loads the all testscripts from all sub-packages in testScripts Folder.
	 */
	private static void loadTestScripts(){
		
		URL location = Driver.class.getProtectionDomain().getCodeSource().getLocation();
		testScriptFiles = FileUtility.getFilesList(location.getPath()+testScriptsFolder, new String[] {"class"}, true);
		
		filesMap = new HashMap<String, String>();
		
		for (File scriptFile : testScriptFiles) {
			String scriptFileName = scriptFile.getName().replace(".class", "");
			String scriptFilePath = scriptFile.getPath().substring(scriptFile.getPath().indexOf(testScriptsFolder));
			scriptFilePath = scriptFilePath.replace(testScriptsFolder, "");
			scriptFilePath = scriptFilePath.replaceAll(".class", "");
			scriptFilePath = scriptFilePath.replace('\\', '.');
			
			filesMap.put(scriptFileName, scriptFilePath);
		}
		
	}
	
	private static void clearMemoryObjects() {
		testScriptFiles.clear();
		filesMap.clear();
		testScriptFiles = null;
		filesMap = null;
		System.gc();
	}

}
