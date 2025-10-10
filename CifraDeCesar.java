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
} else {
textoCifrado.append(caractere);
}
}
return textoCifrado.toString();
}
public static String descriptografar(String textoCifrado, int chave) {
int chaveInversa = 26 - (chave % 26);
return criptografar(textoCifrado, chaveInversa);
}
public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);
System.out.println("--- Criptografia com Cifra de César ---");
System.out.print("Digite a mensagem: ");
String mensagemOriginal = scanner.nextLine();
System.out.print("Digite a chave: ");
int chave = scanner.nextInt();
String mensagemCriptografada = criptografar(mensagemOriginal, chave);
System.out.println("Mensagem Criptografada: " +
mensagemCriptografada);
String mensagemDescriptografada =
descriptografar(mensagemCriptografada, chave);
System.out.println("Mensagem Descriptografada: " +
mensagemDescriptografada);
scanner.close();
}
}