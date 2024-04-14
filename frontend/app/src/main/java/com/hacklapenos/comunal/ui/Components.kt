package com.hacklapenos.comunal.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ComunalButton(
    text: String,
    enabled: Boolean,
    onClick: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Button(
        onClick = {
            keyboardController?.hide()
            onClick()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(8.dp)
            .height(50.dp)
            .fillMaxWidth(),
        enabled = enabled
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun ComunalTextField(
    value: String,
    placeholder: String,
    modifier: Modifier? = Modifier,
    keyboardType: KeyboardType? = KeyboardType.Text,
    onTextChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = {
            onTextChanged(it)
        },
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.bodySmall
            )
        },
        textStyle = MaterialTheme.typography.bodySmall,
        modifier = modifier!!
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType!!,
            imeAction = ImeAction.Done
        ),
    )
}

@Composable
fun DurationInput(onDurationChanged: (String) -> Unit) {
    var number by remember { mutableStateOf("") }
    var selectedUnit by remember { mutableStateOf("") }
    val units = listOf("días", "semanas", "meses", "años")
    var expanded by remember { mutableStateOf(false) }

    if (selectedUnit.isEmpty()) {
        selectedUnit = units.first()
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {

        OutlinedTextField(
            value = number,
            onValueChange = { value ->
                number = value.takeIf { it.isNotEmpty() && it.toIntOrNull() != null } ?: number
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            textStyle = MaterialTheme.typography.bodySmall,
            placeholder = { Text("Duración", style = MaterialTheme.typography.bodySmall) },
            modifier = Modifier.width(150.dp)
        )

        Spacer(modifier = Modifier.width(32.dp))

        Row(
            modifier = Modifier
                .clickable { expanded = true }
                .align(Alignment.CenterVertically),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = selectedUnit,
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Rounded.KeyboardArrowDown,
                contentDescription = "Expand",
                modifier = Modifier.size(20.dp)
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(100.dp)
        ) {
            units.forEach { unit ->
                DropdownMenuItem(onClick = {
                    selectedUnit = unit
                    onDurationChanged("$number $unit")
                    expanded = false
                }, text = { Text(text = unit) })
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DurationPreview() {
    DurationInput { }

}

@Composable
fun DateRangePicker(
    startDate: String,
    endDate: String,
    onStartDateChanged: (String) -> Unit,
    onEndDateChanged: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        DatePicker(
            date = startDate,
            onDateChange = { onStartDateChanged(it) },
            label = "Fecha de inicio",
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.weight(0.1f))
        DatePicker(
            date = endDate,
            onDateChange = { onEndDateChanged(it) },
            label = "Fecha de fin",
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun DatePicker(
    date: String,
    onDateChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {

    Log.d("DatePicker", "date: $date")

    Column(modifier = modifier) {
        Text(text = label, style = MaterialTheme.typography.bodyMedium)
        ComunalTextField(value = date, onTextChanged = {
            if (it.length <= 10) {
                onDateChange(it)
            }
        }, placeholder = "DD/MM/YYYY")
    }
}

fun dateToString(date: Date, format: String): String {
    val dateFormat = SimpleDateFormat(format, Locale.getDefault())
    return dateFormat.format(date)
}

fun stringToDate(dateString: String, format: String): Date {
    val dateFormat = SimpleDateFormat(format, Locale.getDefault())
    return dateFormat.parse(dateString)!!
}