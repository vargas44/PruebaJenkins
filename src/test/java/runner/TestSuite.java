package runner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestRunner.class,
        // Aqu� puedes agregar todas las clases de prueba que quieras incluir en el suite
        // OtraClaseDePrueba.class,
        // TerceraClaseDePrueba.class,
})
public class TestSuite {
    // Esta clase permanece vac�a, se utiliza solo como marcador para las anotaciones de arriba
}
