<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PerfilDeFirmaFields" className="es.caib.utilitatsfirma.model.fields.PerfilDeFirmaFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${suf:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PerfilDeFirmaFields.USUARIAPLICACIOPERFILID)}">
        <th>${suf:getSortIcons(__theFilterForm,PerfilDeFirmaFields.USUARIAPLICACIOPERFILID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PerfilDeFirmaFields.NOM)}">
        <th>${suf:getSortIcons(__theFilterForm,PerfilDeFirmaFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PerfilDeFirmaFields.CODI)}">
        <th>${suf:getSortIcons(__theFilterForm,PerfilDeFirmaFields.CODI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PerfilDeFirmaFields.DESCRIPCIO)}">
        <th>${suf:getSortIcons(__theFilterForm,PerfilDeFirmaFields.DESCRIPCIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PerfilDeFirmaFields.CONDICIO)}">
        <th>${suf:getSortIcons(__theFilterForm,PerfilDeFirmaFields.CONDICIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PerfilDeFirmaFields.CONFIGURACIODEFIRMA1ID)}">
        <th>${suf:getSortIcons(__theFilterForm,PerfilDeFirmaFields.CONFIGURACIODEFIRMA1ID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PerfilDeFirmaFields.CONFIGURACIODEFIRMA2ID)}">
        <th>${suf:getSortIcons(__theFilterForm,PerfilDeFirmaFields.CONFIGURACIODEFIRMA2ID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PerfilDeFirmaFields.CONFIGURACIODEFIRMA3ID)}">
        <th>${suf:getSortIcons(__theFilterForm,PerfilDeFirmaFields.CONFIGURACIODEFIRMA3ID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PerfilDeFirmaFields.CONFIGURACIODEFIRMA4ID)}">
        <th>${suf:getSortIcons(__theFilterForm,PerfilDeFirmaFields.CONFIGURACIODEFIRMA4ID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PerfilDeFirmaFields.CONFIGURACIODEFIRMA5ID)}">
        <th>${suf:getSortIcons(__theFilterForm,PerfilDeFirmaFields.CONFIGURACIODEFIRMA5ID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PerfilDeFirmaFields.URLBASE)}">
        <th>${suf:getSortIcons(__theFilterForm,PerfilDeFirmaFields.URLBASE)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${suf:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

