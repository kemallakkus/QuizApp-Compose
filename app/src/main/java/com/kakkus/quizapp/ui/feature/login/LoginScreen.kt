package com.kakkus.quizapp.ui.feature.login

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kakkus.quizapp.R
import com.kakkus.quizapp.components.KAIconButtonType
import com.kakkus.quizapp.components.KAPreview
import com.kakkus.quizapp.components.KASupportingText
import com.kakkus.quizapp.components.KATextFieldType
import com.kakkus.quizapp.ui.feature.login.navigation.LoginAction
import com.kakkus.quizapp.ui.theme.Black
import com.kakkus.quizapp.ui.theme.GeneralDisableRed
import com.kakkus.quizapp.ui.theme.GeneralRed
import com.kakkus.quizapp.ui.theme.Gray20
import com.kakkus.quizapp.ui.theme.Gray40
import com.kakkus.quizapp.ui.theme.Gray80
import com.kakkus.quizapp.ui.theme.MontserratH5Style
import com.kakkus.quizapp.ui.theme.MontserratH6Style
import com.kakkus.quizapp.ui.theme.MontserratLabelStyle
import com.kakkus.quizapp.ui.theme.MontserratParagraphStyle
import com.kakkus.quizapp.ui.theme.White
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun LoginScreen(
    onEvent: (LoginEvent) -> Unit,
    state: LoginState,
    action: LoginAction,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LoginContent(
            email = state.email,
            password = state.password,
            onEmailChange = { onEvent(LoginEvent.EmailChanged(it)) },
            onPasswordChange = { onEvent(LoginEvent.PasswordChanged(it)) },
            onTrailingIconClick = { onEvent(LoginEvent.PasswordVisibilityChanged) },
            trailingIconVisibility = state.trailingIconVisible,
            onForgotPasswordClick = { action.navigateToForgotPassword() },
            isPasswordVisible = state.isPasswordVisible,
            emailError = state.emailError,
            passwordError = state.passwordError,
        )

        Spacer(modifier = Modifier.height(24.dp))

        LoginButton(
            onLoginClick = { onEvent(LoginEvent.LoginButtonClicked)},
            isTextFieldsFull = state.isTextFieldsFull,
        )

        Spacer(modifier = Modifier.height(24.dp))

        OrLoginWithText()

        Spacer(modifier = Modifier.height(24.dp))

        GoogleLoginButton(onClick = { /* Google giriş işlemi */ })

    }
    BottomLoginText(onSignupClick = { action.navigateToRegister() })


}

@Composable
fun LoginContent(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    isPasswordVisible: Boolean = false,
    onTrailingIconClick: () -> Unit,
    trailingIconVisibility: Boolean = false,
    onForgotPasswordClick: () -> Unit,
    emailError: String?,
    passwordError: String?,
) {

    val visualTransformation = if (isPasswordVisible) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation(mask = '*')
    }

    Text(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        text = stringResource(R.string.welcome_to_quizapp),
        style = MontserratH5Style.Bold.copy(
            color = Color.Black
        ),
        textAlign = TextAlign.Start
    )

    Spacer(modifier = Modifier.height(16.dp))

    KATextFieldType.KATextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        value = email,
        titleLabel = stringResource(R.string.email),
        onValueChange = onEmailChange,
        supportingText = {
            KASupportingText(text = emailError)
        }

    )

    Spacer(modifier = Modifier.height(16.dp))

    KATextFieldType.KATextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        value = password,
        titleLabel = stringResource(R.string.password),
        onValueChange = onPasswordChange,
        visualTransformation = visualTransformation,
        supportingText = {
            KASupportingText(text = passwordError)
        },
        trailingIcon = if (trailingIconVisibility) {
            {
                KAIconButtonType.KAIconButton(
                    onClick = onTrailingIconClick,
                    colors = KAIconButtonType.saIconButtonColor(
                        containerColor = Color.Transparent
                    )
                ) {
                    val icon = if (isPasswordVisible) {
                        ImageVector.vectorResource(id = R.drawable.visibility_icon)
                    } else {
                        ImageVector.vectorResource(id = R.drawable.visibility_off_icon)
                    }
                    Icon(
                        imageVector = icon,
                        contentDescription = if (isPasswordVisible) stringResource(R.string.hide_password) else stringResource(
                            R.string.show_password
                        )
                    )
                }
            }
        } else null
    )

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clickable {
                onForgotPasswordClick()
            },
        text = stringResource(R.string.forgot_password),
        style = MontserratParagraphStyle.Bold.copy(
            color = Color.Black
        ),
        textAlign = TextAlign.End
    )
}

@Composable
fun LoginButton (
    onLoginClick: () -> Unit,
    isTextFieldsFull: Boolean = false,
) {

    // Renk geçişi için animateColorAsState kullanıyoruz
    val backgroundColor by animateColorAsState(
        targetValue = if (isTextFieldsFull) GeneralRed else GeneralDisableRed,
        animationSpec = tween(durationMillis = 600) // Animasyon süresi (600ms)
    )

    val textColor by animateColorAsState(
        targetValue = if (isTextFieldsFull) White else GeneralRed,
        animationSpec = tween(durationMillis = 600) // Animasyon süresi (600ms)
    )

    // Buton boyut geçişi için animateDpAsState kullanıyoruz
    val buttonHeight by animateDpAsState(
        targetValue = if (isTextFieldsFull) 55.dp else 50.dp,
        animationSpec = tween(durationMillis = 500)
    )

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(buttonHeight),
        onClick = onLoginClick,
        enabled = isTextFieldsFull,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor, // Enabled için mor, disabled için gri
            contentColor = White, // Buton enabled iken metin rengi
            disabledContainerColor = GeneralDisableRed, // Disabled durumunda arka plan rengi
            disabledContentColor = Gray40 // Disabled durumda metin rengi
        )
    ) {
        Text(
            text = stringResource(R.string.login),
            style = MontserratH6Style.SemiBold.copy(
                color = textColor
            ),
        )
    }
}

@Composable
fun OrLoginWithText() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        text = stringResource(R.string.or_login_with),
        style = MontserratParagraphStyle.Regular.copy(
            color = Black,
            textDecoration = TextDecoration.Underline
        ),
        textAlign = TextAlign.Center
    )
}

@Composable
fun GoogleLoginButton(onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(50.dp),
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Gray20
        )
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_google),
                    contentDescription = stringResource(R.string.google_logo),
                    tint = Color.Unspecified,
                    modifier = Modifier.size(24.dp)

                )
            }
            Text(
                text = stringResource(R.string.connect_with_google),
                style = MontserratH6Style.SemiBold.copy(
                    color = Black
                )
            )
        }
    }
}

@Composable
fun BottomLoginText(onSignupClick: () -> Unit) {
    val annotatedText = buildAnnotatedString {
        // İlk kısmı (normal metin)
        append(stringResource(R.string.don_t_have_an_account))

        // Tıklanabilir ve stil uygulanacak "Register!" kısmı
        pushStringAnnotation(tag = stringResource(R.string.register), annotation = stringResource(R.string.register))
        withStyle(
            style = SpanStyle(
                textDecoration = TextDecoration.Underline, // Altı çizili
                fontFamily = MontserratLabelStyle.Bold.fontFamily, // Montserrat fontu kullan
            )
        ) {
            append(stringResource(R.string.register_big))
        }
        pop() // Stil ve etiket bitişi
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        ClickableText(
            text = annotatedText,
            style = MontserratLabelStyle.Regular.copy(color = Color.Black), // Genel stil
            onClick = { offset ->
                // Tıklanılan bölgeyi kontrol et
                annotatedText.getStringAnnotations(tag = "register", start = offset, end = offset)
                    .firstOrNull()?.let {
                        onSignupClick() // Tıklama işlemi
                    }
            },
        )
    }
}


@KAPreview
@Composable
fun LoginScreenPreview(
    @PreviewParameter(LoginScreenPreviewProvider::class) state: LoginState,
) {
    LoginScreen(
        onEvent = {},
        state = state,
        action = LoginAction(
            navigateToRegister = {},
            navigateToHome = {},
            navigateToForgotPassword = {}
        ),
    )
}