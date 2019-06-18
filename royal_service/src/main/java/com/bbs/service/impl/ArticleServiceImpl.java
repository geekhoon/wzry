package com.bbs.service.impl;

import com.bbs.dao.ArticleDao;
import com.bbs.domain.Article;
import com.bbs.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by geekhoon on 2019/6/18.
 */
@Service
@Transactional
public class ArticleServiceImpl implements IArticleService {


    @Autowired
    private ArticleDao articleDao;
    /**
     * 发帖
     * @param article
     */
    @Override
    public void addArticle(Article article) {
        articleDao.insert(article);
    }
}
