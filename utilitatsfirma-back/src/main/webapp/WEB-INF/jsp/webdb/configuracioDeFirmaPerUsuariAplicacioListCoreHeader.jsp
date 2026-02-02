<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ConfiguracioDeFirmaPerUsuariAplicacioFields" className="es.caib.utilitatsfirma.model.fields.ConfiguracioDeFirmaPerUsuariAplicacioFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${suf:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ConfiguracioDeFirmaPerUsuariAplicacioFields.CONFIGSPERUSRAPPID)}">
        <th>${suf:getSortIcons(__theFilterForm,ConfiguracioDeFirmaPerUsuariAplicacioFields.CONFIGSPERUSRAPPID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ConfiguracioDeFirmaPerUsuariAplicacioFields.USUARIAPLICACIOID)}">
        <th>${suf:getSortIcons(__theFilterForm,ConfiguracioDeFirmaPerUsuariAplicacioFields.USUARIAPLICACIOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ConfiguracioDeFirmaPerUsuariAplicacioFields.USUARIAPLICACIOCONFIGID)}">
        <th>${suf:getSortIcons(__theFilterForm,ConfiguracioDeFirmaPerUsuariAplicacioFields.USUARIAPLICACIOCONFIGID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${suf:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

