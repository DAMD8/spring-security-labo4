package com.server.app.services.impl;

import com.server.app.dto.finanzas.TransferenciaRequest;
import com.server.app.entities.impl.*;
import com.server.app.repositories.*;
import com.server.app.services.FinanzasService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FinanzasServiceImpl implements FinanzasService {

    private final CuentaRepository cuentaRepository;
    private final MovimientoRepository movimientoRepository;
    private final CategoriaRepository categoriaRepository;

    public FinanzasServiceImpl(CuentaRepository cuentaRepository, MovimientoRepository movimientoRepository, CategoriaRepository categoriaRepository) {
        this.cuentaRepository = cuentaRepository;
        this.movimientoRepository = movimientoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override @Transactional(readOnly = true)
    public List<Cuenta> listarCuentasPorUsuario(Integer userId) { return cuentaRepository.findByUsuarioId(userId); }

    @Override @Transactional
    public Cuenta guardarCuenta(Cuenta cuenta) { return cuentaRepository.save(cuenta); }

    @Override @Transactional(readOnly = true)
    public Page<Movimiento> listarMovimientosPaginados(Integer userId, int page, int size) {
        return movimientoRepository.findByCuentaUsuarioId(userId, PageRequest.of(page, size));
    }

    @Override @Transactional(readOnly = true)
    public List<Categoria> listarCategoriasGlobales() { return categoriaRepository.findAll(); }

    @Override
    @Transactional
    public void realizarTransferencia(TransferenciaRequest req) {
        Cuenta origen = cuentaRepository.findById(req.getCuentaOrigenId())
                .orElseThrow(() -> new IllegalArgumentException("Cuenta de origen no encontrada"));
        Cuenta destino = cuentaRepository.findById(req.getCuentaDestinoId())
                .orElseThrow(() -> new IllegalArgumentException("Cuenta de destino no encontrada"));

        if (origen.getSaldoBase() < req.getMonto()) {
            throw new IllegalArgumentException("Fondos insuficientes en la cuenta de origen");
        }

        origen.setSaldoBase(origen.getSaldoBase() - req.getMonto());
        double montoDestino = req.getMonto() * req.getTasaCambio();
        destino.setSaldoBase(destino.getSaldoBase() + montoDestino);

        cuentaRepository.save(origen);
        cuentaRepository.save(destino);

        Movimiento movO = new Movimiento();
        movO.setMonto(-req.getMonto());
        movO.setMonedaOriginal(origen.getMoneda());
        movO.setTasaCambio(1.0);
        movO.setFecha(LocalDateTime.now());
        movO.setDescription("Transferencia enviada a: " + destino.getAlias());
        movO.setCuenta(origen);

        Movimiento movD = new Movimiento();
        movD.setMonto(montoDestino);
        movD.setMonedaOriginal(origen.getMoneda());
        movD.setTasaCambio(req.getTasaCambio());
        movD.setFecha(LocalDateTime.now());
        movD.setDescription("Transferencia recibida desde: " + origen.getAlias());
        movD.setCuenta(destino);

        movimientoRepository.save(movO);
        movimientoRepository.save(movD);
    }
}