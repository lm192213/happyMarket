fun main() {
    val productosDisponibles = mutableListOf(
        Producto("Laptop", 800.0, 5),
        Producto("Mouse", 20.0, 10),
        Producto("Teclado", 30.0, 8)
    )

    val carrito = Carrito()
    val factura = Factura()

    while (true) {
        // Limpiar la pantalla
        clearScreen()

        println("\n📢 Main Menú:")
        println("1. Ver productos")
        println("2. Agregar producto al carrito")
        println("3. Eliminar producto del carrito")
        println("4. Ver carrito")
        println("5. Finalizar compra")
        println("6. Salir")

        print("Seleccione una opción: ")
        when (readLine()?.toIntOrNull()) {
            1 -> {
                println("\n📦 Productos disponibles:")
                productosDisponibles.forEachIndexed { index, producto ->
                    println("${index + 1}. ${producto.nombre} - ${producto.precio} USD (Stock: ${producto.cantidadDisponible})")
                }
            }
            2 -> {
                print("Ingrese el número del producto a agregar: ")
                val productoIndex = readLine()?.toIntOrNull()?.minus(1)
                if (productoIndex in productosDisponibles.indices) {
                    print("Ingrese la cantidad: ")
                    val cantidad = readLine()?.toIntOrNull() ?: 0
                    carrito.agregarProducto(productosDisponibles[productoIndex!!], cantidad)
                } else {
                    println("⚠️ Opción inválida.")
                }
            }
            3 -> {
                // Mostrar el carrito con productos numerados
                println("\n🛒 Productos en el carrito:")
                val productosEnCarrito = carrito.obtenerProductos().toList()  // Convertimos el Map a una lista
                productosEnCarrito.forEachIndexed { index, (producto, cantidad) ->
                    println("${index + 1}. ${producto.nombre} - ${producto.precio} USD/u x$cantidad → Subtotal: ${producto.precio * cantidad} USD")
                }
                println("Total: ${carrito.total()} USD")

                print("Ingrese el número del producto a eliminar: ")
                val productoIndex = readLine()?.toIntOrNull()?.minus(1)
                if (productoIndex != null && productoIndex in productosEnCarrito.indices) {
                    val productoAEliminar = productosEnCarrito[productoIndex].first
                    carrito.eliminarProducto(productoAEliminar)
                    println("✅ Producto eliminado: ${productoAEliminar.nombre}")
                } else {
                    println("⚠️ Opción inválida.")
                }
            }
            4 -> carrito.mostrarCarrito()
            5 -> {
                carrito.mostrarCarrito()
                factura.generarFactura(carrito)
            }
            6 -> {
                println("👋 ¡Gracias por usar la tienda!")
                break
            }
            else -> println("⚠️ Opción inválida.")
        }
        // Espera a que el usuario presione Enter para continuar
        println("\nPresione Enter para continuar...")
        readLine()
    }
}

// Función para limpiar la pantalla en consola
fun clearScreen() {
    if (System.getProperty("os.name").contains("Windows")) {
        // En Windows
        println("\u001b[H\u001b[2J")
    } else {
        // En Linux o macOS
        print("\u001b[H\u001b[2J")
    }
}
