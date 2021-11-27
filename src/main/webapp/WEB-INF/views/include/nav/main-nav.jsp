<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

                <div class="mypage"><i class="fas fa-bookmark"></i>${project.proName}</div>
                <div class="search">
                    <div class="find"><i class="fas fa-search"></i>검색</div>
                    <div class="setting"><i class="fas fa-cog"></i>프로젝트 관리</div>
                </div>
                <div class="worktit">워크스페이스</div>
                <div class="workspace">
               
                <c:forEach var="ws" items="${workspaceList}" varStatus="i">
                
                    <ul class="folder">
                    	<li class="cate${i.index+1}" id="${ws.wsIdx}" data-type="${ ws.wsType }">
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
                
                $('.mypage').on('click' , function() {
                	location.href="/project/${projectIdx}";
                	
                	
                })
                
                $('.search').find('.setting').on('click' , function() {
                	location.href="/project/setting/role-management/${projectIdx}";
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
                	
                		}
                		
                		
                		
                	})
                	
                	
                	
                })
                
                </script>
            