package stepsDefinitions.Google;
import framework.BasePage;
import framework.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static framework.BasePage.waitForSeconds;


public class GoogleSteps {
    private WebDriver driver = DriverManager.getDriver();
    private String baseUrl = DriverManager.config.getProperty("url");
    private BasePage basePage = new BasePage(driver);
    @Given(  "El usuario se situa en la pagina de google")
    public void elUsuarioSeSituaEnLosCamposEmailYPassword() {
        driver.get(baseUrl);
    }

    @When("El usuario ingresa a mercado libre")
    public void elUsuarioIngresaAMercadoLibre() {
        basePage.writeText(By.xpath("//textarea[@id='APjFqb']"),"Mercado Libre");
        waitForSeconds(2);
    }
    @And("Preciona buscar")
    public void precionaBuscar() {
        basePage.click(By.xpath("//div[@class='lJ9FBc']//input[@name='btnK']"));

    }
    @Then("Aparece el sitio buscado")
    public void apareceElSitioBuscado() {
        basePage.assertURL("https://www.google.com/search?q=Mercado+Libre&sca_esv=2b629f2852664d00&hl=es&source=hp&ei=Kzo1ZsikFYnS1sQPyISH8AI&iflsig=ANes7DEAAAAAZjVIO6AzPWCfZRXEdD8uho9bRFomGTy9&ved=0ahUKEwiI5t_2mvKFAxUJqZUCHUjCAS4Q4dUDCA0&oq=Mercado+Libre&gs_lp=Egdnd3Mtd2l6Ig1NZXJjYWRvIExpYnJlMhkQLhiABBixAxjRAxhDGIMBGMcBGMkDGIoFMgsQABiABBiSAxiKBTILEAAYgAQYkgMYigUyCBAAGIAEGLEDMg0QABiABBixAxhDGIoFMg0QABiABBixAxhDGIoFMgUQABiABDIFEAAYgAQyCxAAGIAEGLEDGIMBMg0QABiABBixAxhDGIoFSNbSalCyiWpYjLdqcAh4AJABAJgBmAGgAekMqgEEMTIuNbgBDMgBAPgBAZgCGaAC6A6oAgrCAhoQABiABBi0AhjUAxjlAhi3AxiKBRjqAhiKA8ICFxAAGIAEGLQCGOUCGLcDGIoFGOoCGIoDwgIREC4YgAQYsQMY0QMYgwEYxwHCAhYQLhiABBixAxjRAxhDGIMBGMcBGIoFwgIKEAAYgAQYQxiKBcICChAuGIAEGEMYigXCAhAQLhiABBjRAxhDGMcBGIoFwgIQEAAYgAQYsQMYQxiDARiKBcICDRAuGIAEGLEDGEMYigXCAhMQLhiABBixAxjRAxiDARjHARgKwgIFEC4YgATCAgsQLhiABBjHARivAZgDGpIHBDE4LjegB7S3AQ&sclient=gws-wiz");
    }




}
