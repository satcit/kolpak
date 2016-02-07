package ru.satcit.kolpak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.satcit.kolpak.model.Article;
import ru.satcit.kolpak.model.ArticleComment;
import ru.satcit.kolpak.model.ArticleManager;
import ru.satcit.kolpak.model.Person;
import ru.satcit.kolpak.model.PersonManager;
import ru.satcit.kolpak.view.MultipleDateEditor;
import ru.satcit.kolpak.view.PersonEditor;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * User: Aleksei
 * Date: 22.01.16 13:29
 */
@Controller
@Scope("request")
@RequestMapping("/articles")
public class ArticleController {
  @Autowired private ArticleManager articleManager;
  @Autowired private PersonManager personManager;
  @Autowired private PersonEditor personEditor;

  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView processArticles() {
    List<Article> articles = articleManager.findByCriteria();
    return new ModelAndView("articles", "articles", articles);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ModelAndView processArticle(@PathVariable long id) {
    Article article = articleManager.findById(id);
    if(article != null) {
      Collections.sort(article.getComments());
      ModelAndView view = new ModelAndView("article");
      view.addObject("article", article);
      view.addObject("comment", new ArticleComment());
      return view;
    }
    return new ModelAndView("notFound");
  }

  @RequestMapping(value = "/create", method = RequestMethod.GET)
  public ModelAndView createArticle() {
    ModelAndView view = new ModelAndView("createArticle", "article", new Article());
    List<Person> allAuthors = personManager.findByCriteria();
    Collections.sort(allAuthors);
    view.addObject("allAuthors", allAuthors);

    return view;
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public String createArticle(@ModelAttribute("article") Article article, ModelMap model) {
    article.setCreationDate(new Date());
    articleManager.createArticle(article);
    return "redirect:/client/articles";
  }

  @RequestMapping(value = "/{id}/comments/create", method = RequestMethod.POST)
  public String createComment(@ModelAttribute("comment") ArticleComment comment, @PathVariable long id) {
    Article article = articleManager.findById(id);
    comment.setDate(new Date());
    comment.setArticle(article);
    article.getComments().add(comment);
    Collections.sort(article.getComments());
    articleManager.createArticleComment(comment);
    return "redirect:/client/articles/" + Long.toString(id);
  }

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    MultipleDateEditor dateEditor = new MultipleDateEditor();
    dateEditor.setAllowEmpty(true);
    binder.registerCustomEditor(Date.class, dateEditor);
    binder.registerCustomEditor(Person.class, personEditor);
  }
}
