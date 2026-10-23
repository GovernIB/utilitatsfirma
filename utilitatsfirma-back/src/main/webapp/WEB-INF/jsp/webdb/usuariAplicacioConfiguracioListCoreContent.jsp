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
             <c:if test="${empty usuariAplicacioConfiguracio.usuariAplicacioConfigID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.NOM)}">
        <td>
          ${usuariAplicacioConfiguracio.nom}
             <c:if test="${empty usuariAplicacioConfiguracio.nom}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA)}">
        <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.tipusOperacioFirma}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForTipusOperacioFirma[tmp]}
          </c:if>
             <c:if test="${empty usuariAplicacioConfiguracio.tipusOperacioFirma}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.TIPUSFIRMA)}">
        <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.tipusFirma}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForTipusFirma[tmp]}
          </c:if>
             <c:if test="${empty usuariAplicacioConfiguracio.tipusFirma}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMA)}">
        <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.algorismeDeFirma}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForAlgorismeDeFirma[tmp]}
          </c:if>
             <c:if test="${empty usuariAplicacioConfiguracio.algorismeDeFirma}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.MODEDEFIRMA)}">
        <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.modeDeFirma}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForModeDeFirma[tmp]}
          </c:if>
             <c:if test="${empty usuariAplicacioConfiguracio.modeDeFirma}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA)}">
        <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.usPoliticaDeFirma}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForUsPoliticaDeFirma[tmp]}
          </c:if>
             <c:if test="${empty usuariAplicacioConfiguracio.usPoliticaDeFirma}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER)}">
        <td>
          ${usuariAplicacioConfiguracio.policyIdentifier}
             <c:if test="${empty usuariAplicacioConfiguracio.policyIdentifier}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH)}">
        <td>
          ${usuariAplicacioConfiguracio.policyIdentifierHash}
             <c:if test="${empty usuariAplicacioConfiguracio.policyIdentifierHash}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM)}">
        <td>
          ${usuariAplicacioConfiguracio.policyIdentifierHashAlgorithm}
             <c:if test="${empty usuariAplicacioConfiguracio.policyIdentifierHashAlgorithm}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT)}">
        <td>
          ${usuariAplicacioConfiguracio.policyUrlDocument}
             <c:if test="${empty usuariAplicacioConfiguracio.policyUrlDocument}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS)}">
        <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.politicaSegellatDeTemps}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForPoliticaSegellatDeTemps[tmp]}
          </c:if>
             <c:if test="${empty usuariAplicacioConfiguracio.politicaSegellatDeTemps}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID)}">
        <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.pluginFirmaServidorID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfPluginForPluginFirmaServidorID[tmp]}
          </c:if>
             <c:if test="${empty usuariAplicacioConfiguracio.pluginFirmaServidorID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT)}">
        <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.upgradeSignFormat}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForUpgradeSignFormat[tmp]}
          </c:if>
             <c:if test="${empty usuariAplicacioConfiguracio.upgradeSignFormat}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.VALIDARFIRMA)}">
        <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${usuariAplicacioConfiguracio.validarFirma?'success':'error'}.png"/>" alt="${usuariAplicacioConfiguracio.validarFirma}" >
             <c:if test="${empty usuariAplicacioConfiguracio.validarFirma}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT)}">
        <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${usuariAplicacioConfiguracio.checkCanviatDocFirmat?'success':'error'}.png"/>" alt="${usuariAplicacioConfiguracio.checkCanviatDocFirmat}" >
             <c:if test="${empty usuariAplicacioConfiguracio.checkCanviatDocFirmat}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA)}">
        <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${usuariAplicacioConfiguracio.comprovarNifFirma?'success':'error'}.png"/>" alt="${usuariAplicacioConfiguracio.comprovarNifFirma}" >
             <c:if test="${empty usuariAplicacioConfiguracio.comprovarNifFirma}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
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


