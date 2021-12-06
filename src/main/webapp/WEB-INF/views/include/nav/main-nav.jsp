<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

				<c:if test="${empty golden}">
					<div class="mypage"><i class="fas fa-bookmark"></i>${project.proName}</div>
				</c:if>
				<c:if test="${not empty golden}">
				 <div class="mypage" style="color: #ffce3d"><i class="fas fa-star" style="color: #ffce3d"></i>${project.proName}</div>
				</c:if>
                
                <div class="search">
                    <div class="find"><i class="fas fa-search"></i>검색</div>
                    <div class="setting"><i class="fas fa-cog"></i>프로젝트 관리</div>
                    <div class="projectProfile"><i class="fas fa-user-cog"></i>프로필 관리</div>
                </div>
                <div class="worktit">워크스페이스</div>
                <div class="workspace">
               
                <c:forEach var="ws" items="${workspaceList}" varStatus="i">

                    <ul class="folder">
                    	<li class="cate${i.index+1}" id="${ws.wsIdx}" data-type="${ws.wsType}">
                    	<c:choose>
                    		<c:when test="${ws.wsType == 'ME'}">
                    		<i class="far fa-sticky-note" id="folderclose"></i>${ws.wsName}
                    		</c:when>
                    		<c:when test="${ws.wsType == 'LD'}">
                    		<i class="fas fa-map-signs" id="folderclose"></i>${ws.wsName}
                    		</c:when>
                    		<c:when test="${ws.wsType == 'CH'}">
                    		<i class="far fa-comments" id="folderclose"></i>${ws.wsName}
                    		</c:when>
                    		<c:when test="${ws.wsType == 'CL'}">
                    		<i class="far fa-calendar-alt" id="folderclose"></i>${ws.wsName}
                    		</c:when>
                    		<c:otherwise>
                    			<i class="far fa-clipboard" id="folderclose"></i>${ws.wsName}
                    		</c:otherwise>
                    	</c:choose>
                        	
                        </li>
                    
                    </ul>
                </c:forEach>
                </div>
                <div class="private">
                			<div class="cash"><i class="fas fa-dollar-sign"></i>결제</div>
                            <div class="reload" onClick="window.location.reload()"><i class="fas fa-sync"></i>동기화</div>
                </div>
                
                
                <script>
              
        		document.querySelector(".projectProfile").addEventListener('click', e=>{
        			location.href = '/project/project-profile/${projectIdx}'; 
        		})
        	
                
                $('.mypage').on('click' , function() {
                	location.href="/project/${projectIdx}";
                	
                	
                })
                
                $('.search').find('.setting').on('click' , function() {
                	location.href="/project/setting/project-setting/${projectIdx}";
                })
                
                 $('.private').find('.cash').on('click' , function() {
                	location.href="/cash/${projectIdx}";
                })
                
                $('.folder').each(function() {
                	
                	$(this).on('click' , function() {
                		let cat = $(this).find('li');
                		let type = cat.data('type');
                		let wsIdx = cat.attr('id');
             			let loca = "/${projectIdx}?wsIdx=" + wsIdx;
             			
                		switch(type) {
                		case "BO" :
                			location.href= "/board" + loca ;
                    		break;
                		case "ME" :
                			location.href="/memo" + loca + "&order=0" ;
                			break;
                		case "CL" :
                			location.href= "/calendar" + loca ;
                			break;
                		case "LD" :
                			location.href= "/loadmap" + loca ;
                			break;
                		case "CH" :
                			location.href= "/chat/chatting" + loca ;
                			break;
                			
                	
                		}
                		
                		
                		
                	})
                	
                	
                	
                })
                
                </script>
            