package ru.satcit.kolpak.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

/**
 * User: Aleksei
 * Date: 22.01.16 12:59
 */

@Service
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class ArticleManager implements Serializable {
  @Autowired
  private DataBase dataBase;

  public Article findById(long id) {
    return dataBase.getManager().find(Article.class, id);
  }

  public List<Article> findByCriteria() {
    TypedQuery<Article> q = dataBase.getManager().createQuery("SELECT a from Article a", Article.class);
    return q.getResultList();
  }

  public void createArticle(Article article) {
    dataBase.getManager().getTransaction().begin();
    dataBase.getManager().persist(article);
    dataBase.getManager().getTransaction().commit();
  }

  public void createArticleComment(ArticleComment comment) {
    dataBase.getManager().getTransaction().begin();
    dataBase.getManager().persist(comment);
    dataBase.getManager().getTransaction().commit();
  }
}
