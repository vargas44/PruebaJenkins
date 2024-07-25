package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = { "pretty", "json:target/cucumber-reports/Cucumber.json",
                "junit:target/cucumber-reports/Cucumber.xml",
                "html:target/cucumber-reports/Cucumber.html"},
        monochrome = true,
        stepNotifications = true,
        publish = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/main/resources/features/PlataformaIntegracion/1WooLoginMiCorreo.feature",
                "src/main/resources/features/PlataformaIntegracion/2WooCrearUsuarioCUIT.feature",
                "src/main/resources/features/PlataformaIntegracion/3WooCrearUsuarioDNI.feature",
                "src/main/resources/features/PlataformaIntegracion/4WooSeleccionSucursalOp1.feature",
                "src/main/resources/features/PlataformaIntegracion/5WooSeleccionDomicilioOp2.feature",
                "src/main/resources/features/PlataformaIntegracion/6WooSeleccionDomicilioOp3.feature",
                "src/main/resources/features/PlataformaIntegracion/7WooSeleccionSucursalOp4.feature",
                "src/main/resources/features/PlataformaIntegracion/8WoocommerceE2E.feature",
                "src/main/resources/features/PlataformaIntegracion/9WooLoginPaqAr.feature",
                "src/main/resources/features/PlataformaIntegracion/10WooCancelarPedidoPaqAr.feature",
                "src/main/resources/features/PlataformaIntegracion/11WooCancelarTodosLosPedidosPaqAr.feature",
                "src/main/resources/features/PlataformaIntegracion/12WooCompletarTodosLosPedidosPaqAr.feature",
                "src/main/resources/features/PlataformaIntegracion/13WooSeleccionSucursalOp1PaqAr.feature",
                "src/main/resources/features/PlataformaIntegracion/14WooSeleccionDomicilioOp2PaqAr.feature",
                "src/main/resources/features/PlataformaIntegracion/15WooSeleccionDomicilioOp3PaqAr.feature",
                "src/main/resources/features/PlataformaIntegracion/16WooSeleccionSucursalOp4PaqAr.feature"
        },
        glue = {"stepsDefinitions", "framework","PlataformaIntegracion","Google","MiCorreo1_5"}, // Agrega el paquete de los hooks y framework
        tags =  ""
        //"@PPDIPI-3167"
)
public class TestRunner {
}

