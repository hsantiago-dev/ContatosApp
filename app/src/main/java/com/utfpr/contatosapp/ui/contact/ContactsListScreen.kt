package com.utfpr.contatosapp.ui.contact

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.utfpr.contatosapp.R
import com.utfpr.contatosapp.data.Contact
import com.utfpr.contatosapp.ui.theme.ContatosAppTheme

@Composable
fun ContactsListScreen(modifier: Modifier = Modifier) {
    var isLoading = true
    var isError = false
    var contacts = listOf<Contact>()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { AppBar() }
    ) { paddingValues ->
        val defaultModifier: Modifier = Modifier.padding(paddingValues)
        if (isLoading) {
            LoadingState(modifier = defaultModifier)
        } else if (isError) {
            ErrorState(modifier = defaultModifier)
        } else if (contacts.isEmpty()) {
            EmptyListState(modifier = defaultModifier)
        } else {
            List(
                modifier = defaultModifier,
                contacts = contacts
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(modifier: Modifier = Modifier) {
    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        title = {
            Text("Contatos")
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AppBarPreview() {
    ContatosAppTheme {
        AppBar()
    }
}

@Composable
fun LoadingState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier.size(60.dp)
        )
        Spacer(Modifier.size(8.dp))
        Text(
            text = "Carregando...",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = true, heightDp = 400)
@Composable
fun LoadingStatePreview() {
    ContatosAppTheme {
        LoadingState()
    }
}

@Composable
fun ErrorState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.CloudOff,
            contentDescription = "Error Icon",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(60.dp)
        )
        val textPadding = PaddingValues(
            top = 8.dp,
            start = 8.dp,
            end = 8.dp

        )
        Text(
            modifier = Modifier.padding(textPadding),
            text = "Ocorreu um erro ao carregar os dados",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            modifier = Modifier.padding(textPadding),
            text = "Aguarde um momento e tente novamente",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary
        )
        ElevatedButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Tentar novamente")
        }
    }
}

@Preview(showBackground = true, heightDp = 400)
@Composable
fun ErrorStatePreview() {
    ContatosAppTheme {
        ErrorState()
    }
}

@Composable
fun EmptyListState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(all = 16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.no_data),
            contentDescription = "Sem imagem"
        )
        val textPadding = PaddingValues(
            top = 16.dp,
        )
        Text(
            modifier = Modifier.padding(textPadding),
            text = "Nada por aqui...",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            modifier = Modifier.padding(textPadding),
            text = "Você ainda não adicionou nenhum contato." +
                "\n Adicione o primeiro utilizando o botão \"Novo contato\"",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )
        ElevatedButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Novo contato")
        }
    }
}

@Preview(showBackground = true, heightDp = 500)
@Composable
fun EmptyListStatePreview() {
    ContatosAppTheme {
        EmptyListState()
    }
}

@Composable
fun List(
    modifier: Modifier = Modifier,
    contacts: List<Contact> = emptyList()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
    ) {
        contacts.forEach {
            var isFavorite = it.isFavorite

            ListItem(
                headlineContent = {
                    Text(it.fullName)
                },
                supportingContent = {
                    Text(it.phoneNumber)
                },
                trailingContent = {
                    IconButton(
                        onClick = {
                            isFavorite = !isFavorite
                        }
                    ) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = if (isFavorite) {
                                Color.Red
                            } else {
                                LocalContentColor.current
                            }
                        )
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun List() {
    ContatosAppTheme {
        List(
            contacts = listOf(
                Contact(firstName = "Henrick", lastName = "Souza", phoneNumber = "(11) 99999-9999"),
                Contact(firstName = "Ana", lastName = "Silva", phoneNumber = "(11) 99999-9999"),
                Contact(firstName = "João", lastName = "Souza", phoneNumber = "(11) 99999-9999"),
                Contact(firstName = "Maria", lastName = "Silva", phoneNumber = "(11) 99999-9999"),
                Contact(firstName = "Pedro", lastName = "Souza", phoneNumber = "(11) 99999-9999"),
            )
        )
    }
}