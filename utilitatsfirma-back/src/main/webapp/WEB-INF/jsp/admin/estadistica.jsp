<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<h1>${actionLabel}${rang}</h1>

<form method="get" action="<c:url value='/admin/estadistica/'/>" class="form-inline">
    <label for="actionSelect">Tipus:</label> <select id="actionSelect" name="action" class="form-control">
        <option value="1" <c:if test="${selectedAction == 1}"> selected="selected"</c:if>>Firmes</option>
        <option value="2" <c:if test="${selectedAction == 2}"> selected="selected"</c:if>>Upgrades</option>
        <option value="3" <c:if test="${selectedAction == 3}"> selected="selected"</c:if>>Validacions</option>
    </select> &nbsp;&nbsp; <label for="rangeSelect">Rang:</label> <select id="rangeSelect" name="rang" class="form-control">
        <option value="1" <c:if test="${selectedRange == 1}"> selected="selected"</c:if>>Any</option>
        <option value="2" <c:if test="${selectedRange == 2}"> selected="selected"</c:if>>Mes</option>
        <option value="3" <c:if test="${selectedRange == 3}"> selected="selected"</c:if>>Dia</option>
    </select> &nbsp;&nbsp; <label for="dateInput">Data:</label> <input type="date" class="form-control" id="dateInput" name="date"
        value="${selectedDate}" /> &nbsp;&nbsp;
    <button type="submit" class="btn btn-primary">Aplicar</button>
</form>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<canvas id="barChart" style="padding: 50px"></canvas>
<script>
  const ctx = document.getElementById('barChart').getContext('2d');
  const labels = [
      <c:forEach var="entry" items="${labels}">'${entry}',</c:forEach>]; // or hours/days of month
  const dataOk = [ <c:forEach var="entry" items="${valuesOK}">${entry},</c:forEach>];
  const dataError = [ <c:forEach var="entry" items="${valuesError}">${entry},</c:forEach>];
  new Chart(ctx, {
    type: 'bar',
    data: {
      labels: labels,
      datasets: [{
        label: '${titleOK}',
        data: dataOk,
        backgroundColor: 'rgba(186, 219, 168, 0.8)'
      }, {
        label: '${titleError}',
        data: dataError,
        backgroundColor: 'rgba(242, 180, 172, 0.8)'
      }]
    },
    options: {
      responsive: true,
      scales: { y: { beginAtZero: true } }
    }
  });
</script>