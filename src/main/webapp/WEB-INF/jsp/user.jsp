<?xml version="1.0" encoding="UTF-8"?>
<%@page contentType="text/html;charset=utf8"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

    <body>
        <h1>User oldal</h1>

        <p>Bejelentkezett felhasználó: <security:authentication property="principal.username" /></p>

        <security:authorize ifAllGranted="ROLE_ADMIN">
            <p><a href="<c:url value='/admin'/>">Admin oldal</a></p>
        </security:authorize>
    </body>
</html>