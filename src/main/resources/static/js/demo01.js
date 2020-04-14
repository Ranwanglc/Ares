

function post_comment(){
   var questionId  =  $("#question_id").val();
   var content = $("#comment_content").val();
   comment2(questionId,content,1);
}

function comment2(targetId,content,type) {
    if(!content){
        alert("不能回复空的内容");
        return ;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType:"application/json",
        data: JSON.stringify({
            "parentId":targetId,
            "content":content,
            "type":type
        }),
        success: function(response){
            if(response.code == 200)
            {
                window.location.reload();

            }
            else{
                if(response.code==2003)
                {
                    window.open("http://github.com/login/oauth/authorize?client_id=Iv1.89e8b8ea70d3c003&redirect_uri=http://localhost:2603/callback&scope=user&state=1");
                    window.localStorage.setItem("closable",true);
                }
                else
                {

                    alert(response.message);
                }
            }
        },
        dataType: "json"
    });
}

function comment(e){
    var id= e.getAttribute("data-id");
    var content = $("#input-"+id).val();
    comment2(id,content,2);
}

function collapseComments(e) {
    var id= e.getAttribute("data-id");
    var comments=$("#comment-"+id);
    //获取二级评论展开状态
    var collapse=e.getAttribute("data-collapse");
    if(collapse){
        //目前是显示，改为隐藏
        comments.removeClass("in");
        e.removeAttribute("data-collapse");

        //删除按钮颜色
        e.classList.remove("active");
    }
    else
    {
        var twoComment=$("#comment-"+id);
        if($("#comment-"+id).children().length!=1)
        {
            comments.addClass("in");
            e.setAttribute("data-collapse","in");

            //添加按钮颜色
            e.classList.add("active");
        }
        else
        {
            //目前是隐藏，改为显示
            $.getJSON( "/comment/"+id, function( data ) {


                $.each( data.data.reverse(), function( index,comment) {

                    var mediaLeftElement=$( "<div/>", {
                        "class": "media-left "
                    }).append($("<img/>", {
                        "class": "media-object img-rounded",
                        "src": comment.user.avatarUrl,
                        "style": "height: 38px;width: 38px"
                    }));

                    var mediaBodyElement=$( "<div/>", {
                        "class": "media-body "
                    }).append($("<h5/>", {
                        "class": "media-heading",
                        html: comment.user.name,
                        "style": "padding-top: 10px;"
                    })).append($("<div/>", {
                        html: comment.content
                    })).append($("<div/>", {
                        "class": "menu"
                    }).append($("<span/>", {
                        "class": "float-right",
                        html: moment(comment.gmtCreate).format('YYYY-MM-do h:mm')
                    })));
                    var mediaElement=$( "<div/>", {

                        "class": "media "
                    }).append(mediaLeftElement)
                        .append(mediaBodyElement);

                    var c=$( "<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments "
                    }).append(mediaElement);
                    twoComment.prepend(c);
                });

                comments.addClass("in");
                e.setAttribute("data-collapse","in");

                //添加按钮颜色
                e.classList.add("active");
            });
        }
    }
}

function selectTag(e){

    var value= e.getAttribute("data-tag");
    var previous = $("#tag").val();
    if(previous.split(',').indexOf(value) !=-1)
    {

    }
    else
    {
        if(previous){
            $("#tag").val(previous+','+value);
        }
        else{
            $("#tag").val(value);
        }
    }

}

function showSelectTag() {
    var select=$("#select-tag").get(0);

    var check=select.getAttribute("selectTag");

    if(!check)

    {
        $("#select-tag").eq(0).removeClass("publish-tag-tab");
        select.setAttribute("selectTag","tag");
    }


}
/*$(document).on("click", function(){
    var select=$("#select-tag").get(0);
    console.log(select);
    var check=select.getAttribute("selectTag");

    if(check)
    {
        $("#select-tag").eq(0).addClass("publish-tag-tab");
        select.removeAttribute("selectTag");

    }
    $("#tag").on("click", function(e){
        e.stopPropagation();
    });
    $("#select-tag").on("click", function(e){
        e.stopPropagation();
    });
});*/



