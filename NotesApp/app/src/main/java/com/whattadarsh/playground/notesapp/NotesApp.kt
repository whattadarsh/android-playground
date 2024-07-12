package com.whattadarsh.playground.notesapp

import android.app.Application
import com.google.firebase.FirebaseApp

class NotesApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}
