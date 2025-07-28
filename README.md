# SecureText Vault 🔐

|<img width="1280" height="640" alt="STV" src="https://github.com/user-attachments/assets/37d003c9-c819-498a-a653-1e5d472ac28e" />|
|---|

**SecureText Vault** is a secure, Kotlin-only desktop application for encrypting and storing sensitive text data. Built with Jetpack Compose for Desktop and fully written in Kotlin, it requires no Java code and no internet access.

## ✅ Features

- 100% pure Kotlin code (Jetpack Compose Desktop)
- AES-256-GCM encryption
- Save and load encrypted `.vault` files
- Clean and modern UI
- Fully offline application
- No external libraries required

## 📦 How to Build and Run

Make sure you have:
- JDK 17+ installed
- Kotlin and Gradle configured

```bash
./gradlew run
```

> You can package the app as a `.exe` using `jpackage` or create an installer using tools like InnoSetup or NSIS.

## 🔐 Security Note

All encryption is handled locally using AES-256-GCM. The password is never stored and is only used transiently during encryption and decryption.

## 📁 Files

- `SecureTextVault.kt` – Main UI and logic
- `EncryptionUtils.kt` – Pure Kotlin encryption utility
- `build.gradle.kts` – Gradle build file
- `assets/` – Placeholder for future icons or assets

## 📄 License

MIT – [LICENSE](LICENSE)
