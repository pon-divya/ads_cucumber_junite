
// This class defines steps that will be used by all feature files
package com.ads.steps;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ads.pages.HomePage;
import com.ads.runnerTest.login;
import com.ads.pages.LoginPage;
import com.ads.pages.SignInPage;
import com.ads.utility.ConfigServer;
import com.ads.utility.Logger;

public class adsWebStep {
	protected static BrowserDI browser;
	protected String baseURL;
	protected String csqURL;
	protected String mfaURL;
	protected LoginPage loginPage;
	protected HomePage homeIndexPage;
	protected SignInPage SignupPage;

	// public HashMap<String, String> map = new HashMap<String, String>();
	public Map<String, String> test = new HashMap<String, String>();

	public Map<String, String> map = new HashMap<String, String>();

	static String workingdirectory = System.getProperty("user.dir");
	// This defines the wait that needs to be used for explicit wait
	protected WebDriverWait browserWait;

	/**
	 * Instantiates a new ease web step.
	 *
	 * @param driver
	 *            the driver
	 */
	Actions action;

	List<String> accountRefIds = new ArrayList<String>();

	public adsWebStep(BrowserDI driver) {

		adsWebStep.browser = driver;

		this.browser = driver;
		browser.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		loginPage = new LoginPage(browser);
		homeIndexPage = new HomePage(browser);

		browserWait = new WebDriverWait(browser, 30);

		/**
		 * Instantiates a new ease web step.
		 * 
		 * @author lmn490
		 * @param properies
		 *            path name
		 * 
		 */

		// if (BrowserDI.map == null || BrowserDI.map.size() <= 0 ||
		// BrowserDI.map.isEmpty()) {
		// System.out.println("Executed");
		// getpropertiesValue();
		// }

		List<String> fileList = new ArrayList<String>();
		if (ConfigServer.getValue("skip") == null) {
			if (ConfigServer.getValue("xmlexecuted") == null) {
				// fileList.add(workingdirectory +
				// "/src/test/resources/TestData/TestData.xml");
				// fileList.add(workingdirectory +
				// "/ease-core_ParmSetting.xml");
			}

		}

		writeProperties(fileList);
		fileList.clear();
		if (map == null || map.size() <= 0) {
			map = ConfigServer.getValues();
		}
		setMap();

		action = new Actions(driver);
		accountRefIds.add(map.get("data_ref1"));
		accountRefIds.add("9u1SfGt55dLR22urD+20UJ/qPS1Aq7BufmeWiLFOZfp70DGaSVOHmaQw72UxJy39");

	}

	// Populating instance variable through static access of a static variable
	// of a Static attribute.
	public void setMap() {
		this.map = BrowserDI.map;
		this.test = BrowserDI.dynamicMap;
	}

	/**
	 * parsing the given xml and updating to map
	 *
	 * @param filePath
	 * @param fileName
	 */
	public void parseAndLoadXML(String filePath, String fileName) {
		// if(ConfigServer.getValue(fileName.toLowerCase()+ "xmlloaded") ==
		// null) {
		List<String> fileList = new ArrayList<String>();

		fileList.add(filePath);
		// fileList.add(ConfigServer.getProperty("common.applicationhome") +
		// filePath);
		writeProperties(fileList);
		// ConfigServer.updateValue(fileName.toLowerCase()+ "xmlloaded",
		// "updated");
		// }
	}

	/**
	 * This is used to parse the xml's which is there in the file list and
	 * adding into map.
	 *
	 * @param fileList
	 *            - List<String> contains xml file list to be loaded
	 *
	 */
	public void writeProperties(List<String> fileList) {
		for (String filePath : fileList) {
			try {

				// Read Environment parameters from xml file
				// Library readXML = new Library();
				// readXML.GetXMLParameter(filePath);
				getXMLParameter(filePath);

				// Read test data from xml file
				// readXML.GetXMLData(filePath);
				getXMLData(filePath);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public void getXMLParameter(String filePath) throws Exception {
		String EnvTagName = "Variable";
		String Name = "Name";
		String Value = "Value";

		try {

			File file = new File(filePath);

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			NodeList nodeLst = doc.getElementsByTagName(EnvTagName);
			for (int s = 0; s < nodeLst.getLength(); s++) {
				Node fstNode = nodeLst.item(s);
				if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
					Element fstElmnt = (Element) fstNode;
					NodeList fstNmElmntLst = fstElmnt.getElementsByTagName(Name);
					Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
					NodeList fstNm = fstNmElmnt.getChildNodes();
					// EnvParams[Count++] = ((Node)
					// fstNm.item(0)).getNodeValue();
					String key = fstNm.item(0).getNodeValue();
					NodeList lstNmElmntLst = fstElmnt.getElementsByTagName(Value);
					Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
					NodeList lstNm = lstNmElmnt.getChildNodes();
					// EnvParams[Count++] = ((Node)
					// lstNm.item(0)).getNodeValue();
					String value = lstNm.item(0).getNodeValue();
					ConfigServer.updateValues(key, value);
					// System.out.println(key + value);
					// Logger.write(key+"="+value);

				}
			}

		} catch (Exception e) {

			System.out.println("Exception Occured : BaseTestCase:getXMLParameter()");
			e.printStackTrace();

		}
	}

	public void getXMLData(String filename) {

		String DataTagName = "Data";
		String Name = "Name";
		String Value = "Value";

		try {

			File file = new File(filename);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			NodeList nodeLst = doc.getElementsByTagName(DataTagName);
			for (int s = 0; s < nodeLst.getLength(); s++) {
				Node fstNode = nodeLst.item(s);
				if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
					Element fstElmnt = (Element) fstNode;
					NodeList fstNmElmntLst = fstElmnt.getElementsByTagName(Name);
					Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
					NodeList fstNm = fstNmElmnt.getChildNodes();
					// XMLData[Count++] = ((Node) fstNm.item(0)).getNodeValue();
					String key = fstNm.item(0).getNodeValue();
					NodeList lstNmElmntLst = fstElmnt.getElementsByTagName(Value);
					Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
					NodeList lstNm = lstNmElmnt.getChildNodes();
					// XMLData[Count++] = ((Node) lstNm.item(0)).getNodeValue();
					String value = lstNm.item(0).getNodeValue();
					ConfigServer.updateValues(key, value);

					// System.out.println(key + "=" + value);
				}
			}

		} catch (Exception e) {

			System.out.println("Exception Occured : BaseTestCase:getXMLData");
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @param exception
	 *            - Object exception
	 * @param testCase
	 *            - String test case name
	 * @param testSuite
	 *            - String test suite name
	 * @throws Throwable
	 *             -
	 *
	 *             handling the exception or error and writing into the log file
	 *             at the execution.
	 */
	public void handleError(Object exception, String scenario, String testSuite) throws Throwable {
		Logger.log("*********** Exception Occured **************");
		Logger.log(" Test Suite Run ID  : " + "Sample");

		setMakdir();
		System.out.println(exception);

		if (exception instanceof RuntimeException) {
			Logger.log(((RuntimeException) exception).getStackTrace());
			((RuntimeException) exception).printStackTrace();
			throw (RuntimeException) exception;
		} else if (exception instanceof ExceptionInInitializerError) {
			Logger.log(((ExceptionInInitializerError) exception).getStackTrace());
			((ExceptionInInitializerError) exception).printStackTrace();
			throw (ExceptionInInitializerError) exception;
		} else if (exception instanceof Error) {
			Logger.log(((Error) exception).getStackTrace());
			((Error) exception).printStackTrace();
			throw (Error) exception;
		} else if (exception instanceof NoSuchElementException) {
			Logger.log(((NoSuchElementException) exception).getStackTrace());
			((NoSuchElementException) exception).printStackTrace();
			throw (NoSuchElementException) exception;
		}

	}

	public static void setMakdir() {

		File setF = new File(workingdirectory + "/src/test/log/" + "log");
		try {
			if (!setF.exists()) {
				setF.mkdir();
				System.out.println("Following folder is successfully created" + workingdirectory);
			}

		} catch (Exception e) {
		}
	}

}
