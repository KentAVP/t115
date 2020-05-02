<%--
  Created by IntelliJ IDEA.
  User: alexe
  Date: 02.05.2020
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<div align="center">
    <h1>Register Form</h1>
    <form action="<%= request.getContextPath() %>/register" method="post">
        <table style="with: 80%">
            <tr>
                <td>Имя пользователя: </td>
                <td><input type="text" name="name" /></td>
            </tr>
            <tr>
                <td>Возраст: </td>
                <td><input type="text" name="age" /></td>
            </tr>
            <tr>
                <th>Тип пользователя: </th>
                <td>
                    <input type="radio" name="role" value="user"/> user
                    <input type="radio" name="role" value="admin"/> admin
                </td>
            </tr>
            <tr>
                <td>UserName</td>
                <td><input type="text" name="username" /></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password" /></td>
            </tr>
        </table>
        <input type="submit" value="Submit" />
    </form>
</div>
<center>
    <h1><a href="/">Логин пользователя</a></h1>
</center>
</body>
</html>
