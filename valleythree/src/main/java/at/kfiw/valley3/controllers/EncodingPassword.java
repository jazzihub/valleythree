package at.kfiw.valley3.controllers;



import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.kfiw.valley3.services.Service;

@ManagedBean
@RequestScoped
public class EncodingPassword
{
	private static final Logger logger = LoggerFactory.getLogger(Service.class);
	private static byte[] key = { 0x74, 0x68, 0x69, 0x73, 0x49, 0x73, 0x41,
			0x53, 0x65, 0x63, 0x72, 0x65, 0x74, 0x4b, 0x65, 0x79 };// "thisIsASecretKey";

	public String encrypt(String strToEncrypt)
	{
		
		try 
		{
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			final String encryptedString = Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes()));
			
			return encryptedString;
		} catch (Exception e)
		{
			logger.error("Fehler EncodingPassword.encoding");
		}
		return null;

	}
	
	public static String decrypt(String strToDecrypt)
    {
        try
        {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            final String decryptedString = new String(cipher.doFinal(Base64.decodeBase64(strToDecrypt)));
            return decryptedString;
        }
        catch (Exception e)
        {
          logger.error("Fehler: EncodingPassword.decrypt");

        }
        return null;
    }
	
	
//	public String encrypt1(String strToEncrypt)
//	{
//	       
//	      // Das Passwort bzw der Schluesseltext
//	      String keyStr = strToEncrypt;
//	      // byte-Array erzeugen
//	      byte[] key = (keyStr).getBytes("UTF-8");
//	      // aus dem Array einen Hash-Wert erzeugen mit MD5 oder SHA
//	      MessageDigest sha = MessageDigest.getInstance("MD5");
//	      key = sha.digest(key);
//	      // nur die ersten 128 bit nutzen
//	      key = Arrays.copyOf(key, 16); 
//	      // der fertige Schluessel
//	      SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
//	       
//	 
//	      // der zu verschl. Text
//	      String text = strToEncrypt;
//	 
//	      // Verschluesseln
//	      Cipher cipher = Cipher.getInstance("AES");
//	      cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
//	      byte[] encrypted = cipher.doFinal(text.getBytes());
//	 
//	      // bytes zu Base64-String konvertieren (dient der Lesbarkeit)
//	      Base64Encoder myEncoder = new Base64Encoder();
//	      String geheim = myEncoder.encode(encrypted);
//	 
//	      // Ergebnis
//	      System.out.println(geheim);
//	       
//	      // BASE64 String zu Byte-Array konvertieren
//	      BASE64Decoder myDecoder2 = new BASE64Decoder();
//	      byte[] crypted2 = myDecoder2.decodeBuffer(geheim);
//	 
//	      // Entschluesseln
//	      Cipher cipher2 = Cipher.getInstance("AES");
//	      cipher2.init(Cipher.DECRYPT_MODE, secretKeySpec);
//	      byte[] cipherData2 = cipher2.doFinal(crypted2);
//	      String erg = new String(cipherData2);
//	 
//	      // Klartext
//	      System.out.println(erg);
//	 
//	   }


}
