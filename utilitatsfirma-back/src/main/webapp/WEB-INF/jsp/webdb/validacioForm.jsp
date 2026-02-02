
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="validacioForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="validacioFormTitle.jsp" %>
 
  <c:set var="contexte" value="${validacioForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="validacioFormCorePre.jsp" %>

  <%@include file="validacioFormCore.jsp" %>

  <%@include file="validacioFormCorePost.jsp" %>

  <%@include file="validacioFormButtons.jsp" %>

  <c:if test="${not empty validacioForm.sections}">
     <c:set var="__basename" value="validacio" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${validacioForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/validacioFormModificable.jsp" %>
  </c:if>

</form:form>


