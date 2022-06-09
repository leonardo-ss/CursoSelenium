package Teste;
import static core.DriverFactory.getDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import core.DSL;
import core.DriverFactory;

public class TesteAlert {
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
    public void deveInteragirComAlertSimples(){
        dsl.clicarBotao("alert");
        String texto = dsl.alertaObterTextoEAceita();
        Assert.assertEquals("Alert Simples", texto);

        dsl.escreve("elementosForm:nome", texto);
    }

    @Test
    public void deveInteragirComAlertConfirm(){
        dsl.clicarBotao("confirm");
        Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoEAceita());
        Assert.assertEquals("Confirmado", dsl.alertaObterTextoEAceita());

        dsl.clicarBotao("confirm");
        Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoENega());
        Assert.assertEquals("Negado", dsl.alertaObterTextoENega());
    }

    @Test
    public void deveInteragirComAlertPrompt(){
        dsl.clicarBotao("prompt");
        Assert.assertEquals("Digite um numero", dsl.alertaObterTexto());
        dsl.alertaEscrever("27");
        Assert.assertEquals("Era 27?", dsl.alertaObterTextoEAceita());
        Assert.assertEquals(":D", dsl.alertaObterTextoEAceita());
    }
    
}
