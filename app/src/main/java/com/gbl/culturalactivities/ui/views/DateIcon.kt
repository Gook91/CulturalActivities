package com.gbl.culturalactivities.ui.views

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gbl.culturalactivities.R
import java.util.Calendar
import java.util.Locale

@Composable
fun DateIcon(
    dateInMillis: Long?,
    modifier: Modifier = Modifier,
    borderColor: Color = Color.Black // ToDO: add color from theme
) {
    val dayString: String
    val monthString: String?
    if (dateInMillis != null) {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = dateInMillis
        dayString = calendar.get(Calendar.DAY_OF_MONTH).toString()
        monthString =
            calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault())
    } else {
        dayString = stringResource(id = R.string.no_date_icon)
        monthString = null
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .size(48.dp) // ToDo: Add size to dimensional resource or to constructor
            .border(
                width = dimensionResource(id = R.dimen.border_width),
                color = borderColor,
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.border_corner))
            )
    ) {
        Text(
            text = dayString, style = MaterialTheme.typography.titleLarge
        )
        monthString?.let {
            Text(text = it, style = MaterialTheme.typography.titleSmall)
        }
    }
}


@Preview
@Composable
fun PreviewBlankDateIcon() {
    DateIcon(dateInMillis = null)
}

@Preview
@Composable
fun PreviewDateIcon() {
    DateIcon(dateInMillis = 1719273600000)
}