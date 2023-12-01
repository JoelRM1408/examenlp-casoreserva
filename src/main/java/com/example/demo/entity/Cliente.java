package com.example.demo.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name="CLIENTES")
public class Cliente {
	@Id
	@Column(name = "ID_CLIENTE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCliente")
    @SequenceGenerator(name = "seqCliente", allocationSize = 1, sequenceName = "SEQ_CLIENTE")
    @Builder.Default
    private Long id=0L;
	
	@Column(name = "DNI")
	@NotNull     
    private Integer dni;
	
	@Column(name = "NOMBRES")
	@NotNull     
    private String nombres;
	
	@Column(name = "APELLIDOS")
	@NotNull     
    private String apellidos;
	
	@Column(name = "TELEFONO")
	@NotNull     
    private Integer telefono;
	
	@Column(name = "EMAIL")
	@NotNull     
    private String email;
		
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cliente")
	@JsonIgnore
	private Set<Reserva> reservas;
}
