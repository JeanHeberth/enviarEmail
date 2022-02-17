package br.com.teste.enviar.email.Test;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.teste.enviar.email.EnviaEmail;
import org.junit.Test;

public class EnviaEmailTest {


    @Test
    public void testeEmail() throws Exception {

        EnviaEmail enviaEmail =
                new EnviaEmail("jeanheberth19@gmail.com",
                        "Jean - Teste de Envio",
                        "Testando enviar email",
                        " Testando o envio de email com java ");


        enviaEmail.enviarEmail(false);

    }

    @Test
    public void testeEmailComHtml() throws Exception {

        StringBuilder stringBuilderTexotEmail = new StringBuilder();
        stringBuilderTexotEmail.append("Olá, <br/><br/>");
        stringBuilderTexotEmail.append("Você está cadastrado no nosso site de cuidador de idosos, <br/><br/>");
        stringBuilderTexotEmail.append("Para ter acesso clique no botão a baixo, <br/><br/>");


        stringBuilderTexotEmail.append("<b>Login:</b> jeanheberth <br/><br/>");
        stringBuilderTexotEmail.append("<b>Senha:</b> jeanheberth <br/><br/>");
        stringBuilderTexotEmail.append("<a target=\"_blank\" href=\"www.google.com.br\" " +
                "style=\"color:#2525a7;" +
                "padding: 14px 25px;" +
                "text-align:center;" +
                "text-decoration: none; " +
                "display: inline-block;" +
                "border-radius: 30px;" +
                "font-size: 20px;" +
                "font-family:courier;" +
                "border: 3px solid green;\">" +
                "Clique aqui</a> <br/><br/>");


            stringBuilderTexotEmail.append("<span style=\"font-size:12px;\">" +
                    "&copy; Ass.: Cuidadores com amor</span>");

        EnviaEmail enviaEmail =
                new EnviaEmail("jeanheberth19@gmail.com",
                        "Jean - Teste de Envio",
                        "Testando enviar email",
                        stringBuilderTexotEmail.toString());


        enviaEmail.enviarEmail(true);

    }

    @Test
    public void enviarAnexoPdf() throws Exception {

        StringBuilder stringBuilderTexotEmail = new StringBuilder();
        stringBuilderTexotEmail.append("Olá, <br/><br/>");
        stringBuilderTexotEmail.append("Você está cadastrado no nosso site de cuidador de idosos, <br/><br/>");
        stringBuilderTexotEmail.append("Para ter acesso clique no botão a baixo, <br/><br/>");


        stringBuilderTexotEmail.append("<b>Login:</b> jeanheberth <br/><br/>");
        stringBuilderTexotEmail.append("<b>Senha:</b> jeanheberth <br/><br/>");
        stringBuilderTexotEmail.append("<a target=\"_blank\" href=\"www.google.com.br\" " +
                "style=\"color:#2525a7;" +
                "padding: 14px 25px;" +
                "text-align:center;" +
                "text-decoration: none; " +
                "display: inline-block;" +
                "border-radius: 30px;" +
                "font-size: 20px;" +
                "font-family:courier;" +
                "border: 3px solid green;\">" +
                "Clique aqui</a> <br/><br/>");


        stringBuilderTexotEmail.append("<span style=\"font-size:12px;\">" +
                "&copy; Ass.: Cuidadores com amor</span>");

        EnviaEmail enviaEmail =
                new EnviaEmail("jeanheberth19@gmail.com",
                        "Jean - Teste de Envio",
                        "Testando enviar email",
                        stringBuilderTexotEmail.toString());


        enviaEmail.enviarEmailComPdf(true);

    }
}