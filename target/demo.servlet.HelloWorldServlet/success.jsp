<html>
<h2>Hello World landing JSP!</h2>
<body>
<%
String jSessionid= null;
String token= null;
Cookie[] cookies = request.getCookies();
for(Cookie cookie: cookies){
   if(cookie.getName().equals("JSESSIONID")){
      jSessionid = cookie.getValue();
   }
    if(cookie.getName().equals("token")){
      token = cookie.getValue();
    }
}
%>
<div>
<span> JSessionID: </span>
<span><%=jSessionid%> </span>
</div>
<span Token: </span>
<span><%=token%></span>
</div>
</body>
</html>
