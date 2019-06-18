package com.bbs.controller;

import com.bbs.domain.Article;
import com.bbs.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * Created by geekhoon on 2019/6/18.
 */
@Controller
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;


    /**
     * 发帖
     * @param article
     * @return
     */
    @RequestMapping("/add")
    public String addArticle(Article article){

        article.setSendtime(new Date());
        article.setSendername("geekhoon");
        article.setIstop(0);
        article.setReplycount(0);
        article.setUpvotecount(0);
        article.setBrowsecount(0);
        article.setZoneid(1);
        articleService.addArticle(article);

        return "getArticle";
    }

}
