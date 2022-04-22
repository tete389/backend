package com.rmuti.guidemap.backend;

import com.rmuti.guidemap.backend.exception.BaseException;
import com.rmuti.guidemap.backend.exception.UserException;

import com.rmuti.guidemap.backend.services.UserProfileService;
import com.rmuti.guidemap.backend.services.UserService;
import com.rmuti.guidemap.backend.table.UserData;

import com.rmuti.guidemap.backend.table.UserProfile;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApplicationTests {

	@Autowired
	private UserService userService;

	@Autowired
	private UserProfileService userProfileService;

	@Order(1)
	@Test
	void testCreate() throws BaseException {
		UserData userData = userService.createUser(
				TestCreateData.email,
				TestCreateData.password

		);

		// check null
		Assertions.assertNotNull(userData);
		Assertions.assertNotNull(userData.getId());

		// check equal
		Assertions.assertEquals(TestCreateData.email, userData.getEmail());
		boolean matchPassword = userService.matchPassword(TestCreateData.password, userData.getPassword());
		Assertions.assertTrue(matchPassword);


	}
	@Order(2)
	@Test
	void testUpdate() throws BaseException {
		Optional<UserData> byEmail = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(byEmail.isPresent());

		UserData userData = byEmail.get();
		UserProfile updateName = userProfileService.updateName(userData.getId(), TestUpDateData.name);

		Assertions.assertNotNull(updateName);
		Assertions.assertEquals(TestUpDateData.name, updateName.getName());

	}

//	@Order(4)
//	@Test
//	void testUpdate() throws BaseException {
//		Optional<UserData> byEmail = userService.findByEmail(TestCreateData.email);
//		Assertions.assertTrue(byEmail.isPresent());
//
//		UserData userData = byEmail.get();
//		UserProfile updateName = userProfileService.updateName(userData.getId(), TestUpDateData.name);
//
//		Assertions.assertNotNull(updateName);
//		Assertions.assertEquals(TestUpDateData.name, updateName.getName());
//
//	}

	@Order(3)
	@Test
	void testDelete() {
		Optional<UserData> byEmail = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(byEmail.isPresent());

		UserData userData = byEmail.get();
		userService.deleteById(userData.getId());

		Optional<UserData> dataDelete = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(dataDelete.isEmpty());
	}

	interface TestCreateData {
		String email = "asd@asd.com";
		String password = "123456";
		//String name = "asd";
	}

	interface TestUpDateData {
		String name = "qwe";
	}

}
