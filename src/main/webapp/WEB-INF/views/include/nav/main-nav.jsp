<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

                <div class="mypage"><i class="fas fa-bookmark"></i>3조 final</div>
                <div class="search">
                    <div class="find"><i class="fas fa-search"></i>검색</div>
                    <div class="setting"><i class="fas fa-cog"></i>프로젝트 관리</div>
                </div>
                <div class="worktit">워크스페이스</div>
                <div class="workspace">
                <c:forEach var="ws" items="${workspace}" varStatus="i">
                
                    <ul class="folder">
                    	<li class="cate1">
                        	<i class="fas fa-folder" id="folderclose"></i>${ws.wsName}
                        </li>
                    
                    </ul>
                    </c:forEach>
                </div>
                <div class="private">
                			<div class="cash"><i class="fas fa-dollar-sign"></i>결제</div>
                            <div class="reload" onClick="window.location.reload()"><i class="fas fa-sync"></i>동기화</div>
                </div>
                
                
                <script>
                
                $('.search').find('.setting').on('click' , function() {
                	location.href="/project/setting/role-management/${projectIdx}";
                })
                
                 $('.private').find('.cash').on('click' , function() {
                	location.href="/cash/${projectIdx}";
                })
                
                </script>
            