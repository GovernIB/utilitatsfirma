<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TipusDocumentalFields" className="es.caib.utilitatsfirma.model.fields.TipusDocumentalFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusDocumentalFields.TIPUSDOCUMENTALID)}">
        <tr id="tipusDocumental_tipusDocumentalID_rowid">
          <td id="tipusDocumental_tipusDocumentalID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusDocumentalFields.TIPUSDOCUMENTALID])?'tipusDocumental.tipusDocumentalID':__theForm.labels[TipusDocumentalFields.TIPUSDOCUMENTALID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TipusDocumentalFields.TIPUSDOCUMENTALID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TipusDocumentalFields.TIPUSDOCUMENTALID]}" ></i>
              </c:if>
            </td>
          <td id="tipusDocumental_tipusDocumentalID_columnvalueid">
            <form:errors path="tipusDocumental.tipusDocumentalID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TipusDocumentalFields.TIPUSDOCUMENTALID)? 'true' : 'false'}" cssClass="w-50 form-control  ${gen:contains(__theForm.readOnlyFields ,TipusDocumentalFields.TIPUSDOCUMENTALID)? ' uneditable-input' : ''}"  style=""  path="tipusDocumental.tipusDocumentalID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusDocumentalFields.PARETIPUSDOCUMENTALID)}">
        <tr id="tipusDocumental_pareTipusDocumentalID_rowid">
          <td id="tipusDocumental_pareTipusDocumentalID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusDocumentalFields.PARETIPUSDOCUMENTALID])?'tipusDocumental.pareTipusDocumentalID':__theForm.labels[TipusDocumentalFields.PARETIPUSDOCUMENTALID]}" />
             </label>
              <c:if test="${not empty __theForm.help[TipusDocumentalFields.PARETIPUSDOCUMENTALID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TipusDocumentalFields.PARETIPUSDOCUMENTALID]}" ></i>
              </c:if>
            </td>
          <td id="tipusDocumental_pareTipusDocumentalID_columnvalueid">
            <form:errors path="tipusDocumental.pareTipusDocumentalID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TipusDocumentalFields.PARETIPUSDOCUMENTALID)? 'true' : 'false'}" cssClass="w-50 form-control  ${gen:contains(__theForm.readOnlyFields ,TipusDocumentalFields.PARETIPUSDOCUMENTALID)? ' uneditable-input' : ''}"  style=""  path="tipusDocumental.pareTipusDocumentalID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusDocumentalFields.NOMCATALA)}">
        <tr id="tipusDocumental_nomCatala_rowid">
          <td id="tipusDocumental_nomCatala_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusDocumentalFields.NOMCATALA])?'tipusDocumental.nomCatala':__theForm.labels[TipusDocumentalFields.NOMCATALA]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TipusDocumentalFields.NOMCATALA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TipusDocumentalFields.NOMCATALA]}" ></i>
              </c:if>
            </td>
          <td id="tipusDocumental_nomCatala_columnvalueid">
            <form:errors path="tipusDocumental.nomCatala" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TipusDocumentalFields.NOMCATALA)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,TipusDocumentalFields.NOMCATALA)? ' uneditable-input' : ''}"  style="" maxlength="255" path="tipusDocumental.nomCatala"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusDocumentalFields.NOMCASTELLA)}">
        <tr id="tipusDocumental_nomCastella_rowid">
          <td id="tipusDocumental_nomCastella_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusDocumentalFields.NOMCASTELLA])?'tipusDocumental.nomCastella':__theForm.labels[TipusDocumentalFields.NOMCASTELLA]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TipusDocumentalFields.NOMCASTELLA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TipusDocumentalFields.NOMCASTELLA]}" ></i>
              </c:if>
            </td>
          <td id="tipusDocumental_nomCastella_columnvalueid">
            <form:errors path="tipusDocumental.nomCastella" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TipusDocumentalFields.NOMCASTELLA)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,TipusDocumentalFields.NOMCASTELLA)? ' uneditable-input' : ''}"  style="" maxlength="256" path="tipusDocumental.nomCastella"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusDocumentalFields.DESCRIPCIOCATALA)}">
        <tr id="tipusDocumental_descripcioCatala_rowid">
          <td id="tipusDocumental_descripcioCatala_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusDocumentalFields.DESCRIPCIOCATALA])?'tipusDocumental.descripcioCatala':__theForm.labels[TipusDocumentalFields.DESCRIPCIOCATALA]}" />
             </label>
              <c:if test="${not empty __theForm.help[TipusDocumentalFields.DESCRIPCIOCATALA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TipusDocumentalFields.DESCRIPCIOCATALA]}" ></i>
              </c:if>
            </td>
          <td id="tipusDocumental_descripcioCatala_columnvalueid">
            <form:errors path="tipusDocumental.descripcioCatala" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TipusDocumentalFields.DESCRIPCIOCATALA)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,TipusDocumentalFields.DESCRIPCIOCATALA)? ' uneditable-input' : ''}"  style="" maxlength="256" path="tipusDocumental.descripcioCatala"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusDocumentalFields.DESCRIPCIOCASTELLA)}">
        <tr id="tipusDocumental_descripcioCastella_rowid">
          <td id="tipusDocumental_descripcioCastella_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusDocumentalFields.DESCRIPCIOCASTELLA])?'tipusDocumental.descripcioCastella':__theForm.labels[TipusDocumentalFields.DESCRIPCIOCASTELLA]}" />
             </label>
              <c:if test="${not empty __theForm.help[TipusDocumentalFields.DESCRIPCIOCASTELLA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TipusDocumentalFields.DESCRIPCIOCASTELLA]}" ></i>
              </c:if>
            </td>
          <td id="tipusDocumental_descripcioCastella_columnvalueid">
            <form:errors path="tipusDocumental.descripcioCastella" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TipusDocumentalFields.DESCRIPCIOCASTELLA)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,TipusDocumentalFields.DESCRIPCIOCASTELLA)? ' uneditable-input' : ''}"  style="" maxlength="256" path="tipusDocumental.descripcioCastella"   />

           </td>
        </tr>
        </c:if>
        
