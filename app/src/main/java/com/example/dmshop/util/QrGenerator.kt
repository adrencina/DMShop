package com.example.dmshop.util

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder

/**
 * Utilidad para generar códigos QR.
 */
class QrGenerator {

    /**
     * Genera un código QR con el contenido proporcionado.
     * @param content Contenido a codificar en el QR
     * @param size Tamaño del código QR en píxeles
     * @return Bitmap con el código QR generado
     */
    fun generateQrCode(content: String, size: Int = 512): Bitmap {
        val multiFormatWriter = MultiFormatWriter()
        val bitMatrix: BitMatrix = multiFormatWriter.encode(
            content,
            BarcodeFormat.QR_CODE,
            size,
            size
        )
        return BarcodeEncoder().createBitmap(bitMatrix)
    }
} 