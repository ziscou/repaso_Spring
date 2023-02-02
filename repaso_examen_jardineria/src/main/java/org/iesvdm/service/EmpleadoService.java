package org.iesvdm.service;


import java.util.List;
import java.util.Optional;


import org.iesvdm.dao.EmpleadoDAO;
import org.iesvdm.modelo.Empleado;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService {
	private EmpleadoDAO empleadoDAO;
	
	//Se utiliza inyección automática por constructor del framework Spring.
	//Por tanto, se puede omitir la anotación Autowired
	//@Autowired
	public EmpleadoService(EmpleadoDAO empleadoDAO) {
		this.empleadoDAO = empleadoDAO;
	}
	
	public List<Empleado> listAll() {
		
		return empleadoDAO.getAll();
		
	}
	public Empleado one(Integer id) {
		Optional<Empleado> optEmp = empleadoDAO.find(id);
		if (optEmp.isPresent())
			return optEmp.get();
		else 
			return null;
	}
	
	public void newEmpleado(Empleado empleado) {
		
		empleadoDAO.create(empleado);
		
	}
	
	public void replaceEmpleado(Empleado empleado) {
		
		empleadoDAO.update(empleado);
		
	}
	
	public void deleteEmpleado(int id) {
		
		empleadoDAO.delete(id);
		
	}
}
