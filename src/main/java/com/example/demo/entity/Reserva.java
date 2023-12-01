package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="RESERVAS")
public class Reserva {
	@Id
	@Column(name = "ID_RESERVA")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqReserva")
    @SequenceGenerator(name = "seqReserva", allocationSize = 1, sequenceName = "SEQ_RESERVA")
    @Builder.Default
    private Long id=0L;
	
	@Column(name = "CLASE")
	@NotNull     
    private String clase;
	
	@ManyToOne
    @JoinColumn(name="HOTEL_ID", nullable = false)
    private Hotel hotel;
	
	@ManyToOne
    @JoinColumn(name="SUCURSAL_ID", nullable = false)
    private Sucursal sucursal;
	
	@ManyToOne
    @JoinColumn(name="VUELO_ID", nullable = false)
    private Vuelo vuelo;
	
	@ManyToOne
    @JoinColumn(name="CLIENTE_ID", nullable = false)
    private Cliente cliente;
	
}
