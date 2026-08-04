<%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@page import="org.fundaciobit.genapp.common.web.menuoptions.MenuOptionManager"
%><%@page import="org.fundaciobit.genapp.common.web.menuoptions.MenuItem"
%><%@page import="es.caib.utilitatsfirma.back.utils.Tab"
%><%@page import="java.util.List"
%><%@page import="java.util.ArrayList"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<c:set var="url" value="${urlActual}" />
<div>
  <h5><fmt:message key="menuinici" /></h5>
     
    <%
    List<List<MenuItem>> menus = new ArrayList<List<MenuItem>>();
    MenuItem[] menusAddicionals = null;
    Object loginInfo = request.getAttribute("loginInfo");
    
    if (loginInfo != null) {
        
        menusAddicionals = new MenuItem[] {
                new MenuItem("=Pàgina Inicial","/common/principal.html", 10),
                /*
                null,
                new MenuItem("=Menú Inici Option 1","/common/option1", 20),
                new MenuItem("=Menú Inici Option 2","/common/option2", 30),
                */
        };
    }
    List<MenuItem> discoveredMenus = MenuOptionManager.getMenuItems(Tab.MENU_PUBLIC_AND_COMMON, menusAddicionals);
    menus.add(discoveredMenus);
    %>

   <%@ include file="/WEB-INF/jsp/moduls/menu_role_generator.jsp"%>
  </ul>
</div>

