  <c:if test="${empty tipusDocumentalItems}">
     <%@include file="tipusDocumentalListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty tipusDocumentalItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="tipusDocumentalListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="tipusDocumentalListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="tipusDocumentalListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="tipusDocumental" items="${tipusDocumentalItems}">

        <tr id="tipusDocumental_rowid_${tipusDocumental.tipusDocumentalID}">
          <%@include file="tipusDocumentalListCoreMultipleSelect.jsp" %>

          <%@include file="tipusDocumentalListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="tipusDocumentalListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
