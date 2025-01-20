package com.kakkus.quizapp.common

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CustomPasswordVisualTransformation(
    private val mask: Char = '*' // Varsayılan olarak büyük nokta, isterseniz '*' de kullanabilirsiniz
) : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        // Her karakter için mask uyguluyoruz (örneğin, '*' veya '•')
        val transformedText = AnnotatedString(mask.toString().repeat(text.length))

        // OffsetMapping: Orijinal metin ve masklenmiş metin arasındaki haritalama
        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int = offset
            override fun transformedToOriginal(offset: Int): Int = offset
        }

        return TransformedText(transformedText, offsetMapping)
    }
}