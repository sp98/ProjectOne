package com.santoshpillai.projectone.ui.student

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.santoshpillai.projectone.R
import com.santoshpillai.projectone.ui.NavActions
import com.santoshpillai.projectone.ui.common.*


@Composable
fun AddStudentScreen(
    navActions: NavActions,
    addStudentViewModel: AddStudentViewModel

) {
    val showSaveButton = addStudentViewModel.showAddButton()
    Scaffold(
        topBar = {
            AddStudentTopBar(
                navActions,
                addStudentViewModel,
                showSaveButton,
            )
        },
        bottomBar = {},
    ) {
        AddStudentForm(addStudentViewModel)
    }
}

@Composable
fun AddStudentTopBar(
    navActions: NavActions,
    viewModel: AddStudentViewModel,
    showSaveButton: Boolean
) {
    TopAppBar(
        title = { ScreenTitle(stringResource(R.string.add_student_screen_title)) },
        navigationIcon = {
            IconButton(
                onClick = {
                    navActions.toHomeScreen()
                    viewModel.reset()
                }
            ) {
                Icon(Icons.Filled.ArrowBack, stringResource(R.string.back))
            }
        },
        actions = {
            AddStudentTopBarActions(
                onAdd = {
                    navActions.toHomeScreen()
                    viewModel.reset()
                },
                showSaveButton = showSaveButton
            )
        }
    )
}

@Composable
fun AddStudentTopBarActions(
    onAdd: () -> Unit,
    showSaveButton: Boolean
) {
    AppButton(
        btnText = "OK",
        onClick = onAdd,
        isEnabled = showSaveButton
    )
}


@Composable
fun AddStudentForm(
    addStudentViewModel: AddStudentViewModel
) {
    val firstName = addStudentViewModel.firstName
    val lastName = addStudentViewModel.lastName
    val mobileNumber = addStudentViewModel.mobileNumber
    val gender = addStudentViewModel.gender
    val licenseType = addStudentViewModel.licenseType

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .fillMaxHeight()

    ) {
        TextField(firstName, stringResource(R.string.first_name_label))
        TextField(lastName, stringResource(R.string.last_name_label))
        TextField(mobileNumber, stringResource(R.string.mobile_number_label))

        Spacer(modifier = Modifier.height(8.dp))
        FormHelpText(text = stringResource(R.string.select_gender_label))
        GenderRadioButton(
            options = addStudentViewModel.availableGenders,
            selectedOption = gender,
        ) { addStudentViewModel.onGenderChange(it) }

        Spacer(modifier = Modifier.height(8.dp))
        FormHelpText(text = stringResource(R.string.select_license_type))
        LicenseTypeRadioButton(
            options = addStudentViewModel.availableLicenseTypes,
            selectedOption = licenseType
        ) { addStudentViewModel.onLicenseTypeChange(it) }

        // TODO: Add date picker to set DOB
    }

}

@Composable
fun FormHelpText(text: String) {
    Text(
        text,
        style = MaterialTheme.typography.overline
    )
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
        label = { FormLabel(label) }
    )

    textFieldState.getError()?.let { it -> TextFieldError(it) }
    Spacer(modifier = Modifier.height(5.dp))

}

@Composable
fun TextFieldError(
    errorText: String
) {
    Text(
        text = errorText,
        color = MaterialTheme.colors.error,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        style = MaterialTheme.typography.caption
    )
}

@Composable
fun LicenseTypeRadioButton(
    options: List<String>,
    selectedOption: String,
    onOptionSelect: (String) -> Unit
) {
    HorizontalRadioButtonMenu(
        options = options,
        selectedOption = selectedOption,
        onOptionSelect = onOptionSelect
    )
}

@Composable
fun GenderRadioButton(
    options: List<String>,
    selectedOption: String,
    onOptionSelect: (String) -> Unit
) {
    HorizontalRadioButtonMenu(
        options = options,
        selectedOption = selectedOption,
        onOptionSelect = onOptionSelect
    )
}

@Preview(name = "Add Student Form")
@Composable
fun PreviewAddStudentForm() {
//    ProjectOneTheme {
//        AddStudentForm{}
//    }
}