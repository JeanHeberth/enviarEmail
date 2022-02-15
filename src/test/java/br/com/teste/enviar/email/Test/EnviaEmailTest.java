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

        for (int i = 0; i <= 10; i++) {
            EnviaEmail enviaEmail =
                    new EnviaEmail("jeanheberth19@gmail.com, jean-hv@hotmail.com, jessicaflorzinha2014@gmail.com",
                            "Jean - Teste de Envio",
                            "Testando enviar email",
                            "Será se essa pouha vai dar certo?");

            enviaEmail.enviarEmail();

            Thread.sleep(2000);
        }
    }
}