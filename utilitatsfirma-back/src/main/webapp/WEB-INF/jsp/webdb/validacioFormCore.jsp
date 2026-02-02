<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ValidacioFields" className="es.caib.utilitatsfirma.model.fields.ValidacioFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,ValidacioFields.NOM)}">
        <tr id="validacio_nom_rowid">
          <td id="validacio_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ValidacioFields.NOM])?'validacio.nom':__theForm.labels[ValidacioFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[ValidacioFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ValidacioFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="validacio_nom_columnvalueid">
            <form:errors path="validacio.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ValidacioFields.NOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,ValidacioFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="validacio.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ValidacioFields.SIGNATURAID)}">
        <tr id="validacio_signaturaID_rowid">
          <td id="validacio_signaturaID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ValidacioFields.SIGNATURAID])?'validacio.signaturaID':__theForm.labels[ValidacioFields.SIGNATURAID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[ValidacioFields.SIGNATURAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ValidacioFields.SIGNATURAID]}" ></i>
              </c:if>
            </td>
          <td id="validacio_signaturaID_columnvalueid">
              <form:errors path="validacio.signaturaID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,ValidacioFields.SIGNATURAID)}" >
              <a target="_blank" href="<c:url value="${suf:fileUrl(__theForm.validacio.signatura)}"/>">${__theForm.validacio.signatura.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,ValidacioFields.SIGNATURAID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,ValidacioFields.SIGNATURAID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,ValidacioFields.SIGNATURAID)? ' uneditable-input' : ''}"   path="signaturaID" type="file" />
                  <label class="custom-file-label" for="signaturaID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.validacio.signatura}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${suf:fileUrl(__theForm.validacio.signatura)}"/>">${__theForm.validacio.signatura.nom}</a>
</small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="signaturaID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#signaturaID').on('change', function(){
						var ruta = $('#signaturaID').val(); 
						var rutaArray = ruta.split('\\');
						$('#signaturaID-custom-file-label').css('display','block');
						$('#signaturaID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ValidacioFields.DETACHEDID)}">
        <tr id="validacio_detachedID_rowid">
          <td id="validacio_detachedID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ValidacioFields.DETACHEDID])?'validacio.detachedID':__theForm.labels[ValidacioFields.DETACHEDID]}" />
             </label>
              <c:if test="${not empty __theForm.help[ValidacioFields.DETACHEDID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ValidacioFields.DETACHEDID]}" ></i>
              </c:if>
            </td>
          <td id="validacio_detachedID_columnvalueid">
              <form:errors path="validacio.detachedID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,ValidacioFields.DETACHEDID)}" >
              <a target="_blank" href="<c:url value="${suf:fileUrl(__theForm.validacio.detached)}"/>">${__theForm.validacio.detached.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,ValidacioFields.DETACHEDID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,ValidacioFields.DETACHEDID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,ValidacioFields.DETACHEDID)? ' uneditable-input' : ''}"   path="detachedID" type="file" />
                  <label class="custom-file-label" for="detachedID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.validacio.detached}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${suf:fileUrl(__theForm.validacio.detached)}"/>">${__theForm.validacio.detached.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="detachedIDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="detachedID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#detachedID').on('change', function(){
						var ruta = $('#detachedID').val(); 
						var rutaArray = ruta.split('\\');
						$('#detachedID-custom-file-label').css('display','block');
						$('#detachedID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ValidacioFields.RESULTAT)}">
        <tr id="validacio_resultat_rowid">
          <td id="validacio_resultat_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ValidacioFields.RESULTAT])?'validacio.resultat':__theForm.labels[ValidacioFields.RESULTAT]}" />
             </label>
              <c:if test="${not empty __theForm.help[ValidacioFields.RESULTAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ValidacioFields.RESULTAT]}" ></i>
              </c:if>
            </td>
          <td id="validacio_resultat_columnvalueid">
          <form:errors path="validacio.resultat" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ValidacioFields.RESULTAT)}" >
          <form:hidden path="validacio.resultat"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.validacio.resultat,__theForm.listOfValuesForResultat)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ValidacioFields.RESULTAT)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="validacio_resultat"  onchange="if(typeof onChangeResultat == 'function') {  onChangeResultat(this); };"  cssClass="form-control col-md-9-optional" path="validacio.resultat">
            <c:forEach items="${__theForm.listOfValuesForResultat}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.validacio.resultat }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.validacio.resultat }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ValidacioFields.INFORESULTAT)}">
        <tr id="validacio_infoResultat_rowid">
          <td id="validacio_infoResultat_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ValidacioFields.INFORESULTAT])?'validacio.infoResultat':__theForm.labels[ValidacioFields.INFORESULTAT]}" />
             </label>
              <c:if test="${not empty __theForm.help[ValidacioFields.INFORESULTAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ValidacioFields.INFORESULTAT]}" ></i>
              </c:if>
            </td>
          <td id="validacio_infoResultat_columnvalueid">
              <form:errors path="validacio.infoResultat" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,ValidacioFields.INFORESULTAT)? 'true' : 'false'}" path="validacio.infoResultat"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_infoResultat" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_infoResultat" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('validacio.infoResultat'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('validacio.infoResultat'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('validacio.infoResultat'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_infoResultat').on('click', function(){
					var valor = ($('#dropdownMenuContainer_infoResultat').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_infoResultat').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ValidacioFields.DATAINICI)}">
        <tr id="validacio_dataInici_rowid">
          <td id="validacio_dataInici_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ValidacioFields.DATAINICI])?'validacio.dataInici':__theForm.labels[ValidacioFields.DATAINICI]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[ValidacioFields.DATAINICI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ValidacioFields.DATAINICI]}" ></i>
              </c:if>
            </td>
          <td id="validacio_dataInici_columnvalueid">
    <form:errors path="validacio.dataInici" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="validacio_dataInici" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ValidacioFields.DATAINICI)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#validacio_dataInici" path="validacio.dataInici" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,ValidacioFields.DATAINICI)}" >
                    <div class="input-group-append"  data-target="#validacio_dataInici"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#validacio_dataInici').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ValidacioFields.DATAFI)}">
        <tr id="validacio_dataFi_rowid">
          <td id="validacio_dataFi_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ValidacioFields.DATAFI])?'validacio.dataFi':__theForm.labels[ValidacioFields.DATAFI]}" />
             </label>
              <c:if test="${not empty __theForm.help[ValidacioFields.DATAFI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ValidacioFields.DATAFI]}" ></i>
              </c:if>
            </td>
          <td id="validacio_dataFi_columnvalueid">
    <form:errors path="validacio.dataFi" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="validacio_dataFi" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ValidacioFields.DATAFI)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#validacio_dataFi" path="validacio.dataFi" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,ValidacioFields.DATAFI)}" >
                    <div class="input-group-append"  data-target="#validacio_dataFi"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#validacio_dataFi').datetimepicker({
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
        
