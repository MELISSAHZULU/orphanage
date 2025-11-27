package com.example.orphanage

import com.example.orphanage.model.Organization
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class RegistrationRepository {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = Firebase.firestore

    suspend fun registerOrganizationAndAdmin(
        organization: Organization,
        adminFullName: String,
        adminEmail: String,
        adminPhone: String,
        adminPassword: String
    ): Result<Unit> {
        return try {
            // 1. Create the Admin user in Firebase Authentication
            val authResult = auth.createUserWithEmailAndPassword(adminEmail, adminPassword).await()
            val newUserId = authResult.user?.uid ?: throw IllegalStateException("User creation failed in Firebase Auth.")

            // Use a write batch to ensure the entire operation is atomic
            db.runBatch { batch ->
                // 2. Create the Organization document in Firestore
                val orgCollection = db.collection("organizations")
                val newOrgRef = orgCollection.document() // Auto-generate ID
                batch.set(newOrgRef, organization.copy(id = newOrgRef.id))

                // 3. Create the User document in Firestore
                val userCollection = db.collection("users")
                val newUserDoc = hashMapOf(
                    "uid" to newUserId,
                    "organizationId" to newOrgRef.id,
                    "fullName" to adminFullName,
                    "email" to adminEmail,
                    "phone" to adminPhone,
                    "role" to "Organization Admin" // Automatically set role
                )
                batch.set(userCollection.document(newUserId), newUserDoc)
            }.await()
            
            Result.success(Unit)
        } catch (e: Exception) {
            // TODO: In a real app, you might want to delete the created auth user if firestore fails
            Result.failure(e)
        }
    }
}
