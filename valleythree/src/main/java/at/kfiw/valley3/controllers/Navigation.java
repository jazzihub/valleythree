package at.kfiw.valley3.controllers;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Navigation implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6108226058547115977L;

	public String showData()
	{
		return "showData.xhtml?faces-redirect=true";
	}
	
	public String registry()
	{
		return "registry.xhtml?faces-redirect=true";
	}
	
	public String login()
	{
		return "login";
	}
	
	public String index()
	{
		return "index";
	}
	
	public String organizerArea()
	{
		return "organizerArea";
	}
}