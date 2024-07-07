package com.udemy.gmailrecyclerview.model;

import java.util.Arrays;
import java.util.List;

public class Emails {

    public static List<Email> fakeEmails() {
        return Arrays.asList(
                Email.EmailBuilder.builder()
                        .setUser("Facebook")
                        .setSubject("Veja nossas três dicas")
                        .setDate("5 jun")
                        .build(),

                Email.EmailBuilder.builder()
                        .setUser("Facebook")
                        .setSubject("Um amigo quer que você curta uma página dele")
                        .setPreview("Funalo convidou você para curtir a sua Página no Facebook")
                        .setDate("30 mai")
                        .build(),

                Email.EmailBuilder.builder()
                        .setUser("Youtube")
                        .setSubject("Tiago Aguiar acabou de publicar um vídeo")
                        .setPreview("Tiago Aguiar enviou: ANDROID: GOOLE MAPS, LOCATION")
                        .setDate("30 mai")
                        .setStared(true)
                        .setUnread(true)
                        .build(),

                Email.EmailBuilder.builder()
                        .setUser("Instagram")
                        .setSubject("tiagoaguiar.oficial começou a seguir-te")
                        .setPreview("tiagoaguiar.oficial, tens um novo seguidor")
                        .setDate("18 mai")
                        .setStared(false)
                        .build(),

                // Novos e-mails adicionados
                Email.EmailBuilder.builder()
                        .setUser("LinkedIn")
                        .setSubject("Você tem novas conexões aguardando")
                        .setPreview("Veja quem quer se conectar com você")
                        .setDate("10 jun")
                        .setStared(false)
                        .setUnread(true)
                        .build(),

                Email.EmailBuilder.builder()
                        .setUser("Twitter")
                        .setSubject("Novos seguidores para você")
                        .setPreview("3 pessoas começaram a seguir você no Twitter")
                        .setDate("8 jun")
                        .setStared(true)
                        .build(),

                Email.EmailBuilder.builder()
                        .setUser("GitHub")
                        .setSubject("Notificações não lidas")
                        .setPreview("Você tem 5 notificações não lidas no GitHub")
                        .setDate("6 jun")
                        .setStared(false)
                        .setUnread(true)
                        .build(),

                Email.EmailBuilder.builder()
                        .setUser("Netflix")
                        .setSubject("Novidades na sua lista")
                        .setPreview("Temos novas recomendações para você")
                        .setDate("4 jun")
                        .setStared(false)
                        .build(),

                Email.EmailBuilder.builder()
                        .setUser("Spotify")
                        .setSubject("Sua playlist semanal está pronta")
                        .setPreview("Ouça agora sua nova playlist semanal")
                        .setDate("3 jun")
                        .setStared(true)
                        .setUnread(false)
                        .build(),

                Email.EmailBuilder.builder()
                        .setUser("Amazon")
                        .setSubject("Seu pedido foi enviado")
                        .setPreview("Seu pedido #123456 foi enviado")
                        .setDate("2 jun")
                        .setStared(false)
                        .setUnread(true)
                        .build()
        );
    }
}