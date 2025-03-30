## 📹 Video YT

Puedes ver una demostración del proyecto en el siguiente enlace:

[🔗 Ver en YouTube](https://youtu.be/kfCMkreENV8)


# 🛒 Tienda en Consola en Kotlin

## Integrantes

- Ronald Vladimir Leon Marroquin      LM192213
- Rene Alexander Hernanedez Soriano   HS191498
- Manuel Antonio Bolaños Marcía       BM192191
- Christopher Alberto Muñoz Reyes     MR202832
- Cristian Alberto Muñoz Reyes        MR211303

Este es un proyecto simple de consola hecho en **Kotlin**, que simula una tienda en línea. Permite a los usuarios ver productos disponibles, agregarlos al carrito, eliminarlos, ver el total y finalizar la compra con una factura.

## 🚀 Funcionalidades

- Ver los productos disponibles y su stock
- Agregar productos al carrito
- Eliminar productos del carrito
- Ver el contenido actual del carrito y el total
- Generar una factura al finalizar la compra

## 📦 Estructura del Proyecto

- `Producto`: Representa un producto con nombre, precio y cantidad disponible.
- `Carrito`: Clase para manejar los productos añadidos, calcular el total y mostrar el carrito.
- `Factura`: Genera una factura al finalizar la compra.
- `Main.kt`: Contiene el menú principal interactivo y la lógica de interacción.

## ▶️ Ejecución

Para ejecutar este proyecto, necesitas tener instalado Kotlin. Puedes compilar y ejecutar con:

```bash
kotlinc Main.kt -include-runtime -d Tienda.jar
java -jar Tienda.jar

#
