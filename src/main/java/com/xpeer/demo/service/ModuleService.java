package com.xpeer.demo.service;


import java.util.Optional;

import com.xpeer.demo.entity.Module;

public interface ModuleService {
	
	public Iterable<Module> findAll();
			
	public Optional<Module> findById(Long id);
	
	public Module save(Module Module);
	
	public void deleteById(Long id);
	
}
