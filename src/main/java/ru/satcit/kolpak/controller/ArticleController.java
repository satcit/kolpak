package ru.satcit.kolpak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.satcit.kolpak.model.Article;
import ru.satcit.kolpak.model.ArticleComment;
import ru.satcit.kolpak.model.Person;
import ru.satcit.kolpak.model.RecordManager;
import ru.satcit.kolpak.view.ArticleBean;
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
  @Autowired private RecordManager manager;
  @Autowired private PersonEditor personEditor;

  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView processArticles() {
    List<Article> articles = manager.findByCriteria(Article.class);
    return new ModelAndView("articles", "articles", articles);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ModelAndView processArticle(@PathVariable long id) {
    Article article = manager.findById(Article.class, id);
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
    ArticleBean bean = new ArticleBean();
    bean.setAction("Create");
    ModelAndView view = new ModelAndView("createEditArticle", "articleBean", bean);
    List<Person> allAuthors = manager.findByCriteria(Person.class);
    Collections.sort(allAuthors);
    view.addObject("allAuthors", allAuthors);
    view.addObject("title", "Create article");

    return view;
  }

  @RequestMapping(value = {"/update", "/{id}/update"}, method = RequestMethod.POST)
  public String createArticle(@ModelAttribute("articleBean") ArticleBean articleBean) {
    Article article = articleBean.getArticle();
    if("Create".equals(articleBean.getAction())) {
      article.setCreationDate(new Date());
      manager.createUpdateEntity(article);
      return "redirect:/client/articles";
    } else {
      Article persistedArticle = manager.findById(Article.class, article.getId());

      //TODO make new method 'merge'?
      persistedArticle.setName(article.getName());
      persistedArticle.setDescription(article.getDescription());
      persistedArticle.updateAuthors(article.getAuthors());
      persistedArticle.setPublicationDate(article.getPublicationDate());

      manager.createUpdateEntity(persistedArticle);
      return "redirect:/client/articles/" + article.getId();
    }
  }

  @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
  public ModelAndView editArticle(@PathVariable long id) {
    Article article = manager.findById(Article.class, id);
    ArticleBean bean = new ArticleBean();
    bean.setAction("Update");
    bean.setArticle(article);
    ModelAndView view = new ModelAndView("createEditArticle", "articleBean", bean);
    List<Person> allAuthors = manager.findByCriteria(Person.class);
    Collections.sort(allAuthors);
    view.addObject("allAuthors", allAuthors);
    view.addObject("title", "Edit article");

    return view;
  }

  @RequestMapping(value = "/{id}/comments/create", method = RequestMethod.POST)
  public String createComment(@ModelAttribute("comment") ArticleComment comment, @PathVariable long id) {
    comment.setId(0);
    Article article = manager.findById(Article.class, id);
    comment.setDate(new Date());
    comment.setArticle(article);
    article.getComments().add(comment);
    manager.createUpdateEntity(comment);
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
