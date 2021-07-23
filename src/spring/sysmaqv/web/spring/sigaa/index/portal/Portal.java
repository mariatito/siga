package spring.sysmaqv.web.spring.sigaa.index.portal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;
import spring.sysmaqv.domain.Persona;
import spring.sysmaqv.domain.Personal;

/**
 * @author Marco Antonio Quenta Vrlasco
 * @since 17.7.2009
 */
public class Portal implements Controller {

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
       
        Map retorno = new HashMap();

      
 
        List documentos = this.sigaa.getListarTodosDocumentosByArea();
        retorno.put("documentos", documentos);
        List distintos = this.sigaa.getListarDistintosDocumentosByArea();
        retorno.put("distintos", distintos);

        return new ModelAndView(this.perfectView, retorno);

    }
}

