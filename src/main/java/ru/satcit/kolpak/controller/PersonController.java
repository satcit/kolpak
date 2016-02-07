package ru.satcit.kolpak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.satcit.kolpak.model.ArticleComment;
import ru.satcit.kolpak.model.Person;
import ru.satcit.kolpak.model.PersonComment;
import ru.satcit.kolpak.model.PersonManager;

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
  private PersonManager manager;

  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView listPersons() {
    List<Person> persons = manager.findByCriteria();
    return new ModelAndView("persons", "persons", persons);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ModelAndView viewPerson(@PathVariable long id) {
    Person person = manager.findById(id);
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
    return new ModelAndView("createPerson", "person", new Person());
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public String createPerson(@ModelAttribute("person") Person person) {
    manager.createPerson(person);
    return "redirect:/client/persons";
  }

  @RequestMapping(value = "/{id}/comments/create", method = RequestMethod.POST)
  public String createComment(@ModelAttribute("comment") PersonComment comment, @PathVariable long id) {
    Person person = manager.findById(id);
    comment.setDate(new Date());
    comment.setPerson(person);
    person.getComments().add(comment);
    Collections.sort(person.getComments());
    manager.createPersonComment(comment);
    return "redirect:/client/persons/" + Long.toString(id);
  }

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    // TODO fix binders
    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
  }
}
