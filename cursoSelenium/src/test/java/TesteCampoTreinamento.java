import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteCampoTreinamento {

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
    public void testeTextField (){
        dsl.escreve("elementosForm:nome","Leonardo");
        Assert.assertEquals("Leonardo", dsl.obterValorCampo("elementosForm:nome"));
    }

    @Test
    public void deveInteragirComTextArea(){
        dsl.escreve("elementosForm:sugestoes", "Teste text area");
        Assert.assertEquals("Teste text area", dsl.obterValorCampo("elementosForm:sugestoes"));
    }

    @Test
    public void deveInteragirComRadioButtom(){
        dsl.clicarRadio("elementosForm:sexo:0");
        Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
    }

    @Test
    public void deveInteragirComCheckBox(){
        dsl.clicarCheck("elementosForm:comidaFavorita:2");
        Assert.assertTrue(dsl.isRadioMarcado("elementosForm:comidaFavorita:2"));
    }
    
    @Test
    public void deveInteragirComCombo(){
        dsl.selecionarCombo("elementosForm:escolaridade","Mestrado");
        Assert.assertEquals("Mestrado", dsl.obterValorCombo("elementosForm:escolaridade"));
    }

    @Test
    public void deveVerificarValoresCombo(){
        Assert.assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
        Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));
    }

    @Test
    public void deveVerificarValoresComboMultiplo(){
        dsl.selecionarCombo("elementosForm:esportes", "Natacao");
        dsl.selecionarCombo("elementosForm:esportes", "Corrida");
        dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
       
        List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
        Assert.assertEquals(3, opcoesMarcadas.size());

        dsl.deselecionarCombo("elementosForm:esportes", "Natacao");
        opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
        Assert.assertEquals(2, opcoesMarcadas.size());
        Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Corrida", "O que eh esporte?")));
    }

    @Test
    public void deveInteragirComBotoes(){
        dsl.clicarBotao("buttonSimple");

        WebElement botao = driver.findElement(By.id("buttonSimple"));
        Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
    }

    @Test
    public void deveInteragirComlinks(){
        dsl.clicarLink("Voltar");
        Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
    }

    @Test
    public void deveBuscarTextosNaPagina(){
        Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));

        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
    }
        
    @Test
    public void testJavascript(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // js.executeScript("alert('Testando js via selenium')");
        js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via js'");
        js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");

        WebElement elemento = driver.findElement(By.id("elementosForm:nome"));
        js.executeScript("arguments[0].style.border = arguments[1]", elemento, "solid 4px red");
    }

    @Test
    public void deveClicarBotatoTabela(){
        dsl.clicarBotaoTabela("Escolaridade", "Superior", "Radio", "elementosForm:tabelaUsuarios");
    }
}
