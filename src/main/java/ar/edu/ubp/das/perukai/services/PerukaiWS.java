package ar.edu.ubp.das.perukai.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ar.edu.ubp.das.perukai.beans.ActualizarContenidosNoPublicadosBean;
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
      @WebParam(name = "Body") String body) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      ClicksContenidosRestaurantesBean bodyJson = mapper.readValue(body, ClicksContenidosRestaurantesBean.class);
      localidadesRepository.registrarClickContenido(bodyJson);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Error al deserializar body", e);
    }
  }

  @WebMethod(operationName = "CrearReservaDesdeRistorino")
  @RequestWrapper(localName = "CrearReservaDesdeRistorinoRequest")
  @ResponseWrapper(localName = "CrearReservaDesdeRistorinoResponse")
  @WebResult(name = "ReservaDesdeRistorinoResponse")
  public void crearReservaDesdeRistorino(
      @WebParam(name = "Body") String body) {
    try {

      ObjectMapper mapper = new ObjectMapper();

      CrearReservaConClienteBean reservaCliente = mapper.readValue(body, CrearReservaConClienteBean.class);

      // 1) Insertar cliente
      localidadesRepository.insertarClienteDesdeRistorino(
          reservaCliente.getNroCliente(),
          reservaCliente.getApellido(),
          reservaCliente.getNombre(),
          reservaCliente.getCorreo(),
          reservaCliente.getTelefonos());

      // 2) Insertar reserva
      localidadesRepository.crearReservaSucursal(
          reservaCliente.getCodReserva(),
          reservaCliente.getNroCliente(),
          LocalDate.parse(reservaCliente.getFechaReserva()),
          reservaCliente.getNroRestaurante(),
          reservaCliente.getNroSucursal(),
          reservaCliente.getCodZona(),
          LocalTime.parse(reservaCliente.getHoraReserva()),
          reservaCliente.getCantAdultos(),
          reservaCliente.getCantMenores(),
          reservaCliente.getCostoReserva());

    } catch (JsonProcessingException e) {
      throw new RuntimeException("Error al deserializar body", e);
    }
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

  // OBTENER CONTENIDOS NO PUBLICADOS
  @WebMethod(operationName = "ObtenerContenidosNoPublicados")
  @RequestWrapper(localName = "ObtenerContenidosNoPublicadosRequest")
  @ResponseWrapper(localName = "ObtenerContenidosNoPublicadosResponse")
  @WebResult(name = "SoapStringResponse")
  public String obtenerContenidosNoPublicados() {
    List<ContenidoNoPublicadoBean> contenidos = localidadesRepository.getContenidosNoPublicados();
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.writeValueAsString(contenidos);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Error al serializar contenidos", e);
    }
  }

  // ACTUALIZAR LOS CONTENIDOS NO PUBLICADOS A PUBLICADOS
  @WebMethod(operationName = "ActualizarContenidosNoPublicados")
  @RequestWrapper(localName = "ActualizarContenidosNoPublicadosRequest")
  @ResponseWrapper(localName = "ActualizarContenidosNoPublicadosResponse")
  @WebResult(name = "ContenidoNoPublicadosResponse")
  public void ActualizarContenidosNoPublicados(
      @WebParam(name = "Body") String body) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      ActualizarContenidosNoPublicadosBean bean = mapper.readValue(body, ActualizarContenidosNoPublicadosBean.class);
      localidadesRepository.actualizarContenidoPublicado(bean);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Error al deserializar body", e);
    }
  }

}