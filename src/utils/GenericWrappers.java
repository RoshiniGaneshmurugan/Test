package utils;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
 
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class GenericWrappers implements wrappers {
 
       public static RemoteWebDriver driver;
       protected static Properties prop;
       public String sUrl, sHubUrl, sHubPort;
       static int i=1;
 
       /* public GenericWrappers(RemoteWebDriver driver) { this.driver = driver; }*/ 
 
       public void loadObjects() {
              prop = new Properties();
              try {
                     prop.load(new FileInputStream(new File("./objectsdata/object.properties")));
              } catch (FileNotFoundException e) {
                     e.printStackTrace();
              } catch (IOException e) {
                     e.printStackTrace();
              }
 
       }
 
       public void unloadObjects() {
              prop = null;
       }
 
       /**
        * This method will launch the browser in local machine and maximise the browser
        * and set the wait for 30 seconds and load the url
        *
        * @param url
        *            - The url with http or https
        *
        */
       public void invokeApp(String browser, String Url) {
              invokeApp(browser, false, Url);
       }
 
       /**
        * This method will launch the browser in grid node (if remote) and maximise the
        * browser and set the wait for 30 seconds and load the url
        *
        * @param url
        *            - The url with http or https
        *
        */
       public void invokeApp(String browser, boolean bRemote, String surl) {
              try {
 
                     DesiredCapabilities dc = new DesiredCapabilities();
                     dc.setCapability("disable-popup-blocking", true);
                     dc.setBrowserName(browser);
                     dc.setPlatform(Platform.WINDOWS);
 
                     // this is for grid run
                     if (bRemote)
                           driver = new RemoteWebDriver(new URL("http://" + sHubUrl + ":" + sHubPort + "/wd/hub"), dc);
                     else { // this is for local run
                           if (browser.equalsIgnoreCase("chrome")) {
                                  System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
                                  driver = new ChromeDriver();
                           } else if (browser.equalsIgnoreCase("firefox")) {
                                  System.setProperty("webdriver.gecko.driver", "./Drivers/geckodriver.exe");
                                  driver = new FirefoxDriver();
                           } else {
                        	   System.setProperty("webdriver.ie.driver", "./Drivers/IEDriverServer.exe");
                        	   driver = new InternetExplorerDriver();
                           }
                     }
                     driver.manage().window().maximize();
                     driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                     driver.get(surl);
                     System.out.println("URL: " + surl);
              } catch (Exception e) {
                     System.out.println("Exception: " + e);
              }
       }
 
       /**
        * This method will enter the value to the text field using name attribute to
        * locate
        *
        * @param xpathValue
        *            - xpathValue of the webelement
        * @param data
        *            - The data to be sent to the webelement
        *
        * @throws IOException
        *
        */
       public void enterByID(String idValue, String data) {
           try {
                  driver.findElement(By.id(idValue)).clear();
                  driver.findElement(By.id(idValue)).sendKeys(data);
           } catch (Exception e) {
                  System.out.println("Exception: " + e);
           }
    }

       
       
       public   void enterByXpath(String xpathValue, String data) {
              try {
                     driver.findElement(By.xpath(xpathValue)).clear();
                     driver.findElement(By.xpath(xpathValue)).sendKeys(data);
              } catch (Exception e) {
                     System.out.println("Exception: " + e);
              }
       }
 
       /**
        * This method will verify the title of the browser
        *
        * @param title
        *            - The expected title of the browser
        *
        */
       public boolean verifyTitle(String title) {
              boolean bReturn = false;
              try {
                     if (driver.getTitle().equalsIgnoreCase(title)) {
                           bReturn = true;
                     } else
                           System.out.println("Values are not matching for title");
              } catch (Exception e) {
                     System.out.println("Exception: " + e);
              }
              return bReturn;
       }
 
       public void closeBrowser() {
              try {
                     driver.close();
              } catch (Exception e) {
                     System.out.println("Exception: " + e);
              }
       }
 
       /**
        * This method will click the element using id as locator
        *
        * @param id
        *            The id (locator) of the element to be clicked
        *
        */
       public static void ClickByID(String idVal) {
           try {
        	   WebDriverWait wait=new WebDriverWait(driver,10);
               wait.until(ExpectedConditions.presenceOfElementLocated(By.id(idVal))).click();                 

           } catch (Exception e) {
                  System.out.println("Exception: " + e);
           }
    }
       
       public void clickByClassName(String classVal) {
              try {
                     driver.findElement(By.className(classVal)).click();
              } catch (Exception e) {
                     System.out.println("Exception: " + e);
              }
       }
 
       /**
        * This method will click the element using xpath as locator
        *
        * @param xpathVal
        *            The xpath (locator) of the element to be clicked
        *
        */
       public void ClickByXpath(String xpathVal) {
              try {
                    
         driver.findElement(By.xpath(xpathVal)).click();
              } catch (Exception e) {
                     System.out.println("Exception: " + e);
              }
       }
      
       /**
        * This method will select all the element under the specified locator
        */
       public List<WebElement> clickByXpathElements(String xpathVal) {
              List<WebElement> val=null;
              try {
                     val = driver.findElements(By.xpath(xpathVal));
              } catch (Exception e) {
                     System.out.println("Exception: " + e);
              }            
              return val;
       }
 
      public static void waitToLoad(int x) {
    	  try {
    		  Thread.sleep(x);
    	  }
    	  catch(Exception e) {
    		  System.out.println("Exception: " + e);
    	  }
      }
       
       
       /**
        * This method will mouse over on the element using xpath as locator
        */
       public void moveToElementAndClick(WebElement xpathVal, String x, String y) {
              try {
                     Thread.sleep(2000);
                     new Actions(driver).moveToElement(xpathVal, Integer.parseInt(x), Integer.parseInt(y)).click().build().perform();
              } catch (Exception e) {
                     System.out.println("Exception: " + e);
              }
       }
 
       /**
        * This method will take snapshot of the browser
        */
       public void takeSnap() {
              try {
                     FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE),
                                  new File("./snapshot/" + "Redbus" + i + ".jpg"));
              } catch (Exception e) {
                     System.out.println("Exception: " + e);
              }
              i++;  
       }
      
       /**
        * This method will get the text by using xpath
        * @return
        */
     /*  public String getTextByXpath(String xpath) {        
              String text="";
              try{
              text= driver.findElementByXPath(xpath).getText();
              
              } catch (Exception e) {
                    
              }
              return text;
       }*/
 
       /**
        * This method will select the specified month
        */
      
      
       /**
        * This method will select the specified date
        */
       public void selectDate(String xpathVal, String expected_date) {
              List<WebElement> days= clickByXpathElements(xpathVal);
           for (WebElement day : days) {
                     if(day.getText().equals(expected_date)){
                           day.click();
                     }
              }
       }
             
      
       /**
        * This method will get the text by using classname
        */
       public void getTextByClassName(String classname) {
              try {
                     driver.findElement(By.className(classname)).getText();
              } catch (Exception e) {
                     System.out.println("Exception: " + e);
              }
       }
      
       /**
        * This method will scroll the page
        */
       public void scroll(String x, String y) {
              JavascriptExecutor je= (JavascriptExecutor)driver;
              String value ="window.scrollBy("+Integer.parseInt(x)+","+Integer.parseInt(y)+")";
              je.executeScript(value);
       }
             
       /**
        * This method will select the elements using tagname from WebElement
        */
       public void selectByTagNameFromElement(String classname, String tag) {
              try {
                     List<WebElement> details=driver.findElement(By.className(classname)).findElements(By.tagName(tag));
                     for(WebElement detail:details) {
                    System.out.println(detail.getText());
               }
              } catch (Exception e) {
                     System.out.println("Exception: " + e);
              }
       }
      
       /**
        * This method will select the Gender
        */
       public void selectGender(String classname, String gender) {
              try {
                     if(("male").equals(gender)) {
                     driver.findElements(By.className(classname)).get(0).click();
               }
                     else {
                     driver.findElements(By.className(classname)).get(1).click();
                     }
                          
              } catch (Exception e) {
                     System.out.println("Exception: " + e);
              }            
       }
 
       /**
        * This method will for the element using xpath
        */
       public void waitByXpath(String xpath) {
              WebDriverWait wait=new WebDriverWait(driver,10);
              wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
       }
 
       /**
        * This method will for the element using classname
        */
       public void waitByClassName(String className) {           
              WebDriverWait wait=new WebDriverWait(driver,10);
              wait.until(ExpectedConditions.presenceOfElementLocated(By.className(className)));
       }
      
}