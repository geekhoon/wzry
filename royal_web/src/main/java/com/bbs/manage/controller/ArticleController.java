package com.bbs.manage.controller;

import com.bbs.domain.Article;
import com.bbs.domain.Comment;
import com.bbs.domain.Reply;
import com.bbs.domain.User;
import com.bbs.service.IArticleService;
import com.bbs.service.ICommentService;
import com.bbs.service.IReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by geekhoon on 2019/6/18.
 */
@Controller
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;
    @Autowired
    private ICommentService commentService;
    @Autowired
    private IReplyService replyService;

    /**
     * 发帖
     * @param article
     * @return
     */
    @RequestMapping("/add")
    public void addArticle(HttpServletRequest request, HttpServletResponse response, Article article) throws IOException {
        User user = (User)request.getSession().getAttribute("user");
        article.setSendtime(new Date());
        if (user == null){

        }else{
            article.setSendername(user.getUsername());
        }
        article.setIstop(0);
        article.setReplycount(0);
        article.setUpvotecount(0);
        article.setBrowsecount(0);
        article.setZoneid(1);
        Integer articleid =  articleService.addArticle(article);
        response.sendRedirect(request.getContextPath()+"/article/getArticle.do?articleid="+articleid);
    }

    @RequestMapping("/getTotalCount")
    @ResponseBody
    public Integer getTotalCount(){
        Integer totalCount = articleService.getTotalCount();
        return totalCount;
    }

    @RequestMapping("/getTodayCount")
    @ResponseBody
    public Integer getTodayCount(){
        Integer todayCount = articleService.getTodayCount();
        return todayCount;
    }

    @RequestMapping("/upvoteChange")
    @ResponseBody
    public Integer upvoteChange(Integer i,Integer articleid,HttpServletRequest request) throws IOException {
        if (i % 2 == 1){
            articleService.upvoteChange(1,articleid);
        }else {
            articleService.upvoteChange(-1,articleid);
        }
        Integer upvoteCount = articleService.findUpvoteCount(articleid);

        return upvoteCount;

    }
    @RequestMapping("/replyChange")
    @ResponseBody
    public Integer replyChange(Integer articleid,HttpServletRequest request) throws IOException {

        articleService.replyChange(articleid);
        Integer replyCount = articleService.findReplyCount(articleid);
        return replyCount;

    }





    @RequestMapping("/getArticleList")
    public String getArticleList(Model model){
        List<Article> list = articleService.getArticleList();
        model.addAttribute("articleList",list);

        return "index";
    }

    @RequestMapping("/getArticle")
    public String getArticle(Integer articleid,HttpServletRequest request, HttpServletResponse response) throws IOException {
        Article article = articleService.getArticle(articleid);
        request.getSession().setAttribute("article",article);
        List<Comment> list = commentService.findCommentList(articleid);
        request.getSession().setAttribute("commentList",list);
        HashMap map = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            Comment comment = list.get(i);


            List<Reply> replyList = replyService.findReplyList(comment.getCommentid());
            map.put(comment.getCommentid(),replyList);
        }

        request.getSession().setAttribute("replyMap",map);

        return "getArticle";
    }

}
