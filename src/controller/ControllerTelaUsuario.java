
package controller;

import dao.UsuarioDao;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Usuario;


public class ControllerTelaUsuario {
    private final UsuarioDao usuarioDao = new UsuarioDao();
    
    public void cadastrarUsuario(String nome, String senha, String nivel, Calendar dataCadastro){
        Usuario usuarioModel = new Usuario();
        
        if(nome == null || nome.trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "O Nome é obrigatório.");
            return;
        }
        
        if(senha == null || senha.trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "a Senha é obrigatório.");
            return;
        }
        
        if("-".equals(nivel)){
            JOptionPane.showMessageDialog(null, "Selecione o nivel antes de enviar");
            return;
        }
        
        usuarioModel.setNomeUsuario(nome);
        usuarioModel.setSenha(senha);
        usuarioModel.setNivel(nivel);
        LocalDate data = LocalDate.of(
            dataCadastro.get(Calendar.YEAR),
            dataCadastro.get(Calendar.MONTH) + 1,
            dataCadastro.get(Calendar.DAY_OF_MONTH)
        );
        usuarioModel.setDataCadastro(data);
        
        usuarioDao.cadastroUsuario(usuarioModel);
        JOptionPane.showMessageDialog(null,"Cadastro realizado com sucesso");
    }
    
    public DefaultTableModel carregarTabela(){
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("Nivel");
        modelo.addColumn("Data Cadastro");
        
        List<Usuario> lista = usuarioDao.buscarTudo();
        
        for(Usuario usuario : lista){
            modelo.addRow(new Object[]{
                usuario.getCodUsuario(),
                usuario.getNomeUsuario(),
                usuario.getNivel(),
                usuario.getDataCadastro()
            
            });
        }
        return modelo;
    }
}
