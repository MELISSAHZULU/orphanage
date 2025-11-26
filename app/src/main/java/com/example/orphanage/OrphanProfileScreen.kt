package com.example.orphanage

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.orphanage.model.Orphan

// Mock data - in a real app, this would come from a ViewModel
private val mockOrphans = mapOf(
    "newOrphan123" to Orphan(documentId = "newOrphan123", childHistoryName = "John Doe", status = "Pending"),
    "verifiedOrphan456" to Orphan(documentId = "verifiedOrphan456", childHistoryName = "Jane Smith", status = "Active")
)

@Composable
fun OrphanProfileScreen(navController: NavController, orphanId: String?) {
    val orphan = orphanId?.let { mockOrphans[it] } ?: Orphan()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Orphan Profile", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(32.dp))

        ProfileDetail(label = "Name", value = orphan.childHistoryName)
        ProfileDetail(label = "Status", value = orphan.status)

        // TODO: Display all other orphan details from the intake form
    }
}

@Composable
fun ProfileDetail(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(text = label, style = MaterialTheme.typography.labelSmall)
        Text(text = value, style = MaterialTheme.typography.bodyLarge)
    }
}
