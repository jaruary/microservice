(function () {

    $.ajax
    ({
        url: "http://localhost:8080/api/connect/getById?id=0",
        type: 'GET',
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJkYXZlIiwiaWF0IjoxNDc2OTEzMzk1LCJleHAiOjE1MDg0NDkzOTUsImF1ZCI6IiIsInN1YiI6IiIsImtleSI6InZhbHVlIn0.LAzN0sGThzh7O9uJGdutmojLOZwPOkz4ySxA_u4j96Q');
        },
        async: true,
        success: function (data) {
            var script = document.createElement("script");
            script.innerHTML = data;
            $("head").append(script);
        },
        error: function (err) {
            console.log(JSON.stringify(err));
        }
    });

    $.ajax
    ({
        url: "http://localhost:8080/api/customer/getAll",
        type: 'GET',
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJkYXZlIiwiaWF0IjoxNDc2OTEzMzk1LCJleHAiOjE1MDg0NDkzOTUsImF1ZCI6IiIsInN1YiI6IiIsImtleSI6InZhbHVlIn0.LAzN0sGThzh7O9uJGdutmojLOZwPOkz4ySxA_u4j96Q');
        },
        async: true,
        success: function (data) {

            $("#div-json").append(JSON.stringify(data));

            $.each(data, function () {
                var html = "<p id='name" + this.id + "'>" + this.firstName + " " + this.lastName + "</p>";
                $("#div-result").append(html);
            });
        },
        error: function (err) {
            console.log(JSON.stringify(err));
        }
    });

})();