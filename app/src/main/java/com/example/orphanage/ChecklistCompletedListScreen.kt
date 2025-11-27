package com.example.orphanage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.orphanage.navigation.MainScaffold
import com.example.orphanage.viewmodel.AuthViewModel

@Composable
fun ChecklistCompletedListScreen(navController: NavController, authViewModel: AuthViewModel) {
    MainScaffold(
        navController = navController,
        authViewModel = authViewModel,
        title = "Checklist Completed"
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Orphans with Completed Checklists (Placeholder)")
        }
    }
}
