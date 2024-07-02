@Woocommerce
Feature: Flujo principal de usuario en el sistema de ecommerce
  Background:
    Given el usuario se situa en el ambiente de pruebas
    And visualiza los campos de usuario y contrasena

  Scenario: Usuario realiza una compra y verifica la sincronización con MiCorreo
    Given que el usuario habilitado inicia sesion en el dashboard
    When ingresa al ecommerce
    And anade articulos al carrito
    And realiza el proceso de checkout obteniendo los numeros de pedido y monto de cotizacion
    And se visualizan los numeros de pedido en el dashboard y se importa el mismo
    Then los numeros de pedido se visualizan dentro de la plataforma MiCorreo
    And los datos en MiCorreo coinciden con los del ecommerce