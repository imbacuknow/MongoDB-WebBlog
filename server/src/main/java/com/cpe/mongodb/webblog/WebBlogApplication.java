package com.cpe.mongodb.webblog;

import com.cpe.mongodb.webblog.entity.Role;
import com.cpe.mongodb.webblog.entity.Tag;
import com.cpe.mongodb.webblog.entity.User;
import com.cpe.mongodb.webblog.repository.RoleRepo;
import com.cpe.mongodb.webblog.repository.TagRepo;
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
	@Autowired
	TagRepo tagRepo;

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
//		u1.setRoleName(r3.getRoleOfUser());
//
//		u2.setUsername("admin2");
//		u2.setPassword("admin2");
//		u2.setEmail("admin2@gmail.com");
//		u2.setName("IAM ADMIN2");
//		u2.setRegisterDate(u2.getRegisterDate());
//		u1.setRoleName(r3.getRoleOfUser());
//
//		u3.setUsername("imbac");
//		u3.setPassword("imbac");
//		u3.setEmail("imbac@gmail.com");
//		u3.setName("Natthawut Sunthornrot");
//		u3.setRegisterDate(u3.getRegisterDate());
//		u1.setRoleName(r2.getRoleOfUser());
//
//		u4.setUsername("newbac");
//		u4.setPassword("newbac");
//		u4.setEmail("newbac@gmail.com");
//		u4.setName("Newbac Natthawut");
//		u4.setRegisterDate(u4.getRegisterDate());
//		u1.setRoleName(r1.getRoleOfUser());
//
//		listOfUsers.add(u1);
//		listOfUsers.add(u2);
//		listOfUsers.add(u3);
//		listOfUsers.add(u4);
//
//		userRepo.insert(listOfUsers);
//
//		// create tag
//		List<Tag> listOfTags = new ArrayList<Tag>();
//		Tag t1 = new Tag();
//		Tag t2 = new Tag();
//		Tag t3 = new Tag();
//
//		t1.setColor("orange");
//		t1.setText("หุ้น");
//
//		t2.setColor("teal");
//		t2.setText("กองทุนรวม");
//
//		t3.setColor("indigo");
//		t3.setText("ข่าว");
//
//		listOfTags.add(t1);
//		listOfTags.add(t2);
//		listOfTags.add(t3);
//
//		tagRepo.insert(listOfTags);
	}
}
