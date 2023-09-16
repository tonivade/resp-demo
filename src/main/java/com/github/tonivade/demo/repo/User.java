package com.github.tonivade.demo.repo;

import static java.util.Objects.requireNonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class User {
  @Column
  @Id
  private String id;
  @Column
  private String name;

  public User() { }

  public User(String id, String name) {
    setId(id);
    setName(name);
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  protected void setId(String id) {
    this.id = requireNonNull(id);
  }

  protected void setName(String name) {
    this.name = requireNonNull(name);
  }
}
