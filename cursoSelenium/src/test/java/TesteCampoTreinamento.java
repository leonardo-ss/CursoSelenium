import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCampoTreinamento {

    WebDriver driver = new ChromeDriver();
    String CAMINHO_DRIVER = "src/resource/chromedriver-v10205005.exe";
    
    @Test
    public void testeTextField (){

        System.setProperty("webdriver.chrome.driver", CAMINHO_DRIVER);
        driver.get("file://" + System.getProperty("user.dir") + "/src/resource/componentes.html");
        driver.manage().window().maximize();

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Leonardo");
        Assert.assertEquals("Leonardo", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
        System.out.println(driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
        driver.quit();
    }

    @Test
    public void deveInteragirComTextArea(){

        System.setProperty("webdriver.chrome.driver", CAMINHO_DRIVER);
        driver.get("file://" + System.getProperty("user.dir") + "/src/resource/componentes.html");
        driver.manage().window().maximize();

        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Teste text area");
        Assert.assertEquals("Teste text area", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));

        driver.quit();
    }

    @Test
    public void deveInteragirComRadioButtom(){

        System.setProperty("webdriver.chrome.driver", CAMINHO_DRIVER);
        driver.get("file://" + System.getProperty("user.dir") + "/src/resource/componentes.html");
        driver.manage().window().maximize();

        driver.findElement(By.id("elementosForm:sexo:0")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());

        driver.quit();
    }

    @Test
    public void deveInteragirComCheckBox(){

        System.setProperty("webdriver.chrome.driver", CAMINHO_DRIVER);
        driver.get("file://" + System.getProperty("user.dir") + "/src/resource/componentes.html");
        driver.manage().window().maximize();

        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());

        driver.quit();
    }
    
    @Test
    public void deveInteragirComCombo(){

        System.setProperty("webdriver.chrome.driver", CAMINHO_DRIVER);
        driver.get("file://" + System.getProperty("user.dir") + "/src/resource/componentes.html");
        driver.manage().window().maximize();
        
        WebElement elemento = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(elemento);
       // combo.selectByIndex(3);
       // combo.selectByValue("superior");
        combo.selectByVisibleText("Mestrado");
        Assert.assertEquals("Mestrado", combo.getFirstSelectedOption().getText());

        driver.quit();
    }

    @Test
    public void deveVerificarValoresCombo(){

        System.setProperty("webdriver.chrome.driver", CAMINHO_DRIVER);
        driver.get("file://" + System.getProperty("user.dir") + "/src/resource/componentes.html");
        driver.manage().window().maximize();
        
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

        driver.quit();
    }

    @Test
    public void deveVerificarValoresComboMultiplo(){

        System.setProperty("webdriver.chrome.driver", CAMINHO_DRIVER);
        driver.get("file://" + System.getProperty("user.dir") + "/src/resource/componentes.html");
        driver.manage().window().maximize();
        
        WebElement elemento = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(elemento);
        combo.selectByVisibleText("Natacao");
        combo.selectByVisibleText("Corrida");
        combo.selectByVisibleText("O que eh esporte?");

        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(3, allSelectedOptions.size());

        combo.deselectByVisibleText("Corrida");
        allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(2, allSelectedOptions.size());


        driver.quit();
    }

    @Test
    public void deveInteragirComBotoes(){

        System.setProperty("webdriver.chrome.driver", CAMINHO_DRIVER);
        driver.get("file://" + System.getProperty("user.dir") + "/src/resource/componentes.html");
        driver.manage().window().maximize();

        WebElement botao = driver.findElement(By.id("buttonSimple"));
        botao.click();
        Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
        driver.quit();
    }

    @Test
    public void deveInteragirComlinks(){

        System.setProperty("webdriver.chrome.driver", CAMINHO_DRIVER);
        driver.get("file://" + System.getProperty("user.dir") + "/src/resource/componentes.html");
        driver.manage().window().maximize();

        driver.findElement(By.linkText("Voltar")).click();
        Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());
        driver.quit();
    }

    @Test
    public void deveBuscarTextosNaPagina(){

        System.setProperty("webdriver.chrome.driver", CAMINHO_DRIVER);
        driver.get("file://" + System.getProperty("user.dir") + "/src/resource/componentes.html");
        driver.manage().window().maximize();

        Assert.assertEquals("Campo de Treinamento",driver.findElement(By.tagName("h3")).getText());

        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", driver.findElement(By.className("facilAchar")).getText());

        driver.quit();
    }
        
}
