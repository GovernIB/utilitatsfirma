  <c:if test="${empty configuracioDeFirmaPerUsuariAplicacioItems}">
     <%@include file="configuracioDeFirmaPerUsuariAplicacioListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty configuracioDeFirmaPerUsuariAplicacioItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="configuracioDeFirmaPerUsuariAplicacioListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="configuracioDeFirmaPerUsuariAplicacioListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="configuracioDeFirmaPerUsuariAplicacioListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="configuracioDeFirmaPerUsuariAplicacio" items="${configuracioDeFirmaPerUsuariAplicacioItems}">

        <tr id="configuracioDeFirmaPerUsuariAplicacio_rowid_${configuracioDeFirmaPerUsuariAplicacio.configsPerUsrAppID}">
          <%@include file="configuracioDeFirmaPerUsuariAplicacioListCoreMultipleSelect.jsp" %>

          <%@include file="configuracioDeFirmaPerUsuariAplicacioListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="configuracioDeFirmaPerUsuariAplicacioListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
