package ru.satcit.kolpak.model;

import org.springframework.beans.factory.annotation.Autowired;
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
@Scope("singleton")
public class RecordManager implements Serializable {
  @Autowired private DataBase dataBase;

  public <T> T findById(Class<T> clazz, long id) {
    return dataBase.getManager().find(clazz, id);
  }

  public <T> List<T> findByCriteria(Class<T> clazz) {
    TypedQuery<T> q = dataBase.getManager().createQuery("SELECT o from " + clazz.getSimpleName() + " o", clazz);
    return q.getResultList();
  }

  public void createUpdateEntity(HaveId entity) {
    dataBase.getManager().getTransaction().begin();
    // entity created but detached
    if(entity.getId() != 0 && !dataBase.getManager().contains(entity)) {
      dataBase.getManager().merge(entity);
    } else {
      dataBase.getManager().persist(entity);
    }
    dataBase.getManager().getTransaction().commit();
  }
}
