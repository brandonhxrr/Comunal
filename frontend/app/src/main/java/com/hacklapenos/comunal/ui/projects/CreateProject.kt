package com.hacklapenos.comunal.ui.projects

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hacklapenos.comunal.data.projects.ProjectViewModel
import com.hacklapenos.comunal.ui.ComunalButton
import com.hacklapenos.comunal.ui.ComunalTextField
import com.hacklapenos.comunal.ui.DateRangePicker
import com.hacklapenos.comunal.ui.DurationInput

@Composable
fun CreateProjectScreen(navController: NavController?, projectViewModel: ProjectViewModel) {

    val name: String by projectViewModel.name.observeAsState(initial = "")
    val description by projectViewModel.description.observeAsState(initial = "")
    val goal: String by projectViewModel.goal.observeAsState(initial = "")
    val startDate: String by projectViewModel.startDate.observeAsState(initial = "")
    val endDate: String by projectViewModel.endDate.observeAsState(initial = "")
    val duration: String by projectViewModel.duration.observeAsState(initial = "")
    val initialAmount: String by projectViewModel.initialAmount.observeAsState("")


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Row {
            IconButton(
                onClick = { navController?.navigateUp() },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "Go back",
                )
            }

            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Crear proyecto",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

        Text(
            text = "Llena los siguientes campos para crear un proyecto",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 16.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        ComunalTextField(value = name, placeholder = "Nombre del proyecto") {
            projectViewModel.onNameChanged(it)
        }

        ComunalTextField(value = goal, placeholder = "Objetivo") {
            projectViewModel.onGoalChanged(it)
        }

        ComunalTextField(value = description, placeholder = "Descripción") {
            projectViewModel.onDescriptionChanged(it)
        }

        DurationInput() {
            projectViewModel.onDurationChanged(it)
        }

        DateRangePicker(
            startDate = startDate,
            endDate = endDate,
            onStartDateChanged = { projectViewModel.onStartDateChanged(it) },
            onEndDateChanged = { projectViewModel.onEndDateChanged(it) })

        ComunalTextField(
            value = initialAmount,
            placeholder = "Presupuesto",
            keyboardType = KeyboardType.Number
        ) {
            projectViewModel.onInitialAmountChanged(it)
        }

        Spacer(modifier = Modifier.height(20.dp))

        ComunalButton(
            text = "Crear comunidad",
            enabled = projectViewModel.enableCreateProject(
                name = name,
                description = description,
                goal = goal,
                startDate = startDate,
                endDate = endDate,
                duration = duration
            ),
        ) {

        }
    }
}

@Composable
@Preview(showBackground = true)
fun CreateProjectScreenPreview() {
    CreateProjectScreen(navController = null, projectViewModel = ProjectViewModel())
}