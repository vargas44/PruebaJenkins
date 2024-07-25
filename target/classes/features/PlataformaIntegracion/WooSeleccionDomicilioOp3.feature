@PPDIPI-3234
Feature: Compra de productos en la tienda a través de WordPress y Correo Argentino
  Como usuario de la tienda
  Quiero comprar productos y seleccionar opciones de envío
  Para poder completar mi compra y verificar el número de pedido

  Scenario: Compra exitosa con envío a sucursal y verificación del numero de pedido
    Given que abro el dashboard de WordPress
    And valido que los campos de usuario y contrasena existan
    And me logueo ingresando usuario y contrasena
    And valido los submenus del plugin de Correo Argentino
    And ingreso al Ecommerce
    And me dirijo a la tienda que contiene los productos
    When agrego algun producto al carrito y espero que se cargue en el carrito
    And me dirijo al Carrito
    And selecciono una opcion de envio que sea a domicilio
    And hago clic en el boton Finalizar Compra para dirigirme al checkout
    And quito el check del envio alterno
    And selecciono la tercera opcion de envio
    And hago clic en el boton de Finalizar pedido y me redirijo al detalle del pedido
    Then capturo los datos del pedido
    And visualizo el numero del pedido
    And visualizo la guia de la plataforma de mi correo
    And comparo el numero de pedido del Ecommerce con el de Mi Correo
