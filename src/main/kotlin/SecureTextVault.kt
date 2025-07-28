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
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Secure Text") },
                modifier = Modifier.fillMaxHeight(0.7f).fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Button(onClick = {
                    val encrypted = EncryptionUtils.encrypt(text, password)
                    Files.write(Paths.get("encrypted.vault"), encrypted)
                    message = "✅ Encrypted and saved."
                }) {
                    Text("Encrypt & Save")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    val bytes = Files.readAllBytes(Paths.get("encrypted.vault"))
                    text = EncryptionUtils.decrypt(bytes, password)
                    message = "✅ Decrypted and loaded."
                }) {
                    Text("Load & Decrypt")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(message)
        }
    }
}
