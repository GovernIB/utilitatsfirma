<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ConfiguracioDeFirmaPerUsuariAplicacioFields" className="es.caib.utilitatsfirma.model.fields.ConfiguracioDeFirmaPerUsuariAplicacioFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,ConfiguracioDeFirmaPerUsuariAplicacioFields.USUARIAPLICACIOID)}">
        <tr id="configuracioDeFirmaPerUsuariAplicacio_usuariAplicacioID_rowid">
          <td id="configuracioDeFirmaPerUsuariAplicacio_usuariAplicacioID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ConfiguracioDeFirmaPerUsuariAplicacioFields.USUARIAPLICACIOID])?'configuracioDeFirmaPerUsuariAplicacio.usuariAplicacioID':__theForm.labels[ConfiguracioDeFirmaPerUsuariAplicacioFields.USUARIAPLICACIOID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[ConfiguracioDeFirmaPerUsuariAplicacioFields.USUARIAPLICACIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ConfiguracioDeFirmaPerUsuariAplicacioFields.USUARIAPLICACIOID]}" ></i>
              </c:if>
            </td>
          <td id="configuracioDeFirmaPerUsuariAplicacio_usuariAplicacioID_columnvalueid">
          <form:errors path="configuracioDeFirmaPerUsuariAplicacio.usuariAplicacioID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ConfiguracioDeFirmaPerUsuariAplicacioFields.USUARIAPLICACIOID)}" >
          <form:hidden path="configuracioDeFirmaPerUsuariAplicacio.usuariAplicacioID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.configuracioDeFirmaPerUsuariAplicacio.usuariAplicacioID,__theForm.listOfUsuariAplicacioForUsuariAplicacioID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ConfiguracioDeFirmaPerUsuariAplicacioFields.USUARIAPLICACIOID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="configuracioDeFirmaPerUsuariAplicacio_usuariAplicacioID"  onchange="if(typeof onChangeUsuariAplicacioID == 'function') {  onChangeUsuariAplicacioID(this); };"  cssClass="form-control col-md-9-optional" path="configuracioDeFirmaPerUsuariAplicacio.usuariAplicacioID">
            <c:forEach items="${__theForm.listOfUsuariAplicacioForUsuariAplicacioID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ConfiguracioDeFirmaPerUsuariAplicacioFields.USUARIAPLICACIOCONFIGID)}">
        <tr id="configuracioDeFirmaPerUsuariAplicacio_usuariAplicacioConfigID_rowid">
          <td id="configuracioDeFirmaPerUsuariAplicacio_usuariAplicacioConfigID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ConfiguracioDeFirmaPerUsuariAplicacioFields.USUARIAPLICACIOCONFIGID])?'configuracioDeFirmaPerUsuariAplicacio.usuariAplicacioConfigID':__theForm.labels[ConfiguracioDeFirmaPerUsuariAplicacioFields.USUARIAPLICACIOCONFIGID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[ConfiguracioDeFirmaPerUsuariAplicacioFields.USUARIAPLICACIOCONFIGID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ConfiguracioDeFirmaPerUsuariAplicacioFields.USUARIAPLICACIOCONFIGID]}" ></i>
              </c:if>
            </td>
          <td id="configuracioDeFirmaPerUsuariAplicacio_usuariAplicacioConfigID_columnvalueid">
          <form:errors path="configuracioDeFirmaPerUsuariAplicacio.usuariAplicacioConfigID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ConfiguracioDeFirmaPerUsuariAplicacioFields.USUARIAPLICACIOCONFIGID)}" >
          <form:hidden path="configuracioDeFirmaPerUsuariAplicacio.usuariAplicacioConfigID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.configuracioDeFirmaPerUsuariAplicacio.usuariAplicacioConfigID,__theForm.listOfUsuariAplicacioConfiguracioForUsuariAplicacioConfigID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ConfiguracioDeFirmaPerUsuariAplicacioFields.USUARIAPLICACIOCONFIGID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="configuracioDeFirmaPerUsuariAplicacio_usuariAplicacioConfigID"  onchange="if(typeof onChangeUsuariAplicacioConfigID == 'function') {  onChangeUsuariAplicacioConfigID(this); };"  cssClass="form-control col-md-9-optional" path="configuracioDeFirmaPerUsuariAplicacio.usuariAplicacioConfigID">
            <c:forEach items="${__theForm.listOfUsuariAplicacioConfiguracioForUsuariAplicacioConfigID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
