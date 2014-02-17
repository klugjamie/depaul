<%-- 
    Document   : ListTicket
    Created on : Feb 16, 2014, 7:10:29 PM
    Author     : jklug3
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tickets</title>
    </head>
    <body>
        <h1>List of Tickets currently in Database</h1>
        
    <table id="ticketListTable" border="3">
<tr >
    <th bgcolor=>ID</th>
    <th bgcolor=>Name</th>
    <th bgcolor=>Title</th>
</tr>
<c:forEach var="ticket" begin="0" items="${requestScope.ticketList}">
<tr>
    <td>${ticket.id}&nbsp;&nbsp;</td> 
    <td>${ticket.name}&nbsp;&nbsp;</td> 
    <td>${ticket.title}&nbsp;&nbsp;</td> 
</tr> 

</c:forEach>

</table>
    <jsp:include page="footer.jspf"/>    
    </body>
</html>


   
