/*
 *
 */
package com.ads.steps;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.codehaus.classworlds.Launcher;
import org.junit.Assert;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ads.utility.lib;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

// TODO: Auto-generated Javadoc
/**
 * The Class BrowserDI.
 */
public class BrowserDI extends EventFiringWebDriver {

	/** The browser. */
	public static WebDriver BROWSER = null;

	/** The workingdirectory. */
	static String workingdirectory = System.getProperty("user.dir");

	/** The default log file. */
	protected static String defaultLogFile = workingdirectory + "/src/test/logger/img/";

	/** The system properties. */
	private static Properties systemProperties;

	/** The remote url. */
	private static String remoteURL = "test";

	private static String testDroidURL = "test";
	private static String testdeviceFURL = "test";

	/** The sauce connect tunnel. */
	private static String sauceConnectTunnel = "ease_team";

	/** The map. */
	public static Map<String, String> map = new HashMap<String, String>();

	public static Map<String, String> dynamicMap = new HashMap<String, String>();
	/** The browser. */
	private static String browser = System.getProperty("browserName");

	/** The version. */
	private static String version = System.getProperty("version");

	/** The os. */
	private static String os = System.getProperty("os");

	/** The exec platform. */
	private static String execPlatform = System.getProperty("execPlatform");

	private static String execPlatform1 = System.getProperty("env");
	/** The username. */
	private static String username = browser + "_" + version + "_" + os;

	/** The dc. */
	private static DesiredCapabilities dc = new DesiredCapabilities();

	/** The verification errors. */
	public StringBuffer verificationErrors;

	/** The set time out. */
	private static int setTimeOut;

	/** The scenario. */
	public Scenario scenario;

	/** The capabilities. */
	static DesiredCapabilities capabilities = new DesiredCapabilities();

	/** The Testcaseid. */
	private String Testcaseid = lib.AutoRunid();

	private String setTestSuiteName;

	private String setEnvironment;

	/** The Constant CLOSE_THREAD. */
	private static final Thread CLOSE_THREAD = new Thread() {

		@Override
		public void run() {
			BROWSER.quit();

		}
	};

	/**
	 * Sets the up data map.
	 */
	public static void setUpDataMap() {

		/**
		 * Instantiates a new ease web step.
		 *
		 * @author lmn490
		 * @param properies
		 *            path name
		 *
		 */
		if (map == null || map.size() <= 0 || map.values().isEmpty()) {
			getpropertiesValue();
		}

	}

	static {

		try {
			setUpDataMap();
			int width = Integer.parseInt(map.get("env_width"));
			int height = Integer.parseInt(map.get("env_height"));
			// int TimeOut = Integer.parseInt(map.get("setTime_Wait"));
			// eyes.setMatchTimeout(TimeOut);
			// eyes.setApiKey(map.get("env_ApiKey"));
			// eyes.setProxy(new ProxySettings(map.get("env_Proxy")));
			// eyes.setMatchLevel(MatchLevel.LAYOUT);
			setTimeOut = Integer.parseInt(map.get("setTime_Wait"));

			if (execPlatform == null) {
				execPlatform = map.get("execution_mode");
			}
			if (browser == null) {
				browser = map.get("Browsername");
			}

			// String execMode = map.get("execution_mode");

			else if (execPlatform.equals("native")) {

				switch (browser) {
				case "chrome":

					BROWSER = new ChromeDriver();

					break;
				case "firefox":
					BROWSER = new FirefoxDriver();

					setBrowserAttributes(setTimeOut);
					break;
				case "ie":
					BROWSER = new InternetExplorerDriver();
					setBrowserAttributes(setTimeOut);
					break;
				case "safari":
					BROWSER = new SafariDriver();
					setBrowserAttributes(setTimeOut);
					break;

				default:
					System.out.println("Specified capabilities are not set for native tesing");
				}

			} else if (execPlatform.equalsIgnoreCase("device")) {
				switch (browser) {
				case "iOS":
					capabilities.setCapability("deviceName", "iPad 2");
					capabilities.setCapability("platformName", "iOS");
					capabilities.setCapability("platformVersion", "8.3");
					capabilities.setCapability("browserName", "safari");
					// capabilities.setCapability("noReset", true);
					capabilities.setCapability("autoLaunch", true);
					capabilities.setCapability("autoAcceptAlerts", true);
					BROWSER = new IOSDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
					BROWSER.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

					break;

				case "Android":

					capabilities.setCapability("browsername", "chrome");
					capabilities.setCapability("app", "chrome");
					capabilities.setCapability("device", "ddd481e4063a5a82");
					capabilities.setCapability("deviceName", "android");
					capabilities.setCapability("platformversion", "4.2");
					capabilities.setCapability("platformname", "Android");
					BROWSER = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
					break;
				default:
					System.out.println("Specified capabilities are not set for native tesing");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
	}

	/**
	 * Instantiates a new browser di.
	 */
	public BrowserDI() {

		super(BROWSER);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.openqa.selenium.support.events.EventFiringWebDriver#close()
	 */
	@Override
	public void close() {
		if (Thread.currentThread() != CLOSE_THREAD) {
			throw new UnsupportedOperationException("WebDriver will shutdown when JVM exits");
		}
		super.close();
	}

	/**
	 * Before scenario.
	 *
	 * @author lmn490
	 * @param scenario
	 *            the scenario,TestSuiteRunID,TestSuiteName,Execution_mode
	 * @return
	 */
	@Before
	public void beforeScenario(Scenario scenario) {
		this.scenario = scenario;

		// String setTestSuiteName = scenario.getSourceTagNames().toString();
		// boolean value = setTestSuiteName.contains("Initial");
		//
		// if (value) {
		// if (System.getProperty("execPlatform") == null) {
		// String execEnv = map.get("environment");
		// setEnvironment = execEnv;
		// } else {
		// setEnvironment = execPlatform;
		// }
		// setTestSuiteName = lib.getTestSuiteName(setTestSuiteName);
		// // System.out.println(setTestSuiteName+"setTestSuiteName");
		// map.put("getTestSuiteName", setTestSuiteName);
		// map.put("getExecEnv", setEnvironment);
		// map.put("getTestSuiteRunid", lib.TestSuiteRunid());
		// // lib.setInistialEnv(map.get("getTestSuiteRunid"),
		// // map.get("Environment"), "4000", "Chrome");
		// System.out.println("###################Your TestSuiteRunID :-" +
		// map.get("getTestSuiteRunid")
		// + "#########################");
		// }
		//
		// if (value == false) {
		// lib.ReportHeader(map.get("getTestSuiteRunid"), Testcaseid,
		// map.get("getTestSuiteName"), scenario.getName(),
		// map.get("execution_mode"), map.get("Environment"));
		// }
		//
		// verificationErrors = new StringBuffer();

	}

	/**
	 * Embed a screenshot in test report if test is marked as failed.
	 *
	 * @author lmn490
	 * @param scenario
	 *            the scenario,TestSuiteRunID,TestSuiteName,Execution_mode
	 */

	@After
	public void afterScenario(Scenario scenario) {

		String setTestSuiteName = scenario.getSourceTagNames().toString();
		// boolean value = setTestSuiteName.contains("Initial");

		// if (value) {
		// lib.IniReportHeader(map.get("getTestSuiteRunid"), Testcaseid,
		// map.get("getTestSuiteName"),
		// scenario.getName(), map.get("execution_mode"));
		//
		// }
		// if (scenario.getStatus().contains("passed")) {
		// lib.ReportFooter(map.get("getTestSuiteRunid"), Testcaseid, "Passed",
		// "no error", "Finished");
		//
		// }
		// if (scenario.getStatus().contains("undefined")) {
		// lib.ReportFooter(map.get("getTestSuiteRunid"), Testcaseid, "Failed",
		// "undefined", "Undefined");
		//
		// }
		// if (scenario.getStatus().contains("pending")) {
		// lib.ReportFooter(map.get("getTestSuiteRunid"), Testcaseid, "Failed",
		// "pending", "Pending");
		//
		// }
		// if (scenario.getStatus().contains("skipped")) {
		// lib.ReportFooter(map.get("getTestSuiteRunid"), Testcaseid, "Failed",
		// "skipped", "Skipped");
		//
		// }
		//
		// if (scenario.isFailed())
		// lib.ReportFooter(map.get("getTestSuiteRunid"), Testcaseid, "Failed",
		// "no error", "Finished");
		//
		// {
		// try {
		// byte[] screenshot = getScreenshotAs(OutputType.BYTES);
		// scenario.embed(screenshot, "image/png");
		// // custom Image
		// File scrFile = ((TakesScreenshot)
		// BROWSER).getScreenshotAs(OutputType.FILE);
		// try {
		// lib.mkdirtest(map.get("getTestSuiteRunid"));
		// File theDir = new File(defaultLogFile + "/" +
		// map.get("getTestSuiteRunid"));
		//
		// FileUtils.copyFile(scrFile, new File(theDir, scenario.getName() +
		// ".jpg"));
		// lib.ReportFooter(map.get("getTestSuiteRunid"), Testcaseid, "Failed",
		// theDir + "/" + scenario.getName() + ".jpg", "Finished");
		//
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// } catch (WebDriverException somePlatformsDontSupportScreenshots) {
		// System.err.println(somePlatformsDontSupportScreenshots.getMessage());
		// }

		// }

		// if (verificationErrors.length() != 0) {
		// Assert.fail(verificationErrors.toString());
		// }
	}

	/**
	 * Soft assert true.
	 *
	 * @param message
	 *            the message
	 * @param condition
	 *            the condition
	 */
	public void softAssertTrue(String message, boolean condition) {
		try {
			Assert.assertTrue(condition);
		} catch (AssertionError e) {
			verificationErrors.append(message);
			try {
				byte[] screenshot = getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");

			} catch (WebDriverException somePlatformsDontSupportScreenshots) {
				System.err.println(somePlatformsDontSupportScreenshots.getMessage());
			}

		}

	}

	/**
	 * Sets the browser attributes.
	 *
	 * @param waitTime
	 *            the new browser attributes
	 */
	private static void setBrowserAttributes(int waitTime) {
		setBrowserCleanup();
		// BROWSER.manage().window().maximize();
		BROWSER.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
	}
	// clear the cache

	/**
	 * Sets the browser cleanup.
	 */
	private static void setBrowserCleanup() {

		//
		// ResourceBundle.clearCache();
		//
		// BROWSER.manage().deleteAllCookies();
		//

	}

	public String getTestSuiteName() {
		// Collection<String> test = scenario.getSourceTagNames();
		String setTestSuiteName = scenario.getSourceTagNames().toString();
		String[] parts = setTestSuiteName.split(", ", 10);
		int TotalTags = parts.length;
		setTestSuiteName = parts[TotalTags - 1];
		String getTestSuiteName = setTestSuiteName.substring(11);
		int inTestSuiteName = setTestSuiteName.indexOf("]");
		getTestSuiteName = setTestSuiteName.substring(11, inTestSuiteName);
		return getTestSuiteName;

	}

	/**
	 * Gets the properties value.
	 *
	 * @return the properties value
	 */
	public static void getpropertiesValue() {
		InputStream setFilePath = null;
		List<String> fileList = new ArrayList<String>();
		// If this pathname does not denote a directory, then listFiles()

		fileList.add(workingdirectory + "/ParmSetting.properties");
		fileList.add(workingdirectory + "/src/test/environments/config.properties");
		fileList.add(workingdirectory + "/src/test/resources/TestData/global/config.properties");

		for (String filePath : fileList) {
			try {
				setFilePath = new FileInputStream(filePath);
				ResourceBundle resources = new PropertyResourceBundle(setFilePath);

				// convert ResourceBundle to Map
				Enumeration<String> keys = resources.getKeys();
				while (keys.hasMoreElements()) {
					String key = keys.nextElement();
					map.put(key, resources.getString(key));

				}
				// Now you can use the 'map' object as you wish.

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
