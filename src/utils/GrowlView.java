package utils;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
 
@ManagedBean
public class GrowlView {
	
    
   private String message;
   private String title;

   public String getMessage() {
       return message;
   }

   public void setMessage(String message) {
       this.message = message;
   }
   
   public String getTitle() {
       return title;
   }

   public void setTitle(String title) {
       this.title = title;
   }
    
    public void saveMessage() {  
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(title,  message) );
    }
   
}