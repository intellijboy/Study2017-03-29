<%--
  Created by IntelliJ IDEA.
  User: liuburu
  Date: 2017/3/17
  Time: 8:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试首页</title>
</head>
<body>
<h2>测试RequestBody</h2>
<button id="stringbtn">单个String按钮</button>
<button id="userBth1">单个User按钮</button>
<button id="userBtn2">多个User按钮</button>
<br>
<form id="userForm">
    ID<input name="id"><br>
    Name<input name="name"><br>
    Age<input name="age"><br>
</form>
<button id="userBtn3">提交表单</button>
</body>
</html>
<script src="http://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script>

    $(function () {
        stringBtnClick();//字符串
        userBth1Click();//单个User
        userBtn2Click();//多个User
        userBtn3Click();//表单User
    });

    function stringBtnClick() {
    //    var userData = JSON.stringify(jsonStr);
        $("#stringbtn").bind("click",function () {
            //var userObj = '{"id":10086,"name":"admin","age","24"}';//注意：是json字符串而不是json对象
//            var user1 = new Object();
//            user1.id = 10086;
//            user1.name = '刘卜铷';
//            user1.age = 23;
           // var userData = JSON.stringify(userObj);
           // var userDataStr = {"id":102,"name":"liuburu","age":12};
            var userDataStr = {"id":"102","name":"liuburu","age":"12"};
            //console.log(userDataStr);
            var userData = JSON.stringify(userDataStr);
            $.ajax({
                url:"/test/string",
                type:"post",
                data:userData,
                contentType:"application/json",
                success:function (data) {
                    console.log(data);
                }
            })
        });
    }

    function userBth1Click(){
        $("#userBth1").bind("click",function () {
            var user1 = new Object();
            user1.id = 10086;
            user1.name = '刘卜铷';
            user1.age = 23;
            var userData = JSON.stringify(user1);
            $.ajax({
                url:"/test/user",
                data:userData,
                type:"post",
                contentType:"application/json",
                success:function (user) {
                    console.log(user);
                }
            })
        });
    }

    function userBtn2Click(){
        $("#userBtn2").bind("click",function () {
            var users = new Array();
            var user1 = new Object();
            user1.id = 10086;
            user1.name = '刘卜铷';
            user1.age = 23;
            var user2 = new Object();
            user2.id = 10087;
            user2.name = '卡卡罗特';
            user2.age = 65;
            users.push(user1);
            users.push(user2);
            var userData = JSON.stringify(users);
            $.ajax({
                url:"/test/users",
                data:userData,
                type:"post",
                contentType:"application/json",
                success:function (user) {
                    console.log(user);
                }
            })
        });
    }

    function userBtn3Click() {
        $("#userBtn3").bind("click",function () {
            console.log("rewt   ");
            //var userData = $("#userForm").serialize();//键值对请求参数
            var userObj = $("#userForm").serializeObject();//序列化成json参数
            var userData = JSON.stringify(userObj);
            $.ajax({
                url:"/test/form",
                data:userData,
                type:"post",
                contentType:"application/json",
                success:function (user) {
                    console.log(user);
                }
            })
        })
    }


    /*序列化表单参数为json参数*/
    $.fn.serializeObject = function() {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function() {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [ o[this.name] ];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    }
</script>
