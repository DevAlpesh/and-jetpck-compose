package com.devalpesh.jetpack.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.devalpesh.jetpack.R

@Composable
fun StandardTextField(
    text: String = "",
    hint: String = "",
    maxLength: Int = 40,
    isError: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit
) {
    val isPasswordToggleDisplayed by remember {
        mutableStateOf(keyboardType == KeyboardType.Password)
    }
    var isPasswordVisible by remember {
        mutableStateOf(false)
    }

    TextField(
        value = text,
        onValueChange = {
            if (it.length <= maxLength) {
                onValueChange(it)
            }
        },
        placeholder = {
            Text(
                text = hint,
                style = MaterialTheme.typography.body1
            )
        },
        isError = isError,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        singleLine = true,
        visualTransformation = if (!isPasswordVisible && isPasswordToggleDisplayed) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        trailingIcon = {
            if (isPasswordToggleDisplayed) {
                IconButton(onClick = {
                    isPasswordVisible = !isPasswordVisible
                }) {
                    Icon(
                        imageVector = if (isPasswordVisible) {
                            Icons.Filled.VisibilityOff
                        } else {
                            Icons.Filled.Visibility
                        },
                        contentDescription = if (isPasswordVisible) {
                            stringResource(id = R.string.desc_visible_password)
                        } else {
                            stringResource(id = R.string.desc_hidden_password)
                        }
                    )
                }
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}