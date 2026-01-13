package ar.edu.ubp.das.perukai.services;

import ar.edu.ubp.das.perukai.beans.ClicksContenidosRestaurantesBean;
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
}