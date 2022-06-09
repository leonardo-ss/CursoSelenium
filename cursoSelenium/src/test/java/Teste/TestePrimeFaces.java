package Teste;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import core.DSL;
import core.DriverFactory;

public class TestePrimeFaces {
    
        private DSL dsl;
    
        @Before
        public void inicializa(){
            DriverFactory.getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml?jfwid=36e7f");
            // driver.manage().window().setSize(new Dimension(700, 500));
            dsl = new DSL();
        }
    
        @After
        public void finaliza(){
            DriverFactory.killDriver();
        }

        @Test
        public void deveInterargirComRadioButton(){
            dsl.clicarRadio(By.xpath("//label[.='Option1']/../div//span"));
        }
}
