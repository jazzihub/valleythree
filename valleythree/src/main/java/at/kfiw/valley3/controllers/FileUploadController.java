package at.kfiw.valley3.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.kfiw.valley3.entities.Event;
import at.kfiw.valley3.services.Service;

@ManagedBean
@RequestScoped
public class FileUploadController
{
	private Part tempPoster;
	InputStream input = null;
	Blob blob = null;
	
	@ManagedProperty(value="#{event}")
	private Event e;
	

	private static final Logger logger = LoggerFactory.getLogger(Service.class);

	
	//TypeConverter converter;
	
	public FileUploadController()
	{
		//converter = new TypeConverter();
	}
	
	public Event getE()
	{
		return e;
	}

	public void setE(Event e)
	{
		this.e = e;
	}
		
	public Part getTempPoster()
	{
		return tempPoster;
	}

	public void setTempPoster(Part tempPoster)
	{
		this.tempPoster = tempPoster;
	}

	public void upload() throws IOException
	{
//		Event e = (Event) FacesContext.getCurrentInstance()
//				.getExternalContext().getSessionMap().get("event");

		
		try
		{
			if (tempPoster != null)
			{
				input = tempPoster.getInputStream();
				byte[] bytes = IOUtils.toByteArray(input);
				e.setPoster(bytes);
//				try
//				{
//					blob = converter.convertByteToBlob(bytes);
//				} catch (SerialException e1)
//				{
//					logger.error("Fehler FileUploadController, SerialException");
//					e1.printStackTrace();
//				} catch (SQLException e1)
//				{
//					logger.error("Fehler FileUploadController, SQLException");
//					e1.printStackTrace();
//				}
				logger.info("FileUploadController.upload() ok");
				//return 0;
			} else
			{
				//return -1;
			}

		} catch (IOException ex)
		{
			logger.error("Fehler: FileUploadController.upload()", ex);
		} finally
		{
			if (input != null)
			{
				input.close();
			}
		}
		//return -1;
	}

	
	
	
}