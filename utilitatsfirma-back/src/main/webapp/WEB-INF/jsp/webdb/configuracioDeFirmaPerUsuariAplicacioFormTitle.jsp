<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(configuracioDeFirmaPerUsuariAplicacioForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(configuracioDeFirmaPerUsuariAplicacioForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty configuracioDeFirmaPerUsuariAplicacioForm.titleCode}">
    <fmt:message key="${configuracioDeFirmaPerUsuariAplicacioForm.titleCode}" >
      <fmt:param value="${configuracioDeFirmaPerUsuariAplicacioForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty configuracioDeFirmaPerUsuariAplicacioForm.entityNameCode}">
      <fmt:message var="entityname" key="configuracioDeFirmaPerUsuariAplicacio.configuracioDeFirmaPerUsuariAplicacio"/>
    </c:if>
    <c:if test="${not empty configuracioDeFirmaPerUsuariAplicacioForm.entityNameCode}">
      <fmt:message var="entityname" key="${configuracioDeFirmaPerUsuariAplicacioForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${configuracioDeFirmaPerUsuariAplicacioForm.nou?'genapp.createtitle':(configuracioDeFirmaPerUsuariAplicacioForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty configuracioDeFirmaPerUsuariAplicacioForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(configuracioDeFirmaPerUsuariAplicacioForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(configuracioDeFirmaPerUsuariAplicacioForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${configuracioDeFirmaPerUsuariAplicacioForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>