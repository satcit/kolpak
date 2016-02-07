package ru.satcit.kolpak.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: Aleksei
 * Date: 26.01.16 13:17
 */
@Entity
@Table(name = "person")
public class Person implements Commented, Comparable<Person> {
  @Id
  @GeneratedValue
  @Column(name = "id")
  private long id;

  @Column(name = "name")
  private String name;

  @Column(name = "surname")
  private String surname;

  @Column(name = "birth_date")
  //TODO make date format work
//  @DateTimeFormat(pattern = "dd/MM/yyyy")
  private Date birthDate;

  @Column(name = "description")
  private String description;

  @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
  private List<PersonComment> comments = new ArrayList<>();

  @ManyToMany(mappedBy = "authors")
  private List<Article> articles = new ArrayList<>();

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public List<PersonComment> getComments() {
    return comments;
  }

  public void setComments(List<PersonComment> comments) {
    this.comments = comments;
  }

  public List<Article> getArticles() {
    return articles;
  }

  public void setArticles(List<Article> articles) {
    this.articles = articles;
  }

  public String getShortName() {
    return getSurname() + " " + getName().charAt(0) + ".";
  }

  @Override
  public int compareTo(Person o) {
    int result = getSurname().compareTo(o.getSurname());
    if(result == 0) {
      result = getName().compareTo(o.getName());
    }
    return result;
  }
}
