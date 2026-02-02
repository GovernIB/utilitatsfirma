<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${configuracioDeFirmaPerUsuariAplicacioFilterForm.contexte}"/>
  <c:set var="formName" value="configuracioDeFirmaPerUsuariAplicacio" />
  <c:set var="__theFilterForm" value="${configuracioDeFirmaPerUsuariAplicacioFilterForm}" />
  <c:if test="${empty configuracioDeFirmaPerUsuariAplicacioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="configuracioDeFirmaPerUsuariAplicacio.configuracioDeFirmaPerUsuariAplicacio"/>
  </c:if>
  <c:if test="${not empty configuracioDeFirmaPerUsuariAplicacioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${configuracioDeFirmaPerUsuariAplicacioFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty configuracioDeFirmaPerUsuariAplicacioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="configuracioDeFirmaPerUsuariAplicacio.configuracioDeFirmaPerUsuariAplicacio"/>
  </c:if>
  <c:if test="${not empty configuracioDeFirmaPerUsuariAplicacioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${configuracioDeFirmaPerUsuariAplicacioFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.configuracioDeFirmaPerUsuariAplicacio.submit();  
  }
</script>
