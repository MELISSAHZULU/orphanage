package com.example.orphanage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orphanage.DashboardCounts
import com.example.orphanage.DashboardRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {
    private val dashboardRepository = DashboardRepository()

    // Expose a flow of dashboard counts that the UI can collect.
    // This will automatically update when the underlying data changes.
    val dashboardCounts: StateFlow<DashboardCounts> = dashboardRepository.getDashboardCounts()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DashboardCounts() // Start with default/loading values
        )

    // Function to be called from the UI to verify an orphan
    fun verifyOrphan(orphanId: String) {
        viewModelScope.launch {
            dashboardRepository.updateOrphanStatus(orphanId, "Active")
            // The dashboardCounts flow will automatically emit the new counts.
        }
    }
    
    fun declineOrphan(orphanId: String) {
        viewModelScope.launch {
            dashboardRepository.updateOrphanStatus(orphanId, "Declined")
            // The dashboardCounts flow will automatically emit the new counts.
        }
    }
}
