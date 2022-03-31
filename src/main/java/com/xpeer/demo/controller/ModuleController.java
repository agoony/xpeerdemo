package com.xpeer.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xpeer.demo.entity.Module;
import com.xpeer.demo.service.ModuleService;

@RestController
@RequestMapping("/api/Modules")
public class ModuleController { 

	@Autowired
	private ModuleService  ModuleService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Module Module){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(ModuleService.save(Module));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Long id)
	{
		Optional<Module> oModule = ModuleService.findById(id);
		if(!oModule.isPresent()) {
			return ResponseEntity.notFound().build();
		}		
		
		return ResponseEntity.ok(oModule);
		
	}
	
	//Update Module 
	@PutMapping("/{id}")
	public  ResponseEntity<?> update (@RequestBody Module ModuleDetails,@PathVariable(value = "id" )Long ModuleId)
	{
		Optional<Module> Module = ModuleService.findById(ModuleId);
		if(!Module.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Module.get().setModuleName(ModuleDetails.getModuleName());
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(ModuleService.save(Module.get()));
		}
	
	
	//Delete Module
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable( value="id") Long ModuleId) {
		Optional<Module> Module = ModuleService.findById(ModuleId);
		if(!Module.isPresent()) {
			return ResponseEntity.notFound().build();					
		}
		
		ModuleService.deleteById(ModuleId);
		return  ResponseEntity.ok().build();
	}
	
	//Read All Modules

	@GetMapping
	public List<Module> readAll(){
		
		List<Module> Modules = StreamSupport
				.stream(ModuleService.findAll().spliterator(),false)
				.collect(Collectors.toList());
		
		return Modules;
		
	}

	 

	
}
