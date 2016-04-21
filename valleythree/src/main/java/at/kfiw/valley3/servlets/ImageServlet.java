package at.kfiw.valley3.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import at.kfiw.valley3.entities.Event;
import at.kfiw.valley3.services.Service;

@WebServlet("/image")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @EJB
    Service service;
    
	public ImageServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String title = request.getParameter("title");
		Event e = service.getEventByName(title);
		
		if(e.getPoster() != null)
		{
			byte[] imageBytes = e.getPoster();
			response.getOutputStream().write(imageBytes);
			response.getOutputStream().close();
		}
		
	}

}
