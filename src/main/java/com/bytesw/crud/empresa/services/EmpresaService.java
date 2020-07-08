/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bytesw.crud.empresa.services;

import com.bytesw.crud.empresa.beans.EmpresaBean;
import com.bytesw.crud.empresa.model.Empresa;
import com.bytesw.crud.empresa.repository.EmpresaRepository;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {

    @Autowired
    EmpresaRepository empresaRepository;

    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    
    public List<Empresa> listarEmpresas() {
        List<Empresa> listaEmpresas = new ArrayList<>();
        empresaRepository.findAll().forEach(listaEmpresas::add);
        return listaEmpresas;
    }
    
    public Optional<Empresa> buscarPorIdEmpresa(Long idEmpresa) {
        return empresaRepository.findById(idEmpresa);
    }
    
    public Empresa crearEmpresa(EmpresaBean empresaBean) throws Exception {
        Empresa empresa = new Empresa();
        empresa.setNombre(empresaBean.getNombre());
        empresa.setNit(empresaBean.getNit());
        empresa.setFechaCreacion(formatter.parse(empresaBean.getFechaCreacion()));
        empresa.setDireccion(empresaBean.getDireccion());

        return empresaRepository.save(empresa);
    }

    public Empresa actualizarEmpresa(Long idEmpresa, EmpresaBean empresaBean) throws Exception {
        Optional<Empresa> empresa = empresaRepository.findById(idEmpresa);

        if (!empresa.isPresent()) {
            throw new Exception("Empresa con ID[" + idEmpresa + "] no existe en el sistema");
        }

        Empresa empresaBD = empresa.get();

        empresaBD.setNombre(empresaBean.getNombre() != null ? empresaBean.getNombre() : empresaBD.getNombre());
        empresaBD.setNit(empresaBean.getNit() != null ? empresaBean.getNit() : empresaBD.getNit());
        empresaBD.setFechaCreacion(empresaBean.getFechaCreacion() != null ? formatter.parse(empresaBean.getFechaCreacion()) : empresaBD.getFechaCreacion());
        empresaBD.setDireccion(empresaBean.getDireccion() != null ? empresaBean.getDireccion() : empresaBD.getDireccion());

        return empresaRepository.save(empresaBD);

    }

    public void eliminarEmpresa(Long idEmpresa) throws Exception {
        Optional<Empresa> empresa = empresaRepository.findById(idEmpresa);

        if (!empresa.isPresent()) {
            throw new Exception("Empresa con ID[" + idEmpresa + "] no existe en el sistema");
        }
        
        empresaRepository.deleteById(idEmpresa);
    }
}
