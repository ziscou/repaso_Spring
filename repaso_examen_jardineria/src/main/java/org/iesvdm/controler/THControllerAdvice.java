package org.iesvdm.controler;

import org.iesvdm.exception.MiExcepcion;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class THControllerAdvice {
	
	@ExceptionHandler(MiExcepcion.class)
	public String handleMiException(Model model, MiExcepcion excepcion) {
		
		model.addAttribute("traza", excepcion.getMessage());
		
		return "empleados";
	}
	@ExceptionHandler(RuntimeException.class)
	public String handleAllUncaugtException(Model model, RuntimeException excepcion) {
		
		model.addAttribute("traza", excepcion.getMessage());
		
		return "empleados";
	}
}
