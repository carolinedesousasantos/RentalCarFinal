<%
    //Apaga o cookie do lado do servidor e a sess�o � invalidada.
    session.invalidate();
    response.sendRedirect("index.jsp");
%>
