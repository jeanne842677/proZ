<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">


<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
    <link type="text/css" rel="stylesheet" href="resources/css/modal/modal.css"/>
    <link type="text/css" rel="stylesheet" href="resources/css/setting/member_management.css"/>
    <script type="text/javascript" src="resources/js/modal/modal.js"></script>
</head>
<body>

    <div class="wrap">
        <header>
        




        </header>

        <div class="con">
        <nav></nav>
        <section>
            <!--ì¬ê¸°ìë§ ìì-->
            
            <div class="member-manage-top">
                <div class="title-img"><img src="resource/img/premium-icon-people-5322431.png"></div>
                <div class="title">ë©¤ë²ê´ë¦¬</div>
            </div>
            <div class="member-manage-main">
                <div class="invite-link">
                    <div>
                        <p class="category-title">ì´ëë§í¬</p>
                        <p style="color :slategray;">ì´ë ë§í¬ë¥¼ ê³µì íì¬ ì´ ìí¬ì¤íì´ì¤ë¡ ì¬ëë¤ì ì´ëíì¸ì.</p>
                        <div class="form-group">
                              <input class="form-control" id="link-input" type="text" value="https://www.notion.so/invite/ca8302610cc4da6ed479247cfd0715046b4bc540" readonly>
                              <div class="button-wrapper">
                                <button type="button" class="btn btn-gradi1 copy-url">ë§í¬ ë³µì¬</button>
                                
                              </div>
                          </div>
                          <hr style="margin: 50px 0 50px 0;">
                    </div>
                    <div>
                        <p class="category-title">íë¡ì í¸ ì´ëíê¸°</p>
                        <div class="form-group">
                            <input class="form-control" id="invite-member-input" type="text" placeholder="ëë¤ì#0000 ëë ì´ë©ì¼ ìë ¥" >
                            <div class="button-wrapper">
                              <button type="button" class="btn btn-gradi1 insert-member">ë©¤ë² ì¶ê°</button>
                              
                            </div>
                        </div>
                    </div>
                    


                    <div class="member-list-wrapper">
                        <div class="search-member-wrapper">
                            <h4 style="font-weight: bolder; margin : 2px 10px 0 0;">ë©¤ë²</h4> <input class="form-control" id="search-member" type="text" placeholder="ê²ìíê¸°" >
                            <a href=""><img class="search-icon" src="resource/img/search.png"></a>
                            <div class="category-member">
                                <div> </div>
                                <div><p>ì­í </p></div>
                                <div><p>ê°ì ì¶ë°©</p></div>
                            </div>
                        </div>

                        <hr style="margin: 20px 0 20px 0;">

                        <div class="member">
                            <div class="member-img-wrapper">
                                <img class="search-icon" src="resource/img/profile-user.png">
                            </div>
                            <div class="member-profile">
                                <p class="user-name">ê°ë¯¼í</p>
                                <p class="user-email">kkmh7511@naver.com</p>
                            </div>
                            <div style="width: 200px;"> </div>
                            <div class="category-member-info">
                                <div> </div>
                                <div>    
                                    <select class="role">
                                        <option>íì¥</option>
                                        <option>íì</option>
                                        <option>ê²ì¤í¸</option>
                                    </select>
                                </div>
                                <div class="exile-button">
                                    <button type="button" class="btn btn btn-danger exile">ì¶ë°©íê¸°</button>
                                </div>
                            </div>

                            
                        </div>
                        <hr style="margin: 20px 0 20px 0;">
                        <div class="member">
                            <div class="member-img-wrapper">
                                <img class="search-icon" src="resource/img/profile-user.png">
                            </div>
                            <div class="member-profile">
                                <p class="user-name">ê°ë¯¼í</p>
                                <p class="user-email">kkmh7511@naver.com</p>
                            </div>
                            <div style="width: 200px;"> </div>
                            <div class="category-member-info">
                                <div> </div>
                                <div>    
                                    <select class="role">
                                        <option>íì¥</option>
                                        <option>íì</option>
                                        <option>ê²ì¤í¸</option>
                                    </select>
                                </div>
                                <div class="exile-button">
                                    <button type="button" class="btn btn btn-danger exile">ì¶ë°©íê¸°</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="role-list-wrapper-bottom">
                    </div>
                </div>

                
            </div>
           

        </section>
        <aside></aside>
        </div>

    </div>
    


</body>
<script type="text/javascript" src="resources/js/setting/member_management.js"></script>
</html>