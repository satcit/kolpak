package ru.satcit.kolpak.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * User: Aleksei
 * Date: 22.01.16 12:54
 */
@Service
@Scope("singleton")
public class DataBase {
  private EntityManagerFactory factory = Persistence.createEntityManagerFactory("entities");
  private EntityManager em = factory.createEntityManager();

  public EntityManager getManager() {
    return em;
  }

  @PreDestroy
  public void destroy() throws Exception {
    em.close();
    factory.close();
  }
}
