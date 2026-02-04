<%@page import="es.caib.utilitatsfirma.logic.utils.SignatureUtils"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Arrays"%>
<%@page import="org.fundaciobit.pluginsib.utils.signature.SignatureCommonUtils"%>
<%@page import="es.caib.utilitatsfirma.commons.utils.Constants"%>
<%@page import="org.fundaciobit.pluginsib.utils.signature.SignatureConstants"%>
<%@page import="es.caib.utilitatsfirma.model.fields.UsuariAplicacioConfiguracioFields"%>
<%@page import="org.fundaciobit.genapp.common.query.Field"%>

<style>
div.usMarker {
    display: inline-block;
    background-color: grey;
    color: white;
    font-size: xx-small;
    font-weight: normal;
    border-radius: 5px;
    line-height: 10px;
    padding: 2px;
    margin: 2px;
}

div.markerContainer {
    text-align: left;
}

span.usFieldMarker {
    background-color: grey;
    margin: 3px;
    padding: 3px;
    border-radius: 5px;
    line-height: 10px;
}
</style>



<script type="text/javascript">
 
 <c:if test="${not __theForm.view}" >

alert("La configuració de l'aplicació no es pot modificar en mode visualització.");

 // Politica de Firma (ocultar o mostrar valor)
 onChangeUsPoliticaDeFirma(document.getElementById("<%=UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA.fullName.replace('.', '_')%>"));

 function onChangeUsPoliticaDeFirma(combo) {
<%final Field<?>[] fieldsUsPolitica = {UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER,
		UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH,
		UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM,
		UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT};%>
     var value = combo.options[combo.selectedIndex].value;
     if (value == <%=Constants.US_POLITICA_DE_FIRMA_OBLIGATORI_DEFINIT%>) { 
       <%for (int c = 0; c < fieldsUsPolitica.length; c++) {%>
       document.getElementById("<%=fieldsUsPolitica[c].fullName.replace('.', '_')%>_rowid").style.display = '';
       <%}%>
     } else {
       <%for (int c = 0; c < fieldsUsPolitica.length; c++) {%>
       document.getElementById("<%=fieldsUsPolitica[c].fullName.replace('.', '_')%>_rowid").style.display = 'none';
       <%}%>
     }

 }
 


 var modesFirmaPerTipusFirma = {
         <%Map<String, Integer[]> modesFirmaByTipusFirma = SignatureConstants.SIGNMODES_BY_SIGNTYPE;
	for (String type : modesFirmaByTipusFirma.keySet()) {%>
         <%=SignatureUtils.convertApiSignTypeToPortafibSignType(type)%>: <%=Arrays.toString(modesFirmaByTipusFirma.get(type))%>,
         <%}%>
 }

 var modesFirmaToString = {
         <%for (int mode : SignatureConstants.ALL_SING_MODES) {
	String modeStr = SignatureCommonUtils.signModeToString(mode);
	int i = modeStr.indexOf('(');
	if (i != -1) {
		modeStr = modeStr.substring(0, i);
	}
	modeStr = modeStr.replace("SIGN_MODE_", "").replace("_", " ");%>
         <%=mode%>: '<%=modeStr%>',
         <%}%>
 }
 

 
 function onChangeTipusFirma(combo) {
   if (combo == null) {
         return;
   }
       
 
   
   var signType = combo.options[combo.selectedIndex].value;
   
   var modesFirma = modesFirmaPerTipusFirma[signType];
   
   var modeFirmaInput = document.getElementById("<%=UsuariAplicacioConfiguracioFields.MODEDEFIRMA.fullName.replace('.', '_')%>");
   
   var modeFirmaCurrent = modeFirmaInput.options[modeFirmaInput.selectedIndex].value;
   
   // Eliminem els modes de firma existents
   modeFirmaInput.innerHTML = "";
   
   // Afegim els modes de firma corresponents (guardats dins l'array modesFirma)
   var modeFirmaToSelect;
   modesFirma.forEach(function(mode) {
     var option = document.createElement("option");
     option.text = modesFirmaToString[mode];
     option.value = mode;
     if (mode == modeFirmaCurrent) {
         modeFirmaToSelect = mode;
     }
     modeFirmaInput.add(option);
   });

   // Deixam el mode de firma anterior si es possible
   if (modeFirmaToSelect) {
       modeFirmaInput.value = modeFirmaToSelect;
   }

   
   
 }
 
 // Tipus Firma i Mode de Firma
 onChangeTipusFirma(document.getElementById("<%=UsuariAplicacioConfiguracioFields.TIPUSFIRMA.fullName.replace('.', '_')%>"));
 

 
 </c:if>
 
 </script>
