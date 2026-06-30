
package controller;

import view.TelaAluno;
import view.TelaUsuario;


public class ControllerTelaInicial {

    public void abrirTelaAluno(){
        new TelaAluno().setVisible(true);
    }
    
    public void abrirTelaUsuario(){
        new TelaUsuario().setVisible(true);
    }
}
