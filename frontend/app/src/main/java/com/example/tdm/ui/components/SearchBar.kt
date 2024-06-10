package com.example.tdm.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tdm.data.models.Parking
import androidx.compose.runtime.*

@Composable
fun SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onSearch: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = query,
            onValueChange = onQueryChanged,
            modifier = Modifier.weight(1f),
            placeholder = {
                Text(text = "Search for parkings...")
            }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = onSearch) {
            Text(text = "Search")
        }
    }
}

@Composable
fun FilterBar(
    selectedCommune: String,
    onCommuneChanged: (String) -> Unit,
    parkings: List<Parking>?
) {
    val communes = parkings?.map { it.commune }?.distinct()?.sorted() ?: emptyList()

    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Filter by Commune: ", style = MaterialTheme.typography.bodyLarge)

        Box {
            OutlinedButton(onClick = { expanded = true }) {
                Text(selectedCommune.ifEmpty { "Select Commune" })
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("All") },
                    onClick = {
                        onCommuneChanged("")
                        expanded = false
                    }
                )
                communes.forEach { commune ->
                    DropdownMenuItem(
                        text = { Text(commune) },
                        onClick = {
                            onCommuneChanged(commune)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}
