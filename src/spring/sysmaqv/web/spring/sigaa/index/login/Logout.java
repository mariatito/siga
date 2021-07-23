package spring.sysmaqv.web.spring.sigaa.index.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * @author Marco Antonio Quenta Vrlasco
 * @since 14.7.2009
 */
public class Logout implements Controller {

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.getSession().invalidate();
		return new ModelAndView("index/login/End");
                
	}

}
