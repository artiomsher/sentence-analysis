package com.example.junior;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HistoryController {

	@GetMapping("/history")
	public String historyPage(Model model, @ModelAttribute History history) {
		model.addAttribute("form", history);
		return "history";
	}
}
