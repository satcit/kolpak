package ru.satcit.kolpak.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * User: Aleksei
 * Date: 26.01.16 13:23
 */
@Entity
@Table(name = "person_comment")
public class PersonComment extends AbstractComment {
  @ManyToOne
  @JoinColumn(name = "person_id", nullable = false)
  private Person person;

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }
}
