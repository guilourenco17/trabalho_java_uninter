import java.util.ArrayList; // Importa ArrayList
import java.util.Scanner; //Importa Scanner

public class Cofrinho {
    private ArrayList<Moeda> moedas;
    
    public Cofrinho() { //Cria a classe Cofrinho
        moedas = new ArrayList<Moeda>();
    }
    
    public void adicionarMoeda(Moeda moeda) { // Adiciona moedas
        moedas.add(moeda);
        System.out.println("Moeda adicionada com sucesso!");
    }
    
    public void removerMoeda(Moeda moeda) { // Remove moedas
        if (moedas.remove(moeda)) {
            System.out.println("Moeda removida com sucesso!");
        } else {
            System.out.println("Moeda não encontrada no cofrinho!");
        }
    }
    
    public void listarMoedas() { //Lista as moedas no cofrinho sem conversão
        System.out.println("Moedas no cofrinho:");
        for (Moeda moeda : moedas) {
            System.out.println(moeda);
        }
    }
    
    public double calcularValorTotal() { // Calcula o valor total convertido para Real
        double valorTotal = 0;
        for (Moeda moeda : moedas) {
            valorTotal += moeda.getValorEmReal();
        }
        return valorTotal;
    }
    
    public static void main(String[] args) { //Apresenta menu de opções
        Scanner scanner = new Scanner(System.in);
        Cofrinho cofrinho = new Cofrinho();
        
        int opcao;
        do { 
            System.out.println("\nCofrinho - Escolha uma opção:");
            System.out.println("1 - Adicionar moeda");
            System.out.println("2 - Remover moeda");
            System.out.println("3 - Listar moedas");
            System.out.println("4 - Calcular valor total");
            System.out.println("0 - Sair");
            
            opcao = scanner.nextInt();
            
            switch (opcao) { //Uusário escolhe a moeda 
                case 1:
                    System.out.println("\nEscolha a moeda a ser adicionada:");
                    System.out.println("1 - Euro");
                    System.out.println("2 - Dólar");
                    System.out.println("3 - Real");
                    
                    int opcaoMoeda = scanner.nextInt();
                    
                    switch (opcaoMoeda) {
                        case 1:
                            System.out.println("Digite o valor em euros:");
                            double valorEuros = scanner.nextDouble();
                            cofrinho.adicionarMoeda(new Euro(valorEuros));
                            break;
                        case 2:
                            System.out.println("Digite o valor em dólares:");
                            double valorDolares = scanner.nextDouble();
                            cofrinho.adicionarMoeda(new Dolar(valorDolares));
                            break;
                        case 3:
                            System.out.println("Digite o valor em reais:");
                            double valorReais = scanner.nextDouble();
                            cofrinho.adicionarMoeda(new Real(valorReais));
                            break;
                        default:
                            System.out.println("Opção inválida!"); //Tratamento de erro caso o usuário escolha uma opção inválida
                            break;
                    }
                    break;
                case 2:
                    System.out.println("\nDigite o valor da moeda a ser removida:"); //Remover moeda
                    double valorMoeda = scanner.nextDouble();
                    Moeda moeda = null;
                    for (Moeda m : cofrinho.moedas) {
                        if (m.getValor() == valorMoeda) {
                            moeda = m;
                            break;
                        }
                    }
                    if (moeda != null) {
                        cofrinho.removerMoeda(moeda);
                    } else {
                        System.out.println("Moeda não encontrada no cofrinho!");
                    }
                    break;
                case 3:
                    cofrinho.listarMoedas();
                break;
            case 4:
                double valorTotal = cofrinho.calcularValorTotal(); //Calcula o valor total convertido e limitado a duas casas decimais
                System.out.printf("\nValor total no cofrinho: R$%.2f\n", valorTotal);
                break;
            case 0:
                System.out.println("\nEncerrando programa...");
                break;
            default:
                System.out.println("\nOpção inválida!");
                break;
        }
    } while (opcao != 0);
    
    scanner.close();
}

}

abstract class Moeda {
protected double valor;

public Moeda(double valor) {
    this.valor = valor;
}

public double getValor() {
    return valor;
}

public abstract double getValorEmReal(); //getValoEmReal multiplica o valor em Real pelo valor da cotação aproximado

public String toString() {
    return String.format("%.2f %s", valor, this.getClass().getSimpleName());
}

}

// Cria uma classe para cada moeda
class Euro extends Moeda {
public Euro(double valor) {
super(valor);
}

public double getValorEmReal() { 
    return valor * 6.23;
}

}

class Dolar extends Moeda {
public Dolar(double valor) {
super(valor);
}


public double getValorEmReal() {
    return valor * 5.23;
}

}

class Real extends Moeda {
public Real(double valor) {
super(valor);
}

public double getValorEmReal() {
    return valor;
}

}