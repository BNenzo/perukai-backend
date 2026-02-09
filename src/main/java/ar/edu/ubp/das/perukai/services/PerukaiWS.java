package ar.edu.ubp.das.perukai.services;

import ar.edu.ubp.das.perukai.beans.ActualizarReservaClienteRequestBean;
import ar.edu.ubp.das.perukai.beans.ClicksContenidosRestaurantesBean;
import ar.edu.ubp.das.perukai.beans.ContenidoNoPublicadoBean;
import ar.edu.ubp.das.perukai.beans.CrearReservaConClienteBean;
import ar.edu.ubp.das.perukai.beans.ProvinciaBean;
import ar.edu.ubp.das.perukai.repositories.PerukaiRepository;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@WebService(serviceName = "PerukaiWS", targetNamespace = "http://services.perukai.das.ubp.edu.ar/")
public class PerukaiWS {
  @Autowired
  private PerukaiRepository localidadesRepository;

  @WebMethod(operationName = "ObtenerProvincias")
  @RequestWrapper(localName = "ObtenerProvinciasRequest")
  @ResponseWrapper(localName = "ObtenerProvinciasResponse")
  @WebResult(name = "ProvinciasResponse")
  public List<ProvinciaBean> obtenerProvincias() {
    return localidadesRepository.getProvincias();
  }

  @WebMethod(operationName = "RegistrarClickContenido")
  @RequestWrapper(localName = "RegistrarClickContenidoRequest")
  @ResponseWrapper(localName = "RegistrarClickContenidoResponse")
  public void registrarClickContenido(
      @WebParam(name = "ClicksContenidosRestaurantes") ClicksContenidosRestaurantesBean body) {
    localidadesRepository.registrarClickContenido(body);
  }

  // OBTENER CONTENIDOS NO PUBLICADOS
  @WebMethod(operationName = "ObtenerContenidosNoPublicados")
  @RequestWrapper(localName = "ObtenerContenidosNoPublicadosRequest")
  @ResponseWrapper(localName = "ObtenerContenidosNoPublicadosResponse")
  @WebResult(name = "ContenidosNoPublicadosResponse")
  public List<ContenidoNoPublicadoBean> obtenerContenidosNoPublicados() {
    return localidadesRepository.getContenidosNoPublicados();
  }

  @WebMethod(operationName = "CrearReservaDesdeRistorino")
  @RequestWrapper(localName = "CrearReservaDesdeRistorinoRequest")
  @ResponseWrapper(localName = "CrearReservaDesdeRistorinoResponse")
  @WebResult(name = "ReservaDesdeRistorinoResponse")
  public void crearReservaDesdeRistorino(
      @WebParam(name = "CrearReservaDesdeRistorinoRequest") CrearReservaConClienteBean body) {

    System.out.println(" LLEGUE ACA");
    // 1) Insertar cliente
    localidadesRepository.insertarClienteDesdeRistorino(
        body.getCliente().getNroCliente(),
        body.getCliente().getApellido(),
        body.getCliente().getNombre(),
        body.getCliente().getCorreo(),
        body.getCliente().getTelefonos());

    // 2) Insertar reserva
    localidadesRepository.crearReservaSucursal(
        body.getReserva().getCodReserva(),
        body.getReserva().getNroCliente(),
        LocalDate.parse(body.getReserva().getFechaReserva()),
        body.getReserva().getNroRestaurante(),
        body.getReserva().getNroSucursal(),
        body.getReserva().getCodZona(),
        LocalTime.parse(body.getReserva().getHoraReserva()),
        body.getReserva().getCantAdultos(),
        body.getReserva().getCantMenores(),
        body.getReserva().getCostoReserva());
  }

  // ACTUALIZAR LA RESERVA DE UN CLIENTE
  @WebMethod(operationName = "ActualizarReservaCliente")
  @RequestWrapper(localName = "ActualizarReservaClienteRequest")
  @ResponseWrapper(localName = "ActualizarReservaClienteResponse")
  @WebResult(name = "ReservaClienteResponse")
  public void actualizarReservaCliente(
      @WebParam(name = "ActualizarReservaClienteRequest") ActualizarReservaClienteRequestBean body) {
    localidadesRepository.actualizarReservaCliente(body);
  }

}