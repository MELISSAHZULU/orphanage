package com.example.orphanage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orphanage.OrganizationRepository
import com.example.orphanage.model.Organization
import com.example.orphanage.model.OrganizationAddress
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegistrationViewModel : ViewModel() {

    // Section 1: Organization Information
    val organizationName = MutableStateFlow("")
    val organizationType = MutableStateFlow("NGO") // Default value
    val organizationEmail = MutableStateFlow("")
    val organizationPhone = MutableStateFlow("")
    val country = MutableStateFlow("")
    val city = MutableStateFlow("")
    val physicalAddress = MutableStateFlow("")
    val logoUrl = MutableStateFlow<String?>(null)
    val licenseNumber = MutableStateFlow("")

    // Section 2: Administrator (Tenant Owner) Account
    val adminFullName = MutableStateFlow("")
    val adminEmail = MutableStateFlow("")
    val adminPhone = MutableStateFlow("")
    val password = MutableStateFlow("")
    val confirmPassword = MutableStateFlow("")

    // Section 3: Organization Size & Settings
    val numberOfStaff = MutableStateFlow("1-10") // Default value
    val numberOfBeneficiaries = MutableStateFlow("")
    val preferredLanguage = MutableStateFlow("English")
    val preferredTheme = MutableStateFlow("Light")

    // Terms and Conditions
    val agreedToTerms = MutableStateFlow(false)

    // --- Dropdown options ---
    val organizationTypes = listOf("NGO", "Orphanage", "Health Facility", "School", "Company", "Government Dept")
    val staffNumberOptions = listOf("1-10", "11-50", "51-200", "201-1000", "1000+")
    val themeOptions = listOf("Light", "Dark")
    
    // --- Registration Logic ---
    private val orgRepository = OrganizationRepository()
    // TODO: Add UserRepository and AuthRepository

    fun onRegisterClicked() {
        viewModelScope.launch {
            // TODO: Implement the full registration logic
            // 1. Validate all fields
            val newOrg = Organization(
                name = organizationName.value,
                type = organizationType.value,
                email = organizationEmail.value,
                phone = organizationPhone.value,
                address = OrganizationAddress(country.value, city.value, physicalAddress.value),
                licenseNumber = licenseNumber.value,
                numberOfStaff = numberOfStaff.value
            )
            // 2. Create Organization in Firestore
            // 3. Create Admin User in Firebase Auth
            // 4. Create User record in Firestore and link to Organization
        }
    }
}
