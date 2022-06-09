package core;
import static core.DriverFactory.getDriver;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {

    /*********** TextField e TextArea ***********/

    public void escreve(By by, String texto){
        getDriver().findElement(by).sendKeys(texto);
    }

    public void escreve(String id_campo, String texto){
        getDriver().findElement(By.id(id_campo)).sendKeys(texto);
    }

    public String obterValorCampo(String id_campo){
        return getDriver().findElement(By.id(id_campo)).getAttribute("value");
    }

    /*********** Radio e Check ***********/

    public void clicarRadio(By by){
        getDriver().findElement(by).click();
    }

    public void clicarRadio(String id_radio){
        getDriver().findElement(By.id(id_radio)).click();
    }

    public boolean isRadioMarcado(By by){
        return getDriver().findElement(by).isSelected();
    }

    public boolean isRadioMarcado(String id_campo){
        return getDriver().findElement(By.id(id_campo)).isSelected();
    }

    public void clicarCheck(String id_check){
        getDriver().findElement(By.id(id_check)).click();
    }

    public boolean isCheckMarcado(String id_campo){
        return getDriver().findElement(By.id(id_campo)).isSelected();
    }
    
    /*********** Combo ***********/

    public void selecionarCombo(String id_campo, String valor){
        WebElement elemento = getDriver().findElement(By.id(id_campo));
        Select combo = new Select(elemento);
        combo.selectByVisibleText(valor);
        // combo.selectByIndex(3);
       // combo.selectByValue("superior");
    }

    public void deselecionarCombo(String id_campo, String valor){
        WebElement elemento = getDriver().findElement(By.id(id_campo));
        Select combo = new Select(elemento);
        combo.deselectByVisibleText(valor);
    }
    
    public String obterValorCombo(String id_campo){
        WebElement elemento = getDriver().findElement(By.id(id_campo));
        Select combo = new Select(elemento);
        return combo.getFirstSelectedOption().getText();
    }

    public List<String> obterValoresCombo(String id_campo){
        WebElement elemento = getDriver().findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(elemento);
        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        List<String> valores = new ArrayList<String>();
        for(WebElement opcao: allSelectedOptions){
            valores.add(opcao.getText());
        }
        return valores;
    }

    public int obterQuantidadeOpcoesCombo(String id_campo){
        WebElement elemento = getDriver().findElement(By.id(id_campo));
        Select combo = new Select(elemento);
        List<WebElement> options = combo.getOptions();
        return options.size();
    }

    public boolean verificarOpcaoCombo(String id_campo, String opcao){
        WebElement elemento = getDriver().findElement(By.id(id_campo));
        Select combo = new Select(elemento);
        List<WebElement> options = combo.getOptions();
        for(WebElement option: options){
            if(option.getText().equals(opcao)){
                return true;
            }
        }
        return false;
    }

    /*********** Botão ***********/

    public void clicarBotao(String id_botao){
        getDriver().findElement(By.id(id_botao)).click();
    }

    public String obterValueElemento(String id_campo){
        return getDriver().findElement(By.id(id_campo)).getAttribute("value");
    }

    /*********** Link ***********/

    public void clicarLink(String id_link){
        getDriver().findElement(By.linkText(id_link)).click();
    }

    /*********** Textos ***********/

    public String obterTexto(By by){
       return getDriver().findElement(by).getText();
    }
    public String obterTexto(String id_campo){
       return obterTexto(By.id(id_campo));
    }

    /*********** Alerts ***********/

    public String alertaObterTexto(){
        Alert alerta = getDriver().switchTo().alert();
        return alerta.getText();
    }

    public String alertaObterTextoEAceita(){
        Alert alerta = getDriver().switchTo().alert();
        String texto = alerta.getText();
        alerta.accept();
        return texto;
    }

    public String alertaObterTextoENega(){
        Alert alerta = getDriver().switchTo().alert();
        String texto = alerta.getText();
        alerta.dismiss();;
        return texto;
    }

    public void alertaEscrever(String texto){
        Alert alerta = getDriver().switchTo().alert();
        alerta.sendKeys(texto);
        alerta.accept();
    }

    /*********** Frames e Janelas ***********/

    public void entrarFrame(String id){
        getDriver().switchTo().frame(id);
    }

    public void sairFrame(){
        getDriver().switchTo().defaultContent();
    }

    public void trocarJanela(String id){
        getDriver().switchTo().window(id);
    }

    /**************** JS ********************/

    public Object executarJS(String cmd, Object... param){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        return js.executeScript(cmd, param);
    }

     /**************** Tabela ********************/

     public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String idTabela){
         //procurar coluna do registro
        WebElement tabela = getDriver().findElement(By.xpath("//*[@id='elementosForm:tableUsuarios']"));
        int idColuna = obterIndiceColuna(colunaBusca, tabela);
         
        //encontrar a linha do registro
        int idLinha = obterIndiceLinha(valor, tabela, idColuna);

         //procurar a coluna do botao
         int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);

         //clicar no botao da celula encontrada
         WebElement celula = tabela.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]"));
         celula.findElement(By.xpath(".//input")).click();
     }

    private int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
        List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td["+idColuna+"]"));
        int idLinha = -1;
        for(int i =0; i < linhas.size(); i++){
                if(linhas.get(i).getText().equals(valor)){
                    idLinha = i+1;
                    break;
                }
            }
            return idLinha;
    }

    private int obterIndiceColuna(String coluna, WebElement tabela) {
        List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
        int idColuna = -1;
        for(int i =0; i < colunas.size(); i++){
                if(colunas.get(i).getText().equals(coluna)){
                    idColuna = i+1;
                    break;
                }
        }
        return idColuna;
    }
}
