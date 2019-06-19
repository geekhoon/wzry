package com.bbs.controller;

import com.bbs.domain.Article;
import com.bbs.service.IArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    /**
     * 分页,条件查询
     * @param model
     * @param pageNum
     * @param article
     * @return
     */
    @GetMapping("/findByPage.do")
    public String findByPage(Model model, @RequestParam(required = false, defaultValue = "1", value = "pn") Integer pageNum, Article article) {
        PageHelper.startPage(pageNum, 6);
        List<Article> list = articleService.findByPage(article);
        PageInfo pageInfo = new PageInfo(list, 6);
        model.addAttribute("articleMsgs", pageInfo);
        model.addAttribute("articleSearch", article);
        return "/ArticlePage";
    }

//    /**
//     * 修改禁言状态
//     * @param id
//     * @param pageNum
//     * @param user
//     */
//    @GetMapping("/changeStatus.do")
//    public String changeStatus(@RequestParam("id") Integer id, @RequestParam(required = false, defaultValue = "1", value = "pn") Integer pageNum, User user) {
//        userService.changeStatus(id);
//        String username="";
//        if (user.getUsername()!=null){
//            username=user.getUsername();
//        }
//        if (user.getRole()!=null){
//            return "redirect:/user/findByPage.do?username="+username+"&pn="+pageNum+"&role="+user.getRole();
//        }else{
//            return "redirect:/user/findByPage.do?username="+username+"&pn="+pageNum+"&role=";
//        }
//    }

//    /**
//     * 根据用户id查询信息
//     * @param id
//     * @return
//     */
//    @ResponseBody
//    @GetMapping("/findById.do")
//    public User findById(Integer id) {
//        User user=userService.findById(id);
//        return user;
//    }



}
