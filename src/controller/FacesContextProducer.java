package controller;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;


public class FacesContextProducer {
	@Produces
	public FacesContext getFacesContext(InjectionPoint ip){
		return FacesContext.getCurrentInstance();
	}
}
