package net.slipp.web.users;

import java.util.List;

import javax.validation.Valid;

import net.slipp.dao.users.UserDao;
import net.slipp.domain.users.Authenticate;
import net.slipp.domain.users.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/users")
public class UserController {

	private static final Logger log = LoggerFactory
			.getLogger(UserController.class);

	private UserDao userDao;

	@RequestMapping("/form")
	public String form(Model model) {
		model.addAttribute("user", new User());
		return "users/form";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String create(@Valid User user, BindingResult bindingResult) {

		log.debug("User : {}", user);
		if (bindingResult.hasErrors()) {
			log.debug("BindingResult has error");
			List<ObjectError> errors = bindingResult.getAllErrors();
			for (ObjectError error : errors) {
				log.debug("error : {}, {}", error.getCode(), error.getDefaultMessage());
			}
			return "users/form";
		}

		userDao.create(user);
		log.debug("Database : {}", userDao.findById(user.getUserId()));
		return "redirect:/";
	}
	
	@RequestMapping(value = "/login/form")
	public String loginform(Model model) {
		model.addAttribute("authenticate", new Authenticate());
		return "users/login";
	}
	
}
