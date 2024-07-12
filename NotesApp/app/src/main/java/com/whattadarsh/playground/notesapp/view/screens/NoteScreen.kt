package com.whattadarsh.playground.notesapp.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.whattadarsh.playground.notesapp.view.widgets.NoteItem
import com.whattadarsh.playground.notesapp.model.Note

@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onDeleteNote: (Note) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        BasicTextField(
            value = title,
            onValueChange = { title = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            decorationBox = { innerTextField ->
                if (title.isEmpty()) {
                    Text(
                        text = "Title",
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                    )
                }
                innerTextField()
            }
        )
        BasicTextField(
            value = content,
            onValueChange = { content = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            decorationBox = { innerTextField ->
                if (content.isEmpty()) {
                    Text(
                        text = "Content",
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                    )
                }
                innerTextField()
            }
        )
        Button(onClick = {
            val note = Note(
                title = title,
                content = content
            )
            onAddNote(note)
            title = ""
            content = ""
        }) {
            Text(text = "Add Note")
        }
        Spacer(modifier = Modifier.height(16.dp))
        notes.forEach { note ->
            NoteItem(note = note, onDelete = { onDeleteNote(it) })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(notes = listOf(
        Note(
            id = "1",
            title = "Sample Title",
            content = "Sample Content"
        )
    ), onAddNote = {}, onDeleteNote = {})
}
