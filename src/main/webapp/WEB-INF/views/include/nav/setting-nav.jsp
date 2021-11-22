<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    


                <div class="setproject"><i class="fas fa-arrow-left"></i>${ project.proName }</div>
                <div class="search">
                    <div class="pjset"><i class="fas fa-tools"></i>프로젝트 설정</div>
                    <div class="workspacemanage"><i class="fas fa-user-cog"></i>워크스페이스 설정</div>
                    <div class="rolemanage"><i class="fas fa-user-cog"></i>역할 관리</div>
                    <div class="membermanage"><i class="fas fa-users-cog"></i>멤버 관리</div>
                    <div class="chatset"><i class="fas fa-quote-right"></i>채팅설정</div>
                    <div class="cash"><i class="fas fa-dollar-sign"></i>결제</div>
                </div>
                
                
                <script>
                $('.setproject').find('i').on('click' , function() {
                	location.href="/project/${projectIdx}";
                	
                })
                
                $('.search').find('.workspacemanage').on('click' , function() {
                	location.href="/project/setting/workspace-management/${projectIdx}";
                	
                })

                $('.search').find('.rolemanage').on('click' , function() {
                	location.href="/project/setting/role-management/${projectIdx}";
                	
                })
                $('.search').find('.membermanage').on('click' , function() {
                	location.href="/project/setting/member-management/${projectIdx}";
                	
                })
                
                
                </script>
                