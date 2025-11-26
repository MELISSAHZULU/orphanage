package com.example.orphanage.checklist

import androidx.compose.foundation.layout.*
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.orphanage.viewmodel.ChecklistViewModel

@Composable
fun StudentProfileSection(viewModel: ChecklistViewModel) {
    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = viewModel.studentProfileSchool.value,
            onValueChange = { viewModel.studentProfileSchool.value = it },
            label = { Text("Current School") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = viewModel.studentProfileFavoriteThings.value,
            onValueChange = { viewModel.studentProfileFavoriteThings.value = it },
            label = { Text("Favorite Things") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = viewModel.studentProfileFutureDreams.value,
            onValueChange = { viewModel.studentProfileFutureDreams.value = it },
            label = { Text("Future Dreams") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}