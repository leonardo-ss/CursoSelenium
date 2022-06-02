import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastro {
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
    public void deveRealizarCadastroComSucesso(){
        dsl.escreve("elementosForm:nome","Leonardo");
        dsl.escreve("elementosForm:sobrenome","Santos");
        dsl.clicarBotao("elementosForm:sexo:0");
        dsl.clicarBotao("elementosForm:comidaFavorita:2");
        
        dsl.selecionarCombo("elementosForm:escolaridade", "Superior");
    //ou
        new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("Futebol");

        dsl.clicarBotao("elementosForm:cadastrar");

        Assert.assertTrue(dsl.obterTexto(By.id("resultado")).startsWith("Cadastrado!"));
        Assert.assertTrue(dsl.obterTexto(By.id("descNome")).contains("Leonardo"));
        Assert.assertTrue(dsl.obterTexto(By.id("descSobrenome")).contains("Santos"));
        Assert.assertTrue(dsl.obterTexto(By.id("descSexo")).contains("Masculino"));
        Assert.assertTrue(dsl.obterTexto(By.id("descComida")).contains("Pizza"));
        Assert.assertTrue(dsl.obterTexto(By.id("descEscolaridade")).contains("superior"));
        Assert.assertTrue(dsl.obterTexto(By.id("descEsportes")).contains("Futebol"));
    }

    @Test
    public void deveValidarNomeObrigatorio(){
        dsl.clicarBotao("elementosForm:cadastrar");
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Nome eh obrigatorio", alert.getText());
    }

    @Test
    public void deveValidarSobreNomeObrigatorio(){
        dsl.escreve("elementosForm:nome","Leonardo");

        dsl.clicarBotao("elementosForm:cadastrar");
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
    }

    @Test
    public void deveValidarSexoObrigatorio(){
        dsl.escreve("elementosForm:nome","Leonardo");
        dsl.escreve("elementosForm:sobrenome","Santos");

        dsl.clicarBotao("elementosForm:cadastrar");
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
    }

    @Test
    public void deveValidarComida(){
        dsl.escreve("elementosForm:nome","Leonardo");
        dsl.escreve("elementosForm:sobrenome","Santos");
        dsl.clicarRadio("elementosForm:sexo:0");
        dsl.clicarRadio("elementosForm:comidaFavorita:0");
        dsl.clicarRadio("elementosForm:comidaFavorita:3");

        dsl.clicarBotao("elementosForm:cadastrar");
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
    }

    @Test
    public void deveValidarEsporte(){
        dsl.escreve("elementosForm:nome","Leonardo");
        dsl.escreve("elementosForm:sobrenome","Santos");
        dsl.clicarRadio("elementosForm:sexo:0");
        dsl.clicarRadio("elementosForm:comidaFavorita:0");
        dsl.selecionarCombo("elementosForm:esportes", "Futebol");
        dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");

        dsl.clicarBotao("elementosForm:cadastrar");
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
    }

}
