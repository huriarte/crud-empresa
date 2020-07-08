/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bytesw.crud.empresa.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "EMPRESA")
@NamedQuery(name = "Empresa.findByPK", query = "Select t from Empresa t where t.id = ?1")
@Getter @Setter @ToString
@EqualsAndHashCode(of = {"id"}, callSuper = false)
public class Empresa {
    
    @Id
    @Column(name = "EMPR_ID")
    @TableGenerator(name = "EMPRESA_GENERADOR", table = "SEQUENCE_TABLE", pkColumnName = "SEQ_NAME",
            valueColumnName = "SEQ_COUNT", pkColumnValue = "EMPRESA_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "EMPRESA_GENERADOR")
    private Long id;
    
    @Column(name = "EMPR_NAME", nullable = false)
    private String nombre;
    
    @Column(name = "EMPR_NIT", nullable = false)
    private String nit;
    
    @Column(name = "EMPR_CREDA")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    
    @Column(name = "EMPR_ADRESS", nullable = false)
    private String direccion;
    
    
}
