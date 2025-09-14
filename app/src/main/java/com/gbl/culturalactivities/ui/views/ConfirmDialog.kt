package com.gbl.culturalactivities.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.gbl.culturalactivities.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmDialog(
    question: String,
    onConfirm: () -> Unit,
    confirmDialogState: MutableState<Boolean>,
) {
    BasicAlertDialog(
        onDismissRequest = { confirmDialogState.value = false }
    ) {
        Card {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.screen_padding))
            ) {
                Text(text = question)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { confirmDialogState.value = false }) {
                        Text(text = stringResource(id = R.string.no))
                    }
                    TextButton(onClick = {
                        onConfirm()
                        confirmDialogState.value = false
                    }) {
                        Text(text = stringResource(id = R.string.yes))
                    }
                }
            }
        }
    }
}