package br.com.teste.enviar.email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EnviaEmail {

    private String userName = "testandoemailjean@gmail.com";
    private String senha = "testandoemailjean12#";

    private String listaDestinarios = "";
    private String nomeRemetente = "";
    private String assuntoEmail = "";
    private String textoEmail = "";

    public EnviaEmail(String listaDestinarios, String nomeRemetente, String assuntoEmail, String textoEmail) {
        this.listaDestinarios = listaDestinarios;
        this.nomeRemetente = nomeRemetente;
        this.assuntoEmail = assuntoEmail;
        this.textoEmail = textoEmail;
    }

    public void enviarEmail() throws Exception {

        Properties properties = new Properties();

        properties.put("mail.smtp.ssl", "*"); /* Autenticação */
        properties.put("mail.smtp.auth", "true"); /* Autorização */
        properties.put("mail.smtp.starttls", "true"); /* Autenticação */
        properties.put("mail.smtp.host", "smtp.gmail.com"); /* Servidor gmail */
        properties.put("mail.smtp.port", "465"); /* porta do servidor */
        properties.put("mail.smtp.socketFactory.port", "465"); /* Expecifica a porta a ser conectada pelo socket */
        properties.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory"); /* Classe socket de conexão ao SMTP */

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, senha);
            }

        });
        Address[] toUser = InternetAddress
                .parse(listaDestinarios);

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(userName, nomeRemetente)); /* Endereço de quem está enviando a mensagem */
        message.setRecipients(Message.RecipientType.TO, toUser); /* Email de destino */
        message.setSubject(assuntoEmail); /* Assunto do email */
        message.setText(textoEmail); /* Corpo do email */
        Transport.send(message);

    }
}

