package ru.satcit.kolpak.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * User: Aleksei
 * Date: 25.01.16 12:29
 */
@Entity
@Table(name="article_history")
public class ArticleHistory implements HaveId, Comparable<ArticleHistory> {
  @Id
  @GeneratedValue
  @Column(name="id")
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="article_id", nullable = false)
  private Article article;

  @Enumerated(EnumType.ORDINAL)
  @Column(name="action")
  private Action action;

  @Column(name="date")
  private Date date;

  public ArticleHistory() {}

  public ArticleHistory(Article article, Action action) {
    setArticle(article);
    setAction(action);
    setDate(new Date());
  }

  @Override
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Article getArticle() {
    return article;
  }

  public void setArticle(Article article) {
    if(this.article != null && this.article.equals(article)) {
      return;
    }
    this.article = article;
    article.addHistory(this);
  }

  public Action getAction() {
    return action;
  }

  public void setAction(Action action) {
    this.action = action;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  @Override
  public int compareTo(ArticleHistory o) {
    return -1 * getDate().compareTo(o.getDate());
  }

  public static enum Action {
    OPEN,
    EDIT,
    CREATE
  }
}
