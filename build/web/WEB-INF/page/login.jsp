<%-- 
    Document   : login
    Created on : Oct 3, 2011, 9:12:43 PM
    Author     : bandenk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:Toko DVD Online:.</title>
    </head>
    <body topmargin="0" leftmargin="0">
        <center>
            <table border="0" cellpadding="0" cellspacing="0" width="650">
                <tr>
                    <td><img src="img/banner.jpg"></td>
                </tr>
                <tr>
                    <td>
                        <table width="100%" bgcolor="black" cellpadding="2" cellspacing="2" border="0">
                            <tr>
                                <td><font color="white"><a href="controller?action=home">Home</a> | <a href="controller?action=goReg">Registration</a> | <a href="controller?action=view">Your Cart</a> | <a href="controller?action=loginadmin">Login</a></font></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <table cellpadding="0" cellspacing="2" border="0" width="100%">
                            <tr>
                                <td width="20%" valign="top">
                                    <!-- menu -->
                                    <table bgcolor="#CCCCCC" width="100%">
                                        <tr>
                                            <td><b>Category</b></td>
                                        </tr>
                                        <c:forEach items="${categories}" var="category">
                                            <tr><td><a href="controller?action=filter&id=${category.id}">${category.name}</a></td></tr>
                                        </c:forEach>
                                    </table>
                                </td>
                                <td align="center">
                                    <!-- content -->
                                    <h2>${msg}</h2>
                                    <form method="post" action="controller?action=finish">
                                    <table border="0" cellpadding="2" cellspacing="2">
                                        <tr>
                                            <td>Username </td>
                                            <td> : </td>
                                            <td><input type="text" name="username"></td>
                                        </tr>
                                        <tr>
                                            <td>Password </td>
                                            <td> : </td>
                                            <td><input type="password" name="password"></td>
                                        </tr>
                                    </table>
                                        <p><input type="Submit" value="Login"></p>
                                    </form>
                                    <h4>If you don't register yet, <a href="controller?action=goReg">Please Register</a></h4>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <small>&copy; I Gede Arya Wiratama 2011</small>
                    </td>
                </tr>
            </table>
        </center>
    </body>
</html>
