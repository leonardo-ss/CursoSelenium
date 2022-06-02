import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

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
        dsl.clicarRadio("elementosForm:comidaFavorita:2");
        Assert.assertTrue(dsl.isRadioMarcado("elementosForm:comidaFavorita:2"));
    }
    
    @Test
    public void deveInteragirComCombo(){
        dsl.selecionarCombo("elementosForm:escolaridade","Mestrado");
        
        Assert.assertEquals("Mestrado", dsl.obterValorCombo("elementosForm:escolaridade"));
    }

    @Test
    public void deveVerificarValoresCombo(){
        WebElement elemento = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(elemento);
        List<WebElement> options = combo.getOptions();
        Assert.assertEquals(8, options.size());

        boolean encontrou = false;
        for(WebElement option: options){
            if(option.getText().equals("Mestrado")){
                encontrou = true;
                break;
            }
        }
        Assert.assertTrue(encontrou);
    }

    @Test
    public void deveVerificarValoresComboMultiplo(){
        dsl.selecionarCombo("elementosForm:esportes", "Natacao");
        dsl.selecionarCombo("elementosForm:esportes", "Corrida");
        dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
       
        WebElement elemento = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(elemento);
        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(3, allSelectedOptions.size());

        combo.deselectByVisibleText("Corrida");
        allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(2, allSelectedOptions.size());
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
        
}
