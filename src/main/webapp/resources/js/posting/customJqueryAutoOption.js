// 1. initial Variable for Custom jquery_ui autocomplete 
// Caret 위치정보 / ctrl+enter 검증 / enter키 검증 
var selection = window.getSelection();
var isEnter= false; 
var isSelect = false; 

// custom_autocomplete (category inserted) (순서 반드시!) 
var initcustomAutocomplete = function() { 
    $.widget( "custom.catcomplete", $.ui.autocomplete, {
          _create: function() {
            this._super();
            this.widget().menu( "option", "items", "> :not(.ui-autocomplete-category)" );
          },
          _renderMenu: function( ul, items ) {
            var that = this,
              currentCategory = "";
            $.each( items, function( index, item ) {
              var li;
              if ( item.category != currentCategory ) {
                ul.append( "<li class='ui-autocomplete-category'>" + item.category + "</li>" );
                currentCategory = item.category;
              };
              that._renderItemData( ul, item );
            });
          }
    });
}; 

// autoComplete & sourceMenu initiate  
var initCustomAutocomplete = function(element) {
  element.catcomplete({
          minLength: 0,
          source: popUpMenu,
              focus: function( event, ui ) {
                return false;
              },
          // menu value switch_case function  
              select: function( event, ui ) {  
                switch(ui.item.label) {
                  
                  // 3_Title function
                  case 'title1':
                    $(this).after(function(){
                      return "<div class='commonDiv'contenteditable='true'"
                      + "style='font-size:30px; font-weight:bold;'"
                      + "></div>";
                    });
                    createCustomAutocomplete($(this).next());
                  break;
                  case 'title2':
                    $(this).after(function(){
                      return "<div class='commonDiv'contenteditable='true'"
                      + "style='font-size:26px; font-weight:bold;'"
                      + "></div>";
                    });
                    createCustomAutocomplete($(this).next());
                  break;
                  case 'title3':
                    $(this).after(function(){
                      return "<div class='commonDiv'contenteditable='true'"
                      + "style='font-size:22px; font-weight:bold;'"
                      + "></div>";
                    });
                    createCustomAutocomplete($(this).next()); 
                  break;

                  // 3_file function, (말줄임 필요, 업데이트이전)
                  case 'file' :    
                    $(this).after(function(){ 
                      // input을 포함한 작은 div 생성 
                      return "<div class='immutableDiv'contenteditable='false' style='caret-color:transparent; padding:20px;'>" 
                      + "<div id='drop_zone'class='dropleaved' data-fetchResult='file'"
                      + "ondrop='dropHandler(event);' ondragover='dragOverHandler(event);' ondragleave='dragleaveHandler(event)'"
                      + "style='height:120px; width:200px; font-size:8px;"  
                      + "border-radius:5px; margin:10px; color:slategray; font-weight:bolder;" 
                      + "display:flex; flex-direction:column; justify-content:center; align-items:center;'>"
                      + "파일을 드래그 앤 드롭하세요" 
                      + "</div></div>"
                    });
                    createHyperLinkDiv($(this).next());
                  break; 
                  case 'img':
                    $(this).after(function(){ 
                      // input을 포함한 작은 div 생성 
                      return "<div class='immutableDiv'contenteditable='false' style='caret-color:transparent; padding:20px;'>" 
                      + "<div id='drop_zone'class='dropleaved' data-fetchResult='img'"
                      + "ondrop='dropHandler(event);' ondragover='dragOverHandler(event);' ondragleave='dragleaveHandler(event)'"
                      + "style='height:120px; width:200px; font-size:8px;"  
                      + "border-radius:5px; margin:10px; color:slategray; font-weight:bolder;" 
                      + "display:flex; flex-direction:column; justify-content:center; align-items:center;'>"
                      + "파일을 드래그 앤 드롭하세요" 
                      + "</div></div>"
                    });
                    createImmutableDiv($(this).next()); 
                  break;
                  case 'audio':
                    $(this).after(function(){ 
                      // input을 포함한 작은 div 생성 
                     return "<div class='immutableDiv'contenteditable='false' style='caret-color:transparent; padding:20px;'>" 
                      + "<div id='drop_zone'class='dropleaved' data-fetchResult='audio'"
                      + "ondrop='dropHandler(event);' ondragover='dragOverHandler(event);' ondragleave='dragleaveHandler(event)'"
                      + "style='height:120px; width:200px; font-size:8px;"  
                      + "border-radius:5px; margin:10px; color:slategray; font-weight:bolder;" 
                      + "display:flex; flex-direction:column; justify-content:center; align-items:center;'>"
                      + "파일을 드래그 앤 드롭하세요" 
                      + "</div></div>"
                    });
                    createImmutableDiv($(this).next());

                  // 3_function for write, line / quotation / codeBlock 
                  break;
                  case 'line':
                    $(this).after(function(){
                        return "<div class='immutableDiv'contenteditable='false' "
                        + "style='display:flex; align-items:center; height:20px; width:100%; caret-color: transparent;'>"
                        + "<div style='width:80%; border-top:1.5px solid lightgray;'></div>"
                        + "</div>";
                      });
                    createImmutableDiv($(this).next());
                  break;
                  case 'quotation':
                    $(this).after(function(){
                        return "<div class='commonDiv'contenteditable='true'"
                        + "style='height:27px; font-size:16px; border-left:3px inset; padding-left:10px;"
                        + "padding-top:5px; font-weight:bolder; color:#5b5b5b;'"
                        + "></div>";
                      });
                    createCustomAutocomplete($(this).next());
                  break;
                  case 'code':
                    $(this).after(function(){
                        return "<div class='immutableDiv' contentEditable='false' style='padding:20px; caret-color:transparent;width:100%;'>"
                        + "<div contentEditable='true' style='height:90%; min-height:100px; width:85%; background-color:#ebebeb;"
                        + "border-radius:10px; color:white; padding:20px; caret-color: white; font-size:12px;'>"
                        +"</div></div>";
                      });
                      createCodeBlock($(this).next()); 
                  break; 
                  
                  // 3_function for anchor
                  case 'link' :
                    $(this).after(function(){
                        return "<div class='immutableDiv'contenteditable='false'"
                        + "style='width:200px; height:25px; border: 1px solid lightgray; margin:4px; border-radius:5px;"
                        + "font-size: 12px; display:flex; align-items:center; padding-left:10px; padding-right:10px; caret-color: transparent;'>"
                        + "<a href='resources/h1.png' type='file' target='blank'>"
                        + "PUT_YOUR_Link</a>"
                        + "</div>";
                    });
                    createHyperLinkDiv($(this).next()); 
                  break;
                  case 'redirect' : 
                    $(this).after(function(){
                        return "<div class='immutableDiv' id='code' contenteditable='false'"
                        + "style='width:200px; height:25px; border: 1px solid lightgray; margin:4px; border-radius:5px;"
                        + "font-size: 12px; display:flex; align-items:center; padding-left:10px; padding-right:10px; caret-color: transparent;'>"
                        + "<a href='resources/h1.png' type='file' target='blank'>"
                        + "PUT_YOUR_Link</a>"
                        + "</div>";
                    });
                    createHyperLinkDiv($(this).next()); 
                  break;
                  case 'memo' : 
                    $(this).after(function(){
                        return "<div class='immutableDiv'contenteditable='false'"
                        + "style='width:200px; height:25px; border: 1px solid lightgray; margin:4px; border-radius:5px;"
                        + "font-size: 12px; display:flex; align-items:center; padding-left:10px; padding-right:10px; caret-color: transparent;'>"
                        + "<a href='resources/h1.png' type='file' target='blank'>"
                        + "PUT_YOUR_Link</a>"
                        + "</div>";
                    });
                    createHyperLinkDiv($(this).next()); 
                  break;

                  // fetch_test_case 
                  case 'fetch' :
                    $(this).after(function(){ 
                      // input을 포함한 작은 div 생성 
                      return "<div class='immutableDiv'contenteditable='false' style='caret-color:transparent;'>" 
                      + "<div id='drop_zone'class='dropleaved' data-fetchResult='file'"
                      + "ondrop='dropHandler(event);' ondragover='dragOverHandler(event);' ondragleave='dragleaveHandler(event)'"
                      + "style='height:120px; width:200px; font-size:8px;"  
                      + "border-radius:5px; margin:10px; color:slategray; font-weight:bolder;" 
                      + "display:flex; flex-direction:column; justify-content:center; align-items:center;'>"
                      + "파일을 드래그 앤 드롭하세요" 
                      + "</div></div>"
                    });
                    // 링크에 fetch추가, 코드블록이벤트(immutable, but 작성가능) 추가 
                    createLinkDiv($(this).next()); 
                    createCodeBlock($(this).next());
                  
                    // if command is wrong 
                    break; 
                  default : 
                    console.log('function_not_exist'); 
                  break; 
                }

                return true;
          }
    });
  };

  //****** function for Common autocomplete Div (일반DIV용 함수 및 생성함수)
  var createCustomAutocomplete = function(element){
    initCustomAutocomplete(element); 
    commandFnc(element); 
    enterFnc(element); 
    backSpaceFnc(element); 
    arrowFnc(element); 
  }

  // 1) Ctrl+space로 메뉴가 작동하는 코드를 만든다. 
  var commandFnc = function(element){
    element.on('keydown', e=>{
        if (e.keyCode === 32){
          if(e.ctrlKey){
            isEnter = true; 
          }
        }
    });
  };
  // 2) enter중 menu를 select하는 enter를 구분하여 작동시킨다. 
  // ++ 만일 text를 포함하여 enter가 작동할 경우 Caret을 기준으로 잘라 
  // innerHTML을 재구성하여 ENTER 오른쪽 VALUE가 내려가도록 한다. 
  // autoComplete를 다음 div에 적용시킨 후 focus를 바꿔준다.    
  var enterFnc = function(element) {
    element.on('keydown', function(e) { 
      
      if(e.keyCode === 13){
          if(isSelect === false){
            e.preventDefault();
            let afterText;
              // isSelect를 통해서 일반 enter와 특수한 enter를 구분 
              // ** 주의, selection은 저장되지 않고 계속 바뀌므로 값 저장필요 
                      $(this).after(function(){
                        let text = $(this).text();
                        let currentSel = selection.anchorOffset;
                        let originText = text.slice(0, currentSel); 
                        $(this).text(originText); 
                            if(text.length-currentSel !== 0){
                              afterText = text.slice(-(text.length-currentSel));
                            } else {
                              afterText = ""; 
                            }           
                        return "<div class='commonDiv'contenteditable='true' style='font-size:14px;'></div>";
                      });
            //3. 포커싱 바꾸기
            let sibling = $(this).next();
            sibling.text(afterText); 
            createCustomAutocomplete(sibling);
            sibling.focus(); 
          } else {
            // Event trigger Enter일 경우 : 
            isSelect = false; 
            $(this).next().focus();
          };
      }; 
    });
  };

  // 3) anchorOffset이 0일때, backSpace가 입력되었을 때 이전 div중 CommonDiv를 찾을때까지 올라간다. 
  // 찾지 못한다면 중지, 찾는다면 div를 삭제하고 찾은 div에 모든 값을 붙여넣는다. 
  var backSpaceFnc = function(element){
    element.on('keydown', function(e){
      if(e.keyCode === 8 && selection.anchorOffset === 0 ) {
        e.preventDefault(); 
        let preSib = $(this)[0].previousElementSibling;
        let searchDiv = preSib; 
          let textAreaLength = $('.textArea').children().length; 
          for (let i=0; i<textAreaLength; i++) {
            if(searchDiv.className !== 'commonDiv ui-autocomplete-input') {
              searchDiv = searchDiv.previousElementSibling;
            } else{
              break;  
            }
          }
          searchDiv.innerText += $(this)[0].innerText; 
          
        // 커서변경작업 (포커싱)  
        searchDiv.focus();
        let customRange = selection.getRangeAt(0); 
        let previousContainer = customRange.startContainer;
        customRange.setStart(previousContainer, searchDiv.innerText.length);
        customRange.setEnd(previousContainer, searchDiv.innerText.length); 
        //collapse를 쓰지않고 직접 range객체를 바꿔서 cursor위치를 조정한다. 
        // focusing을 주면 enter의 selection이 정상작동하지 않는다.  
        if($(this).parent().children().length === 2) {
          $(this).parent().append("<div class='initPage'><div>이곳을 클릭하여 새로운 포스트를 작성하세요</div>"
          +"<img src='/resources/img/undraw_control_panel_re_y3ar.svg' style='height: 200px;'></div>")
          createInitPageFnc($(this).next()); 
          $(this).remove();
        } else {
          $(this).remove();
        }
      }
    })
  };

  // 4) arrowFnc, CommonDiv만을 찾아 arrowFnc의 대상으로 삼는다. 
  var arrowFnc = function(element){
    element.on('keydown', function(e){
      if( (e.keyCode === 38 || e.keyCode === 40) && isEnter === false ) {
        if(e.keyCode === 38 ) {
          // 기존 event 취소, focusing대상을 찾는다면 focus하고 
          // cursor 위치를 맨 끝으로 옮긴다. 
          e.preventDefault();  
          let preSib = $(this)[0].previousElementSibling; 
          let searchDiv = preSib; 
          let textAreaLength = $('section').children().length;
          for (let i=0; i<textAreaLength; i++) {
            if(searchDiv.className !== 'commonDiv ui-autocomplete-input' && searchDiv.className !=='editor-title') { 
              searchDiv = searchDiv.previousElementSibling;
            } else{
              break;  
            }
          }  
          searchDiv.focus(); 
          let customRange = selection.getRangeAt(0); 
          let previousContainer = customRange.startContainer;
          customRange.setStart(previousContainer, searchDiv.innerHTML.length);
          customRange.setEnd(previousContainer, searchDiv.innerHTML.length); 
        } else {
          
          // 만일 아래쪽 fucntion이라면 대상을 찾아 focusing을 옮긴다. 
          let nextSib = $(this)[0].nextElementSibling; 
          let searchDiv = nextSib;
          let textAreaLength = $('section').children().length;  
          for (let i=0; i<textAreaLength; i++) {
            if(searchDiv.className !== 'commonDiv ui-autocomplete-input') {
              searchDiv = searchDiv.nextElementSibling;
            } else{
              break;  
            }
          }
          searchDiv.focus();
        }
      }
    })
  }
  
  //****** function for ImmutableDiv (container for Immutable objects)
  // img, src, audio 등을 위한 immutable div 생성 
  var createImmutableDiv = function(element){
    immutableDivClickFnc(element);
    immutableDivBlurFnc(element); 
    immutableDivDeleteFnc(element);
  };
  
  // 1) 클릭 시 바깥 contentEditable을 활성화, div를 삭제가능하게 focusing한다 
  var immutableDivClickFnc = function(element){
    element.on('click', function(e){
      $(this).attr('contenteditable', "true"); 
      console.log('클릭 테스트!')
      // jqueryFocus는 contentEditable에 약간 문제가 있다. 
      $(this).get(0).focus();  
    })
  }

  // 2) blur()시 arrowFunction의 search에 방해되지 않도록 contentEditable을 false처리한다. 
  var immutableDivBlurFnc = function(element) {
    element.blur(function(){
      $(this).attr('contenteditable', "false");
    })
  }

  // 3) Div의 모든 input을 preventDefault하고 backSpace로 삭제만을 가능하게 한다. 
  var immutableDivDeleteFnc = function(element){
    element.on('keydown', function(e){
      e.preventDefault(); 
      if(e.keyCode === 8 || e.keyCode === 46 ) {
        $(this).remove();
      }
    })
  }

  //****** function for HyperLink source
  // 하이퍼링크를 위한 Div 생성 (deprecated, view단 switch_case에서 처리) 
  var createHyperLinkDiv = function(element){
    hyperLinkDivClickFnc(element); 
    triggerHyperLink(element);
    hyperLinkDivBlurFnc(element); 
    immutableDivDeleteFnc(element);
  };
  // 1) hyperLink를 click 시 작동시킨다. 
  var triggerHyperLink = function(element){
    element.children('a')
    .on('click', function(e){
      // jqueryFocus는 contentEditable에 약간 문제가 있다. 
      window.open(
		
        $(this).children('a').attr('href'),
        '_blank'
      );
      
    }); 
  };
  var hyperLinkDivClickFnc = function(element){
    element.on('click', function(e){
      $(this).attr('contenteditable', "true"); 
      // jqueryFocus는 contentEditable에 약간 문제가 있다. 
      $(this).get(0).focus();  
    });
    element.children('a').click(function (evt) {
         evt.stopPropagation();
    });
  }
  
  var hyperLinkDivBlurFnc = function(element) {
    element.blur(function(){
      $(this).attr('contenteditable', "false");
    })
    element.children('a').blur(function(evt){
      evt.stopPropagation(); 
    })
  }
  
  
  //***** function for codeBlockFnc (contentEditable Div, codeBlock)
  var createCodeBlock = function(element){
    codeBlockPreventEnterFnc(element); 
    codeBlockDivClickFnc(element);
    codeBlockDivBlurFnc(element);  
    codeBlockDivDeleteFnc(element); 
  } 

  // 1) 코드블록 enter키가 <div>가 아닌 <br>로 작동하도록 한다. 
  var codeBlockPreventEnterFnc = function(element){
    element.on('keydown', function(e) {
        if(e.keyCode === 13 ) {
          e.preventDefault();
          document.execCommand('insertLineBreak')
        }
    });
  }
  
  

  // 2) 코드블록에 영향없이 바깥 Div를 선택하면 focus가 발생하도록 한다.   
  // 코드블록을 클릭하면 바깥이벤트가 발생하지 않도록 버블링 차단. 
  // 바깥이벤트가 안쪽에 영향을 주지 않도록 다시 차단 
  var codeBlockDivClickFnc = function(element){ 
     element.click(function() {
      element.attr('contentEditable', 'true'); 
      element.get(0).focus(); 
     });
     element.children('div').click(function (evt) {
         evt.stopPropagation();
     });
  }

  // 2) 코드블록에 영향없이 바깥 Div가 blur()처리되면 arrowFunction에 
  // 방해되지 않도록 contentEditable을 false로 바꾼다.   
  var codeBlockDivBlurFnc = function(element) {
    element.blur(function(){
      $(this).attr('contenteditable', "false");
    })
    element.children('div').blur(function(evt){
      evt.stopPropagation(); 
    })
  }

  // 3) 코드블록을 지우는 기능을 만든다. 
  var codeBlockDivDeleteFnc = function(element){
    element.on('keydown', function(e){
      let isSelected = element.attr('contentEditable');        
      if(e.keyCode === 8 && isSelected === 'true') {
        e.preventDefault();
        $(this).remove(); 
      } 
    })
  }