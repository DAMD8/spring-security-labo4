package com.server.app.services;

import com.server.app.dto.finanzas.TransferenciaRequest;
import com.server.app.entities.impl.*;
import org.springframework.data.domain.Page;
import java.util.List;

public interface FinanzasService {
    List<Cuenta> listarCuentasPorUsuario(Integer userId);
    Cuenta guardarCuenta(Cuenta cuenta);
    Page<Movimiento> listarMovimientosPaginados(Integer userId, int page, int size);
    List<Categoria> listarCategoriasGlobales();
    void realizarTransferencia(TransferenciaRequest request);
}