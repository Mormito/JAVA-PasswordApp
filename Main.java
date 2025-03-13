import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PasswordManager gerador = new PasswordManager();

        boolean confirma = false;
        while(confirma == false){
            String senha = gerador.gerarSenha(10);
            System.out.println("Senha gerada: " + senha);

            System.out.println("Gostou da senha? (S/N) >> ");
            String opcao = sc.nextLine();

            if(opcao.equals("S") || opcao.equals("s")){
                confirma = true;
                System.out.println("Senha escolhida = " + senha);
                String criptografado = gerador.criptografarSenha(senha);
                System.out.println("Criptografado: " + criptografado);

            } else if (opcao.equals("N") || opcao.equals("n")) {
                System.out.println("Senha sera refeita!");
            } else System.out.println("Falha na entrada");
        }
    }
}