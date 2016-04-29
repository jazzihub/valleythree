package at.kfiw.valley3.controllers;

import java.io.IOException;
import java.io.InputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

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

	private static final Logger logger = LoggerFactory.getLogger(Service.class);
	private FacesContext fc;
	
	public FileUploadController()
	{
		fc  = FacesContext.getCurrentInstance();
	}

	public void upload() throws IOException
	{
		Event event = (Event) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("event");
		try
		{
			if (tempPoster != null)
			{
				
				input = tempPoster.getInputStream();
				byte[] bytes = IOUtils.toByteArray(input);
				event.setPoster(bytes);
				
				logger.info("FileUploadController.upload() ok");
				
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
	}
	

	public Part getTempPoster()
	{
		return tempPoster;
	}

	public void setTempPoster(Part tempPoster)
	{
		this.tempPoster = tempPoster;
	}
	
	
}
