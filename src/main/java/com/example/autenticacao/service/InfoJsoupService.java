package com.example.autenticacao.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class InfoJsoupService {
     public String extrairData(Document doc) {
        String[] seletoresPossiveis = {
                "meta[name=date]",
                "meta[name=pubdate]",
                "meta[property=article:published_time]",
                "meta[itemprop=datePublished]",
                "time[datetime]",
                "span[class*=date]",
                "p[class*=data]",
                "div[class*=date]"
        };

        for (String seletor : seletoresPossiveis) {
            Element elemento = doc.selectFirst(seletor);
            if (elemento != null) {
                String data;

                if (elemento.hasAttr("content")) {
                    data = elemento.attr("content");
                } else if (elemento.hasAttr("datetime")) {
                    data = elemento.attr("datetime");
                } else {
                    data = elemento.text();
                }

                if (data != null && !data.isEmpty()) {
                    String dataFormatada = tentarFormatarData(data);
                    if (dataFormatada != null) {
                        return dataFormatada;
                    } else
                        return data;
                }
            }
        }

        return "";
    }

    private String tentarFormatarData(String data) {
        String[] formatos = {
                "yyyy-MM-dd'T'HH:mm:ss.SSSX",
                "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
                "yyyy-MM-dd'T'HH:mm:ssX",
                "yyyy-MM-dd'T'HH:mm:ssXXX",
                "yyyy-MM-dd'T'HH:mm:ss",
                "yyyy-MM-dd",
                "dd/MM/yyyy",
                "dd-MM-yyyy"
        };

        for (String formato : formatos) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
                if (formato.contains("X")) {
                    return java.time.OffsetDateTime.parse(data, formatter)
                            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                } else if (formato.contains("HH")) {
                    LocalDateTime dateTime = LocalDateTime.parse(data, formatter);
                    return dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                } else {
                    LocalDate date = LocalDate.parse(data, formatter);
                    return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                }
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido: " + formato + " para a data: " + data);
            }
        }
        return null;
    }
/*<-----------Fonte----------->*/
   public String extrairFonte(Document doc) {
        return doc.select("meta[name=author], meta[property=og:site_name]").attr("content");
    }

/*<-----------Titulo----------->*/
 public String extrairAutorOuResponsavel(Document doc) {
        return doc.select("meta[name=author], meta[property=og:site_name]").attr("content");
    }

    public String extrairTitulos(Document doc) {
        Elements titulos = doc.select("h1");
        StringBuilder sb = new StringBuilder();

        for (Element titulo : titulos) {
            sb.append(titulo.text()).append("\n");
        }

        return sb.toString();
    }

    public String tipoDeTexto(Element titulos) {
        String texto = titulos.text();

        if (texto.equals(texto.toUpperCase())) {
            return "O título está todo em MAIÚSCULAS.";
        } else if (texto.equals(texto.toLowerCase())) {
            return "O título está todo em minúsculas.";
        } else {
            return "O título está misturado.";
        }

    }

    
}
