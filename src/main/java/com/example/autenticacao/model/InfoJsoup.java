package com.example.autenticacao.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.example.autenticacao.service.InfoJsoupService;

public class InfoJsoup {

    public void processarUrl(String url) {
        String html = tentarComJsoup(url);

        if (html != null) {
            Document doc = Jsoup.parse(html);
            // Criando instâncias dos serviços para extrair informações
            InfoJsoupService fonteExtrair = new InfoJsoupService();
            InfoJsoupService dataExtrair = new InfoJsoupService();
            InfoJsoupService tituloExtrair = new InfoJsoupService();

            // Extraindo informações usando os métodos do serviço
            String fonte = fonteExtrair.extrairFonte(doc);
            String data = dataExtrair.extrairData(doc);
            String autor = tituloExtrair.extrairAutorOuResponsavel(doc);
            String titulo = tituloExtrair.extrairTitulos(doc);

            System.out.println("Fonte: " + fonte);
            System.out.println("Data de publicação: " + data);
            System.out.println("Autor da publicação: " + autor);
            System.out.println("Título da publicação: " + titulo);
        } else {
            System.out.println("Não foi possível obter o conteúdo da página com Jsoup.");
        }
    }

    public static String tentarComJsoup(String url) {
        try {
            System.out.println("Tentando com Jsoup...");
            return Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                            "(KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
                    .timeout(10000)
                    .get()
                    .html();
        } catch (Exception e) {
            System.out.println("Jsoup falhou: " + e.getMessage());
            return null;
        }
    }
}
