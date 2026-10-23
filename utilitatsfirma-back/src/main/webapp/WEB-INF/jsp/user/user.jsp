<%@page import="org.springframework.security.core.Authentication"
%><%@page import="org.springframework.security.core.context.SecurityContext"
%><%@page import="org.springframework.security.core.context.SecurityContextHolder"
%><%@page language="java" 
%><%@include file="/WEB-INF/jsp/moduls/includes.jsp" 
%><%@page import="es.caib.utilitatsfirma.back.utils.Tab" %>
<div class="clear"></div>
<div class="spacer"></div>

<div style="text-align:center;"><h1>Option Page ${optionNumber}</h1></div>

<div style="text-align:center;"><img src="<c:url value="/img/icn_alert_success.png"/>"  alt="exemple" title="exemple"/></div>

Exemple de Controler que "extends" de WebDB:

<table style="border: 1px solid; border-collapse: collapse;">
<tr><td style="border: 1px solid;">
<textarea cols="120" rows="25" style="font-family: 'Courier New'">
package es.caib.utilitatsfirma.back.controller.user;

import es.caib.utilitatsfirma.back.controller.webdb.AlumneController;
import es.caib.utilitatsfirma.back.form.webdb.AlumneFilterForm;
import es.caib.utilitatsfirma.back.form.webdb.AlumneForm;

import es.caib.utilitatsfirma.back.utils.Tab;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@MenuOption(labelCode = "alumne.alumne.plural", order = 25, group = Tab.MENU_USER)
@Controller
@RequestMapping(value = "/user/alumne")
@SessionAttributes(types = { AlumneForm.class, AlumneFilterForm.class })
@Tile(name = "alumneListUser", type = TileType.WEBDB_LIST, extendsTile = Tab.MENU_USER)
@Tile(name = "alumneFormUser", type = TileType.WEBDB_FORM, extendsTile = Tab.MENU_USER)
public class AlumneUserController extends AlumneController {

}
</textarea>
</td></tr>
</table>