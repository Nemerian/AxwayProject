<%-- 
    Document   : CursForm
    Created on : Jun 22, 2018, 3:12:57 PM
    Author     : xenot
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Curs Application</title>
</head>
<body>
    <center>
        <h1>Curs Management</h1>
        <h2>
            <a href="/newc">Add New Curs</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/listc">List All Curss</a>
             
        </h2>
    </center>
    <div align="center">
        <c:if test="${curs != null}">
            <form action="updatec" method="post">
        </c:if>
        <c:if test="${curs == null}">
            <form action="insertc" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${curs != null}">
                        Edit Curs
                    </c:if>
                    <c:if test="${curs == null}">
                        Add New Curs
                    </c:if>
                </h2>
            </caption>
                <c:if test="${curs != null}">
                    <input type="hidden" name="id" value="<c:out value='${curs.id}' />" />
                </c:if>           
            <tr>
                <th>Cod: </th>
                <td>
                    <input type="text" name="cod" size="45"
                            value="<c:out value='${curs.cod}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Titlu: </th>
                <td>
                    <input type="text" name="titlu" size="45"
                            value="<c:out value='${curs.titlu}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>