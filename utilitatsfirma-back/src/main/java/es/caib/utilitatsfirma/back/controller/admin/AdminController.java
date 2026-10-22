package es.caib.utilitatsfirma.back.controller.admin;


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fundaciobit.genapp.common.web.html.IconUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.web.menuoptions.MenuItem;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOptionManager;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileAttribute;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import es.caib.utilitatsfirma.back.utils.Tab;
import es.caib.utilitatsfirma.commons.utils.Configuracio;
import es.caib.utilitatsfirma.logic.SampleLogicaService;

/**
 * 
 * @author anadal
 *
 */
/*

@MenuOption(
        labelCode = "=Menú ADMIN Option 1",
        order = 10,
        group = Tab.MENU_ADMIN,
        baseLink = "/admin/option1",
        relativeLink = "")
@Tile(name = "option1Admin", extendsTile = Tab.MENU_ADMIN, type = TileType.ANOTHER, contentJsp = "/WEB-INF/jsp/admin/admin.jsp")
@MenuOption(
        labelCode = "=Menú ADMIN Option 2",
        order = 20,
        group = Tab.MENU_ADMIN,
        baseLink = "/admin/option2",
        relativeLink = "",
        addSeparatorBefore = true)
@Tile(name = "option2Admin", extendsTile =  "option1Admin", type = TileType.ANOTHER)
*/
@Controller
@RequestMapping(value = "/admin")
@MenuOption(
        labelCode = "=Contents of utilitatsfirma.properties file",
        order = 1000,
        group = Tab.MENU_ADMIN,
        baseLink = "/admin/properties",
        relativeLink = "",
        addSeparatorBefore = true)
@MenuOption(
        labelCode = "=Contents of utilitatsfirma.system.properties file",
        order = 1010,
        group = Tab.MENU_ADMIN,
        baseLink = "/admin/systemproperties",
        relativeLink = "")
@MenuOption(
        labelCode = "=Reload contents of property files",
        order = 1020,
        group = Tab.MENU_ADMIN,
        baseLink = "/admin/reloadproperties",
        relativeLink = "")
@MenuOption(
        labelCode = "=Size of database tables",
        order = 1030,
        group = Tab.MENU_ADMIN,
        baseLink = "/admin/sizeofdatabasetables",
        relativeLink = "",
        addSeparatorBefore = true)
@MenuOption(
        labelCode = "=WebPages list",
        order = 1050,
        group = Tab.MENU_ADMIN,
        baseLink = "/admin/webpages",
        relativeLink = "")

@Tile(
        name = AdminController.KEYVALUE_ADMIN_TILE,
        extendsTile = Tab.MENU_ADMIN,
        contentJsp = "/WEB-INF/jsp/common/keyvalue.jsp",
        attributes = {
            @TileAttribute(name = "titol", value = "admin.admin")
        },
        type = TileType.ANOTHER)

public class AdminController {

    public static final String KEYVALUE_ADMIN_TILE = "keyvalueAdmin";
    
    @EJB(mappedName=SampleLogicaService.JNDI_NAME)
    SampleLogicaService sampleLogicaService;
    
/*
    @RequestMapping(value = "/option1")
    public ModelAndView option1(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ModelAndView mav = new ModelAndView("option1Admin");
        mav.addObject("optionNumber", "OPCIÓ ADMIN -1-");
        return mav;

    }

    @RequestMapping(value = "/option2")
    public ModelAndView option2(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ModelAndView mav = new ModelAndView("option2Admin");
        mav.addObject("optionNumber", "OPCIÓ ADMIN -2-");
        return mav;
    }
*/

    @RequestMapping(value = "/properties")
    public ModelAndView properties(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Properties prop = Configuracio.getAppProperties();

        List<KeyValueItem> keyValuelist = new ArrayList<KeyValueItem>();

        for (Object key : prop.keySet()) {
            keyValuelist.add(new KeyValueItem((String) key, prop.getProperty((String) key, ""),
                    "<i class=\"" + IconUtils.ICON_INFO + "\"></i>", ""));
        }

        Collections.sort(keyValuelist);

        ModelAndView mav = new ModelAndView("keyvalueAdmin");
        mav.addObject("title", "Item list of utilitatsfirma.app.properties file");
        mav.addObject("subtitle", "");
        mav.addObject("keyValueList", keyValuelist);
        return mav;
    }
    
    @RequestMapping(value = "/sizeofdatabasetables")
    public ModelAndView tablesize(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Map<String, Long> sizes = sampleLogicaService.getTableSizes();

        List<KeyValueItem> keyValuelist = new ArrayList<KeyValueItem>();

        for (Map.Entry<String, Long> entry : sizes.entrySet()) {
            keyValuelist.add(new KeyValueItem((String) entry.getKey(), entry.getValue() + " bytes" ));
        }

        Collections.sort(keyValuelist);

        ModelAndView mav = new ModelAndView("keyvalueAdmin");
        mav.addObject("title", "Size of database tables");
        mav.addObject("subtitle", "");
        mav.addObject("keyValueList", keyValuelist);
        return mav;
    }
    
    

    @RequestMapping(value = "/systemproperties")
    public ModelAndView systemproperties(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Properties prop = Configuracio.getAppSystemProperties();

        List<KeyValueItem> keyValuelist = new ArrayList<KeyValueItem>();

        for (Object key : prop.keySet()) {
            keyValuelist.add(new KeyValueItem((String) key, "***************"));
        }

        Collections.sort(keyValuelist);

        ModelAndView mav = new ModelAndView("keyvalueAdmin");
        mav.addObject("title", "Item list of utilitatsfirma.system.properties file");
        mav.addObject("subtitle", "");
        mav.addObject("keyValueList", keyValuelist);
        return mav;
    }

    @RequestMapping(value = "/reloadproperties")
    public String reloadproperties(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Configuracio.reloadProperties();

        return "redirect:/admin/properties";
    }
    
    

    @RequestMapping(value = "/webpages")
    public ModelAndView webPages(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String[] tabs = { Tab.MENU_PUBLIC, Tab.MENU_COMMON, Tab.MENU_USER, Tab.MENU_ADMIN };

        List<KeyValueItem> keyValuelist = new ArrayList<KeyValueItem>();

        for (String tab : tabs) {
            List<MenuItem> items = MenuOptionManager.getMenuItems(tab);
            for (MenuItem item : items) {

                if (item == null) {
                    continue;
                }

                String label = item.getLabel();

                if (label.startsWith("=")) {
                    label = label.substring(1);
                } else {
                    label = I18NUtils.tradueix(label);
                }

                KeyValueItem keyValueItem = new KeyValueItem(label, Configuracio.getBackUrl() + item.getUrl());

                keyValueItem.setPre(tab);

                keyValuelist.add(keyValueItem);

                // System.out.println("Tab: " + tab + ", Label: " + item.getLabel() + ", Link: " + item.getLink());
            }
        }

        ModelAndView mav = new ModelAndView("keyvalueAdmin");
        mav.addObject("title", "Llistat d'Opcions de Menú");
        mav.addObject("subtitle",
                "Per descarregar les URL a les pàgines web en format pla, feis clic aquí:"
                + " <b><a target=\"_blank\"  href=\"" + request.getContextPath() 
                + "/admin/webpagesplain?includenew=true\" >[AMB NEW]</a></b>"
                + " &nbsp;&nbsp;<b><a target=\"_blank\"  href=\"" + request.getContextPath() 
                + "/admin/webpagesplain?includenew=false\" >[SENSE NEW]</a></b>");
        mav.addObject("keyValueList", keyValuelist);
        return mav;
    }

    @RequestMapping(value = "/webpagesplain")
    public void webPagesPlain(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String includeNewStr = request.getParameter("includenew");

        boolean includeNew = "true".equalsIgnoreCase(includeNewStr);

        String[] tabs = { Tab.MENU_PUBLIC, Tab.MENU_COMMON, Tab.MENU_USER, Tab.MENU_ADMIN };

        // Plain text response
        response.setContentType("text/plain");

        PrintWriter writer = response.getWriter();

        for (String tab : tabs) {

            Map<Class<?>, List<MenuItem>> fullInfo = MenuOptionManager.getMenuItemsByClass(tab);

            fullInfo.forEach((classe, items) -> {
                //writer.println("Class: " + classe.getName());
                for (MenuItem item : items) {
                    if (item == null) {
                        continue;
                    }
                    writer.println(Configuracio.getBackUrl() + item.getUrl());
                }

                // Si classe  té mètode isActiveFormNew i retorna true, afegir una URL amb /new
                if (includeNew) {

                    try {
                        if (classe.getMethod("isActiveFormNew") != null) {
                            Object obj = classe.getDeclaredConstructor().newInstance();
                            Boolean isActiveFormNew = (Boolean) classe.getMethod("isActiveFormNew").invoke(obj);

                            if (Boolean.TRUE.equals(isActiveFormNew)) {

                                // Cercar si classe té un mètode "String getContextWeb()"

                                if (classe.getMethod("getContextWeb") != null) {

                                    String contextWeb = (String) classe.getMethod("getContextWeb").invoke(obj);

                                    writer.println(Configuracio.getBackUrl() + contextWeb + "/new");
                                }

                            }

                        }
                    } catch (NoSuchMethodException e) {
                        // No action needed, just skip
                    } catch (Throwable e) {
                        // No action needed, just skip
                        e.printStackTrace();
                    }
                }

            });

        }

        writer.println(Configuracio.getBackUrl() + "/public/index.html");
        writer.println(Configuracio.getBackUrl() + "/common/principal.html");

        writer.flush();
        writer.close();
    }

    public class KeyValueItem implements Comparable<KeyValueItem> {
        private String key;
        private String value;
        private String pre;
        private String post;

        public KeyValueItem(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public KeyValueItem(String key, String value, String pre, String post) {
            this.key = key;
            this.value = value;
            this.pre = pre;
            this.post = post;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getPre() {
            return pre;
        }

        public void setPre(String pre) {
            this.pre = pre;
        }

        public String getPost() {
            return post;
        }

        public void setPost(String post) {
            this.post = post;
        }

        @Override
        public int compareTo(KeyValueItem o2) {
            return this.getKey().compareTo(o2.getKey());
        }
    }


}
