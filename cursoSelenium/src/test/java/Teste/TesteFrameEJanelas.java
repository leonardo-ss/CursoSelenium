package Teste;
import static core.DriverFactory.getDriver;
import static core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import core.DSL;

public class TesteFrameEJanelas{
    private DSL dsl;

    @Before
    public void iniciar(){
        getDriver().get("file://" + System.getProperty("user.dir") + "/src/resource/componentes.html");
        dsl = new DSL();
    }

    @After
    public void finaliza(){
        killDriver();
    }
      
    @Test
    public void deveInteragirComFrames(){
        dsl.entrarFrame("frame1");
        dsl.clicarBotao("frameButton");
        String texto = dsl.alertaObterTextoEAceita();
        Assert.assertEquals("Frame OK!", texto);

        dsl.sairFrame();
        dsl.escreve("elementosForm:nome", texto);
    }

    @Test
    public void deveInteragirComFrameEscondido(){
        //WebElement frame = getDriver().findElement(By.id("frame2"));
        //Scroll para encontrar o botão do frame
        // dsl.executarJS("window.scrollby(0, arguments[0])", frame.getLocation().y)
        dsl.entrarFrame("frame2");
        dsl.clicarBotao("frameButton");
        String texto = dsl.alertaObterTextoEAceita();
        Assert.assertEquals("Frame OK!", texto);
    }

    @Test
    public void deveInteragirComJanelas(){
        dsl.clicarBotao("buttonPopUpEasy");
        dsl.trocarJanela("Popup");
        dsl.escreve(By.tagName("textarea"),"Deu certo?");
        getDriver().close();
        dsl.trocarJanela("");
        dsl.escreve(By.tagName("textarea"),"E agora?");
    }

    @Test
    public void deveInteragirComJanelasSemTitulo(){
        dsl.clicarBotao("buttonPopUpHard");
        System.out.println(getDriver().getWindowHandle());
        System.out.println(getDriver().getWindowHandles());
        dsl.trocarJanela((String)getDriver().getWindowHandles().toArray()[1]);
        dsl.escreve(By.tagName("textarea"),"Deu certo?");
        dsl.trocarJanela((String)getDriver().getWindowHandles().toArray()[0]);
        dsl.escreve(By.tagName("textarea"),"E agora?");
    }


}