package com.example.orphanage

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

data class DashboardCounts(
    val totalOrphans: Int = 0,
    val pendingVerification: Int = 0,
    val verifiedAndAdmitted: Int = 0
)

class DashboardRepository {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val orphansCollection = firestore.collection("orphans")

    fun getDashboardCounts(): Flow<DashboardCounts> = flow {
        // This flow will emit data whenever the underlying data changes in Firestore.
        // However, for simplicity here, we'll just fetch it once.
        // For real-time updates, you would use snapshot listeners.
        
        try {
            val totalQuery = orphansCollection.get().await()
            val pendingQuery = orphansCollection.whereEqualTo("status", "Pending").get().await()
            val verifiedQuery = orphansCollection.whereEqualTo("status", "Active").get().await()

            emit(DashboardCounts(
                totalOrphans = totalQuery.size(),
                pendingVerification = pendingQuery.size(),
                verifiedAndAdmitted = verifiedQuery.size()
            ))
        } catch (e: Exception) {
            // In case of an error, emit default counts
            emit(DashboardCounts())
        }
    }
    
    suspend fun updateOrphanStatus(orphanId: String, newStatus: String): Result<Unit> {
        return try {
            orphansCollection.document(orphanId).update("status", newStatus).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
