package ar.edu.ubp.das.perukai.beans;

public class ActualizarReservaClienteRequestBean {

  private String fechaReserva;
  private Integer cantAdultos;
  private Integer cantMenores;
  private Integer codEstado;
  private String horaReserva;
  private String codReservaSucursal;
  private String fechaCancelacion;

  public Integer getCantMenores() {
    return cantMenores;
  }

  public void setCantMenores(Integer cantMenores) {
    this.cantMenores = cantMenores;
  }

  public Integer getCodEstado() {
    return codEstado;
  }

  public void setCodEstado(Integer codEstado) {
    this.codEstado = codEstado;
  }

  public String getFechaCancelacion() {
    return fechaCancelacion;
  }

  public void setFechaCancelacion(String fechaCancelacion) {
    this.fechaCancelacion = fechaCancelacion;
  }

  public String getCodReservaSucursal() {
    return codReservaSucursal;
  }

  public void setCodReservaSucursal(String codReservaSucursal) {
    this.codReservaSucursal = codReservaSucursal;
  }

  public String getFechaReserva() {
    return fechaReserva;
  }

  public void setFechaReserva(String fechaReserva) {
    this.fechaReserva = fechaReserva;
  }

  public String getHoraReserva() {
    return horaReserva;
  }

  public void setHoraReserva(String horaReserva) {
    this.horaReserva = horaReserva;
  }

  public Integer getCantAdultos() {
    return cantAdultos;
  }

  public void setCantAdultos(Integer cantAdultos) {
    this.cantAdultos = cantAdultos;
  }
}
