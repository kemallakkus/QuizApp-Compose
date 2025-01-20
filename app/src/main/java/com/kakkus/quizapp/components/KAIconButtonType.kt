package com.kakkus.quizapp.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kakkus.quizapp.R
import com.kakkus.quizapp.components.KAIconButtonType.KAIconHeightSizeCategory.StandardIconButtonHeight
import com.kakkus.quizapp.ui.theme.GeneralRed
import com.kakkus.quizapp.ui.theme.Gray10
import com.kakkus.quizapp.ui.theme.Gray100
import com.kakkus.quizapp.ui.theme.Gray20
import com.kakkus.quizapp.ui.theme.Gray40
import com.kakkus.quizapp.ui.theme.Gray70
import com.kakkus.quizapp.ui.theme.MontserratBigParagraphStyle
import com.kakkus.quizapp.ui.theme.QuizAppTheme
import com.kakkus.quizapp.ui.theme.White

object KAIconButtonType {
    @Composable
    fun saIconButtonColor(
        containerColor: Color = Gray40,
        contentColor: Color = Gray100,
        disabledContainerColor: Color = Gray20,
        disabledContentColor: Color = Gray10,
    ): IconButtonColors = IconButtonDefaults.filledIconButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )

    object KAIconHeightSizeCategory {
        val StandardIconButtonHeight: Dp = 40.dp
    }

    data class KAButtonIconSize(
        val size: Dp = StandardIconButtonHeight,
    )

    @Composable
    fun KAIconButton(
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        shape: Shape = IconButtonDefaults.filledShape,
        buttonSize: KAButtonIconSize = KAButtonIconSize(),
        colors: IconButtonColors = saIconButtonColor(),
        interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
        content: @Composable () -> Unit,
    ) {
        val privateModifier =
            if (buttonSize.size == 0.dp) Modifier else Modifier.size(buttonSize.size)
        FilledIconButton(
            onClick = onClick,
            modifier = privateModifier
                .then(modifier),
            enabled = enabled,
            colors = colors,
            interactionSource = interactionSource,
            content = content,
            shape = shape,
        )
    }
}

@KAPreview
@Composable
fun SADecrementButtonIconButtonPreview() {
    QuizAppTheme  {
        KAIconButtonType.KAIconButton(
            onClick = {
            }
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_back_arrow),
                contentDescription = "",
                tint = Gray70
            )
        }
    }
}

@Composable
fun SkipAndNextButton(
    modifier : Modifier,
    text: String,
    backGround: Color = GeneralRed,
    textColor: Color = White,
    onClick: () -> Unit,
) {

    Button(
        modifier = modifier
            .padding(horizontal = 10.dp),
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backGround
        )
    ) {
        Text(
            modifier = Modifier.padding(vertical = 5.dp),
            text = text,
            color = textColor,
            style = MontserratBigParagraphStyle.SemiBold
        )
    }
}