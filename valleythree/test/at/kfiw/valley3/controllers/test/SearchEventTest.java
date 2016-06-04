package at.kfiw.valley3.controllers.test;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import at.kfiw.valley3.controllers.SearchEvent;
import at.kfiw.valley3.entities.Event;
import at.kfiw.valley3.services.Service;

public class SearchEventTest
{
	@EJB
	Service service;
	
	
	SearchEvent se;

	@Before
	public void setUp()
	{
		se = new SearchEvent();
		//sdf = new SimpleDateFormat("yyyy-MM-dd");
		
//		se.setDate(new Date(2016-06-07));
//		se.setPlace("TestOrt");
	}
	
	@Test
	public void searchWithName() throws Exception
	{
		se.setName("TestName");
		se.setDate(null);
		se.setPlace("");
		
		se.search();
		int size = se.getEvents().size();
		Assert.assertEquals(1, size);
	}
	
	
	
	

}
