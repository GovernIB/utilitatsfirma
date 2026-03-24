package es.caib.utilitatsfirma.back.controller.webdb;

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
import es.caib.utilitatsfirma.back.form.webdb.TipusDocumentalForm;

import es.caib.utilitatsfirma.back.validator.webdb.TipusDocumentalWebValidator;

import es.caib.utilitatsfirma.persistence.TipusDocumentalJPA;
import es.caib.utilitatsfirma.model.entity.TipusDocumental;
import es.caib.utilitatsfirma.model.fields.*;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileAttribute;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import es.caib.utilitatsfirma.back.utils.Tab;

/**
 * Controller per gestionar un TipusDocumental
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@MenuOption(labelCode="tipusDocumental.tipusDocumental.plural", order=60, group=Tab.MENU_WEBDB)
@Controller
@RequestMapping(value = "/webdb/tipusDocumental")
@SessionAttributes(types = { TipusDocumentalForm.class, TipusDocumentalFilterForm.class })
@Tile(name="tipusDocumentalFormWebDB", extendsTile=Tab.MENU_WEBDB,
    // Els següents atributs no són necessaris si heredes aquesta classe
    contentJsp="/WEB-INF/jsp/webdb/tipusDocumentalForm.jsp", type=TileType.WEBDB_FORM,
    attributes={ @TileAttribute(name="titol", value="tipusDocumental.tipusDocumental")})
@Tile(name="tipusDocumentalListWebDB", extendsTile=Tab.MENU_WEBDB,
    // Els següents atributs no són necessaris si heredes aquesta classe 
    contentJsp="/WEB-INF/jsp/webdb/tipusDocumentalList.jsp", type=TileType.WEBDB_LIST,
    attributes={ @TileAttribute(name="titol", value="tipusDocumental.tipusDocumental")})
public class TipusDocumentalController
    extends es.caib.utilitatsfirma.back.controller.UtilitatsFirmaBaseController<TipusDocumental, java.lang.Long> implements TipusDocumentalFields {

  @EJB(mappedName = es.caib.utilitatsfirma.ejb.TipusDocumentalService.JNDI_NAME)
  protected es.caib.utilitatsfirma.ejb.TipusDocumentalService tipusDocumentalEjb;

  @Autowired
  private TipusDocumentalWebValidator tipusDocumentalWebValidator;

  @Autowired
  protected TipusDocumentalRefList tipusDocumentalRefList;

  /**
   * Llistat de totes TipusDocumental
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    TipusDocumentalFilterForm ff;
    ff = (TipusDocumentalFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar TipusDocumental de forma paginada
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
    llistat(mav, request, getTipusDocumentalFilterForm(pagina, mav, request));
    return mav;
  }

  public TipusDocumentalFilterForm getTipusDocumentalFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    TipusDocumentalFilterForm tipusDocumentalFilterForm;
    tipusDocumentalFilterForm = (TipusDocumentalFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(tipusDocumentalFilterForm == null) {
      tipusDocumentalFilterForm = new TipusDocumentalFilterForm();
      tipusDocumentalFilterForm.setContexte(getContextWeb());
      tipusDocumentalFilterForm.setEntityNameCode(getEntityNameCode());
      tipusDocumentalFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      tipusDocumentalFilterForm.setNou(true);
    } else {
      tipusDocumentalFilterForm.setNou(false);
    }
    tipusDocumentalFilterForm.setPage(pagina == null ? 1 : pagina);
    return tipusDocumentalFilterForm;
  }

  /**
   * Segona i següent peticions per llistar TipusDocumental de forma paginada
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
      @ModelAttribute TipusDocumentalFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getTipusDocumentalFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de TipusDocumental de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<TipusDocumental> llistat(ModelAndView mav, HttpServletRequest request,
     TipusDocumentalFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<TipusDocumental> tipusDocumental = processarLlistat(tipusDocumentalEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("tipusDocumentalItems", tipusDocumental);

    mav.addObject("tipusDocumentalFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, tipusDocumental, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, tipusDocumental);

    return tipusDocumental;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(TipusDocumentalFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<TipusDocumental> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    TipusDocumentalFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<TipusDocumental> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_TIPUSDOCUMENTAL_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou TipusDocumental
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearTipusDocumentalGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    TipusDocumentalForm tipusDocumentalForm = getTipusDocumentalForm(null, false, request, mav);
    mav.addObject("tipusDocumentalForm" ,tipusDocumentalForm);
    fillReferencesForForm(tipusDocumentalForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public TipusDocumentalForm getTipusDocumentalForm(TipusDocumentalJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    TipusDocumentalForm tipusDocumentalForm;
    if(_jpa == null) {
      tipusDocumentalForm = new TipusDocumentalForm(new TipusDocumentalJPA(), true);
    } else {
      tipusDocumentalForm = new TipusDocumentalForm(_jpa, false);
      tipusDocumentalForm.setView(__isView);
    }
    tipusDocumentalForm.setContexte(getContextWeb());
    tipusDocumentalForm.setEntityNameCode(getEntityNameCode());
    tipusDocumentalForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return tipusDocumentalForm;
  }

  public void fillReferencesForForm(TipusDocumentalForm tipusDocumentalForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou TipusDocumental
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearTipusDocumentalPost(@ModelAttribute TipusDocumentalForm tipusDocumentalForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    TipusDocumentalJPA tipusDocumental = tipusDocumentalForm.getTipusDocumental();

    try {
      preValidate(request, tipusDocumentalForm, result);
      getWebValidator().validate(tipusDocumentalForm, result);
      postValidate(request,tipusDocumentalForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        tipusDocumental = create(request, tipusDocumental);
        createMessageSuccess(request, "success.creation", tipusDocumental.getTipusDocumentalID());
        tipusDocumentalForm.setTipusDocumental(tipusDocumental);
        return getRedirectWhenCreated(request, tipusDocumentalForm);
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

  @RequestMapping(value = "/view/{tipusDocumentalID}", method = RequestMethod.GET)
  public ModelAndView veureTipusDocumentalGet(@PathVariable("tipusDocumentalID") java.lang.Long tipusDocumentalID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTipusDocumentalGet(tipusDocumentalID,
        request, response, true);
  }


  protected ModelAndView editAndViewTipusDocumentalGet(@PathVariable("tipusDocumentalID") java.lang.Long tipusDocumentalID,
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
    TipusDocumentalJPA tipusDocumental = findByPrimaryKey(request, tipusDocumentalID);

    if (tipusDocumental == null) {
      createMessageWarning(request, "error.notfound", tipusDocumentalID);
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      TipusDocumentalForm tipusDocumentalForm = getTipusDocumentalForm(tipusDocumental, __isView, request, mav);
      tipusDocumentalForm.setView(__isView);
      if(__isView) {
        tipusDocumentalForm.setAllFieldsReadOnly(ALL_TIPUSDOCUMENTAL_FIELDS);
        tipusDocumentalForm.setSaveButtonVisible(false);
        tipusDocumentalForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(tipusDocumentalForm, request, mav);
      mav.addObject("tipusDocumentalForm", tipusDocumentalForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un TipusDocumental existent
   */
  @RequestMapping(value = "/{tipusDocumentalID}/edit", method = RequestMethod.GET)
  public ModelAndView editarTipusDocumentalGet(@PathVariable("tipusDocumentalID") java.lang.Long tipusDocumentalID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTipusDocumentalGet(tipusDocumentalID,
        request, response, false);
  }



  /**
   * Editar un TipusDocumental existent
   */
  @RequestMapping(value = "/{tipusDocumentalID}/edit", method = RequestMethod.POST)
  public String editarTipusDocumentalPost(@ModelAttribute TipusDocumentalForm tipusDocumentalForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    TipusDocumentalJPA tipusDocumental = tipusDocumentalForm.getTipusDocumental();

    try {
      preValidate(request, tipusDocumentalForm, result);
      getWebValidator().validate(tipusDocumentalForm, result);
      postValidate(request, tipusDocumentalForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        tipusDocumental = update(request, tipusDocumental);
        createMessageSuccess(request, "success.modification", tipusDocumental.getTipusDocumentalID());
        status.setComplete();
        return getRedirectWhenModified(request, tipusDocumentalForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          tipusDocumental.getTipusDocumentalID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, tipusDocumentalForm, __e);
    }

  }


  /**
   * Eliminar un TipusDocumental existent
   */
  @RequestMapping(value = "/{tipusDocumentalID}/delete")
  public String eliminarTipusDocumental(@PathVariable("tipusDocumentalID") java.lang.Long tipusDocumentalID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      TipusDocumental tipusDocumental = this.findByPrimaryKey(request, tipusDocumentalID);
      if (tipusDocumental == null) {
        String __msg = createMessageError(request, "error.notfound", tipusDocumentalID);
        return getRedirectWhenDelete(request, tipusDocumentalID, new Exception(__msg));
      } else {
        delete(request, tipusDocumental);
        createMessageSuccess(request, "success.deleted", tipusDocumentalID);
        return getRedirectWhenDelete(request, tipusDocumentalID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", tipusDocumentalID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, tipusDocumentalID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute TipusDocumentalFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarTipusDocumental(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __tipusDocumentalID, Throwable e) {
    java.lang.Long tipusDocumentalID = (java.lang.Long)__tipusDocumentalID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (tipusDocumentalID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(tipusDocumentalID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "tipusDocumental.tipusDocumental";
  }

  public String getEntityNameCodePlural() {
    return "tipusDocumental.tipusDocumental.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("tipusDocumental.tipusDocumentalID");
  }

  @InitBinder("tipusDocumentalFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("tipusDocumentalForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder);
  }

  public TipusDocumentalWebValidator getWebValidator() {
    return tipusDocumentalWebValidator;
  }


  public void setWebValidator(TipusDocumentalWebValidator __val) {
    if (__val != null) {
      this.tipusDocumentalWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de TipusDocumental
   */
  @RequestMapping(value = "/{tipusDocumentalID}/cancel")
  public String cancelTipusDocumental(@PathVariable("tipusDocumentalID") java.lang.Long tipusDocumentalID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, tipusDocumentalID);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de TipusDocumental
   */
  @RequestMapping(value = "/cancel")
  public String cancelTipusDocumental(HttpServletRequest request,HttpServletResponse response) {
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

  public void preValidate(HttpServletRequest request,TipusDocumentalForm tipusDocumentalForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,TipusDocumentalForm tipusDocumentalForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, TipusDocumentalFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, TipusDocumentalFilterForm filterForm,  List<TipusDocumental> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, TipusDocumentalForm tipusDocumentalForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, TipusDocumentalForm tipusDocumentalForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long tipusDocumentalID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long tipusDocumentalID) {
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
    return "tipusDocumentalFormWebDB";
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
        return "tipusDocumentalListWebDB";
    }

  public String getSessionAttributeFilterForm() {
    return "TipusDocumental_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public TipusDocumentalJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long tipusDocumentalID) throws I18NException {
    return (TipusDocumentalJPA) tipusDocumentalEjb.findByPrimaryKey(tipusDocumentalID);
  }


  public TipusDocumentalJPA create(HttpServletRequest request, TipusDocumentalJPA tipusDocumental)
    throws I18NException, I18NValidationException {
    return (TipusDocumentalJPA) tipusDocumentalEjb.create(tipusDocumental);
  }


  public TipusDocumentalJPA update(HttpServletRequest request, TipusDocumentalJPA tipusDocumental)
    throws I18NException, I18NValidationException {
    return (TipusDocumentalJPA) tipusDocumentalEjb.update(tipusDocumental);
  }


  public void delete(HttpServletRequest request, TipusDocumental tipusDocumental) throws I18NException {
    tipusDocumentalEjb.delete(tipusDocumental);
  }

} // Final de Classe

