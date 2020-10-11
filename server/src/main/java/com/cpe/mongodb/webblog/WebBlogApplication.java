package com.cpe.mongodb.webblog;

import com.cpe.mongodb.webblog.entity.Role;
import com.cpe.mongodb.webblog.entity.User;
import com.cpe.mongodb.webblog.repository.RoleRepo;
import com.cpe.mongodb.webblog.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class WebBlogApplication implements CommandLineRunner {

	@Autowired
	RoleRepo roleRepo;
	@Autowired
	UserRepo userRepo;

	public static void main(String[] args) {
		SpringApplication.run(WebBlogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//	    // create Role
//		List<Role> listOfRoles = new ArrayList<Role>();
//		Role r1 = new Role();
//		Role r2 = new Role();
//		Role r3 = new Role();
//
//		r1.setRoleOfUser("Newbie");
//		r2.setRoleOfUser("Experienced");
//		r3.setRoleOfUser("Admin");
//
//		listOfRoles.add(r1);
//		listOfRoles.add(r2);
//		listOfRoles.add(r3);
//
//		roleRepo.insert(listOfRoles);
//
//		// create User
//		List<User> listOfUsers = new ArrayList<User>();
//		User u1 = new User();
//		User u2 = new User();
//		User u3 = new User();
//		User u4 = new User();
//
//		u1.setUsername("admin1");
//		u1.setPassword("admin1");
//		u1.setEmail("admin1@gmail.com");
//		u1.setName("IAM ADMIN1");
//		u1.setRegisterDate(u1.getRegisterDate());
//		u1.setRole(r3);
//
//		u2.setUsername("admin2");
//		u2.setPassword("admin2");
//		u2.setEmail("admin2@gmail.com");
//		u2.setName("IAM ADMIN2");
//		u2.setRegisterDate(u2.getRegisterDate());
//		u2.setRole(r3);
//
//		u3.setUsername("imbac");
//		u3.setPassword("imbac");
//		u3.setEmail("imbac@gmail.com");
//		u3.setName("Natthawut Sunthornrot");
//		u3.setRegisterDate(u3.getRegisterDate());
//		u3.setRole(r2);
//
//		u4.setUsername("newbac");
//		u4.setPassword("newbac");
//		u4.setEmail("newbac@gmail.com");
//		u4.setName("Newbac Natthawut");
//		u4.setRegisterDate(u4.getRegisterDate());
//		u4.setRole(r1);
//
//		listOfUsers.add(u1);
//		listOfUsers.add(u2);
//		listOfUsers.add(u3);
//		listOfUsers.add(u4);
//
//		userRepo.insert(listOfUsers);
	}
}
