package ru.satcit.kolpak.model;

import javax.persistence.*;

/**
 * User: Aleksei
 * Date: 07.01.16 20:59
 */
@Entity
@Table(name = "ARTICLE")
public class Article {
  @Id
  @GeneratedValue
  @Column(name = "id")
  private int id;

  @Column(name = "name")
  private String name;

  public Article() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
