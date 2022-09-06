import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class AES {

  public static byte[] encriptar(String msg, String chave) throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException {
    Key key = new SecretKeySpec(chave.getBytes(StandardCharsets.UTF_8), "AES");
    Cipher encriptador = Cipher.getInstance("AES");
    encriptador.init(Cipher.ENCRYPT_MODE, key);
    return encriptador.doFinal(msg.getBytes());
  }

  public static String descriptar(byte[] msg, String chave) throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException {
    Key key = new SecretKeySpec(chave.getBytes(StandardCharsets.UTF_8), "AES");
    Cipher descriptador = Cipher.getInstance("AES");
    descriptador.init(Cipher.DECRYPT_MODE, key);
    byte[] mensagemDescripted = descriptador.doFinal(msg);
    return new String(mensagemDescripted);
  }

  public static void main(String[] args) {
    String chave_128 = "bolabolabolabola";
    String chave_256 = chave_128.repeat(2);
    try {
      long tempo = System.currentTimeMillis();
      String texto = "RSA é um algoritmo que deve o seu nome a três professores do MIT: Ronald Rivest,Adi Shamir e Leonard Adleman";
      byte[] encriptado = AES.encriptar(texto, chave_256);
      String descriptado = AES.descriptar(encriptado, chave_256);
      float tempo_final = System.currentTimeMillis() - tempo;
      System.out.println("Tempo : " + tempo_final + " ms");
      System.out.println("Encriptado : " + encriptado);
      System.out.println("Texto descriptado: " + descriptado);
    }
    catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }

}
