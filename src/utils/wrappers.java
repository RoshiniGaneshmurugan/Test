package utils;
 
import java.util.List;
 
import org.openqa.selenium.WebElement;
 
public interface wrappers {
 
             
              /**
               * This method will launch the given browser and maximise the browser and set the
               * wait for 30 seconds and load the url
               * @author Babu - TestLeaf
               * @param browser - The browser of the application to be launched
               * @param url - The url with http or https
               * @throws Exception
               *
               */
           public void invokeApp(String browser, String Url) throws Exception;
 
              /**
               * This method will enter the value to the text field using xpath attribute to locate
               *
               * @param xpathValue - name of the webelement
               * @param data - The data to be sent to the webelement
               * @author Babu - TestLeaf
               */
              public void enterByXpath(String xpathValue, String data) throws Exception;
              public void enterByID(String idValue, String data) throws Exception;
 
 
              /**
               * This method will verify the title of the browser
               * @param title - The expected title of the browser
               * @author Babu - TestLeaf
               */
              public boolean verifyTitle(String title);
 
              /**
               * This method will click the element using id as locator
               * @param id  The id (locator) of the element to be clicked
               * @author Babu - TestLeaf
               * @throws Exception
               */
              public void clickByClassName(String classVal) throws Exception;
             
              /**
               * This method will click the element using xpath as locator
               * @param xpathVal  The xpath (locator) of the element to be clicked
               * @author Babu - TestLeaf
               */
            //  public void ClickByXpath(String xpathVal) throws Exception;
             
              /**
               * This method will take snapshot of the browser
               * @author Babu - TestLeaf
               */
              public void takeSnap();
                    
              /**
               * This method will close the active browser
               * @author Babu - TestLeaf
               */
              public void closeBrowser();
             
              /**
               * This method move the cursor to specified element
               * @author Babu - TestLeaf
               */
              public void moveToElementAndClick(WebElement xpathVal, String x, String y);
                          
              /**
               * This method will select the specified month
               * @author Babu - TestLeaf
               */
             
              public void getTextByClassName(String classname);
             
              /**
               * This method will get the text by using xpath
               */
              //public String getTextByXpath(String xpath);
              
             
              /**
               * This method will select the specified date
               */
              public void selectDate(String xpathVal, String expected_date);
             
              /**
               * This method will select all the element under the specified locator
               */
              public List<WebElement> clickByXpathElements(String xpathVal);
             
              /**
               * This method will scroll the page
               */
              public void scroll(String x, String y);
             
              /**
               * This method will select the elements using tagname from WebElement
               */
              public void selectByTagNameFromElement(String classname, String tag);
             
              /**
               * This method will select the Gender
               */
              public void waitByXpath(String Xpath);
             
              /**
               * This method will for the element using class name
               */
              public void waitByClassName(String ClassName);
             
}
 