package com.aayurtseven.emailorganizer.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aayurtseven.emailorganizer.ui.viewmodel.EmailDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailDetailScreen(
    viewModel: EmailDetailViewModel,
    onBackClick: () -> Unit
) {
    val email = viewModel.email.collectAsState().value
    val summary = viewModel.summary.collectAsState().value
    val suggestedReply = viewModel.suggestedReply.collectAsState().value
    val error = viewModel.error.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(email?.subject ?: "Email") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.toggleFlag() }) {
                        Icon(Icons.Filled.Flag, contentDescription = "Flag")
                    }
                    IconButton(onClick = { 
                        viewModel.deleteEmail()
                        onBackClick()
                    }) {
                        Icon(Icons.Filled.Delete, contentDescription = "Delete")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.background)
        ) {
            if (email != null) {
                // From
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Gönderen: ${email.from}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    Text(
                        text = "Kategori: ${email.category}",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                    )
                    if (email.isSpam) {
                        Text(
                            text = "⚠️ Bu email spam olarak işaretlenmiştir",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }

                // Body
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = email.body,
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    )
                }

                // Summary
                if (summary != null) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "🤖 AI Özeti",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = summary,
                            fontSize = 12.sp,
                            lineHeight = 16.sp
                        )
                    }
                }

                // Suggested Reply
                if (suggestedReply != null) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "💬 Önerilen Cevap",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = suggestedReply,
                            fontSize = 12.sp,
                            lineHeight = 16.sp
                        )
                    }
                }

                // Actions
                Column(modifier = Modifier.padding(16.dp)) {
                    Button(
                        onClick = { viewModel.generateReply() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        Text("Cevap Öner")
                    }
                    Button(
                        onClick = { /* TODO */ },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Cevap Gönder")
                    }
                }

                // Error
                if (error != null) {
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}
