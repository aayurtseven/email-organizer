package com.aayurtseven.emailorganizer.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aayurtseven.emailorganizer.data.model.Email

@Composable
fun EmailCard(
    email: Email,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(12.dp)
    ) {
        Text(
            text = email.subject,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            maxLines = 2
        )
        
        Text(
            text = email.from,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            modifier = Modifier.padding(top = 4.dp)
        )
        
        email.summary?.let {
            Text(
                text = it,
                fontSize = 11.sp,
                maxLines = 2,
                modifier = Modifier.padding(top = 4.dp),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
    }
}
