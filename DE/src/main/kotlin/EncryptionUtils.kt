import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

object EncryptionUtils {
    private const val ALGO = "AES"
    private const val TRANSFORMATION = "AES/GCM/NoPadding"
    private const val KEY_LENGTH = 256
    private const val ITERATIONS = 65536
    private const val SALT_LENGTH = 16
    private const val IV_LENGTH = 12

    fun encrypt(data: String, password: String): ByteArray {
        val salt = ByteArray(SALT_LENGTH)
        val iv = ByteArray(IV_LENGTH)
        SecureRandom().nextBytes(salt)
        SecureRandom().nextBytes(iv)

        val key = generateKey(password, salt)
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, key, GCMParameterSpec(128, iv))

        val encrypted = cipher.doFinal(data.toByteArray(Charsets.UTF_8))
        return salt + iv + encrypted
    }

    fun decrypt(encryptedData: ByteArray, password: String): String {
        val salt = encryptedData.copyOfRange(0, SALT_LENGTH)
        val iv = encryptedData.copyOfRange(SALT_LENGTH, SALT_LENGTH + IV_LENGTH)
        val encrypted = encryptedData.copyOfRange(SALT_LENGTH + IV_LENGTH, encryptedData.size)

        val key = generateKey(password, salt)
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.DECRYPT_MODE, key, GCMParameterSpec(128, iv))

        val decrypted = cipher.doFinal(encrypted)
        return String(decrypted, Charsets.UTF_8)
    }

    private fun generateKey(password: String, salt: ByteArray): SecretKeySpec {
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val spec = PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH)
        val secret = factory.generateSecret(spec).encoded
        return SecretKeySpec(secret, ALGO)
    }
}
