package ru.satcit.kolpak.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.satcit.kolpak.model.Person;
import ru.satcit.kolpak.model.PersonManager;

import java.beans.PropertyEditorSupport;

/**
 * User: Aleksei
 * Date: 26.01.16 17:09
 */
@Component
public class PersonEditor extends PropertyEditorSupport {
  @Autowired private PersonManager manager;

  @Override
  public void setAsText(String text) throws IllegalArgumentException {
    setValue(manager.findById(Long.parseLong(text)));
  }

  @Override
  public String getAsText() {
    return Long.toString(((Person) getValue()).getId());
  }
}
