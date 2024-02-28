package com.example.monumental.presentation.common

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.monumental.R
import com.example.monumental.ui.theme.MonumentalTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomBottomSheetLayout(
    modifier: Modifier = Modifier,
    state: SheetState = rememberModalBottomSheetState(),
    onDismissRequest: () -> Unit,
    title: String,
    leftButtonText: String? = null,
    rightButtonText: String? = null,
    onLeftButtonClick: (() -> Unit)? = null,
    onRightButtonClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    ModalBottomSheet(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        containerColor = colorResource(id = R.color.white),
        sheetState = state,
        dragHandle = {},
        shape = MaterialTheme.shapes.medium,
        onDismissRequest = onDismissRequest,
    ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (leftButtonText != null) {
                    TextButton(onClick = {
                        if (onLeftButtonClick == null)
                            onDismissRequest()
                        else
                            onLeftButtonClick()
                    }) {
                        Text(
                            leftButtonText ?: "Cancel",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Medium,
                            ),
                            color = colorResource(id = R.color.primary_color),
                        )
                    }
                }

                Text(
                    title,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 8.dp)
                        .padding(
                            start = if (leftButtonText != null) 0.dp else 56.dp,
                            end = if (rightButtonText != null) 0.dp else 56.dp
                        ),
                )

                if (rightButtonText != null) {
                    TextButton(
                        onClick = {
                            onRightButtonClick?.invoke()
                        }
                    ) {
                        Text(
                            rightButtonText,
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Medium,
                            ),
                            color = colorResource(id = R.color.primary_color),
                        )
                    }
                }

            }

            CustomDivider()

            content()
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun BottomSheetLayoutNoLeft() {
    MonumentalTheme {
        CustomBottomSheetLayout(
            onDismissRequest = {},
            title = "Title",
            rightButtonText = "Right",
        ) {
            Text("Content")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun BottomSheetLayoutNoRight() {
    MonumentalTheme {
        CustomBottomSheetLayout(
            onDismissRequest = {},
            title = "Title",
            leftButtonText = "Left",
        ) {
            Text("Content")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun BottomSheetLayoutNoActions() {
    MonumentalTheme {
        CustomBottomSheetLayout(
            onDismissRequest = {},
            title = "Title",
        ) {
            Text("Content")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun BottomSheetLayoutPrev() {
    MonumentalTheme {
        CustomBottomSheetLayout(
            onDismissRequest = {},
            title = "Title",
            leftButtonText = "Left",
            rightButtonText = "Right",
        ) {
            Text("Content")
        }
    }
}