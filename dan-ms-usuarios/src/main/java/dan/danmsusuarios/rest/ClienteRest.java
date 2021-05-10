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


import dan.danmsusuarios.Cliente;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/cliente")
@Api(value="ClienteRest",description="Permite gestionar los clientes")



public class ClienteRest {
	
	private static final List<Cliente> listaClientes=new ArrayList<>();
	private static Integer ID_GEN=1;
	
	 @GetMapping(path = "/{id}")
	public ResponseEntity<Cliente> clientePorId(@PathVariable Integer id){

	      Optional<Cliente> c =  listaClientes
	              .stream()
	              .filter(unCli -> unCli.getId().equals(id))
	              .findFirst();
	      return ResponseEntity.of(c);
	 }
	 
	 @GetMapping
	 public ResponseEntity<List<Cliente>> todos(){
		 return ResponseEntity.ok(listaClientes);
	 }
	 
	 @PostMapping
	 public ResponseEntity<Cliente> crear (@RequestBody Cliente nuevo){
		 nuevo.setId(ID_GEN++);
		 listaClientes.add(nuevo);
		 return ResponseEntity.ok(nuevo);
	 }
	 
	 @PutMapping(path = "/{id}")
	 public ResponseEntity<Cliente> actualizar(@RequestBody Cliente nuevo,  @PathVariable Integer id){
	        OptionalInt indexOpt =   IntStream.range(0, listaClientes.size())
	        .filter(i -> listaClientes.get(i).getId().equals(id))
	        .findFirst();

	        if(indexOpt.isPresent()){
	            listaClientes.set(indexOpt.getAsInt(), nuevo);
	            return ResponseEntity.ok(nuevo);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	 
	    @DeleteMapping(path = "/{id}")
	    public ResponseEntity<Cliente> borrar(@PathVariable Integer id){
	        OptionalInt indexOpt =   IntStream.range(0, listaClientes.size())
	        .filter(i -> listaClientes.get(i).getId().equals(id))
	        .findFirst();

	        if(indexOpt.isPresent()){
	            listaClientes.remove(indexOpt.getAsInt());
	            return ResponseEntity.ok().build();
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	

}
