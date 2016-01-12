package at.kfiw.valley3.controllers;

import java.io.IOException;
import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.kfiw.valley3.services.Service;

@ManagedBean
@SessionScoped
public class FileUploadController
{
	private Part file;
	
	private static final Logger logger = LoggerFactory.getLogger(Service.class);

	public byte[] upload()
	{
		
		try
		{
			System.out.println("erste");
			InputStream input = file.getInputStream();
			byte[] bytes = IOUtils.toByteArray(input);
			//System.out.println("ok");
			return bytes;
			
		} catch (IOException e)
		{
			logger.error("Fehler upload()");
		}
		return null;
	}
}
