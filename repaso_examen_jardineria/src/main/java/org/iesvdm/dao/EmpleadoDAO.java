package org.iesvdm.dao;

import java.util.List;
import java.util.Optional;

import org.iesvdm.modelo.Empleado;



public interface EmpleadoDAO {
	
	public void create(Empleado empleado);
	
	public List<Empleado> getAll();
	
	public Optional<Empleado>  find(int id);
	
	public void update(Empleado empleado);
	
	public void delete(int id);
	
}
