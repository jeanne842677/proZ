
    //defalutOptions 
    const defaults = {
        loadingId : "loadingGear",
        loadingClass : "loadingGear",
        outerColor : "#664688",
        innerColor : '#ffffff' 
    };

    class loadingGear{
        lodingGear; 
        loadingId;
        loadingClass; 
        outerColor; 
        innerColor; 

        // json 객체를 받아 ||을 통해 값 비교, 처리 (Overloading 유사)
        constructor(options) {
            this.loadingId = options.loadingId || defaults.loadingId; 
            this.loadingClass = options.loadingClass || defaults.loadingClass;
            this.outerColor = options.outerColor || defaults.outerColor;
            this.innerColor = options.innerColor || defaults.innerColor;
        }

        // 뷰포트를 통해서 width와 height를 고정값으로 줄수 있지 않나?
        // jquery를 사용하지 않을 경우, createElement로 일일히 만들어주어야 한다. 
        createLoadingGear = function() {
  
            // div를 생성, 자식요소를 추가 
            let div = document.createElement('div'); 
            div.setAttribute('id', this.loadingId); 
            div.setAttribute('class', this.loadingClass);
            div.style.cssText =  
            'display: flex;flex-direction: column;justify-content: center;'
             + 'align-items: center;position: fixed;top: 0px;bottom: 0px;width: 100%;'
             + 'height: 100%;background-color: rgba(0, 0, 0, 0.5);z-index: 999;'
            
            let secondDiv = document.createElement('div');
            secondDiv.setAttribute('class', 'ldio-8mhodazy58c');
            div.append(secondDiv);

            secondDiv.append(document.createElement('div'));

            for (let i = 0; i < 6; i++) {
                secondDiv.firstChild
                .append(document.createElement('div'));
            };
            
            // body에 추가, 자식요소를 querySelector, querySelectorAll로 호출 
            document.querySelector('body').append(div);
            // css 추가 
            let spinDivs = document.querySelectorAll('.ldio-8mhodazy58c > div div');  
            spinDivs.forEach(element => {
                element.style.backgroundColor = this.outerColor; 
            });
            
            document.querySelector('.ldio-8mhodazy58c > div div:nth-child(6)').style.backgroundColor = this.innerColor; 
            div.style.display = 'none'; 
            
            this.loadingGear = div; 
        } 

        on = function(){
            this.loadingGear.style.display = 'flex'; 
        }

        off = function(){
            this.loadingGear.style.display = 'none'; 
        }

        getOuterColor = function(){
            return this.outerColor;
        }
        getInnerColor = function(){
            return this.innerColor; 
        }
        setOuterColor = function(color){
            this.outerColor = color; 
        }
        setInnerColor = function(color){
            this.innerColor = color; 
        }
        destroy = function(){
            this.loadingGear.remove(); 
        }
    };
    

    
    
    


