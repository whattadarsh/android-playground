package com.whattadarsh.playground.notesapp.repo

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.whattadarsh.playground.notesapp.common.StringResources
import com.whattadarsh.playground.notesapp.model.Note
import kotlinx.coroutines.tasks.await

class NoteRepository {
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private fun getUserId(): String? {
        return auth.currentUser?.uid
    }

    suspend fun getNotes(): List<Note> {
        val userId = getUserId() ?: return emptyList()
        return try {
            db.collection("notes")
                .get()
                .await()
                .map { document ->
                    document.toObject(Note::class.java).copy(id = document.id)
                }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun addNote(note: Note) {
        val userId = getUserId() ?: return
        try {
            db.collection("notes")
                .add(note)
                .await()
        } catch (e: Exception) {
            // Handle exception
            Log.d(StringResources.LOGGER_TAG, "addNote Failed. Reason: ${e.message}")
        }
    }

    suspend fun deleteNote(noteId: String) {
        val userId = getUserId() ?: return
        try {
            db.collection("notes")
                .document(noteId)
                .delete()
                .await()
        } catch (e: Exception) {
            // Handle exception
            Log.d(StringResources.LOGGER_TAG, "deleteNote Failed. Reason: ${e.message}")
        }
    }
}
