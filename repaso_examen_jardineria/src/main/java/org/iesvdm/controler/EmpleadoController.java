package org.iesvdm.controler;

import java.util.List;

import org.iesvdm.exception.MiExcepcion;
import org.iesvdm.modelo.Empleado;

import org.iesvdm.service.EmpleadoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class EmpleadoController {
		
		private EmpleadoService empleadoService;
		
		//Se utiliza inyección automática por constructor del framework Spring.
		//Por tanto, se puede omitir la anotación Autowired
		//@Autowired
		public EmpleadoController(EmpleadoService empleadoService) {
			this.empleadoService = empleadoService;
		}
		
		//@RequestMapping(value = "/empleados", method = RequestMethod.GET)
		//equivalente a la siguiente anotación
		@GetMapping("/empleados") //Al no tener ruta base para el controlador, cada método tiene que tener la ruta completa
		public String listar(Model model) {
			
			List<Empleado> listaEmpleados =  empleadoService.listAll();
			model.addAttribute("listaEmpleados", listaEmpleados);
					
			return "empleados";
			
		}
		@GetMapping("/empleados/{id}")
		public String detalle(Model model, @PathVariable Integer id ) {
			
			Empleado empleado = empleadoService.one(id);
			model.addAttribute("empleado", empleado);
			
			
		
			
			return "detalle-empleado";
			
		}
		
		@GetMapping("/empleados/crear")
		public String crear(Model model) {
			
			Empleado empleado = new Empleado();
			model.addAttribute("empleado", empleado);
			
			return "crear-empleado";
			
		}
		
		@PostMapping("/empleados/crear")
		public RedirectView submitCrear(@ModelAttribute("empleado") Empleado empleado) {
			
			empleadoService.newEmpleado(empleado);
					
			return new RedirectView("/empleados") ;
			
		}
		
		@GetMapping("/empleados/editar/{id}")
		public String editar(Model model, @PathVariable Integer id) {
			
			Empleado empleado = empleadoService.one(id);
			model.addAttribute("empleado", empleado);
			
			return "editar-empleado";
			
		}
		
		
		@PostMapping("/empleados/editar/{id}")
		public RedirectView submitEditar(@ModelAttribute("empleado") Empleado empleado) {
			
			empleadoService.replaceEmpleado(empleado);		
			
			return new RedirectView("/empleados");
		}
		
		@PostMapping("/empleados/borrar/{id}")
		public RedirectView submitBorrar(@PathVariable Integer id) {
			
			empleadoService.deleteEmpleado(id);
			
			return new RedirectView("/empleados");
		}
		@GetMapping("/demoth-runtime-excepcion")
		public String demothRuntimeException() {
		throw new RuntimeException("Prueba de lanzamiento de excepción y manejo por ControllerAdvice...");
		//return "demoth-runtime-exception"; No lo ponemos porque ya lo hace la RuntimeException y no
		//s redirige a error.html
		}
		@GetMapping("/demoth-mi-excepcion")
		public String demothMiExcepcion() throws MiExcepcion {
		throw new MiExcepcion();
		//return "demoth-mi-excepcion"; No lo ponemos porque ya lo hace MiExcepcion y nos redirige a
		//error-mi-excepcion.html
		}
}
