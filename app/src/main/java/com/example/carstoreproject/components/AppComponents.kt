package com.example.carstoreproject.components

import android.text.TextUtils
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carstoreproject.R
import com.example.carstoreproject.animation.bounceClick

@Composable
fun LogoImage(
    @DrawableRes imageId: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(imageId),
            contentDescription = "",
            modifier = Modifier
                .height(100.dp)
                .width(250.dp)
        )
    }
}

@Composable
fun CustomizedTextField(
    icon: ImageVector,
    @StringRes labelId: Int,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    onTextSelected: (String) -> Unit,
    @StringRes errorMessage: Int,
    isError: Boolean = false,
    isPassword: Boolean = false,
    showError: Boolean
) {
    var textValue by remember { mutableStateOf("")}
    var isVisible by remember { mutableStateOf(true) }

    Column {
        OutlinedTextField(
            leadingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = null
                )
            },
            value = textValue,
            onValueChange = {
                textValue = it
                onTextSelected(it)
            },
            label = { Text(stringResource(labelId)) },
            keyboardOptions = KeyboardOptions (
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            singleLine = true,
            maxLines = 1,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
                focusedLabelColor = MaterialTheme.colorScheme.primary
            ),
            visualTransformation = if(isPassword) {
                if (isVisible) {
                    PasswordVisualTransformation()
                } else {
                    VisualTransformation.None
                }
            } else {
                VisualTransformation.None
            },
            trailingIcon = {
                if(isPassword) {
                    val iconImage = if(isVisible) {
                        Icons.Outlined.Visibility
                    } else {
                        Icons.Outlined.VisibilityOff
                    }

                    val description = if(isVisible) {
                        stringResource(R.string.hide_password)
                    } else {
                        stringResource(R.string.show_password)
                    }

                    IconButton(onClick = { isVisible = !isVisible }) {
                        Icon(
                            imageVector = iconImage,
                            contentDescription = description
                        )
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        if(showError && !TextUtils.isEmpty(stringResource(errorMessage)) && !isError) {
            ShowErrorText(textId = errorMessage)
        }
    }
}

@Composable
fun ShowErrorText(
    @StringRes textId: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(textId),
        style = MaterialTheme.typography.displaySmall,
        color = Color.Red,
        modifier = modifier
    )
}

@Composable
fun CheckboxComponent(
    onTextSelected: (String) -> Unit,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically,

    ) {
        var isChecked by rememberSaveable { mutableStateOf(false)}
        Checkbox(
            checked = isChecked,
            onCheckedChange = {
                isChecked = it
                onCheckedChange.invoke(it)
            }
        )
        ClickableTextComponent(
            onTextSelected = onTextSelected
        )
    }
}

@Composable
fun ClickableTextComponent(
    onTextSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val initialText = "I have read and agree to the "
    val privacyPolicyText = "Privacy Policy "
    val andText = "and "
    val termsOfUseText = "Term of Use"
    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary, textDecoration = TextDecoration.Underline)) {
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }
        append(andText)
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary, textDecoration = TextDecoration.Underline)) {
            pushStringAnnotation(tag = termsOfUseText, annotation = termsOfUseText)
            append(termsOfUseText)
        }
    }
    ClickableText(text = annotatedString, onClick = { offset ->
        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.let { span ->
                Log.d("Clickable text component: ", span.item)
                if(span.item == termsOfUseText || span.item == privacyPolicyText) {
                    onTextSelected(span.item)
                }
        }
    },
        style = TextStyle(
            color = MaterialTheme.colorScheme.onSurface
        )
    )
}

@Composable
fun ButtonComponent(
    @StringRes textId: Int,
    onButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = {
            onButtonClicked()
        },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .bounceClick(),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
    ) {
        Text(
            text = stringResource(textId)
        )
    }
}

@Composable
fun TextDivider() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = MaterialTheme.colorScheme.outline,
            thickness = 1.dp
        )
        Text(
            text = stringResource(R.string.or),
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.padding(dimensionResource(R.dimen.medium_padding))
        )
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = MaterialTheme.colorScheme.outline,
            thickness = 1.dp
        )
    }
}

@Composable
fun LoginSignUpTextComponent(
    @StringRes initialTextId: Int,
    @StringRes clickableTextId: Int,
    onTextSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val initialText = stringResource(initialTextId)
    val loginText = stringResource(clickableTextId)
    val annotatedString = buildAnnotatedString {
        append("$initialText ")
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary, textDecoration = TextDecoration.Underline)) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }
    ClickableText(
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            color = MaterialTheme.colorScheme.onSurface,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        ),
        text = annotatedString, onClick = { offset ->
        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.let { span ->
                Log.d("Clickable text component: ", span.item)
                if(span.item == loginText) {
                    onTextSelected(span.item)
                }
            }
    })
}

@Composable
fun CustomizedTextButton(
    @StringRes textId: Int,
    onButtonClicked: () -> Unit
) {
    TextButton(onClick = onButtonClicked) {
        Text(
            text = stringResource(textId),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 40.dp)
                .padding(vertical = dimensionResource(R.dimen.small_padding)),
            style = MaterialTheme.typography.displayMedium,
            textAlign = TextAlign.End,
            textDecoration = TextDecoration.Underline,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit
) {
    var query by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = null)
        },
        value = query,
        onValueChange = {
            query = it
        },
        label = { Text(text = stringResource(R.string.search))},
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()
                onSearch(query)
            }
        ),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
            focusedLabelColor = MaterialTheme.colorScheme.primary
        ),
        singleLine = true,
        modifier = modifier
    )
}

@Composable
fun DataTextField(
    icon: ImageVector,
    labelId: Int,
    data: String,
    onValueChange: (String) -> Unit,
    isReadOnly: Boolean,
    modifier: Modifier = Modifier
) {
    TextField(
        leadingIcon = {
            Icon(imageVector = icon, contentDescription = null)
        },
        label = { Text(text = stringResource(labelId)) },
        value = data,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.inverseOnSurface,
            focusedContainerColor = MaterialTheme.colorScheme.inverseOnSurface,
            unfocusedIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        singleLine = true,
        readOnly = isReadOnly,
        modifier = modifier
    )
}

@Composable
fun SocialMediaAuthButton(
    containerColor: Color,
    contentColor: Color,
    iconId: Int,
    textId: Int
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        modifier = Modifier,
        shape = RoundedCornerShape(dimensionResource(R.dimen.small_padding)),
        onClick = {  }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(iconId),
                contentDescription = null,
                modifier = Modifier.size(15.dp)
            )
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.small_padding)))
            Text(text = stringResource(textId))
        }
    }
}