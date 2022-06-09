package Teste;
import static core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Page.CampoTreinamentoPage;
import core.BaseTest;

public class TesteCadastro extends BaseTest{

    private CampoTreinamentoPage page;

    @Before
    public void iniciar(){
        getDriver().get("file://" + System.getProperty("user.dir") + "/src/resource/componentes.html");
        page = new CampoTreinamentoPage();
    }

    // WebDriver driver = new ChromeDriver();
    // String CAMINHO_DRIVER = "src/resource/chromedriver-v10205005.exe";
    // private CampoTreinamentoPage page;

    // @Before
    // public void inicializa(){
    //     System.setProperty("webdriver.chrome.driver", CAMINHO_DRIVER);
    //     driver.get("file://" + System.getProperty("user.dir") + "/src/resource/componentes.html");
    //     driver.manage().window().maximize();
    //     page = new CampoTreinamentoPage(driver);
    // }

    
    
    @Test
    public void deveRealizarCadastroComSucesso(){
        page.setNome("Leonardo");
        page.setSobrenome("Santos");
        page.setSexoMasculino();
        page.setComidaPizza();
        page.setEscolaridade("Superior");
        page.setEsporte("Futebol");
        page.cadastrar();

        Assert.assertEquals("Cadastrado!",page.obterResultadoCadastro());
        Assert.assertEquals("Leonardo",page.obterNomeCadastro());
        Assert.assertEquals("Santos",page.obterSobrenomeCadastro());
        Assert.assertEquals("Masculino",page.obterSexoCadastro());
        Assert.assertEquals("Pizza",page.obterComidaCadastro());
        Assert.assertEquals("superior",page.obterEscolaridadeCadastro());
        Assert.assertEquals("Futebol",page.obterEsporteCadastro());
    }

}
