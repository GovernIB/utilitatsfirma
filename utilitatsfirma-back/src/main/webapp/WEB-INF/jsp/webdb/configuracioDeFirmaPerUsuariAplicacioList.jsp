<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<form:form name="configuracioDeFirmaPerUsuariAplicacio" cssClass="form-search"  modelAttribute="configuracioDeFirmaPerUsuariAplicacioFilterForm" 
        method="${(empty method)?'post':method}"  enctype="multipart/form-data">

  <%@include file="configuracioDeFirmaPerUsuariAplicacioListCommon.jsp" %>
  <div id="${formName}_listheader" class="filterLine lead" style="margin-bottom:10px">
    <%@include file="configuracioDeFirmaPerUsuariAplicacioListHeaderButtons.jsp" %>
    <%-- ADD HERE NEW HEADER BUTTONS (Multiple Select or similar to add item)  --%>

  </div>
  <%@include file="configuracioDeFirmaPerUsuariAplicacioListSubtitle.jsp" %>
  <%@include file="configuracioDeFirmaPerUsuariAplicacioListFilterBy.jsp" %>
  <%-- Inici de div d'AGRUPACIO i TAULA CONTINGUTS --%>  
  <div>
  <%@include file="configuracioDeFirmaPerUsuariAplicacioListGroupBy.jsp" %>
  <%-- Inici de div de TAULA CONTINGUTS --%>
  <div style="width: 100%;">
  <%@include file="configuracioDeFirmaPerUsuariAplicacioListCore.jsp" %>
  <c:if test="${not empty configuracioDeFirmaPerUsuariAplicacioItems && __theFilterForm.footerListVisible}">
          <%@include file="webdbPagination.jsp" %>

  </c:if>

  </div> <%-- Final de div de TAULA CONTINGUTS --%>
  <%--  ADD HERE OTHER CONTENT --%>

  <c:if test="${__theFilterForm.attachedAdditionalJspCode}">
          <%@include file="../webdbmodificable/configuracioDeFirmaPerUsuariAplicacioListModificable.jsp" %>
  </c:if>
  
  </div> <%-- Final de div d'AGRUPACIO i TAULA CONTINGUTS --%>

</form:form> 
    

