package org.example.tanmay.droidvbeta;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

public class ParseData {

    private String data;
    private ArrayList<PostsData> posts;

    public ParseData(String data) {
        this.data = data;
        posts = new ArrayList<PostsData>();
    }

    public ArrayList<PostsData> getPosts() {
        return posts;
    }

    public boolean process(){

        boolean status = true;
        PostsData currentRecord =null;
        boolean inItem =false;
        String textValue = "";

        try{
            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(this.data));
            int eventType = xpp.getEventType();
            while(eventType!=XmlPullParser.END_DOCUMENT){

                String tagName = xpp.getName();
                switch (eventType){
                    case XmlPullParser.START_TAG:
                       // Log.d("ParseData", "Starting tag for "+ tagName);

                        if(tagName.equalsIgnoreCase("item")){
                            inItem =true;
                            currentRecord = new PostsData();

                        } break;
                        case XmlPullParser.TEXT:
                            textValue = xpp.getText();

                            break;
                        case XmlPullParser.END_TAG:
                          //  Log.d("ParseData", "Ending tag for "+ tagName);

                            if(inItem){
                                if(tagName.equalsIgnoreCase("item")) {
                                    posts.add(currentRecord);
                                    inItem = false;
                                }
                                else if(tagName.equalsIgnoreCase("title")){
                                    currentRecord.setTitle(textValue);

                                }
                                else if(tagName.equalsIgnoreCase("link")){
                                    currentRecord.setLink(textValue);

                                }
                                else if(tagName.equalsIgnoreCase("pubDate")){
                                    currentRecord.setPubDate(textValue);

                                }
                            }

                            break;
                        default:
                            //Nothing else to do

                }
                eventType =xpp.next();
            }

        }catch (Exception e){
            status = false;
            e.printStackTrace();
        }


        for(PostsData post: posts){
            Log.d("ParseData", "#############");
            Log.d("ParseData", "Title: "+ post.getTitle());
            Log.d("ParseData", "Link: "+ post.getLink());
            Log.d("ParseData", "Publish Date: "+ post.getPubDate());
        }

        return true;

    }
}
