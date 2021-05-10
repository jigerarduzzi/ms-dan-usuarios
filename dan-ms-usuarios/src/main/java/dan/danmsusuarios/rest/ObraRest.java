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

import dan.danmsusuarios.Obra;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/obra")
@Api(value="ObraRest",description="Permite gestionar las obras..")
public class ObraRest {
	private static final List<Obra> listaObras=new ArrayList<>();
	private static Integer ID_GEN=1;
	
	@GetMapping(path="/{id}")
	public ResponseEntity<Obra> obraPorId(@PathVariable Integer id){
		  Optional<Obra> c =  listaObras
	              .stream()
	              .filter(unCli -> unCli.getId().equals(id))
	              .findFirst();
	      return ResponseEntity.of(c);
	}
	
	 @PostMapping
	 public ResponseEntity<Obra> crear (@RequestBody Obra nuevo){
		 nuevo.setId(ID_GEN++);
		 listaObras.add(nuevo);
		 return ResponseEntity.ok(nuevo);
	 }
	 
	 @PutMapping(path = "/{id}")
	 public ResponseEntity<Obra> actualizar(@RequestBody Obra nuevo,  @PathVariable Integer id){
	        OptionalInt indexOpt =   IntStream.range(0, listaObras.size())
	        .filter(i -> listaObras.get(i).getId().equals(id))
	        .findFirst();

	        if(indexOpt.isPresent()){
	        	listaObras.set(indexOpt.getAsInt(), nuevo);
	            return ResponseEntity.ok(nuevo);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	 
	   @DeleteMapping(path = "/{id}")
	  public ResponseEntity<Obra> borrar(@PathVariable Integer id){
	        OptionalInt indexOpt =   IntStream.range(0, listaObras.size())
	        .filter(i -> listaObras.get(i).getId().equals(id))
	        .findFirst();

	        if(indexOpt.isPresent()){
	        	listaObras.remove(indexOpt.getAsInt());
	        	return ResponseEntity.ok().build();
	        } else {
	        	return ResponseEntity.notFound().build();
	        }
	   	}
}
