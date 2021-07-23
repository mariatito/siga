package spring.sysmaqv.web.spring.sigaa.seguridad;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import spring.sysmaqv.domain.Personal;

import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;

/**
 * @author Marco Antonio Quenta Vrlasco
 * @since 17.7.2009
 */
public class ActualizarPassword implements Controller {

    private SigaaInterface sigaa;
    private String ecxessTimeView;
    private String perfectView;

    public void setSigaa(SigaaInterface sigaa) {
        this.sigaa = sigaa;
    }

    public void setEcxessTimeView(String ecxessTimeView) {
        this.ecxessTimeView = ecxessTimeView;
    }

    public void setPerfectView(String perfectView) {
        this.perfectView = perfectView;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String url = request.getServletPath();
        Personal personal = (Personal) WebUtils.getSessionAttribute(request, "___personal");
        if (personal != null) {
            Map retorno = new HashMap();
            String old_password = request.getParameter("old_password");
            String new_password1 = request.getParameter("new_password1");
            String new_password2 = request.getParameter("new_password2");
            if (old_password != null) {
                try {
                    boolean condition = true;
                    if (!old_password.equals(personal.getClave())) {
                        condition = false;
                    }
                    if (condition) {
                        if (new_password1.length() < 4) {
                            condition = false;
                        }
                        if (condition) {
                            if (!new_password1.equals(new_password2)) {
                                condition = false;
                            }
                        }
                    }
                    if (condition) {
                        Personal pers = new Personal();
                        pers.setId_personal(personal.getId_personal());
                        pers.setClave(new_password1);
                        this.sigaa.setCambiarPassword(pers);
                        retorno.put("mensaje", "true");
                    } else {
                        retorno.put("mensaje", "false");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    retorno.put("mensaje", "false");
                }
            }
            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }
}
