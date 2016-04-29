package at.kfiw.valley3.validator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.kfiw.valley3.services.Service;

@ManagedBean
@RequestScoped
@FacesValidator(value="fileUploadValidator")
public class FileUploadValidator implements Validator
{
 
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
    {
    	final Logger logger = LoggerFactory.getLogger(FileUploadValidator.class);
    	
    	Part file = (Part) value;
        FacesMessage message=null;
 
        try {
 
            if (file==null || file.getSize()<=0 || file.getContentType().isEmpty() )
                message=new FacesMessage("Bitte w‰hlen Sie eine Datei aus");
            else if (!file.getContentType().toLowerCase().endsWith("jpeg") && !file.getContentType().toLowerCase().endsWith("jpg") && !file.getContentType().toLowerCase().endsWith("gif") && !file.getContentType().toLowerCase().endsWith("png"))
            {
            	System.out.println(file.getContentType());
            	message=new FacesMessage("erlaubte Formate: jpeg, png, gif");
            	logger.info("kein erlaubtes Format");
            }
            else if (file.getSize()>2000000)
            {
                 message=new FacesMessage("Datei ist zu groﬂ. Maximal erlaubte Grˆﬂe: 2 MB");
                 logger.info("Datei zu groﬂ");
            }
 
            if (message!=null && !message.getDetail().isEmpty())
                {
                    throw new ValidatorException(message);
                }
 
        } catch (Exception ex) {
               throw new ValidatorException(new FacesMessage(ex.getMessage()));
        }
 
    }

	
 
}