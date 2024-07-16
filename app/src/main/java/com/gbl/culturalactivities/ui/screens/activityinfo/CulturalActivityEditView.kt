package com.gbl.culturalactivities.ui.screens.activityinfo

import android.icu.text.DateFormat
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.gbl.culturalactivities.R
import com.gbl.culturalactivities.entity.CulturalActivity
import com.gbl.culturalactivities.ui.SingleItemPreviewParameterProvider
import com.gbl.culturalactivities.ui.views.DateIcon
import com.gbl.culturalactivities.ui.views.OpenCalendarDialogButton
import java.util.Calendar
import java.util.Locale

@Composable
fun CulturalActivityEditView(
    culturalActivityUiState: CulturalActivityUiState,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {

        val fieldModifier = Modifier
            .padding(bottom = dimensionResource(id = R.dimen.view_padding))
            .fillMaxWidth()

        TitleWithDate(
            culturalActivityUiState = culturalActivityUiState,
            modifier = fieldModifier
        )
        DescriptionField(
            culturalActivityUiState = culturalActivityUiState,
            modifier = fieldModifier
        )
        LinkField(
            culturalActivityUiState = culturalActivityUiState,
            modifier = fieldModifier
        )
        PlaceField(
            culturalActivityUiState = culturalActivityUiState,
            modifier = fieldModifier
        )
        EndingDate(culturalActivityUiState = culturalActivityUiState, modifier = fieldModifier)
    }
}

@Composable
private fun TitleWithDate(
    culturalActivityUiState: CulturalActivityUiState,
    modifier: Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        OutlinedTextField(
            value = culturalActivityUiState.nameState,
            onValueChange = {
                culturalActivityUiState.nameState = it
            },
            placeholder = { Text(text = stringResource(id = R.string.name_title)) },
            modifier = Modifier.weight(1f),
            singleLine = true,
            textStyle = MaterialTheme.typography.titleLarge
        )

        OpenCalendarDialogButton(
            dateInMillis = culturalActivityUiState.dateOfVisitState,
            changeDate = { culturalActivityUiState.dateOfVisitState = it },
            modifier = Modifier.padding(all = dimensionResource(id = R.dimen.view_padding))
        ) {
            DateIcon(dateInMillis = culturalActivityUiState.dateOfVisitState)
        }
    }
}

@Composable
private fun DescriptionField(
    culturalActivityUiState: CulturalActivityUiState,
    modifier: Modifier
) {
    OutlinedTextField(
        value = culturalActivityUiState.descriptionState,
        onValueChange = { culturalActivityUiState.descriptionState = it },
        label = { Text(text = stringResource(id = R.string.description_title)) },
        modifier = modifier,
        minLines = 7
    )
}

@Composable
private fun LinkField(
    culturalActivityUiState: CulturalActivityUiState,
    modifier: Modifier
) {
    OutlinedTextField(
        value = culturalActivityUiState.linkState,
        onValueChange = { culturalActivityUiState.linkState = it },
        modifier = modifier,
        label = { Text(text = stringResource(id = R.string.link_title)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri),
        leadingIcon = {
            Icon(
                painterResource(id = R.drawable.link_icon),
                contentDescription = ""
            )
        },
        singleLine = true
    )
}

@Composable
fun PlaceField(culturalActivityUiState: CulturalActivityUiState, modifier: Modifier) {
    OutlinedTextField(
        value = culturalActivityUiState.placeState,
        onValueChange = { culturalActivityUiState.placeState = it },
        modifier = modifier,
        label = { Text(text = stringResource(id = R.string.place_title)) },
        leadingIcon = {
            Icon(
                painterResource(id = R.drawable.place_icon),
                contentDescription = ""
            )
        },
        singleLine = true
    )
}

@Composable
private fun EndingDate(
    culturalActivityUiState: CulturalActivityUiState,
    modifier: Modifier
) {
    OpenCalendarDialogButton(
        dateInMillis = culturalActivityUiState.endingDateState,
        changeDate = { culturalActivityUiState.endingDateState = it },
        modifier = modifier
    ) {
        val dateInText: String? = culturalActivityUiState.endingDateState?.let { dateInMillis ->
            val calendar = Calendar.getInstance().apply { timeInMillis = dateInMillis }
            val dateFormatter = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault())
            dateFormatter.format(calendar.time)
        }

        OutlinedTextField(
            value = dateInText?.let {
                stringResource(id = R.string.before_ending_date, it)
            } ?: "",
            onValueChange = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(id = R.dimen.view_padding)),
            enabled = false,
            label = {
                Text(text = stringResource(id = R.string.ending_date_title))
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.calendar_icon),
                    contentDescription = stringResource(id = R.string.ending_date_title)
                )
            },
            singleLine = true,
            // This is created for disable disabled style, because onClick being processed in the parent.
            // User might be confused if this field looked like disabled
            colors = OutlinedTextFieldDefaults.colors(
                disabledTextColor = OutlinedTextFieldDefaults.colors().unfocusedTextColor,
                disabledBorderColor = OutlinedTextFieldDefaults.colors().unfocusedIndicatorColor,
                disabledLeadingIconColor = OutlinedTextFieldDefaults.colors().unfocusedLeadingIconColor,
                disabledLabelColor = OutlinedTextFieldDefaults.colors().unfocusedLabelColor,
            )
        )
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

@Preview(showBackground = true)
@Composable
fun PreviewBlankCulturalActivityEditView() {
    CulturalActivityEditView(
        culturalActivityUiState = CulturalActivityUiState()
    )
}