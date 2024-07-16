package com.gbl.culturalactivities.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.gbl.culturalactivities.R

@Composable
fun OpenCalendarDialogButton(
    dateInMillis: Long?,
    changeDate: (Long?) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val openDialogState = remember { mutableStateOf(false) }
    if (openDialogState.value) {
        CalendarDialog(
            defaultDate = dateInMillis,
            openDialogState = openDialogState,
            changeDate = changeDate
        )
    }
    Box(
        modifier = modifier
            .clickable { openDialogState.value = true },
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CalendarDialog(
    defaultDate: Long?,
    openDialogState: MutableState<Boolean>,
    changeDate: (Long?) -> Unit
) {
    val datePickerState = rememberDatePickerState()
    datePickerState.selectedDateMillis = defaultDate
    DatePickerDialog(
        onDismissRequest = { openDialogState.value = false },
        confirmButton = {
            TextButton(onClick = {
                changeDate(datePickerState.selectedDateMillis)
                openDialogState.value = false
            }) {
                Text(text = stringResource(id = R.string.calendar_select_button))
            }
        },
        dismissButton = {
            TextButton(onClick = {
                changeDate(null)
                openDialogState.value = false
            }) {
                Text(text = stringResource(id = R.string.calendar_clear_button))
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}