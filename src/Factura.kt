// Definición de la clase Factura, que se encarga de generar una factura de compra.
class Factura {

    // Función para generar una factura basada en los productos en el carrito.
    // Recibe un objeto de tipo Carrito como parámetro.
    fun generarFactura(carrito: Carrito) {
        // Obtiene los productos del carrito utilizando el método obtenerProductos().
        val productos = carrito.obtenerProductos()

        // Verifica si el carrito está vacío.
        if (productos.isEmpty()) {
            // Si no hay productos en el carrito, muestra un mensaje de advertencia y termina la función.
            println("⚠️ No hay productos para facturar.")
            return
        }

        // Si hay productos en el carrito, comienza a generar la factura.
        println("\n🧾 Factura de Compra") // Encabezado de la factura.
        println("=================================") // Línea separadora.

        var total = 0.0 // Variable para almacenar el total de la factura.

        // Itera sobre cada producto en el mapa de productos.
        productos.forEach { (producto, cantidad) ->
            // Calcula el subtotal para cada producto (precio * cantidad).
            val subtotal = cantidad * producto.precio

            // Muestra el nombre del producto, la cantidad, el precio unitario y el subtotal.
            println("${producto.nombre} x$cantidad - ${producto.precio} USD/u → Subtotal: ${subtotal} USD")

            // Suma el subtotal al total general de la factura.
            total += subtotal
        }

        // Muestra una línea separadora para organizar la factura.
        println("=================================")

        // Muestra el total a pagar.
        println("💰 Total a pagar: $total USD")

        // Muestra un mensaje de agradecimiento por la compra.
        println("✅ ¡Gracias por su compra!\n")
    }
}