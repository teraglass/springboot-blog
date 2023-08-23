package me.tera.blog.controller;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thymleaf")
public class ExampleController {

	@GetMapping("/example")
	public String example(Model model){

		Person person = new Person();
		person.setId(1L);
		person.setName("인간");
		person.setAge(10);
		person.setHobbies(List.of("운동","독서"));

		model.addAttribute("person", person);
		model.addAttribute("today", LocalDate.now());

		return "example";
	}

	@Setter
	@Getter
	class Person{
		private Long id;
		private String name;
		private int age;
		private List<String> hobbies;
	}
}
