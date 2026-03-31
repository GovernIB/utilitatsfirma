package es.caib.utilitatsfirma.back.controller.admin;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileAttribute;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import es.caib.utilitatsfirma.back.utils.Tab;
import es.caib.utilitatsfirma.commons.utils.Constants;
import es.caib.utilitatsfirma.model.fields.EstadisticaFields;

/**
 * 
 * @author anadal (u80067)
 * 30 mar 2026 11:57:30
 */
@MenuOption(
        labelCode = "estadistica.estadistica.plural",
        order = 110,
        group = Tab.MENU_ADMIN,
        addSeparatorBefore = true,
        baseLink = "/admin/estadistica/",
        relativeLink = "/")
@Controller
@RequestMapping(value = "/admin/estadistica")
@Tile(
        name = "estadisticaAdmin",
        extendsTile = Tab.MENU_ADMIN,
        // Els següents atributs no són necessaris si heredes aquesta classe
        contentJsp = "/WEB-INF/jsp/admin/estadistica.jsp",
        type = TileType.WEBDB_FORM,
        attributes = { @TileAttribute(name = "titol", value = "estadistica.estadistica") })
public class EstadisticaAdminController {

    protected static final String[] MESOS = new String[] { "Gener", "Febrer", "Març", "Abril", "Maig", "Juny", "Juliol",
            "Agost", "Setembre", "Octubre", "Novembre", "Desembre" };

    @EJB(mappedName = es.caib.utilitatsfirma.ejb.EstadisticaService.JNDI_NAME)
    protected es.caib.utilitatsfirma.ejb.EstadisticaService estadisticaEjb;

    public enum EstadistiquesRang {
        ANY(1), MES(2), DIA(3);

        private final int code;

        EstadistiquesRang(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public static EstadistiquesRang fromCode(Integer code) {
            if (code == null) {
                return DIA;
            }
            for (EstadistiquesRang value : values()) {
                if (value.code == code.intValue()) {
                    return value;
                }
            }
            return DIA;
        }
    }

    public enum EstadistiquesAccio {
        FIRMA(1), UPGRADE(2), VALIDACIO(3);

        private final int code;

        EstadistiquesAccio(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public static EstadistiquesAccio fromCode(Integer code) {
            if (code == null) {
                return FIRMA;
            }
            for (EstadistiquesAccio value : values()) {
                if (value.code == code.intValue()) {
                    return value;
                }
            }
            return FIRMA;
        }
    }

    @RequestMapping(value = "/")
    @GET
    @POST
    public ModelAndView index(@RequestParam(value = "action", required = false)
    Integer actionCode, @RequestParam(value = "rang", required = false)
    Integer rangCode, @RequestParam(value = "date", required = false)
    String dateParam) throws Exception {

        ModelAndView mav = new ModelAndView("estadisticaAdmin");

        EstadistiquesRang estadisticaRang = EstadistiquesRang.fromCode(rangCode);
        LocalDate selectedDate = (dateParam == null || dateParam.isBlank()) ? LocalDate.now()
                : LocalDate.parse(dateParam);
        int any = selectedDate.getYear();
        int mes = selectedDate.getMonthValue();
        int dia = selectedDate.getDayOfMonth();
        EstadistiquesAccio action = EstadistiquesAccio.fromCode(actionCode); //  FIRMA=1; UPGRADE=2; VALIDACIO=3;

        String actionLabel;

        mav.addObject("titleOK", "Correctes");
        mav.addObject("titleError", "Errònies");

        switch (action) {
            case FIRMA:
                actionLabel = "Firmes en servidor";
            break;
            case UPGRADE:
                actionLabel = "Upgrade de firmes";
            break;
            case VALIDACIO:
                actionLabel = "Validació de firmes";
                mav.addObject("titleOK", "Valides i invàlides");
            break;
            default:
                throw new IllegalArgumentException("Acció no suportada: " + action);
        }
        ;
        mav.addObject("actionLabel", actionLabel);
        mav.addObject("selectedAction", action.getCode());
        mav.addObject("selectedRange", estadisticaRang.getCode());
        mav.addObject("selectedDate", selectedDate.toString());

        final Map<EstadistiquesAccio, Integer[]> tipusOkByAction = new HashMap<>();

        tipusOkByAction.put(EstadistiquesAccio.FIRMA, new Integer[] { Constants.ESTADISTICA_TIPUS_FIRMA_SERVIDOR_OK });
        tipusOkByAction.put(EstadistiquesAccio.VALIDACIO, new Integer[] { Constants.ESTADISTICA_TIPUS_UPGRADE_OK });
        tipusOkByAction.put(EstadistiquesAccio.VALIDACIO, new Integer[] {
                Constants.ESTADISTICA_TIPUS_VALIDACIO_OK_VALIDA, Constants.ESTADISTICA_TIPUS_VALIDACIO_OK_INVALIDA });

        final Map<EstadistiquesAccio, Integer[]> tipusErrorByAction = new HashMap<>();
        tipusErrorByAction.put(EstadistiquesAccio.FIRMA,
                new Integer[] { Constants.ESTADISTICA_TIPUS_FIRMA_SERVIDOR_ERROR });
        tipusErrorByAction.put(EstadistiquesAccio.UPGRADE, new Integer[] { Constants.ESTADISTICA_TIPUS_UPGRADE_ERROR });
        tipusErrorByAction.put(EstadistiquesAccio.VALIDACIO,
                new Integer[] { Constants.ESTADISTICA_TIPUS_VALIDACIO_ERROR, });

        final String[] labels;
        final String[] valuesOK;
        final String[] valuesError;

        switch (estadisticaRang) {
            case ANY: {
                mav.addObject("rang", " per l´any " + any);

                labels = MESOS;
                valuesOK = new String[labels.length];
                valuesError = new String[labels.length];
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, any);

                for (int i = 0; i < 12; i++) {

                    calendar.set(Calendar.MONTH, i);

                    calendar.set(Calendar.DAY_OF_MONTH, 1);

                    calendar.set(Calendar.HOUR_OF_DAY, 0);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);

                    Timestamp from = new Timestamp(calendar.getTimeInMillis());

                    // Posar el darrer dia del mes actual. Per exemple per febre són 28 dies, per abril són 30 dies, etc.

                    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

                    calendar.set(Calendar.HOUR_OF_DAY, 23);
                    calendar.set(Calendar.MINUTE, 59);
                    calendar.set(Calendar.SECOND, 59);

                    Timestamp to = new Timestamp(calendar.getTimeInMillis());

                    Where wData = EstadisticaFields.DATA.between(from, to);
                    Where wTipus = EstadisticaFields.TIPUS.in(tipusOkByAction.get(action));

                    valuesOK[i] = "" + estadisticaEjb.sumDecimal(EstadisticaFields.VALOR, Where.AND(wData, wTipus));

                    wTipus = EstadisticaFields.TIPUS.in(tipusErrorByAction.get(action));
                    valuesError[i] = "" + estadisticaEjb.sumDecimal(EstadisticaFields.VALOR, Where.AND(wData, wTipus));
                }
            }
            break;
            case MES: {

                mav.addObject("rang", " pel mes de " + MESOS[mes - 1] + " de " + any);

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, any);
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                calendar.set(Calendar.MONTH, mes - 1);

                int darrerDiaDeMes = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

                labels = new String[darrerDiaDeMes];
                valuesOK = new String[labels.length];
                valuesError = new String[labels.length];

                for (int i = 0; i < darrerDiaDeMes; i++) {

                    labels[i] = "" + (i + 1);

                    calendar.set(Calendar.DAY_OF_MONTH, i + 1);

                    calendar.set(Calendar.HOUR_OF_DAY, 0);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);

                    Timestamp from = new Timestamp(calendar.getTimeInMillis());

                    // Posar el darrer dia del mes actual. Per exemple per febre són 28 dies, per abril són 30 dies, etc.

                    calendar.set(Calendar.HOUR_OF_DAY, 23);
                    calendar.set(Calendar.MINUTE, 59);
                    calendar.set(Calendar.SECOND, 59);

                    Timestamp to = new Timestamp(calendar.getTimeInMillis());

                    Where wData = EstadisticaFields.DATA.between(from, to);
                    Where wTipus = EstadisticaFields.TIPUS.in(tipusOkByAction.get(action));

                    Double sumOK = estadisticaEjb.sumDecimal(EstadisticaFields.VALOR, Where.AND(wData, wTipus));
                    valuesOK[i] = sumOK == null ? "0" : ("" + sumOK.intValue());

                    wTipus = EstadisticaFields.TIPUS.in(tipusErrorByAction.get(action));
                    Double sumError = estadisticaEjb.sumDecimal(EstadisticaFields.VALOR, Where.AND(wData, wTipus));
                    valuesError[i] = sumError == null ? "0" : ("" + sumError.intValue());
                }
            }

            break;

            case DIA:

                labels = new String[] { "00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00",
                        "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00",
                        "19:00", "20:00", "21:00", "22:00", "23:00" };
                valuesOK = new String[labels.length];
                valuesError = new String[labels.length];

            {

                mav.addObject("rang", " pel dia " + dia + " de " + MESOS[mes - 1] + " de " + any);

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, any);
                calendar.set(Calendar.DAY_OF_MONTH, dia);
                calendar.set(Calendar.MONTH, mes - 1);

                for (int i = 0; i < 24; i++) {

                    calendar.set(Calendar.HOUR_OF_DAY, i);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);

                    Timestamp from = new Timestamp(calendar.getTimeInMillis());

                    // Posar el darrer dia del mes actual. Per exemple per febre són 28 dies, per abril són 30 dies, etc.

                    calendar.set(Calendar.HOUR_OF_DAY, i);
                    calendar.set(Calendar.MINUTE, 59);
                    calendar.set(Calendar.SECOND, 59);

                    Timestamp to = new Timestamp(calendar.getTimeInMillis());

                    Where wData = EstadisticaFields.DATA.between(from, to);
                    Where wTipus = EstadisticaFields.TIPUS.in(tipusOkByAction.get(action));

                    Double sumOK = estadisticaEjb.sumDecimal(EstadisticaFields.VALOR, Where.AND(wData, wTipus));
                    valuesOK[i] = sumOK == null ? "0" : ("" + sumOK.intValue());

                    wTipus = EstadisticaFields.TIPUS.in(tipusErrorByAction.get(action));
                    Double sumError = estadisticaEjb.sumDecimal(EstadisticaFields.VALOR, Where.AND(wData, wTipus));
                    valuesError[i] = sumError == null ? "0" : ("" + sumError.intValue());
                }
            }

            break;

            default:
                throw new IllegalArgumentException("Rang no suportat: " + estadisticaRang);
        }

        mav.addObject("labels", labels);
        mav.addObject("valuesOK", valuesOK);
        mav.addObject("valuesError", valuesError);

        return mav;
    }

}