package ar.edu.ubp.das.perukai.beans;

public class CrearReservaConClienteBean {

  private ClienteRestauranteBean cliente;
  private ReservaSucursalBean reserva;

  public ClienteRestauranteBean getCliente() {
    return cliente;
  }

  public void setCliente(ClienteRestauranteBean cliente) {
    this.cliente = cliente;
  }

  public ReservaSucursalBean getReserva() {
    return reserva;
  }

  public void setReserva(ReservaSucursalBean reserva) {
    this.reserva = reserva;
  }
}
