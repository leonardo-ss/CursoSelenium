import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteCadastro {
    WebDriver driver = new ChromeDriver();
    String CAMINHO_DRIVER = "src/resource/chromedriver-v10205005.exe";
    private DSL dsl;
    private CampoTreinamentoPage page;

    @Before
    public void inicializa(){
        System.setProperty("webdriver.chrome.driver", CAMINHO_DRIVER);
        driver.get("file://" + System.getProperty("user.dir") + "/src/resource/componentes.html");
        driver.manage().window().maximize();
        dsl = new DSL(driver);
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

    @Test
    public void deveValidarNomeObrigatorio(){
        page.cadastrar();
        Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarSobreNomeObrigatorio(){
        page.setNome("Leonardo");
        page.cadastrar();
        Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarSexoObrigatorio(){
        page.setNome("Leonardo");
        page.setSobrenome("Santos");
        page.cadastrar();
        Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarComida(){
        page.setNome("Leonardo");
        page.setSobrenome("Santos");
        page.setSexoMasculino();
        page.setComidaCarne();
        page.setComidaVegetariano();
        page.cadastrar();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarEsporte(){
        page.setNome("Leonardo");
        page.setSobrenome("Santos");
        page.setSexoMasculino();
        page.setComidaCarne();
        page.setEsporte("Futebol", "O que eh esporte?");
        page.cadastrar();
        Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());
    }

}
