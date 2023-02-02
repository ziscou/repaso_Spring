package org.iesvdm.dao;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import org.iesvdm.modelo.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@Repository
public class EmpleadoDAOImpl implements EmpleadoDAO{
	
	//Plantilla jdbc inyectada automáticamente por el framework Spring, gracias a la anotación @Autowired.
		 @Autowired
		 private JdbcTemplate jdbcTemplate;
		
		/**
		 * Inserta en base de datos el nuevo Empleado, actualizando el id en el bean Empleado.
		 */
		@Override	
		public synchronized void create(Empleado empleado) {
			
			
			Integer idx = jdbcTemplate.queryForObject("Select MAX(codigo_empleado) from empleado;", (rs, rowNum) -> rs.getInt("MAX(codigo_empleado)"));

			int rows = jdbcTemplate.update("""
											INSERT INTO empleado ( codigo_empleado ,nombre ,apellido1 WHERE codigo_empleado = ?
											""", empleado.getNombre()
											, empleado.getApellido1()
											, empleado.getId());
			
			log.info("Update de Empleado con {} registros actualizados.", rows);
	    
			}
			
								//Desde java15+ se tiene la triple quote """ para bloques de texto como cadenas.
			String sqlInsert = """
								INSERT INTO empleado (nombre, apellido1) 
								VALUES  (     ?,         ?)
							   """;
			
//			KeyHolder keyHolder = new GeneratedKeyHolder();
//
//			
//			//Con recuperación de id generado
//			int rows = jdbcTemplate.update(connection -> {
//				PreparedStatement ps = connection.prepareStatement(sqlInsert, new String[] { "codigo_empleado" });
//				int idx = 1;
//				ps.setString(idx++, empleado.getNombre());
//				ps.setString(idx++, empleado.getApellido1());
//				return ps;
//			},keyHolder);
//			
//			empleado.setId(keyHolder.getKey().intValue());
//
//			log.info("Insertados {} registros.", rows);
//		}

		/**
		 * Devuelve lista con todos loa Empleados.
		 */
		@Override
		public List<Empleado> getAll() {
			
			List<Empleado> listEmp = jdbcTemplate.query(
	                "SELECT * FROM empleado",
	                (rs, rowNum) -> new Empleado(rs.getInt("codigo_empleado"),
	                						 	rs.getString("nombre"),
	                						 	rs.getString("apellido1")
	                						 	)
	        );
			
			log.info("Devueltos {} registros.", listEmp.size());
			
	        return listEmp;
	        
		}

		/**
		 * Devuelve Optional de Empleado con el ID dado.
		 */
		@Override
		public Optional<Empleado> find(int id) {
			
			Empleado emp =  jdbcTemplate
					.queryForObject("SELECT * FROM empleado WHERE codigo_empleado = ?"														
									, (rs, rowNum) -> new Empleado(rs.getInt("codigo_empleado"),
	            						 						rs.getString("nombre"),
	            						 						rs.getString("apellido1"))
									, id
									);
			
			if (emp != null) { 
				return Optional.of(emp);}
			else { 
				log.info("Empleado no encontrado.");
				return Optional.empty(); }
	        
		}
		/**
		 * Actualiza Empleado con campos del bean Empleado según ID del mismo.
		 */
		@Override
		public void update(Empleado empleado) {
			
			int rows = jdbcTemplate.update("""
											UPDATE empleado SET 
															nombre = ?, 
															apellido1 = ?,   
													WHERE codigo_empleado = ?
											""", empleado.getNombre()
											, empleado.getApellido1()
											, empleado.getId());
			
			log.info("Update de Empleado con {} registros actualizados.", rows);
	    
		}

		/**
		 * Borra Empleado con ID proporcionado.
		 */
		@Override
		public void delete(int id) {
			
			int rows = jdbcTemplate.update("DELETE FROM empleado WHERE codigo_empleado = ?", id);
			
			log.info("Delete de Empleado con {} registros eliminados.", rows);		
			
		}
	
}
