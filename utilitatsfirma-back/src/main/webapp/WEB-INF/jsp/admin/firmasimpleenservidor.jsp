<%@page language="java"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"
%>
<div class="clear"></div>
<div class="spacer"></div>

<center><h1>Firma Simple en Servidor (Test)</h1></center>

<c:if test="${not empty errorMsg}">
  <div class="alert alert-danger" role="alert" style="color:#a94442;background-color:#f2dede;border:1px solid #ebccd1;padding:10px;border-radius:4px;margin:10px 0;">
    <img src="<c:url value="/img/icn_alert_error.png"/>" alt="error"/> <c:out value="${errorMsg}"/>
  </div>
</c:if>

<%-- ===================== PAS 1: Elegir usuari aplicaci&oacute; ===================== --%>
<c:if test="${step == 1}">
  <h3>Pas 1 de 4: Elegir usuari aplicaci&oacute;</h3>
  <form method="post" action="<c:url value="/admin/firmasimpleenservidor/perfils"/>">
    <div style="margin:10px 0;">
      <label for="usuariAplicacioID">Usuari aplicaci&oacute;:</label>
      <select name="usuariAplicacioID" id="usuariAplicacioID" class="form-control">
        <option value="">-- Seleccionau un usuari aplicaci&oacute; --</option>
        <c:forEach var="ua" items="${usuariAplicacions}">
          <option value="<c:out value="${ua.usuariAplicacioID}"/>">
            <c:out value="${ua.usuariAplicacioID}"/><c:if test="${not empty ua.descripcio}"> - <c:out value="${ua.descripcio}"/></c:if>
          </option>
        </c:forEach>
      </select>
    </div>
    <button type="submit" class="btn btn-primary">Seg&uuml;ent</button>
  </form>
</c:if>

<%-- ===================== PAS 2: Elegir perfil ===================== --%>
<c:if test="${step == 2}">
  <h3>Pas 2 de 4: Elegir perfil</h3>
  <p><strong>Usuari aplicaci&oacute;:</strong> <c:out value="${usuariAplicacioID}"/></p>
  <form method="post" action="<c:url value="/admin/firmasimpleenservidor/configuracions"/>">
    <input type="hidden" name="usuariAplicacioID" value="<c:out value="${usuariAplicacioID}"/>"/>
    <div style="margin:10px 0;">
      <label for="perfilID">Perfil:</label>
      <select name="perfilID" id="perfilID" class="form-control">
        <option value="">-- Seleccionau un perfil --</option>
        <c:forEach var="p" items="${perfils}">
          <option value="<c:out value="${p.usuariAplicacioPerfilID}"/>">
            <c:out value="${p.codi}"/><c:if test="${not empty p.nom}"> - <c:out value="${p.nom}"/></c:if>
          </option>
        </c:forEach>
      </select>
    </div>
    <a href="<c:url value="/admin/firmasimpleenservidor/inici"/>" class="btn btn-default">Enrere</a>
    <button type="submit" class="btn btn-primary">Seg&uuml;ent</button>
  </form>
</c:if>

<%-- ===================== PAS 3: Elegir configuraci&oacute; ===================== --%>
<c:if test="${step == 3}">
  <h3>Pas 3 de 4: Elegir configuraci&oacute; del perfil</h3>
  <p><strong>Usuari aplicaci&oacute;:</strong> <c:out value="${usuariAplicacioID}"/></p>
  <p><strong>Perfil:</strong> <c:out value="${perfilCodi}"/><c:if test="${not empty perfilNom}"> - <c:out value="${perfilNom}"/></c:if></p>
  <form method="post" action="<c:url value="/admin/firmasimpleenservidor/fitxer"/>">
    <input type="hidden" name="usuariAplicacioID" value="<c:out value="${usuariAplicacioID}"/>"/>
    <input type="hidden" name="perfilID" value="<c:out value="${perfilID}"/>"/>
    <div style="margin:10px 0;">
      <label for="configID">Configuraci&oacute;:</label>
      <select name="configID" id="configID" class="form-control">
        <option value="">-- Seleccionau una configuraci&oacute; --</option>
        <c:forEach var="cfg" items="${configuracions}">
          <option value="<c:out value="${cfg.usuariAplicacioConfigID}"/>">
            <c:out value="${cfg.usuariAplicacioConfigID}"/><c:if test="${not empty cfg.nom}"> - <c:out value="${cfg.nom}"/></c:if>
          </option>
        </c:forEach>
      </select>
    </div>
    <button type="submit" class="btn btn-primary">Seg&uuml;ent</button>
  </form>
</c:if>

<%-- ===================== PAS 4: Seleccionar fitxer i signar ===================== --%>
<c:if test="${step == 4}">
  <h3>Pas 4 de 4: Seleccionar el fitxer a signar</h3>
  <p><strong>Usuari aplicaci&oacute;:</strong> <c:out value="${usuariAplicacioID}"/></p>
  <p><strong>Perfil:</strong> <c:out value="${perfilCodi}"/></p>
  <p><strong>Configuraci&oacute;:</strong> <c:out value="${configID}"/><c:if test="${not empty configNom}"> - <c:out value="${configNom}"/></c:if></p>
  <form method="post" action="<c:url value="/admin/firmasimpleenservidor/signar"/>" enctype="multipart/form-data">
    <input type="hidden" name="usuariAplicacioID" value="<c:out value="${usuariAplicacioID}"/>"/>
    <input type="hidden" name="perfilID" value="<c:out value="${perfilID}"/>"/>
    <input type="hidden" name="configID" value="<c:out value="${configID}"/>"/>
    <div style="margin:10px 0;">
      <label for="fileToSign">Fitxer a signar:</label>
      <input type="file" name="fileToSign" id="fileToSign" class="form-control"/>
    </div>
    <a href="<c:url value="/admin/firmasimpleenservidor/inici"/>" class="btn btn-default">Tornar a comen&ccedil;ar</a>
    <button type="submit" class="btn btn-primary">Signar document</button>
  </form>
</c:if>

<%-- ===================== PAS 5: Resultat de la firma ===================== --%>
<c:if test="${step == 5}">
  <h3>Resultat de la firma</h3>

  <div style="margin:15px 0;">
    <c:choose>
      <c:when test="${signedFileAvailable}">
        <a href="<c:url value="/admin/firmasimpleenservidor/descarregar"/>" class="btn btn-success">
          <img src="<c:url value="/img/icn_alert_success.png"/>" alt="ok"/> Descarregar document firmat
        </a>
      </c:when>
      <c:otherwise>
        <span>No hi ha cap document firmat disponible per descarregar.</span>
      </c:otherwise>
    </c:choose>
    &nbsp;
    <a href="<c:url value="/admin/firmasimpleenservidor/inici"/>" class="btn btn-default">Nova firma</a>
  </div>

  <h4>Dades del context</h4>
  <table class="table table-bordered" border="1" cellpadding="4" style="border-collapse:collapse;">
    <tr><th>Usuari aplicaci&oacute;</th><td><c:out value="${usuariAplicacioID}"/></td></tr>
    <tr><th>Perfil (ID)</th><td><c:out value="${perfilID}"/></td></tr>
    <tr><th>Configuraci&oacute; (ID)</th><td><c:out value="${configID}"/></td></tr>
  </table>

  <h4>PassarelaSignatureInServerResults</h4>
  <table class="table table-bordered" border="1" cellpadding="4" style="border-collapse:collapse;">
    <tr>
      <th>pluginFirmaEnServidorId</th>
      <td><c:out value="${pluginFirmaEnServidorId}"/></td>
    </tr>
  </table>

  <h4>PassarelaFullResults &raquo; signaturesSetStatus</h4>
  <table class="table table-bordered" border="1" cellpadding="4" style="border-collapse:collapse;">
    <tr><th>status</th><td><c:out value="${signaturesSetStatusText}"/></td></tr>
    <tr><th>errorMessage</th><td><c:out value="${signaturesSetStatus.errorMessage}"/></td></tr>
    <tr>
      <th>errorStackTrace</th>
      <td>
        <c:if test="${not empty signaturesSetStatus.errorStackTrace}">
          <pre style="white-space:pre-wrap;max-height:200px;overflow:auto;"><c:out value="${signaturesSetStatus.errorStackTrace}"/></pre>
        </c:if>
      </td>
    </tr>
  </table>

  <h4>PassarelaFullResults &raquo; signResults</h4>
  <c:forEach var="psr" items="${signResults}">
    <table class="table table-bordered" border="1" cellpadding="4" style="border-collapse:collapse;margin-bottom:15px;">
      <tr><th>signID</th><td><c:out value="${psr.signID}"/></td></tr>
      <tr><th>status</th><td><c:out value="${statusTextBySignID[psr.signID]}"/></td></tr>
      <tr><th>errorMessage</th><td><c:out value="${psr.errorMessage}"/></td></tr>
      <tr>
        <th>errorStackTrace</th>
        <td>
          <c:if test="${not empty psr.errorStackTrace}">
            <pre style="white-space:pre-wrap;max-height:200px;overflow:auto;"><c:out value="${psr.errorStackTrace}"/></pre>
          </c:if>
        </td>
      </tr>
      <c:if test="${not empty psr.signedFile}">
        <tr><th>signedFile.nom</th><td><c:out value="${psr.signedFile.nom}"/></td></tr>
        <tr><th>signedFile.mime</th><td><c:out value="${psr.signedFile.mime}"/></td></tr>
        <tr><th>signedFile.tamany</th><td><c:out value="${psr.signedFile.tamany}"/> bytes</td></tr>
      </c:if>
      <c:if test="${not empty psr.validationInfo}">
        <tr><th>validationInfo.checkAdministrationIDOfSigner</th><td><c:out value="${psr.validationInfo.checkAdministrationIDOfSigner}"/></td></tr>
        <tr><th>validationInfo.checkDocumentModifications</th><td><c:out value="${psr.validationInfo.checkDocumentModifications}"/></td></tr>
        <tr><th>validationInfo.checkValidationSignature</th><td><c:out value="${psr.validationInfo.checkValidationSignature}"/></td></tr>
        <tr><th>validationInfo.noCheckValidationReason</th><td><c:out value="${psr.validationInfo.noCheckValidationReason}"/></td></tr>
      </c:if>
    </table>
  </c:forEach>

  <h4>validacioResponseBySignID</h4>
  <c:choose>
    <c:when test="${empty validacioResponseBySignID}">
      <p>(sense informaci&oacute; de validaci&oacute;)</p>
    </c:when>
    <c:otherwise>
      <c:forEach var="entry" items="${validacioResponseBySignID}">
        <table class="table table-bordered" border="1" cellpadding="4" style="border-collapse:collapse;margin-bottom:15px;">
          <tr><th>signID</th><td><c:out value="${entry.key}"/></td></tr>
          <tr><th>signType</th><td><c:out value="${entry.value.signType}"/></td></tr>
          <tr><th>mime</th><td><c:out value="${entry.value.mime}"/></td></tr>
          <tr><th>extension</th><td><c:out value="${entry.value.extension}"/></td></tr>
          <tr><th>nifFirmant</th><td><c:out value="${entry.value.nifFirmant}"/></td></tr>
          <tr><th>checkAdministrationIDOfSigner</th><td><c:out value="${entry.value.checkAdministrationIDOfSigner}"/></td></tr>
          <tr><th>checkDocumentModifications</th><td><c:out value="${entry.value.checkDocumentModifications}"/></td></tr>
          <tr><th>checkValidationSignature</th><td><c:out value="${entry.value.checkValidationSignature}"/></td></tr>
          <tr><th>numeroSerieCertificat</th><td><c:out value="${entry.value.numeroSerieCertificat}"/></td></tr>
          <tr><th>emissorCertificat</th><td><c:out value="${entry.value.emissorCertificat}"/></td></tr>
          <tr><th>subjectCertificat</th><td><c:out value="${entry.value.subjectCertificat}"/></td></tr>
          <tr><th>perfilDeFirma</th><td><c:out value="${entry.value.perfilDeFirma}"/></td></tr>
        </table>
      </c:forEach>
    </c:otherwise>
  </c:choose>
</c:if>

