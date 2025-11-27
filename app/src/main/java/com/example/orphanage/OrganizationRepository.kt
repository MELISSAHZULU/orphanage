package com.example.orphanage

import com.example.orphanage.model.Organization
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class OrganizationRepository {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val orgCollection = firestore.collection("organizations")

    fun getAllOrganizations(): Flow<List<Organization>> = flow {
        try {
            val snapshot = orgCollection.get().await()
            val organizations = snapshot.toObjects(Organization::class.java)
            emit(organizations)
        } catch (e: Exception) {
            // On error, emit an empty list
            emit(emptyList())
        }
    }

    suspend fun addOrganization(organization: Organization): Result<Unit> {
        return try {
            // We can use the organization's name as a document ID if we assume they are unique,
            // or let Firestore auto-generate one. Auto-generation is safer.
            orgCollection.add(organization).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
     // TODO: Add a function to associate a user with an organization
}
