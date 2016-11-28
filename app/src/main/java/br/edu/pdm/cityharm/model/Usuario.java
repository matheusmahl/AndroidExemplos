package br.edu.pdm.cityharm.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by MatheusMahl on 28/11/2016.
 */

@DatabaseTable(tableName = "usuario")
public class Usuario implements Serializable {

  @DatabaseField(generatedId = true)
  private Integer codigo;
  @DatabaseField(canBeNull = false)
  private String nome;
  @DatabaseField(canBeNull = false, unique = true)
  private String login;
  @DatabaseField(canBeNull = false)
  private String senha;
  @DatabaseField(canBeNull = false)
  private String email;

  public Usuario() {

  }

  public Integer getCodigo() {
    return codigo;
  }

  public void setCodigo(Integer codigo) {
    this.codigo = codigo;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
