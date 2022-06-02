import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class testeGoogle {
   
    
    WebDriver driver = new ChromeDriver();
    String CAMINHO_DRIVER = "src/resource/chromedriver-v10205005.exe";
    
    @Test
    public void iniciar (){

    System.setProperty("webdriver.chrome.driver", CAMINHO_DRIVER);
    driver.get("https://www.google.com.br");
    driver.manage().window().maximize();
    Assert.assertEquals("Google", driver.getTitle());
    driver.quit();

    }
}
