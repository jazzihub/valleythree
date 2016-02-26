package at.kfiw.valley3.controllers.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import at.kfiw.valley3.controllers.EncodingPassword;

public class EncodingPasswordTest
{
	EncodingPassword ep;
	
	@Before
	public void setUp()
	{
		ep = new EncodingPassword();
	}

	@Test
	public void encryptTest()
	{
		String strToEncrypt = "password";
		String encrypt = ep.encrypt(strToEncrypt);
		
		Assert.assertNotEquals(encrypt, strToEncrypt);
		
	}
	
	@Test
	public void decryptTest()
	{
		final String strToDecrypt = "vfkkoy9AwmHG0SAuo/YeLw==";
		final String decrypt = ep.decrypt(strToDecrypt);
		
		Assert.assertNotEquals(decrypt, strToDecrypt);
		
	}
	
	

}
