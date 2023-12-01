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
@Table(name="Vuelos")
public class Vuelo {
	@Id
	@Column(name = "ID_VUELO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqVuelo")
    @SequenceGenerator(name = "seqVuelo", allocationSize = 1, sequenceName = "SEQ_VUELO")
    @Builder.Default
    private Long id=0L;
	
	@Column(name = "FECHA_SALIDA")
	@NotNull     
    private String fechasalida;
	
	@Column(name = "HORA_SALIDA")
	@NotNull     
    private String hsalida;
	
	@Column(name = "FECHA_LLEGADA")
	@NotNull     
    private String fechallegada;
	
	@Column(name = "HORA_LLEGADA")
	@NotNull     
    private String hllegada;
	
	@Column(name = "ORIGEN")
	@NotNull     
    private String origen;
	
	@Column(name = "DESTINO")
	@NotNull     
    private String destino;
	
	@Column(name = "N° DE PLAZAS")
	@NotNull     
    private Integer nplazas;
		
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vuelo")
	@JsonIgnore
	private Set<Reserva> reservas;
}
