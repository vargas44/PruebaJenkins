package framework;


import org.openqa.selenium.interactions.Actions;import org.openqa.selenium.Keys;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class BasePage {
    private final WebDriver driver;
    public final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void click(By locator) {
        findElement(locator).click();
    }

    public void clickDoble(By locator){
        try{click(locator);
        }
        catch (Exception e){
            click(locator);
        }
    }

    public void clickWithRetry(By locator) {
        int maxAttempts = 3; // Número máximo de intentos
        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            try {
                click(locator);
                return; // Salir del método si la operación de clic tiene éxito
            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                // Manejar la excepción (puede agregar un registro o tomar una captura de pantalla aquí)
                System.err.println("Error al hacer clic en el elemento: " + e.getMessage());
                // Esperar antes de intentar nuevamente
                waitForSeconds(1);
            }
        }
        // Si todos los intentos fallan, lanzar la excepción
        throw new ElementNotInteractableException("No se pudo interactuar con el elemento después de " + maxAttempts + " intentos");
    }

    public void clickMultiple (By locator,int cantidadDeClicks) {
        int i;
        for(i=0;i<cantidadDeClicks;i++) {
            findElement(locator).click();
        }
    }


    public void moveToElementAndClick(By locator) {
        Actions actions = new Actions(driver);
        WebElement element = findElement(locator);
        actions.moveToElement(element).click().perform();
    }

    public boolean elementExists(By locator) {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator)) != null;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Método para seleccionar una opción de un dropdown por valor
    public void selectOptionFromDropdownByValue(String dropdownId, String value) {
        WebElement dropdownElement = driver.findElement(By.id(dropdownId));
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByValue(value);
    }


    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public void assertURL(String expectedUrl) {
        String currentUrl = getCurrentURL();
        Assert.assertEquals("La URL actual no coincide con la URL esperada", expectedUrl, currentUrl);
    }

    public boolean waitForUrlToBe(String url, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            return wait.until(ExpectedConditions.urlToBe(url));
        } catch (TimeoutException e) {
            return false; // El tiempo de espera se agotó antes de que la URL coincidiera
        }
    }

    public void writeText(By locator, String text) {
        WebElement element = findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    public String getText(By locator) {
        return findElement(locator).getText().trim();
    }
    public boolean compararTextoConMensajeEsperado(By locator, String textoEsperado) {
        // Obtener el texto del sitio utilizando el localizador proporcionado
        String textoDelSitio = getText(locator);
        // Comparar el texto del sitio con el texto esperado
        return textoDelSitio.equals(textoEsperado);
    }

    public String extraerNumeros(By locator) {
        WebElement element = driver.findElement(locator);
        String texto = element.getText();
        String numeros = texto.replaceAll("[^0-9]", "");
        return numeros;
    }

    public static void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void waitForElementToBeClickable(By locator) {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitUntilElementVisible (By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    protected List<WebElement> sendWebElements(By locator) {
        try {
            return driver.findElements(locator);
        } catch (NoSuchElementException e) {
            System.out.println("Elemento no encontrado con el localizador proporcionado: " + locator);
            return null;
        }
    }
    public void clickLastElementInDropdown(By locator) {
        List<WebElement> dropdownElements = sendWebElements(locator);

        if (dropdownElements != null && !dropdownElements.isEmpty()) {
            WebElement lastElement = dropdownElements.get(dropdownElements.size() - 1);
            try {
                lastElement.click();
            } catch (Exception e) {
                System.out.println("Error al hacer clic en el último elemento del menú desplegable: " + e.getMessage());
            }
        } else {
            System.out.println("No se encontraron elementos en el dropdown con el locator proporcionado: " + locator);
        }
    }
    // Método para seleccionar una opción de un elemento <select> por texto visible
    protected void seleccionarOpcionPorTextoVisible(By locator, String textoVisible) {
        WebElement selectElement = driver.findElement(locator);
        Select select = new Select(selectElement);
        select.selectByVisibleText(textoVisible);
    }

    // Método para seleccionar una opción de un elemento <select> por valor
    protected void seleccionarOpcionPorValor(By locator, String valor) {
        WebElement selectElement = driver.findElement(locator);
        Select select = new Select(selectElement);
        select.selectByValue(valor);
    }

    public void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void scrollToElement(By locator) {
        WebElement element = findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void validarCampo(String nombreCampo, By locator, String textoEsperado) {
        String textoCampo = getText(locator); // Utiliza el método getText() de BasePage para obtener el texto del elemento
        // Verifica si el texto del campo coincide con el texto esperado
        if (textoCampo.equals(textoEsperado)) {
            System.out.println("El campo '" + nombreCampo + "' está correctamente llenado: " + textoCampo);
        } else {
            System.out.println("El campo '" + nombreCampo + "' no coincide con el texto esperado.");
            System.out.println("Texto esperado: " + textoEsperado);
            System.out.println("Texto actual: " + textoCampo);
        }
    }
    public boolean validarCampoExistenteYEditable(By locator) {
        try {
            // Buscar el elemento por el locator proporcionado
            WebElement element = findElement(locator);

            // Validar que el elemento existe
            Assert.assertTrue(element.isDisplayed());

            // Validar que el elemento es editable (en este caso, solo para campos de texto)
            Assert.assertTrue(element.isEnabled());

            // Si se llega a este punto, la validación fue exitosa
            return true;
        } catch (Exception e) {
            // En caso de cualquier excepción, capturar y mostrar el mensaje de error
            e.printStackTrace();
            System.out.println("Error al validar el campo: " + e.getMessage());
            // La validación no fue exitosa
            return false;
        }
    }

    // Método personalizado para cambiar al contexto de un iframe específico
    public void switchToFrame(WebElement frameElement) {
        driver.switchTo().frame(frameElement);
    }

    // Método personalizado para regresar al contexto del iframe padre
    public void switchToParentFrame() {
        driver.switchTo().parentFrame();
    }

    // Método para cambiar al contexto predeterminado de la página
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    // Método para obtener los identificadores de todas las ventanas abiertas
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    // Método para cambiar al contexto de una ventana específica
    public void switchToWindow(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }

    // Cambia al contexto de un iframe utilizando su índice.
    public void switchToFrameByIndex(int index) {
        driver.switchTo().frame(index);
    }

    // Cambia al contexto de un iframe utilizando su nombre o ID.
    public void switchToFrameByNameOrId(String nameOrId) {
        driver.switchTo().frame(nameOrId);
    }

    // Método para abrir una nueva pestaña y navegar a una URL
    public void abrirNuevaPestanaYNavegarA(String url) {
        try {
            // Ejecutar JavaScript para abrir una nueva pestaña
            ((JavascriptExecutor) driver).executeScript("window.open()");

            // Cambiar el foco al nuevo tab
            switchToNewTab();

            // Navegar a la URL proporcionada
            driver.get(url);
        } catch (WebDriverException e) {
            System.out.println("No se pudo abrir una nueva pestaña: " + e.getMessage());
        }
    }
    // Método para cambiar el foco al nuevo tab
    private void switchToNewTab() {
        // Obtener todas las ventanas o tabs abiertas
        for (String ventana : driver.getWindowHandles()) {
            // Cambiar el foco a la nueva ventana
            driver.switchTo().window(ventana);
            break; // Salir después de cambiar al nuevo tab
        }
    }

    // Método para validar la existencia de un campo por su XPath
    public boolean validarCampoExistente(By locator) {
        try {
            driver.findElement(locator);
            return true;// El campo existe
        } catch (NoSuchElementException e) {
            return false; // El campo no existe
        }
    }



// Método para posicionar el cursor sobre un elemento identificado por su XPath

    public void posicionarCursorEnElemento(By locator) {

        WebElement element = driver.findElement(locator);

        Actions actions = new Actions(driver);

        actions.moveToElement(element).perform();

    }

    public void comparadorTexto(By locator1, By locator2) {
        try {
            String texto1 = getText(locator1);
            System.out.println(texto1);
            String texto2 = getText(locator2);
            System.out.println(texto2);
            if (texto1.equals(texto2)) {
                System.out.println("Los textos coinciden: " + texto1);
            } else {
                System.out.println("Los textos NO coinciden.");
                System.out.println("Texto 1: " + texto1);
                System.out.println("Texto 2: " + texto2);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Uno de los elementos no fue encontrado.");
        } catch (Exception e) {
            System.out.println("Ocurrió un error al comparar los textos.");
        }
    }

    public void imprimirContenidoTabla(By tablaLocator) {
        WebElement tabla = driver.findElement(tablaLocator);

        // Localiza todas las filas de la tabla
        List<WebElement> filas = tabla.findElements(By.tagName("tr"));

        // Itera sobre las filas para obtener los valores
        for (WebElement fila : filas) {
            // Localiza todas las celdas de la fila
            List<WebElement> celdas = fila.findElements(By.tagName("td"));
            // Imprime el texto de cada celda
            for (WebElement celda : celdas) {
                System.out.print(celda.getText() + " ");
            }
            System.out.println(); // Salto de línea entre filas
        }
    }
    public List<List<String>> obtenerContenidoTabla(By tablaLocator, String valorBuscado) {
        List<List<String>> contenidoTabla = new ArrayList<>();
        WebElement tabla = driver.findElement(tablaLocator);
        // Localiza todas las filas de la tabla
        List<WebElement> filas = tabla.findElements(By.tagName("tr"));
        // Itera sobre las filas para obtener los valores
        for (WebElement fila : filas) {
            // Lista para almacenar las celdas de la fila actual
            List<String> celdasFila = new ArrayList<>();
            // Localiza todas las celdas de la fila
            List<WebElement> celdas = fila.findElements(By.tagName("td"));
            // Agrega el texto de cada celda a la lista de celdas de la fila actual
            for (WebElement celda : celdas) {
                String textoCelda = celda.getText();
                celdasFila.add(textoCelda);
                // Si la celda contiene el valor buscado, puedes hacer algo aquí
                if (textoCelda.equals(valorBuscado)) {
                    System.out.println("Valor encontrado en la tabla: " + textoCelda);
                    // Puedes realizar alguna acción específica, como romper el bucle o almacenar la fila que contiene el valor buscado
                }
            }
            // Agrega la lista de celdas de la fila actual a la lista de filas
            contenidoTabla.add(celdasFila);
        }
        return contenidoTabla;
    }

    public void sendEnter() {
        Actions actions = new Actions(driver);
            actions.sendKeys(Keys.ENTER).perform();
    }

    public void sendBorrar(int repetir) {
        Actions actions = new Actions(driver);
        for (int i=0;i<repetir;i++){actions.sendKeys(Keys.DELETE).perform();}

    }

    public void scrollPageUpDown(int Up,int Down) {
        Actions actions = new Actions(driver);
        int i;
        for (i=0;i<Up;i++) {
            actions.sendKeys(Keys.PAGE_UP).perform();
        }

        for (i = 0; i < Down; i++) {
            actions.sendKeys(Keys.PAGE_DOWN).perform();
        }

    }

    public String generadorCorreos(){
        String[] domains = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com"};
        Random random = new Random();

        // Generar un nombre de usuario aleatorio
        String username = "user" + random.nextInt(1000);

        // Seleccionar un dominio aleatorio
        String domain = domains[random.nextInt(domains.length)];

        // Crear la dirección de correo electrónico aleatoria
        String email = username + "@" + domain;

        return email;
    }
    public String generadornombres(){
        Random random = new Random();
        // Generar un nombre de usuario aleatorio
        String username = "user" + random.nextInt(1000);
        return username;
    }

    public String caracteresAleatorios (int length) {
            String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
            Random random = new Random();
            StringBuilder sb = new StringBuilder(length);

            for (int i = 0; i < length; i++) {
                int index = random.nextInt(characters.length());
                sb.append(characters.charAt(index));
            }

            return sb.toString();
        }
    public String numerosAleatorios (int length) {
        String characters = "012356789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }


}

