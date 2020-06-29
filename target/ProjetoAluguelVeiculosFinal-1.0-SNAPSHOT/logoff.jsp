<%
    //Apaga o cookie do lado do servidor e a sessão é invalidada.
    session.invalidate();
    response.sendRedirect("index.jsp");
%>
