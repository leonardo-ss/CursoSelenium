package Suite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import Teste.TesteCadastro;
import Teste.TesteCampoTreinamento;
import Teste.TesteRegrasCadastro;

@RunWith(Suite.class)
@SuiteClasses({
    TesteCadastro.class,
    TesteRegrasCadastro.class,
    TesteCampoTreinamento.class
})
public class SuiteTeste {
    

}
