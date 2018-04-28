function myLocation(lat, lng, id){
     $.ajax({
        type: "POST",
        url: "/myLocation",
        data: {            
            Id: id,
            lat: lat,
            lng: lng
        },
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType: "json",
        success: function (data) {
            //Ajax 성공시
            console.log("myLocation POST SUCCESS");
        }, error: function () {
            //Ajax 실패시
            console.log("myLocation POST FAIL");      
        }
    });
}
