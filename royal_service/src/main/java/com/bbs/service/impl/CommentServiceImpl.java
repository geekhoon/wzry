package com.bbs.service.impl;

import com.bbs.dao.CommentDao;
import com.bbs.domain.Comment;
import com.bbs.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by geekhoon on 2019/6/19.
 */
@Service
@Transactional
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentDao commentDao;


    /**
     * 发表评论
     * @param comment
     */
    @Override
    public void addComment(Comment comment) {
        commentDao.insert(comment);
    }

    /**
     * 查询所有评论
     * @return
     */
    @Override
    public List findCommentList(Integer articleid) {
        return commentDao.findAll(articleid);
    }
}
