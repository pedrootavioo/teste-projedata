import services.FuncionarioService;

import java.math.BigDecimal;
import java.util.Arrays;

public class Principal {
    public static void main(String[] args) {
        FuncionarioService service = new FuncionarioService();

        // Adicionando funcionários da tabela (enunciado)
        service.adicionarFuncionariosDaTabela();
        System.out.println("\n✅ Lista de Funcionários carregada conforme a tabela.");

        // Removendo João
        service.removerFuncionarioPorNome("João");
        System.out.println("\n✅ João removido da lista.");

        // Imprimindo funcionários
        System.out.println("\n🔷 Lista de Funcionários:");
        service.imprimirFuncionarios();

        // Aumento de 10%
        service.aumentarSalarios(new BigDecimal("0.10"));
        System.out.println("\n✅ Aumento de 10% atribuído para cada funcionário.");

        System.out.println("\n🔷 Lista de Funcionários após aumento:");
        service.imprimirFuncionarios();

        // Imprimir agrupados por função
        System.out.println("\n🔷 Funcionários agrupados por função:");
        service.imprimirPorFuncao();

        // Imprimindo funcionários que fazem aniversário no mês 10 e 12
        System.out.println("\n🔷 Funcionários aniversariantes de outubro e dezembro:");
        service.imprimirAniversariantesPorMeses(Arrays.asList(10, 12));

        // Imprimir funcionário mais velho
        System.out.println("\n🔷 Funcionário mais velho:");
        service.imprimirFuncionarioMaisVelho();

        // Imprimir lista em ordem alfabética
        System.out.println("\n🔷 Funcionários em ordem alfabética:");
        service.imprimirFuncionariosOrdemAlfabetica();

        // Imprimir soma dos salários
        System.out.println("\n🔷 Total dos salários");
        service.imprimirTotalSalarios();

        // Imprimindo salários mínimos por funcionário
        System.out.println("\n🔷 Qtd. de Salários mínimos por funcionário:");
        service.imprimirSalariosMinimos();
    }
}
