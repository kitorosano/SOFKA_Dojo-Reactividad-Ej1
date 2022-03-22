package uy.com.sofka.dojoreactividad;

import java.util.Objects;

public class Correo {
  private final Integer id;
  private final String url;
  private Boolean estado;


  public Correo(Integer id, String url, Boolean estado) {
    this.id = id;
    this.url = url;
    this.estado = estado;
  }


  public Integer getId() {
    return this.id;
  }


  public String getUrl() {
    return this.url;
  }


  public Boolean getEstado() {
    return this.estado;
  }

  public void setEstado(Boolean estado) {
    this.estado = estado;
  }


  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Correo)) {
            return false;
        }
        Correo correo = (Correo) o;
        return Objects.equals(id, correo.id) && Objects.equals(url, correo.url) && Objects.equals(estado, correo.estado);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, url, estado);
  }
  

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", url='" + getUrl() + "'" +
      ", estado='" + getEstado() + "'" +
      "}";
  }
  
}
