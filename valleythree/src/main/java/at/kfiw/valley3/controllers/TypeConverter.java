package at.kfiw.valley3.controllers;

import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

public class TypeConverter
{

	public Blob convertByteToBlob(byte[] b) throws SerialException, SQLException
	{
		Blob blob = null; 
	 
		blob = new SerialBlob(b);
		return blob;
	}
}
