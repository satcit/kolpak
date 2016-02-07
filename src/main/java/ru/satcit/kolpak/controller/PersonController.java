package ru.satcit.kolpak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.satcit.kolpak.model.ArticleComment;
import ru.satcit.kolpak.model.Person;
import ru.satcit.kolpak.model.PersonComment;
import ru.satcit.kolpak.model.RecordManager;
import ru.satcit.kolpak.view.PersonBean;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * User: Aleksei
 * Date: 26.01.16 13:46
 */
@Controller
@Scope("request")
@RequestMapping("/persons")
public class PersonController {
  @Autowired
  private RecordManager manager;

  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView listPersons() {
    List<Person> persons = manager.findByCriteria(Person.class);
    return new ModelAndView("persons", "persons", persons);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ModelAndView viewPerson(@PathVariable long id) {
    Person person = manager.findById(Person.class, id);
    if(person != null) {
      Collections.sort(person.getComments());
      ModelAndView view = new ModelAndView("person");
      view.addObject("person", person);
      view.addObject("comment", new ArticleComment());
      return view;
    }
    return new ModelAndView("notFound");
  }

  @RequestMapping(value = "/create", method = RequestMethod.GET)
  public ModelAndView createPerson() {
    PersonBean bean = new PersonBean();
    bean.setAction("Create");
    ModelAndView view = new ModelAndView("createEditPerson", "personBean", bean);
    view.addObject("title", "Add person");
    return view;
  }

  @RequestMapping(value = {"/update", "/{id}/update"}, method = RequestMethod.POST)
  public String createPerson(@ModelAttribute("personBean") PersonBean bean) {
    Person person = bean.getPerson();
    if("Create".equals(bean.getAction())) {
      manager.createUpdateEntity(person);
      return "redirect:/client/persons";
    } else {
      Person persisted = manager.findById(Person.class, person.getId());

      //TODO make new method 'merge'?
      persisted.setName(person.getName());
      persisted.setSurname(person.getSurname());
      persisted.setDescription(person.getDescription());
      persisted.setBirthDate(person.getBirthDate());
      return "redirect:/client/persons/" + person.getId();
    }
  }

  @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
  public ModelAndView editArticle(@PathVariable long id) {
    Person person = manager.findById(Person.class, id);
    PersonBean bean = new PersonBean();
    bean.setAction("Update");
    bean.setPerson(person);
    ModelAndView view = new ModelAndView("createEditPerson", "personBean", bean);
    view.addObject("title", "Edit person");

    return view;
  }

  @RequestMapping(value = "/{id}/comments/create", method = RequestMethod.POST)
  public String createComment(@ModelAttribute("comment") PersonComment comment, @PathVariable long id) {
    Person person = manager.findById(Person.class, id);
    comment.setId(0);
    comment.setDate(new Date());
    comment.setPerson(person);
    person.getComments().add(comment);
    manager.createUpdateEntity(comment);
    return "redirect:/client/persons/" + Long.toString(id);
  }

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    // TODO fix binders
    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
  }
}
