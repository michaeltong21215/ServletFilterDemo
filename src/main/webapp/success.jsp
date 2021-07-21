<html>
<h2>Hello World landing JSP!</h2>
<body>
<%
String cookieName= null;
String jSessionid= null;
String token= null;
Cookie[] cookies = request.getCookies();
for(Cookie cookie: cookies){
   if(cookie.getName().equals("current cookie")){
      cookieName = cookie.getValue();
   }
   if(cookie.getName().equals("JSESSIONID")){
      jSessionid = cookie.getValue();
   }
    if(cookie.getName().equals("token")){
      token = cookie.getValue();
    }
}
%>
<%=cookieName%>
<%=jSessionid%>
<%=token%>
</body>
</html>
