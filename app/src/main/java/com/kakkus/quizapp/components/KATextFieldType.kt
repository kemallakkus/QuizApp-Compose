package com.kakkus.quizapp.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kakkus.quizapp.R
import com.kakkus.quizapp.components.KATextFieldType.KATextFieldBorderWidthCategory.StandardTextFieldBorderWidth
import com.kakkus.quizapp.components.KATextFieldType.KATextFieldShapeCategory.StandardTextFieldShape
import com.kakkus.quizapp.ui.theme.ErrorRed
import com.kakkus.quizapp.ui.theme.Gray10
import com.kakkus.quizapp.ui.theme.Gray100
import com.kakkus.quizapp.ui.theme.Gray30
import com.kakkus.quizapp.ui.theme.Gray50
import com.kakkus.quizapp.ui.theme.Gray90
import com.kakkus.quizapp.ui.theme.MontserratLabelStyle
import com.kakkus.quizapp.ui.theme.MontserratTinyStyle
import com.kakkus.quizapp.ui.theme.NavyBlue90
import com.kakkus.quizapp.ui.theme.QuizAppTheme
import com.kakkus.quizapp.ui.theme.Red20
import com.kakkus.quizapp.ui.theme.Transparent

object KATextFieldType {
    @Composable
    fun KATextFieldColor(
        focusedTextColor: Color = Gray100,
        unfocusedTextColor: Color = Gray90,
        focusedPlaceholderColor: Color = Gray50,
        unfocusedPlaceholderColor: Color = Gray50,
        disabledContainerColor: Color = Gray10,
        errorContainerColor: Color = Gray10,
        focusedContainerColor: Color = Gray10,
        unfocusedContainerColor: Color = Gray10,
        cursorColor: Color = NavyBlue90,
        errorCursorColor: Color = Red20,
        selectionColors: TextSelectionColors = TextSelectionColors(
            handleColor = focusedTextColor,
            backgroundColor = focusedTextColor.copy(0.5f)
        ),
        focusedBorderColor: Color = Gray50,
        unfocusedBorderColor: Color = Gray30,
        disabledBorderColor: Color = Gray30,
        errorBorderColor: Color = ErrorRed,
    ): TextFieldColors = OutlinedTextFieldDefaults.colors(
        // Text
        focusedTextColor = focusedTextColor,
        unfocusedTextColor = focusedTextColor,
        disabledTextColor = focusedTextColor,
        errorTextColor = focusedTextColor,
        // Selection
        selectionColors = selectionColors,
        // Supporting
        focusedSupportingTextColor = focusedTextColor,
        unfocusedSupportingTextColor = focusedTextColor,
        disabledSupportingTextColor = focusedTextColor,
        errorSupportingTextColor = focusedTextColor,
        // Placeholder
        focusedPlaceholderColor = focusedPlaceholderColor,
        unfocusedPlaceholderColor = unfocusedPlaceholderColor,
        disabledPlaceholderColor = focusedTextColor,
        errorPlaceholderColor = focusedTextColor,
        // Container
        disabledContainerColor = disabledContainerColor,
        errorContainerColor = errorContainerColor,
        focusedContainerColor = focusedContainerColor,
        unfocusedContainerColor = unfocusedContainerColor,
        // Cursor
        cursorColor = cursorColor,
        errorCursorColor = errorCursorColor,
        // Indicator
        focusedBorderColor = focusedBorderColor,
        unfocusedBorderColor = unfocusedBorderColor,
        disabledBorderColor = disabledBorderColor,
        errorBorderColor = errorBorderColor,
        // LeadingIcon
        focusedLeadingIconColor = focusedTextColor,
        unfocusedLeadingIconColor = unfocusedTextColor,
        disabledLeadingIconColor = focusedTextColor,
        errorLeadingIconColor = focusedTextColor,
        // TrailingIcon
        focusedTrailingIconColor = focusedTextColor,
        unfocusedTrailingIconColor = unfocusedTextColor,
        disabledTrailingIconColor = focusedTextColor,
        errorTrailingIconColor = focusedTextColor,
        // Label
        focusedLabelColor = focusedTextColor,
        unfocusedLabelColor = focusedTextColor,
        disabledLabelColor = focusedTextColor,
        errorLabelColor = focusedTextColor,
        // Prefix
        focusedPrefixColor = focusedTextColor,
        unfocusedPrefixColor = focusedTextColor,
        disabledPrefixColor = focusedTextColor,
        errorPrefixColor = focusedTextColor,
        // Suffix
        focusedSuffixColor = focusedTextColor,
        unfocusedSuffixColor = focusedTextColor,
        disabledSuffixColor = focusedTextColor,
        errorSuffixColor = focusedTextColor
    )

    object KATextFieldWidthSizeCategory {
        val StandardTextFieldWidth: Dp = 350.dp
    }

    object KATextFieldBorderWidthCategory {
        val StandardTextFieldBorderWidth: Dp = 1.dp
    }

    object KATextFieldShapeCategory {
        val StandardTextFieldShape = RoundedCornerShape(10.dp)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun KATextField(
        value: String,
        onValueChange: (String) -> Unit,
        modifier: Modifier = Modifier,
        titleLabel: String? = null,
        helpText: String? = null,
        enabled: Boolean = true,
        readOnly: Boolean = false,
        textStyle: TextStyle = MontserratLabelStyle.Regular.copy(
            color = Gray90
        ),
        label: @Composable (() -> Unit)? = null,
        placeholder: @Composable (() -> Unit)? = null,
        leadingIcon: @Composable (() -> Unit)? = null,
        trailingIcon: @Composable (() -> Unit)? = null,
        supportingText: @Composable (() -> Unit)? = null,
        prefix: @Composable (() -> Unit)? = null,
        suffix: @Composable (() -> Unit)? = null,
        isError: Boolean = false,
        width: Dp? = null,
        height: Dp = 48.dp,
        visualTransformation: VisualTransformation = VisualTransformation.None,
        keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
        keyboardActions: KeyboardActions = KeyboardActions.Default,
        singleLine: Boolean = true,
        maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
        minLines: Int = 1,
        interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
        shape: Shape = StandardTextFieldShape,
        colors: TextFieldColors = KATextFieldColor()
    ) {
        var helpTextHeight by remember {
            mutableStateOf(12.dp)
        }

        val localDensity = LocalDensity.current

        Column(
            modifier = modifier // Tüm bileşenlere aynı padding uygulanacak
                .fillMaxWidth()
        ) {
            titleLabel?.let {
                // Title Label'a padding ekleniyor
                Text(
                    text = titleLabel,
                    style = MontserratLabelStyle.Regular.copy(color = Gray90),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.padding(4.dp)) // Title ile TextField arasında boşluk
            }

            // TextField yüksekliği burada ayarlanıyor
            BasicTextField(
                value = value,
                onValueChange ={
                    onValueChange(it)
                } ,
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                maxLines = maxLines,
                minLines = minLines,
                textStyle = textStyle,
                singleLine = singleLine,
                readOnly = readOnly,
                enabled = enabled,
                interactionSource = interactionSource,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height) // Burada yükseklik ayarlanıyor
                    .padding(horizontal = 0.dp) // İçerik yatay olarak genişliyor
                    .then(Modifier),
                decorationBox = @Composable { innerTextField ->
                    OutlinedTextFieldDefaults.DecorationBox(
                        value = value,
                        visualTransformation = visualTransformation,
                        innerTextField = innerTextField,
                        placeholder = placeholder,
                        label = label,
                        leadingIcon = leadingIcon,
                        trailingIcon = trailingIcon,
                        prefix = prefix,
                        suffix = suffix,
                        singleLine = singleLine,
                        enabled = enabled,
                        isError = isError,
                        interactionSource = interactionSource,
                        colors = colors,
                        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = helpTextHeight)
                    ) {
                        OutlinedTextFieldDefaults.ContainerBox(
                            enabled,
                            isError,
                            interactionSource,
                            colors,
                            shape,
                            focusedBorderThickness = StandardTextFieldBorderWidth,
                            unfocusedBorderThickness = StandardTextFieldBorderWidth,
                        )
                        if (height > 84.dp && helpText != null) {
                            Box(
                                modifier = Modifier.fillMaxHeight()
                            ) {
                                Text(
                                    text = helpText,
                                    style = MontserratTinyStyle.Regular.copy(
                                        color = Gray50
                                    ),
                                    onTextLayout = {
                                        helpTextHeight = 16.dp + maxOf(with(localDensity) { it.size.height.toDp() }, 12.dp)
                                    },
                                    modifier = Modifier
                                        .padding(bottom = 16.dp, end = 14.dp)
                                        .align(Alignment.BottomEnd)
                                )
                            }
                        }
                    }
                }
            )

            if (supportingText != null) {
                supportingText()
            }
        }
    }
}

@KAPreview
@Composable
fun TextFieldPreview() {
    QuizAppTheme  {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            KATextFieldType.KATextField(
                value = "",
                onValueChange = {}
            )

            Spacer(modifier = Modifier.padding(8.dp))

            KATextFieldType.KATextField(
                value = "",
                onValueChange = {},
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.email)
                    )
                }
            )

            Spacer(modifier = Modifier.padding(8.dp))

            KATextFieldType.KATextField(
                value = stringResource(id = R.string.email),
                onValueChange = {},
            )

            Spacer(modifier = Modifier.padding(8.dp))

            KATextFieldType.KATextField(
                value = stringResource(id = R.string.email),
                onValueChange = {},
                trailingIcon = {
                    KAIconButtonType.KAIconButton(
                        onClick = {},
                        colors = KAIconButtonType.saIconButtonColor(
                            containerColor = Transparent
                        )
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_visibility_icon),
                            contentDescription = ""
                        )
                    }
                },
            )

            Spacer(modifier = Modifier.padding(8.dp))

            KATextFieldType.KATextField(
                value = stringResource(id = R.string.email),
                onValueChange = {},
                leadingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
                        contentDescription = ""
                    )
                },
            )

            Spacer(modifier = Modifier.padding(8.dp))

            KATextFieldType.KATextField(
                value = stringResource(id = R.string.email),
                textStyle = MontserratLabelStyle.Regular,
                onValueChange = {},
                prefix = {
                    Text(
                        text = "TL",
                        style = MontserratLabelStyle.Regular,
                    )
                },
            )

            Spacer(modifier = Modifier.padding(8.dp))

            KATextFieldType.KATextField(
                value = stringResource(id = R.string.email),
                onValueChange = {},
                suffix = {
                    Text(text = "£")
                },
            )

            Spacer(modifier = Modifier.padding(8.dp))

            KATextFieldType.KATextField(
                value = "ibrahim.kurt@ubuntuyouiwe.com",
                onValueChange = {},
                label = {
                    Text(text = "Email")
                },
            )

            Spacer(modifier = Modifier.padding(8.dp))

            KATextFieldType.KATextField(
                value = "",
                onValueChange = {},
                titleLabel = "ahmet",
                label = {
                    Text(text = stringResource(id = R.string.email))
                },
            )

            Spacer(modifier = Modifier.padding(8.dp))

            KATextFieldType.KATextField(
                value = "",
                onValueChange = {},
                height = 98.dp,
                singleLine = false,
                placeholder = {
                    Text(text = stringResource(id = R.string.email))
                },
                helpText = "Karakter: ${"".length}",
                supportingText = {
                    KASupportingText(text = stringResource(R.string.max_character))
                }
            )
        }
    }
}