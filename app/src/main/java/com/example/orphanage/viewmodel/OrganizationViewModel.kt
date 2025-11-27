package com.example.orphanage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orphanage.OrganizationRepository
import com.example.orphanage.model.Organization
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OrganizationViewModel : ViewModel() {
    private val repository = OrganizationRepository()

    private val _organizations = MutableStateFlow<List<Organization>>(emptyList())
    val organizations: StateFlow<List<Organization>> = _organizations.asStateFlow()

    init {
        fetchAllOrganizations()
    }

    private fun fetchAllOrganizations() {
        viewModelScope.launch {
            repository.getAllOrganizations().collect { organizationList ->
                _organizations.value = organizationList
            }
        }
    }

    fun addOrganization(name: String, location: String) {
        viewModelScope.launch {
            val newOrganization = Organization(name = name, location = location)
            repository.addOrganization(newOrganization).onSuccess {
                // After adding, refresh the list to include the new organization
                fetchAllOrganizations()
            }
            // TODO: Handle failure case
        }
    }
}
