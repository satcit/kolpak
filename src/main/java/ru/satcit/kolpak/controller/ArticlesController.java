package ru.satcit.kolpak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.satcit.kolpak.model.Article;
import ru.satcit.kolpak.model.ArticleManager;

import java.util.List;

/**
 * User: Aleksei
 * Date: 22.01.16 13:29
 */
@Controller
@Scope("request")
@RequestMapping("/articles")
public class ArticlesController {
  @Autowired
  private ArticleManager manager;

  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView processArticles() {
    List<Article> articles = manager.findByCriteria();
    return new ModelAndView("articles", "articles", articles);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ModelAndView processArticle(@PathVariable long id) {
    Article article = manager.findById(id);
    if(article != null) {
      ModelAndView view = new ModelAndView("article");
      view.addObject("title", article.getName());
      view.addObject("article", article);
      return view;
    }
    return new ModelAndView("notFound");
  }
}
