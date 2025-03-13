import java.util.Random;
import java.security.*;
import java.util.concurrent.ExecutionException;
//Senti certa dificuldade para utilizar o security, mas deu tudo certo no final.

public class PasswordManager {

    public String gerarSenha(int tamanho) {
        Random rand = new Random();
        StringBuilder senha = new StringBuilder();
        String caracteres = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789!@#$%&*_";

            for(int j = 0; j < tamanho; j++){
                int indice = rand.nextInt(caracteres.length());
                System.out.print(indice + ", ");
                senha.append(caracteres.charAt(indice));
            }

        return senha.toString();
    }

    public String criptografarSenha(String senha) {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(senha.getBytes());
            byte[] hashBytes = md.digest();

            StringBuilder senhaEn = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(b & 0xff);
                if (hex.length() == 1) {
                    senhaEn.append('0');
                }
                senhaEn.append(hex);
            }
            return senhaEn.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}