package com.forte.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forte.entity.TipoPermiso;

public interface TipoPDao extends JpaRepository<TipoPermiso, Long> {

}
