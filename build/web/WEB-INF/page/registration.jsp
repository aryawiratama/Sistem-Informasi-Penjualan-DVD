<%-- 
    Document   : registration
    Created on : Oct 3, 2011, 2:02:19 PM
    Author     : bandenk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
                                <td><font color="white"><a href="controller?action=home">Home</a> | <a href="controller?action=goReg">Registration</a> | <a href="controller?action=view">Your Cart</a> | <a href="controller?action=loginadmin">${status}</a></font></td>
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
                                <td align="center" valign="top">
                                    <!-- content -->
                                    <h2>Customer Registration</h2>
                                    <form action="controller?action=reg" method="post">
                                        <table border="0" cellpadding="2" cellspacing="2">
                                            <tr>
                                                <td>Username : </td>
                                                <td><input type="text" name="userName"/></td>
                                            </tr>
                                            <tr>
                                                <td>Password : </td>
                                                <td><input type="password" name="password"/></td>
                                            </tr>
                                            <tr>
                                                <td>Real Name : </td>
                                                <td><input type="text" name="realName"/></td>
                                            </tr>
                                            <tr>
                                                <td>Address 1 : </td>
                                                <td><input type="text" name="address1"/> </td>
                                            </tr>
                                            <tr>
                                                <td>Address 2 : </td>
                                                <td><input type="text" name="address2"/></td>
                                            </tr>
                                            <tr>
                                                <td>City : </td>
                                                <td><input type="text" name="city"/></td>
                                            </tr>
                                            <tr>
                                                <td>State : </td>
                                                <td><input type="text" name="state" /></td>
                                            </tr>
                                            <tr>
                                                <td>Zip : </td>
                                                <td><input type="text" name="zip" /></td>
                                            </tr>
                                            <tr>
                                                <td>Email : </td>
                                                <td><input type="text" name="email"/></td>
                                            </tr>
                                            <tr>
                                                <td>Phone : </td>
                                                <td><input type="text" name="phone"/></td>
                                            </tr>
                                        </table>
                                        <p><input type="Submit" value="Save"/></p>
                                    </form>
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