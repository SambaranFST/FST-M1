import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Job {
    WebDriver driver;
    @BeforeClass
    public void beforeMethod() {
        // Set up Firefox driver
        WebDriverManager.firefoxdriver().setup();
        // Create a new instance of the Firefox driver
         driver = new FirefoxDriver();
        // Create the Wait object
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Create the Actions object
        Actions builder = new Actions(driver);

        // Open the page
        driver.get("https://alchemy.hguy.co/jobs/");
        // Print the title of the page

    }
    @Test (priority = 1)
    public void Activity1() {
        String MatchTitle1 = driver.getTitle();
        System.out.println("First Title:" + MatchTitle1);

        if (MatchTitle1.contains("Alchemy Jobs – Job Board Application")) {
            System.out.println("First Title Matched");
        } else {
            System.out.println("First Title Failed");
        }
    }
    @Test (priority = 2)
    public void Activity2() {
        String Heading=driver.findElement(By.cssSelector(".entry-title")).getText();
        System.out.println("Activity2 Heading:"+Heading);
        if (Heading.contains("Welcome to Alchemy Jobs")) {
            System.out.println("Heading Matched");
        } else {
            System.out.println(" Failed");
        }
    }
    @Test (priority = 3)
    public void Activity3() {
        WebElement img = driver.findElement(By.xpath("//article[@id='post-16']/header/div/img"));
        String src = img.getAttribute("src");
        System.out.println("Image URL:"+src);
    }
    @Test (priority = 4)
    public void Activity4() {
        String MatchText4 = driver.findElement(By.xpath("/html/body/div/div/div/div/main/article/div/h2")).getText();
        System.out.println("Second Text:" + MatchText4);
        if (MatchText4.contains("Quia quis non")) {
            System.out.println("Third Text Matched");
        } else {
            System.out.println("Third Title Failed");
        }
    }
    @Test (priority = 5)
    public void Activity5() {
        driver.findElement(By.linkText("Jobs")).click();
        String secondPageTitle = driver.getTitle();
        System.out.println("Job Page Title:"+secondPageTitle);
        if (secondPageTitle.contains("Jobs – Alchemy Jobs")) {
            System.out.println("Job page title Matched");
        } else {
            System.out.println(" Failed");
        }
    }
    @Test (priority = 6)
    public void Activity6() throws InterruptedException {
        driver.findElement(By.linkText("Jobs")).click();
        //search_keywords
        driver.findElement(By.id("search_keywords")).click();
        driver.findElement(By.id("search_keywords")).sendKeys("Banking");
        driver.findElement(By.xpath("//input[@value='Search Jobs']")).click();
        JavascriptExecutor js1 = (JavascriptExecutor)driver;
        js1.executeScript("window.scrollBy(0,100)");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//article[@id='post-7']/div/div/ul/li/a/div/h3")).click();
        driver.findElement(By.xpath("//input[@value='Apply for job']")).click();
        String str1=driver.findElement(By.xpath("//a[contains(text(),'san@gmail.com')]")).getText();
        System.out.println("Email id"+str1);
    }
    @Test (priority = 7)
    public void Activity7() throws InterruptedException {
        driver.findElement(By.linkText("Post a Job")).click();
        //create_account_email
        driver.findElement(By.id("create_account_email")).sendKeys("Samba@ibm.com");
        //job_title
        driver.findElement(By.id("job_title")).sendKeys("Test_Job");
        //job_location
        driver.findElement(By.id("job_location")).sendKeys("Kolkata");
        //job_type
        Select dropdown= new Select(driver.findElement(By.id("job_type")));
        dropdown.selectByIndex(2);
        //tinymce tinymce
        JavascriptExecutor jse = (JavascriptExecutor)driver;
         jse.executeScript("window.scrollBy(0,300)");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Thread.sleep(2000);


        driver.switchTo().frame(0);
        driver.findElement(By.xpath("//body[@id='tinymce']/p")).click();
        WebElement element1=driver.findElement(By.id("tinymce"));
          element1.click();
           element1.sendKeys("Test");

        driver.switchTo().defaultContent();
        //application
        driver.findElement(By.id("application")).sendKeys("sam@gmail.com");
        //company_name
        driver.findElement(By.id("company_name")).sendKeys("SAM");
        //company_website
        driver.findElement(By.id("company_website")).sendKeys("https://alchemy.hguy.co/jobs/");
        //company_tagline
        driver.findElement(By.id("company_website")).sendKeys("Sam");
        // Preview
        driver.findElement(By.xpath("//input[@name='submit_job']")).click();
        //job_preview_submit_button
        driver.findElement(By.id("job_preview_submit_button")).click();
        //click here

        driver.findElement(By.linkText("click here")).click();
        Thread.sleep(2000);
    }
    @Test (priority = 8)
    public void Activity8() throws InterruptedException {
        driver.findElement(By.linkText("Post a Job")).click();
        driver.findElement(By.linkText("Sign in")).click();

        driver.findElement(By.id("user_login")).sendKeys("sambacha2");
        driver.findElement(By.id("user_pass")).sendKeys("Sambaran@@@2024");
        //wp-submit
        driver.findElement(By.id("wp-submit")).click();
    }
    @Test (priority = 9)
    public void Activity9() throws InterruptedException {
        driver.get("https://alchemy.hguy.co/jobs/wp-admin");
        driver.findElement(By.id("user_login")).sendKeys("root");
        driver.findElement(By.id("user_pass")).sendKeys("pa$$w0rd");
        //wp-submit
        driver.findElement(By.id("wp-submit")).click();
        driver.findElement(By.xpath("//li[@id='menu-dashboard']/a/div[3]")).click();
        driver.findElement(By.xpath("//li[@id='menu-posts-job_listing']/a/div[3]")).click();
        driver.findElement(By.xpath("(//a[contains(text(),'Add New')])[7]")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //post-title-0
     //   Thread.sleep(5000);
        String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
        String subWindowHandler = null;

        Set<String> handles = driver.getWindowHandles(); // get all window handles
        Iterator<String> iterator = handles.iterator();
        subWindowHandler = iterator.next();
        driver.switchTo().window(subWindowHandler);

        String str22=driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div/div/div/div/div[2]/div[1]/p")).getText();
        System.out.println(str22);
        driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div/div/div/div/div[2]/div[2]/button")).click();
        driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div/div/div/div/div[2]/div[2]/button[2]")).click();
        driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div/div/div/div/div[2]/div[2]/button[2]")).click();
        driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div/div/div/div/div[2]/div[2]/button[2]")).click();

         driver.switchTo().window(parentWindowHandler);
        Thread.sleep(5000);
        //post-title-0
        driver.findElement(By.id("post-title-0")).sendKeys("Sam");
        JavascriptExecutor js2 = (JavascriptExecutor)driver;
        js2.executeScript("window.scrollBy(0,300)");
        driver.findElement(By.id("_job_location")).sendKeys("London");
        driver.findElement(By.id("_company_name")).sendKeys("IBM");
        driver.findElement(By.id("_company_website")).sendKeys("http://google.com");
        //(//button[@type='button'])[9]
        driver.findElement(By.xpath("//button[contains(.,'Publish…')]")).click();
        driver.findElement(By.cssSelector(".editor-post-publish-button")).click();
    }
    @AfterClass
    public void afterMethod() {
        //Close the browser
        driver.close();
    }

}