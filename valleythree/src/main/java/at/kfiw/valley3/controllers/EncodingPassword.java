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
	
	//256 Bit
	private static byte[] key = { 0x74, 0x68, 0x69, 0x73, 0x49, 0x73, 0x41,
			0x53, 0x65, 0x63, 0x72, 0x65, 0x74, 0x4b, 0x65, 0x79 };

	public String encrypt(String strToEncrypt) throws Exception
	{
		
		try 
		{
			//verschlüsseln
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			
			 //bytes zu Base64-String konvertieren (dient der Lesbarkeit)
			final String encryptedString = Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes()));
			
			return encryptedString;
			
		} catch (Exception e)
		{
			logger.error("Fehler EncodingPassword.encoding");
			throw new Exception();
		}
		

	}
	
	public static String decrypt(String strToDecrypt) throws Exception
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
          throw new Exception();
        }
        
    }	
}
