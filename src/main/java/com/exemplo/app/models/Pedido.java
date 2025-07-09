package com.exemplo.app.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private Double valor;

    private LocalDateTime dataHoraEntrega;
    private String formaPagamento;
    private boolean agendado;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Restaurante restaurante;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> itens;


    // Getters e Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }

    public LocalDateTime getDataHoraEntrega() { return dataHoraEntrega; }
    public void setDataHoraEntrega(LocalDateTime dataHoraEntrega) { this.dataHoraEntrega = dataHoraEntrega; }

    public String getFormaPagamento() { return formaPagamento; }
    public void setFormaPagamento(String formaPagamento) { this.formaPagamento = formaPagamento; }

    public boolean isAgendado() { return agendado; }
    public void setAgendado(boolean agendado) { this.agendado = agendado; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Restaurante getRestaurante() { return restaurante; }
    public void setRestaurante(Restaurante restaurante) { this.restaurante = restaurante; }

    public List<Item> getItens() { return itens; }
    public void setItens(List<Item> itens) { this.itens = itens; }
}