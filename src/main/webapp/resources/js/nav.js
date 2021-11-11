 //카테고리 보이기
    $('.cate1').click(function(){
        if($(".folderli").css("display")== "none"){
            $(".folderli").show();
            $("#folderopen").show();
            $("#folderclose").hide();
        }else{
            $(".folderli").hide();
            $("#folderopen").hide();
            $("#folderclose").show();
        }
    })

    $('.cate2').click(function(){
        if($(".videoli").css("display")== "none"){
            $(".videoli").show();
        }else{
            $(".videoli").hide();
        }
    })