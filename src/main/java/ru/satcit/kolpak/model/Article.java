package ru.satcit.kolpak.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: Aleksei
 * Date: 07.01.16 20:59
 */
@Entity
@Table(name = "ARTICLE")
public class Article implements Commented, HaveId {
  @Id
  @GeneratedValue
  @Column(name = "id")
  private long id;

  @Column(name = "name")
  private String name = "";

  @Column(name = "description")
  private String description = "";

  @Column(name = "creation_date")
  @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
  private Date creationDate;

  @OneToMany(mappedBy = "article")
  private List<ArticleHistory> history = new ArrayList<>();

  @OneToMany(mappedBy = "article")
  private List<ArticleComment> comments = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "file_id")
  private File file;

  @ManyToMany
  @JoinTable(
      name="author_to_article",
      joinColumns = {@JoinColumn(name = "article_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "author_id", referencedColumnName = "id")})
  private List<Person> authors = new ArrayList<>();

  @Column(name = "journal")
  private String journal;

  @Column(name = "publication_date")
  private Date publicationDate;

  public Article() {
  }

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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public List<ArticleHistory> getHistory() {
    return history;
  }

  public void setHistory(List<ArticleHistory> history) {
    this.history = history;
  }

  public void addHistory(ArticleHistory event) {
    if(history.contains(event)) {
      return;
    }
    history.add(event);
    event.setArticle(this);
  }

  @Override
  public List<ArticleComment> getComments() {
    return comments;
  }

  public void setComments(List<ArticleComment> comments) {
    this.comments = comments;
  }

  public File getFile() {
    return file;
  }

  public void setFile(File file) {
    this.file = file;
  }

  public List<Person> getAuthors() {
    return authors;
  }

  public void setAuthors(List<Person> authors) {
    this.authors = authors;
  }

  public void updateAuthors(List<Person> authors) {
    if(this.authors.equals(authors)) {
      return;
    }
    for(Person author : new ArrayList<>(this.authors)) {
      deleteAuthor(author);
    }
    for(Person author : authors) {
      addAuthor(author);
    }
  }

  public void addAuthor(Person person) {
    if(authors.contains(person)) {
      return;
    }
    authors.add(person);
    person.addArticle(this);
  }

  public void deleteAuthor(Person person) {
    if(!authors.contains(person)) {
      return;
    }
    authors.remove(person);
    person.deleteArticle(this);
  }

  public Date getPublicationDate() {
    return publicationDate;
  }

  public void setPublicationDate(Date publicationDate) {
    this.publicationDate = publicationDate;
  }

  public String getJournal() {
    return journal;
  }

  public void setJournal(String journal) {
    this.journal = journal;
  }

  @Override
  public boolean equals(Object obj) {
    if(!(obj instanceof Article)) {
      return false;
    }
    Article p = (Article)obj;
    return getId() == p.getId();
  }
}
