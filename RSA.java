import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import java.util.Base64;

public class RSA {

    private PrivateKey privateKey;
    private PublicKey publicKey;

    public RSA() {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(512);
            KeyPair pair = generator.generateKeyPair();
            privateKey = pair.getPrivate();
            publicKey = pair.getPublic();
        } catch (Exception ignored) {
        }
    }

    public String encrypt(String message) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        byte[] encryptedBytes = cipher.doFinal(message.getBytes());
        return encode(encryptedBytes);
    }
    private String encode(byte[] data){
        return Base64.getEncoder().encodeToString(data);
    }

    public String decrypt(String encryptedMessage) throws Exception{
        byte[] encryptedBytes = decode(encryptedMessage);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE,privateKey);
        byte[] decryptedMessage = cipher.doFinal(encryptedBytes);
        return new String(decryptedMessage, StandardCharsets.UTF_8);
    }
    private byte[] decode(String data){
        return Base64.getDecoder().decode(data);
    }

    public static void main(String[] args) {
        RSA rsa = new RSA();
        String mensagem = "RSA é um algoritmo que deve o seu nome a três professores do MIT: Ronald Rivest,Adi Shamir e Leonard Adleman";
        try {
            long tempo = System.currentTimeMillis();
            String encryptedMessage = rsa.encrypt(mensagem);
            String decryptedMessage = rsa.decrypt(encryptedMessage);
            float tempo_final = System.currentTimeMillis() - tempo;
            System.out.println("Tempo de execucao: " + tempo_final + " ms");
            System.out.println("Encriptado: "+encryptedMessage);
            System.out.println("Descriptado: "+decryptedMessage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}