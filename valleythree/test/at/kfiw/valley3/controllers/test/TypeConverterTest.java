package at.kfiw.valley3.controllers.test;

import static org.junit.Assert.*;

import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.junit.Assert;
import org.junit.Test;

import at.kfiw.valley3.controllers.TypeConverter;

public class TypeConverterTest
{

	@Test
	public void test() throws SerialException, SQLException
	{
		byte[] b = {0,1,0,1,1,1,1,0,0,0,0,1,1,0,1,0,0,1,0,0,1};
		
		TypeConverter tc = new TypeConverter();
		Blob blob = tc.convertByteToBlob(b);
				
		Assert.assertNotNull("blob darf nicht null sein", blob);
	}

}
