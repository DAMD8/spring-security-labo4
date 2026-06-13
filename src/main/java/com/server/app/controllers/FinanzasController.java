package com.server.app.controllers;

import com.server.app.dto.finanzas.TransferenciaRequest;
import com.server.app.dto.response.Pagination;
import com.server.app.dto.response.PaginationMeta;
import com.server.app.entities.impl.*;
import com.server.app.services.FinanzasService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/finanzas")
public class FinanzasController {

    private final FinanzasService finanzasService;

    public FinanzasController(FinanzasService finanzasService) {
        this.finanzasService = finanzasService;
    }

    @GetMapping("/cuentas")
    public ResponseEntity<List<Cuenta>> getCuentas() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(finanzasService.listarCuentasPorUsuario(user.getId()));
    }

    @PostMapping("/cuentas")
    public ResponseEntity<Cuenta> crearCuenta(@RequestBody Cuenta cuenta) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        cuenta.setUsuario(user);
        return ResponseEntity.ok(finanzasService.guardarCuenta(cuenta));
    }

    @GetMapping("/movimientos")
    public ResponseEntity<Pagination<Movimiento>> getMovimientos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Page<Movimiento> mPage = finanzasService.listarMovimientosPaginados(user.getId(), page, size);

        // SOLUCIÓN AQUÍ: Pasamos los 4 parámetros directamente al constructor
        PaginationMeta meta = new PaginationMeta(
                mPage.getNumber(),              // page
                mPage.getSize(),                // pageSize
                mPage.getTotalPages(),          // pageCount
                (int) mPage.getTotalElements()  // total
        );

        Pagination<Movimiento> response = new Pagination<>(mPage.getContent(), meta);        response.setData(mPage.getContent());
        response.setPagination(meta);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/transferencias")
    public ResponseEntity<Void> ejecutarTransferencia(@RequestBody TransferenciaRequest request) {
        finanzasService.realizarTransferencia(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> getCategorias() {
        return ResponseEntity.ok(finanzasService.listarCategoriasGlobales());
    }
}