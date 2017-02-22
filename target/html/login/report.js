$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/features/login/login.feature");
formatter.feature({
  "comments": [
    {
      "line": 1,
      "value": "# ALM Test Case ref :-"
    },
    {
      "line": 2,
      "value": "# Reviewed by :-\t\tAshok Natarajan"
    }
  ],
  "line": 3,
  "name": "qbakbak login page",
  "description": "",
  "id": "qbakbak-login-page",
  "keyword": "Feature"
});
formatter.before({
  "duration": 90893308950,
  "status": "passed"
});
formatter.scenario({
  "line": 16,
  "name": "TS001_verify the Login page by clciking on the sign upbutton",
  "description": "",
  "id": "qbakbak-login-page;ts001-verify-the-login-page-by-clciking-on-the-sign-upbutton",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 15,
      "name": "@regression2"
    },
    {
      "line": 15,
      "name": "@smoke2"
    },
    {
      "line": 15,
      "name": "@bvts2"
    }
  ]
});
formatter.step({
  "line": 17,
  "name": "Iam on the qbakbak login page",
  "keyword": "Given "
});
formatter.step({
  "line": 18,
  "name": "I should be able to click on signup button",
  "keyword": "When "
});
formatter.step({
  "line": 19,
  "name": "verify iam on the Sign up page",
  "keyword": "Then "
});
formatter.match({
  "location": "LoginHappyPath.iam_on_the_qbkbak_login_page()"
});
formatter.result({
  "duration": 82674378677,
  "status": "passed"
});
formatter.match({
  "location": "LoginHappyPath.i_should_be_able_to_click_on_signup_button()"
});
formatter.result({
  "duration": 30034737647,
  "error_message": "org.openqa.selenium.NoSuchElementException: no such element: Unable to locate element: {\"method\":\"class name\",\"selector\":\"btn-signup\"}\n  (Session info: chrome\u003d56.0.2924.87)\n  (Driver info: chromedriver\u003d2.24.417431 (9aea000394714d2fbb20850021f6204f2256b9cf),platform\u003dWindows NT 10.0.14393 x86_64) (WARNING: The server did not provide any stacktrace information)\nCommand duration or timeout: 30.03 seconds\nFor documentation on this error, please visit: http://seleniumhq.org/exceptions/no_such_element.html\nBuild info: version: \u00273.0.1\u0027, revision: \u00271969d75\u0027, time: \u00272016-10-18 09:49:13 -0700\u0027\nSystem info: host: \u0027yuvi\u0027, ip: \u0027192.168.1.36\u0027, os.name: \u0027Windows 10\u0027, os.arch: \u0027amd64\u0027, os.version: \u002710.0\u0027, java.version: \u00271.8.0_91\u0027\nDriver info: org.openqa.selenium.chrome.ChromeDriver\nCapabilities [{applicationCacheEnabled\u003dfalse, rotatable\u003dfalse, mobileEmulationEnabled\u003dfalse, networkConnectionEnabled\u003dfalse, chrome\u003d{chromedriverVersion\u003d2.24.417431 (9aea000394714d2fbb20850021f6204f2256b9cf), userDataDir\u003dC:\\Users\\yuvi_007\\AppData\\Local\\Temp\\scoped_dir4476_12979}, takesHeapSnapshot\u003dtrue, pageLoadStrategy\u003dnormal, databaseEnabled\u003dfalse, handlesAlerts\u003dtrue, hasTouchScreen\u003dfalse, version\u003d56.0.2924.87, platform\u003dXP, browserConnectionEnabled\u003dfalse, nativeEvents\u003dtrue, acceptSslCerts\u003dtrue, locationContextEnabled\u003dtrue, webStorageEnabled\u003dtrue, browserName\u003dchrome, takesScreenshot\u003dtrue, javascriptEnabled\u003dtrue, cssSelectorsEnabled\u003dtrue}]\nSession ID: 69ec9499802219dd720aebfa91a912d2\n*** Element info: {Using\u003dclass name, value\u003dbtn-signup}\r\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)\r\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance(Unknown Source)\r\n\tat sun.reflect.DelegatingConstructorAccessorImpl.newInstance(Unknown Source)\r\n\tat java.lang.reflect.Constructor.newInstance(Unknown Source)\r\n\tat org.openqa.selenium.remote.ErrorHandler.createThrowable(ErrorHandler.java:216)\r\n\tat org.openqa.selenium.remote.ErrorHandler.throwIfResponseFailed(ErrorHandler.java:168)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:635)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:368)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElementByClassName(RemoteWebDriver.java:457)\r\n\tat org.openqa.selenium.By$ByClassName.findElement(By.java:391)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:360)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)\r\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n\tat java.lang.reflect.Method.invoke(Unknown Source)\r\n\tat org.openqa.selenium.support.events.EventFiringWebDriver$2.invoke(EventFiringWebDriver.java:103)\r\n\tat com.sun.proxy.$Proxy20.findElement(Unknown Source)\r\n\tat org.openqa.selenium.support.events.EventFiringWebDriver.findElement(EventFiringWebDriver.java:187)\r\n\tat org.openqa.selenium.support.pagefactory.DefaultElementLocator.findElement(DefaultElementLocator.java:69)\r\n\tat org.openqa.selenium.support.pagefactory.internal.LocatingElementHandler.invoke(LocatingElementHandler.java:38)\r\n\tat com.sun.proxy.$Proxy22.click(Unknown Source)\r\n\tat com.ads.steps.LoginHappyPath.i_should_be_able_to_click_on_signup_button(LoginHappyPath.java:58)\r\n\tat ✽.When I should be able to click on signup button(src/test/resources/features/login/login.feature:18)\r\n",
  "status": "failed"
});
formatter.match({
  "location": "LoginHappyPath.verify_iam_on_the_Sign_up_page()"
});
formatter.result({
  "status": "skipped"
});
formatter.after({
  "duration": 452943,
  "status": "passed"
});
});{
  "status": "skipped"
});
formatter.after({
  "duration": 599137,
  "status": "passed"
});
});