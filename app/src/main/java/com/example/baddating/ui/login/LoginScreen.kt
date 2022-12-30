package com.example.baddating.ui.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.baddating.R
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoginScreen() {
    val emailValue = remember { mutableStateOf(TextFieldValue()) }
    val passwordValue = remember { mutableStateOf(TextFieldValue()) }
    val isLoggingIn = remember { mutableStateOf(false) }
    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
    ) {
        val (username, password, loginButton, progressBar) = createRefs()
        if (!isLoggingIn.value) {
            TextField(
                value = emailValue.value,
                onValueChange = { emailValue.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(username) {
                        top.linkTo(parent.top, margin = 96.dp)
                    },
                placeholder = { Text(stringResource(id = R.string.prompt_email)) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )
            TextField(
                value = passwordValue.value,
                onValueChange = { passwordValue.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(password) {
                        top.linkTo(username.bottom, margin = 8.dp)
                    },
                placeholder = { Text(stringResource(id = R.string.prompt_password)) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = PasswordVisualTransformation()
            )
            OutlinedButton(
                onClick = {
                    isLoggingIn.value = true
                    // TODO: check for valid inputs first
                },
                enabled = !isLoggingIn.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(loginButton) {
                        top.linkTo(password.bottom, margin = 16.dp)
                    }
                    .padding(bottom = 64.dp)
            ) {
                Text(stringResource(id = R.string.action_sign_in))
            }
        }
        val color = MaterialTheme.colorScheme.primary
        if (isLoggingIn.value) {
            LinearProgressIndicator(
                modifier = Modifier
                    .constrainAs(progressBar) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top, 64.dp)
                        bottom.linkTo(parent.bottom, 64.dp)
                    },
                color = color,
                trackColor = color.copy(alpha = 0.5F)
            )
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}