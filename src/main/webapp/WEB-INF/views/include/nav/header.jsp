<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <div class="head1"><a href="/"><img src="/resources/img/logo-white.png"></a></div>
            <div class="righthead">
            	<div class="projects" onclick="location.href='/project/project-list'">projects</div>
                <div class="alram">
                    <!-- <div class="badge">●</div>  -->   
                    <i class="fas fa-bell"></i>
                </div>
                <div class="profile"><div class="myprofile-photo"></div></div>
            </div>
        	<script>
        		document.querySelector('.profile').addEventListener('click', e=>{
        			location.href = '/member/mypage'; 
        		})
        	</script>