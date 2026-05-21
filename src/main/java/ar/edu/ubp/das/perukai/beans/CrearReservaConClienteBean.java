package ar.edu.ubp.das.perukai.beans;

public class CrearReservaConClienteBean {

  // Cliente
  private Integer nroCliente;
  private String apellido;
  private String nombre;
  private String correo;
  private String telefonos;

  // Reserva
  private String codReserva;
  private String fechaReserva;
  private Integer nroRestaurante;
  private Integer nroSucursal;
  private String codZona;
  private String horaReserva;
  private Integer cantAdultos;
  private Integer cantMenores;
  private Double costoReserva;

  public CrearReservaConClienteBean() {
  }

  public Integer getNroCliente() {
    return nroCliente;
  }

  public void setNroCliente(Integer nroCliente) {
    this.nroCliente = nroCliente;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  public String getTelefonos() {
    return telefonos;
  }

  public void setTelefonos(String telefonos) {
    this.telefonos = telefonos;
  }

  public String getCodReserva() {
    return codReserva;
  }

  public void setCodReserva(String codReserva) {
    this.codReserva = codReserva;
  }

  public String getFechaReserva() {
    return fechaReserva;
  }

  public void setFechaReserva(String fechaReserva) {
    this.fechaReserva = fechaReserva;
  }

  public Integer getNroRestaurante() {
    return nroRestaurante;
  }

  public void setNroRestaurante(Integer nroRestaurante) {
    this.nroRestaurante = nroRestaurante;
  }

  public Integer getNroSucursal() {
    return nroSucursal;
  }

  public void setNroSucursal(Integer nroSucursal) {
    this.nroSucursal = nroSucursal;
  }

  public String getCodZona() {
    return codZona;
  }

  public void setCodZona(String codZona) {
    this.codZona = codZona;
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

  public Integer getCantMenores() {
    return cantMenores;
  }

  public void setCantMenores(Integer cantMenores) {
    this.cantMenores = cantMenores;
  }

  public Double getCostoReserva() {
    return costoReserva;
  }

  public void setCostoReserva(Double costoReserva) {
    this.costoReserva = costoReserva;
  }
}