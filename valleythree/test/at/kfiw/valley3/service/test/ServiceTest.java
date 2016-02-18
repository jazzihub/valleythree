package at.kfiw.valley3.service.test;

import static org.junit.Assert.*;

import javax.ejb.EJB;

import org.junit.Test;

import at.kfiw.valley3.services.Service;

public class ServiceTest
{
//	@PersistenceContext(unitName = "valleythree")
//	protected EntityManager entityManager;
	
	@EJB
	Service service;

	@Test
	public void getEventsFromNowTest()
	{
			
		
		fail("Not yet implemented");
	}

}
