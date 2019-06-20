package com.bbs.service.impl;

import com.bbs.dao.ReplyDao;
import com.bbs.domain.Reply;
import com.bbs.service.IReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by geekhoon on 2019/6/19.
 */
@Service
@Transactional
public class ReplyServiceImpl implements IReplyService {

    @Autowired
    private ReplyDao replyDao;
    @Override
    public void addReply(Reply reply) {
        replyDao.insert(reply);
    }

    @Override
    public List<Reply> findReplyList(Integer commentid) {
        return replyDao.findAll(commentid);
    }
}
