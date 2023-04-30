package br.com.desafio.api.utils;

public class ValidarCPF {
    
    public static boolean validateCPF(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", ""); // remove caracteres não numéricos
        
        if (cpf.length() != 11) {
            return false;
        }
        
        // calcula o primeiro dígito verificador
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (10 - i) * (cpf.charAt(i) - '0');
        }
        int digit1 = 11 - (sum % 11);
        if (digit1 > 9) {
            digit1 = 0;
        }
        
        // calcula o segundo dígito verificador
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += (11 - i) * (cpf.charAt(i) - '0');
        }
        int digit2 = 11 - (sum % 11);
        if (digit2 > 9) {
            digit2 = 0;
        }
        
        // verifica se os dígitos calculados correspondem aos dígitos do CPF informado
        return (cpf.charAt(9) - '0' == digit1) && (cpf.charAt(10) - '0' == digit2);
    }
}