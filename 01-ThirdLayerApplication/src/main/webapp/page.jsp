<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询所有学生</title>
    <script src="js/jquery-1.8.3.min.js"></script>
</head>
<body>
<%--    <div style="width: 100%;height: 50px">--%>
<%--        <div style="float: left;">--%>
<%--            <ul style="list-style-type: none;display: inline;">--%>
<%--                <li style="float: left;padding: 5px;">校园外卖管理系统</li>--%>
<%--                <li style="float: left;padding: 5px;"><a href="menu">导航分类管理</a></li>--%>
<%--                <li style="float: left;padding: 5px;">外卖商家管理</li>--%>
<%--                <li style="float: left;padding: 5px;">外卖菜品管理</li>--%>
<%--                <li style="float: left;padding: 5px;">外卖订单管理</li>--%>
<%--                <li style="float: left;padding: 5px;">学生用户管理</li>--%>
<%--            </ul>--%>
<%--        </div>--%>
<%--        <div style="float: right; width: 300px;">--%>
<%--            用户:<img src="${faceSrc}" width="30px" height="20px">${username}&nbsp--%>
<%--            <a href="userInfo?username=${username}">个人中心</a>&nbsp--%>
<%--            <a href="logout">退出登录</a>--%>
<%--        </div>--%>
<%--    </div>--%>
    <div style="width: 100%">
        <h1 align="center">学生信息</h1>
    </div>
<%--    <div align="center">--%>
<%--        <form action="fuzzy">--%>
<%--            <input type="text" name="name" placeholder="学生姓名">--%>
<%--            <input type='radio' name='gender' value='男'>男--%>
<%--            <input type='radio' name='gender' value='女'>女--%>
<%--            <input type="submit" value="查询">--%>
<%--        </form>--%>
<%--    </div>--%>
    <form action="delAll">
        <table align="center" border="1px">
            <tr>
<%--                <th><input type="checkbox" onclick="selectAll(this)" name="parentCheck">全选</th>--%>
                <th>编号</th>
                <th>姓名</th>
                <th>年龄</th>
                <th>性别</th>
                <th>信息</th>
                <th>操作</th>
            </tr>

            <c:if test="${not empty pageInfo.list}">
                <c:forEach var="student" items="${pageInfo.list}">
                    <tr>
<%--                        <td><input type="checkbox" name="ck" value=${student.id} onclick="selectSon()"></td>--%>
                        <td>${student.id}</td>
                        <td>${student.name}</td>
                        <td>${student.age}</td>
                        <td>${student.gender}</td>
                        <td>${student.info}</td>
                        <td>
                            <a href="updateValue?id=${student.id}">修改</a>
                            <a onclick="del(${student.id});">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            <tr>
                <td align="center"><input type="submit" value="删除选中"></td>
                <td colspan="6" align="center"><a href="add.html">添加学生</a></td>
            </tr>
        </table>
    </form>

    <form action="page" method="get">
        <table align="center" border="1px">
            <tr>
                <c:if test="${pageInfo.pageNo == 1}">
                    <td><a style="color: red">首页</a></td>
                </c:if>

                <c:if test="${pageInfo.pageNo != 1}">
                    <td><a href="page?pageNo=1">首页</a></td>
                </c:if>


                <c:if test="${pageInfo.pageNo == 1}">
                    <td><a style="color: red">上一页</a></td>
                </c:if>

                <c:if test="${pageInfo.pageNo != 1}">
                    <td><a href="page?pageNo=${pageInfo.pageNo - 1}">上一页</a></td>
                </c:if>

                <c:forEach begin="1" end="${pageInfo.pageCount}" var="pageNo" varStatus="pageStatus">
                    <td><input type="submit" name="pageNo" value="${pageNo}"></td>
                </c:forEach>

                <c:if test="${pageInfo.pageNo == pageInfo.pageCount}">
                    <td><a style="color: red">下一页</a></td>
                </c:if>

                <c:if test="${pageInfo.pageNo != pageInfo.pageCount}">
                    <td><a href="page?pageNo=${pageInfo.pageNo + 1}">下一页</a></td>
                </c:if>

                <c:if test="${pageInfo.pageNo == pageInfo.pageCount}">
                    <td><a style="color: red">尾页</a></td>
                </c:if>

                <c:if test="${pageInfo.pageNo != pageInfo.pageCount}">
                    <td><a href="page?pageNo=${pageInfo.pageCount}">尾页</a></td>
                </c:if>
                <td>共${pageInfo.totalCount}条内容</td>
                <td>共${pageInfo.pageCount}页</td>
            </tr>
        </table>
    </form>
<%--<script>--%>
<%--    function del(id) {--%>
<%--        if (confirm('您是否确认删除？') === true) {--%>
<%--            window.location.href = "delete?id=" + id--%>
<%--        }--%>
<%--    }--%>

<%--    // 全选/取消全选事件--%>
<%--    function selectAll(obj){--%>
<%--        let isCheck = obj.checked;--%>
<%--        if(isCheck) {--%>
<%--            $("input[type='checkbox']").each(function () {--%>
<%--                //循环赋值给每个复选框选中--%>
<%--                this.checked = isCheck;--%>
<%--            });--%>
<%--        }else {--%>
<%--            $("input[type='checkbox']").each(function () {--%>
<%--                //取消选中--%>
<%--                this.checked = isCheck;--%>
<%--            });--%>
<%--        }--%>
<%--    };--%>

<%--    // 同步取消父元素勾选事件--%>
<%--    function selectSon(){--%>
<%--        //子元素取消选中个数>1条--%>
<%--        if($("input[name='ck']:not(:checked)").length > 0){--%>
<%--            //父元素取消勾选--%>
<%--            $("input[name='parentCheck']").attr("checked", false);--%>
<%--        }else {--%>
<%--            $("input[name='parentCheck']").attr("checked", true);--%>
<%--        }--%>
<%--    };--%>

<%--</script>--%>
</body>
</html>
