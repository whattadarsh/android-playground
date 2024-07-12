package com.whattadarsh.playground.notesapp.view.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.whattadarsh.playground.notesapp.model.Note

@Composable
fun NoteItem(note: Note, onDelete: (Note) -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = note.title, style = MaterialTheme.typography.titleSmall)
            Text(text = note.content, style = MaterialTheme.typography.bodyMedium)
            Button(onClick = { onDelete(note) }) {
                Text(text = "Delete")
            }
        }
    }
}
