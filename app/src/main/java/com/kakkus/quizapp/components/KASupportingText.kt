package com.kakkus.quizapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.kakkus.quizapp.R
import com.kakkus.quizapp.components.KATextFieldType.KATextFieldWidthSizeCategory.StandardTextFieldWidth
import com.kakkus.quizapp.ui.theme.ErrorRed
import com.kakkus.quizapp.ui.theme.MontserratTinyStyle

@Composable
fun KASupportingText(
    text: String?
) {
    text?.let {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(top = 16.dp, start = 4.dp).width(StandardTextFieldWidth)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_alert_icon),
                contentDescription = "",
                tint = ErrorRed
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = text,
                style = MontserratTinyStyle.Regular.copy(
                    color = ErrorRed
                )
            )
        }
    }

}