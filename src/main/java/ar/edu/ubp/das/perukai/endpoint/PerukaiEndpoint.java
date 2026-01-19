package ar.edu.ubp.das.perukai.endpoint;

import ar.edu.ubp.das.perukai.beans.ClicksContenidosRestaurantesBean;
import ar.edu.ubp.das.perukai.beans.ContenidoNoPublicadoBean;
import ar.edu.ubp.das.perukai.beans.ProvinciaBean;
import ar.edu.ubp.das.perukai.services.PerukaiWS;
import ar.edu.ubp.das.perukai.services.jaxws.ObtenerProvinciasResponse;
import ar.edu.ubp.das.perukai.services.jaxws.RegistrarClickContenido;
import ar.edu.ubp.das.perukai.services.jaxws.RegistrarClickContenidoResponse;
import ar.edu.ubp.das.perukai.services.jaxws.ObtenerContenidosNoPublicados;
import ar.edu.ubp.das.perukai.services.jaxws.ObtenerContenidosNoPublicadosResponse;
import ar.edu.ubp.das.perukai.services.jaxws.ObtenerProvincias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class PerukaiEndpoint {

  private static final String NAMESPACE_URI = "http://services.perukai.das.ubp.edu.ar/";
  private PerukaiWS perukaiService;

  @Autowired
  public PerukaiEndpoint(PerukaiWS perukaiService) {
    this.perukaiService = perukaiService;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ObtenerProvinciasRequest")
  @ResponsePayload
  public ObtenerProvinciasResponse obtenerProvincias(@RequestPayload ObtenerProvincias request) {
    List<ProvinciaBean> paises = perukaiService.obtenerProvincias();
    ObtenerProvinciasResponse response = new ObtenerProvinciasResponse();
    response.setProvinciasResponse(paises);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RegistrarClickContenidoRequest")
  @ResponsePayload
  public RegistrarClickContenidoResponse insertarLocalidad(@RequestPayload RegistrarClickContenido request) {
    ClicksContenidosRestaurantesBean click = request.getClicksContenidosRestaurantes();
    perukaiService.registrarClickContenido(click);
    return new RegistrarClickContenidoResponse();
  }

  // OBTENER CONTENIDOS NO PUBLICADOS
  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ObtenerContenidosNoPublicadosRequest")
  @ResponsePayload
  public ObtenerContenidosNoPublicadosResponse obtenerContenidosNoPublicados(
      @RequestPayload ObtenerContenidosNoPublicados request) {
    List<ContenidoNoPublicadoBean> contenidosNoPublicados = perukaiService.obtenerContenidosNoPublicados();
    ObtenerContenidosNoPublicadosResponse response = new ObtenerContenidosNoPublicadosResponse();
    response.setContenidosNoPublicadosResponse(contenidosNoPublicados);
    return response;
  }
}