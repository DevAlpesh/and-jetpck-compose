package com.devalpesh.jetpack.presentation.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devalpesh.jetpack.R
import com.devalpesh.jetpack.presentation.components.StandardTextField
import com.devalpesh.jetpack.presentation.ui.theme.SpaceLarge
import com.devalpesh.jetpack.presentation.ui.theme.SpaceMedium

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = SpaceLarge,
                top = SpaceLarge,
                end = SpaceLarge,
                bottom = 50.dp
            )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        ) {
            Text(
                text = stringResource(id = R.string.txt_register),
                style = MaterialTheme.typography.h1
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = viewModel.emailText.value,
                onValueChange = {
                    viewModel.setEmailText(it)
                },
                hint = stringResource(id = R.string.txt_email),
                keyboardType = KeyboardType.Email,
                error = viewModel.emailError.value
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = viewModel.usernameText.value,
                onValueChange = {
                    viewModel.setUsernameText(it)
                },
                hint = stringResource(id = R.string.txt_username),
                keyboardType = KeyboardType.Email,
                error = viewModel.userNameError.value
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = viewModel.passwordText.value,
                onValueChange = {
                    viewModel.setPasswordText(it)
                },
                hint = stringResource(id = R.string.text_password_hint),
                keyboardType = KeyboardType.Password,
                showPasswordToggle = viewModel.showPassword.value,
                onPasswordToggleClick = {
                    viewModel.setShowPassword(it)
                },
                error = viewModel.passwordError.value
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            Button(
                onClick = { },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(
                    text = stringResource(id = R.string.txt_register),
                    color = MaterialTheme.colors.onPrimary
                )
            }
        }
        Text(
            text = buildAnnotatedString {
                append(stringResource(id = R.string.txt_already_have_an_account))
                append(" ")
                val signUpText = stringResource(id = R.string.txt_sign_in)
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colors.primary
                    )
                ) {
                    append(signUpText)
                }
            },
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .clickable {
                    navController.popBackStack()
                },
        )
    }
}