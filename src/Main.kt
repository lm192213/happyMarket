// Función principal del programa (punto de entrada).
fun main() {
    // Lista mutable de productos disponibles en la tienda.
    // Cada producto tiene un nombre, precio y cantidad disponible en stock.
    val productosDisponibles = mutableListOf(
        Producto("Laptop", 800.0, 5),
        Producto("Mouse", 20.0, 10),
        Producto("Teclado", 30.0, 8),
        Producto("Monitor", 150.0, 7),
        Producto("Impresora", 120.0, 4),
        Producto("Disco Duro Externo", 80.0, 6),
        Producto("Memoria USB", 15.0, 20),
        Producto("Tarjeta Gráfica", 300.0, 3),
        Producto("Procesador", 250.0, 5),
        Producto("Router", 60.0, 9),
        Producto("Altavoces", 50.0, 12),
        Producto("Webcam", 40.0, 8),
        Producto("Micrófono", 35.0, 10),
        Producto("Silla Gamer", 200.0, 4),
        Producto("Tableta Gráfica", 100.0, 6)
    )

    // Instancia de la clase Carrito para manejar los productos seleccionados por el usuario.
    val carrito = Carrito()

    // Instancia de la clase Factura para generar facturas de compra.
    val factura = Factura()

    // Bucle infinito que muestra el menú principal y maneja las interacciones del usuario.
    while (true) {
        // Limpia la pantalla de la consola para una mejor experiencia de usuario.
        clearScreen()

        // Muestra el menú principal con las opciones disponibles.
        println("\n📢 Main Menú:")
        println("1. Ver productos")
        println("2. Agregar producto al carrito")
        println("3. Eliminar producto del carrito")
        println("4. Ver carrito")
        println("5. Finalizar compra")
        println("6. Salir")

        // Solicita al usuario que seleccione una opción.
        print("Seleccione una opción: ")
        when (readLine()?.toIntOrNull()) {
            // Opción 1: Ver productos disponibles.
            1 -> {
                println("\n📦 Productos disponibles:")
                // Itera sobre la lista de productos disponibles y los muestra con su índice, nombre, precio y stock.
                productosDisponibles.forEachIndexed { index, producto ->
                    println("${index + 1}. ${producto.nombre} - ${producto.precio} USD (Stock: ${producto.cantidadDisponible})")
                }
            }
            // Opción 2: Agregar un producto al carrito.
            2 -> {
                print("Ingrese el número del producto a agregar: ")
                // Lee el índice del producto seleccionado por el usuario.
                val productoIndex = readLine()?.toIntOrNull()?.minus(1)
                // Verifica si el índice es válido (está dentro del rango de la lista de productos).
                if (productoIndex in productosDisponibles.indices) {
                    print("Ingrese la cantidad: ")
                    // Lee la cantidad deseada del producto.
                    val cantidad = readLine()?.toIntOrNull() ?: 0
                    // Agrega el producto al carrito usando el método agregarProducto de la clase Carrito.
                    carrito.agregarProducto(productosDisponibles[productoIndex!!], cantidad)
                } else {
                    // Si el índice no es válido, muestra un mensaje de advertencia.
                    println("⚠️ Opción inválida.")
                }
            }
            // Opción 3: Eliminar un producto del carrito.
            3 -> {
                // Muestra los productos en el carrito con su índice, nombre, precio, cantidad y subtotal.
                println("\n🛒 Productos en el carrito:")
                // Convierte el mapa de productos en el carrito a una lista para poder acceder por índice.
                val productosEnCarrito = carrito.obtenerProductos().toList()
                productosEnCarrito.forEachIndexed { index, (producto, cantidad) ->
                    println("${index + 1}. ${producto.nombre} - ${producto.precio} USD/u x$cantidad → Subtotal: ${producto.precio * cantidad} USD")
                }
                // Muestra el total actual del carrito.
                println("Total: ${carrito.total()} USD")

                print("Ingrese el número del producto a eliminar: ")
                // Lee el índice del producto que el usuario desea eliminar.
                val productoIndex = readLine()?.toIntOrNull()?.minus(1)
                // Verifica si el índice es válido.
                if (productoIndex != null && productoIndex in productosEnCarrito.indices) {
                    // Obtiene el producto a eliminar usando el índice.
                    val productoAEliminar = productosEnCarrito[productoIndex].first
                    // Elimina el producto del carrito usando el método eliminarProducto de la clase Carrito.
                    carrito.eliminarProducto(productoAEliminar)
                    // Muestra un mensaje de confirmación.
                    println("✅ Producto eliminado: ${productoAEliminar.nombre}")
                } else {
                    // Si el índice no es válido, muestra un mensaje de advertencia.
                    println("⚠️ Opción inválida.")
                }
            }
            // Opción 4: Ver el contenido del carrito.
            4 -> carrito.mostrarCarrito()
            // Opción 5: Finalizar la compra y generar la factura.
            5 -> {
                // Muestra el contenido del carrito.
                carrito.mostrarCarrito()
                // Genera la factura usando el método generarFactura de la clase Factura.
                factura.generarFactura(carrito)
            }
            // Opción 6: Salir del programa.
            6 -> {
                println("👋 ¡Gracias por usar la tienda!")
                // Rompe el bucle while y termina el programa.
                break
            }
            // Si el usuario ingresa una opción no válida, muestra un mensaje de advertencia.
            else -> println("⚠️ Opción inválida.")
        }
        // Espera a que el usuario presione Enter para continuar.
        println("\nPresione Enter para continuar...")
        readLine()
    }
}

// Función para limpiar la pantalla de la consola.
fun clearScreen() {
    // Verifica el sistema operativo para usar el comando correcto.
    if (System.getProperty("os.name").contains("Windows")) {
        // En Windows, usa secuencias de escape ANSI para limpiar la pantalla.
        println("\u001b[H\u001b[2J")
    } else {
        // En Linux o macOS, usa secuencias de escape ANSI para limpiar la pantalla.
        print("\u001b[H\u001b[2J")
    }
}