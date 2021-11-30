<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html lang="ko">

<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
    <title>Document</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
    <script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.js" ></script>
    <link type="text/css" rel="stylesheet" href="/resources/js/modal/modal.js">
	
    <script src="https://cdn.jsdelivr.net/npm/treeviz@2.3.0/dist/index.min.js"></script>

    <style type="text/css">
        html,
        body {

            width: 100%;
            height: 100%;
            min-width: 1000px;
            min-height: 800px;
        }

        .wrap {
            height: 100%;
            display: flex;
            flex-direction: column;

        }

        header {
            height: 60px;
            background: rgb(143, 122, 229);
            background: radial-gradient(circle, rgba(143, 122, 229, 1) 0%, rgba(241, 112, 170, 1) 100%);

        }

        .con {

            height: 100%;
            display: flex;
            justify-content: space-between;

        }

        nav {
            background: rgb(19, 23, 34);
            background: linear-gradient(0deg, rgba(19, 23, 34, 1) 0%, rgba(26, 17, 47, 1) 22%, rgba(26, 30, 41, 1) 83%, rgba(19, 23, 34, 1) 100%);


            width: 220px;
            height: 100%;
            flex-shrink: 0;

        }

        section {

            flex: 1;
            width: 100%;
            height:100%;
            background: RGB(245, 246, 247);
            display: flex;
            justify-content: center;
            overflow-y: auto;
            min-width: 1300px;
            overflow-x: hidden;
        }


        aside {

            background: rgb(19, 23, 34);
            background: linear-gradient(0deg, rgba(19, 23, 34, 1) 0%, rgba(26, 17, 47, 1) 22%, rgba(26, 30, 41, 1) 83%, rgba(19, 23, 34, 1) 100%);

            height: 100%;
            width: 200px;
            flex-shrink: 0;

        }



        /*섹션 영역////////////////////////////////////////////////////////////*/

        .section-wrap {
            margin: 0 50px;
            height: auto;
            width:100%;
            display: flex;
            flex-direction: column;
            align-items: center;

        }

        .roadmap-tit {
            font-size: 25px;
            margin: 20px 0px;
            font-weight: 900;
            width: 1200px;

        }

        .content-wrap {
            width: 1200px;
            height:90%;
            padding-bottom: 20px;
            display: flex;
            flex-direction: column;
        }

        .btnarea{
            width:100%;
            height:5%;
            display:flex;
            justify-content: flex-end;
        }


        #modifinishbtn, #gitbtn, #modifybtn, #resetbtn{
            margin-right: 10px;
        }

        .gitinput{
            width:400px;
            margin-right: 10px;

        }

        .map-view{
            width:100%;
            height:95%;
            display: flex;
        }

        


        #cy{
            width:100%;
            height:100%;
            
        }

        .map-editor{
            width:15%;
            height:100%;
            display:none;
            padding:3px;
            margin-top:10px;
        }


        .input-area{
            width:100%;
            height:100%;
            /* border: solid 1px #ccc; */
            background-color: RGB(227, 228, 231);
            border-radius: 5px;
            padding:10px;
            display:flex;
            flex-direction: column;
        }

        input{
            width:100%;
        }



         .linebtn {
            display:flex;
            width:100%;
            padding: 10px 0px 10px 0px;
            justify-content: space-around;
            flex-direction: column;
            
        }
        


        #addline, #removeline{
        cursor: pointer;
        width:100%;
        font-size:25px;
        height:30px;
        display:flex;
        justify-content: center;
        align-items: center;
        margin-bottom:10px;
      }

      .fromto{
          display:flex;
          width:100%;
          justify-content: space-between;
      }

      .sourceclass, .targetclass{
          width:48%;
      }

      .btn-info {
        color: #fff;
        background-color: #8f7ae5;
        border-color: #8f7ae5;
    }

    .btn-info:hover {
        color: #fff;
        background-color: #6d5dac;
        border-color: #6d5dac;
    }






    </style>

</head>

<body>

    <div class="wrap">
        <header>





        </header>

        <div class="con">
            <nav></nav>
            <section>
                    <div class="section-wrap">
    
    
                        <div class="roadmap-tit">
                            #RoadMap
                            <hr>
                        </div>

                    <div class="content-wrap">

                        <!-- cytoscape -->
                        <div class="btnarea">
                            <div id="modifybtn" class="btn btn-info">수정하기</div>
                            <input type="text" class= "gitinput form-control" name="element" style="display:none;" placeholder="깃주소를 입력하세요">
                            <div id="gitbtn" class="btn btn-primary" style="display:none;">깃 연동하기</div>
                            <div id="resetbtn" class="btn btn-primary" style="display:none;">모두 지우기</div>
                            <div id="modifinishbtn" class="btn btn-info" style="display:none;">수정완료</div>
                        </div>
                        <div class="map-view">
                            
    						<div id="tree" style="height:700px; width:1200px"></div>
    					
                        <!-- cytoscapt-end -->
                        <div class="map-editor">
                            <div class="input-area">
                                <div class="fromto">
                                    <div class="sourceclass">from<input type="text" class= "source form-control form-control-sm" name="source" placeholder="source"></div>
                                    <div class="targetclass">to<input type="text" class="target form-control form-control-sm" name="target" placeholder="target"></div>
                                </div>
                                <div class="linebtn">
                                    <div id="addline"  class= "btn btn-success"> + </div>
                                    <div id="removeline" class= "btn btn-danger"> - </div>
                                </div>
                            </div>
                        </div>
                        </div>
                    </div>
            </section>
            <aside></aside>
        </div>

    </div>

<script type="text/javascript">
	
	

    $("#modifybtn").click(function(){
        $("#modifinishbtn").css("display","flex");
        $("#gitbtn").css("display","flex");
        $("#resetbtn").css("display","flex");
        $("#modifybtn").hide();
        $("#cy").css("width","85%");
        $(".map-editor").css("display","flex");
        $(".gitinput").css("display","flex");
    });
    

    $("#modifinishbtn").click(function(){
        $("#modifybtn").css("display","flex");
        $("#modifinishbtn").hide();
        $("#cy").css("width","100%");
        $(".map-editor").hide();
        $("#gitbtn").hide();
        $("#resetbtn").hide();
        $(".gitinput").hide();
    });
    
    
    $("#gitbtn").on('click', function() {
    	
    	var git = $(".gitinput").val();
    	
    	fetch("/loadmap/git/upload",{
			method : "POST",
			headers :  {"Content-type" : "application/json; charset=UTF-8"},
			body : JSON.stringify({
					wsIdx : ${param.wsIdx},
					gitRepo : git	,
					branch : "main" ,
					ignore : ["css" , "resources" , "test" , "img"]
					
			
				})
			
		})
    	
    	
    	
    })


	let dataJson = JSON.parse('${loadmap.gitTree}');
    
    console.dir(dataJson);
    let dataArr = [{ id: "2785aa0e" ,text_1:"src", father:null }];
    
    

    
    dataJson.forEach(function(e) {
    	
    	let d =  { id: e.sha , text_1:e.path , father: e.prev , color:"#2196F3" };
    	dataArr.push(d);
    	
    })
    
    console.dir(dataArr);



</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/cytoscape/3.20.0/cytoscape.umd.js" integrity="sha512-bgoBr08oPe0Fgqfk4TY8yNOXb1g3pkWHnsiVLLqmR+71gyo1v4PRwFEYTIL1xuFG/EHZRAvn7P1aMvs/9rKoAg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://pagecdn.io/lib/cytoscape/3.19.1/cytoscape.umd.js" crossorigin="anonymous"  ></script>
<!-- cytoscape--------------------------------------------------------------------------------- -->
 <script>
 
        var data = dataArr;
        

    var myTree = Treeviz.create({
        htmlId: "tree",
        idKey: "id",
        hasFlatData: true,
        relationnalField: "father",
        hasPanAndZoom: true,
        nodeWidth:150,
        nodeHeight:50,
        mainAxisNodeSpacing:2, //너비
        isHorizontal:true,
        renderNode: function(node) { 
        return result = "<div class='box' style='cursor:pointer;height:"+node.settings.nodeHeight+"px; width:auto;display:flex;flex-direction:column;justify-content:center;align-items:center;background-color:"
                +node.data.color+
              ";border-radius:5px;'><div><strong>"
          +node.data.text_1+
          "</strong></div></div>";
        },
        linkWidth : (nodeData)=> 5, //노드 선 두께
        linkShape:"curve",
        linkColor : (nodeData) => "#B0BEC5" ,
        onNodeClick : (nodeData) => console.log(nodeData)
    });

    
    
    myTree.refresh(data);

    
    var toggle=true;
/*     
    document.querySelector("#add").addEventListener("click", () => {toggle ? myTree.refresh(data_2) : myTree.refresh(data_3); toggle = false});
    document.querySelector("#remove").addEventListener("click", () => myTree.refresh(data));
      */
    </script>

<!-- 
<script type="text/javascript">


   const data = dataArr;


/*
[ // 일반객체로 지정된 요소
        { data: { id: 'proz' , url:"menu.html" ,label: "prozlabel"}},  //그래프 수준데이터를 포함하는 일반 개체
        { data: { id: 'src' , label: "srclabel" }},
        { data: { id: 'main' , label: "mainlabel" }},
        { data: { id: 'test' , label: "testlabel"}},
        { data: { id: 'java' , label: "mainlabel" }},
        { data: { id: 'java1' , label: "mainlabel" }},
        { data: { id: 'webapp' , label: "webapplabel" }},
        { data: { id: 'resources' , label: "resourceslabel" }},
        { data: { id: 'resources1' , label: "resourceslabel" }},
        { data: { id: 'ab', source: 'proz', target: 'src' }},
        { data: { source: 'src', target: 'main' }},
        { data: { source: 'src', target: 'test' }},
        { data: { source: 'test', target: 'resources' }},
        { data: { source: 'test', target: 'java' }},
        { data: { source: 'main', target: 'resources1' }},
        { data: { source: 'main', target: 'java1' }},
        { data: { source: 'main', target: 'webapp' }},

      ];
*/


  
  //뎁스에따라 크기차이 주기 
    const cy_for_rank = cytoscape({
    elements: data
});
// rank를 활용하기 위해 data만 입력한 cytoscape 객체입니다

const pageRank = cy_for_rank.elements().pageRank();
// elements들의 rank들입니다.


/// 변경된 부분
const nodeMaxSize = 50;
const nodeMinSize = 30;
const nodeActiveSize = 28;
const fontMaxSize = 20;
const fontMinSize = 15;
const fontActiveSize = 7;
// node & font 크기 값

const edgeWidth = '2px';
var edgeActiveWidth = '4px';
const arrowScale = 0.8;
const arrowActiveScale = 1.2;
// edge & arrow 크기값

const dimColor = '#dfe4ea';
const edgeColor = '#ced6e0';
const nodeColor = '#57606f';
const nodeActiveColor = '#5000bf';

const successorColor = '#8f7ae5';
// 상위 node & edge color
const predecessorsColor = '#f170aa';
// 하위 node & edge color

// 변경된 부분


function setDimStyle(target_cy, style) {
    target_cy.nodes().forEach(function (target) {
        target.style(style);
    });
    target_cy.edges().forEach(function (target) {
        target.style(style);
    });
}

function setFocus(target_element, successorColor, predecessorsColor, edgeWidth, arrowScale) {
    target_element.style('background-color', nodeActiveColor);
    target_element.style('color', nodeColor);
    target_element.successors().each(function (e) {
        // 상위  엣지와 노드
        if (e.isEdge()) {
            e.style('width', edgeWidth);
            e.style('arrow-scale', arrowScale);
        }
        e.style('color', nodeColor);
        e.style('background-color', successorColor);
        e.style('line-color', successorColor);
        e.style('source-arrow-color', successorColor);
        setOpacityElement(e, 0.5);
    }
    );
    target_element.predecessors().each(function (e) {
        // 하위 엣지와 노드
        if (e.isEdge()) {
            e.style('width', edgeWidth);
            e.style('arrow-scale', arrowScale);
        }
        e.style('color', nodeColor);
        e.style('background-color', predecessorsColor);
        e.style('line-color', predecessorsColor);
        e.style('source-arrow-color', predecessorsColor);
        setOpacityElement(e, 0.5);
    });
    target_element.neighborhood().each(function (e) {
        // 이웃한 엣지와 노드
        setOpacityElement(e, 1);
    }
    );
    target_element.style('width', Math.max(parseFloat(target_element.style('width')), nodeActiveSize));
    target_element.style('height', Math.max(parseFloat(target_element.style('height')), nodeActiveSize));
    target_element.style('font-size', Math.max(parseFloat(target_element.style('font-size')), fontActiveSize));
}

function setOpacityElement(target_element, degree) {
    target_element.style('opacity', degree);
}

function setResetFocus(target_cy) {
    target_cy.nodes().forEach(function (target) {
        target.style('background-color', nodeColor);
        var rank = pageRank.rank(target);
        target.style('width', nodeMaxSize * rank + nodeMinSize);
        target.style('height', nodeMaxSize * rank + nodeMinSize);
        target.style('font-size', fontMaxSize * rank + fontMinSize);
        target.style('color', nodeColor);
        target.style('opacity', 1);
    });
    target_cy.edges().forEach(function (target) {
        target.style('line-color', edgeColor);
        target.style('source-arrow-color', edgeColor);
        target.style('width', edgeWidth);
        target.style('arrow-scale', arrowScale);
        target.style('opacity', 1);
    });
}



    var cy = cytoscape({    //초기화
  
      container: $('#cy'), // 맵을 그릴 컨테이너 준비 (시각화를 위한 dom 요소)

      
      elements: data,

  
      style: [ // the stylesheet for the graph
        {
            selector: 'node',
            style: {
              // 변경된 부분
                'background-color': nodeColor,
              //
                'label': 'data(label)',
                'width': function (ele) {
                    return nodeMaxSize * pageRank.rank('#' + ele.id()) + nodeMinSize;
                },
                'height': function (ele) {
                    return nodeMaxSize * pageRank.rank('#' + ele.id()) + nodeMinSize;
                },
                'font-size': function (ele) {
                    return fontMaxSize * pageRank.rank('#' + ele.id()) + fontMinSize;
                },
              // 추가된 부분
                'color': nodeColor
              //
            }
        },

        {
            selector: 'edge',
            style: {
              // 변경된 부분
                'width': edgeWidth,
                'curve-style': 'bezier',
                'line-color': edgeColor,
                'source-arrow-color': edgeColor,
                'source-arrow-shape': 'vee',
                'arrow-scale': arrowScale,
                'curve-style': 'bezier'
              //
            }
        }
    ],

    layout: {
        name: 'breadthfirst',
        rows: 20,
        nodeDimensionsIncludeLabels: false,
        directed: true, 
        pavoidOverlap: true,
        fit: false
    }
});





      



    //url 연결
    cy.on('tap', function (e) {
    const url = e.target.data('url')
    if (url && url !== '') {
        window.open(url);
    }
    });

    cy.on('tapstart mouseover', 'node', function (e) {
    setDimStyle(cy, {
        'background-color': dimColor,
        'line-color': dimColor,
        'source-arrow-color': dimColor,
        'color': dimColor
    });

    setFocus(e.target, successorColor, predecessorsColor, edgeActiveWidth, arrowActiveScale);
});

cy.on('tapend mouseout', 'node', function (e) {
    setResetFocus(e.cy);
}); 

    //요소&관계 추가
      // $("#addline").click(function(){
      //   cy.add ( [{ group: 'nodes', data: { id: $(".source").val() }, position: { x: 100, y: 100 } }, 
      //            { group: 'nodes', data: { id: $(".target").val() }, position: { x: 100, y: 100 } }, 
      //            { group: 'edges', data: { id: $(".source").val() + $(".target").val() , source: $(".source").val() , target: $(".target").val() } }, ]);
      //            cy.layout({ name: 'breadthfirst', rows: 1 });
      //            layout.run();
      //   data.push({ group: 'nodes', data: { id: $(".source").val() }, position: { x: 100, y: 100 } },); 
      //   });

        $("#addline").click(function(){
        data.push({ group: 'nodes', data: { id: $(".source").val() + "Id", label: $(".source").val()}, position: { x: 100, y: 100 } }, ); 
        data.push({ group: 'nodes', data: { id: $(".target").val() + "Id", label: $(".target").val()}, position: { x: 100, y: 100 } }, ); 
        data.push({ group: 'edges', data: { id: $(".source").val() + $(".target").val() , source: $(".source").val() , target: $(".target").val() } }, ); 
        });

      $("#removeele").click(function(){
        cy.remove ({ group: 'nodes', data: { id: $(".source").val() }, position: { x: 100, y: 100 } }, );
      });



      
    



  </script>

 -->
</body>

</html>