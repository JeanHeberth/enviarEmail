package br.com.teste.enviar.email;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

    public void enviarEmail(boolean enviaHTML) throws Exception {


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

        if (enviaHTML) {
            message.setContent(textoEmail, "text/html; charset=utf-8");
        } else {
            message.setText(textoEmail);
        }
        Transport.send(message);
    }

    public void enviarEmailComPdf(boolean enviaHTML) throws Exception {


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

        /* Parte  1 do email com texto e descrição */
        MimeBodyPart corpoEmail = new MimeBodyPart();

        if (enviaHTML) {
            corpoEmail.setContent(textoEmail, "text/html; charset=utf-8");
        } else {
            corpoEmail.setText(textoEmail);
        }

        /*  Parte 2 do email enviando o anexo  */
        MimeBodyPart anexoEmail = new MimeBodyPart();
        anexoEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(simuladorDePdf(), "application/pdf")));
        anexoEmail.setFileName("anexoemail.pdf");

        /* Junçao da parte 1 com a parte 2  */
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(corpoEmail);
        multipart.addBodyPart(anexoEmail);
        message.setContent(multipart);

        Transport.send(message);
    }


    /*
    Esse método simula o PDF ou qualquer arquivo para o email
     */
    private FileInputStream simuladorDePdf() throws Exception{

        Document document = new Document();
        File file = new File("fileanexo.pdf");
        file.createNewFile();
        PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();
        document.add(new Paragraph("Verificando o texto do pdf"));
        document.close();


        return new FileInputStream(file);
    }
}
