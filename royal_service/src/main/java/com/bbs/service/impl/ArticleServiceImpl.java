package com.bbs.service.impl;

import com.bbs.dao.ArticleDao;
import com.bbs.domain.Article;
import com.bbs.domain.ArticleExample;
import com.bbs.service.IArticleService;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public List<Article> findByPage(Article article) {
        ArticleExample articleExample = new ArticleExample();
        ArticleExample.Criteria criteria = articleExample.createCriteria();
        if (StringUtil.isNotEmpty(article.getTitle()))
            criteria.andTitleLike("%"+article.getTitle().trim()+"%");
        if (StringUtil.isNotEmpty(article.getSendername()))
            criteria.andSendernameLike("%"+article.getSendername().trim()+"%");
        articleExample.setOrderByClause("isTop desc");
        articleExample.setOrderByClause("sendTime desc");
        articleExample.setOrderByClause("upvoteCount desc");
        articleExample.setOrderByClause("browseCount desc");
        List<Article> articleList = articleDao.selectByExample(articleExample);
        return articleList;
    }
}
