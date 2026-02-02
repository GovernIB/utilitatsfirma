<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariAplicacioConfiguracioFields" className="es.caib.utilitatsfirma.model.fields.UsuariAplicacioConfiguracioFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[usuariAplicacioConfiguracio.usuariAplicacioConfigID]}" />
             </c:if>
             <c:if test="${not empty __entry.value.valueField }">
               <c:set var="__tmp" value="${pageScope}" />
               <c:set var="__trosos" value="${fn:split(__entry.value.valueField.fullName,'.')}" />
               <c:forEach var="__tros" items="${__trosos}">
                  <c:set var="__tmp" value="${__tmp[__tros]}" />
               </c:forEach>
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__tmp}" />
             </c:if>
          </td>
          </c:if>
          </c:forEach>


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID)}">
          <td>
          ${usuariAplicacioConfiguracio.usuariAplicacioConfigID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.NOM)}">
          <td>
          ${usuariAplicacioConfiguracio.nom}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA)}">
          <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.usPoliticaDeFirma}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForUsPoliticaDeFirma[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER)}">
          <td>
          ${usuariAplicacioConfiguracio.policyIdentifier}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH)}">
          <td>
          ${usuariAplicacioConfiguracio.policyIdentifierHash}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM)}">
          <td>
          ${usuariAplicacioConfiguracio.policyIdentifierHashAlgorithm}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT)}">
          <td>
          ${usuariAplicacioConfiguracio.policyUrlDocument}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA)}">
          <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.tipusOperacioFirma}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForTipusOperacioFirma[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.TIPUSFIRMA)}">
          <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.tipusFirma}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForTipusFirma[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMA)}">
          <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.algorismeDeFirma}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForAlgorismeDeFirma[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.MODEDEFIRMA)}">
          <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.modeDeFirma}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForModeDeFirma[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA)}">
          <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${usuariAplicacioConfiguracio.comprovarNifFirma?'success':'error'}.png"/>">
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT)}">
          <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${usuariAplicacioConfiguracio.checkCanviatDocFirmat?'success':'error'}.png"/>">
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.VALIDARFIRMA)}">
          <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${usuariAplicacioConfiguracio.validarFirma?'success':'error'}.png"/>">
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID)}">
          <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.pluginFirmaServidorID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfPluginForPluginFirmaServidorID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT)}">
          <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.upgradeSignFormat}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForUpgradeSignFormat[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS)}">
          <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.politicaSegellatDeTemps}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForPoliticaSegellatDeTemps[tmp]}
          </c:if>
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[usuariAplicacioConfiguracio.usuariAplicacioConfigID]}" />
             </c:if>
             <c:if test="${not empty __entry.value.valueField }">
               <c:set var="__tmp" value="${pageScope}" />
               <c:set var="__trosos" value="${fn:split(__entry.value.valueField.fullName,'.')}" />
               <c:forEach var="__tros" items="${__trosos}">
                  <c:set var="__tmp" value="${__tmp[__tros]}" />
               </c:forEach>
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__tmp}" />
             </c:if>
          </td>
          </c:if>
          </c:forEach>


