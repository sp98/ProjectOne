package com.santoshpillai.projectone.ui.common

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.material.datepicker.MaterialDatePicker

@Composable
fun FormLabel(label: String) {
    Text(
        text = label,
        style = MaterialTheme.typography.body2
    )
}

@Composable
fun ScreenTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.h6
    )
}

@Composable
fun AppButton(
    btnText: String,
    onClick: () -> Unit,
    isEnabled: Boolean
) {
    Button(
        onClick = onClick,
        enabled = isEnabled
    ) {
        Text(btnText, style = MaterialTheme.typography.button)
    }
}

@Composable
fun HorizontalRadioButtonMenu(
    options: List<String>,
    selectedOption: String,
    onOptionSelect: (String) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
    ) {
        options.forEachIndexed { i, text ->
            val borderColor = if (selectedOption == text) {
                MaterialTheme.colors.primary.copy(alpha = 0.5f)
            } else {
                MaterialTheme.colors.onSurface.copy(alpha = 0.12f)
            }

            val backgroundColor = if (selectedOption == text) {
                MaterialTheme.colors.primary.copy(alpha = 0.12f)
            } else {
                MaterialTheme.colors.background
            }
            Surface(
                shape = MaterialTheme.shapes.small,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .weight(1f),
                border = BorderStroke(
                    width = 1.dp,
                    color = borderColor
                ),
            ) {
                Row(
                    modifier = Modifier
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = { onOptionSelect(text) },
                            role = Role.RadioButton,
                        )
                        .background(backgroundColor)
                        .padding(vertical = 8.dp, horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    FormLabel(text)
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = null,
                    )
                }
            }
            // don't add spacer after last checkbox
            if (i < options.size - 1) {
                Spacer(Modifier.weight(.1f))
            }
        }
    }
}

@Composable
fun ShowDatePicker() {
    val activity = LocalContext.current as AppCompatActivity
    val picker = MaterialDatePicker.Builder.datePicker()
        .build()
    activity?.let {
        picker.show(it.supportFragmentManager, picker.toString())
        picker.addOnPositiveButtonClickListener {
            Log.i("pcker", "selected")
        }
    }
}


@Composable
fun AppCheckbox(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    val borderColor = if (checked) {
        MaterialTheme.colors.primary.copy(alpha = 0.5f)
    } else {
        MaterialTheme.colors.onSurface.copy(alpha = 0.12f)
    }

    val backgroundColor = if (checked) {
        MaterialTheme.colors.primary.copy(alpha = 0.12f)
    } else {
        MaterialTheme.colors.background
    }

    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier.padding(vertical = 8.dp),
        border = BorderStroke(
            width = 1.dp,
            color = borderColor
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .clickable(
                    onClick = {
                        onCheckedChange(!checked)
                    }
                )
                .padding(vertical = 12.dp, horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FormLabel(label)
            Checkbox(
                checked = checked,
                onCheckedChange = null,
            )
        }
    }

}


@Preview(name = "HorizontalRadioButtonMenu")
@Composable
fun PreviewHorizontalRadioButtonMenu() {
    HorizontalRadioButtonMenu(
        listOf("Male", "Female"),
        "Female"
    ) {}
}
