package com.multibanco.multiBanco.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "perfis")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "perfis")
    @SequenceGenerator(name = "perfis", sequenceName = "perfis_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    private String descricao;

    @Column(name = "super")
    private Character superUsuario = 'N';
}
