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
@Table(name="SUCURSALES")
public class Sucursal {
	@Id
	@Column(name = "ID_SUCURSAL")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqSucursal")
    @SequenceGenerator(name = "seqSucursal", allocationSize = 1, sequenceName = "SEQ_SUCURSAL")
    @Builder.Default
    private Long id=0L;
	
	@Column(name = "DIRECCION")
	@NotNull     
    private String direccion;
	
	@Column(name = "LOCALIDAD")
	@NotNull     
    private String localidad;
	
	@Column(name = "PROVINCIA")
	@NotNull     
    private String provincia;
	
	@Column(name = "TELEFONO")
	@NotNull     
    private Integer telefono;
		
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sucursal")
	@JsonIgnore
	private Set<Reserva> reservas;
}
