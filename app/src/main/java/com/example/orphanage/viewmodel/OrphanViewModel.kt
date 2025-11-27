package com.example.orphanage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orphanage.OrphanRepository
import com.example.orphanage.model.Orphan
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OrphanViewModel : ViewModel() {
    private val repository = OrphanRepository()

    private val _checklistCompletedOrphans = MutableStateFlow<List<Orphan>>(emptyList())
    val checklistCompletedOrphans: StateFlow<List<Orphan>> = _checklistCompletedOrphans.asStateFlow()

    private val _admittedOrphans = MutableStateFlow<List<Orphan>>(emptyList())
    val admittedOrphans: StateFlow<List<Orphan>> = _admittedOrphans.asStateFlow()

    private val _declinedOrphans = MutableStateFlow<List<Orphan>>(emptyList())
    val declinedOrphans: StateFlow<List<Orphan>> = _declinedOrphans.asStateFlow()

    fun fetchOrphansByStatus(status: String) {
        viewModelScope.launch {
            repository.getOrphansByStatus(status).collect {
                when (status) {
                    "ChecklistComplete" -> _checklistCompletedOrphans.value = it
                    "Admitted" -> _admittedOrphans.value = it
                    "Declined" -> _declinedOrphans.value = it
                }
            }
        }
    }

    fun enrollOrphan(orphanId: String) {
        viewModelScope.launch {
            repository.enrollOrphan(orphanId).onSuccess {
                // After enrolling, refresh the relevant list if needed
                fetchOrphansByStatus("ChecklistComplete")
            }
        }
    }
    
    // Note: The logic for admitting/declining is in the DashboardViewModel
    // To keep concerns separated. Verification is a Dashboard/Admin task.
}
