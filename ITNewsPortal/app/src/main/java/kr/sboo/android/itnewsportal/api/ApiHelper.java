package kr.sboo.android.itnewsportal.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import kr.sboo.android.itnewsportal.model.News;
import lombok.Getter;
import lombok.Setter;

public class ApiHelper {
    public static final String NEWS_API_URL = "https://www.swjang.com/api/feed";

    public static List<News> getNewsListFromApi(){
        HttpsURLConnection connection = null;
        BufferedReader reader = null;
        try{
            URL url = new URL(NEWS_API_URL);
            connection = (HttpsURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String inputLine;
            while((inputLine = reader.readLine()) != null){
                stringBuffer.append(inputLine);
            }

            return convertToNewsList(stringBuffer.toString());
        }
        catch (MalformedURLException ex){
            return null;
        }
        catch (IOException ex){
            return null;
        }
        finally {
            try{
                if(reader != null)
                    reader.close();
            }
            catch (IOException ex){}
        }
    }

    private static List<News> convertToNewsList(String json){
        Gson gson = new Gson();
        Type responseType = new TypeToken<ResponseWrapper>(){}.getType();
        ResponseWrapper response = gson.fromJson(json, responseType);
        return response.getData().getFeed().getContent();
    }

    @Getter
    @Setter
    class Error{
        private String code;
        private String msg;
        private String alert;
    }

    @Getter
    @Setter
    class FeedWrapper{
        private List<News> content;
    }

    @Getter
    @Setter
    class DataWrapper{
        private FeedWrapper feed;
    }

    @Getter
    @Setter
    class ResponseWrapper{
        private Error err;
        private DataWrapper data;
    }
}
