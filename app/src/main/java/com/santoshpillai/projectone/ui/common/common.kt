package com.santoshpillai.projectone.ui.common

import android.app.Activity
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.material.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.material.datepicker.MaterialDatePicker

@Composable
fun FormLabel(label:String){
    Text(
        text = label,
        style = MaterialTheme.typography.body2
    )
}

@Composable
fun ScreenTitle(title:String){
    Text(
        text = title,
        style = MaterialTheme.typography.h6
    )
}

@Composable
fun HorizontalRadioButtonMenu(
    options: List<String>,
    selectedOption: String,
    onOptionSelect: (String) -> Unit
) {
    val borderColor = if (selectedOption.isNotEmpty()){
        MaterialTheme.colors.primary.copy(alpha = 0.5f)
    }else{
        MaterialTheme.colors.onSurface.copy(alpha = 0.12f)
    }

    val backgroundColor = if (selectedOption.isNotEmpty()){
        MaterialTheme.colors.primary.copy(alpha = 0.12f)
    } else {
        MaterialTheme.colors.background
    }
    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(vertical = 8.dp),
        border = BorderStroke(
            width = 1.dp,
            color = borderColor
        ),
    ){
    Row(
        Modifier
            .fillMaxWidth()
            .background(backgroundColor),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        options.forEach { text ->
            Row(
                modifier = Modifier
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelect(text) },
                        role = Role.RadioButton,
                    )
                    .weight(1f)
                    .padding(vertical = 8.dp, horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.body2,
                )
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = null,
                )
            }
        }
        }
    }

}

@Composable
fun ShowDatePicker(){
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
){
    val borderColor = if (checked){
        MaterialTheme.colors.primary.copy(alpha = 0.5f)
    }else{
        MaterialTheme.colors.onSurface.copy(alpha = 0.12f)
    }

    val backgroundColor = if (checked){
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
        ){
            Text(
                text = label,
                style = MaterialTheme.typography.subtitle2
            )
            Checkbox(
                checked = checked,
                onCheckedChange = null,
            )
        }
    }

}



@Preview(name = "HorizontalRadioButtonMenu")
@Composable
fun PreviewHorizontalRadioButtonMenu(){
    HorizontalRadioButtonMenu(
        listOf("Male", "Female"),
        "Female"
    ) {}
}
