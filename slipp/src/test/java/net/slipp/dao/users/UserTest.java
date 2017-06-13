package net.slipp.dao.users;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import net.slipp.domain.users.User;
import net.slipp.web.users.UserController;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.internal.runners.model.EachTestNotifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UserTest {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	private static Validator validator;
	
	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    validator = factory.getValidator();
	}
	
	
	@Test
	public void userIdWhenIsEmpty() {
		User user = new User("", "password", "name", "email@gmail.net");
		
	    Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);		
	    assertThat(constraintViolations.size(), is(2));
	    
	    for (ConstraintViolation<User> constraintViolation : constraintViolations) {
	    	log.debug("violation error message : {}", constraintViolation.getMessage());
			
		}
	}
}