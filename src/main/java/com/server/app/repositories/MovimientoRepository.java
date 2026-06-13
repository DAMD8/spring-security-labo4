package com.server.app.repositories;

import com.server.app.entities.impl.Movimiento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {
    Page<Movimiento> findByCuentaUsuarioId(Integer usuarioId, Pageable pageable);
}