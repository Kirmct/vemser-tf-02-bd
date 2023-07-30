package service;

import model.Hospital;
import model.Medico;
import model.Paciente;
import model.exceptions.BancoDeDadosException;
import repository.MedicoRepository;
import util.CoresMenu;

import java.util.List;

public class MedicoService{

    private final MedicoRepository medicoRepository = new MedicoRepository();
    public boolean buscarCpf(Medico medico){
        return medicoRepository.buscarCpf(medico);
    }

    public void inserir(Medico medico) {
        try {
            String cpf = medico.getCpf().replaceAll("[^0-9]", ""); // Remove caracteres não numéricos do CPF
            if (cpf.length() != 11) {
                throw new Exception("CPF Invalido!");
            }
            medico.setCpf(cpf);

            String cep = medico.getCep().replaceAll("[^0-9]", ""); // Remove caracteres não numéricos do CEP
            if (cep.length() != 8) {
                throw new Exception("CEP inválido! Deve conter exatamente 8 dígitos numéricos.");
            }
            medico.setCep(cep); // Atualiza o CEP no objeto Paciente com o valor formatado
            medicoRepository.cadastrar(medico);
            System.out.println(CoresMenu.VERDE_BOLD + "\nOperação realizada com sucesso!" + CoresMenu.RESET);

        }catch (BancoDeDadosException e){
            e.printStackTrace();
        }catch (Exception e){
            System.out.println("Unnexpected error: " +  e.getMessage());
        }
    }
    public void listarTodos() {
        try {
            List<Medico> list = medicoRepository.listarTodos();
            list.forEach(System.out::println);
        }catch (BancoDeDadosException e){
            e.printStackTrace();
        }
    }
    public void listarPeloId(Integer id) throws BancoDeDadosException {
        Medico medico = medicoRepository.listarPeloId(id);
        System.out.println(medico);
    }

    public void alterarPeloId(Integer id, Medico medicoAtualizado) throws BancoDeDadosException {
        try {
            boolean consegueEditar = medicoRepository.alterarPeloId(id, medicoAtualizado);
            if (consegueEditar){

            }
        }catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void deletarPeloId(Integer id){
        try {
            boolean removeu =  medicoRepository.deletarPeloId(id);
            if (removeu){
                System.out.println(CoresMenu.VERDE_BOLD + "\nOperação realizada com sucesso!" + CoresMenu.RESET);
            }

        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }

    }
//
//    private MedicoRepository medicoRepository = new MedicoRepository();
//
//    public void inserir(Hospital hospital, Medico medico) {
//        medicoRepository.cadastrar(hospital, medico);
//    }
//
//    public void listarTodos(Hospital hospital) {
//        medicoRepository.listarTodos(hospital);
//    }
//
//    public void listarPeloId(Hospital hospital, Integer id) {
//        medicoRepository.listarPeloId(hospital, id);
//    }
//
//    public void alterarPeloId(Hospital hospital, Integer id, Medico medicoAtualizado){
//        medicoRepository.alterarPeloId(hospital, id, medicoAtualizado);
//    }
//
//    public void deletarPeloId(Hospital hospital, Integer id){
//        medicoRepository.deletarPeloId(hospital, id);
//    }
//
//    public Medico buscarId(Hospital hospital, Integer id){
//        return medicoRepository.buscarId(hospital.getMedicos(), id);
//    }
}
