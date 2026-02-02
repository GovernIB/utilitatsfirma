<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PluginFields" className="es.caib.utilitatsfirma.model.fields.PluginFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.NOMID)}">
        <tr id="plugin_nomID_rowid">
          <td id="plugin_nomID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.NOMID])?'plugin.nomID':__theForm.labels[PluginFields.NOMID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PluginFields.NOMID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFields.NOMID]}" ></i>
              </c:if>
            </td>
          <td id="plugin_nomID_columnvalueid">
       <form:errors path="plugin.nom" cssClass="errorField alert alert-danger" />
       <div class="row-fluid col-md-9-optional">
         <ul class="nav nav-tabs" style="margin: 0 15px -1px;">
             <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
            <li class="nav-item ">
                 <a class="nav-link ${(counter.index == 0)? 'active':''}" href="#${counter.index}_tab_nom_${idioma.idiomaID}" data-toggle="tab">${idioma.nom}</a>
            </li>
          </c:forEach>
           
         </ul>
         <div class="tab-content well well-white" style="padding:8px;margin:0px;">
           <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
           <div class="tab-pane ${(counter.index == 0)? 'active':'' }" id="${counter.index}_tab_nom_${idioma.idiomaID}">
               <form:errors path="plugin.nom.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-danger"/>
               <form:input path="plugin.nom.traduccions['${idioma.idiomaID}'].valor" cssClass="form-control  ${gen:contains(__theForm.readOnlyFields ,PluginFields.NOMID)? ' uneditable-input' : ''}" readonly="${gen:contains(__theForm.readOnlyFields ,PluginFields.NOMID)}" maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.DESCRIPCIOCURTAID)}">
        <tr id="plugin_descripcioCurtaID_rowid">
          <td id="plugin_descripcioCurtaID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.DESCRIPCIOCURTAID])?'plugin.descripcioCurtaID':__theForm.labels[PluginFields.DESCRIPCIOCURTAID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PluginFields.DESCRIPCIOCURTAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFields.DESCRIPCIOCURTAID]}" ></i>
              </c:if>
            </td>
          <td id="plugin_descripcioCurtaID_columnvalueid">
       <form:errors path="plugin.descripcioCurta" cssClass="errorField alert alert-danger" />
       <div class="row-fluid col-md-9-optional">
         <ul class="nav nav-tabs" style="margin: 0 15px -1px;">
             <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
            <li class="nav-item ">
                 <a class="nav-link ${(counter.index == 0)? 'active':''}" href="#${counter.index}_tab_descripcioCurta_${idioma.idiomaID}" data-toggle="tab">${idioma.nom}</a>
            </li>
          </c:forEach>
           
         </ul>
         <div class="tab-content well well-white" style="padding:8px;margin:0px;">
           <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
           <div class="tab-pane ${(counter.index == 0)? 'active':'' }" id="${counter.index}_tab_descripcioCurta_${idioma.idiomaID}">
               <form:errors path="plugin.descripcioCurta.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-danger"/>
               <form:input path="plugin.descripcioCurta.traduccions['${idioma.idiomaID}'].valor" cssClass="form-control  ${gen:contains(__theForm.readOnlyFields ,PluginFields.DESCRIPCIOCURTAID)? ' uneditable-input' : ''}" readonly="${gen:contains(__theForm.readOnlyFields ,PluginFields.DESCRIPCIOCURTAID)}" maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.CLASSE)}">
        <tr id="plugin_classe_rowid">
          <td id="plugin_classe_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.CLASSE])?'plugin.classe':__theForm.labels[PluginFields.CLASSE]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PluginFields.CLASSE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFields.CLASSE]}" ></i>
              </c:if>
            </td>
          <td id="plugin_classe_columnvalueid">
            <form:errors path="plugin.classe" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PluginFields.CLASSE)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,PluginFields.CLASSE)? ' uneditable-input' : ''}"  style="" maxlength="255" path="plugin.classe"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.PROPERTIESADMIN)}">
        <tr id="plugin_propertiesAdmin_rowid">
          <td id="plugin_propertiesAdmin_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.PROPERTIESADMIN])?'plugin.propertiesAdmin':__theForm.labels[PluginFields.PROPERTIESADMIN]}" />
             </label>
              <c:if test="${not empty __theForm.help[PluginFields.PROPERTIESADMIN]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFields.PROPERTIESADMIN]}" ></i>
              </c:if>
            </td>
          <td id="plugin_propertiesAdmin_columnvalueid">
              <form:errors path="plugin.propertiesAdmin" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,PluginFields.PROPERTIESADMIN)? 'true' : 'false'}" path="plugin.propertiesAdmin"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_propertiesAdmin" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_propertiesAdmin" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('plugin.propertiesAdmin'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('plugin.propertiesAdmin'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('plugin.propertiesAdmin'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_propertiesAdmin').on('click', function(){
					var valor = ($('#dropdownMenuContainer_propertiesAdmin').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_propertiesAdmin').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.ACTIU)}">
        <tr id="plugin_actiu_rowid">
          <td id="plugin_actiu_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.ACTIU])?'plugin.actiu':__theForm.labels[PluginFields.ACTIU]}" />
             </label>
              <c:if test="${not empty __theForm.help[PluginFields.ACTIU]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFields.ACTIU]}" ></i>
              </c:if>
            </td>
          <td id="plugin_actiu_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginFields.ACTIU)}" >
              <form:errors path="plugin.actiu" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,PluginFields.ACTIU)? 'false' : 'true'}" path="plugin.actiu" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginFields.ACTIU)}" >
                <fmt:message key="genapp.checkbox.${__theForm.plugin.actiu}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.TIPUS)}">
        <tr id="plugin_tipus_rowid">
          <td id="plugin_tipus_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.TIPUS])?'plugin.tipus':__theForm.labels[PluginFields.TIPUS]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PluginFields.TIPUS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFields.TIPUS]}" ></i>
              </c:if>
            </td>
          <td id="plugin_tipus_columnvalueid">
          <form:errors path="plugin.tipus" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginFields.TIPUS)}" >
          <form:hidden path="plugin.tipus"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.plugin.tipus,__theForm.listOfValuesForTipus)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginFields.TIPUS)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="plugin_tipus"  onchange="if(typeof onChangeTipus == 'function') {  onChangeTipus(this); };"  cssClass="form-control col-md-9-optional" path="plugin.tipus">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.CODI)}">
        <tr id="plugin_codi_rowid">
          <td id="plugin_codi_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.CODI])?'plugin.codi':__theForm.labels[PluginFields.CODI]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PluginFields.CODI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFields.CODI]}" ></i>
              </c:if>
            </td>
          <td id="plugin_codi_columnvalueid">
            <form:errors path="plugin.codi" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PluginFields.CODI)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,PluginFields.CODI)? ' uneditable-input' : ''}"  style="" maxlength="255" path="plugin.codi"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.ORDRE)}">
        <tr id="plugin_ordre_rowid">
          <td id="plugin_ordre_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.ORDRE])?'plugin.ordre':__theForm.labels[PluginFields.ORDRE]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PluginFields.ORDRE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFields.ORDRE]}" ></i>
              </c:if>
            </td>
          <td id="plugin_ordre_columnvalueid">
            <form:errors path="plugin.ordre" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PluginFields.ORDRE)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,PluginFields.ORDRE)? ' uneditable-input' : ''}"  style=""  path="plugin.ordre"   />

           </td>
        </tr>
        </c:if>
        
