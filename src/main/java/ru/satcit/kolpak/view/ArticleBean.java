package ru.satcit.kolpak.view;

import ru.satcit.kolpak.model.Article;

/**
 * User: Aleksei
 * Date: 07.02.16 16:57
 */
public class ArticleBean {
  private String action;
  private Article article;

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public Article getArticle() {
    return article;
  }

  public void setArticle(Article article) {
    this.article = article;
  }
}