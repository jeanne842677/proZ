<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
                <div class="mypage"><i class="fas fa-bookmark"></i>3조 final</div>
                <div class="search">
                    <div class="find"><i class="fas fa-search"></i>검색</div>
                    <div class="setting"><i class="fas fa-cog"></i>프로젝트 관리</div>
                </div>
                <div class="worktit">워크스페이스</div>
                <div class="workspace">
                    <ul class="folder"><div class="cate1"><i class="fas fa-folder-open" id="folderopen" style="display:none;"></i>
                        <i class="fas fa-folder" id="folderclose"></i>메모</div>
                        <li class="folderli" style="display: none;">오늘 뭐먹지</li>
                        <li class="folderli" style="display: none;">잡담방</li>
                    	
                    </ul>
                    <ul class="video"><div class="cate2"><i class="fas fa-chalkboard-teacher"></i>화상회의</div>
                        <li class="videoli" style="display: none;"><p class="on">●</p>오늘 뭐먹지</li>
                        <li class="videoli" style="display: none;"><p class="off">●</p>잡담방</li>
                    </ul>
                </div>
                <div class="private">
                            <div class="reload" onClick="window.location.reload()"><i class="fas fa-sync"></i>동기화</div>
                </div>
                
                
                <script>
                
                $('.search').find('.setting').on('click' , function() {
                	location.href="/project/setting/role-management/${projectIdx}";
                })
                
                </script>
            