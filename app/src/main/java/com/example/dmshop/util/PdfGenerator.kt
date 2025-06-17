package com.example.dmshop.util

import android.content.Context
import android.graphics.pdf.PdfDocument
import android.print.PrintAttributes
import android.print.pdf.PrintedPdfDocument
import com.example.dmshop.domain.model.CartState
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * Utilidad para generar documentos PDF.
 */
class PdfGenerator(private val context: Context) {

    /**
     * Genera un PDF con los datos proporcionados.
     * @param data Datos a incluir en el PDF
     * @return ByteArray con el contenido del PDF
     */
    fun generatePdf(data: Map<String, Any>): ByteArray {
        // TODO: Implementar la generación de PDF con PdfDocument
        val pdfDocument = PrintedPdfDocument(
            context,
            PrintAttributes.Builder()
                .setMediaSize(PrintAttributes.MediaSize.ISO_A4)
                .setResolution(PrintAttributes.Resolution("pdf", "pdf", 300, 300))
                .setMinMargins(PrintAttributes.Margins.NO_MARGINS)
                .build()
        )

        return ByteArray(0) // Placeholder
    }

    fun generate(cartState: CartState): File {
        val pdfDocument = PrintedPdfDocument(
            context,
            PrintAttributes.Builder()
                .setMediaSize(PrintAttributes.MediaSize.ISO_A4)
                .setResolution(PrintAttributes.Resolution("pdf", "pdf", 300, 300))
                .setMinMargins(PrintAttributes.Margins.NO_MARGINS)
                .build()
        )

        val page = pdfDocument.startPage(0)
        val canvas = page.canvas

        var y = 100f
        val paint = android.graphics.Paint().apply {
            textSize = 12f
        }

        // Título
        canvas.drawText("Pedido DMShop", 100f, y, paint.apply { textSize = 24f })
        y += 50f

        // Fecha
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        canvas.drawText("Fecha: ${dateFormat.format(Date())}", 100f, y, paint)
        y += 30f

        // Datos del cliente
        canvas.drawText("Datos del Cliente:", 100f, y, paint.apply { textSize = 16f })
        y += 30f
        canvas.drawText("Nombre: ${cartState.customerName}", 100f, y, paint)
        y += 20f
        canvas.drawText("Contacto: ${cartState.contact}", 100f, y, paint)
        y += 20f
        canvas.drawText("Fecha y Hora: ${cartState.dateTime}", 100f, y, paint)
        y += 20f
        canvas.drawText("Dirección: ${cartState.address}", 100f, y, paint)
        if (cartState.notes.isNotEmpty()) {
            y += 20f
            canvas.drawText("Notas: ${cartState.notes}", 100f, y, paint)
        }
        y += 40f

        // Productos
        canvas.drawText("Productos:", 100f, y, paint.apply { textSize = 16f })
        y += 30f
        cartState.items.forEach { item ->
            canvas.drawText("${item.product.name} - Talla: ${item.size}", 100f, y, paint)
            y += 20f
            canvas.drawText("Cantidad: ${item.quantity} - Subtotal: $${item.subtotal}", 120f, y, paint)
            y += 30f
        }

        // Total
        y += 20f
        canvas.drawText("Total: $${cartState.total}", 100f, y, paint.apply { textSize = 18f })

        pdfDocument.finishPage(page)

        val file = File(context.cacheDir, "pedido_${System.currentTimeMillis()}.pdf")
        pdfDocument.writeTo(file.outputStream())
        pdfDocument.close()

        return file
    }
} 