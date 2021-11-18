
        
        
        // 아이콘의 정상출력을 위해서 fontawsome 필요 
        class ValidateMachine {

            validator; 
            regExp; 
            passedColor;
            failedColor;  
            blankColor; 
            reverseRegExp; 
            failedMsg; 
            passedMsg; 

            constructor() {
                this.regExp = []; 
                this.reverseRegExp = []; 
                this.passedColor = 'RGB(95, 205, 224)'; 
                this.failedColor = 'RGB(234, 101, 146)'; 
                this.blankColor = '#ced4da';
                this.failedMsg = '<i class="fas fa-times-circle"></i>&nbsp&nbsp 사용 불가능한 값입니다. 다시 시도하세요'; 
                this.passedMsg = '<i class="fas fa-times-circle"></i>&nbsp&nbsp사용 가능합니다.';
            }

            //**INPUT TAG에 Validator를 추가하는 메서드 
            addValidator = function(element) {
                var failedMsg = this.failedMsg; 
                const realParent = element.parentElement; 
                const clone = element.cloneNode();
                //1. 부모 DIV 생성, 클론과 Validator용 div 추가  
                const newDiv = document.createElement("div");
                let childDiv = document.createElement("div"); 
                newDiv.appendChild(clone); 
                newDiv.appendChild(childDiv.cloneNode()); 
                newDiv.appendChild(childDiv.cloneNode()); 
                    //원위치 후 기존 inputTag 삭제 
                realParent.insertBefore(newDiv, element);
                realParent.removeChild(element);
                this.validator = newDiv;  

                //2. 새로운 inputTag에 Validator 추가
                newDiv.addEventListener('input', e => {
                var result = null;  
                var inputValue = e.target.value; 
                //3. 외부 RegExp 객체를 불러와 Foreach를 통해 regExp 검증
                //Array 전환, foreach문 돌리기 
                //Array.every를 이용,for-in의 return문의 상태에 따라 break처리
                // Reverse > verse 순서로 순차진행

                //리버스 정규표현식
                if(this.reverseRegExp){
                    this.reverseRegExp.every(e =>{
                        for (const property in e) {
                            let reg = new RegExp(`${property}`); 
                            console.log('들어온 정규표현식 : ' + reg); 
                            console.log('들어온 input' + inputValue);
                            let res = reg.test(inputValue); 
                            console.log('테스트 결과 : ' + res);
                            failedMsg = `${e[property]}`; 
                            console.log('테스트결과메시지 : ' + failedMsg);
                            result = !res; 
                            return result; 
                        };
                    });
                };  
                
                // 만약 리버스만 있고 regExp 없을 경우, 그냥 보낸다. 
                // 만약 그냥만 있고 리버스 없을 경우, 그냥 보낸다. 

                if(result != false){
                    if(this.regExp){
                        // 일반 정규표현식
                            this.regExp.every(e =>{
                                for (const property in e) {
                                    let reg = new RegExp(`${property}`); 
                                    console.log('들어온 정규표현식 : ' + reg); 
                                    console.log('들어온 input' + inputValue);
                                    let res = reg.test(inputValue); 
                                    console.log('테스트 결과 : ' + res);
                                    failedMsg = `${e[property]}`; 
                                    console.log('테스트결과메시지 : ' + failedMsg);
                                    result = res; 
                                    return result; 
                                };
                            });
                        }
                }
                
                // 3. 테스트 결과에 따라 pass/fail/default로 나누어 css 변경 	
                let divList = newDiv.childNodes;         

                        if(result) {
                            //input조작
                            divList[0].style.borderColor = this.passedColor; 
                            //valid조작
                            divList[1].style.display = "block"; 
                            divList[1].style.color = this.passedColor;
                            divList[1].innerHTML =  this.passedMsg; 
                            //invalid조작 
                            divList[2].style.display = "none";
                        } else {
                            divList[0].style.borderColor = this.failedColor; 
                            //valid조작
                            divList[1].style.display = "none"; 
                            //invalid조작 
                            divList[2].style.display = "block";
                            divList[2].innerHTML = `<i class="fas fa-times-circle"></i>&nbsp&nbsp${failedMsg}`; 
                            divList[2].style.color = this.failedColor; 
                        };
                        
                        // default 
                        if(!divList[0].value) {
                            divList[0].style.borderColor = this.blankColor; 
                            //valid조작
                            divList[1].style.display = "none"; 
                            //invalid조작 
                            divList[2].style.display = "none";
                        };
                });

            };

            addDuplicateValidator(element, compareElement){
                var failedMsg = this.failedMsg; 
                const realParent = element.parentElement; 
                const clone = element.cloneNode();
                //1. 부모 DIV 생성, 클론과 Validator용 div 추가  
                const newDiv = document.createElement("div");
                let childDiv = document.createElement("div"); 
                newDiv.appendChild(clone); 
                newDiv.appendChild(childDiv.cloneNode()); 
                newDiv.appendChild(childDiv.cloneNode()); 
                    //원위치 후 기존 inputTag 삭제 
                realParent.insertBefore(newDiv, element);
                realParent.removeChild(element);
                this.validator = newDiv;  

                //2. 새로운 inputTag에 Validator 추가, 
                //3. compareElement(비교대상)에 문제 발생 시 조치 
                
                
                if(compareElement){

                    newDiv.addEventListener('input', e => { 
                      
                        var compareValue = compareElement.value; 
                        console.dir(compareElement); 
                        console.log(compareValue);
                        var inputValue = e.target.value; 

                        let reg = new RegExp(`^${compareValue}$`); 
                        console.log('들어온 정규표현식 : ' + reg);
                        console.log('들어온 input' + inputValue);
                        let res = reg.test(inputValue);
                        console.log('테스트 결과 : ' + res); 

                        let divList = newDiv.childNodes;   
                        
                        if(res) {
                            //input조작
                            divList[0].style.borderColor = this.passedColor; 
                            //valid조작
                            divList[1].style.display = "block"; 
                            divList[1].style.color = this.passedColor;
                            divList[1].innerHTML =  this.passedMsg; 
                            //invalid조작 
                            divList[2].style.display = "none";
                        } else {
                            divList[0].style.borderColor = this.failedColor; 
                            //valid조작
                            divList[1].style.display = "none"; 
                            //invalid조작 
                            divList[2].style.display = "block";
                            divList[2].innerHTML = this.failedMsg; 
                            divList[2].style.color = this.failedColor; 
                        };
                        
                        // default 
                        if(!divList[0].value) {
                            divList[0].style.borderColor = this.blankColor; 
                            //valid조작
                            divList[1].style.display = "none"; 
                            //invalid조작 
                            divList[2].style.display = "none";
                        };
                    }); 
                }; 
            };


            userValidator = function() {
                console.dir(this.validator); 
            };

            addRegExp = function(exp) {
                this.regExp.push(exp);
                //이때 EXP를 JSON객체로 받아 validateMessage까지 받아야 한다. 
            };

            addReverseRegExp = function(exp){
                this.reverseRegExp.push(exp); 
            }

            clearRegExp = function(){
                this.regExp = []; 
            };

            clearReverseRegExp = function(){
                this.reverseRegExp = []; 
            };


            setPassedColor = function(color){
                this.passedColor = color; 
            };

            setFailedColor = function(color){
                this.failedColor = color; 
            };
            
            setBlankColor = function(color){                
                this.blankColor = color; 
                this.validator.firstChild.style.borderColor = color;
            };

            setPassedMsg = function(msg){
                this.passedMsg = msg; 
            }
            setfailedMsg = function(msg){
                this.failedMsg = msg; 
            }

            getPassedColor = function() {
                return this.passedColor; 
            };

            getFailedColor = function() {
                return this.failedColor; 
            };

            getBlankColor = function() {
                return this.blankColor; 
            };
            getPassedMsg = function() {
                return this.passedMsg;
            }
            getFailedMsg = function() {
                return this.failedMsg; 
            }
        }

    