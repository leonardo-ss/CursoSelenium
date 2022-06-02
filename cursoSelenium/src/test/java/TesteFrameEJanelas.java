import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteFrameEJanelas{

    WebDriver driver = new ChromeDriver();
    String CAMINHO_DRIVER = "src/resource/chromedriver-v10205005.exe";
      
    @Test
    public void deveInteragirComFrames(){
        System.setProperty("webdriver.chrome.driver", CAMINHO_DRIVER);
        driver.get("file://" + System.getProperty("user.dir") + "/src/resource/componentes.html");
        driver.manage().window().maximize();

        driver.switchTo().frame("frame1");
        driver.findElement(By.id("frameButton")).click();
        Alert alerta = driver.switchTo().alert();
        String texto = alerta.getText();
        Assert.assertEquals("Frame OK!", texto);
        alerta.accept();

        driver.switchTo().defaultContent();
        driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
        Assert.assertEquals("Frame OK!", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));

        driver.quit();    
    }

    @Test
    public void deveInteragirComJanelas(){
        System.setProperty("webdriver.chrome.driver", CAMINHO_DRIVER);
        driver.get("file://" + System.getProperty("user.dir") + "/src/resource/componentes.html");
        driver.manage().window().maximize();
        
        driver.findElement(By.id("buttonPopUpEasy")).click();
        driver.switchTo().window("Popup");
        driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
        driver.close();
        driver.switchTo().window("");
        driver.findElement(By.tagName("textarea")).sendKeys("E agora?");
        driver.quit();

    }
}