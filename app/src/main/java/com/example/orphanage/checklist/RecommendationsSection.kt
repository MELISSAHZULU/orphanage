package com.example.orphanage.checklist

import androidx.compose.foundation.layout.*
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.orphanage.viewmodel.ChecklistViewModel

@Composable
fun RecommendationsSection(viewModel: ChecklistViewModel) {
    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = viewModel.recommendationsProgram.value,
            onValueChange = { viewModel.recommendationsProgram.value = it },
            label = { Text("Recommended Program") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = viewModel.recommendationsAdmissionReasons.value,
            onValueChange = { viewModel.recommendationsAdmissionReasons.value = it },
            label = { Text("Reasons for Admission") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}