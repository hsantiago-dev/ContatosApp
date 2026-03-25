package com.utfpr.contatosapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.utfpr.contatosapp.ui.contact.ContactsListScreen
import com.utfpr.contatosapp.ui.theme.ContatosAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContatosAppTheme {
                ContactsListScreen()
            }
        }
    }
}