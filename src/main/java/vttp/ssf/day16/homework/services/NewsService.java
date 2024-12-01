package vttp.ssf.day16.homework.services;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.ssf.day16.homework.models.Article;
import vttp.ssf.day16.homework.models.SearchParams;

@Service
public class NewsService {
    
    public static final String GET_URL = "https://newsapi.org/v2/top-headlines";

    @Value("${api.key}")
    private String API_KEY;

    public List<Article> searchNewsArticles(SearchParams params){

        String url = UriComponentsBuilder
                    .fromUriString(GET_URL)
                    .queryParam("q", params.query())
                    .queryParam("country", params.country())
                    .queryParam("category", params.category())
                    .queryParam("apiKey", API_KEY)
                    .toUriString();
        
        RequestEntity<Void> req = RequestEntity.get(url)
                                .accept(MediaType.APPLICATION_JSON)
                                .build();

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp;

        List<Article> newsArticles = new LinkedList<>();

        try {
            resp = template.exchange(req, String.class);
            String payload = resp.getBody();
            JsonReader reader = Json.createReader(new StringReader(payload));

            JsonObject articles = reader.readObject();
            JsonArray articleArray = articles.getJsonArray("articles");
            for(int i = 0; i < articleArray.size(); i++){
                Article article = new Article();
                String title = articleArray.getJsonObject(i)
                                .getString("title"); 
                String description = articleArray.getJsonObject(i)
                                .getString("description");
                String imageUrl = articleArray.getJsonObject(i)
                                .getString("urlToImage");
                String articleUrl = articleArray.getJsonObject(i)
                                .getString("url");
                
                article.setTitle(title);
                article.setDescription(description);
                article.setImageUrl(imageUrl);
                article.setArticleUrl(articleUrl);
                
                newsArticles.add(article);
            }
            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return newsArticles;
    }

}
