package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class webControllerD2UD3 {
	private static final String USUARIO_VALIDO = "KevinMorenito777";
	private static final String PASSWORD_VALIDO = "12345";

	@GetMapping("/login")
	public String mostrarLogin(@RequestParam(name = "error", required = false) String error, HttpSession sesion,
			Model model) {
		if (sesion.getAttribute("usuario") != null) {
			return "redirect:/";
		}
		model.addAttribute("error", error);
		return "login";
	}

	@PostMapping("/login")
	public String procesarLogin(@RequestParam(name = "usuario", required = false) String usuario,
			@RequestParam(name = "contraseña", required = false) String contraseña, HttpSession sesion) {

		if (USUARIO_VALIDO.equals(usuario) && PASSWORD_VALIDO.equals(contraseña)) {
			sesion.setAttribute("usuario", usuario);
			return "redirect:/";
		} else {
			return "redirect:/login?error=Usuario o contraseña incorrectos";
		}
	}

	@GetMapping("/")
	public String mostrarInicio(HttpSession sesion, Model model) {
		String usuario = (String) sesion.getAttribute("usuario");
		if (usuario == null) {
			return "redirect:/login";
		}
		model.addAttribute("usuario", usuario);
		model.addAttribute("pagina", "Inicio");
		return "principal";
	}

	@GetMapping("/pagina1")
	public String mostrarPagina1(HttpSession sesion, Model model) {
		return mostrarPaginaGenerica(sesion, model, "pagina1");
	}

	@GetMapping("/pagina2")
	public String mostrarPagina2(HttpSession sesion, Model model) {
		return mostrarPaginaGenerica(sesion, model, "pagina2");
	}

	private String mostrarPaginaGenerica(HttpSession sesion, Model model, String nombrePagina) {
		String usuario = (String) sesion.getAttribute("usuario");
		if (usuario == null) {
			return "redirect:/login";
		}
		model.addAttribute("usuario", usuario);
		model.addAttribute("pagina", nombrePagina);
		return "principal";
	}

	@GetMapping("/logout")
	public String cerrarSesion(HttpSession sesion) {
		sesion.setAttribute("usuario", null);
		sesion.setAttribute("conntraseña", null);
		return "redirect:/login";
	}

}
