package utils;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;


public class ExtentManager {
	
	private static ExtentReports extent;
	public synchronized static ExtentReports getReporter(){
        if(extent == null){
        	//Set HTML reporting file location
            String workingDir = System.getProperty("user.dir");
            //DateFormat dateFormat = new SimpleDateFormat("MM_dd_yyyy_hh_mm_ss");
           //Date date = new Date();
            //String date1= dateFormat.format(date);
            String extentreports="./ExtentReports/" +"reports"+".html";
            extent = new ExtentReports(extentreports, true);
        }
        return extent;
    }


}
