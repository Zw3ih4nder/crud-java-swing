
package controller;

import dao.AlunoDao;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Aluno;


public class ControllerTelaAluno {
    private AlunoDao alunoDao = new AlunoDao();
    
    public void cadastrarAluno(String nome,String cpf, String email, String rg, Calendar dataNasc){
        Aluno alunoModel = new Aluno();
        
        if (nome == null || nome.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O nome é obrigatório.");
            return;
        }
        if (cpf == null || cpf.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O CPF é obrigatório.");
            return;
        }
        if (email == null || email.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O e-mail é obrigatório.");
            return;
        }
        
        alunoModel.setNomeAluno(nome);
        alunoModel.setCpf(cpf);
        alunoModel.setEmail(email);
        alunoModel.setRg(rg);
        // Converte o Calendar para LocalDate corretamente
        LocalDate data = LocalDate.of(
            dataNasc.get(Calendar.YEAR),
            dataNasc.get(Calendar.MONTH) + 1,
            dataNasc.get(Calendar.DAY_OF_MONTH)
        );
        alunoModel.setDataNasc(data);
        
        alunoDao.cadastrarAluno(alunoModel);
        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso");
    }
    
    //metodo para montar a tabela com os dados do aluno
    public DefaultTableModel carregarTabela(){
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("CPF");
        modelo.addColumn("RG");
        modelo.addColumn("Email");
        modelo.addColumn("Data Nascimento");
        
        List<Aluno> lista = alunoDao.buscarTudo();
        
        for(Aluno aluno : lista){
            modelo.addRow(new Object[]{
                aluno.getCodAluno(),
                aluno.getNomeAluno(),
                aluno.getCpf(),
                aluno.getRg(),
                aluno.getEmail(),
                aluno.getDataNasc()
            });
        }
        return modelo;
    }
}
