package at.kfiw.valley3.service.test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;

public class ServiceTest
{
	@PersistenceContext(unitName = "valleythree")
	protected EntityManager entityManager;

	@Test
	public void test()
	{
			
		
		fail("Not yet implemented");
	}

}
