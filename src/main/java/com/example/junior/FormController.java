package com.example.junior;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FormController {

	private final String UPLOAD_DIRECTORY = "./uploads/";
	
	@GetMapping("/")
	public String homepage(Model model) {
		model.addAttribute("form", new Form());
		return "index";
	}

	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file, @ModelAttribute Form form, Model model) {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			Path path = Paths.get(UPLOAD_DIRECTORY + fileName);
			form.setFilePath(path.toString());
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
		}

		return "result";
	}
}
