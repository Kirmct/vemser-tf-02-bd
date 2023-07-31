package service;

import model.Funcionario;

import model.exceptions.BancoDeDadosException;
import repository.FuncionarioRepository;
import util.CoresMenu;

import java.util.List;


public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository = new FuncionarioRepository();

    public void inserir(Funcionario funcionario) {
        try {
            String cpf = funcionario.getCpf().replaceAll("[^0-9]", "");
            if (cpf.length() != 11) {
                throw new Exception("CPF Invalido!");
            }
            funcionario.setCpf(cpf);

            String cep = funcionario.getCep().replaceAll("[^0-9]", "");
            if (cep.length() != 8) {
                throw new Exception("CEP inválido! Deve conter exatamente 8 dígitos numéricos.");
            }
            funcionario.setCep(cep);
            funcionarioRepository.cadastrar(funcionario);
            System.out.println(CoresMenu.VERDE_BOLD + "\nOperação realizada com sucesso!" + CoresMenu.RESET);

        }catch (BancoDeDadosException e){
            e.printStackTrace();
        }catch (Exception e){
            System.out.println("Unnexpected error: " +  e.getMessage());
        }
    }
    public boolean buscarCpf(Funcionario funcionario){
        return funcionarioRepository.buscarCpf(funcionario);
    }

    public Funcionario buscarId(Integer id) throws BancoDeDadosException {
        return funcionarioRepository.buscarId(id);
    }

    public void listarTodos() {
        try {
            List<Funcionario> list = funcionarioRepository.listarTodos();
            list.forEach(System.out::println);
        }catch (BancoDeDadosException e){
            e.printStackTrace();
        }
    }
    //
    public void listarPeloId(Integer id) throws BancoDeDadosException{
        Funcionario funcionario = funcionarioRepository.listarPeloId(id);
        System.out.println(funcionario);
    }

    public void alterarPeloId(Integer id, Funcionario funcionarioAtualizado) throws BancoDeDadosException{
        try {
            boolean consegueEditar = funcionarioRepository.alterarPeloId(id, funcionarioAtualizado);
            if (consegueEditar){
                System.out.println(CoresMenu.VERDE_BOLD + "\nOperação realizada com sucesso!" + CoresMenu.RESET);
            }
        }catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void deletarPeloId(Integer id){
        try {
            boolean removeu =  funcionarioRepository.deletarPeloId(id);
            if (removeu){
                System.out.println(CoresMenu.VERDE_BOLD + "\nOperação realizada com sucesso!" + CoresMenu.RESET);
            }

        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
}