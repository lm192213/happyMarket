// Definición de la clase Carrito, que representa el carrito de compras de una tienda.
class Carrito {
    // Mapa mutable que almacena los productos en el carrito y sus cantidades.
    // La clave es un objeto de tipo Producto y el valor es la cantidad de ese producto en el carrito.
    private val productosEnCarrito = mutableMapOf<Producto, Int>()

    // Función para agregar un producto al carrito.
    // Recibe un objeto Producto y la cantidad que se desea agregar.
    fun agregarProducto(producto: Producto, cantidad: Int) {
        // Verifica si la cantidad solicitada es mayor que la cantidad disponible en stock.
        if (cantidad > producto.cantidadDisponible) {
            // Si no hay suficiente stock, muestra un mensaje de advertencia y no agrega el producto.
            println("⚠️ No hay suficiente stock de ${producto.nombre}. Disponible: ${producto.cantidadDisponible}")
            return
        }
        // Si hay suficiente stock, agrega el producto al carrito o incrementa la cantidad si ya está en el carrito.
        productosEnCarrito[producto] = productosEnCarrito.getOrDefault(producto, 0) + cantidad
        // Reduce la cantidad disponible en stock del producto.
        producto.cantidadDisponible -= cantidad
        // Muestra un mensaje de confirmación de que el producto fue agregado al carrito.
        println("✅ ${cantidad}x ${producto.nombre} agregado al carrito.")
    }

    // Función para eliminar un producto del carrito.
    // Recibe un objeto Producto que se desea eliminar.
    fun eliminarProducto(producto: Producto) {
        // Verifica si el producto está en el carrito.
        if (productosEnCarrito.containsKey(producto)) {
            // Obtiene la cantidad actual del producto en el carrito.
            val cantidadActual = productosEnCarrito[producto]!!
            if (cantidadActual > 1) {
                // Si hay más de una unidad, reduce la cantidad en 1.
                productosEnCarrito[producto] = cantidadActual - 1
                println("✅ Se eliminó 1 unidad de ${producto.nombre} del carrito.")
            } else {
                // Si solo hay una unidad, elimina el producto por completo del carrito.
                productosEnCarrito.remove(producto)
                println("🗑️ Producto eliminado del carrito: ${producto.nombre}")
            }
            // Restaura una unidad del producto al stock disponible.
            producto.cantidadDisponible += 1
        } else {
            // Si el producto no está en el carrito, muestra un mensaje de advertencia.
            println("⚠️ El producto no está en el carrito.")
        }
    }

    // Función para mostrar el contenido actual del carrito.
    fun mostrarCarrito() {
        // Verifica si el carrito está vacío.
        if (productosEnCarrito.isEmpty()) {
            println("🛒 El carrito está vacío.")
            return
        }
        // Si no está vacío, muestra los productos en el carrito con sus detalles.
        println("\n🛒 Carrito de Compras:")
        var total = 0.0
        // Itera sobre cada producto en el carrito.
        productosEnCarrito.forEach { (producto, cantidad) ->
            // Calcula el subtotal para cada producto (precio * cantidad).
            val subtotal = cantidad * producto.precio
            // Muestra el nombre del producto, la cantidad, el precio unitario y el subtotal.
            println("${producto.nombre} x$cantidad - ${producto.precio} USD/u → Subtotal: ${subtotal} USD")
            // Suma el subtotal al total general del carrito.
            total += subtotal
        }
        // Muestra el total general del carrito.
        println("💰 Total: $total USD")
    }

    // Función para calcular el total del carrito.
    // Retorna el valor total de todos los productos en el carrito.
    fun total(): Double {
        var total = 0.0
        // Itera sobre cada producto en el carrito y suma el precio total de cada uno.
        productosEnCarrito.forEach { (producto, cantidad) ->
            total += producto.precio * cantidad
        }
        return total
    }

    // Función para obtener el mapa de productos en el carrito.
    // Retorna un mapa inmutable con los productos y sus cantidades.
    fun obtenerProductos(): Map<Producto, Int> = productosEnCarrito
}