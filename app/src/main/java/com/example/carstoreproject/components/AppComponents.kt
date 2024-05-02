package com.example.carstoreproject.components

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
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
                .height(70.dp)
                .width(150.dp)
        )
    }
}

@Composable
fun CustomizedTextField(
    icon: ImageVector,
    @StringRes labelId: Int,
    keyboardType: KeyboardType,
    imeAction: ImeAction
) {
    var textValue by rememberSaveable { mutableStateOf("")}
    OutlinedTextField(
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = ""
            )
        },
        value = textValue,
        onValueChange = { textValue = it},
        label = { Text(stringResource(labelId)) },
        keyboardOptions = KeyboardOptions (
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        singleLine = true,
        maxLines = 1,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color(0xff2596BE),
            focusedBorderColor = Color(0xff2596BE),
            focusedLeadingIconColor = Color(0xff2596BE),
            focusedLabelColor = Color(0xff2596BE)
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun PasswordTextField(
    icon: ImageVector,
    @StringRes labelId: Int,
    keyboardType: KeyboardType,
    imeAction: ImeAction
) {
    var password by rememberSaveable { mutableStateOf("")}
    var isVisible by rememberSaveable { mutableStateOf(false) }
    OutlinedTextField(
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = ""
            )
        },
        value = password,
        onValueChange = { password = it},
        label = { Text(stringResource(labelId)) },
        visualTransformation = if(isVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        keyboardOptions = KeyboardOptions (
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        singleLine = true,
        maxLines = 1,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color(0xff2596BE),
            focusedBorderColor = Color(0xff2596BE),
            focusedLeadingIconColor = Color(0xff2596BE),
            focusedLabelColor = Color(0xff2596BE)
        ),
        trailingIcon = {
            val iconImage = if(isVisible) {
                Icons.Outlined.Visibility
            } else {
                Icons.Outlined.VisibilityOff
            }

            var description = if(isVisible) {
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
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun CheckboxComponent(
    @StringRes textId: Int,
    onTextSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically,

    ) {
        val isChecked by rememberSaveable { mutableStateOf(false)}
        Checkbox(
            checked = isChecked,
            onCheckedChange = { isChecked != isChecked }
        )
        ClickableTextComponent(
            onTextSelected = onTextSelected
        )
    }
}

@Composable
fun ClickableTextComponent(
    onTextSelected: (String) -> Unit
) {
    val initialText = "I have read and agree to the "
    val privacyPolicyText = "Privacy Policy "
    val andText = "and "
    val termsOfUseText = "Term of Use"
    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Color(0xff2596BE), textDecoration = TextDecoration.Underline)) {
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }
        append(andText)
        withStyle(style = SpanStyle(color = Color(0xff2596BE), textDecoration = TextDecoration.Underline)) {
            pushStringAnnotation(tag = termsOfUseText, annotation = termsOfUseText)
            append(termsOfUseText)
        }
    }
    ClickableText(text = annotatedString, onClick = { offset ->
        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.let { span ->
                Log.d("Clickable text component: ","${span.item}")
                if(span.item == termsOfUseText || span.item == privacyPolicyText) {
                    onTextSelected(span.item)
                }
        }
    })
}

@Composable
fun ButtonComponent(
    @StringRes textId: Int
) {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color(0xff2596BE))
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
            color = Color.Gray,
            thickness = 1.dp
        )
        Text(
            text = stringResource(R.string.or),
            fontSize = 18.sp,
            color = Color.Gray,
            modifier = Modifier.padding(dimensionResource(R.dimen.medium_padding))
        )
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = Color.Gray,
            thickness = 1.dp
        )
    }
}

@Composable
fun LoginSignUpTextComponent(
    @StringRes initialTextId: Int,
    @StringRes clickableTextId: Int,
    onTextSelected: (String) -> Unit
) {
    val initialText = stringResource(initialTextId)
    val loginText = stringResource(clickableTextId)
    val annotatedString = buildAnnotatedString {
        append(initialText + " ")
        withStyle(style = SpanStyle(color = Color(0xff2596BE), textDecoration = TextDecoration.Underline)) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }
    ClickableText(
        modifier = Modifier.fillMaxWidth(),
        style = MaterialTheme.typography.displayMedium,
        text = annotatedString, onClick = { offset ->
        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.let { span ->
                Log.d("Clickable text component: ","${span.item}")
                if(span.item == loginText) {
                    onTextSelected(span.item)
                }
            }
    })
}

@Composable
fun UnderlinedText(
    @StringRes textId: Int,
    textColor: Color = Color.Black
) {
    Text(
        text = stringResource(textId),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp)
            .padding(vertical = dimensionResource(R.dimen.small_padding)),
        style = MaterialTheme.typography.displayMedium,
        textAlign = TextAlign.End,
        textDecoration = TextDecoration.Underline,
        color = textColor
    )
}