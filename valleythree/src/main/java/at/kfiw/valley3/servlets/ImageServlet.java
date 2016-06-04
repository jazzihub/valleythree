package at.kfiw.valley3.servlets;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import at.kfiw.valley3.entities.Event;

import at.kfiw.valley3.services.Service;

@WebServlet("/image")
public class ImageServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@EJB
	Service service;

	public ImageServlet()
	{
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{

		String number = request.getParameter("id");
		int nr = Integer.parseInt(number);
		Event e = null;

		if (nr != 0)
		{
			// try-catch
			e = service.getEventById(nr);

			if (e.getPoster() != null)
			{
				
				byte[] imageBytes = e.getPoster();

				response.getOutputStream().write(imageBytes);
				response.getOutputStream().close();
			}
		} 

	}
}
