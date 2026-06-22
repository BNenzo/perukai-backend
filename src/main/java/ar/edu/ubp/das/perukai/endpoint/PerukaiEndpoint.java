package ar.edu.ubp.das.perukai.endpoint;

import ar.edu.ubp.das.perukai.services.PerukaiWS;
import ar.edu.ubp.das.perukai.services.jaxws.RegistrarClickContenido;
import ar.edu.ubp.das.perukai.services.jaxws.RegistrarClickContenidoResponse;
import ar.edu.ubp.das.perukai.services.jaxws.CrearReservaDesdeRistorino;
import ar.edu.ubp.das.perukai.services.jaxws.CrearReservaDesdeRistorinoResponse;
import ar.edu.ubp.das.perukai.services.jaxws.ActualizarContenidosNoPublicados;
import ar.edu.ubp.das.perukai.services.jaxws.ActualizarContenidosNoPublicadosResponse;
import ar.edu.ubp.das.perukai.services.jaxws.ObtenerContenidosNoPublicados;
import ar.edu.ubp.das.perukai.services.jaxws.ObtenerContenidosNoPublicadosResponse;
import ar.edu.ubp.das.perukai.services.jaxws.ObtenerDisponibilidadHorariaZona;
import ar.edu.ubp.das.perukai.services.jaxws.ObtenerDisponibilidadHorariaZonaResponse;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class PerukaiEndpoint {

  private static final String NAMESPACE_URI = "http://services.perukai.das.ubp.edu.ar/";
  private PerukaiWS perukaiService;

  public PerukaiEndpoint(PerukaiWS perukaiService) {
    this.perukaiService = perukaiService;
  }

  // REGISTAR CLICK DE UN CONTENIDO
  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RegistrarClickContenidoRequest")
  @ResponsePayload
  public RegistrarClickContenidoResponse insertarLocalidad(@RequestPayload RegistrarClickContenido request) {
    String body = request.getBody();
    perukaiService.registrarClickContenido(body);
    return new RegistrarClickContenidoResponse();
  }

  // OBTENER CONTENIDOS NO PUBLICADOS
  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ObtenerContenidosNoPublicadosRequest")
  @ResponsePayload
  public ObtenerContenidosNoPublicadosResponse obtenerContenidosNoPublicados(
      @RequestPayload ObtenerContenidosNoPublicados request) {
    String responseString = perukaiService.obtenerContenidosNoPublicados();
    ObtenerContenidosNoPublicadosResponse response = new ObtenerContenidosNoPublicadosResponse();
    response.setSoapStringResponse(responseString);
    return response;
  }

  // CREAR RESERVA EN UNA SUCURSAL
  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CrearReservaDesdeRistorinoRequest")
  @ResponsePayload
  public CrearReservaDesdeRistorinoResponse crearReservaDesdeRistorino(
      @RequestPayload CrearReservaDesdeRistorino request) {
    String bodyString = request.getBody();
    String codReserva = perukaiService.crearReservaDesdeRistorino(bodyString);
    CrearReservaDesdeRistorinoResponse response = new CrearReservaDesdeRistorinoResponse();
    response.setSoapStringResponse(codReserva);
    return response;
  }

  // ACTUALIZAR LOS CONTENIDOS NO PUBLICADOS A PUBLICADOS
  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ActualizarContenidosNoPublicadosRequest")
  @ResponsePayload
  public ActualizarContenidosNoPublicadosResponse actualizarContenidosNoPublicados(
      @RequestPayload ActualizarContenidosNoPublicados request) {
    String body = request.getBody();
    perukaiService.ActualizarContenidosNoPublicados(body);
    return new ActualizarContenidosNoPublicadosResponse();
  }

  // OBTENER CONTENIDOS NO PUBLICADOS
  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ObtenerDisponibilidadHorariaZonaRequest")
  @ResponsePayload
  public ObtenerDisponibilidadHorariaZonaResponse obtenerDisponibilidadHorariaZona(
      @RequestPayload ObtenerDisponibilidadHorariaZona request) {
    String body = request.getBody();
    String responseString = perukaiService.obtenerDisponibilidadHorariaZona(body);
    ObtenerDisponibilidadHorariaZonaResponse response = new ObtenerDisponibilidadHorariaZonaResponse();
    response.setSoapStringResponse(responseString);
    return response;
  }
}