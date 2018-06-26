<%-- 
    Document   : StudentList
    Created on : Jun 22, 2018, 2:58:15 PM
    Author     : xenot
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Students Application</title>
</head>
<body>
    <center>
        <h1>Students Management</h1>
        <h2>
            <a href="/news">Add New Student</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/lists">List All Students</a>
             
        </h2>
    </center>
 <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Students</h2></caption>
            <tr>
                <th>ID</th>
                <th>Cnp</th>
                <th>Nume</th>
            </tr>
            <c:forEach var="student" items="${listStudent}">
                <tr>
                    <td><c:out value="${student.id}" /></td>
                    <td><c:out value="${student.cnp}" /></td>
                    <td><c:out value="${student.nume}" /></td>
                    <td>
                        <a href="/edits?id=<c:out value='${student.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/deletes?id=<c:out value='${student.id}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    <center>
        <h1>Curs Management</h1>
        <h2>
            <a href="/newc">Add New Curs</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/listsc">List All Cursuri</a>
             
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Curss</h2></caption>
            <tr>
                <th>ID</th>
                <th>Cod</th>
                <th>Titlu</th>
            </tr>
            <c:forEach var="curs" items="${listCurs}">
                <tr>
                    <td><c:out value="${curs.id}" /></td>
                    <td><c:out value="${curs.cod}" /></td>
                    <td><c:out value="${curs.titlu}" /></td>
                    <td>
                        <a href="/editc?id=<c:out value='${curs.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/deletec?id=<c:out value='${curs.id}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>