package ru.satcit.kolpak.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: Aleksei
 * Date: 26.01.16 13:17
 */
@Entity
@Table(name = "person")
public class Person implements Commented, Comparable<Person>, HaveId {
  @Id
  @GeneratedValue
  @Column(name = "id")
  private long id;

  @Column(name = "name")
  private String name;

  @Column(name = "surname")
  private String surname;

  @Column(name = "birth_date")
  private Date birthDate;

  @Column(name = "description")
  private String description;

  @OneToMany(mappedBy = "person")
  private List<PersonComment> comments = new ArrayList<>();

  @ManyToMany(mappedBy = "authors")
  private List<Article> articles = new ArrayList<>();

  @Override
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

  public void addArticle(Article article) {
    if(articles.contains(article)) {
      return;
    }
    articles.add(article);
    article.addAuthor(this);
  }

  public void deleteArticle(Article article) {
    if(articles.contains(article)) {
      return;
    }
    articles.remove(article);
    article.deleteAuthor(this);
  }

  public void updateArticles(List<Article> articles) {
    if(this.articles.equals(articles)) {
      return;
    }
    for(Article article : new ArrayList<>(this.articles)) {
      deleteArticle(article);
    }
    for(Article article : articles) {
      addArticle(article);
    }
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

  @Override
  public boolean equals(Object obj) {
    if(!(obj instanceof Person)) {
      return false;
    }
    Person p = (Person)obj;
    return getId() == p.getId();
  }
}
