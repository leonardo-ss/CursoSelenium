import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {

    private DSL dsl;
    
    public CampoTreinamentoPage(WebDriver driver){
        dsl = new DSL(driver);
    }
    
    /*********** Setar **********/

    public void setNome(String nome){
        dsl.escreve("elementosForm:nome",nome);
    }

    public void setSobrenome(String sobrenome){
        dsl.escreve("elementosForm:sobrenome", sobrenome);
    }

    public void setSexoMasculino(){
        dsl.clicarBotao("elementosForm:sexo:0");
    }

    public void setSexoFeminino(){
        dsl.clicarBotao("elementosForm:sexo:1");
    }

    public void setComidaCarne(){
        dsl.clicarBotao("elementosForm:comidaFavorita:0");
    }

    public void setComidaFrango(){
        dsl.clicarBotao("elementosForm:comidaFavorita:1");
    }

    public void setComidaPizza(){
        dsl.clicarBotao("elementosForm:comidaFavorita:2");
    }

    public void setComidaVegetariano(){
        dsl.clicarBotao("elementosForm:comidaFavorita:3");
    }

    public void setEscolaridade(String valor){
        dsl.selecionarCombo("elementosForm:escolaridade", valor);
    }

    public void setEsporte(String... valores){
        for(String valor: valores)
            dsl.selecionarCombo("elementosForm:esportes",valor);
    }

    public void cadastrar(){
        dsl.clicarBotao("elementosForm:cadastrar");
    }

    /*********** Obter ************/

    public String obterResultadoCadastro(){
        return dsl.obterTexto("resultado");
    }

    public String obterNomeCadastro(){
        return dsl.obterTexto("descNome");
    }

    public String obterSobrenomeCadastro(){
        return dsl.obterTexto("descSobrenome");
    }

    public String obterSexoCadastro(){
        return dsl.obterTexto("descSexo");
    }

    public String obterComidaCadastro(){
        return dsl.obterTexto("descComida");
    }

    public String obterEscolaridadeCadastro(){
        return dsl.obterTexto("descEscolaridade");
    }

    public String obterEsporteCadastro(){
        return dsl.obterTexto("descEsportes");
    }
    

}
