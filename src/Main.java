

import model.Sistema.Sistema;
import model.autenticacao.Profile;
import model.autenticacao.Role;
import model.autenticacao.User;
import model.pessoa.Pessoa;
import model.pessoa.PessoaFisica;
import model.pessoa.PessoaJuridica;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Objects;

public class Main {

    public static PessoaFisica novaPessoa(Long id, String nome, LocalDate nascimento, String cpf) {
        PessoaFisica pf = new PessoaFisica();
        pf.setNome(nome);
        pf.setCpf(cpf);
        pf.setId(id);
        pf.setNascimento(nascimento);
        return pf;
    }

    public static PessoaJuridica novaPessoaJuridica(Long id, String nome, LocalDate nascimento, String cnpj) {
        PessoaJuridica pj = new PessoaJuridica();
        pj.setNome(nome);
        pj.setNascimento(nascimento);
        pj.setId(id);
        pj.setCnpj(cnpj);
        return pj;
    }

    public static User novoUsuario(Long id, String email, String password, Pessoa pessoa) {
        User login = new User();
        login.setPessoa(pessoa);
        login.setId(id);
        login.setEmail(email);
        login.setPassword(password);
        return login;
    }

    public static Profile novoProfile(Long id, String nome) {
        Profile profile = new Profile();
        profile.setId(id);
        profile.setNome(nome);
        return profile;
    }

    public static Role novaRole(Long id, String nome, String descricao, Sistema sistema) {
        Role rl = new Role();
        rl.setId(id);
        rl.setNome(nome);
        rl.setDescricao(descricao);
        return rl;
    }

    public static Sistema novoSistema(Long id, String nome, String sigla) {
        Sistema sistema = new Sistema();
        sistema.setId(id);
        sistema.setNome(nome);
        sistema.setSigla(sigla);
        return sistema;
    }

    public static void main(String[] args) {
        PessoaFisica leo = novaPessoa(1L,"leo",LocalDate.of(2005,1,9),"111111111");
        PessoaJuridica holding = novaPessoaJuridica(2L,"Holding",LocalDate.of(2021,3,15),"111111");
        User leol = novoUsuario(3L,"leo@hotmail.com","senha",leo);
        Profile gerente = novoProfile(4L,"leo");
        Sistema mercado = novoSistema(5L,"Mercado","mc");
        Role operaCaixa = novaRole(6L, "Parducci", "Vai operar o caixa", mercado);

        PessoaFisica brabo = novaPessoa(732L,"leo filho", LocalDate.of(2023,8,15),"2342345");

        leo.addFilho(brabo);
        holding.addSocio(leo);
        leol.addProfile(gerente);
        mercado.addResponsavel(holding);
        int sucesso = 0;

        do {

            var login = JOptionPane.showInputDialog("Email: ");
            var senha = JOptionPane.showInputDialog("Senha: ");

            if (Objects.equals(login, leol.getEmail()) && Objects.equals(senha, leol.getPassword())) {
                System.out.println("Login efetuado com sucesso.");
                sucesso = 1;
            } else {
                System.out.println("Email ou senha incorretos Falha no login.");
                sucesso = 0;
            }

        }while (sucesso != 1);

        System.out.println("Pessoa física: " + leo);
        System.out.println("Filho da Pessoa física: " + brabo);
        System.out.println("Pessoa Jurídica: " + holding);
        System.out.println("Login: " + leol);
        System.out.println("Gerente: " + gerente);
        System.out.println("Login: " + leol);
        System.out.println("Sistema: " + mercado);
        System.out.println("Role: " + operaCaixa);

    }




}
