package ru.satcit.kolpak.view;

import ru.satcit.kolpak.model.Person;

/**
 * User: Aleksei
 * Date: 07.02.16 17:25
 */
public class PersonBean {
  private String action;
  private Person person;

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }
}
