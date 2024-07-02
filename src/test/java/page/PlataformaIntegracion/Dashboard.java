package page.PlataformaIntegracion;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Dashboard extends BasePage {

    private By usrlocator = By.xpath("//input[@id='user_login']");
    private By passlocator = By.xpath("//input[@id='user_pass']");
    private By btnLogin = By.xpath("//input[@id='wp-submit']");
    private By lblCorreo = By.xpath("//div[@class='wp-menu-name' and contains(text(),'Correo')]");
    private By SubCorreoArg = By.xpath("//a[@class='wp-first-item' and normalize-space()='Correo Argentino']");
    private By SubConexionApi = By.xpath("//*[@id='toplevel_page_correoargentino-orders']/ul/li[3]/a");
    private By SubDatosComerciales = By.xpath("//*[@id='toplevel_page_correoargentino-orders']/ul/li[4]/a");
    private By myWordpressLocator = By.xpath("//li[@id='wp-admin-bar-site-name']");
    private By btnVisitarSitio = By.xpath("//*[@id='wp-admin-bar-view-site']/a");
    private By btnVisitarTienda = By.xpath("//*[@id='wp-admin-bar-view-store']/a");
    private By numeroPedido = By.xpath("(//span[@class='wc-block-order-confirmation-summary-list-item__value'])[1]");
    private By wooCommerce = By.xpath("//div[@class='wp-menu-name' and normalize-space()='WooCommerce']");
    private By pedidosLocator = By.xpath("//a[normalize-space()='Pedidos']");
    private By dropdownLocator = By.xpath("(//select[@name='bulk-actions-top' and @class='bulk-actions-top'])[1]");
    private By btnAplicar = By.xpath("(//input[@type='submit' and @value='Aplicar'])[1]");



    public Dashboard(WebDriver driver) {
        super(driver);
    }


    public void verificarUsrAndPass(){
        // Verifica la existencia del campo de usuario
        boolean usrExists = validarCampoExistente(usrlocator);
        // Verifica la existencia del campo de contrase�a
        boolean passExists = validarCampoExistente(passlocator);

        // Imprime mensajes en la consola sobre la existencia de los campos
        if (usrExists && passExists) {
            System.out.println("Los campos de usuario y contrase�a existen.");
        } else if (usrExists) {
            System.out.println("El campo de usuario existe, pero el campo de contrase�a no.");
        } else if (passExists) {
            System.out.println("El campo de contrase�a existe, pero el campo de usuario no.");
        } else {
            System.out.println("Ni el campo de usuario ni el campo de contrase�a existen.");
        }
    }



    public void login(){

//      writeText(usrlocator,"giulliana123@yopmail.com");
        writeText(usrlocator,"admin");
        writeText(passlocator,"{0n[VK*{+wR?");
        click(btnLogin);
        posicionarCursorEnElemento(lblCorreo);

    }

    public void validarSubMenu(){
        validarCampoExistente(SubCorreoArg);
        validarCampoExistente(SubConexionApi);
        validarCampoExistente(SubDatosComerciales);

        // Verifica la existencia del submenu Correo Argentino
        boolean SubmenuSubCorreoArg = validarCampoExistente(SubCorreoArg);
        // Verifica la existencia del submenu ConexionApi
        boolean SubmenuSubConexionApi = validarCampoExistente(SubConexionApi);
        // Verifica la existencia del Submenu Datos Comerciales
        boolean SubmenuSubDatosComerciales = validarCampoExistente(SubDatosComerciales);

        // Imprime mensajes en la consola sobre la existencia de los campos
        if (SubmenuSubCorreoArg && SubmenuSubConexionApi && SubmenuSubDatosComerciales) {
            System.out.println("Los Elementos buscados exiten.");
        } else if (SubmenuSubCorreoArg) {
            System.out.println("El campo de usuario existe, pero el campo de Correo Arg no.");
        } else if (SubmenuSubConexionApi) {
            System.out.println("El campo de contrase�a existe, pero el campo de Conexion APi no.");
        } else if (SubmenuSubDatosComerciales) {
            System.out.println("El campo de contrase�a existe, pero el campo de Datos Comerciales no.");
        } else {
            System.out.println("Los Elementos buscados no exiten.");
        }
    }

    public void ingresarEcommerce(){

        posicionarCursorEnElemento(myWordpressLocator);
        validarCampoExistente(btnVisitarSitio);
        validarCampoExistente(btnVisitarTienda);


        // Verifica la existencia del submenu Visitar Sitio
        boolean SubmenuSitio = validarCampoExistente(btnVisitarSitio);
        // Verifica la existencia del submenu Visitar Tienda
        boolean SubmenuTienda = validarCampoExistente(SubConexionApi);

        // Imprime mensajes en la consola sobre la existencia de los campos
        if (SubmenuSitio && SubmenuTienda) {
            System.out.println("Los Elementos buscados exiten.");
        } else if (SubmenuSitio) {
            System.out.println("El campo de usuario existe, pero el campo de Visitar Sitio no.");
        } else if (SubmenuTienda) {
            System.out.println("El campo de contrase�a existe, pero el campo de Visitar Tienda no.");
        } else {
            System.out.println("Los Elementos buscados no exiten.");
        }

        waitForSeconds(2);
        click(myWordpressLocator);
        System.out.println("Ingreso correcto al Ecommerce");
    }

    public String importarPedido(){
        String Pedido = extraerNumeros(numeroPedido);
        click(myWordpressLocator);
        posicionarCursorEnElemento(lblCorreo);
        waitForSeconds(2);
        click(By.xpath("//a[contains(text(),'Correo Argentino')]"));
        // Generar el XPath dinámico concatenando el número de pedido al XPath deseado
        String xpathPedido = "//input[@value='" + Pedido + "']";
        // Hacer clic en el elemento generado utilizando el XPath dinámico
        click(By.xpath(xpathPedido));
        waitForSeconds(2);
        seleccionarOpcionPorValor(dropdownLocator, "importar");
        waitForSeconds(1);
        click(btnAplicar);
        waitForSeconds(5);
        return Pedido;
    }
}
