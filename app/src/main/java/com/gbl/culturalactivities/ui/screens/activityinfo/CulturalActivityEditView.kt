package com.gbl.culturalactivities.ui.screens.activityinfo

import android.icu.text.DateFormat
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.gbl.culturalactivities.R
import com.gbl.culturalactivities.entity.CulturalActivity
import com.gbl.culturalactivities.ui.SingleItemPreviewParameterProvider
import java.util.Calendar

@Composable
fun CulturalActivityEditView(
    culturalActivityUiState: CulturalActivityUiState
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = culturalActivityUiState.nameState,
                onValueChange = {
                    culturalActivityUiState.nameState = it
                },
                placeholder = { Text(text = stringResource(id = R.string.name_title)) },
                modifier = Modifier.weight(1f)
            )

        }
        TextField(
            value = culturalActivityUiState.descriptionState,
            onValueChange = { culturalActivityUiState.descriptionState = it },
            placeholder = { Text(text = stringResource(id = R.string.description_title)) }
        )
        TextField(
            value = culturalActivityUiState.placeState,
            onValueChange = { culturalActivityUiState.placeState = it },
            placeholder = { Text(text = stringResource(id = R.string.place_title)) }
        )
        TextField(
            value = culturalActivityUiState.linkState,
            onValueChange = { culturalActivityUiState.linkState = it },
            placeholder = { Text(text = stringResource(id = R.string.link_title)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri),
            singleLine = true
        )
        CalendarText(
            label = stringResource(id = R.string.ending_date_title),
            dateInMillis = culturalActivityUiState.endingDateState,
            changeDate = { culturalActivityUiState.endingDateState = it }
        )

        CalendarText(
            label = stringResource(id = R.string.date_of_visit_title),
            dateInMillis = culturalActivityUiState.dateOfVisitState,
            changeDate = { culturalActivityUiState.dateOfVisitState = it }
        )
    }
}

@Composable
private fun CalendarText(
    label: String = "",
    dateInMillis: Long? = null,
    changeDate: (Long?) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label)

        val dateInText = if (dateInMillis == null)
            ""
        else {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = dateInMillis
            DateFormat.getDateInstance().format(calendar.time)
        }
        val openDialog = remember { mutableStateOf(false) }
        Text(text = dateInText)
        IconButton(onClick = {
            openDialog.value = true
        }) {
            Icon(
                painter = painterResource(id = R.drawable.open_calendar_icon),
                contentDescription = stringResource(id = R.string.select_date_button)
            )
        }
        if (openDialog.value) {
            CalendarDialog(
                defaultDate = dateInMillis,
                openDialogState = openDialog,
                changeDate = changeDate
            )
        }

        IconButton(onClick = { changeDate(null) }) {
            Icon(
                painter = painterResource(id = R.drawable.delete_icon),
                contentDescription = stringResource(id = R.string.clear_date_button)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarDialog(
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
                Text(text = "ok")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                changeDate(null)
                openDialogState.value = false
            }) {
                Text(text = "очистить")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCulturalActivityEditView(
    @PreviewParameter(SingleItemPreviewParameterProvider::class, limit = 1)
    culturalActivity: CulturalActivity
) {
    CulturalActivityEditView(
        culturalActivityUiState = CulturalActivityUiState(
            culturalActivity
        )
    )
}