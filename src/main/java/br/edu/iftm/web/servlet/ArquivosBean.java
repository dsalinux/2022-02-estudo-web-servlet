package br.edu.iftm.web.servlet;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author danilo
 */
@Named
@SessionScoped
public class ArquivosBean implements Serializable{
    
    public String[] getArquivos() {
        File pasta = new File("/home/danilo/Documentos/aula_pw2/");
        return pasta.list();
    }
    
}
