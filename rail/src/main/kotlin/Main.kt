package org.example

import javax.swing.JOptionPane

fun railFenceCifrado(mensaje: String, rails: Int): String {
    val matriz = Array(rails) { CharArray(mensaje.length) { ' ' } }
    var direccion = -1
    var fila = 0

    for (i in mensaje.indices) {
        matriz[fila][i] = mensaje[i]
        if (fila == 0 || fila == rails - 1) {
            direccion *= -1
        }
        fila += direccion
    }

    val resultado = StringBuilder()
    for (i in 0 until rails) {
        for (j in 0 until mensaje.length) {
            if (matriz[i][j] != ' ') {
                resultado.append(matriz[i][j])
            }
        }
    }

    return resultado.toString()
}

fun railFenceDescifrado(mensaje: String, rails: Int): String {
    val matriz = Array(rails) { CharArray(mensaje.length) { ' ' } }
    val longitud = mensaje.length
    var direccion = -1
    var fila = 0
    val posiciones = IntArray(longitud)

    for (i in 0 until longitud) {
        posiciones[i] = fila
        if (fila == 0 || fila == rails - 1) {
            direccion *= -1
        }
        fila += direccion
    }

    var index = 0
    for (i in 0 until rails) {
        for (j in 0 until longitud) {
            if (posiciones[j] == i) {
                matriz[i][j] = mensaje[index++]
            }
        }
    }

    val resultado = StringBuilder()
    fila = 0
    for (i in 0 until longitud) {
        resultado.append(matriz[fila][i])
        if (fila == 0 || fila == rails - 1) {
            direccion *= -1
        }
        fila += direccion
    }

    return resultado.toString()
}

fun main() {
    var mensajeOriginal: String
    var rails: Int
    var opcion: Int

    while (true) {
        mensajeOriginal = JOptionPane.showInputDialog("Dame el mensaje (o 'salir' para terminar)") ?: ""
        if (mensajeOriginal.equals("salir", ignoreCase = true)) break

        val numeroDeRails = JOptionPane.showInputDialog("Dame el número de rails") ?: "3"
        rails = numeroDeRails.toIntOrNull() ?: 3

        opcion = JOptionPane.showInputDialog("Selecciona una opción: 1 para Cifrar, 2 para Descifrar ")?.toIntOrNull() ?: 1

        if (opcion == 1) {
            val mensajeCifrado = railFenceCifrado(mensajeOriginal, rails)
            JOptionPane.showMessageDialog(null, "El texto cifrado es: $mensajeCifrado")
        } else {
            val mensajeDescifrado = railFenceDescifrado(mensajeOriginal, rails)
            JOptionPane.showMessageDialog(null, "El texto descifrado es: $mensajeDescifrado")
        }
    }
}
