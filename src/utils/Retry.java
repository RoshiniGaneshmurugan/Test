package utils;

import com.relevantcodes.extentreports.LogStatus;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	
	
	 private int count = 0;
	 private int j=1;
	    private static int maxTry = 0; //Run the failed test 2 times
	 
	    @Override
	    public boolean retry(ITestResult iTestResult) {
	        if (!iTestResult.isSuccess()) {                      //Check if test not succeed
	            if (count < maxTry) {                            //Check if maxtry count is reached
	                count++;                                     //Increase the maxTry count by 1
	                iTestResult.setStatus(ITestResult.FAILURE);  //Mark test as failed
	                extendReportsFailOperations(iTestResult);    //ExtentReports fail operations
	                return true;                                 //Tells TestNG to re-run the test
	            }
	        } else {
	            iTestResult.setStatus(ITestResult.SUCCESS);      //If test passes, TestNG marks it as passed
	        }
	        return false;
	    }
	 
	    public void extendReportsFailOperations (ITestResult iTestResult) {
	        Object testClass = iTestResult.getInstance();
	        TakesScreenshot webDriver = null;
			//WebDriver webDriver = ((FFCtestng) testClass).driver;
	        String base64Screenshot = "data:image/png;base64,"+((TakesScreenshot)webDriver).getScreenshotAs(OutputType.BASE64);
	        ExtentTestManager.getTest().log(LogStatus.FAIL,"Test Failed",
	                ExtentTestManager.getTest().addScreenCapture(base64Screenshot));
	        try {
				FileUtils.copyFile(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE),
				        new File("./Failure/" + "FFCtest" + j + ".jpg"));
				              j=j+1;
			} catch (WebDriverException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e){
				e.printStackTrace();
			}
	        
	    }

}
