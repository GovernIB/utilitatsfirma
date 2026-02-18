
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="tipusDocumentalForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="tipusDocumentalFormTitle.jsp" %>
 
  <c:set var="contexte" value="${tipusDocumentalForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="tipusDocumentalFormCorePre.jsp" %>

  <%@include file="tipusDocumentalFormCore.jsp" %>

  <%@include file="tipusDocumentalFormCorePost.jsp" %>

  <%@include file="tipusDocumentalFormButtons.jsp" %>

  <c:if test="${not empty tipusDocumentalForm.sections}">
     <c:set var="__basename" value="tipusDocumental" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${tipusDocumentalForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/tipusDocumentalFormModificable.jsp" %>
  </c:if>

</form:form>


