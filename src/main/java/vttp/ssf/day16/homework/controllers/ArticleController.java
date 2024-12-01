package vttp.ssf.day16.homework.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vttp.ssf.day16.homework.models.Article;
import vttp.ssf.day16.homework.models.SearchParams;
import vttp.ssf.day16.homework.services.NewsService;

@Controller
@RequestMapping
public class ArticleController {
    
    @Autowired
    private NewsService newsSvc;

    @GetMapping("/search")
    public ModelAndView getSearch(@RequestParam MultiValueMap<String, String> queryParams) {

        SearchParams params = new SearchParams(queryParams.getFirst("query"),queryParams.getFirst("country"), queryParams.getFirst("category"));

        List<Article> articlesList = newsSvc.searchNewsArticles(params);

        ModelAndView mav = new ModelAndView();

        mav.setViewName("results");
        mav.addObject("query", params.query());
        mav.addObject("articleslist", articlesList);

        return mav;

    }
}
