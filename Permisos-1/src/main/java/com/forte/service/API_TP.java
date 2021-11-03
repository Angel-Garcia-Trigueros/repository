package com.forte.service;

import java.util.List;
import java.util.Optional;

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

import com.forte.dao.TipoPDao;
import com.forte.entity.TipoPermiso;

@RestController
@RequestMapping("/TP")
public class API_TP {
	
	@Autowired
	TipoPDao dao;
	
	/**
	 * Este metodo obtiene todos los resultados de la tabla de tipos de permisos
	 * <p>
	 * 	 (Metodo GET) - 
	 * 	 http://localhost:8080/calificaciones/getBy/
	 * <p>
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<TipoPermiso>> getTPs(){
		
		try {
			
			List<TipoPermiso> all = dao.findAll();
			
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
	public ResponseEntity<TipoPermiso> getTP(@PathVariable("id") Long idpath){
		
		try {
			
			Optional<TipoPermiso> one = dao.findById(idpath);
			
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
	public ResponseEntity<TipoPermiso> setTP(@RequestBody TipoPermiso tp_request){
		
		try {
			
			TipoPermiso nuevo = dao.save(tp_request);
			
			return new ResponseEntity<>(nuevo,HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TipoPermiso> updateTP(@PathVariable("id") Long idpath, @RequestBody TipoPermiso tp_request){
		
		try {
			Optional<TipoPermiso> actualizar = dao.findById(idpath);
			
			if(actualizar.isPresent()) {
				tp_request.setId(idpath);
				TipoPermiso tp = dao.save(tp_request);
				
				return new ResponseEntity<>(tp,HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<TipoPermiso> deleteTP(@PathVariable("id") long idpath){
		
		try {
			
			Optional<TipoPermiso> eliminar = dao.findById(idpath);
			
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
