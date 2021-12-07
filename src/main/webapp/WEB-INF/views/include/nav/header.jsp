<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <div class="head1"><a href="/"><img src="/resources/img/logo-white.png"></a></div>
            <div class="righthead">
            	<div class="projects" onclick="location.href='/project/project-list'">projects</div>
                <div class="alram" style="position: relative;">
		
						<div class="new" style="width:15px ; height: 17px; border-radius: 17px; background-color: red; 
						position: absolute; z-index: 10;  top:15px; right:-2px; border: solid thin white; font-size: 9pt; color: white; text-align: center;">0</div>
							
			
                    <i class="fas fa-bell bell-btn"></i>
                </div>
                <div class="profile">     
				<c:forEach items="${ projectMemberList }" var="pm">
                <c:if test="${pm.userIdx ==authentication.userIdx }">
                <img class="profile-img" 
									style="border: solid thin black; height: 40px%; width: 40px; border-radius: 30px;"
									src="/file/${pm.savePath}${pm.renameFileName}">
				</c:if>
				</c:forEach>
						</div>
            </div>
        	<script>
        		document.querySelector('.profile').addEventListener('click', e=>{
        			location.href = '/member/mypage'; 
        		})
        	</script>