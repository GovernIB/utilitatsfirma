<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${tipusDocumentalFilterForm.contexte}"/>
  <c:set var="formName" value="tipusDocumental" />
  <c:set var="__theFilterForm" value="${tipusDocumentalFilterForm}" />
  <c:if test="${empty tipusDocumentalFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="tipusDocumental.tipusDocumental"/>
  </c:if>
  <c:if test="${not empty tipusDocumentalFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${tipusDocumentalFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty tipusDocumentalFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="tipusDocumental.tipusDocumental"/>
  </c:if>
  <c:if test="${not empty tipusDocumentalFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${tipusDocumentalFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.tipusDocumental.submit();  
  }
</script>
