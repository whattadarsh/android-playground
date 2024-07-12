package com.whattadarsh.playground.notesapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.whattadarsh.playground.notesapp.view.screens.NoteScreen
import com.google.firebase.auth.FirebaseAuth
import com.whattadarsh.playground.notesapp.common.StringResources
import com.whattadarsh.playground.notesapp.viewmodel.NoteViewModel
import com.whattadarsh.playground.notesapp.common.theme.NotesAppTheme

class MainActivity : ComponentActivity() {
    private val noteViewModel: NoteViewModel by viewModels()
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        signInAnonymously()

        setContent {
            NotesAppTheme {
                NoteScreen(
                    notes = noteViewModel.notes.collectAsState().value,
                    onAddNote = { noteViewModel.addNote(it) },
                    onDeleteNote = { noteViewModel.deleteNote(it.id) }
                )
            }
        }
    }

    private fun signInAnonymously() {
        auth.signInAnonymously().addOnCompleteListener { task ->
            var authResult = if (task.isSuccessful) {
                noteViewModel.fetchNotes()
                "succeeded"

            } else {
                var msg = ""
                task.addOnFailureListener {
                    msg = it.message.toString()
                }
                "failed. Reason: $msg"
            }
            Log.d(StringResources.LOGGER_TAG, "signInAnonymously: $authResult")
        }
    }
}