package com.company.api.validator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EmailValidatorTest {

	@InjectMocks
	private EmailValidator emailValidator;

	@Test
	public void ValidEmailTest() {
		String[] email = new String[] { "harsh@yahoo.com", "harsh-100@yahoo.com", "harsh.100@yahoo.com", "harsh111@harsh.com",
				"harsh-100@harsh.net", "harsh.100@harsh.com.au", "harsh@1.com", "harsh@gmail.com.com", "harsh+100@gmail.com",
				"harsh-100@yahoo-test.com" };
		for (String each : email) {
			boolean valid = emailValidator.isValid(each);
			assertEquals(valid, true);
		}

	}

	@Test
	public void InValidEmailTest() {
		String[] email = new String[] { "harsh", "harsh@.com.my", "harsh123@gmail.a", "harsh123@.com", "harsh123@.com.com",
				".harsh@harsh.com", "harsh()*@gmail.com", "harsh@%*.com", "harsh..2002@gmail.com", "harsh.@gmail.com",
				"harsh@harsh@gmail.com", "harsh@gmail.com.1a" };
		for (String each : email) {
			boolean valid = emailValidator.isValid(each);
			assertEquals(valid, false);
		}
	}

}
