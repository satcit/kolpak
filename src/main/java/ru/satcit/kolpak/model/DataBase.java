package ru.satcit.kolpak.model;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * User: Aleksei
 * Date: 22.01.16 12:54
 */
@Service
@Scope("singleton")
public class DataBase {
  private SessionFactory factory;
  private EntityManager em = Persistence.createEntityManagerFactory("entities").createEntityManager();

  public EntityManager getManager() {
    return em;
  }
}
