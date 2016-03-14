package at.kfiw.valley3.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class RestfulServiceConfig extends Application
{
	public RestfulServiceConfig()
	{
		System.out.println("test1");
	}
}
