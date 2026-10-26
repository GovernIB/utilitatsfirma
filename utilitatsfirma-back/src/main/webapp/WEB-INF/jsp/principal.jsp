<%@page import="org.springframework.security.core.Authentication"
%><%@page import="org.springframework.context.i18n.LocaleContextHolder"
%><%@page import="org.springframework.security.core.context.SecurityContext"
%><%@page import="org.springframework.security.core.context.SecurityContextHolder"
%><%@ page language="java" 
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp" 
%>
<div class="clear"></div>

<div>
<p>&nbsp;</p>
<p>&nbsp;</p>
<div style="text-align:center;">
<img src="<c:url value="/img/app-logo.png"/>"  alt="UtilitatsFirma" >
<p>&nbsp;</p>
<p><span style="font-size: 1.75rem;">Utilitats de Firma</span> <p/>
<p>&nbsp;</p>
<p>Es tracta d'un aplicactiu que ofereix Serveis Web per signatura digital en servidor, validaci&oacute; de signatures
   digitals i upgrade de firmes digitals.</p>
 <p>&nbsp;</p>
<p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p>
<div class="spacer"></div>
<div>
<a href="https://governdigital.fundaciobit.org" target="_blank">
<img src="<c:url value="/img/fundaciobit.png"/>"  alt="Govern Digital" />
</a>
<p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p>
</div>

</div>
 
</div>




<c:if test="${suf:isDesenvolupament()}">
<b>Only in Development Mode</b>

<div class="dev-info-item">Username: ${loginInfo.username}</div>

<div class="dev-info-item">&#36;{suf:hasRole('ROLE_ADMIN')}= ${suf:hasRole('ROLE_ADMIN')}</div>
<div class="dev-info-item">&#36;{suf:hasRole('ROLE_USER') }= ${suf:hasRole('ROLE_USER') }</div>
<div class="dev-info-item">Locale = <%=LocaleContextHolder.getLocale() %></div>
<div class="dev-info-item">lang = ${lang}</div>
<div class="dev-info-title">UserInformation:</div>
 <c:if test="${not empty loginInfo.userInfo}">
    <div class="user-info-field">name= ${loginInfo.userInfo.name}</div>
    <div class="user-info-field">surname1= ${loginInfo.userInfo.surname1}</div>
    <div class="user-info-field">surname2= ${loginInfo.userInfo.surname2}</div>
    <div class="user-info-field">email= ${loginInfo.userInfo.email}</div>
    <div class="user-info-field">nif= ${loginInfo.userInfo.attributes["nif"]}</div>
  <div class="spacer"></div>
</c:if>
<c:if test="${empty loginInfo.userInfo}">
    Error carregant Plugin UserInfo. Revisar logs per m&eacute;s informaci&oacute;.
</c:if>
</c:if>