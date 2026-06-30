
package controller;

import dao.AlunoDao;
import java.time.LocalDate;
import java.util.Calendar;
import model.Aluno;


public class ControllerTelaAluno {
    private AlunoDao alunoDao = new AlunoDao();
    
    public void cadastrarAluno(String nome,String cpf, String email, String rg, Calendar dataNasc){
        Aluno alunoModel = new Aluno();
        
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
    }
}
