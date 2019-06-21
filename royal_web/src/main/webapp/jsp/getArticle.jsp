<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>黑马程序员论坛详情页</title>
    <link rel="stylesheet" type="text/css" href="../css/common.css"/>
    <link rel="stylesheet" type="text/css" href="../css/common-new.css"/>
    <link rel="stylesheet" type="text/css" href="../css/index.css"/>
    <link rel="stylesheet" type="text/css" href="../css/search.css"/>
    <link rel="stylesheet" type="text/css" href="../css/detail.css"/>
    <link rel="stylesheet" type="text/css" href="../css/getArticle.css"/>
    <script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="../js/hm-bbs.js"></script>
    <%--<script >
        $(function () {
            $.post("/article/getUpvoteCount.do",{"articleid":},function(result){
                $("#upvoteCount").html("<a href='#'><i></i>"+result+"</a>");
            });
        })
    </script>--%>
</head>
<body>

<!-- 头部 -->
<div class="hm-top-nav">
    <div class="hm-inner clearfix">
        <div class="hm-inner-l l">
        </div>
        <div class="hm-inner-r r">
            <div class="box">
                欢迎<a href="user_info.html" style="margin-right:0px;padding:0px 5px;color:blue;">scott</a>回来！
                <a href="#">【注销】</a>
                <div id="dialogBg"></div>
                <div id="dialog" class="animated">
                    <img class="dialogIco" width="50" height="40" src="../images/ico.png"/>
                    <div class="dialogTop" style="height:25px;">
                        <a href="javascript:;" class="closeDialogBtn">关闭</a>
                    </div>
                    <form action="" method="post">
                        <ul class="editInfos">
                            <li>用户名：<input type="text" id="userName" name="userName" class="ipt"/></li>
                            <li>密&nbsp;&nbsp;&nbsp;码：<input type="password" id="userPass" name="userPass" class="ipt"/></li>
                            <li><input type="submit" value="登录" class="submitBtn"/></li>
                        </ul>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>



<div class="hm-header"></div>
<div class="hm-body hm-body-bgc">
    <div class="hm-inner">

        <!--帖子标题，点赞数，回复数，搜索-->
        <div class="hm-bbs-info">
            <div class="hm-bbs-icon l" style="width:130px;">
                <span><img src="../images/bbs-icon.png" height="80"/></span>
            </div>
            <div class="hm-bbs-info-in l" style="margin-left:30px;">
                <div class="t clearfix">
                    <h2 class="l">${article.title}</h2>
                    <div class="hm-detail-fun l">
					     <span class="icon-like" id="upvoteCount">
                            <a href='#'><i></i>${upvoteCount}</a>
					     </span>
                        <span class="icon-talk">
						     <i></i>10
						</span>
                    </div>
                </div>
            </div>
            <div class="search-box l">
                <form action="javascript:;">
                    <input type="text" class="txt l" placeholder="请输入关键字">
                    <input type="button" value="搜索" class="btn l"/>
                </form>
            </div>
        </div>


        <!--导航，回首页，帖子标题，排序-->
        <div class="detail-page-box clearfix">
            <a href="/index.jsp">
                <i class="hm-ico-home"></i>首页
            </a>
            <span>></span>
            <a href="#">${article.title}</a>
            <a class="new-to-old r" href="" style="font-size:12px;float: right;">
                <i></i>从新到旧查看
            </a>
        </div>


        <div class="detail-box">
            <ul class="detail-floors">

                <!--原帖楼-->
                <li class="floor clearfix">
                    <div class="floorer-info l">
                        <div class="floorer-photo"><img src="../images/default.png"/></div>
                        <div class="floorer-name">${article.sendername}</div>
                    </div>
                    <div class="floor-con l">
                        <div class="floor-info clearfix">
                            <div class="floor-time l">发帖时间：${article.sendtimestr}</div>
                            <div class="r">${article.browsecount}</div>
                        </div>
                        <div class="floor-art-ans">
                            <div class="floor-art">
                                <p>${article.content}</p>
                            </div>
                            <div class="floor-ans"></div>
                        </div>
                        <span class="icon-comment">
                            <a href="#comment"> <i></i> 评论</a>
                            <a href="javascript:;" onclick="upvote(${article.articleid})"> <i id="id1"></i> 点赞</a>
                             <a href="javascript:;" onclick="showDialog(1);getCommentId(${comment.commentid})"> <i id="id2"></i> 举报</a>
                        </span>



                    </div>
                </li>

                <c:forEach items="${commentList}" var="comment"  step="1" varStatus="i">
                <!-- 评论部分,一楼及以后 -->
                <li class="floor clearfix">
                    <div class="floorer-info l">
                        <div class="floorer-photo"><img src="../images/default.png"/></div>
                        <div class="floorer-name">${comment.commentusername}</div>
                    </div>
                    <div class="floor-con l">
                        <div class="floor-info clearfix">
                            <div class="floor-time l">回贴时间：${comment.commenttimestr}</div>
                            <div class="r">${i.index+1}楼</div>
                        </div>
                        <div class="floor-art-ans">
                            <div class="floor-art">
                                <p>${comment.commentcontent}</p>
                            </div>
                            <div class="floor-ans">
                                <ul>
                                    <c:forEach var="reply" items="${replyMap}" >
                                        <c:if test="${reply.key == comment.commentid}">
                                        <c:forEach items="${reply.value}" var="rep">

                                    <!-- 回复部分,楼中楼 -->
                                    <li class="clearfix">
                                        <div class="floor-ans-pho l"><img src="../images/default.png"/></div>
                                        <div class="floor-ans-con l">
                                            <span class="name">${rep.replyusername}</span>：${rep.replycontent}
                                            <span class="ans-time">${rep.replytimestr}</span>
                                        </div>
                                    </li>

                                        </c:forEach>
                                        </c:if>
                                    </c:forEach>

                                </ul>
                            </div>
                            <span class="icon-feedback">
                                <a href="javascript:;" onclick="showDialog(1);getCommentId(${comment.commentid})"> <i id="id3"></i> 回复</a>
                            </span>

                        </div>
                    </div>
                </li>
                </c:forEach>
<%--                <!--二楼-->
                <li class="floor clearfix">
                    <div class="floorer-info l">
                        <div class="floorer-photo"><img src="../images/default.png"/> </div>
                        <div class="floorer-name">不哭不闹不炫耀</div>
                    </div>
                    <div class="floor-con l">
                        <div class="floor-info clearfix">
                            <div class="floor-time l">回贴时间：2017-05-24 10:10:00</div>
                            <div class="r">2楼</div>
                        </div>
                        <div class="floor-art-ans">
                            <div class="floor-art">
                                <p>楼主你好，请按以下建议反馈格式回复，我们会有专人进行收集。祝你游戏愉快。</p>
                            </div>
                            <div class="floor-ans">
                                <ul>

                                    <!-- 回复部分 -->
                                    <li class="clearfix">
                                        <div class="floor-ans-pho l"><img src="../images/default.png"/></div>
                                        <div class="floor-ans-con l">
                                            <span class="name">张无忌</span>：顶顶顶！
                                            <span class="ans-time">2017-05-24 10:11:00</span>
                                        </div>
                                    </li>

                                </ul>
                            </div>
                            <span class="icon-feedback">
                                <a href="javascript:;" onclick="showDialog(2)"> <i></i> 回复</a>
                            </span>
                        </div>
                    </div>
                </li>


                <!--三楼-->
                <li class="floor clearfix">
                    <div class="floorer-info l">
                        <div class="floorer-photo"><img src="../images/default.png"/></div>
                        <div class="floorer-name">不哭不闹不炫耀</div>
                    </div>
                    <div class="floor-con l">
                        <div class="floor-info clearfix">
                            <div class="floor-time l">回贴时间：2017-05-24 10:10:00</div>
                            <div class="r">3楼</div>
                        </div>
                        <div class="floor-art-ans">
                            <div class="floor-art">
                                <p>楼主你好，请按以下建议反馈格式回复，我们会有专人进行收集。祝你游戏愉快。</p>
                            </div>
                            <div class="floor-ans">
                                <ul>

                                    <!-- 回复部分 -->
                                    <li class="clearfix">
                                        <div class="floor-ans-pho l"><img src="../images/default.png"/></div>
                                        <div class="floor-ans-con l">
                                            <span class="name">张无忌</span>：顶顶顶！
                                            <span class="ans-time">2017-05-24 10:11:00</span>
                                        </div>
                                    </li>

                                </ul>
                            </div>
                            <span class="icon-feedback">
                                <a href="javascript:;" onclick="showDialog(3)"> <i></i> 回复</a>
                            </span>
                        </div>
                    </div>
                </li>--%>


            </ul>
        </div>

        <!--发表评论-->
        <div class="detail-to-comment">
            <div class="tit"><a name="comment">发表评论</a></div>
            <!-- 未登录时候显示 <div class="con">您没有登录论坛，请登录后再进行回复</div>-->

            <!-- 登录后显示评论输入框-->
            <form action="/comment/add.do" method="post" id="comment_form">
                <div class="con con-loged">
                    <div class="con-t">
                        <input type="hidden" name="articleid" value="${article.articleid}">
                        <textarea id="content" name="commentcontent" required="required" placeholder="请在此输入您要回复的信息"></textarea>
                    </div>
                    <div class="con-b">
                        <%--<input type="button" class="btn" id="btn_commentSub" value="提交"/> --%>
                        <input type="submit" class="btn"/>
                        <span class="num">不能超过5000字</span>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>



<!-- 底部 -->
<div class="hm-footer" style="padding-top:10px;">
    <div class="hm-inner">
        <div class="hm-footer-cpr">
            <p>Copyright@2006-2017 ITCAST. All Rights Reserved</p>
            <p>传智播客 版权所有</p>
        </div>
    </div>
</div>



<!-- 回复弹出框 -->
<form action="/reply/add.do" method="post">
    <div class="pop-box ft-box">
        <div class="mask"></div>
        <div class="win">
            <div class="win_hd">
                <h4 class="l">回复<span id="floorSpan"></span>楼</h4>
                <span class="close r">&times;</span>
            </div>
            <div class="win_bd">
                <div class="win_bd_b">
                    <input type="hidden" value="" name="commentid" id="commentid">
                    <textarea id="replyContent" name="replycontent" placeholder="回复内容限于40字以内"></textarea>
                </div>
            </div>
            <div class="win_ft">
                <div class="win_ft_in">
                    <input type="submit" class="btn" value="回复"/>
                </div>
            </div>
        </div>
    </div>
</form>



<div class="fixedBar" id="j_fixedBar">
    <a href="#comment" class="newTopic"><span></span>回复</a>
    <a href="#" class="goTop"><i></i><span>返回<br/>顶部</span></a>
</div>


</body>

<script type="text/javascript">
//弹出回复框
function showDialog(num) {
    $('.pop-box').css('display', 'block');
    $("#floorSpan").html(num);
}
</script>
<script>
    function getCommentId(id){
        $("#commentid").val(id);
    }
    var i = 0;
    function upvote(articleid) {
        i = i + 1;
        if ( i % 2 == 1){
            $("#id1").css("background-position","-112px -32px");
        }else {
            $("#id1").css("background-position","-0px -0px");
        }
        $.post("/article/upvoteChange.do",{"i":i,"articleid":articleid},function(result){
           /* $("#totalCount").html("全部帖子<strong>"+result+"</strong>")*/
            $("#upvoteCount").html("<a href='#'><i></i>"+result+"</a>");
        });
    }
</script>

</html>