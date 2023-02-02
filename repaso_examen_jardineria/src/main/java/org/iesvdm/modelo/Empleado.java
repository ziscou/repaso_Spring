package org.iesvdm.modelo;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("unused")


public class Empleado {
	
	private int id;
	@Size(max=10, message="El nombre no puede superer ")
	private String nombre;
	private String apellido1;
	
}
