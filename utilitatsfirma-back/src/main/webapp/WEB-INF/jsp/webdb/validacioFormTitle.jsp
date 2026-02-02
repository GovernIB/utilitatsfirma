<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(validacioForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(validacioForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty validacioForm.titleCode}">
    <fmt:message key="${validacioForm.titleCode}" >
      <fmt:param value="${validacioForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty validacioForm.entityNameCode}">
      <fmt:message var="entityname" key="validacio.validacio"/>
    </c:if>
    <c:if test="${not empty validacioForm.entityNameCode}">
      <fmt:message var="entityname" key="${validacioForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${validacioForm.nou?'genapp.createtitle':(validacioForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty validacioForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(validacioForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(validacioForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${validacioForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>