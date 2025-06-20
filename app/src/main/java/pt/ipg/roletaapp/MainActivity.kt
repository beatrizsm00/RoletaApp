package pt.ipg.roletaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.text.style.TextAlign

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoletaApp()
        }
    }
}

@Composable
fun RoletaApp() {
    var nomeInput by remember { mutableStateOf("") }
    val nomes = remember { mutableStateListOf<String>() }
    var nomeSorteado by remember { mutableStateOf<String?>(null) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Roleta de Nomes",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3F51B5),
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // Campo de texto
            OutlinedTextField(
                value = nomeInput,
                onValueChange = { nomeInput = it },
                label = { Text("Digite um nome") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Botão de adicionar nome
            Button(
                onClick = {
                    if (nomeInput.isNotBlank()) {
                        nomes.add(nomeInput.trim())
                        nomeInput = ""
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Adicionar")
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Nomes adicionados:",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(20.dp))
            // Lista de nomes
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 150.dp)
            ) {
                items(nomes) { nome ->
                    Text(
                        text = "• $nome",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            // Botão de sortear
            Button(
                onClick = {
                    if (nomes.isNotEmpty()) {
                        nomeSorteado = nomes.random()
                    }
                },
                enabled = nomes.isNotEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Sortear", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Resultado
            nomeSorteado?.let {
                Text(
                    text = "Nome sorteado:",
                    fontSize = 18.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = it,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF009688),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
