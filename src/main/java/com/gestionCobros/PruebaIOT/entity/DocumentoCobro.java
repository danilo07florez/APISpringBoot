package com.gestionCobros.PruebaIOT.entity;

import com.gestionCobros.PruebaIOT.dto.EstadoDocumento;
import jakarta.persistence.*;
import org.springframework.context.annotation.Primary;

@Entity
@Table
public class DocumentoCobro
{
  @jakarta.persistence.Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;
  @Column(unique=true)
  private String Numerodocumento;
  private String Cliente;
  private double monto;
  private String fechaEmision;
  private String fechaVencimiento;
  @Enumerated(EnumType.STRING)
  private EstadoDocumento estado;

    public DocumentoCobro() {

    }

    public DocumentoCobro(Long id) {
        Id = id;
    }

  public DocumentoCobro(Long id, String numerodocumento, String cliente, double monto, String fechaEmision, String fechaVencimiento) {
    Id = id;
    Numerodocumento = numerodocumento;
    Cliente = cliente;
    this.monto = monto;
    this.fechaEmision = fechaEmision;
    this.fechaVencimiento = fechaVencimiento;
  }

  public Long getId() {
    return Id;
  }

  public void setId(Long id) {
    Id = id;
  }

  public String getNumerodocumento() {
    return Numerodocumento;
  }

  public void setNumerodocumento(String numerodocumento) {
    Numerodocumento = numerodocumento;
  }

  public String getCliente() {
    return Cliente;
  }

  public void setCliente(String cliente) {
    Cliente = cliente;
  }

  public double getMonto() {
    return monto;
  }

  public void setMonto(double monto) {
    this.monto = monto;
  }

  public String getFechaEmision() {
    return fechaEmision;
  }

  public void setFechaEmision(String fechaEmision) {
    this.fechaEmision = fechaEmision;
  }

  public String getFechaVencimiento() {
    return fechaVencimiento;
  }

  public void setFechaVencimiento(String fechaVencimiento) {
    this.fechaVencimiento = fechaVencimiento;
  }

  public EstadoDocumento getEstado() {
    return estado;
  }

  public void setEstado(EstadoDocumento estado) {
    this.estado = estado;
  }
}
