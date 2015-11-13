package base;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;

public class Person_Test {

	private static PersonDomainModel testPDM;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		Date dDate = null;
		try {
			dDate = new SimpleDateFormat("yyyy-MM-dd").parse("1994-11-25");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		testPDM = new PersonDomainModel();
		testPDM.setFirstName("Quentin");
		testPDM.setLastName("Dubroff");
		testPDM.setBirthday(dDate);
		testPDM.setCity("Wilmington");
		testPDM.setPostalCode(19803);
		testPDM.setStreet("1610 Shadybrook rd");

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		PersonDomainModel Chad;
		PersonDAL.deletePerson(testPDM.getPersonID());

		Chad = PersonDAL.getPerson(testPDM.getPersonID());
		assertNull("The Person shouldn't have been in the database",Chad);
	}

	@Test
	public void addTest() {
		PersonDomainModel dude;
		dude = PersonDAL.getPerson(testPDM.getPersonID());
		assertNull("The Person shouldn't have been in the database", dude);
		PersonDAL.addPerson(testPDM);

		dude = PersonDAL.getPerson(testPDM.getPersonID());
		System.out.println(testPDM.getPersonID() + " found");
		assertNotNull("The Person should have been added to the database", testPDM);
	}
	@Test
	public void updateTest(){
		PersonDomainModel upPer;
		final String newAddress = "35 Chambers street";
		
		upPer = PersonDAL.getPerson(testPDM.getPersonID());		
		assertNull("The Person shouldn't have been in the database",upPer);		
		PersonDAL.addPerson(testPDM);
		
		testPDM.setStreet(newAddress);
		PersonDAL.updatePerson(testPDM);
		upPer = PersonDAL.getPerson(testPDM.getPersonID());
		
		assertTrue("Name Didn't Change",testPDM.getStreet() == newAddress);
	}
	@Test 
	public void deleteTest(){
		PersonDomainModel guy;
		guy = PersonDAL.getPerson(testPDM.getPersonID());
		
		assertNull("The Person shouldn't have been in the database",guy);
		PersonDAL.addPerson(testPDM);
		guy = PersonDAL.getPerson(testPDM.getPersonID());
		System.out.println(testPDM.getPersonID() + " found");
		assertNotNull("The Person should have been added to the database",guy);
		
		PersonDAL.deletePerson(testPDM.getPersonID());
		guy = PersonDAL.getPerson(testPDM.getPersonID());		
		assertNull("The Person shouldn't have been in the database",guy);	
	}
}
