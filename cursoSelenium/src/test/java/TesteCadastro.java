import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteCadastro {
    WebDriver driver = new ChromeDriver();
    String CAMINHO_DRIVER = "src/resource/chromedriver-v10205005.exe";
    private CampoTreinamentoPage page;

    @Before
    public void inicializa(){
        System.setProperty("webdriver.chrome.driver", CAMINHO_DRIVER);
        driver.get("file://" + System.getProperty("user.dir") + "/src/resource/componentes.html");
        driver.manage().window().maximize();
        page = new CampoTreinamentoPage(driver);
    }

    @After
    public void finaliza(){
        driver.quit();
    }
    
    @Test
    public void deveRealizarCadastroComSucesso(){
        page.setNome("Leonardo");
        page.setSobrenome("Santos");
        page.setSexoMasculino();
        page.setComidaPizza();
        page.setEscolaridade("Superior");
        page.setEsporte("Futebol");
        page.cadastrar();

        Assert.assertTrue(page.obterResultadoCadastro().startsWith("Cadastrado!"));
        Assert.assertTrue(page.obterNomeCadastro().contains("Leonardo"));
        Assert.assertTrue(page.obterSobrenomeCadastro().contains("Santos"));
        Assert.assertTrue(page.obterSexoCadastro().contains("Masculino"));
        Assert.assertTrue(page.obterComidaCadastro().contains("Pizza"));
        Assert.assertTrue(page.obterEscolaridadeCadastro().contains("superior"));
        Assert.assertTrue(page.obterEsporteCadastro().contains("Futebol"));
    }

}
