package com.xpeer.demo.service;


import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xpeer.demo.entity.Module;
import com.xpeer.demo.repository.ModuleRepository;

@Service
public class ModuleServiceImpl implements ModuleService{

	@Autowired
	private ModuleRepository ModuleRepository;
	
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Module> findAll() {
		
		return ModuleRepository.findAll();
	}

	
	@Override
	@Transactional(readOnly = true)
	public Optional<Module> findById(Long id) {		
		return ModuleRepository.findById(id);
	}

	@Override
	@Transactional
	public Module save(Module Module) {
		return ModuleRepository.save(Module);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		ModuleRepository.deleteById(id);		
	}

	

}
