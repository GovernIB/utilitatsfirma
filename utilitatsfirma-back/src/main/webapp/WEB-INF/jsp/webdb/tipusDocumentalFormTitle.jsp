<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(tipusDocumentalForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(tipusDocumentalForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty tipusDocumentalForm.titleCode}">
    <fmt:message key="${tipusDocumentalForm.titleCode}" >
      <fmt:param value="${tipusDocumentalForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty tipusDocumentalForm.entityNameCode}">
      <fmt:message var="entityname" key="tipusDocumental.tipusDocumental"/>
    </c:if>
    <c:if test="${not empty tipusDocumentalForm.entityNameCode}">
      <fmt:message var="entityname" key="${tipusDocumentalForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${tipusDocumentalForm.nou?'genapp.createtitle':(tipusDocumentalForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty tipusDocumentalForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(tipusDocumentalForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(tipusDocumentalForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${tipusDocumentalForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>