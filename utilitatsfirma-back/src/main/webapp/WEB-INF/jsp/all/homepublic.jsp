<%@page import="org.springframework.security.core.Authentication"
%><%@page import="org.springframework.context.i18n.LocaleContextHolder"
%><%@ page language="java" 
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp" 
%>
<div class="clear"></div>
<div class="spacer"></div>

<div>
<br/>
<center>
<img src="<c:url value="/img/app-logo.png"/>"  alt="UtilitatsFirma" title="UtilitatsFirma"/>

<br/>
<br/>

<h3>Utilitats de Firma</h3> <br/>

Es tracta d'un aplicactiu que ofereix Serveis Web per signatura digital en servidor, validaci&oacute; de signatures digitals i upgrade de firmes digitals.  

<br/>
<br/>
<table border="0" >
<tr>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td valign="top">
<a href="https://governdigital.fundaciobit.org" target="_blank">
<img src="<c:url value="/img/fundaciobit.png"/>"  alt="Govern Digital" title="Govern Digital"/>
</a>
</td>
</tr>
</table>
<br/>
</center>
 
</div>

<br/>

LOGIN ANONIM <br/>
Locale = <%=LocaleContextHolder.getLocale() %> <br/>
lang = ${lang} <br/>
<br/>

<c:if test="${suf:isDesenvolupament()}">
Only in Development Mode
</c:if>
