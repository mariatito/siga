/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package spring.sysmaqv.domain;

/**
 *
 * @author MARCO
 */
public class Personal extends Persona {

    private String id_personal;
    private String mail;
    private String clave;
    private boolean activo;
    private String mensaje;
    private String id_tpersonal;
    private String tpersonal;
    private String slogin;
    private String sclave;
    private String nickname;
    private Menu menu = new Menu();

    public String getId_personal() {
        return id_personal;
    }

    public void setId_personal(String id_personal) {
        this.id_personal = id_personal;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getId_tpersonal() {
        return id_tpersonal;
    }

    public void setId_tpersonal(String id_tpersonal) {
        this.id_tpersonal = id_tpersonal;
    }

    public String getTpersonal() {
        return tpersonal;
    }

    public void setTpersonal(String tpersonal) {
        this.tpersonal = tpersonal;
    }

    public String getSlogin() {
        return slogin;
    }

    public void setSlogin(String slogin) {
        this.slogin = slogin;
    }

    public String getSclave() {
        return sclave;
    }

    public void setSclave(String sclave) {
        this.sclave = sclave;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
