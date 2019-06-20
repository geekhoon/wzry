package com.bbs.service;

import com.bbs.domain.Article;

import java.util.List;

/**
 * Created by geekhoon on 2019/6/18.
 */
public interface IArticleService {

    public void addArticle(Article article);

    List<Article> findByPage(Article article);

    void changeStatus(Integer id);

    Article findById(Integer id);
}
