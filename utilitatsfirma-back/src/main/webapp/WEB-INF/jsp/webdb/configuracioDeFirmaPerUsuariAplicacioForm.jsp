
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="configuracioDeFirmaPerUsuariAplicacioForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="configuracioDeFirmaPerUsuariAplicacioFormTitle.jsp" %>
 
  <c:set var="contexte" value="${configuracioDeFirmaPerUsuariAplicacioForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="configuracioDeFirmaPerUsuariAplicacioFormCorePre.jsp" %>

  <%@include file="configuracioDeFirmaPerUsuariAplicacioFormCore.jsp" %>

  <%@include file="configuracioDeFirmaPerUsuariAplicacioFormCorePost.jsp" %>

  <%@include file="configuracioDeFirmaPerUsuariAplicacioFormButtons.jsp" %>

  <c:if test="${not empty configuracioDeFirmaPerUsuariAplicacioForm.sections}">
     <c:set var="__basename" value="configuracioDeFirmaPerUsuariAplicacio" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${configuracioDeFirmaPerUsuariAplicacioForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/configuracioDeFirmaPerUsuariAplicacioFormModificable.jsp" %>
  </c:if>

</form:form>


