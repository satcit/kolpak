package ru.satcit.kolpak.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * User: Aleksei
 * Date: 26.01.16 13:47
 */
@Service
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class PersonManager {
  @Autowired
  private DataBase dataBase;

  public Person findById(long id) {
    return dataBase.getManager().find(Person.class, id);
  }

  public List<Person> findByCriteria() {
    TypedQuery<Person> q = dataBase.getManager().createQuery("SELECT p from Person p", Person.class);
    return q.getResultList();
  }

  public void createPerson(Person person) {
    dataBase.getManager().getTransaction().begin();
    dataBase.getManager().persist(person);
    dataBase.getManager().getTransaction().commit();
  }

  public void createPersonComment(PersonComment comment) {
    dataBase.getManager().getTransaction().begin();
    dataBase.getManager().persist(comment);
    dataBase.getManager().getTransaction().commit();
  }
}
