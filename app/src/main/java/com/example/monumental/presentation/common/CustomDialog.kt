package com.example.monumental.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.monumental.R
import com.example.monumental.ui.theme.MonumentalTheme
import com.example.monumental.utils.Dimens.PageHorizontalPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = { /*TODO*/ },
    title: String,
    content: String,
    confirmText: String,
    onConfirm: () -> Unit,
    dismissText: String? = null,
    onDismiss: (() -> Unit)? = null,
) {
    AlertDialog(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(color = colorResource(id = R.color.white))
            .padding(PageHorizontalPadding)
            .wrapContentWidth()
            .wrapContentHeight(),
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = content,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Medium),
                color = colorResource(id = R.color.eclipse).copy(alpha = 0.5f),
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(30.dp))
            CustomFilledTonalButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onConfirm,
                text = confirmText
            )
            if (dismissText != null) {
                Spacer(modifier = Modifier.height(8.dp))
                CustomFilledTonalButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onDismiss?.invoke() },
                    text = dismissText,
                    containerColor = colorResource(id = R.color.primary_bg_color),
                )
            }
        }
    }
}

@Preview
@Composable
fun CustomDialogPRev() {
    MonumentalTheme {
        CustomDialog(
            title = "CONGRATULATIONS!",
            content = "You have successfully created your account",
            confirmText = "OK", onConfirm = { /*TODO*/ })
    }
}