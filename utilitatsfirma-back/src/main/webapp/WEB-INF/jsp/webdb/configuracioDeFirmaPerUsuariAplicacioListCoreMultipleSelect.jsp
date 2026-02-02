      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${configuracioDeFirmaPerUsuariAplicacio.configsPerUsrAppID}"/>
       &nbsp;
      </td>
      </c:if>

