<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
   
<%@ include file="/WEB-INF/views/include/head.jsp" %>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
    <script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.js" ></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <style>
      html,body{
        width: 100%;
        height:100%;
        display: flex;   
      }

      #cy {
        width:70%;
        height: 100%;
      }

      .inputzone{
        width:30%;
        height:20%;
        background-color: pink;
        display:flex;
        flex-direction: column;

      }

      .addelement, .addline, .removeline, .removeele{
        cursor: pointer;

      }
    </style>
</head>
<body>

  <div id="cy"></div>
  <div class="inputzone">
  

    <div class="elementclass">source<input type="text" class= "element" name="element"></div>
    <div class="addele">요소추가</div>
    <div class="removeele">요소삭제</div>
    <div class="sourceclass">source<input type="text" class= "source" name="source"></div>
    <div class="targetclass">target<input type="text" class="target" name="target"></div>
    <div class="addline">관계추가</div>
    <div class="removeline">관계삭제</div>
  
  </div>
  
    

    <script type="module">
        import cytoscape from "https://cdnjs.cloudflare.com/ajax/libs/cytoscape/3.15.1/cytoscape.esm.min.js";
        
		var ele = [{ data : {id : "src"}}];
		
		<c:forEach items="${paths}" var="path">
		
			var data = { data : {id : "${path.path}"}};
			ele.push(data);
		 </c:forEach>
		<c:forEach items="${paths}" var="path">
			var data = { data: {source: "${path.prev}", target: "${path.path}" }};
			ele.push(data);
		 </c:forEach>
        
      

        var cy = cytoscape({    //초기화
      
          container: document.getElementById('cy'), // 맵을 그릴 컨테이너 준비 (시각화를 위한 dom 요소)

          
          elements: ele
/*
 [  
             { data: { id: 'proz' }},  //그래프 수준데이터를 포함하는 일반 개체
             { data: { id: 'src' }},
             { data: { id: 'main' }},
             { data: { id: 'test' }},
             { data: { id: 'resources' }},
             { data: { id: 'ab', source: 'proz', target: 'src' }},
             { data: { source: 'src', target: 'main' }},
             { data: { source: 'src', target: 'test' }},
             { data: { source: 'test', target: 'resources' }},

          ]
*/

			,

      
          style: [ // 스타일 지정
            {
              selector: 'node',
              style: {                        
                'background-color': '#666',
                'label': 'data(id)'
              }
            },
            {
              selector: 'edge',
              style: {
                'width': 3,
                'line-color': '#ccc',
                'target-arrow-color': '#ccc',
                'target-arrow-shape': 'triangle',
                'curve-style': 'bezier'
              }
            }
          ],
      
          layout: {           //레이아웃 옵션
            name: 'breadthfirst',
            rows: 1,
            fit: true, // 뷰포트를 그래프에 맞출지 여부
           directed: true, // 트리가 아래쪽을 향하는지 여부(또는 false인 경우 가장자리가 모든 방향을 가리킬 수 있음)
           padding: 30, // 맞을 때 패딩
           circle: false, // true이면 동심원에 깊이를 넣고 false이면 깊이를 위에서 아래로 놓습니다.
             grid: false, //DAG가 배치되는 짝수 그리드 생성 여부(circle:false만 해당)
           spacingFactor: 1.75, // 양수 간격 인수, 더 크면 => 노드 사이에 더 많은 공간(N.B. 겹치는 경우 해당 없음)
          boundingBox: undefined, // 레이아웃 경계를 제한합니다. { x1, y1, x2, y2 } 또는 { x1, y1, w, h }
            avoidOverlap: true, // 노드 겹침을 방지하고 공간이 충분하지 않으면 boundingBox를 오버플로할 수 있습니다.
          nodeDimensionsIncludeLabels: false, // 레이아웃 알고리즘에 대한 노드 경계 상자를 계산할 때 레이블을 제외합니다.
            roots: undefined, // 나무의 뿌리
        maximal: false, // 위쪽 가장자리를 피하기 위해 노드를 자연 BFS 깊이 아래로 이동할지 여부(DAGS만 해당)
          animate: false, // 노드 위치를 전환할지 여부
            animationDuration: 500, // 활성화된 경우 애니메이션 지속 시간(ms)
         animationEasing: undefined, // 활성화된 경우 애니메이션 이징,
          animateFilter: function ( node, i ){ return true; }, // 노드에 애니메이션을 적용할지 여부를 결정하는 함수. 애니메이션 활성화 시 기본적으로 애니메이션되는 모든 노드. 애니메이션이 아닌 노드는 레이아웃이 시작될 때 즉시 배치됩니다.
            ready: undefined, // layoutready에서 콜백
          stop: undefined, // layoutstop에서 콜백
           transform: function (node, position ){ return position; } // 주어진 노드 위치를 변환합니다. 개별 레이아웃에서 흐름 방향을 변경하는 데 유용합니다.
          }

          

        });

     


          $(".addele").click(function(){
            cy.add ( { group: 'nodes', data: { id: $(".element").val() }, position: { x: 100, y: 100 } }, );
            cy.layout({ name: 'breadthfirst', rows: 1});
          });

          $(".addline").click(function(){
            cy.add ({ group: 'edges', data: { id: $(".source").val() + $(".target").val() , source: $(".source").val() , target: $(".target").val() } }, );
          });

          $(".removeline").click(function(){
            cy.remove ({ group: 'edges', data: { id: $(".source").val() + $(".target").val()}} );
          });

          $(".removeele").click(function(){
            cy.remove ({ group: 'nodes', data: { id: $(".element").val() }, position: { x: 100, y: 100 } }, );
          });
        


 
      </script>

 

</body>
</html>