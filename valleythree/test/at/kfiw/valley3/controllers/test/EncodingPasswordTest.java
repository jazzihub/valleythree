package at.kfiw.valley3.controllers.test;


import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import at.kfiw.valley3.controllers.EncodingPassword;
import at.kfiw.valley3.services.Service;

public class EncodingPasswordTest
{
	EncodingPassword ep;
	
	@EJB
	Service service;
	
	@Before
	public void setUp()
	{
		ep = new EncodingPassword();
	}

	@Test
	public void encryptTest() throws Exception
	{
		String strToEncrypt = "password";
		String encrypt = ep.encrypt(strToEncrypt);
		
		Assert.assertNotEquals(encrypt, strToEncrypt);
		
	}
	
	@Test
	public void decryptTest() throws Exception
	{
		final String strToDecrypt = "vfkkoy9AwmHG0SAuo/YeLw==";
		final String decrypt = ep.decrypt(strToDecrypt);
		
		Assert.assertNotEquals(decrypt, strToDecrypt);
		
	}
	
	

}
