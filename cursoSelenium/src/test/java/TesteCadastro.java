import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastro {
    WebDriver driver = new ChromeDriver();
    String CAMINHO_DRIVER = "src/resource/chromedriver-v10205005.exe";

    @Before
    public void inicializa(){
        System.setProperty("webdriver.chrome.driver", CAMINHO_DRIVER);
        driver.get("file://" + System.getProperty("user.dir") + "/src/resource/componentes.html");
        driver.manage().window().maximize();
    }

    @After
    public void finaliza(){
        driver.quit();
    }
    
    @Test
    public void deveRealizarCadastroComSucesso(){
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Leonardo");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Santos");
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
        
        WebElement escolaridade = driver.findElement(By.id("elementosForm:escolaridade"));
        Select escola = new Select(escolaridade);
        escola.selectByVisibleText("Superior");
    //ou
        new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("Futebol");

        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
        Assert.assertTrue(driver.findElement(By.id("descNome")).getText().contains("Leonardo"));
        Assert.assertTrue(driver.findElement(By.id("descSobrenome")).getText().contains("Santos"));
        Assert.assertTrue(driver.findElement(By.id("descSexo")).getText().contains("Masculino"));
        Assert.assertTrue(driver.findElement(By.id("descComida")).getText().contains("Pizza"));
        Assert.assertTrue(driver.findElement(By.id("descEscolaridade")).getText().contains("superior"));
        Assert.assertTrue(driver.findElement(By.id("descEsportes")).getText().contains("Futebol"));
    }

    @Test
    public void deveValidarNomeObrigatorio(){
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Nome eh obrigatorio", alert.getText());
    }

    @Test
    public void deveValidarSobreNomeObrigatorio(){
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Leonardo");

        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
    }

    @Test
    public void deveValidarSexoObrigatorio(){
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Leonardo");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Santos");

        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
    }

    @Test
    public void deveValidarComida(){
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Leonardo");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Santos");
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();

        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
    }

    @Test
    public void deveValidarEsporte(){
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Leonardo");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Santos");
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        Select combo = new Select(driver.findElement(By.id("elementosForm:esportes")));
        combo.selectByVisibleText("Futebol");
        combo.selectByVisibleText("O que eh esporte?");

        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
    }

}
