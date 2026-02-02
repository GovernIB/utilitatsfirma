<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ValidacioFields" className="es.caib.utilitatsfirma.model.fields.ValidacioFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${suf:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ValidacioFields.VALIDACIOID)}">
        <th>${suf:getSortIcons(__theFilterForm,ValidacioFields.VALIDACIOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ValidacioFields.NOM)}">
        <th>${suf:getSortIcons(__theFilterForm,ValidacioFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ValidacioFields.SIGNATURAID)}">
        <th>${suf:getSortIcons(__theFilterForm,ValidacioFields.SIGNATURAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ValidacioFields.DETACHEDID)}">
        <th>${suf:getSortIcons(__theFilterForm,ValidacioFields.DETACHEDID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ValidacioFields.RESULTAT)}">
        <th>${suf:getSortIcons(__theFilterForm,ValidacioFields.RESULTAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ValidacioFields.INFORESULTAT)}">
        <th>${suf:getSortIcons(__theFilterForm,ValidacioFields.INFORESULTAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ValidacioFields.DATAINICI)}">
        <th>${suf:getSortIcons(__theFilterForm,ValidacioFields.DATAINICI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ValidacioFields.DATAFI)}">
        <th>${suf:getSortIcons(__theFilterForm,ValidacioFields.DATAFI)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${suf:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

