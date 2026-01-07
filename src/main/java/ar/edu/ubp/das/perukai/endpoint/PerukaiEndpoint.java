package ar.edu.ubp.das.perukai.endpoint;

import ar.edu.ubp.das.perukai.beans.ProvinciaBean;
import ar.edu.ubp.das.perukai.services.PerukaiWS;
import ar.edu.ubp.das.perukai.services.jaxws.ObtenerProvinciasResponse;

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
}