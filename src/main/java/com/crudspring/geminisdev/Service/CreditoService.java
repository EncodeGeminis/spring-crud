package com.crudspring.geminisdev.Service;

import com.crudspring.geminisdev.Entity.Credito;
import java.util.List;
import java.util.Optional;

public interface CreditoService {
    //metodo que crea un nuevo credito
    public Credito crearCredito(Credito credito, Long clienteId);
    //obtener un credito por Id
    public Optional<Credito> obtenerCreditoPorId(Long id);
    //obtener todos los creditos
    public List<Credito> listaCreditos();
}
