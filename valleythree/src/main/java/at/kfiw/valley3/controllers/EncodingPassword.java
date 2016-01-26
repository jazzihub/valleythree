package at.kfiw.valley3.controllers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
		// MessageDigest md;
		// String out = "";
		//
		// md = MessageDigest.getInstance("SHA-512");
		//
		// md.update(message.getBytes());
		// byte[] mb = md.digest();
		//
		// for (int i = 0; i < mb.length; i++)
		// {
		// byte temp = mb[i];
		// String s = Integer.toHexString(new Byte(temp));
		// while (s.length() < 2)
		// {
		// s = "0" + s;
		// }
		// s = s.substring(s.length() - 2);
		// out += s;
		// }
		// logger.info("EncodingPassword.encoding() ok");
		// return out;

		try
		{
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			final String encryptedString = Base64.encodeBase64String(cipher
					.doFinal(strToEncrypt.getBytes()));
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


}
