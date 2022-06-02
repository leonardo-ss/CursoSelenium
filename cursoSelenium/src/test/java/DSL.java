import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {

    WebDriver driver;

    public DSL(WebDriver driver) {
        this.driver = driver;
    }

    public void escreve(String id_campo, String texto){
        driver.findElement(By.id(id_campo)).sendKeys(texto);
    }

    public String obterValorCampo(String id_campo){
        return driver.findElement(By.id(id_campo)).getAttribute("value");
    }

    public void clicarRadio(String id_radio){
        driver.findElement(By.id(id_radio)).click();
    }

    public boolean isRadioMarcado(String id_campo){
        return driver.findElement(By.id(id_campo)).isSelected();
    }
    
    public void selecionarCombo(String id_campo, String valor){
        WebElement elemento = driver.findElement(By.id(id_campo));
        Select combo = new Select(elemento);
       // combo.selectByIndex(3);
       // combo.selectByValue("superior");
        combo.selectByVisibleText(valor);
    }
    
    public String obterValorCombo(String id_campo){
        WebElement elemento = driver.findElement(By.id(id_campo));
        Select combo = new Select(elemento);
        return combo.getFirstSelectedOption().getText();
    }

    public void clicarBotao(String id_botao){
        driver.findElement(By.id(id_botao)).click();
    }

    public void clicarLink(String id_link){
        driver.findElement(By.id(id_link)).click();
    }

    public String obterTexto(By by){
       return driver.findElement(by).getText();
    }

    public String obterTexto(String id_campo){
       return obterTexto(By.id(id_campo));
    }

}
