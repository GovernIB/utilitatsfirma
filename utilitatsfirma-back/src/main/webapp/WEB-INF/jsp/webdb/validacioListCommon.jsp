<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${validacioFilterForm.contexte}"/>
  <c:set var="formName" value="validacio" />
  <c:set var="__theFilterForm" value="${validacioFilterForm}" />
  <c:if test="${empty validacioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="validacio.validacio"/>
  </c:if>
  <c:if test="${not empty validacioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${validacioFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty validacioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="validacio.validacio"/>
  </c:if>
  <c:if test="${not empty validacioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${validacioFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.validacio.submit();  
  }
</script>
