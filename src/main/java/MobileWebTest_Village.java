import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class MobileWebTest_Village {

    public static final String URL = "https://staff.demo.fusion-team.com/";
    public static final String USER_admin = "fusion.team.llc@gmail.com";
    public static final String PASSWORD_admin = "AAAqqq111";

    @Test
    public void testIncorrectFBLogin() throws Exception {
        System.out.println("Step 1. Create new driver");
        AppiumDriver driver = getAppiumDriver("iOS", new URL("http://127.0.0.1:4723/wd/hub"));
        WebDriverWait wait = new WebDriverWait(driver, 30);

        System.out.println("Step 2. Open staff");
        driver.get(URL);

        System.out.println("Step 3. Enter email");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("login")));
        driver.findElement(By.name("login")).sendKeys(USER_admin);

        System.out.println("Step 4. Enter password");
        driver.findElement(By.name("password")).sendKeys(PASSWORD_admin);

        System.out.println("Step 5. Click Login button");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/form/button/span[1]")).click();

//        System.out.println("Step 6. Check error message");
//        Thread.sleep(5000);
//        assertTrue(driver.findElement(By.xpath("//*[@id=\"root\"]/h3")).getText().contains("Добро пожаловать!"));

        System.out.println("Step 6. Close driver");
        driver.quit();
    }

    private AppiumDriver getAppiumDriver(String platform, URL serverUrl) {
        return platform.equals("iOS") ? getIOSDriver(serverUrl) : getAndroidDriver(serverUrl);
    }

    private AppiumDriver getIOSDriver(URL serverUrl) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "14.3");

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 11 Pro");
        capabilities.setCapability(MobileCapabilityType.UDID, "7B8ACE3A-FC8D-4010-88B5-5E37F9BC0248");

        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, "true");

        capabilities.setCapability("showXcodeLog", "true");
        capabilities.setCapability("autoAcceptAlerts", "true");

        return new IOSDriver(serverUrl, capabilities);
    }

    private AppiumDriver getAndroidDriver(URL serverUrl) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus 6");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Browser");

        return new AndroidDriver(serverUrl, capabilities);
    }


}
