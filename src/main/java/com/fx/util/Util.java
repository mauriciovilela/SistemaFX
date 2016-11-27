package com.fx.util;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

import com.fx.service.NegocioException;
import com.fx.util.jsf.FacesUtil;

public class Util {

	public static String md5Java(String message) {
		String digest = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hash = md.digest(message.getBytes("UTF-8"));
			// converting byte array to Hexadecimal String
			StringBuilder sb = new StringBuilder(2 * hash.length);
			for (byte b : hash) {
				sb.append(String.format("%02x", b & 0xff));
			}
			digest = sb.toString();
		} catch (Exception ex) {

		}
		return digest;
	}
	
	public static Integer fillIdContrato(Integer idContrato) {
		if (idContrato == null)
			return idContrato;
		int tamanho = String.valueOf(idContrato).length();
		if (tamanho <= 4) {
			Calendar hoje = Calendar.getInstance();
			hoje.setTime(new Date());
			Integer anoAtual = hoje.get(Calendar.YEAR);
			return Integer.parseInt(anoAtual.toString().concat(String.format("%04d", idContrato)));
		}
		else {
			return idContrato;
		}
	}
	
	public static String converteData(java.util.Date dtData){  
	   SimpleDateFormat formatBra = new SimpleDateFormat("dd/MM/yyyy");  
	   try {  
	      java.util.Date newData = formatBra.parse(dtData.toString());  
	      return (formatBra.format(newData));  
	   } catch (ParseException Ex) {  
	      return "Erro na conversão da data";  
	   }  
	} 
	
	public static BigDecimal toBigDecimal(String valor) {
		valor = valor.replace(".", "");
		valor = valor.replace(",", ".");
		return new BigDecimal(valor);
	}

	public static Calendar dateToCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public static Date getDataAtual() {
		return new Date();
	}

	@SuppressWarnings("rawtypes")
	public static void mensagemRegistros(List lista) {
		if (lista == null || lista.isEmpty()) {
			FacesUtil.addWarningMessage("Nenhum registro foi encontrado");
		}
	}

	public static void validaCNPJ(String CNPJ) {
		boolean validado = true;
		CNPJ = CNPJ.replace(".", "");
		CNPJ = CNPJ.replace("/", "");
		CNPJ = CNPJ.replace("-", "");
		// considera-se erro CNPJ's formados por uma sequencia de numeros iguais
		if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111")
				|| CNPJ.equals("22222222222222")
				|| CNPJ.equals("33333333333333")
				|| CNPJ.equals("44444444444444")
				|| CNPJ.equals("55555555555555")
				|| CNPJ.equals("66666666666666")
				|| CNPJ.equals("77777777777777")
				|| CNPJ.equals("88888888888888")
				|| CNPJ.equals("99999999999999") || (CNPJ.length() != 14))
			validado = false;
		else {

			char dig13, dig14;
			int sm, i, r, num, peso;

			// "try" - protege o código para eventuais erros de conversao de
			// tipo
			// (int)
			try {
				// Calculo do 1o. Digito Verificador
				sm = 0;
				peso = 2;
				for (i = 11; i >= 0; i--) {
					// converte o i-ésimo caractere do CNPJ em um número:
					// por exemplo, transforma o caractere '0' no inteiro 0
					// (48 eh a posição de '0' na tabela ASCII)
					num = (int) (CNPJ.charAt(i) - 48);
					sm = sm + (num * peso);
					peso = peso + 1;
					if (peso == 10)
						peso = 2;
				}

				r = sm % 11;
				if ((r == 0) || (r == 1))
					dig13 = '0';
				else
					dig13 = (char) ((11 - r) + 48);

				// Calculo do 2o. Digito Verificador
				sm = 0;
				peso = 2;
				for (i = 12; i >= 0; i--) {
					num = (int) (CNPJ.charAt(i) - 48);
					sm = sm + (num * peso);
					peso = peso + 1;
					if (peso == 10)
						peso = 2;
				}

				r = sm % 11;
				if ((r == 0) || (r == 1))
					dig14 = '0';
				else
					dig14 = (char) ((11 - r) + 48);

				// Verifica se os dígitos calculados conferem com os dígitos
				// informados.
				if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13)))
					validado = true;
				else
					validado = false;
			} catch (InputMismatchException erro) {
				validado = false;
			}
		}
		if (!validado) {
			throw new NegocioException("CNPJ não é válido");
		}
	}

}
