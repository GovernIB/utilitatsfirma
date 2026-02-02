package es.caib.utilitatsfirma.back.controller.webdb;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.utils.Utils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.GroupByItem;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.validation.ValidationWebUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Arrays;

import es.caib.utilitatsfirma.back.form.webdb.*;
import es.caib.utilitatsfirma.back.form.webdb.ConfiguracioDeFirmaPerUsuariAplicacioForm;

import es.caib.utilitatsfirma.back.validator.webdb.ConfiguracioDeFirmaPerUsuariAplicacioWebValidator;

import es.caib.utilitatsfirma.persistence.ConfiguracioDeFirmaPerUsuariAplicacioJPA;
import es.caib.utilitatsfirma.model.entity.ConfiguracioDeFirmaPerUsuariAplicacio;
import es.caib.utilitatsfirma.model.fields.*;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileAttribute;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import es.caib.utilitatsfirma.back.utils.Tab;

/**
 * Controller per gestionar un ConfiguracioDeFirmaPerUsuariAplicacio
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@MenuOption(labelCode="configuracioDeFirmaPerUsuariAplicacio.configuracioDeFirmaPerUsuariAplicacio.plural", order=0, group=Tab.MENU_WEBDB)
@Controller
@RequestMapping(value = "/webdb/configuracioDeFirmaPerUsuariAplicacio")
@SessionAttributes(types = { ConfiguracioDeFirmaPerUsuariAplicacioForm.class, ConfiguracioDeFirmaPerUsuariAplicacioFilterForm.class })
@Tile(name="configuracioDeFirmaPerUsuariAplicacioFormWebDB", contentJsp="/WEB-INF/jsp/webdb/configuracioDeFirmaPerUsuariAplicacioForm.jsp", extendsTile=Tab.MENU_WEBDB,
      type=TileType.WEBDB_FORM , attributes={ @TileAttribute(name="titol", value="configuracioDeFirmaPerUsuariAplicacio.configuracioDeFirmaPerUsuariAplicacio")})
@Tile(name="configuracioDeFirmaPerUsuariAplicacioListWebDB", contentJsp="/WEB-INF/jsp/webdb/configuracioDeFirmaPerUsuariAplicacioList.jsp", extendsTile=Tab.MENU_WEBDB,
       type=TileType.WEBDB_LIST, attributes={ @TileAttribute(name="titol", value="configuracioDeFirmaPerUsuariAplicacio.configuracioDeFirmaPerUsuariAplicacio") })
public class ConfiguracioDeFirmaPerUsuariAplicacioController
    extends es.caib.utilitatsfirma.back.controller.UtilitatsFirmaBaseController<ConfiguracioDeFirmaPerUsuariAplicacio, java.lang.Long> implements ConfiguracioDeFirmaPerUsuariAplicacioFields {

  @EJB(mappedName = es.caib.utilitatsfirma.ejb.ConfiguracioDeFirmaPerUsuariAplicacioService.JNDI_NAME)
  protected es.caib.utilitatsfirma.ejb.ConfiguracioDeFirmaPerUsuariAplicacioService configuracioDeFirmaPerUsuariAplicacioEjb;

  @Autowired
  private ConfiguracioDeFirmaPerUsuariAplicacioWebValidator configuracioDeFirmaPerUsuariAplicacioWebValidator;

  @Autowired
  protected ConfiguracioDeFirmaPerUsuariAplicacioRefList configuracioDeFirmaPerUsuariAplicacioRefList;

  // References 
  @Autowired
  protected UsuariAplicacioRefList usuariAplicacioRefList;

  // References 
  @Autowired
  protected UsuariAplicacioConfiguracioRefList usuariAplicacioConfiguracioRefList;

  /**
   * Llistat de totes ConfiguracioDeFirmaPerUsuariAplicacio
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    ConfiguracioDeFirmaPerUsuariAplicacioFilterForm ff;
    ff = (ConfiguracioDeFirmaPerUsuariAplicacioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar ConfiguracioDeFirmaPerUsuariAplicacio de forma paginada
   */
  @RequestMapping(value = "/list/{pagina}", method = RequestMethod.GET)
  public ModelAndView llistatPaginat(HttpServletRequest request,
    HttpServletResponse response, @PathVariable Integer pagina)
      throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileList());
    llistat(mav, request, getConfiguracioDeFirmaPerUsuariAplicacioFilterForm(pagina, mav, request));
    return mav;
  }

  public ConfiguracioDeFirmaPerUsuariAplicacioFilterForm getConfiguracioDeFirmaPerUsuariAplicacioFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    ConfiguracioDeFirmaPerUsuariAplicacioFilterForm configuracioDeFirmaPerUsuariAplicacioFilterForm;
    configuracioDeFirmaPerUsuariAplicacioFilterForm = (ConfiguracioDeFirmaPerUsuariAplicacioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(configuracioDeFirmaPerUsuariAplicacioFilterForm == null) {
      configuracioDeFirmaPerUsuariAplicacioFilterForm = new ConfiguracioDeFirmaPerUsuariAplicacioFilterForm();
      configuracioDeFirmaPerUsuariAplicacioFilterForm.setContexte(getContextWeb());
      configuracioDeFirmaPerUsuariAplicacioFilterForm.setEntityNameCode(getEntityNameCode());
      configuracioDeFirmaPerUsuariAplicacioFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      configuracioDeFirmaPerUsuariAplicacioFilterForm.setNou(true);
    } else {
      configuracioDeFirmaPerUsuariAplicacioFilterForm.setNou(false);
    }
    configuracioDeFirmaPerUsuariAplicacioFilterForm.setPage(pagina == null ? 1 : pagina);
    return configuracioDeFirmaPerUsuariAplicacioFilterForm;
  }

  /**
   * Segona i següent peticions per llistar ConfiguracioDeFirmaPerUsuariAplicacio de forma paginada
   * 
   * @param request
   * @param pagina
   * @param filterForm
   * @return
   * @throws I18NException
   */
  @RequestMapping(value = "/list/{pagina}", method = RequestMethod.POST)
  public ModelAndView llistatPaginat(HttpServletRequest request,
      HttpServletResponse response,@PathVariable Integer pagina,
      @ModelAttribute ConfiguracioDeFirmaPerUsuariAplicacioFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getConfiguracioDeFirmaPerUsuariAplicacioFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de ConfiguracioDeFirmaPerUsuariAplicacio de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<ConfiguracioDeFirmaPerUsuariAplicacio> llistat(ModelAndView mav, HttpServletRequest request,
     ConfiguracioDeFirmaPerUsuariAplicacioFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<ConfiguracioDeFirmaPerUsuariAplicacio> configuracioDeFirmaPerUsuariAplicacio = processarLlistat(configuracioDeFirmaPerUsuariAplicacioEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("configuracioDeFirmaPerUsuariAplicacioItems", configuracioDeFirmaPerUsuariAplicacio);

    mav.addObject("configuracioDeFirmaPerUsuariAplicacioFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, configuracioDeFirmaPerUsuariAplicacio, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, configuracioDeFirmaPerUsuariAplicacio);

    return configuracioDeFirmaPerUsuariAplicacio;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(ConfiguracioDeFirmaPerUsuariAplicacioFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<ConfiguracioDeFirmaPerUsuariAplicacio> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field usuariAplicacioID
    {
      _listSKV = getReferenceListForUsuariAplicacioID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfUsuariAplicacioForUsuariAplicacioID(_tmp);
      if (filterForm.getGroupByFields().contains(USUARIAPLICACIOID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, USUARIAPLICACIOID, false);
      };
    }

    // Field usuariAplicacioConfigID
    {
      _listSKV = getReferenceListForUsuariAplicacioConfigID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfUsuariAplicacioConfiguracioForUsuariAplicacioConfigID(_tmp);
      if (filterForm.getGroupByFields().contains(USUARIAPLICACIOCONFIGID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, USUARIAPLICACIOCONFIGID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    ConfiguracioDeFirmaPerUsuariAplicacioFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<ConfiguracioDeFirmaPerUsuariAplicacio> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_CONFIGURACIODEFIRMAPERUSUARIAPLICACIO_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(USUARIAPLICACIOID, filterForm.getMapOfUsuariAplicacioForUsuariAplicacioID());
    __mapping.put(USUARIAPLICACIOCONFIGID, filterForm.getMapOfUsuariAplicacioConfiguracioForUsuariAplicacioConfigID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou ConfiguracioDeFirmaPerUsuariAplicacio
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearConfiguracioDeFirmaPerUsuariAplicacioGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    ConfiguracioDeFirmaPerUsuariAplicacioForm configuracioDeFirmaPerUsuariAplicacioForm = getConfiguracioDeFirmaPerUsuariAplicacioForm(null, false, request, mav);
    mav.addObject("configuracioDeFirmaPerUsuariAplicacioForm" ,configuracioDeFirmaPerUsuariAplicacioForm);
    fillReferencesForForm(configuracioDeFirmaPerUsuariAplicacioForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public ConfiguracioDeFirmaPerUsuariAplicacioForm getConfiguracioDeFirmaPerUsuariAplicacioForm(ConfiguracioDeFirmaPerUsuariAplicacioJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    ConfiguracioDeFirmaPerUsuariAplicacioForm configuracioDeFirmaPerUsuariAplicacioForm;
    if(_jpa == null) {
      configuracioDeFirmaPerUsuariAplicacioForm = new ConfiguracioDeFirmaPerUsuariAplicacioForm(new ConfiguracioDeFirmaPerUsuariAplicacioJPA(), true);
    } else {
      configuracioDeFirmaPerUsuariAplicacioForm = new ConfiguracioDeFirmaPerUsuariAplicacioForm(_jpa, false);
      configuracioDeFirmaPerUsuariAplicacioForm.setView(__isView);
    }
    configuracioDeFirmaPerUsuariAplicacioForm.setContexte(getContextWeb());
    configuracioDeFirmaPerUsuariAplicacioForm.setEntityNameCode(getEntityNameCode());
    configuracioDeFirmaPerUsuariAplicacioForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return configuracioDeFirmaPerUsuariAplicacioForm;
  }

  public void fillReferencesForForm(ConfiguracioDeFirmaPerUsuariAplicacioForm configuracioDeFirmaPerUsuariAplicacioForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (configuracioDeFirmaPerUsuariAplicacioForm.getListOfUsuariAplicacioForUsuariAplicacioID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForUsuariAplicacioID(request, mav, configuracioDeFirmaPerUsuariAplicacioForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      configuracioDeFirmaPerUsuariAplicacioForm.setListOfUsuariAplicacioForUsuariAplicacioID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (configuracioDeFirmaPerUsuariAplicacioForm.getListOfUsuariAplicacioConfiguracioForUsuariAplicacioConfigID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForUsuariAplicacioConfigID(request, mav, configuracioDeFirmaPerUsuariAplicacioForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      configuracioDeFirmaPerUsuariAplicacioForm.setListOfUsuariAplicacioConfiguracioForUsuariAplicacioConfigID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou ConfiguracioDeFirmaPerUsuariAplicacio
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearConfiguracioDeFirmaPerUsuariAplicacioPost(@ModelAttribute ConfiguracioDeFirmaPerUsuariAplicacioForm configuracioDeFirmaPerUsuariAplicacioForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ConfiguracioDeFirmaPerUsuariAplicacioJPA configuracioDeFirmaPerUsuariAplicacio = configuracioDeFirmaPerUsuariAplicacioForm.getConfiguracioDeFirmaPerUsuariAplicacio();

    try {
      preValidate(request, configuracioDeFirmaPerUsuariAplicacioForm, result);
      getWebValidator().validate(configuracioDeFirmaPerUsuariAplicacioForm, result);
      postValidate(request,configuracioDeFirmaPerUsuariAplicacioForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        configuracioDeFirmaPerUsuariAplicacio = create(request, configuracioDeFirmaPerUsuariAplicacio);
        createMessageSuccess(request, "success.creation", configuracioDeFirmaPerUsuariAplicacio.getConfigsPerUsrAppID());
        configuracioDeFirmaPerUsuariAplicacioForm.setConfiguracioDeFirmaPerUsuariAplicacio(configuracioDeFirmaPerUsuariAplicacio);
        return getRedirectWhenCreated(request, configuracioDeFirmaPerUsuariAplicacioForm);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.creation", null, __e);
      log.error(msg, __e);
      return getTileForm();
    }
  }

  @RequestMapping(value = "/view/{configsPerUsrAppID}", method = RequestMethod.GET)
  public ModelAndView veureConfiguracioDeFirmaPerUsuariAplicacioGet(@PathVariable("configsPerUsrAppID") java.lang.Long configsPerUsrAppID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewConfiguracioDeFirmaPerUsuariAplicacioGet(configsPerUsrAppID,
        request, response, true);
  }


  protected ModelAndView editAndViewConfiguracioDeFirmaPerUsuariAplicacioGet(@PathVariable("configsPerUsrAppID") java.lang.Long configsPerUsrAppID,
      HttpServletRequest request,
      HttpServletResponse response, boolean __isView) throws I18NException {
    if((!__isView) && !isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    } else {
      if(__isView && !isActiveFormView()) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return null;
      }
    }
    ConfiguracioDeFirmaPerUsuariAplicacioJPA configuracioDeFirmaPerUsuariAplicacio = findByPrimaryKey(request, configsPerUsrAppID);

    if (configuracioDeFirmaPerUsuariAplicacio == null) {
      createMessageWarning(request, "error.notfound", configsPerUsrAppID);
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      ConfiguracioDeFirmaPerUsuariAplicacioForm configuracioDeFirmaPerUsuariAplicacioForm = getConfiguracioDeFirmaPerUsuariAplicacioForm(configuracioDeFirmaPerUsuariAplicacio, __isView, request, mav);
      configuracioDeFirmaPerUsuariAplicacioForm.setView(__isView);
      if(__isView) {
        configuracioDeFirmaPerUsuariAplicacioForm.setAllFieldsReadOnly(ALL_CONFIGURACIODEFIRMAPERUSUARIAPLICACIO_FIELDS);
        configuracioDeFirmaPerUsuariAplicacioForm.setSaveButtonVisible(false);
        configuracioDeFirmaPerUsuariAplicacioForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(configuracioDeFirmaPerUsuariAplicacioForm, request, mav);
      mav.addObject("configuracioDeFirmaPerUsuariAplicacioForm", configuracioDeFirmaPerUsuariAplicacioForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un ConfiguracioDeFirmaPerUsuariAplicacio existent
   */
  @RequestMapping(value = "/{configsPerUsrAppID}/edit", method = RequestMethod.GET)
  public ModelAndView editarConfiguracioDeFirmaPerUsuariAplicacioGet(@PathVariable("configsPerUsrAppID") java.lang.Long configsPerUsrAppID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewConfiguracioDeFirmaPerUsuariAplicacioGet(configsPerUsrAppID,
        request, response, false);
  }



  /**
   * Editar un ConfiguracioDeFirmaPerUsuariAplicacio existent
   */
  @RequestMapping(value = "/{configsPerUsrAppID}/edit", method = RequestMethod.POST)
  public String editarConfiguracioDeFirmaPerUsuariAplicacioPost(@ModelAttribute ConfiguracioDeFirmaPerUsuariAplicacioForm configuracioDeFirmaPerUsuariAplicacioForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ConfiguracioDeFirmaPerUsuariAplicacioJPA configuracioDeFirmaPerUsuariAplicacio = configuracioDeFirmaPerUsuariAplicacioForm.getConfiguracioDeFirmaPerUsuariAplicacio();

    try {
      preValidate(request, configuracioDeFirmaPerUsuariAplicacioForm, result);
      getWebValidator().validate(configuracioDeFirmaPerUsuariAplicacioForm, result);
      postValidate(request, configuracioDeFirmaPerUsuariAplicacioForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        configuracioDeFirmaPerUsuariAplicacio = update(request, configuracioDeFirmaPerUsuariAplicacio);
        createMessageSuccess(request, "success.modification", configuracioDeFirmaPerUsuariAplicacio.getConfigsPerUsrAppID());
        status.setComplete();
        return getRedirectWhenModified(request, configuracioDeFirmaPerUsuariAplicacioForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          configuracioDeFirmaPerUsuariAplicacio.getConfigsPerUsrAppID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, configuracioDeFirmaPerUsuariAplicacioForm, __e);
    }

  }


  /**
   * Eliminar un ConfiguracioDeFirmaPerUsuariAplicacio existent
   */
  @RequestMapping(value = "/{configsPerUsrAppID}/delete")
  public String eliminarConfiguracioDeFirmaPerUsuariAplicacio(@PathVariable("configsPerUsrAppID") java.lang.Long configsPerUsrAppID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      ConfiguracioDeFirmaPerUsuariAplicacio configuracioDeFirmaPerUsuariAplicacio = this.findByPrimaryKey(request, configsPerUsrAppID);
      if (configuracioDeFirmaPerUsuariAplicacio == null) {
        String __msg = createMessageError(request, "error.notfound", configsPerUsrAppID);
        return getRedirectWhenDelete(request, configsPerUsrAppID, new Exception(__msg));
      } else {
        delete(request, configuracioDeFirmaPerUsuariAplicacio);
        createMessageSuccess(request, "success.deleted", configsPerUsrAppID);
        return getRedirectWhenDelete(request, configsPerUsrAppID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", configsPerUsrAppID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, configsPerUsrAppID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute ConfiguracioDeFirmaPerUsuariAplicacioFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarConfiguracioDeFirmaPerUsuariAplicacio(stringToPK(seleccionats[i]), request, response);
    }
  }
  if (redirect == null) {
    redirect = getRedirectWhenDelete(request, null,null);
  }

  return redirect;
}



public java.lang.Long stringToPK(String value) {
  return java.lang.Long.parseLong(value, 10);
}

  @Override
  public String[] getArgumentsMissatge(Object __configsPerUsrAppID, Throwable e) {
    java.lang.Long configsPerUsrAppID = (java.lang.Long)__configsPerUsrAppID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (configsPerUsrAppID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(configsPerUsrAppID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "configuracioDeFirmaPerUsuariAplicacio.configuracioDeFirmaPerUsuariAplicacio";
  }

  public String getEntityNameCodePlural() {
    return "configuracioDeFirmaPerUsuariAplicacio.configuracioDeFirmaPerUsuariAplicacio.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("configuracioDeFirmaPerUsuariAplicacio.configsPerUsrAppID");
  }

  @InitBinder("configuracioDeFirmaPerUsuariAplicacioFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("configuracioDeFirmaPerUsuariAplicacioForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "configuracioDeFirmaPerUsuariAplicacio.configsPerUsrAppID");
  }

  public ConfiguracioDeFirmaPerUsuariAplicacioWebValidator getWebValidator() {
    return configuracioDeFirmaPerUsuariAplicacioWebValidator;
  }


  public void setWebValidator(ConfiguracioDeFirmaPerUsuariAplicacioWebValidator __val) {
    if (__val != null) {
      this.configuracioDeFirmaPerUsuariAplicacioWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de ConfiguracioDeFirmaPerUsuariAplicacio
   */
  @RequestMapping(value = "/{configsPerUsrAppID}/cancel")
  public String cancelConfiguracioDeFirmaPerUsuariAplicacio(@PathVariable("configsPerUsrAppID") java.lang.Long configsPerUsrAppID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, configsPerUsrAppID);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de ConfiguracioDeFirmaPerUsuariAplicacio
   */
  @RequestMapping(value = "/cancel")
  public String cancelConfiguracioDeFirmaPerUsuariAplicacio(HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, null);
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

  // Mètodes a sobreescriure 

  public boolean isActiveList() {
    return true;
  }


  public boolean isActiveFormNew() {
    return true;
  }


  public boolean isActiveFormEdit() {
    return true;
  }


  public boolean isActiveDelete() {
    return true;
  }


  public boolean isActiveFormView() {
    return isActiveFormEdit();
  }


  public List<StringKeyValue> getReferenceListForUsuariAplicacioID(HttpServletRequest request,
       ModelAndView mav, ConfiguracioDeFirmaPerUsuariAplicacioForm configuracioDeFirmaPerUsuariAplicacioForm, Where where)  throws I18NException {
    if (configuracioDeFirmaPerUsuariAplicacioForm.isHiddenField(USUARIAPLICACIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (configuracioDeFirmaPerUsuariAplicacioForm.isReadOnlyField(USUARIAPLICACIOID)) {
      _where = UsuariAplicacioFields.USUARIAPLICACIOID.equal(configuracioDeFirmaPerUsuariAplicacioForm.getConfiguracioDeFirmaPerUsuariAplicacio().getUsuariAplicacioID());
    }
    return getReferenceListForUsuariAplicacioID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForUsuariAplicacioID(HttpServletRequest request,
       ModelAndView mav, ConfiguracioDeFirmaPerUsuariAplicacioFilterForm configuracioDeFirmaPerUsuariAplicacioFilterForm,
       List<ConfiguracioDeFirmaPerUsuariAplicacio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (configuracioDeFirmaPerUsuariAplicacioFilterForm.isHiddenField(USUARIAPLICACIOID)
       && !configuracioDeFirmaPerUsuariAplicacioFilterForm.isGroupByField(USUARIAPLICACIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(USUARIAPLICACIOID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (ConfiguracioDeFirmaPerUsuariAplicacio _item : list) {
        _pkList.add(_item.getUsuariAplicacioID());
        }
        _w = UsuariAplicacioFields.USUARIAPLICACIOID.in(_pkList);
      }
    return getReferenceListForUsuariAplicacioID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForUsuariAplicacioID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return usuariAplicacioRefList.getReferenceList(UsuariAplicacioFields.USUARIAPLICACIOID, where );
  }


  public List<StringKeyValue> getReferenceListForUsuariAplicacioConfigID(HttpServletRequest request,
       ModelAndView mav, ConfiguracioDeFirmaPerUsuariAplicacioForm configuracioDeFirmaPerUsuariAplicacioForm, Where where)  throws I18NException {
    if (configuracioDeFirmaPerUsuariAplicacioForm.isHiddenField(USUARIAPLICACIOCONFIGID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (configuracioDeFirmaPerUsuariAplicacioForm.isReadOnlyField(USUARIAPLICACIOCONFIGID)) {
      _where = UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID.equal(configuracioDeFirmaPerUsuariAplicacioForm.getConfiguracioDeFirmaPerUsuariAplicacio().getUsuariAplicacioConfigID());
    }
    return getReferenceListForUsuariAplicacioConfigID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForUsuariAplicacioConfigID(HttpServletRequest request,
       ModelAndView mav, ConfiguracioDeFirmaPerUsuariAplicacioFilterForm configuracioDeFirmaPerUsuariAplicacioFilterForm,
       List<ConfiguracioDeFirmaPerUsuariAplicacio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (configuracioDeFirmaPerUsuariAplicacioFilterForm.isHiddenField(USUARIAPLICACIOCONFIGID)
       && !configuracioDeFirmaPerUsuariAplicacioFilterForm.isGroupByField(USUARIAPLICACIOCONFIGID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(USUARIAPLICACIOCONFIGID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (ConfiguracioDeFirmaPerUsuariAplicacio _item : list) {
        _pkList.add(_item.getUsuariAplicacioConfigID());
        }
        _w = UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID.in(_pkList);
      }
    return getReferenceListForUsuariAplicacioConfigID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForUsuariAplicacioConfigID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return usuariAplicacioConfiguracioRefList.getReferenceList(UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID, where );
  }


    @Override
    /** Ha de ser igual que el RequestMapping de la Classe */
    public String getContextWeb() {
        RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
        final String[] values = rm.value();
        if (values.length == 1) {
            return values[0];
        } else {
            final HttpServletRequest request;
            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            final String servletPath = request.getServletPath();

            for (String webcontext : values) {
                if (servletPath.startsWith(webcontext)) {
                    return webcontext;
                }
            }

            log.warn(" No puc trobar el contextweb associat a la cridada.");
            log.warn(" ==== RequestMapping::value=" + Arrays.toString(values));
            log.warn(" ++++ getContextWeb::Scheme: " + request.getScheme());
            log.warn(" ++++ getContextWeb::PathInfo: " + request.getPathInfo());
            log.warn(" ++++ getContextWeb::PathTrans: " + request.getPathTranslated());
            log.warn(" ++++ getContextWeb::ContextPath: " + request.getContextPath());
            log.warn(" ++++ getContextWeb::ServletPath: " + request.getServletPath());
            log.warn(" ++++ getContextWeb::getRequestURI: " + request.getRequestURI());
            log.warn(" ++++ getContextWeb::getRequestURL: " + request.getRequestURL().toString());
            log.warn(" ++++ getContextWeb::getQueryString: " + request.getQueryString());

            return values[0];
        }  }

  public void preValidate(HttpServletRequest request,ConfiguracioDeFirmaPerUsuariAplicacioForm configuracioDeFirmaPerUsuariAplicacioForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,ConfiguracioDeFirmaPerUsuariAplicacioForm configuracioDeFirmaPerUsuariAplicacioForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, ConfiguracioDeFirmaPerUsuariAplicacioFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, ConfiguracioDeFirmaPerUsuariAplicacioFilterForm filterForm,  List<ConfiguracioDeFirmaPerUsuariAplicacio> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, ConfiguracioDeFirmaPerUsuariAplicacioForm configuracioDeFirmaPerUsuariAplicacioForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, ConfiguracioDeFirmaPerUsuariAplicacioForm configuracioDeFirmaPerUsuariAplicacioForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long configsPerUsrAppID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long configsPerUsrAppID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
        try {
            Set<Tile> rm;
            rm=AnnotationUtils.getDeclaredRepeatableAnnotations(this.getClass(), Tile.class);
            if (rm != null && !rm.isEmpty()) {
                String trobada = null;
                for (Tile tile : rm) {
                    if (tile.type() == TileType.WEBDB_FORM) {
                        trobada = tile.name();
                    }
                }
                if (trobada != null) {
                    return trobada;
                }
            }
        } catch (Exception e) {
            log.error("Error en el getTileForm: " + e.getMessage(), e);
        }
    return "configuracioDeFirmaPerUsuariAplicacioFormWebDB";
  }

    public String getTileList() {
        try {
            Set<Tile> rm;
            rm=AnnotationUtils.getDeclaredRepeatableAnnotations(this.getClass(), Tile.class);
            if (rm != null && !rm.isEmpty()) {
                String trobada = null;
                for (Tile tile : rm) {
                    if (tile.type() == TileType.WEBDB_LIST) {
                        trobada = tile.name();
                    }
                }
                if (trobada != null) {
                    return trobada;
                }
            }
        } catch (Exception e) {
            log.error("Error en el getTileList: " + e.getMessage(), e);
        }
        return "configuracioDeFirmaPerUsuariAplicacioListWebDB";
    }

  public String getSessionAttributeFilterForm() {
    return "ConfiguracioDeFirmaPerUsuariAplicacio_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public ConfiguracioDeFirmaPerUsuariAplicacioJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long configsPerUsrAppID) throws I18NException {
    return (ConfiguracioDeFirmaPerUsuariAplicacioJPA) configuracioDeFirmaPerUsuariAplicacioEjb.findByPrimaryKey(configsPerUsrAppID);
  }


  public ConfiguracioDeFirmaPerUsuariAplicacioJPA create(HttpServletRequest request, ConfiguracioDeFirmaPerUsuariAplicacioJPA configuracioDeFirmaPerUsuariAplicacio)
    throws I18NException, I18NValidationException {
    return (ConfiguracioDeFirmaPerUsuariAplicacioJPA) configuracioDeFirmaPerUsuariAplicacioEjb.create(configuracioDeFirmaPerUsuariAplicacio);
  }


  public ConfiguracioDeFirmaPerUsuariAplicacioJPA update(HttpServletRequest request, ConfiguracioDeFirmaPerUsuariAplicacioJPA configuracioDeFirmaPerUsuariAplicacio)
    throws I18NException, I18NValidationException {
    return (ConfiguracioDeFirmaPerUsuariAplicacioJPA) configuracioDeFirmaPerUsuariAplicacioEjb.update(configuracioDeFirmaPerUsuariAplicacio);
  }


  public void delete(HttpServletRequest request, ConfiguracioDeFirmaPerUsuariAplicacio configuracioDeFirmaPerUsuariAplicacio) throws I18NException {
    configuracioDeFirmaPerUsuariAplicacioEjb.delete(configuracioDeFirmaPerUsuariAplicacio);
  }

} // Final de Classe

