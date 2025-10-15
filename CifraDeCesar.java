import java.util.Scanner;

public class CifraDeCesar {

    // Função para criptografar um texto usando a Cifra de César
    public static String criptografar(String texto, int chave) {
        StringBuilder textoCifrado = new StringBuilder();
        int tamanhoAlfabeto = 26;
        
        for (char caractere : texto.toCharArray()) {
            if (Character.isLetter(caractere)) {
                char base = Character.isUpperCase(caractere) ? 'A' : 'a';
                int posicaoOriginal = caractere - base;
                int novaPosicao = (posicaoOriginal + chave) % tamanhoAlfabeto;
                char novoCaractere = (char) (base + novaPosicao);
                textoCifrado.append(novoCaractere);
            } else if (Character.isDigit(caractere)) {
                // Criptografa números
                int novaPosicao = (caractere - '0' + chave) % 10;
                textoCifrado.append((char) ('0' + novaPosicao));
            } else {
                textoCifrado.append(caractere);
            }
        }
        return textoCifrado.toString();
    }

    // Função para descriptografar um texto usando a Cifra de César
    public static String descriptografar(String textoCifrado, int chave) {
        int chaveInversa = 26 - (chave % 26);
        return criptografar(textoCifrado, chaveInversa);
    }

    // Função para validar a chave (deve ser entre 1 e 25)
    public static int validarChave(int chave) {
        if (chave <= 0 || chave >= 26) {
            throw new IllegalArgumentException("A chave deve ser um valor entre 1 e 25.");
        }
        return chave;
    }

    // Função para realizar o ataque de força bruta
    public static void quebrarCifra(String textoCifrado) {
        System.out.println("Tentando todas as chaves possíveis...");
        for (int chave = 1; chave < 26; chave++) {
            String tentativa = descriptografar(textoCifrado, chave);
            System.out.println("Chave " + chave + ": " + tentativa);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Menu
        while (true) {
            System.out.println("--- Criptografia com Cifra de César ---");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Criptografar");
            System.out.println("2 - Descriptografar");
            System.out.println("3 - Força Bruta (quebrar cifra)");
            System.out.println("4 - Sair");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            if (escolha == 4) {
                System.out.println("Saindo...");
                break;
            }

            switch (escolha) {
                case 1:
                    System.out.print("Digite a mensagem para criptografar: ");
                    String mensagemCripto = scanner.nextLine();
                    System.out.print("Digite a chave (1-25): ");
                    int chaveCripto = scanner.nextInt();
                    try {
                        chaveCripto = validarChave(chaveCripto);
                        String mensagemCifrada = criptografar(mensagemCripto, chaveCripto);
                        System.out.println("Mensagem Criptografada: " + mensagemCifrada);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Digite a mensagem para descriptografar: ");
                    String mensagemDescripto = scanner.nextLine();
                    System.out.print("Digite a chave (1-25): ");
                    int chaveDescripto = scanner.nextInt();
                    try {
                        chaveDescripto = validarChave(chaveDescripto);
                        String mensagemDescriptografada = descriptografar(mensagemDescripto, chaveDescripto);
                        System.out.println("Mensagem Descriptografada: " + mensagemDescriptografada);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Digite a mensagem cifrada para realizar o ataque de força bruta: ");
                    String textoCifrado = scanner.nextLine();
                    quebrarCifra(textoCifrado);
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }
}
