package br.com.paulosa.crud;

import dao.ClienteMapDAO;
import dao.IClienteDAO;
import domain.Cliente;

import javax.swing.*;

public class Programa {

    private static IClienteDAO iClienteDAO;
    
    public static void main(String[] args) {

        iClienteDAO = new ClienteMapDAO();

        String opcao = JOptionPane.showInputDialog(null,
                "Digite 1 para novo cadastro, 2 para consulta, 3 para excluir, 4 para realizar alterações ou 5 para sair da aplicação", 
                "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        
        while (!isOpcaoValida(opcao)){
            if ("".equals(opcao)){
                sair();
            }
            opcao = JOptionPane.showInputDialog(null,
                    "Opção Inválida. Digite 1 para novo cadastro, 2 para consulta, 3 para excluir, 4 para realizar alterações ou 5 para sair da aplicação",
                    "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        }

        while (isOpcaoValida(opcao)){
            if (isOpcaoSair(opcao)){
                sair();
            } else if (isCadastro(opcao)) {
                String dados = JOptionPane.showInputDialog(null, "Digite os dados do cliente separados por vírgula conforme exemplo: Nome, CPF, Telefone, Endereço, Número, Cidade e Estado"
                        ,"Cadastro", JOptionPane.INFORMATION_MESSAGE);
                cadastrar(dados);

            } else if (isConsultar(opcao)) {
                String dados = JOptionPane.showInputDialog(null,"Cadastro", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        if ("1".equals(opcao)){
            JOptionPane.showMessageDialog(null, "Opção" + opcao, "ERRO", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static boolean isConsultar(String opcao) {
        if ("2".equals(opcao)){
            return true;
        }
        return false;
    }

    private static void cadastrar(String dados) {
        String [] dadosSeparados = dados.split(",");
        Cliente cliente = new Cliente(dadosSeparados[0], dadosSeparados[1], dadosSeparados[2], dadosSeparados[3], dadosSeparados[4], dadosSeparados[5], dadosSeparados[6]);
        boolean isCadastrado = iClienteDAO.cadastrar(cliente);
        if (isCadastrado){
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso", "sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente já cadastrado", "erro", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static boolean isCadastro(String opcao) {
        if ("1".equals(opcao)){
            return true;
        }
        return false;
    }

    private static boolean isOpcaoSair(String opcao) {
        if ("5".equals(opcao)){
            return true;
            }
            return false;
    }

    private static void sair() {
        JOptionPane.showMessageDialog(null, "Até logo", "Sair", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private static boolean isOpcaoValida(String opcao) {
        if ("1".equals(opcao) || "2".equals(opcao) || "3".equals(opcao) || "4".equals(opcao)
                || "5".equals(opcao)){
            return true;
        }
        return false;
    }
}
