package spring.sysmaqv.web.spring.sigaa.index.header;

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
 * @since 14.7.2009
 */
public class Header implements Controller {

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
        Personal personal = (Personal) WebUtils.getSessionAttribute(request, "___personal");
        Map retorno = new HashMap();
        retorno.put("personal", personal);
        return new ModelAndView(this.perfectView, retorno);
    }
}
