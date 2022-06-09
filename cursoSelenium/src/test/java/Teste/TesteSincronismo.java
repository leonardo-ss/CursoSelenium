package Teste;
import static core.DriverFactory.getDriver;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.DSL;
import core.DriverFactory;

public class TesteSincronismo {
    private DSL dsl;

    @Before
    public void iniciar(){
        getDriver().get("file://" + System.getProperty("user.dir") + "/src/resource/componentes.html");
        dsl = new DSL();
    }

    @After
    public void finaliza(){
        DriverFactory.killDriver();
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
        getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        dsl.escreve("novoCampo", "teste");
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        Assert.assertEquals("teste", dsl.obterValorCampo("novoCampo"));
    }

    @Test
    public void deveUtilizarEsperaExplicita() throws InterruptedException{
        dsl.clicarBotao("buttonDelay");
        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
        dsl.escreve("novoCampo", "teste");
        Assert.assertEquals("teste", dsl.obterValorCampo("novoCampo"));
    }
}
