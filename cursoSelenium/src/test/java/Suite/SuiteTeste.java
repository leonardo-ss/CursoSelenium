package Suite;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import Teste.TesteCadastro;
import Teste.TesteRegrasCadastro;
import core.DriverFactory;

@RunWith(Suite.class)
@SuiteClasses({
    TesteCadastro.class,
    TesteRegrasCadastro.class
})
public class SuiteTeste {
    
    @AfterClass
    public static void finalizaTudo(){
        DriverFactory.killDriver();
    }

}
