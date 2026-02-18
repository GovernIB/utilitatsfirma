      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${tipusDocumental.tipusDocumentalID}"/>
       &nbsp;
      </td>
      </c:if>

