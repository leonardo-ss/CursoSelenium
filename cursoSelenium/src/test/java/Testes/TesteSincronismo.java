package Testes;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.DSL;

public class TesteSincronismo {
    private WebDriver driver;
    private DSL dsl;

    @Before
    public void iniciar(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("file://" + System.getProperty("user.dir") + "/src/resource/componentes.html");
        dsl = new DSL(driver);
    }

    @After
    public void finaliza(){
        driver.quit();
    }

    @Test
    public void deveUtilizarEsperaFixa() throws InterruptedException{
        dsl.clicarBotao("buttonDelay");
        Thread.sleep(5000);
        dsl.escreve("novoCampo", "teste");
        Assert.assertEquals("teste", dsl.obterValorCampo("novoCampo"));
    }

    @Test
    public void deveUtilizarEsperaImplicita() {
        dsl.clicarBotao("buttonDelay");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        dsl.escreve("novoCampo", "teste");
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        Assert.assertEquals("teste", dsl.obterValorCampo("novoCampo"));
    }

    @Test
    public void deveUtilizarEsperaExplicita() throws InterruptedException{
        dsl.clicarBotao("buttonDelay");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
        dsl.escreve("novoCampo", "teste");
        Assert.assertEquals("teste", dsl.obterValorCampo("novoCampo"));
    }
}
