class Carrito {
    private val productosEnCarrito = mutableMapOf<Producto, Int>()

    fun agregarProducto(producto: Producto, cantidad: Int) {
        if (cantidad > producto.cantidadDisponible) {
            println("⚠️ No hay suficiente stock de ${producto.nombre}. Disponible: ${producto.cantidadDisponible}")
            return
        }
        productosEnCarrito[producto] = productosEnCarrito.getOrDefault(producto, 0) + cantidad
        producto.cantidadDisponible -= cantidad
        println("✅ ${cantidad}x ${producto.nombre} agregado al carrito.")
    }

    fun eliminarProducto(producto: Producto) {
        if (productosEnCarrito.containsKey(producto)) {
            val cantidadActual = productosEnCarrito[producto]!!
            if (cantidadActual > 1) {
                // Si hay más de una unidad, solo restamos una
                productosEnCarrito[producto] = cantidadActual - 1
                println("✅ Se eliminó 1 unidad de ${producto.nombre} del carrito.")
            } else {
                // Si solo queda una unidad, la eliminamos por completo
                productosEnCarrito.remove(producto)
                println("🗑️ Producto eliminado del carrito: ${producto.nombre}")
            }
            producto.cantidadDisponible += 1 // Restauramos el stock
        } else {
            println("⚠️ El producto no está en el carrito.")
        }
    }

    fun mostrarCarrito() {
        if (productosEnCarrito.isEmpty()) {
            println("🛒 El carrito está vacío.")
            return
        }
        println("\n🛒 Carrito de Compras:")
        var total = 0.0
        productosEnCarrito.forEach { (producto, cantidad) ->
            val subtotal = cantidad * producto.precio
            println("${producto.nombre} x$cantidad - ${producto.precio} USD/u → Subtotal: ${subtotal} USD")
            total += subtotal
        }
        println("💰 Total: $total USD")
    }

    // Método total para calcular el total del carrito
    fun total(): Double {
        var total = 0.0
        productosEnCarrito.forEach { (producto, cantidad) ->
            total += producto.precio * cantidad
        }
        return total
    }

    fun obtenerProductos(): Map<Producto, Int> = productosEnCarrito
}
