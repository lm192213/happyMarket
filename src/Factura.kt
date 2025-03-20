class Factura {
    fun generarFactura(carrito: Carrito) {
        val productos = carrito.obtenerProductos()
        if (productos.isEmpty()) {
            println("⚠️ No hay productos para facturar.")
            return
        }

        println("\n🧾 Factura de Compra")
        println("=================================")
        var total = 0.0
        productos.forEach { (producto, cantidad) ->
            val subtotal = cantidad * producto.precio
            println("${producto.nombre} x$cantidad - ${producto.precio} USD/u → Subtotal: ${subtotal} USD")
            total += subtotal
        }
        println("=================================")
        println("💰 Total a pagar: $total USD")
        println("✅ ¡Gracias por su compra!\n")
    }
}
