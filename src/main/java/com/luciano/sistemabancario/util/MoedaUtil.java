// src/main/java/com/luciano/sistemabancario/util/MoedaUtil.java
package com.luciano.sistemabancario.util;

import java.text.NumberFormat;
import java.util.Locale;

public class MoedaUtil {
    private static final NumberFormat FORMATADOR = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    /**
     * Formata um valor double para o formato de moeda brasileira (R$).
     * @param valor O valor a ser formatado.
     * @return Uma string formatada como moeda.
     */
    public static String formatar(double valor) {
        return FORMATADOR.format(valor);
    }
}


