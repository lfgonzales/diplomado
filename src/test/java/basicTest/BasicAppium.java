package basicTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BasicAppium {

    private AppiumDriver driver;

    @BeforeEach
    public void setup() throws MalformedURLException {
        DesiredCapabilities capabilities= new DesiredCapabilities();
        capabilities.setCapability("deviceName","dogor");
        capabilities.setCapability("platformVersion","8.1.0");
        capabilities.setCapability("appPackage","com.vrproductiveapps.whendo");
        capabilities.setCapability("appActivity","com.vrproductiveapps.whendo.ui.HomeActivity");
        capabilities.setCapability("platformName","Android");

        driver =new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
    @AfterEach
    public void cleanup() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }
    @Test
    public void CrudWhenDo() throws InterruptedException{

        //Create
        String nota= "I have to gym, beacuse I am Dogor";
        String newNota= "I am Dogor";
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/fab")).click();
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextTitle")).sendKeys("Tarea para hoy");
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextNotes")).sendKeys(nota);
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/saveItem")).click();

        Assertions.assertEquals(true,driver.findElement(By.id("com.vrproductiveapps.whendo:id/fab")).isDisplayed());
        Thread.sleep(1000);

        //Update
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/home_list_item_text")).click();
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextNotes")).sendKeys(newNota);
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/saveItem")).click();

        Assertions.assertEquals(newNota,driver.findElement(By.id("com.vrproductiveapps.whendo:id/home_list_item_text2")).getText().trim());
        Thread.sleep(1000);
        //Delete
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/home_list_item_marked")).click();
        driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"Más opciones\"]")).click();
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView")).click();
        driver.findElement(By.id("android:id/button1")).click();
        boolean existe = driver.findElements( By.id("com.vrproductiveapps.whendo:id/noteTextTitle") ).size() != 0;

        Assertions.assertEquals(false,existe);
        Thread.sleep(1000);
    }

}
