package page.MiCorreo1_5;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class PageCheckOut extends BasePage {
    PageForm pageForm;
    PageHomeLogin pageHomeLogin;

    private By tablaLocator = By.xpath("//table[@class='table table-hover mcr-table table-responsive']");
    private By integracionLocator = By.xpath("(//td[@class='table-text text-center'])[2]");
    private By numOrden = By.xpath("(//td[@class='table-text text-center'])[3]");
    private By origenLocator = By.xpath("(//td[@class='table-text text-center'])[4]");
    private By destinoLocator = By.xpath("(//td[@class='table-text text-center'])[5]");
    private By pesoInformadoLocator = By.xpath("(//td[@class='table-text text-center'])[6]");
    private By pesoVolumetricoLocator = By.xpath("(//td[@class='table-text text-center'])[7]");
    private By medidasCheckoutLocator = By.xpath("(//td[@class='table-text text-center'])[8]");
    private By precioUnitarioLocator = By.xpath("(//td[@class='table-text text-center'])[9]");
    private By pagoTarjeta = By.id("radioTarjeta");
    private By pagoSaldo = By.id("radioSaldo");
    private By pagoCtaCte = By.id("radioCuentaCorriente");
    public PageCheckOut(WebDriver driver){
        super(driver);
        this.pageForm = new PageForm(driver);
        this.pageHomeLogin = new PageHomeLogin(driver);
    }
    public void validarFormularioCheckout(){
        //assertURL("https://wcpzt.correo.local/MiCorreo/public/misEnviosCheckout");
        validarCampo("Integración", integracionLocator, getText(integracionLocator));
        validarCampo("Origen", origenLocator, getText(origenLocator));
        validarCampo("Destino", destinoLocator, getText(destinoLocator));
        validarCampo("Peso Informado", pesoInformadoLocator,getText(pesoInformadoLocator));
        validarCampo("Peso Volumetrico",pesoVolumetricoLocator,getText(pesoVolumetricoLocator));
        validarCampo("Medidas",medidasCheckoutLocator,getText(medidasCheckoutLocator));
        validarCampo("Precio Unitario", precioUnitarioLocator,getText(precioUnitarioLocator));
        System.out.println("¡Checkout correcto!");
    }
    public void presionarPagar(){
        clickWithRetry(By.xpath("(//button[@id='btnPagar' and normalize-space()='Pagar'])[1]"));
        waitForSeconds(5);
    }
    public void medioPago(String medioPago){
        if (medioPago.equals("Tarjeta")){
            presionarPagar();
            pageForm.pagoConTarjeta();
        }else if (medioPago.equals("Cuenta Corriente")){
            click(pagoCtaCte);
            presionarPagar();
        }else if (medioPago.equals("Saldo")){
            click(pagoSaldo);
            presionarPagar();
        }else {
            throw new IllegalArgumentException("Medio de pago no valido: " + medioPago);
        }
    }
    public void abrirNuevaPestanaYNavegarAEntornoDePruebas() {
        String urlNuevoAmbiente = "https://wcpzt.correo.local/MiCorreo/public/";
        abrirNuevaPestanaYNavegarA(urlNuevoAmbiente);
    }
    public void login() {
        By emailLocator = By.id("email");
        By passwordLocator = By.id("password");
        By btnLogin = By.xpath("//button[@type=\"submit\" and @onclick=\"ValidacionLogin(event)\"]\n");
        boolean loginExitoso = false;
        String expectedUrl = "https://wcpzt.correo.local/MiCorreo/public/message-home";
        int intentos = 0;
        int maxIntentos = 2; // Establece el número máximo de intentos

        while (!loginExitoso && intentos < maxIntentos) {
            try {
                // Paso 2
                writeText(emailLocator, "Automationpi@yopmail.com");
                writeText(passwordLocator, "123123");
                click(btnLogin);

                // Paso 3
                waitForUrlToBe(expectedUrl, 2);

                // Verificar si la URL es la esperada
                String currentUrl = getCurrentURL(); // Utilizando la función encapsulada
                if (currentUrl.equals(expectedUrl)) {
                    // Si estamos en la página principal, el inicio de sesión es exitoso
                    System.out.println("¡Inicio de sesión exitoso!");
                    System.out.println("Estamos en la página principal (message-home).");
                    return; // Salir del método después de un inicio de sesión exitoso
                } else {
                    // Si no estamos en la página principal, continuar con el siguiente intento
                    System.out.println("Inicio de sesión fallido. No estamos en la página principal.");
                    intentos++;
                }
            } catch (Exception e) {
                // Si se produce una excepción, incrementar el contador de intentos
                intentos++;
                System.out.println("Intento de inicio de sesión #" + intentos + " fallido.");
            }
        }
        System.out.println("Inicio de sesión fallido después de " + maxIntentos + " intentos.");
    }

    public void visualizarGrillaMiCorreo(){
        abrirNuevaPestanaYNavegarAEntornoDePruebas();
        login();
        waitForSeconds(1);
        click(By.xpath("//div[@class=' text-center' and normalize-space()='Mis envíos']"));
        imprimirContenidoTabla(tablaLocator);
        waitForSeconds(2);
    }

}
