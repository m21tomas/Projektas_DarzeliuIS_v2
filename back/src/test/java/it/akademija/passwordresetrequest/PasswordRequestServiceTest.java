package it.akademija.passwordresetrequest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import it.akademija.user.User;
import it.akademija.user.UserDTO;
import it.akademija.user.UserService;
import it.akademija.user.passwordresetrequests.UserPasswordResetRequestsService;

@SpringBootTest
public class PasswordRequestServiceTest {

	@Autowired
	private UserPasswordResetRequestsService passwordRequestServiceTest;
	
	@Autowired
	private UserService userService;
	
	@Test
	//@WithMockUser(username="test@test.lt", roles = { "USER" })
	public void userPasswordRequestTest() {
		
		UserDTO testUser = new UserDTO("Testasas", "Testauskasas", "user6@test.lt", "user6@test.lt", "testPassword");

		userService.createUser(testUser);
		
		User user = userService.findByUsername(testUser.getUsername());
		
		int initialSize = passwordRequestServiceTest.getAllRequests().size();
				
		passwordRequestServiceTest.requestPasswordReset(testUser.getEmail());
		
		assertTrue(passwordRequestServiceTest.getAllRequests().size() > initialSize);
		
		passwordRequestServiceTest.deletePasswordRequest(testUser.getUsername());
		
		assertTrue(passwordRequestServiceTest.getAllRequests().size() == initialSize);
		
		userService.deleteUser(user.getUsername());
		
	}
}
