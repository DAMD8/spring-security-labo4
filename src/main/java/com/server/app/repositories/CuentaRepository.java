package com.server.app.repositories;

import com.server.app.entities.impl.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {
    List<Cuenta> findByUsuarioId(Integer usuarioId);
}