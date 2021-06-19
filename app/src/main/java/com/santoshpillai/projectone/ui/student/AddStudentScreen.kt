package com.santoshpillai.projectone.ui.student

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.santoshpillai.projectone.R
import com.santoshpillai.projectone.ui.NavActions
import com.santoshpillai.projectone.ui.common.*


@ExperimentalComposeUiApi
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
        btnText = stringResource(R.string.ok),
        onClick = onAdd,
        isEnabled = showSaveButton
    )
}


@ExperimentalComposeUiApi
@Composable
fun AddStudentForm(
    addStudentViewModel: AddStudentViewModel
) {
    val firstName = addStudentViewModel.firstName
    val lastName = addStudentViewModel.lastName
    val mobileNumber = addStudentViewModel.mobileNumber
    val gender = addStudentViewModel.gender
    val licenseType = addStudentViewModel.licenseType
    val paymentStatus = addStudentViewModel.paymentStatus
    val paidAmount = addStudentViewModel.paidAmount

    Column(
        modifier = Modifier
            .padding(22.dp)
            .fillMaxWidth()
            .fillMaxHeight()

    ) {
        TextField(
            textFieldState = firstName,
            label = stringResource(R.string.first_name_label),
            icon = Icons.Filled.Person
        )
        TextField(
            lastName,
            stringResource(R.string.last_name_label),
            Icons.Filled.Person
        )
        TextField(
            mobileNumber,
            stringResource(R.string.mobile_number_label),
            Icons.Filled.Phone)

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

        Spacer(modifier = Modifier.height(8.dp))
        FormHelpText(text = stringResource(R.string.Select_payment_status))
        LicenseTypeRadioButton(
            options = addStudentViewModel.paymentStatusTypes,
            selectedOption = paymentStatus
        ) { addStudentViewModel.onPaymentStatusChange(it) }

        if (paymentStatus.equals("PARTIAL", true)){
            TextField(
                paidAmount,
                stringResource(R.string.amount_paid_label),
            )
        }


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

@ExperimentalComposeUiApi
@Composable
fun TextField(
    textFieldState: TextFieldState,
    label: String,
    icon: ImageVector? = null
) {
    val keyboardController = LocalSoftwareKeyboardController.current
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
        label = { FormLabel(label) },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            }
        ),
        leadingIcon = {
            if (icon != null) {
                Icon(icon, "textFieldIcon")
            }
        }
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