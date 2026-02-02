<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PluginFields" className="es.caib.utilitatsfirma.model.fields.PluginFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${suf:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.PLUGINID)}">
        <th>${suf:getSortIcons(__theFilterForm,PluginFields.PLUGINID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.NOMID)}">
        <th>${suf:getSortIcons(__theFilterForm,PluginFields.NOMID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.DESCRIPCIOCURTAID)}">
        <th>${suf:getSortIcons(__theFilterForm,PluginFields.DESCRIPCIOCURTAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.CLASSE)}">
        <th>${suf:getSortIcons(__theFilterForm,PluginFields.CLASSE)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.PROPERTIESADMIN)}">
        <th>${suf:getSortIcons(__theFilterForm,PluginFields.PROPERTIESADMIN)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.ACTIU)}">
        <th>${suf:getSortIcons(__theFilterForm,PluginFields.ACTIU)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.TIPUS)}">
        <th>${suf:getSortIcons(__theFilterForm,PluginFields.TIPUS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.CODI)}">
        <th>${suf:getSortIcons(__theFilterForm,PluginFields.CODI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.ORDRE)}">
        <th>${suf:getSortIcons(__theFilterForm,PluginFields.ORDRE)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${suf:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

