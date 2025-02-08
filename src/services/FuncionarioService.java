package services;

import models.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class FuncionarioService {
    private List<Funcionario> funcionarios = new ArrayList<>();
    private BigDecimal salarioMinimo = new BigDecimal("1212.00");

    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public void adicionarFuncionariosDaTabela() {
        adicionarFuncionario(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        adicionarFuncionario(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        adicionarFuncionario(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        adicionarFuncionario(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        adicionarFuncionario(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        adicionarFuncionario(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        adicionarFuncionario(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        adicionarFuncionario(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        adicionarFuncionario(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        adicionarFuncionario(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
    }

    public void removerFuncionarioPorNome(String nome) {
        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase(nome));
    }

    public void aumentarSalarios(BigDecimal percentual) {
        for (Funcionario f : funcionarios) {
            f.aumentarSalario(percentual);
        }
    }

    public void imprimirFuncionarios() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Funcionario f : funcionarios) {
            System.out.printf("%s - %s - R$ %,.2f - %s%n",
                    f.getNome(),
                    f.getDataNascimento().format(formatter),
                    f.getSalario(),
                    f.getFuncao());
        }
    }

    public void imprimirPorFuncao() {
        Map<String, List<Funcionario>> agrupados = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        agrupados.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(f -> System.out.println("  - " + f.getNome()));
        });
    }

    public void imprimirAniversariantesPorMeses(List<Integer> meses) {
        funcionarios.stream()
                .filter(f -> meses.contains(f.getDataNascimento().getMonthValue()))
                .forEach(f -> System.out.println(f.getNome() + " faz aniversário em " + f.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM"))));
    }

    public void imprimirFuncionarioMaisVelho() {
        Funcionario funcionarioMaisVelho = Collections.min(funcionarios, Comparator.comparing(Funcionario::getDataNascimento));
        System.out.println(funcionarioMaisVelho.getNome() + ", com " + funcionarioMaisVelho.getIdade() + " anos");
    }

    public void imprimirFuncionariosOrdemAlfabetica() {
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(f -> System.out.println(f.getNome() + " - " + f.getFuncao()));
    }

    public void imprimirTotalSalarios() {
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("R$ " + String.format("%,.2f", totalSalarios));
    }

    public void imprimirSalariosMinimos() {
        funcionarios.forEach(f -> {
            BigDecimal salariosMinimos = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.println(f.getNome() + " ganha " + salariosMinimos + " salários mínimos.");
        });
    }
}
