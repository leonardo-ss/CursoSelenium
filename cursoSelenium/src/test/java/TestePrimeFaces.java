import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestePrimeFaces {
    

        WebDriver driver = new ChromeDriver();
        String CAMINHO_DRIVER = "src/resource/chromedriver-v10205005.exe";
        private DSL dsl;
    
        @Before
        public void inicializa(){
            System.setProperty("webdriver.chrome.driver", CAMINHO_DRIVER);
            driver.get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml?jfwid=36e7f");
            // driver.manage().window().setSize(new Dimension(700, 500));
            driver.manage().window().maximize();
            dsl = new DSL(driver);
        }
    
        @After
        public void finaliza(){
            driver.quit();
        }

        @Test
        public void deveInterargirComRadioButton(){
            dsl.clicarRadio(By.xpath("//label[.='Option1']/../div//span"));
        }
}
