package utils;

import com.relevantcodes.extentreports.LogStatus;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener{
	
	public static WebDriver driver;
	private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
    
    //Before starting all tests, below method runs.
    @Override
    public void onStart(ITestContext iTestContext) {
        
        iTestContext.setAttribute("WebDriver", this.driver);
    }
 
    //After ending all tests, below method runs.
    @Override
    public void onFinish(ITestContext iTestContext) {
        
        //Do tier down operations for extentreports reporting!
        ExtentTestManager.endTest();
        ExtentManager.getReporter().flush();
    }
 
    @Override
    public void onTestStart(ITestResult iTestResult) {
        
    	ExtentTestManager.startTest(iTestResult.getMethod().getMethodName(),"");
    }
 
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        
    	ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
    }
 
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        //Get driver from BaseTest and assign to local webdriver variable.
        Object testClass = iTestResult.getInstance();
        
        //WebDriver webDriver = ((FFCtestng) testClass).driver;
 
        //Take base64Screenshot screenshot.
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
       String base64Screenshot = "data:image/png;base64,"+((TakesScreenshot)driver).
              getScreenshotAs(OutputType.BASE64);
        try {
			FileUtils.copyFile(src,new File("./Failure/" + src.getName()));
			ExtentTestManager.getTest().log(LogStatus.FAIL, iTestResult.getMethod().getMethodName() + "test is failed",ExtentTestManager.getTest().addScreenCapture(base64Screenshot));
			
			ExtentTestManager.getTest().log(LogStatus.FAIL, iTestResult.getMethod().getMethodName() + "test is failed", iTestResult.getThrowable().getMessage());
        } catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 }
 
    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        
    	ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped", getTestMethodName(iTestResult));
    }
 
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }

}
