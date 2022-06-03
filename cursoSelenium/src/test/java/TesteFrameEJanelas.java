import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteFrameEJanelas{

    WebDriver driver = new ChromeDriver();
    String CAMINHO_DRIVER = "src/resource/chromedriver-v10205005.exe";
    private DSL dsl;

    @Before
    public void inicializa(){
        System.setProperty("webdriver.chrome.driver", CAMINHO_DRIVER);
        driver.get("file://" + System.getProperty("user.dir") + "/src/resource/componentes.html");
        driver.manage().window().maximize();
        dsl = new DSL(driver);
    }

    @After
    public void finaliza(){
        driver.quit();
    }
      
    @Test
    public void deveInteragirComFrames(){
        dsl.entrarFrame("frame1");
        dsl.clicarBotao("frameButton");
        String texto = dsl.alertaObterTextoEAceita();
        Assert.assertEquals("Frame OK!", texto);

        dsl.sairFrame();
        dsl.escreve("elementosForm:nome", texto);
    }

    @Test
    public void deveInteragirComJanelas(){
        dsl.clicarBotao("buttonPopUpEasy");
        dsl.trocarJanela("Popup");
        dsl.escreve(By.tagName("textarea"),"Deu certo?");
        driver.close();
        dsl.trocarJanela("");
        dsl.escreve(By.tagName("textarea"),"E agora?");
    }

    @Test
    public void deveInteragirComJanelasSemTitulo(){
        dsl.clicarBotao("buttonPopUpHard");
        System.out.println(driver.getWindowHandle());
        System.out.println(driver.getWindowHandles());
        dsl.trocarJanela((String)driver.getWindowHandles().toArray()[1]);
        dsl.escreve(By.tagName("textarea"),"Deu certo?");
        dsl.trocarJanela((String)driver.getWindowHandles().toArray()[0]);
        dsl.escreve(By.tagName("textarea"),"E agora?");
    }
}