<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EstadisticaFields" className="es.caib.utilitatsfirma.model.fields.EstadisticaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstadisticaFields.DATA)}">
        <tr id="estadistica_data_rowid">
          <td id="estadistica_data_columnlabelid">
            <label for="estadistica.data">
              <fmt:message key="${(empty __theForm.labels[EstadisticaFields.DATA])?'estadistica.data':__theForm.labels[EstadisticaFields.DATA]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EstadisticaFields.DATA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstadisticaFields.DATA]}" ></i>
              </c:if>
            </td>
          <td id="estadistica_data_columnvalueid">
    <form:errors path="estadistica.data" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="estadistica_data" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstadisticaFields.DATA)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#estadistica_data" path="estadistica.data" aria-label="estadistica.data"  />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstadisticaFields.DATA)}" >
                    <div class="input-group-append"  data-target="#estadistica_data"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#estadistica_data').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstadisticaFields.TIPUS)}">
        <tr id="estadistica_tipus_rowid">
          <td id="estadistica_tipus_columnlabelid">
            <label for="estadistica.tipus">
              <fmt:message key="${(empty __theForm.labels[EstadisticaFields.TIPUS])?'estadistica.tipus':__theForm.labels[EstadisticaFields.TIPUS]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EstadisticaFields.TIPUS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstadisticaFields.TIPUS]}" ></i>
              </c:if>
            </td>
          <td id="estadistica_tipus_columnvalueid">
          <form:errors path="estadistica.tipus" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EstadisticaFields.TIPUS)}" >
          <form:hidden path="estadistica.tipus"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.estadistica.tipus,__theForm.listOfValuesForTipus)}" aria-label="estadistica.tipus" />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstadisticaFields.TIPUS)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="estadistica_tipus"  onchange="if(typeof onChangeTipus == 'function') {  onChangeTipus(this); };"  cssClass="form-control col-md-9-optional" path="estadistica.tipus"  aria-label="estadistica.tipus" >
            <c:forEach items="${__theForm.listOfValuesForTipus}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstadisticaFields.VALOR)}">
        <tr id="estadistica_valor_rowid">
          <td id="estadistica_valor_columnlabelid">
            <label for="estadistica.valor">
              <fmt:message key="${(empty __theForm.labels[EstadisticaFields.VALOR])?'estadistica.valor':__theForm.labels[EstadisticaFields.VALOR]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EstadisticaFields.VALOR]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstadisticaFields.VALOR]}" ></i>
              </c:if>
            </td>
          <td id="estadistica_valor_columnvalueid">
            <form:errors path="estadistica.valor" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstadisticaFields.VALOR)? 'true' : 'false'}" cssClass="w-50 form-control  ${gen:contains(__theForm.readOnlyFields ,EstadisticaFields.VALOR)? ' uneditable-input' : ''}"  style=""  path="estadistica.valor"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstadisticaFields.USUARIAPLICACIOID)}">
        <tr id="estadistica_usuariAplicacioID_rowid">
          <td id="estadistica_usuariAplicacioID_columnlabelid">
            <label for="estadistica.usuariAplicacioID">
              <fmt:message key="${(empty __theForm.labels[EstadisticaFields.USUARIAPLICACIOID])?'estadistica.usuariAplicacioID':__theForm.labels[EstadisticaFields.USUARIAPLICACIOID]}" />
             </label>
              <c:if test="${not empty __theForm.help[EstadisticaFields.USUARIAPLICACIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstadisticaFields.USUARIAPLICACIOID]}" ></i>
              </c:if>
            </td>
          <td id="estadistica_usuariAplicacioID_columnvalueid">
            <form:errors path="estadistica.usuariAplicacioID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstadisticaFields.USUARIAPLICACIOID)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,EstadisticaFields.USUARIAPLICACIOID)? ' uneditable-input' : ''}"  style="" maxlength="101" path="estadistica.usuariAplicacioID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstadisticaFields.ENTORN)}">
        <tr id="estadistica_entorn_rowid">
          <td id="estadistica_entorn_columnlabelid">
            <label for="estadistica.entorn">
              <fmt:message key="${(empty __theForm.labels[EstadisticaFields.ENTORN])?'estadistica.entorn':__theForm.labels[EstadisticaFields.ENTORN]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EstadisticaFields.ENTORN]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstadisticaFields.ENTORN]}" ></i>
              </c:if>
            </td>
          <td id="estadistica_entorn_columnvalueid">
          <form:errors path="estadistica.entorn" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EstadisticaFields.ENTORN)}" >
          <form:hidden path="estadistica.entorn"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.estadistica.entorn,__theForm.listOfValuesForEntorn)}" aria-label="estadistica.entorn" />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstadisticaFields.ENTORN)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="estadistica_entorn"  onchange="if(typeof onChangeEntorn == 'function') {  onChangeEntorn(this); };"  cssClass="form-control col-md-9-optional" path="estadistica.entorn"  aria-label="estadistica.entorn" >
            <c:forEach items="${__theForm.listOfValuesForEntorn}" var="tmp">
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
        
