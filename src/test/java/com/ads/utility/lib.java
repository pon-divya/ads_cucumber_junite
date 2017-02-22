/*
 * 
 */
package com.ads.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ads.steps.BrowserDI;
import com.ads.steps.adsWebStep;
import com.ads.utility.ConfigProperty;


import static org.hamcrest.MatcherAssert.assertThat;

// TODO: Auto-generated Javadoc
//import com.microsoft.sqlserver.jdbc.SQLServerDataSource;


/**
 * The Class lib.
 */
public class lib extends adsWebStep {

	/**
	 * Instantiates a new lib.
	 *
	 * @param driver
	 *            the driver
	 */
	public lib(BrowserDI driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/** The properties. */
	private static Properties properties = null;

	/** The values map. */
	// private static HashMap<String, String> valuesMap = null;

	/** The execution_mode. */
	static String execution_mode = "execution_mode";

	/** The Auto_ url. */
	static String Auto_URL = "Auto_URL";

	/** The finish. */
	static String FINISH = "FINISH";

	/** The Passed. */
	static String Passed = "Passed";

	/** The start. */
	static String START = "START";

	/** The fail. */
	static String FAIL = "FAIL";

	/** The ui. */
	static String UI = "UI";

	/** The autorobo. */
	static String AUTOROBO = "AUTOROBO";

	/** The Test suite. */
	static String TestSuite = "TestSuite";

	/** The Test suite runid. */
	String TestSuiteRunid = "TestSuiteRunid";

	/** The timeout. */
	public static int timeout = 4000;

	/** The Shorttimeout. */
	public static int Shorttimeout = 4000;

	/** The get workingdir. */
	static String getWorkingdir = System.getProperty("user.dir");

	/** The default log file. */
	protected static String defaultLogFile = getWorkingdir + "/src/test/logger/img/";
	protected static String defaultAttestFile = getWorkingdir + "/target/attest/";

	/** The default log file. */
	protected static String setDynamicTestDatapath = getWorkingdir + "/src/test/resources/TestData/Dynamic/Setup/";
	/*************** Class Load Initialisation *******************************/
	/**
	 * Output the path of the properties file we are using.
	 */
	static {
		try {
			java.net.URL configURL = ClassLoader.getSystemResource("Automation.properties");

			if (configURL == null) {
				// configURL = new
				// lib().getClass().getClassLoader().getResource("Automation.properties");
			}
			String filename = "";
			if (configURL == null)
				filename = System.getProperty("java.home") + System.getProperty("file.separator") + "lib"
						+ System.getProperty("file.separator") + "Automation.properties";
			else
				filename = configURL.getFile();

			if (System.getProperty("os.name").toUpperCase().startsWith("WINDOWS")) {
				// Tidy up Windows filename
				if (filename.startsWith("/"))
					filename = filename.substring(1);
				filename = filename.replace('/', '\\');
			}

			System.out.println("ConfigServer: " + filename);
		} catch (Throwable th) {
			// Do nothing
		}

	}

	/**
	 * Wait until load.
	 * 
	 * @author lmn490
	 * @param Object
	 *            the object
	 * @return the boolean
	 */
	public static Boolean WaitUntilLoad(WebElement Object) {
		boolean bFound = false;
		try {
			int flg = ConfigProperty.ReloadCount();
			while (flg != 0) {

				if ((Object.isDisplayed() == true)) {
					bFound = true;
					break;
				}
				flg--;
			}

		} catch (NoSuchElementException s) {
			bFound = false;
			// System.out.println("Login window does not exist");
		}

		if (bFound == true) {
			return Object.isDisplayed();
		} else {
			return bFound;
		}

	}

	public static WebElement fluientWaitforElement(WebElement element) {

		int timoutSec = 40000;
		int pollingSec = 200;

		FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(browser).withTimeout(timoutSec, TimeUnit.SECONDS)
				.pollingEvery(pollingSec, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class, TimeoutException.class)
				.ignoring(StaleElementReferenceException.class);

		int flg = ConfigProperty.ReloadCount();

		try {
			while (flg != 0) {

				boolean isState = fWait.until(ExpectedConditions.visibilityOf(element)).isEnabled();

				if ((isState == true)) {
					break;
				}
				flg--;
			}
		} catch (Exception e) {

			System.out.println("Element Not found trying again - " + element.toString().substring(70));
			e.printStackTrace();

		}
		return element;
	}

	/**
	 * Auto runid.
	 * 
	 * @author lmn490
	 * @return the string
	 */
	public static String AutoRunid() {
		String RUNID = null;
		int a = (int) (1000000 + Math.random() * 10000);
		RUNID = Long.toString(a);
		return RUNID;

	}

	/**
	 * Test suite runid.
	 * 
	 * @author lmn490
	 * @return the string
	 */
	public static String TestSuiteRunid() {
		String RUNID = null;
		int a = (int) (1000000000 + Math.random() * 20000);
		RUNID = Long.toString(a);
		return RUNID;

	}

	/**
	 * Step defin heder.
	 *
	 * @return the string
	 */
	public static String StepDefinHeder() {
		// Logger.log(s);
		return null;

	}

	/**
	 * Sets the account number.
	 * 
	 * @author lmn490
	 * @return the string
	 */
	public static String setAccountNumber() {
		String AccountNumber = null;
		int a = (int) (123456789 + Math.random() * 10000000);
		AccountNumber = Long.toString(a);
		return AccountNumber;

	}

	/**
	 * Sets the length trimmer.
	 *
	 * @param expectedValue
	 *            the expected value
	 * @param length
	 *            the length
	 * @return the string
	 */
	public static String setLengthTrimmer(String expectedValue, int length) {

		String LengthTrimmer = expectedValue.substring(expectedValue.length() - length);
		return LengthTrimmer;

	}

	/**
	 * Sets the inistial env.
	 *
	 * @param TestSuiteRunId
	 *            the test suite run id
	 * @param Environment
	 *            the environment
	 * @param build
	 *            the build
	 * @param setBrowerName
	 *            the set brower name
	 */
	public static void setInistialEnv(String TestSuiteRunId, String Environment, String build, String setBrowerName,
			String osinfo) {

		try {
			String db_connect_string = "jdbc:sqlserver://ads:1433;databaseName=execution;user=sa;password=aaa";

			String db_userid = "admin";
			String db_password = "Winter11";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			try {

				String version = System.getProperty("version");

				Connection conn = DriverManager.getConnection(db_connect_string, db_userid, db_password);
				// System.out.println("connected");
				java.sql.Statement statement = conn.createStatement();

				String queryString = "INSERT INTO execution_environment ( TestSuiteRunId,browser_Type, browser_version,environment,os_info) VALUES("
						+ TestSuiteRunId + ",'" + setBrowerName + "','" + version + "','" + Environment + "','" + osinfo
						+ "')";
				// System.out.println(queryString);
				int reset = statement.executeUpdate(queryString);

				if (reset > 0) {
					// System.out.println("Inserted records into the table...");
				}

			} catch (SQLException s) {
				s.printStackTrace();
				System.out.println("SQL code does not executed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Report header.
	 *
	 * @param TestSuiteRunId
	 *            the test suite run id
	 * @param Testcaseid
	 *            the testcaseid
	 * @param TestSuiteName
	 *            the test suite name
	 * @param TestCaseName
	 *            the test case name
	 * @param ExecutionMode
	 *            the execution mode
	 */
	public static void ReportHeader(String TestSuiteRunId, String Testcaseid, String TestSuiteName, String TestCaseName,
			String ExecutionMode, String environment) {

		try {
			String db_connect_string = "jdbc:sqlserver://ads:1433;databaseName=execution;user=sa;password=sa";

			String db_userid = "admin";
			String db_password = "Winter11";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			try {
				String executionlog = "Failed";

				String executiondate = exeDate();
				String executionTime = getTime();
				String computername = InetAddress.getLocalHost().getHostName();
				String logType = "Started";

				Connection conn = DriverManager.getConnection(db_connect_string, db_userid, db_password);
				// System.out.println("connected");
				java.sql.Statement statement = conn.createStatement();

				String queryString = "INSERT INTO execution_testcases_detail ( TestSuiteRunId, TestCaseRunId, TestSuiteName, TestCase_Name, Executed_Date, ExecutionMode, ExecutedOn, Log_Status,Executed_Time_Started,Executed_Time_Ended,environment,logtype) VALUES("
						+ TestSuiteRunId + ",'" + Testcaseid + "','" + TestSuiteName + "','" + TestCaseName + "','"
						+ executiondate + "','" + ExecutionMode + "','" + computername + "','" + executionlog + "','"
						+ executionTime + "','" + executionTime + "','" + environment + "','" + logType + "')";
				// System.out.println(queryString);
				int reset = statement.executeUpdate(queryString);

				if (reset > 0) {
					// System.out.println("Inserted records into the table...");
				}

			} catch (SQLException s) {
				s.printStackTrace();
				System.out.println("SQL code does not executed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ini report header.
	 *
	 * @param TestSuiteRunId
	 *            the test suite run id
	 * @param Testcaseid
	 *            the testcaseid
	 * @param TestSuiteName
	 *            the test suite name
	 * @param TestCaseName
	 *            the test case name
	 * @param ExecutionMode
	 *            the execution mode
	 */
	public static void IniReportHeader(String TestSuiteRunId, String Testcaseid, String TestSuiteName,
			String TestCaseName, String ExecutionMode) {

		try {
			String db_connect_string = "jdbc:sqlserver://ads:1433;databaseName=execution;user=sa;password=sa";

			String db_userid = "admin";
			String db_password = "Winter11";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			try {
				String executionlog = "Passed";

				String executiondate = exeDate();
				String executionTime = getTime();
				String computername = InetAddress.getLocalHost().getHostName();
				Connection conn = DriverManager.getConnection(db_connect_string, db_userid, db_password);
				// System.out.println("connected");
				java.sql.Statement statement = conn.createStatement();

				String queryString = "INSERT INTO execution_testcases_detail ( TestSuiteRunId, TestCaseRunId, TestSuiteName, TestCase_Name, Executed_Date, ExecutionMode, ExecutedOn, Log_Status,Executed_Time_Started,Executed_Time_Ended) VALUES("
						+ TestSuiteRunId + ",'" + Testcaseid + "','" + TestSuiteName + "','" + TestCaseName + "','"
						+ executiondate + "','" + ExecutionMode + "','" + computername + "','" + executionlog + "','"
						+ executionTime + "','" + executionTime + "')";
				// System.out.println(queryString);
				int reset = statement.executeUpdate(queryString);

				if (reset > 0) {
					// System.out.println("Inserted records into the table...");
				}

			} catch (SQLException s) {
				s.printStackTrace();
				System.out.println("SQL code does not executed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Report footer.
	 * 
	 * @author lmn490
	 * @param TestSuiteRunId
	 *            the test suite run id
	 * @param Testcaseid
	 *            the testcaseid
	 * @param executionlog
	 *            the executionlog
	 * @param getImg
	 *            the get img
	 */
	public static void ReportFooter(String TestSuiteRunId, String Testcaseid, String executionlog, String getImg,
			String getlogtype) {

		try {
			String db_connect_string = "jdbc:sqlserver://ads:1433;databaseName=execution;user=sa;password=sa";

			String db_userid = "admin";
			String db_password = "Winter11";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			try {

				String executiondate = getTime();

				Connection conn = DriverManager.getConnection(db_connect_string, db_userid, db_password);
				// System.out.println("connected");
				java.sql.Statement statement = conn.createStatement();
				String queryString = "UPDATE execution_testcases_detail SET Log_Status='" + executionlog
						+ "',Executed_Time_Ended='" + executiondate + "',log_img=CONVERT(VARBINARY(MAX),'" + getImg
						+ "'),logtype='" + getlogtype + "' WHERE TestSuiteRunId=" + TestSuiteRunId
						+ " and TestCaseRunId=" + Testcaseid + " and Log_Status='Failed'";
				// System.out.println(queryString);
				int reset = statement.executeUpdate(queryString);

				if (reset > 0) {
					// System.out.println("Inserted records into the table...");
				}

			} catch (SQLException s) {
				s.printStackTrace();
				System.out.println("SQL code does not executed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author lmn490 Test suite runner.
	 */
	public static void TestSuiteRunner() {

		try {
			String db_connect_string = "jdbc:sqlserver://ads:1433;databaseName=execution;user=sa;password=sa";

			String db_userid = "admin";
			String db_password = "Winter11";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			try {
				String setTestSuiteId = lib.TestSuiteRunid();

				Connection conn = DriverManager.getConnection(db_connect_string, db_userid, db_password);
				// System.out.println("connected");
				java.sql.Statement statement = conn.createStatement();

				String queryString = "INSERT INTO TestSuiteRunne1rTable ( TestSuiteRunId) VALUES('" + setTestSuiteId
						+ "')";
				// System.out.println(queryString);
				int reset = statement.executeUpdate(queryString);

				if (reset > 0) {
					// System.out.println("Inserted records into the table...");
				}

			} catch (SQLException s) {
				s.printStackTrace();
				System.out.println("SQL code does not executed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the test suite runner.
	 * 
	 * @author lmn490
	 * @category:Framewor
	 * @return the test suite runner
	 */
	public static String getTestSuiteRunner() {

		try {
			String db_connect_string = "jdbc:sqlserver://ads:1433;databaseName=execution;user=sa;password=sa";

			String db_userid = "admin";
			String db_password = "Winter11";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			try {

				Connection conn = DriverManager.getConnection(db_connect_string, db_userid, db_password);
				// System.out.println("connected");
				java.sql.Statement statement = conn.createStatement();

				String queryString = "SELECT TOP 1 TestSuiteRunId FROM execution.dbo.TestSuiteRunnerTable ORDER BY id DESC";
				// System.out.println(queryString);
				ResultSet reset = statement.executeQuery(queryString);
				while (reset.next()) {
					String getTestSuiteId = reset.getString("TestSuiteRunId");
					// System.out.println(getTestSuiteId);
					return getTestSuiteId;
				}

			} catch (SQLException s) {
				s.printStackTrace();
				System.out.println("SQL code does not executed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return AUTOROBO;

	}

	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	private static String getTime() {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormate = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

		return dateFormate.format(cal.getTime());
	}

	/**
	 * Exe date.
	 * 
	 * @author lmn490
	 * @return the string
	 */
	private static String exeDate() {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormate = new SimpleDateFormat("YYYY-MM-dd");

		return dateFormate.format(cal.getTime());
	}

	/**
	 * Getdate time.
	 * 
	 * @author lmn490
	 * @return the string
	 */
	public static String GetdateTime() {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormate = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

		return dateFormate.format(cal.getTime());
	}

	/**
	 * Gets the browser and version.
	 * 
	 * @author lmn490
	 * @return the browser and version
	 */
	public static String getBrowserAndVersion() {
		WebDriver browserDriver = null;
		String browser_version = null;
		Capabilities cap = ((RemoteWebDriver) browserDriver).getCapabilities();
		String browsername = cap.getBrowserName();
		// This block to find out IE Version number
		if ("internet explorer".equalsIgnoreCase(browsername)) {
			String uAgent = (String) ((JavascriptExecutor) browserDriver).executeScript("return navigator.userAgent;");
			System.out.println(uAgent);

			String browserversion = browser_version.substring(0, browser_version.indexOf("."));
			return browsername + " " + browserversion;
		}
		return browsername;

	}

	/**
	 * Mkdirtest.
	 * 
	 * @author lmn490
	 * @param TestSuiteId
	 *            the test suite id
	 */
	public static void mkdirtest(String TestSuiteId) {

		// TODO Auto-generated method stub

		File theDir = new File(defaultLogFile + "/" + TestSuiteId);

		// if the directory does not exist, create it
		if (!theDir.exists()) {
			System.out.println("creating directory: " + theDir);
			boolean result = false;

			try {
				theDir.mkdir();
				result = true;
			} catch (SecurityException se) {
				// handle it
			}
			if (result) {
				System.out.println("DIR created");
			}
		}

	}

	/**
	 * Mkdirtest.
	 * 
	 * @author lmn490
	 * @param TestSuiteId
	 *            the test suite id
	 */
	public static void mkConfigfile(String SetSuiteRunId, String setTestSuiteName, String setEnv) {

		// TODO Auto-generated method stub

		// if the directory does not exist, create it
		Properties prop = new Properties();
		OutputStream output = null;

		try {

			File theDir = new File(setDynamicTestDatapath);

			// if the directory does not exist, create it
			if (!theDir.exists()) {
				boolean result = false;

				try {
					theDir.mkdir();
					result = true;
				} catch (SecurityException se) {
					// handle it
				}
				if (result) {
					System.out.println("DIR created");
				}
			}

			output = new FileOutputStream(setDynamicTestDatapath + "_Setup.properties");
			// set the properties value
			prop.setProperty("TestSuiteName", setTestSuiteName);
			prop.setProperty("TestSuiteRunID", SetSuiteRunId);
			prop.setProperty("Environment", setEnv);

			// save properties to project root folder
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public static void setReloadFile() {
		// Collection<String> test = scenario.getSourceTagNames();
		File f = new File(setDynamicTestDatapath + "_Setup.properties");
	}

	public static String getTestSuiteName(String setTestSuiteName) {
		// Collection<String> test = scenario.getSourceTagNames();
		// String[] parts = setTestSuiteName.split(", ", 10);
		// int TotalTags = parts.length;
		// setTestSuiteName = parts[TotalTags - 1];
		// String getTestSuiteName = setTestSuiteName.substring(10);
		// int inTestSuiteName = setTestSuiteName.indexOf("]");
		// getTestSuiteName = setTestSuiteName.substring(10, inTestSuiteName);

		String[] parts = setTestSuiteName.split("\\."); // String array, each
														// element is text
														// between dots

		String getTestSuiteName = parts[1];

		return getTestSuiteName;

	}

	// START : Added Functionality to fetch random alphanumeric value : By
	// Pradeep Bheemarthi

	public static String setGreetingName(int length) {
		return RandomStringUtils.randomAlphanumeric(length);
	}

	// STOP : Added Functionality to fetch random alphanumeric value : By
	// Pradeep Bheemarthi

	/**
	 * This function waits for new tab to load and returns the title of new
	 * loaded tab link in the first drawer
	 * 
	 * @param -
	 * @return - title of new tab
	 * 
	 * @Team Atlantis
	 * @version 1.1
	 */

	public static String getSecTabTitle(BrowserDI d, WebDriverWait w, String title) {
		String tabTitle = null;
		try {
			// waiting for tab count to be 2

			// Need to revisit and fix the issue commenting it as of now
			w.until(ExpectedConditions.numberOfWindowsToBe(2));
			Object[] wh = d.getWindowHandles().toArray();
			// switching the foocus to second tab. Index for it is 1
			d.switchTo().window((String) wh[1]);
			// waiting until timeout
			w.until(ExpectedConditions.titleContains(title));
			// get the title of the new tab
			tabTitle = d.getTitle();
			return tabTitle;
		} catch (Exception e) {
			System.out.println(e.toString());
			return tabTitle;
		}

	}

	public static String splitAmount(String setAmt) {
		// TODO Auto-generated method stub
		String[] parts = setAmt.split("\\."); // String array, each element is
												// text between dots
		String setAmount = parts[0];
		return setAmount;

	}

	/**
	 * This function is to check if the page is test ready.
	 * 
	 * @nrm086
	 * @version 1.1
	 */

	public static ExpectedCondition<Boolean> angularHasFinishedProcessing() {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				String hasAngularFinishedScript = "var callback = arguments[arguments.length - 1];\n"
						+ "var el = document.querySelector('html');\n" + "if (!window.angular) {\n"
						+ "    callback('false')\n" + "}\n" + "if (angular.getTestability) {\n"
						+ "    angular.getTestability(el).whenStable(function(){callback('true')});\n" + "} else {\n"
						+ "    if (!angular.element(el).injector()) {\n" + "        callback('false')\n" + "    }\n"
						+ "    var browser = angular.element(el).injector().get('$browser');\n"
						+ "    browser.notifyWhenNoOutstandingRequests(function(){callback('true')});\n" + "}";

				JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
				String isProcessingFinished = javascriptExecutor.executeAsyncScript(hasAngularFinishedScript)
						.toString();

				return Boolean.valueOf(isProcessingFinished);
			}
		};
	}

	public static String getComputerName() throws UnknownHostException {
		String hostname = "Unknown";
		InetAddress addr;
		addr = InetAddress.getLocalHost();
		return hostname = addr.getHostName();
	}

	public static String getMigrationURL() {
		String getCurrentURL = browser.getCurrentUrl();
		String[] hope = getCurrentURL.split("/accountSummary");
		String getMirgrationURL = " ";

		for (int i = 0; i < hope.length; i++) {
			getMirgrationURL = getMirgrationURL + hope[i];

		}
		return getMirgrationURL;
	}



}
