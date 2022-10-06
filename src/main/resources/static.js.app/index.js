var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function (){
            _this.save();
        });

        $('#btn-update').on('click', function (){ <!-- btn-update란 id를 가진 HTML 엘리먼트에 click 이벤트가 발생할 때 -->
            _this.update();
        });

        $('#btn-delete').on('click', function (){
            _this.delete();
        });
    },
    save : function () {
        var data = {
            title : $('#title').val(),
            author : $('#author').val(),
            content : $('#content').val()
        };

        $.ajax({
            type : 'POST',
            url : '/api/v1/posts',
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data)
        }).done(function (){
            alert('글이 등록되었습니다.');
            window.location.href = '/'; <!-- 글 등록 성공 시 메인페이지로 이동 -->
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT', <!-- PostsApiController에 API에서 이미 @PutMapping 선언하여 반드시 PUT 사용 -->
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (){
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type : 'DELETE',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function (){
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
};

main.init();