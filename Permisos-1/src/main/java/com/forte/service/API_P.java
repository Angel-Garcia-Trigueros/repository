package com.forte.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.forte.dao.PermisoDao;
import com.forte.dao.TipoPDao;
import com.forte.entity.Permiso;
import com.forte.entity.TipoPermiso;

@RestController
@RequestMapping("/permiso")
public class API_P {
	
	@Autowired
	PermisoDao dao;
	
	@Autowired
	TipoPDao daoTP;
	
	@GetMapping
	public ResponseEntity<List<Permiso>> getPs(){
		
		try {
			
			List<Permiso> all = dao.findAll();
			
			if(all.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<>(all,HttpStatus.OK);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Permiso> getP(@PathVariable("id") Long idpath){
		
		try {
			
			Optional<Permiso> one = dao.findById(idpath);
			
			if(one.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<>(one.get(),HttpStatus.OK);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<Permiso> setP(@RequestParam(required = true) TipoPermiso tp, @RequestBody Permiso per_request){
		
		try {
			
			Optional<TipoPermiso> op = daoTP.findById(tp.getId());
			
			if(op.isPresent()) {
				Permiso nuevo = new Permiso(per_request.getNombre(), per_request.getApellidos(), per_request.getFecha(), tp);
				
				dao.save(nuevo);
				
				return new ResponseEntity<>(nuevo,HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Permiso> updateP(@RequestParam(required = true) TipoPermiso tp, @PathVariable("id") Long idpath, @RequestBody Permiso per_request){
		
		try {
			
			Optional<TipoPermiso> op = daoTP.findById(tp.getId());
			Optional<Permiso> actualizar = dao.findById(idpath);
			
			if(actualizar.isPresent()) {
				
				per_request.setId(idpath);
				
				if(op.isPresent()){
					per_request.setTp(tp);
					Permiso per = dao.save(per_request);
					
					return new ResponseEntity<>(per,HttpStatus.OK);
				}else {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
			}else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Permiso> deleteP(@PathVariable("id") long idpath){
		
		try {
			
			Optional<Permiso> eliminar = dao.findById(idpath);
			
			if(eliminar.isPresent()) {
				dao.delete(eliminar.get());
				
				return new ResponseEntity<>(HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
