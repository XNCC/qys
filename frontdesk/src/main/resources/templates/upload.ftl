<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <link rel="stylesheet" href="/static/layui/css/layui.css">
    <script src="static/layui/layui.js"></script>
    <style>
        #download {
            display: none;
        }

        a {
            disabled: disabled;
        }
    </style>
</head>

<body>
<br/>
<br/>
<blockquote class="layui-elem-quote layui-text">
    上传演示位置
</blockquote>
<div>
    <input id="upload-input" type="file" name="file">
    <button id="submitfile" type="button" class="layui-btn layui-btn-warm">上传</button>
</div>

<br/>
<br/>
<br/>
<blockquote class="layui-elem-quote layui-text">
    下载刚刚上传的文件演示位置,只有上传后才显示下载链接
</blockquote>
<h1><a href="" id="download">下载刚刚上传的文件</a></h1>
<br/>
<br/>
<br/>
<blockquote class="layui-elem-quote layui-text">
    下最近插入的一条元数据信息展示演示位置
</blockquote>
<div>
    <h3>文件类型:</h3><h4 id="filetype"></h4>
    <h3>文件大小:</h3><h4 id="filesize"></h4>
    <h3> 旧文件名称:</h3><h4 id="dileoldname"></h4>
    <h3> 文件创建时间:</h3><h4 id="filecreatetime"></h4>
    <h3>文件路径名:</h3><h4 id="filepath"></h4>
</div>

<blockquote class="layui-elem-quote layui-text">
    查询一条数据，根据uuid
</blockquote>
<input type="text" id="search">
文件大小：<p id="result"></p>
文件类型：<p id="result1"></p>
文件名称： <p id="result2"></p>
文件创建时间： <p id="result3"></p>
文件保存路径： <p id="result4"></p>


<br/>
<br/>
<br/>
<blockquote class="layui-elem-quote layui-text">
    最近插入的十条数据演示位置
</blockquote>
<table class="table table-striped">
    <thead>
    <th>#</th>
    <th>filesize</th>
    <th>filetype</th>
    <th>fileoldname</th>
    <th>filecreatetime</th>
    <th>filesavepath</th>
    </thead>
    <tbody>
    <#if list??>
        <#list list as item >
            <tr>
                <td>${item.id!}</td>
                <td>${item.filesize!}</td>
                <td>${item.filetype!}</td>
                <td>${item.fileoldname!}</td>
                <td>${item.filecreatetime!}</td>
                <td><a href="http://127.0.0.1:8080/download?path=${item.filesavepath!}">${item.filesavepath!}
                        --->>点击可下载</a></td>
            </tr>
        </#list>
    </#if>
    </tbody>
</table>

</body>
<script>
    $('#submitfile').click(function () {
        var formData = new FormData();
        //接受的时候注意name=file
        var path = $("#upload-input").val();
        var name = getFileName(path);
        formData.append("file", $("#upload-input")[0].files[0]);
        formData.append("name", name);
        $.post("/createSign", {}, function (data) {
            console.log(data)
            if (data === "true") {
                $.ajax({
                    url: "http://127.0.0.1:8080/upload",
                    type: "POST",
                    data: formData,    //表单序列化 ,【注意】上传文件的文件流是无法被序列化并传递的
                    processData: false,   //  告诉jquery不要处理发送的数据
                    contentType: false,   // 告诉jquery不要设置content-Type请求头
                    dataType: "json",
                    success: function (data) {
                        console.log(data);
                        $("#result").text(data.filesize);
                        $("#filetype").text(data.filetype);
                        $("#dileoldname").text(data.fileoldname);
                        $("#filecreatetime").text(data.filecreatetime);
                        $("#filepath").text(data.filesavepath);
                        $("#download").attr("href", "http://127.0.0.1:8080/download?path=" + $("#filepath").text());
                        $("#download").show();
                    }
                });
            } else {
                layer.msg('上传签名验证失败');
            }
        }, "text")

    });

    function getFileName(o) {
        var pos = o.lastIndexOf("\\");
        return o.substring(pos + 1);
    }

    $("#search").keyup(function () {
        $.post("/createSign", {}, function (data) {
            if (data === "true") {
                var uuid = $("#search").val();
                var formdata1 = new FormData();
                console.log(uuid);
                formdata1.append("uuid", uuid);
                formdata1.append("name", "name");
                console.log(formdata1.get("name"));
                console.log(formdata1.get("uuid"));
                $.ajax({
                    url: "http://127.0.0.1:8080/getOneMeta",
                    type: "POST",
                    data: formdata1,    //表单序列化 ,【注意】上传文件的文件流是无法被序列化并传递的
                    processData: false,   //  告诉jquery不要处理发送的数据
                    contentType: false,   // 告诉jquery不要设置content-Type请求头
                    dataType: "json",
                    success: function (data) {
                        console.log(data);
                        $("#result").text(data.filesize);
                        $("#result1").text(data.filetype);
                        $("#result2").text(data.fileoldname);
                        $("#result3").text(data.filecreatetime);
                        $("#result4").text(data.filesavepath);
                    }
                });
            } else {
                layer.msg('上传签名验证失败');
            }

        }, "text");
    })
</script>
</html>