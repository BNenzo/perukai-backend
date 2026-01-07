package ar.edu.ubp.das.perukai.repositories;

import ar.edu.ubp.das.perukai.beans.ProvinciaBean;
import ar.edu.ubp.das.perukai.components.SimpleJdbcCallFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PerukaiRepository {
  @Autowired
  private SimpleJdbcCallFactory jdbcCallFactory;

  public List<ProvinciaBean> getProvincias() {
    return jdbcCallFactory.executeQuery("sp_get_provincias", "dbo", "provincias",
        ProvinciaBean.class);
  }
}
