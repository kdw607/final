package net.slipp.web.users;

import net.slipp.dao.users.UserDao;
import net.slipp.domain.users.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/users")
public class UserController {

	@RequestMapping("/form")
	public String form(){
		return "users/form";
	}

}
