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
        articleExample.setOrderByClause("isTop desc,sendTime desc,upvoteCount desc,browseCount desc");
        List<Article> articleList = articleDao.selectByExampleWithBLOBs(articleExample);
        return articleList;
    }

    @Override
    public void changeStatus(Integer id) {
        Article article = articleDao.selectByPrimaryKey(id);
        if (article.getIstop()==0){
            article.setIstop(1);
        }else{
            article.setIstop(0);
        }
        articleDao.updateByPrimaryKey(article);
    }

    @Override
    public Article findById(Integer id) {
        return articleDao.selectByPrimaryKey(id);
    }
}
