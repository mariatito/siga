/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.domain;

import java.util.List;
import java.io.File;
import java.util.Date;
import jxl.*;
import jxl.write.*;
import org.springframework.web.util.WebUtils;

/**
 * Created on : 16-jul-2010, 9:44:43
 *
 * @author MARCO ANTONIO QUENTA VELASCO
 */
public class Deposito {

    private String id_deposito;
    private int nro_transaccion;
    private String concepto;
    private int codigo;
    private String nombre_completo;
    private Date fec_deposito;
    private String anio;
    private double monto;
    private int factura;
    private int cuota;
    private int id_gestion;
    private Date fec_registro;
    private boolean asignado;
    private String desc_curso;

    //Detalle depositos
    private int id_inscripcion;
    //int id_gestion;//int codigo;//double monto,//String nombre_completo;//Date fec_deposito;//int factura;//int cuota;//boolean asignado;
    private int total_cuotas;
    private int id_pago_pension;
    private String mes;
    private Date fecha_ini;
    private Date fecha_fin;
    private int nro_cuota;
    private double monto_dep;
    private String id_estudiante;
    private String id_curso;
    private String id_persona;
    private String paterno;
    private String materno;
    private String nombres;
    //Cuotas hasta el 10 por defecto
    private double cuota1A;
    private double cuota2A;
    private double cuota3A;
    private double cuota4A;
    private double cuota5A;
    private double cuota6A;
    private double cuota7A;
    private double cuota8A;
    private double cuota9A;
    private double cuota10A;
    private double cuotaTotalA;
    private double cuota1D;
    private double cuota2D;
    private double cuota3D;
    private double cuota4D;
    private double cuota5D;
    private double cuota6D;
    private double cuota7D;
    private double cuota8D;
    private double cuota9D;
    private double cuota10D;
    private double cuotaTotalD;
    private String c1;
    private String c2;
    private String c3;
    private String c4;
    private String c5;
    private String c6;
    private String c7;
    private String c8;
    private String c9;
    private String c10;
    private String c11;
    private String c12;
    private String c13;
    private String c14;
    private String c15;
    private String c16;
    private String c17;
    private String c18;
    private String c19;
    private String c20;
    private String c21;
    private String c22;
    private String c23;
    private String c24;
    private String c25;
    private String c26;
    private String c27;
    private String c28;
    private String c29;
    private String c30;
    private String curso;

    public String getId_deposito() {
        return id_deposito;
    }

    public void setId_deposito(String id_deposito) {
        this.id_deposito = id_deposito;
    }

    public int getNro_transaccion() {
        return nro_transaccion;
    }

    public void setNro_transaccion(int nro_transaccion) {
        this.nro_transaccion = nro_transaccion;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public Date getFec_deposito() {
        return fec_deposito;
    }

    public void setFec_deposito(Date fec_deposito) {
        this.fec_deposito = fec_deposito;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getFactura() {
        return factura;
    }

    public void setFactura(int factura) {
        this.factura = factura;
    }

    public int getCuota() {
        return cuota;
    }

    public void setCuota(int cuota) {
        this.cuota = cuota;
    }

    public int getId_gestion() {
        return id_gestion;
    }

    public void setId_gestion(int id_gestion) {
        this.id_gestion = id_gestion;
    }

    public Date getFec_registro() {
        return fec_registro;
    }

    public void setFec_registro(Date fec_registro) {
        this.fec_registro = fec_registro;
    }

    public boolean isAsignado() {
        return asignado;
    }

    public void setAsignado(boolean asignado) {
        this.asignado = asignado;
    }

    public int getId_inscripcion() {
        return id_inscripcion;
    }

    public void setId_inscripcion(int id_inscripcion) {
        this.id_inscripcion = id_inscripcion;
    }

    public int getTotal_cuotas() {
        return total_cuotas;
    }

    public void setTotal_cuotas(int total_cuotas) {
        this.total_cuotas = total_cuotas;
    }

    public int getId_pago_pension() {
        return id_pago_pension;
    }

    public void setId_pago_pension(int id_pago_pension) {
        this.id_pago_pension = id_pago_pension;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Date getFecha_ini() {
        return fecha_ini;
    }

    public void setFecha_ini(Date fecha_ini) {
        this.fecha_ini = fecha_ini;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public int getNro_cuota() {
        return nro_cuota;
    }

    public void setNro_cuota(int nro_cuota) {
        this.nro_cuota = nro_cuota;
    }

    public double getMonto_dep() {
        return monto_dep;
    }

    public void setMonto_dep(double monto_dep) {
        this.monto_dep = monto_dep;
    }

    public String getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(String id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public String getId_curso() {
        return id_curso;
    }

    public void setId_curso(String id_curso) {
        this.id_curso = id_curso;
    }

    public String getId_persona() {
        return id_persona;
    }

    public void setId_persona(String id_persona) {
        this.id_persona = id_persona;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public double getCuota1A() {
        return cuota1A;
    }

    public void setCuota1A(double cuota1A) {
        this.cuota1A = cuota1A;
    }

    public double getCuota2A() {
        return cuota2A;
    }

    public void setCuota2A(double cuota2A) {
        this.cuota2A = cuota2A;
    }

    public double getCuota3A() {
        return cuota3A;
    }

    public void setCuota3A(double cuota3A) {
        this.cuota3A = cuota3A;
    }

    public double getCuota4A() {
        return cuota4A;
    }

    public void setCuota4A(double cuota4A) {
        this.cuota4A = cuota4A;
    }

    public double getCuota5A() {
        return cuota5A;
    }

    public void setCuota5A(double cuota5A) {
        this.cuota5A = cuota5A;
    }

    public double getCuota6A() {
        return cuota6A;
    }

    public void setCuota6A(double cuota6A) {
        this.cuota6A = cuota6A;
    }

    public double getCuota7A() {
        return cuota7A;
    }

    public void setCuota7A(double cuota7A) {
        this.cuota7A = cuota7A;
    }

    public double getCuota8A() {
        return cuota8A;
    }

    public void setCuota8A(double cuota8A) {
        this.cuota8A = cuota8A;
    }

    public double getCuota9A() {
        return cuota9A;
    }

    public void setCuota9A(double cuota9A) {
        this.cuota9A = cuota9A;
    }

    public double getCuota10A() {
        return cuota10A;
    }

    public void setCuota10A(double cuota10A) {
        this.cuota10A = cuota10A;
    }

    public double getCuotaTotalA() {
        return cuotaTotalA;
    }

    public void setCuotaTotalA(double cuotaTotalA) {
        this.cuotaTotalA = cuotaTotalA;
    }

    public double getCuota1D() {
        return cuota1D;
    }

    public void setCuota1D(double cuota1D) {
        this.cuota1D = cuota1D;
    }

    public double getCuota2D() {
        return cuota2D;
    }

    public void setCuota2D(double cuota2D) {
        this.cuota2D = cuota2D;
    }

    public double getCuota3D() {
        return cuota3D;
    }

    public void setCuota3D(double cuota3D) {
        this.cuota3D = cuota3D;
    }

    public double getCuota4D() {
        return cuota4D;
    }

    public void setCuota4D(double cuota4D) {
        this.cuota4D = cuota4D;
    }

    public double getCuota5D() {
        return cuota5D;
    }

    public void setCuota5D(double cuota5D) {
        this.cuota5D = cuota5D;
    }

    public double getCuota6D() {
        return cuota6D;
    }

    public void setCuota6D(double cuota6D) {
        this.cuota6D = cuota6D;
    }

    public double getCuota7D() {
        return cuota7D;
    }

    public void setCuota7D(double cuota7D) {
        this.cuota7D = cuota7D;
    }

    public double getCuota8D() {
        return cuota8D;
    }

    public void setCuota8D(double cuota8D) {
        this.cuota8D = cuota8D;
    }

    public double getCuota9D() {
        return cuota9D;
    }

    public void setCuota9D(double cuota9D) {
        this.cuota9D = cuota9D;
    }

    public double getCuota10D() {
        return cuota10D;
    }

    public void setCuota10D(double cuota10D) {
        this.cuota10D = cuota10D;
    }

    public double getCuotaTotalD() {
        return cuotaTotalD;
    }

    public void setCuotaTotalD(double cuotaTotalD) {
        this.cuotaTotalD = cuotaTotalD;
    }

    public String getC1() {
        return c1;
    }

    public void setC1(String c1) {
        this.c1 = c1;
    }

    public String getC2() {
        return c2;
    }

    public void setC2(String c2) {
        this.c2 = c2;
    }

    public String getC3() {
        return c3;
    }

    public void setC3(String c3) {
        this.c3 = c3;
    }

    public String getC4() {
        return c4;
    }

    public void setC4(String c4) {
        this.c4 = c4;
    }

    public String getC5() {
        return c5;
    }

    public void setC5(String c5) {
        this.c5 = c5;
    }

    public String getC6() {
        return c6;
    }

    public void setC6(String c6) {
        this.c6 = c6;
    }

    public String getC7() {
        return c7;
    }

    public void setC7(String c7) {
        this.c7 = c7;
    }

    public String getC8() {
        return c8;
    }

    public void setC8(String c8) {
        this.c8 = c8;
    }

    public String getC9() {
        return c9;
    }

    public void setC9(String c9) {
        this.c9 = c9;
    }

    public String getC10() {
        return c10;
    }

    public void setC10(String c10) {
        this.c10 = c10;
    }

    public String getC11() {
        return c11;
    }

    public void setC11(String c11) {
        this.c11 = c11;
    }

    public String getC12() {
        return c12;
    }

    public void setC12(String c12) {
        this.c12 = c12;
    }

    public String getC13() {
        return c13;
    }

    public void setC13(String c13) {
        this.c13 = c13;
    }

    public String getC14() {
        return c14;
    }

    public void setC14(String c14) {
        this.c14 = c14;
    }

    public String getC15() {
        return c15;
    }

    public void setC15(String c15) {
        this.c15 = c15;
    }

    public String getC16() {
        return c16;
    }

    public void setC16(String c16) {
        this.c16 = c16;
    }

    public String getC17() {
        return c17;
    }

    public void setC17(String c17) {
        this.c17 = c17;
    }

    public String getC18() {
        return c18;
    }

    public void setC18(String c18) {
        this.c18 = c18;
    }

    public String getC19() {
        return c19;
    }

    public void setC19(String c19) {
        this.c19 = c19;
    }

    public String getC20() {
        return c20;
    }

    public void setC20(String c20) {
        this.c20 = c20;
    }

    public String getC21() {
        return c21;
    }

    public void setC21(String c21) {
        this.c21 = c21;
    }

    public String getC22() {
        return c22;
    }

    public void setC22(String c22) {
        this.c22 = c22;
    }

    public String getC23() {
        return c23;
    }

    public void setC23(String c23) {
        this.c23 = c23;
    }

    public String getC24() {
        return c24;
    }

    public void setC24(String c24) {
        this.c24 = c24;
    }

    public String getC25() {
        return c25;
    }

    public void setC25(String c25) {
        this.c25 = c25;
    }

    public String getC26() {
        return c26;
    }

    public void setC26(String c26) {
        this.c26 = c26;
    }

    public String getC27() {
        return c27;
    }

    public void setC27(String c27) {
        this.c27 = c27;
    }

    public String getC28() {
        return c28;
    }

    public void setC28(String c28) {
        this.c28 = c28;
    }

    public String getC29() {
        return c29;
    }

    public void setC29(String c29) {
        this.c29 = c29;
    }

    public String getC30() {
        return c30;
    }

    public void setC30(String c30) {
        this.c30 = c30;
    }

    public void LLenarCursosSQL(String cursosSQL) {
        String[] cursos = cursosSQL.split(",");
        for (int i = 0; i < cursos.length; i++) {
            switch (i + 1) {
                case 1:
                    this.c1 = cursos[i];
                    break;
                case 2:
                    this.c2 = cursos[i];
                    break;
                case 3:
                    this.c3 = cursos[i];
                    break;
                case 4:
                    this.c4 = cursos[i];
                    break;
                case 5:
                    this.c5 = cursos[i];
                    break;
                case 6:
                    this.c6 = cursos[i];
                    break;
                case 7:
                    this.c7 = cursos[i];
                    break;
                case 8:
                    this.c8 = cursos[i];
                    break;
                case 9:
                    this.c9 = cursos[i];
                    break;
                case 10:
                    this.c10 = cursos[i];
                    break;
                case 11:
                    this.c11 = cursos[i];
                    break;
                case 12:
                    this.c12 = cursos[i];
                    break;
                case 13:
                    this.c13 = cursos[i];
                    break;
                case 14:
                    this.c14 = cursos[i];
                    break;
                case 15:
                    this.c15 = cursos[i];
                    break;
                case 16:
                    this.c16 = cursos[i];
                    break;
                case 17:
                    this.c17 = cursos[i];
                    break;
                case 18:
                    this.c18 = cursos[i];
                    break;
                case 19:
                    this.c19 = cursos[i];
                    break;
                case 20:
                    this.c20 = cursos[i];
                    break;
                case 21:
                    this.c21 = cursos[i];
                    break;
                case 22:
                    this.c22 = cursos[i];
                    break;
                case 23:
                    this.c23 = cursos[i];
                    break;
                case 24:
                    this.c24 = cursos[i];
                    break;
                case 25:
                    this.c25 = cursos[i];
                    break;
                case 26:
                    this.c26 = cursos[i];
                    break;
                case 27:
                    this.c27 = cursos[i];
                    break;
                case 28:
                    this.c28 = cursos[i];
                    break;
                case 29:
                    this.c29 = cursos[i];
                    break;
                case 30:
                    this.c30 = cursos[i];
                    break;
                default:
                    this.c1 = "";
                    break;
            }
        }
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public static String GenerarReporteExcel(List depositoAux,Personal personal, int gestion) {
        String archivo = "Depositos" + StringTools.FormatearFecha(new Date(), StringTools.FormatoFechas.yyyyMMddhhmmss) + ".xls";
        try {
            String dirDestino = System.getenv("SIGAA_HOME") + "/documentos/depositos/repExcel/" + archivo;
            WritableWorkbook workbook = Workbook.createWorkbook(new File(dirDestino));
            WritableSheet sheet = workbook.createSheet("Hoja1ST", 0);
            WritableFont arial_8 = new WritableFont(WritableFont.ARIAL, 8);
            WritableFont arial_10 = new WritableFont(WritableFont.ARIAL, 10);
            arial_10.setBoldStyle(WritableFont.BOLD);
            WritableFont arial_12 = new WritableFont(WritableFont.ARIAL, 12);
            arial_12.setBoldStyle(WritableFont.BOLD);
            WritableFont arial_11 = new WritableFont(WritableFont.ARIAL, 11);
            WritableFont arial_7 = new WritableFont(WritableFont.ARIAL, 7);

            WritableCellFormat arial8 = new WritableCellFormat(arial_8);
            arial8.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.GRAY_50);

            WritableCellFormat arial8orange = new WritableCellFormat(arial_8);
            arial8orange.setBackground(jxl.format.Colour.LIGHT_ORANGE);
            arial8orange.setBorder(jxl.format.Border.BOTTOM, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.GRAY_50);

            WritableCellFormat arial10header = new WritableCellFormat(arial_10);
            arial10header.setBackground(jxl.format.Colour.GRAY_25);
            arial10header.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.GRAY_50);
            arial10header.setAlignment(jxl.format.Alignment.CENTRE);
            arial10header.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);

            WritableCellFormat arial12header = new WritableCellFormat(arial_12);
            arial12header.setAlignment(jxl.format.Alignment.CENTRE);
            arial12header.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);

            WritableCellFormat arial12ins = new WritableCellFormat(arial_12);
            WritableCellFormat arial11ins = new WritableCellFormat(arial_11);
            WritableCellFormat arial7 = new WritableCellFormat(arial_7);

            sheet.setColumnView(0, 5);
            sheet.setColumnView(1, 7);
            sheet.setColumnView(2, 40);
            sheet.setColumnView(3, 14);
            sheet.setColumnView(4, 6);
            sheet.setColumnView(5, 6);
            sheet.setColumnView(6, 6);
            sheet.setColumnView(7, 6);
            sheet.setColumnView(8, 6);
            sheet.setColumnView(9, 6);
            sheet.setColumnView(10, 6);
            sheet.setColumnView(11, 6);
            sheet.setColumnView(12, 6);
            sheet.setColumnView(13, 6);

            int fila = 0;

            sheet.mergeCells(0, fila, 10, fila);
            sheet.addCell(new Label(0, fila, "Colegio Santa Teresa", arial12ins));
            fila++;
            sheet.mergeCells(0, fila, 10, fila);
            sheet.addCell(new Label(0, fila, "Getión Académica " + gestion, arial11ins));
            fila++;
            sheet.mergeCells(0, fila, 10, fila);
            sheet.addCell(new Label(0, fila, "La Paz - Boivia", arial11ins));
            fila += 2;
            sheet.addImage(new WritableImage(11, 0, 3, 2, new File(System.getenv("SIGAA_HOME") + "/imagenes/iconos_sigaa/sigaa_oficial.png")));

            sheet.mergeCells(0, fila, 13, fila);
            sheet.addCell(new Label(0, fila, "Estado Pago de Pensiones", arial12header));
            fila += 2;
            sheet.mergeCells(0, fila, 0, fila + 1);
            sheet.addCell(new Label(0, fila, "Nro.", arial10header));
            sheet.mergeCells(1, fila, 1, fila + 1);
            sheet.addCell(new Label(1, fila, "Código", arial10header));
            sheet.mergeCells(2, fila, 2, fila + 1);
            sheet.addCell(new Label(2, fila, "Apelidos y Nombres", arial10header));
            sheet.mergeCells(3, fila, 3, fila + 1);
            sheet.addCell(new Label(3, fila, "Curso", arial10header));
            sheet.mergeCells(4, fila, 13, fila);
            sheet.addCell(new Label(4, fila, "Cuotas", arial10header));
            sheet.addCell(new Label(4, fila + 1, "1-feb", arial10header));
            sheet.addCell(new Label(5, fila + 1, "2-mar", arial10header));
            sheet.addCell(new Label(6, fila + 1, "3-abr", arial10header));
            sheet.addCell(new Label(7, fila + 1, "4-may", arial10header));
            sheet.addCell(new Label(8, fila + 1, "5-jun", arial10header));
            sheet.addCell(new Label(9, fila + 1, "6-jul", arial10header));
            sheet.addCell(new Label(10, fila + 1, "7-ago", arial10header));
            sheet.addCell(new Label(11, fila + 1, "8-sep", arial10header));
            sheet.addCell(new Label(12, fila + 1, "9-oct", arial10header));
            sheet.addCell(new Label(13, fila + 1, "10-nov", arial10header));
            fila += 2;
            for (int i = 0; i < depositoAux.size(); i++) {
                Deposito dep = (Deposito) depositoAux.get(i);
                sheet.addCell(new jxl.write.Number(0, fila, i + 1, arial8));
                sheet.addCell(new jxl.write.Number(1, fila, dep.getCodigo(), arial8));
                sheet.addCell(new Label(2, fila, dep.getPaterno() + " " + dep.getMaterno() + " " + dep.getNombres(), arial8));
                sheet.addCell(new Label(3, fila, dep.getDesc_curso(), arial8));
                sheet.addCell(new jxl.write.Number(4, fila, dep.getCuota1D(), (dep.getCuota1D() > 0) ? arial8 : arial8orange));
                sheet.addCell(new jxl.write.Number(5, fila, dep.getCuota2D(), (dep.getCuota2D() > 0) ? arial8 : arial8orange));
                sheet.addCell(new jxl.write.Number(6, fila, dep.getCuota3D(), (dep.getCuota3D() > 0) ? arial8 : arial8orange));
                sheet.addCell(new jxl.write.Number(7, fila, dep.getCuota4D(), (dep.getCuota4D() > 0) ? arial8 : arial8orange));
                sheet.addCell(new jxl.write.Number(8, fila, dep.getCuota5D(), (dep.getCuota5D() > 0) ? arial8 : arial8orange));
                sheet.addCell(new jxl.write.Number(9, fila, dep.getCuota6D(), (dep.getCuota6D() > 0) ? arial8 : arial8orange));
                sheet.addCell(new jxl.write.Number(10, fila, dep.getCuota7D(), (dep.getCuota7D() > 0) ? arial8 : arial8orange));
                sheet.addCell(new jxl.write.Number(11, fila, dep.getCuota8D(), (dep.getCuota8D() > 0) ? arial8 : arial8orange));
                sheet.addCell(new jxl.write.Number(12, fila, dep.getCuota9D(), (dep.getCuota9D() > 0) ? arial8 : arial8orange));
                sheet.addCell(new jxl.write.Number(13, fila, dep.getCuota10D(), (dep.getCuota10D() > 0) ? arial8 : arial8orange));
                fila++;
            }
            sheet.addCell(new Label(0, fila, personal.getId_persona()+"-"+StringTools.FormatearFecha(new Date(), StringTools.FormatoFechas.dd_MM_yyyy_hhmmss), arial7));
            workbook.write();
            workbook.close();
        } catch (Exception de) {
            de.printStackTrace();
        }
        return archivo;
    }

    public String getDesc_curso() {
        return desc_curso;
    }

    public void setDesc_curso(String desc_curso) {
        this.desc_curso = desc_curso;
    }
}
