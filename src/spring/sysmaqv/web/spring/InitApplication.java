package spring.sysmaqv.web.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import spring.sysmaqv.domain.Personal;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;

/**
 * @author Marco Antonio Quenta Vrlasco
 * @since 14.7.2009
 */
public class InitApplication implements Controller {

    private String perfectView;
    private String ecxessTimeView;

    public void setPerfectView(String perfectView) {
        this.perfectView = perfectView;
    }

    public void setEcxessTimeView(String ecxessTimeView) {
        this.ecxessTimeView = ecxessTimeView;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Personal personal = (Personal) WebUtils.getSessionAttribute(request, "___personal");
        if (personal != null) {
            return new ModelAndView(this.perfectView, "personal", personal);
        } else {
            return new ModelAndView(this.ecxessTimeView, null);
        }
    }
}
