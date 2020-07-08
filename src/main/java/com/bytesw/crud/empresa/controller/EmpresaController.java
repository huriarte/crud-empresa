/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bytesw.crud.empresa.controller;

import com.bytesw.crud.empresa.beans.EmpresaBean;
import com.bytesw.crud.empresa.model.Empresa;
import com.bytesw.crud.empresa.services.EmpresaService;
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

@RestController
@RequestMapping("/servicios")
public class EmpresaController {
    
    @Autowired
    EmpresaService empresaService;

    @GetMapping("/empresa")
    public ResponseEntity<List<Empresa>> getAllEmpresas() {
        try {
            List<Empresa> empresas = empresaService.listarEmpresas();

            if (empresas.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(empresas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/empresa/{id}")
    public ResponseEntity<Empresa> getEmpresaById(@PathVariable("id") Long id) {
        Optional<Empresa> empresaBD = empresaService.buscarPorIdEmpresa(id);

        if (empresaBD.isPresent()) {
            return new ResponseEntity<>(empresaBD.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/empresa")
    public ResponseEntity<Empresa> createEmpresa(@RequestBody EmpresaBean empresabean) {
        try {
            Empresa empresaBD = empresaService.crearEmpresa(empresabean);
            return new ResponseEntity<>(empresaBD, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/empresa/{id}")
    public ResponseEntity<Empresa> updateEmpresa(@PathVariable("id") Long id, @RequestBody EmpresaBean empresaBean) {
                
        try {
            Empresa empresaBD = empresaService.actualizarEmpresa(id, empresaBean);
            return new ResponseEntity<>(empresaBD, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/empresa/{id}")
    public ResponseEntity<HttpStatus> deleteEmpresa(@PathVariable("id") Long id) {
        try {
            empresaService.eliminarEmpresa(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
