package at.kfiw.valley3.servlets;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.ejb.EJB;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;









import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.kfiw.valley3.controllers.OrganizerController;
import at.kfiw.valley3.entities.Event;
import at.kfiw.valley3.services.Service;

@WebServlet("/imageAddEvent")
public class ImageServletAddEvent extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ImageServletAddEvent.class);

	@EJB
	Service service;

	public ImageServletAddEvent()
	{
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{

				
		Event event = (Event) request.getSession().getAttribute("event");
		

		if (event.getPoster() != null)
		{

			byte[] imageBytes = event.getPoster();

			response.getOutputStream().write(imageBytes);
			response.getOutputStream().close();
			logger.info("ImageServletAddEvent.doGet ok");
			
			
		}
	}

}
