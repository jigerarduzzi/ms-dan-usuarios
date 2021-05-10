package dan.danmsusuarios.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import dan.danmsusuarios.Empleado;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/empleado")
@Api(value="EmpleadoRest",description="Permite gestionar los empleados..")
public class EmpleadoRest {
	
	
	private static final List<Empleado> listaEmpleados=new ArrayList<>();
	private static Integer ID_GEN=1;
	
	@GetMapping(path="/{id}")
	public ResponseEntity<Empleado> empleadoPorId(@PathVariable Integer id){
		  Optional<Empleado> c =  listaEmpleados
	              .stream()
	              .filter(unCli -> unCli.getId().equals(id))
	              .findFirst();
	      return ResponseEntity.of(c);
	}
	
	 @PostMapping
	 public ResponseEntity<Empleado> crear (@RequestBody Empleado nuevo){
		 nuevo.setId(ID_GEN++);
		 listaEmpleados.add(nuevo);
		 return ResponseEntity.ok(nuevo);
	 }
	 
	 @PutMapping(path = "/{id}")
	 public ResponseEntity<Empleado> actualizar(@RequestBody Empleado nuevo,  @PathVariable Integer id){
	        OptionalInt indexOpt =   IntStream.range(0, listaEmpleados.size())
	        .filter(i -> listaEmpleados.get(i).getId().equals(id))
	        .findFirst();

	        if(indexOpt.isPresent()){
	        	listaEmpleados.set(indexOpt.getAsInt(), nuevo);
	            return ResponseEntity.ok(nuevo);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	 
	   @DeleteMapping(path = "/{id}")
	  public ResponseEntity<Empleado> borrar(@PathVariable Integer id){
	        OptionalInt indexOpt =   IntStream.range(0, listaEmpleados.size())
	        .filter(i -> listaEmpleados.get(i).getId().equals(id))
	        .findFirst();

	        if(indexOpt.isPresent()){
	        	listaEmpleados.remove(indexOpt.getAsInt());
	        	return ResponseEntity.ok().build();
	        } else {
	        	return ResponseEntity.notFound().build();
	        }
	   	}
	
}
