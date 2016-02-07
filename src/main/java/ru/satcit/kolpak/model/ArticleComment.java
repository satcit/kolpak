package ru.satcit.kolpak.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * User: Aleksei
 * Date: 25.01.16 12:42
 */
@Entity
@Table(name="article_comment")
public class ArticleComment extends AbstractComment {
  @ManyToOne
  @JoinColumn(name = "article_id", nullable = false)
  private Article article;

  public Article getArticle() {
    return article;
  }

  public void setArticle(Article article) {
    this.article = article;
  }
}
