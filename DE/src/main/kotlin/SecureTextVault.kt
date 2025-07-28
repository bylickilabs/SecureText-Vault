import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.nio.file.Files
import java.nio.file.Paths

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "SecureText Vault") {
        App()
    }
}

@Composable
@Preview
fun App() {
    var password by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    MaterialTheme {
        Column(modifier = Modifier.padding(16.dp).fillMaxSize()) {
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Passwort") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Textdaten") },
                modifier = Modifier.fillMaxHeight(0.7f).fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Button(onClick = {
                    val encrypted = EncryptionUtils.encrypt(text, password)
                    Files.write(Paths.get("encrypted.vault"), encrypted)
                    message = "✅ Verschlüsselt gespeichert."
                }) {
                    Text("Verschlüsseln & Speichern")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    val bytes = Files.readAllBytes(Paths.get("encrypted.vault"))
                    text = EncryptionUtils.decrypt(bytes, password)
                    message = "✅ Entschlüsselt geladen."
                }) {
                    Text("Laden & Entschlüsseln")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(message)
        }
    }
}
