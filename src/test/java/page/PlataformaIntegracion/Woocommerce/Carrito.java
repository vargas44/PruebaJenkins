package page.PlataformaIntegracion.Woocommerce;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Carrito extends BasePage {

    private By radioBtnSegundaOpcion = By.xpath("(//input[@class='shipping_method'])[2]");

    private By btnVerCarrito = By.xpath("(//a[@class='added_to_cart wc_forward'])[1]");
    private By btnCarrito = By.xpath("//a[@class='wp-block-pages-list__item__link wp-block-navigation-item__content']");
   // private By btnViewCart = By.xpath("(//a[@class='added_to_cart wc_forward'])[1]");
    private By btnVerCarrito2 = By.xpath("//a[contains(text(),'Ver') and @tabindex='1']");

    private By seleccionarEnvioClasico = By.xpath("(//input[@name='shipping_method[0]'])[4]");
    private By btnFinalizarCompra = By.xpath("//a[@class='checkout-button button alt wc-forward wp-element-button']");

    public Carrito(WebDriver driver) {
        super(driver);
    }

    public void verCarrito(){

        waitForSeconds(25);
        click(btnCarrito);
        System.out.println("clic en el boton ver carrito");
        System.out.println("Se visualiza el carrito");
        click(radioBtnSegundaOpcion);
        waitForSeconds(10);
        click(btnFinalizarCompra);
        System.out.println("Se hizo clic en el boton finalizar compra");
    }

}
