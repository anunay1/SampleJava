package com.mytests;

import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;


public class excelexplore {
	public static WindowsDriver session= null;
	public  static  WindowsDriver desktopsession = null;
	public  String WindowsApplicationDriverUrl = "http://127.0.0.1:4723";
	
	@BeforeClass
	public void setUp() throws InterruptedException, MalformedURLException {
		

		
		//appCapabilities.setCapability(capabilityName, value);
		try {
			DesiredCapabilities appCapabilities = new DesiredCapabilities();
			appCapabilities.setCapability("app", "Excel");
			appCapabilities.setCapability("appArguments", "/e");
			appCapabilities.setCapability("appArguments", "C:\\Bug1257\\file\\VBALogin.xlsm");

			//	appCapabilities.setCapability("app", "C:\\Users\\LENOVO\\eclipse-workspace\\WinAppDriverSample\\testdata\\VBALogin.xlsm");
			appCapabilities.setCapability("platformName", "Windows");
			appCapabilities.setCapability("deviceName", "WindowsPC");
			session = new WindowsDriver<WindowsElement>(new URL(WindowsApplicationDriverUrl), appCapabilities);
			Thread.sleep(20000);
			session.findElementByName("File");
			
		} catch (Exception e) {

				if (e.getMessage().contains("Currently selected window has been closed"))
				{
					DesiredCapabilities capabilities = new DesiredCapabilities();
					capabilities.setCapability("app", "Root");
					desktopsession = new WindowsDriver<WindowsElement>(new
					URL(WindowsApplicationDriverUrl),capabilities);


					String topWindow = desktopsession.findElementByClassName("XLMAIN").getAttribute("NativeWindowHandle");
					int MAWinHandleInt = Integer.parseInt(topWindow);
					String MAWinHandleHex = Integer.toHexString(MAWinHandleInt);

					DesiredCapabilities caps = new DesiredCapabilities();
					caps.setCapability("appTopLevelWindow", MAWinHandleHex);
					session = new WindowsDriver<WindowsElement>(new URL(WindowsApplicationDriverUrl), caps);

				}

		}
		
		
		

		//session.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		

	}
	
	
	@AfterMethod
	public void cleanup()
	{
		session.quit();
		
	}
	
	
	
	@Test
	public void checkHelp() throws Exception {
		

	    Thread.sleep(4000);
		//session.findElementByName("Message Bars").findElement(By.name("Security Warning")).findElement(By.name("Enable Content")).click();
		//session.findElementByName("Enable Editing").click();
		//session.findElementByName("Enable Content").click();
		//List<WindowsElement> = session.findElementsByName("Enter Your Password");
		int count = session.findElementsByName("Enter Your Password").size();

		WebElement ele = (WebElement) session.findElementsByName("Enter Your Password").get(1);
		ele.findElement(By.name("Open")).click();

		
		//session.findElementsByName("Enter Your Password").get(1);
	    Thread.sleep(4000); 

	}

}