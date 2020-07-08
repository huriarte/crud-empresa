/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bytesw.crud.empresa.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class EmpresaBean {
    
    private String nombre;
    private String nit;
    private String fechaCreacion;
    private String direccion;
    
}
