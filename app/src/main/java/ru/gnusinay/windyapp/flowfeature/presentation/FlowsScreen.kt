package ru.gnusinay.windyapp.flowfeature.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.gnusinay.windyapp.R
import ru.gnusinay.windyapp.ui.theme.WindyAppTheme

@Composable
fun FlowsScreen() {
    val viewModel = viewModel { FlowsScreenViewModel() }
    val scrollState = rememberScrollState()

    LaunchedEffect(scrollState.maxValue) {
        scrollState.scrollTo(scrollState.maxValue)
    }

    Column(
        modifier = Modifier.fillMaxWidth().padding(12.dp),
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewModel.state.flowsCount.toString(),
            onValueChange = { text -> viewModel.setFlowCount(text) },
            label = {
                Text(stringResource(id = R.string.input_flow_count_label))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
        Row(
            modifier = Modifier.align(Alignment.End).padding(vertical = 10.dp),
        ) {
            Button(
                modifier = Modifier.padding(end = 10.dp),
                onClick = { viewModel.runCalculation(viewModel.state.flowsCount) },
            ) {
                Text(stringResource(id = R.string.run_calculate_button))
            }
            Button(
                onClick = { viewModel.stopCalculation() },
            ) {
                Text(stringResource(id = R.string.stop_calculate_button))
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .border(
                    width = 1.dp,
                    color = colorResource(
                        id = R.color.purple_200,
                    ),
                ),
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .verticalScroll(scrollState),
                text = viewModel.state.calculationResult,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FlowsScreenPreview() {
    WindyAppTheme {
        FlowsScreen()
    }
}
