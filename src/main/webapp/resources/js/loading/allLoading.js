var loading = new loadingGear('gear'); 
loading.createLoadingGear(); 

// loadingGear 작동 
window.onbeforeunload = function() {
	loading.on(); 
	console.log('로딩온'); 
}

// Cache에서 불러온 페이지가 Browsing_Context에 표시될 경우 
// loadingGear를 off(); 
window.onpageshow = (event) => {  
    loading.off();   
}

// 뒤로가기 / 앞으로가기 버튼의 원리는? 

// cache라면, 어떻게 컨트롤? 

// 1. onload는 단 한번 작동한다 (뒤로가기와 상관없이)
// 2. beforeunload는 여러번 작동한다 (뒤로가기와 상관없이)

// 2) window.beforeunload는 window Event와 관련이 있다. 
// 3) Document와 window라는 두가지 객체가 있다. 

// afterprint : fired at the Window after printing
// beforeprint : fired at the window before printing 

// DOMContentLoaded : fired at the Document once the parser has finished 

// pageHide : fired at the window whne the page's entry in the session history 
// > stops being the current entry 
// pageShow : 
// fired at the window when the page's entry in the session history
// become the current entry 

// html5는 브라우징 세션이라는 시스템을 쓴다. 
//  브라우징 Context는 각각의 container document가 있다.. 
// 만일 비었다면, null을 반환하고, cotainer의 노드 document를 반환한다. 

// browsing Context는 보통 tab으로 표시되며, iframe등이 존재할때는 
// 부모자식의 관계를 지닌다.. 
// 

// unload 이벤트를 활용하면 페이지를 떠나는 순간 바로 작동시킬 수 있다. 
// 