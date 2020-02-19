function post() {
    var questionId = $("#questionId").val();
    var comment = $("#comment").val();
    var username = $("#username").val();
    if(username == null || username == ""){
        alert("请登录")
    }else {
        alert(username);
        $.ajax({
            type: "POST",
            url: "/comment",
            contentType:"application/json",
            data: JSON.stringify({
                "parentId":questionId,
                "comment":comment,
                "type":1
            }),
            success: function (response) {
                console.log(response);
            },
            dataType: "json"
        });
    }
}