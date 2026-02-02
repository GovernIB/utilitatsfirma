<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariAplicacioConfiguracioFields" className="es.caib.utilitatsfirma.model.fields.UsuariAplicacioConfiguracioFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${suf:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID)}">
        <th>${suf:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.NOM)}">
        <th>${suf:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA)}">
        <th>${suf:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER)}">
        <th>${suf:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH)}">
        <th>${suf:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM)}">
        <th>${suf:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT)}">
        <th>${suf:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA)}">
        <th>${suf:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.TIPUSFIRMA)}">
        <th>${suf:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.TIPUSFIRMA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMA)}">
        <th>${suf:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.MODEDEFIRMA)}">
        <th>${suf:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.MODEDEFIRMA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA)}">
        <th>${suf:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT)}">
        <th>${suf:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.VALIDARFIRMA)}">
        <th>${suf:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.VALIDARFIRMA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID)}">
        <th>${suf:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT)}">
        <th>${suf:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS)}">
        <th>${suf:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${suf:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

