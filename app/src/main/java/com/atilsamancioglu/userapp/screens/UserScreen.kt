package com.atilsamancioglu.userapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.atilsamancioglu.userapp.model.User

@Composable
fun UserList(userList: List<User>) {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.primaryContainer)
    ) {
        items(userList) {
            UserRow(user = it)
        }
    }

}


@Composable
fun UserRow(user: User) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .border(BorderStroke(2.dp, Color.Black)) // Adding a border here

        .background(color = MaterialTheme.colorScheme.primaryContainer)
        .padding(10.dp)
    ) {

            Column {
                Text(text= (user.name),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )

                Text(text= ("Email: " + user.email),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Normal
                )

                Text(text= (user.website),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Normal
                )

                Text(text= ("Phone: " + user.phone),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Normal
                )

            }


    }
}