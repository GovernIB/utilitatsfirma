<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TipusDocumentalFields" className="es.caib.utilitatsfirma.model.fields.TipusDocumentalFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${suf:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TipusDocumentalFields.TIPUSDOCUMENTALID)}">
        <th>${suf:getSortIcons(__theFilterForm,TipusDocumentalFields.TIPUSDOCUMENTALID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TipusDocumentalFields.PARETIPUSDOCUMENTALID)}">
        <th>${suf:getSortIcons(__theFilterForm,TipusDocumentalFields.PARETIPUSDOCUMENTALID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TipusDocumentalFields.NOMCATALA)}">
        <th>${suf:getSortIcons(__theFilterForm,TipusDocumentalFields.NOMCATALA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TipusDocumentalFields.NOMCASTELLA)}">
        <th>${suf:getSortIcons(__theFilterForm,TipusDocumentalFields.NOMCASTELLA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TipusDocumentalFields.DESCRIPCIOCATALA)}">
        <th>${suf:getSortIcons(__theFilterForm,TipusDocumentalFields.DESCRIPCIOCATALA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TipusDocumentalFields.DESCRIPCIOCASTELLA)}">
        <th>${suf:getSortIcons(__theFilterForm,TipusDocumentalFields.DESCRIPCIOCASTELLA)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${suf:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

