<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariAplicacioFields" className="es.caib.utilitatsfirma.model.fields.UsuariAplicacioFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioFields.USUARIAPLICACIOID)}">
        <tr id="usuariAplicacio_usuariAplicacioID_rowid">
          <td id="usuariAplicacio_usuariAplicacioID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioFields.USUARIAPLICACIOID])?'usuariAplicacio.usuariAplicacioID':__theForm.labels[UsuariAplicacioFields.USUARIAPLICACIOID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioFields.USUARIAPLICACIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioFields.USUARIAPLICACIOID]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacio_usuariAplicacioID_columnvalueid">
            <form:errors path="usuariAplicacio.usuariAplicacioID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.USUARIAPLICACIOID)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.USUARIAPLICACIOID)? ' uneditable-input' : ''}"  style="" maxlength="101" path="usuariAplicacio.usuariAplicacioID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioFields.DESCRIPCIO)}">
        <tr id="usuariAplicacio_descripcio_rowid">
          <td id="usuariAplicacio_descripcio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioFields.DESCRIPCIO])?'usuariAplicacio.descripcio':__theForm.labels[UsuariAplicacioFields.DESCRIPCIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioFields.DESCRIPCIO]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacio_descripcio_columnvalueid">
            <form:errors path="usuariAplicacio.descripcio" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.DESCRIPCIO)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.DESCRIPCIO)? ' uneditable-input' : ''}"  style="" maxlength="255" path="usuariAplicacio.descripcio"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioFields.EMAILADMIN)}">
        <tr id="usuariAplicacio_emailAdmin_rowid">
          <td id="usuariAplicacio_emailAdmin_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioFields.EMAILADMIN])?'usuariAplicacio.emailAdmin':__theForm.labels[UsuariAplicacioFields.EMAILADMIN]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioFields.EMAILADMIN]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioFields.EMAILADMIN]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacio_emailAdmin_columnvalueid">
            <form:errors path="usuariAplicacio.emailAdmin" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.EMAILADMIN)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.EMAILADMIN)? ' uneditable-input' : ''}"  style="" maxlength="100" path="usuariAplicacio.emailAdmin"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioFields.ACTIU)}">
        <tr id="usuariAplicacio_actiu_rowid">
          <td id="usuariAplicacio_actiu_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioFields.ACTIU])?'usuariAplicacio.actiu':__theForm.labels[UsuariAplicacioFields.ACTIU]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioFields.ACTIU]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioFields.ACTIU]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacio_actiu_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.ACTIU)}" >
              <form:errors path="usuariAplicacio.actiu" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.ACTIU)? 'false' : 'true'}" path="usuariAplicacio.actiu" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.ACTIU)}" >
                <fmt:message key="genapp.checkbox.${__theForm.usuariAplicacio.actiu}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
