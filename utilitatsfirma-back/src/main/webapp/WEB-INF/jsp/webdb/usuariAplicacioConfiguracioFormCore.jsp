<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariAplicacioConfiguracioFields" className="es.caib.utilitatsfirma.model.fields.UsuariAplicacioConfiguracioFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.NOM)}">
        <tr id="usuariAplicacioConfiguracio_nom_rowid">
          <td id="usuariAplicacioConfiguracio_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.NOM])?'usuariAplicacioConfiguracio.nom':__theForm.labels[UsuariAplicacioConfiguracioFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_nom_columnvalueid">
            <form:errors path="usuariAplicacioConfiguracio.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.NOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="usuariAplicacioConfiguracio.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA)}">
        <tr id="usuariAplicacioConfiguracio_usPoliticaDeFirma_rowid">
          <td id="usuariAplicacioConfiguracio_usPoliticaDeFirma_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA])?'usuariAplicacioConfiguracio.usPoliticaDeFirma':__theForm.labels[UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_usPoliticaDeFirma_columnvalueid">
          <form:errors path="usuariAplicacioConfiguracio.usPoliticaDeFirma" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA)}" >
          <form:hidden path="usuariAplicacioConfiguracio.usPoliticaDeFirma"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.usPoliticaDeFirma,__theForm.listOfValuesForUsPoliticaDeFirma)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="usuariAplicacioConfiguracio_usPoliticaDeFirma"  onchange="if(typeof onChangeUsPoliticaDeFirma == 'function') {  onChangeUsPoliticaDeFirma(this); };"  cssClass="form-control col-md-9-optional" path="usuariAplicacioConfiguracio.usPoliticaDeFirma">
            <c:forEach items="${__theForm.listOfValuesForUsPoliticaDeFirma}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER)}">
        <tr id="usuariAplicacioConfiguracio_policyIdentifier_rowid">
          <td id="usuariAplicacioConfiguracio_policyIdentifier_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER])?'usuariAplicacioConfiguracio.policyIdentifier':__theForm.labels[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_policyIdentifier_columnvalueid">
            <form:errors path="usuariAplicacioConfiguracio.policyIdentifier" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER)? ' uneditable-input' : ''}"  style="" maxlength="100" path="usuariAplicacioConfiguracio.policyIdentifier"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH)}">
        <tr id="usuariAplicacioConfiguracio_policyIdentifierHash_rowid">
          <td id="usuariAplicacioConfiguracio_policyIdentifierHash_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH])?'usuariAplicacioConfiguracio.policyIdentifierHash':__theForm.labels[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_policyIdentifierHash_columnvalueid">
            <form:errors path="usuariAplicacioConfiguracio.policyIdentifierHash" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH)? ' uneditable-input' : ''}"  style="" maxlength="256" path="usuariAplicacioConfiguracio.policyIdentifierHash"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM)}">
        <tr id="usuariAplicacioConfiguracio_policyIdentifierHashAlgorithm_rowid">
          <td id="usuariAplicacioConfiguracio_policyIdentifierHashAlgorithm_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM])?'usuariAplicacioConfiguracio.policyIdentifierHashAlgorithm':__theForm.labels[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_policyIdentifierHashAlgorithm_columnvalueid">
            <form:errors path="usuariAplicacioConfiguracio.policyIdentifierHashAlgorithm" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM)? ' uneditable-input' : ''}"  style="" maxlength="50" path="usuariAplicacioConfiguracio.policyIdentifierHashAlgorithm"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT)}">
        <tr id="usuariAplicacioConfiguracio_policyUrlDocument_rowid">
          <td id="usuariAplicacioConfiguracio_policyUrlDocument_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT])?'usuariAplicacioConfiguracio.policyUrlDocument':__theForm.labels[UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_policyUrlDocument_columnvalueid">
            <form:errors path="usuariAplicacioConfiguracio.policyUrlDocument" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT)? ' uneditable-input' : ''}"  style="" maxlength="255" path="usuariAplicacioConfiguracio.policyUrlDocument"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA)}">
        <tr id="usuariAplicacioConfiguracio_tipusOperacioFirma_rowid">
          <td id="usuariAplicacioConfiguracio_tipusOperacioFirma_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA])?'usuariAplicacioConfiguracio.tipusOperacioFirma':__theForm.labels[UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_tipusOperacioFirma_columnvalueid">
          <form:errors path="usuariAplicacioConfiguracio.tipusOperacioFirma" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA)}" >
          <form:hidden path="usuariAplicacioConfiguracio.tipusOperacioFirma"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.tipusOperacioFirma,__theForm.listOfValuesForTipusOperacioFirma)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="usuariAplicacioConfiguracio_tipusOperacioFirma"  onchange="if(typeof onChangeTipusOperacioFirma == 'function') {  onChangeTipusOperacioFirma(this); };"  cssClass="form-control col-md-9-optional" path="usuariAplicacioConfiguracio.tipusOperacioFirma">
            <c:forEach items="${__theForm.listOfValuesForTipusOperacioFirma}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.TIPUSFIRMA)}">
        <tr id="usuariAplicacioConfiguracio_tipusFirma_rowid">
          <td id="usuariAplicacioConfiguracio_tipusFirma_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.TIPUSFIRMA])?'usuariAplicacioConfiguracio.tipusFirma':__theForm.labels[UsuariAplicacioConfiguracioFields.TIPUSFIRMA]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.TIPUSFIRMA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.TIPUSFIRMA]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_tipusFirma_columnvalueid">
          <form:errors path="usuariAplicacioConfiguracio.tipusFirma" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.TIPUSFIRMA)}" >
          <form:hidden path="usuariAplicacioConfiguracio.tipusFirma"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.tipusFirma,__theForm.listOfValuesForTipusFirma)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.TIPUSFIRMA)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="usuariAplicacioConfiguracio_tipusFirma"  onchange="if(typeof onChangeTipusFirma == 'function') {  onChangeTipusFirma(this); };"  cssClass="form-control col-md-9-optional" path="usuariAplicacioConfiguracio.tipusFirma">
            <c:forEach items="${__theForm.listOfValuesForTipusFirma}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMA)}">
        <tr id="usuariAplicacioConfiguracio_algorismeDeFirma_rowid">
          <td id="usuariAplicacioConfiguracio_algorismeDeFirma_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMA])?'usuariAplicacioConfiguracio.algorismeDeFirma':__theForm.labels[UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMA]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMA]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_algorismeDeFirma_columnvalueid">
          <form:errors path="usuariAplicacioConfiguracio.algorismeDeFirma" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMA)}" >
          <form:hidden path="usuariAplicacioConfiguracio.algorismeDeFirma"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.algorismeDeFirma,__theForm.listOfValuesForAlgorismeDeFirma)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMA)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="usuariAplicacioConfiguracio_algorismeDeFirma"  onchange="if(typeof onChangeAlgorismeDeFirma == 'function') {  onChangeAlgorismeDeFirma(this); };"  cssClass="form-control col-md-9-optional" path="usuariAplicacioConfiguracio.algorismeDeFirma">
            <c:forEach items="${__theForm.listOfValuesForAlgorismeDeFirma}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.MODEDEFIRMA)}">
        <tr id="usuariAplicacioConfiguracio_modeDeFirma_rowid">
          <td id="usuariAplicacioConfiguracio_modeDeFirma_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.MODEDEFIRMA])?'usuariAplicacioConfiguracio.modeDeFirma':__theForm.labels[UsuariAplicacioConfiguracioFields.MODEDEFIRMA]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.MODEDEFIRMA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.MODEDEFIRMA]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_modeDeFirma_columnvalueid">
          <form:errors path="usuariAplicacioConfiguracio.modeDeFirma" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.MODEDEFIRMA)}" >
          <form:hidden path="usuariAplicacioConfiguracio.modeDeFirma"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.modeDeFirma,__theForm.listOfValuesForModeDeFirma)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.MODEDEFIRMA)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="usuariAplicacioConfiguracio_modeDeFirma"  onchange="if(typeof onChangeModeDeFirma == 'function') {  onChangeModeDeFirma(this); };"  cssClass="form-control col-md-9-optional" path="usuariAplicacioConfiguracio.modeDeFirma">
            <c:forEach items="${__theForm.listOfValuesForModeDeFirma}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA)}">
        <tr id="usuariAplicacioConfiguracio_comprovarNifFirma_rowid">
          <td id="usuariAplicacioConfiguracio_comprovarNifFirma_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA])?'usuariAplicacioConfiguracio.comprovarNifFirma':__theForm.labels[UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_comprovarNifFirma_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA)}" >
              <form:errors path="usuariAplicacioConfiguracio.comprovarNifFirma" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA)? 'false' : 'true'}" path="usuariAplicacioConfiguracio.comprovarNifFirma" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA)}" >
                <fmt:message key="genapp.checkbox.${__theForm.usuariAplicacioConfiguracio.comprovarNifFirma}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT)}">
        <tr id="usuariAplicacioConfiguracio_checkCanviatDocFirmat_rowid">
          <td id="usuariAplicacioConfiguracio_checkCanviatDocFirmat_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT])?'usuariAplicacioConfiguracio.checkCanviatDocFirmat':__theForm.labels[UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_checkCanviatDocFirmat_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT)}" >
              <form:errors path="usuariAplicacioConfiguracio.checkCanviatDocFirmat" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT)? 'false' : 'true'}" path="usuariAplicacioConfiguracio.checkCanviatDocFirmat" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT)}" >
                <fmt:message key="genapp.checkbox.${__theForm.usuariAplicacioConfiguracio.checkCanviatDocFirmat}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.VALIDARFIRMA)}">
        <tr id="usuariAplicacioConfiguracio_validarFirma_rowid">
          <td id="usuariAplicacioConfiguracio_validarFirma_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.VALIDARFIRMA])?'usuariAplicacioConfiguracio.validarFirma':__theForm.labels[UsuariAplicacioConfiguracioFields.VALIDARFIRMA]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.VALIDARFIRMA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.VALIDARFIRMA]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_validarFirma_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.VALIDARFIRMA)}" >
              <form:errors path="usuariAplicacioConfiguracio.validarFirma" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.VALIDARFIRMA)? 'false' : 'true'}" path="usuariAplicacioConfiguracio.validarFirma" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.VALIDARFIRMA)}" >
                <fmt:message key="genapp.checkbox.${__theForm.usuariAplicacioConfiguracio.validarFirma}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID)}">
        <tr id="usuariAplicacioConfiguracio_pluginFirmaServidorID_rowid">
          <td id="usuariAplicacioConfiguracio_pluginFirmaServidorID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID])?'usuariAplicacioConfiguracio.pluginFirmaServidorID':__theForm.labels[UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_pluginFirmaServidorID_columnvalueid">
          <form:errors path="usuariAplicacioConfiguracio.pluginFirmaServidorID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID)}" >
          <form:hidden path="usuariAplicacioConfiguracio.pluginFirmaServidorID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.pluginFirmaServidorID,__theForm.listOfPluginForPluginFirmaServidorID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="usuariAplicacioConfiguracio_pluginFirmaServidorID"  onchange="if(typeof onChangePluginFirmaServidorID == 'function') {  onChangePluginFirmaServidorID(this); };"  cssClass="form-control col-md-9-optional" path="usuariAplicacioConfiguracio.pluginFirmaServidorID">
            <c:forEach items="${__theForm.listOfPluginForPluginFirmaServidorID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT)}">
        <tr id="usuariAplicacioConfiguracio_upgradeSignFormat_rowid">
          <td id="usuariAplicacioConfiguracio_upgradeSignFormat_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT])?'usuariAplicacioConfiguracio.upgradeSignFormat':__theForm.labels[UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_upgradeSignFormat_columnvalueid">
          <form:errors path="usuariAplicacioConfiguracio.upgradeSignFormat" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT)}" >
          <form:hidden path="usuariAplicacioConfiguracio.upgradeSignFormat"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.upgradeSignFormat,__theForm.listOfValuesForUpgradeSignFormat)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="usuariAplicacioConfiguracio_upgradeSignFormat"  onchange="if(typeof onChangeUpgradeSignFormat == 'function') {  onChangeUpgradeSignFormat(this); };"  cssClass="form-control col-md-9-optional" path="usuariAplicacioConfiguracio.upgradeSignFormat">
            <c:forEach items="${__theForm.listOfValuesForUpgradeSignFormat}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.usuariAplicacioConfiguracio.upgradeSignFormat }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.usuariAplicacioConfiguracio.upgradeSignFormat }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS)}">
        <tr id="usuariAplicacioConfiguracio_politicaSegellatDeTemps_rowid">
          <td id="usuariAplicacioConfiguracio_politicaSegellatDeTemps_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS])?'usuariAplicacioConfiguracio.politicaSegellatDeTemps':__theForm.labels[UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS]}" ></i>
              </c:if>
            </td>
          <td id="usuariAplicacioConfiguracio_politicaSegellatDeTemps_columnvalueid">
          <form:errors path="usuariAplicacioConfiguracio.politicaSegellatDeTemps" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS)}" >
          <form:hidden path="usuariAplicacioConfiguracio.politicaSegellatDeTemps"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.politicaSegellatDeTemps,__theForm.listOfValuesForPoliticaSegellatDeTemps)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="usuariAplicacioConfiguracio_politicaSegellatDeTemps"  onchange="if(typeof onChangePoliticaSegellatDeTemps == 'function') {  onChangePoliticaSegellatDeTemps(this); };"  cssClass="form-control col-md-9-optional" path="usuariAplicacioConfiguracio.politicaSegellatDeTemps">
            <c:forEach items="${__theForm.listOfValuesForPoliticaSegellatDeTemps}" var="tmp">
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
        
