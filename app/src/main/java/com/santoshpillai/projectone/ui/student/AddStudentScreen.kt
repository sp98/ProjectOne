package com.santoshpillai.projectone.ui.student

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.santoshpillai.projectone.R
import com.santoshpillai.projectone.ui.NavActions
import com.santoshpillai.projectone.ui.common.TextFieldState


@Composable
fun AddStudentScreen(
    navActions: NavActions,
    addStudentViewModel: AddStudentViewModel

) {
    Scaffold(
        topBar = { AddStudentTopBar(navActions) },
        bottomBar = {},
    ) {
        AddStudentForm(addStudentViewModel)
    }
}

@Composable
fun AddStudentTopBar(navActions: NavActions) {
    TopAppBar(
        title = { Text(stringResource(R.string.add_student_screen_title)) },
        navigationIcon = {
            IconButton(
                onClick = { navActions.toHomeScreen() }
            ) {
                Icon(Icons.Filled.ArrowBack, stringResource(R.string.back))
            }
        }
    )
}


@Composable
fun AddStudentForm(
    addStudentViewModel: AddStudentViewModel
) {
    val firstName = addStudentViewModel.firstName
    val lastName = addStudentViewModel.lastName
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .fillMaxHeight()

    ) {
        Text(
            "Add Student Details Below",
            style = MaterialTheme.typography.body2
        )

        TextField(firstName, stringResource(R.string.first_name_label))
        TextField(lastName, stringResource(R.string.last_name_label))
    }

}

@Composable
fun TextField(
    textFieldState: TextFieldState,
    label: String,
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { it ->
                textFieldState.onFocusChange(it.isFocused)
                if (!it.isFocused) {
                    textFieldState.enableShowErrors()
                }

            },
        value = textFieldState.text,
        onValueChange = { textFieldState.text = it },
        isError = textFieldState.showErrors(),
        label = { Text(label) }
    )

    textFieldState.getError()?.let { it -> TextFieldError(it) }


}

@Composable
fun TextFieldError(
    errorText: String
) {
    Text(text = errorText,
        color = MaterialTheme.colors.error,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
        )
}

@Preview(name = "Add Student Form")
@Composable
fun PreviewAddStudentForm() {
    // AddStudentForm()
}