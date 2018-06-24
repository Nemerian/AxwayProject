<%-- 
    Document   : CursForm
    Created on : Jun 22, 2018, 3:12:57 PM
    Author     : xenot
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEnidsing="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Curss Store Application</title>
</head>
<body>
    <center>
        <h1>Curs Management</h1>
        <h2>
            <a href="/new">Add New Curs</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/list">List All Curs</a>
             
        </h2>
    </center>
    <div align="center">
        <c:if test="${curs != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${curs == null}">
            <form action="insert" method="post">
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
            <tr>
                <th>Title: </th>
                <td>
                    <input type="text" name="ids" size="45"
                            value="<c:out value='${curs.ids}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Author: </th>
                <td>
                    <input type="text" name="idc" size="45"
                            value="<c:out value='${curs.idc}' />"
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