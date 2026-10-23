  <c:if test="${empty validacioItems}">
     <%@include file="validacioListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty validacioItems}">

  <div class="row" style="margin-left: 0px;">
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  <fieldset>
  <legend class="sr-only">Selecci&oacute; d'elements de la llista</legend>
  </c:if>
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;" > 
    <thead>
      <tr>

          <%@include file="validacioListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="validacioListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="validacioListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="validacio" items="${validacioItems}">

        <tr id="validacio_rowid_${validacio.validacioID}">
          <%@include file="validacioListCoreMultipleSelect.jsp" %>

          <%@include file="validacioListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="validacioListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  </fieldset>
  </c:if>
  </div>
  </c:if>
  
