<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">


<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
    <link type="text/css" rel="stylesheet" href="resources/css/setting/role_management.css">
    <link type="text/css" rel="stylesheet" href="resources/css/modal/modal.css">
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
                <div class="title-img"><img src="resource/img/premium-icon-genius-2112729.png"></div>
                <div class="title">ì­í ê´ë¦¬</div>
            </div>


            <div class="member-manage-main">
                <div class="main-wrapper">
                        
                        <div class="save-button-wrapper">
                            <p style="color :slategray; margin : 30px 0 0 0">ë²í¼ì ëë¬ on/off ê¶íê´ë¦¬ë¥¼ ë¶ì¬í í ì¤ì ì ì ì¥íì¸ì!</p>
                            <button type="button" class="btn btn-gradi1 save" style="margin-left: 20px;" >ì¤ì  ì ì¥</button>
                        </div>

                        <div class="role-list-wrapper">

                            <div class="form-group">
                                <input class="form-control" id="new-role-input" type="text" placeholder="ì ì­í ì ìë ¥í´ì£¼ì¸ì." value="" >
                                <div class="button-wrapper">
                                  <button type="button" class="btn btn-gradi1 add-role">ì­í  ì¶ê°</button>
                                </div>
                            </div>

                            <div class="hr-wrapper">
                                <hr>
                            </div>
                            
                            <div class="role-list-content1">
                                <div id="role-wrapper">
                                    <div class="role-title-wrapper">
                                        <input class="form-control" id="role-title" type="text" value="ê´ë¦¬ì"  >
                                    </div>
                                    <div id="role">
                                        <button type="button" class="btn btn-info on"  data-bs-toggle="tooltip" data-bs-placement="top" title="íë¡ì í¸ ì ì¤ì í ììë ê¶íì ë¶ì¬í©ëë¤.">íë¡ì í¸ê¶í</button>
                                        <button type="button" class="btn btn-info on"  data-bs-toggle="tooltip" data-bs-placement="top" title="ìì/ì±íë°© ê°ì¤í ììë ê¶íì ë¶ì¬í©ëë¤.".">ìì/ì±íë°©ê°ì¤</button>
                                        <button type="button" class="btn btn-info on"  data-bs-toggle="tooltip" data-bs-placement="top" title="íë¡ì í¸ë¡ ë§´ë²ë¥¼ ì´ëí  ì ìë ê¶íì ë¶ì¬í©ëë¤.">ë§´ë² ì´ë</button>
                                        <button type="button" class="btn btn-danger delete-role">ì­ì </button>
                                    </div>
                                </div>
                                <div class="hr-wrapper">
                                    <hr>
                                </div>

                                <div id="role-wrapper">
                                    <div class="role-title-wrapper">
                                        <input class="form-control" id="role-title" type="text" value="ì¼ë°"  >
                                    </div>
                                    <div id="role">
                                        <button type="button" class="btn btn btn-secondary off"  data-bs-toggle="tooltip" data-bs-placement="top" title="íë¡ì í¸ ì ì¤ì í ììë ê¶íì ë¶ì¬í©ëë¤.">íë¡ì í¸ê¶í</button>
                                        <button type="button" class="btn btn btn-secondary off"  data-bs-toggle="tooltip" data-bs-placement="top" title="ìì/ì±íë°© ê°ì¤í ììë ê¶íì ë¶ì¬í©ëë¤.".">ìì/ì±íë°©ê°ì¤</button>
                                        <button type="button" class="btn btn btn-secondary off"  data-bs-toggle="tooltip" data-bs-placement="top" title="íë¡ì í¸ë¡ ë§´ë²ë¥¼ ì´ëí  ì ìë ê¶íì ë¶ì¬í©ëë¤.">ë§´ë² ì´ë</button>
                                        <button type="button" class="btn btn-danger delete-role">ì­ì </button>
                                    </div>
                                </div>
                            </div>
                          


                       


                        <!-- ìë¡ì´ ì­í  -->
                        <div class="role-list-content-new"  style="display: none;">
                            <div id="role-wrapper">
                                <div class="role-title-wrapper">
                                    <input class="form-control" id="role-title" type="text" value="" >
                                </div>
                                <div id="role">
                                    <button type="button" class="btn btn-info on" data-bs-toggle="tooltip" data-bs-placement="top" title="íë¡ì í¸ ì ì¤ì í ììë ê¶íì ë¶ì¬í©ëë¤.">íë¡ì í¸ê¶í</button>
                                    <button type="button" class="btn btn-info on" data-bs-toggle="tooltip" data-bs-placement="top" title="ìì/ì±íë°© ê°ì¤í ììë ê¶íì ë¶ì¬í©ëë¤.".">ìì/ì±íë°©ê°ì¤</button>
                                    <button type="button" class="btn btn-info on" data-bs-toggle="tooltip" data-bs-placement="top" title="íë¡ì í¸ë¡ ë§´ë²ë¥¼ ì´ëí  ì ìë ê¶íì ë¶ì¬í©ëë¤.">ë§´ë² ì´ë</button>
                                    <button type="button" class="btn btn-danger delete-role">ì­ì </button>
                                </div>
                            </div>
                            <div class="hr-wrapper">
                                <hr>
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

<script type="text/javascript" src="resources/js/setting/role_management.js"></script>


</html>