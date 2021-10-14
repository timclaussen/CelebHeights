/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.celebrityHeights.ServiceLayer;

import com.cs.celebrityHeights.data.CelebDbDao;
import com.cs.celebrityHeights.data.VisitorDbDao;
import com.cs.celebrityHeights.models.Celeb;
import com.cs.celebrityHeights.models.Visitor;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Timothy Claussen
 */
@Repository
public class ServiceLayerImpL implements ServiceLayer {

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    CelebDbDao celebDao;

    @Autowired
    VisitorDbDao visitorDao;

    Document doc;

    @Override
    public int validateHeightInput(String enteredString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//        Document doc = Jsoup.connect("https://www.google.com/search?q=mario").get();

    @Override
    public Celeb searchForCelebHeight(String celebname) throws CelebNotFoundException {
//        
//        List<Celeb> listCelebs = listAllCelebs();
//        for (Celeb listCeleb : listCelebs) {
//            if(listCeleb.getCelebName() == celebname)
//            {
//                //we already have that one!
//            }
//        }
        
        Celeb searchedCeleb = new Celeb();
        searchedCeleb.setCelebName(celebname);

        String query = celebname.trim().replace(" ", "+"); //remove any spaces
        String request = "https://www.google.com/search?q=" + query + "+height";
        searchedCeleb.setSearchUrl(request);
        try {
            doc = Jsoup.connect(request).userAgent(
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.81 Safari/537.36")
                    .get(); //Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)
//            if(doc.is)
//            doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
            Element height = doc.select("[data-attrid=kc:/people/person:height] > div").first();//<div class=\"Z0LcW XcVN5d\"> div.Z0LcW XcVN5d
            if (height == null) {

                //try a second type of fromat:
                height = doc.select("div.IZ6rdc").first();
                if (height == null) {
                    searchedCeleb = null;
                    new CelebNotFoundException("Search failed").printStackTrace();
//                throw 
                    return searchedCeleb;
                }
            }
            String strHeight = height.text(); //get the text inside the div we took
            String totInchHeight = convertGoogleStringToTotalInchesString(strHeight);
            searchedCeleb.setTotalInchesString(totInchHeight);
            //set the total inches string, and the total inches value of the new object
            searchedCeleb.setTotalInches(googleHeightStringToTotalInches(strHeight));

            //get the search URL
//            String searchedString = doc.;
//            searchedCeleb.setSearchUrl(searchedString);
            searchedCeleb.setDateSearched(LocalDateTime.now());

            //get the picture URL
//            JSONObject jsonObject;
//    for (Element element : elements) {
//        if (element.childNodeSize() > 0) {
//            jsonObject = (JSONObject) new JSONParser().parse(element.childNode(0).toString());
//            resultUrls.add((String) jsonObject.get("ou"));
//        }
//    }
            Elements picUrls = doc.select("g-img.BA0A6c"); //g-img.BA0A6c
//            String rawPicUrl = picUrl.html(); //gives us the whole <img id="*" src="*" etc>
//    Element pickedUrl = picUrls.select("img").first();
//            String rawPicUrl = pickedUrl.attr("src");
//            String picUrlSrc = stripHtmlToJustImgSrc(rawPicUrl);
            Element picUrl = picUrls.first();
            String strPicUrl = picUrl.select("img").attr("src");

//            searchedCeleb.setPhotoUrl(picUrl.attr("href"));
            searchedCeleb.setPhotoUrl(strPicUrl);

        } catch (IOException ex) {
            //Something happened... * TODO * 
            searchedCeleb = null;
            ex.printStackTrace();
//            throw new CelebNotFoundException("Celeb not found");
        }

        addCeleb(searchedCeleb);

        return searchedCeleb;
    }

    private int googleHeightStringToTotalInches(String gheight) {
        //6' 4"
//        String myString = convertGoogleStringToTotalInchesString(gheight);
        String myString = gheight;
        myString = myString.replace("\'", " "); //get rid of the '
        myString = myString.replace("\"", " "); //get rid of the trailing "
        myString = myString.replace("?", " ");
        String[] strTokens = myString.split(" ");
        for (String strToken : strTokens) {
            strToken = strToken.replaceAll("[^\\d.]", "");
        }
//        myString = myString.replaceAll("[^\\d.]", "");

        int totalInches;
        if (!strTokens[1].equals("ft")) {
            new CelebNotFoundException(myString).printStackTrace();

            totalInches = (Integer.parseInt(String.valueOf(strTokens[0].charAt(0))) * 12) + Integer.parseInt(strTokens[1].substring(0, strTokens[1].length() - 1)); //multiply feet by 12
        } else {
            // # ft # in
            totalInches = (Integer.parseInt(String.valueOf(strTokens[0].charAt(0))) * 12) + Integer.parseInt(String.valueOf(strTokens[2])); //multiply feet by 12
        }
        return totalInches;
    }

    private String convertGoogleStringToTotalInchesString(String gheight) {

        String myString = gheight.trim();//trim the spaces
//        myString = myString.replace(" ", ""); //getrid of inside spaces
//        myString = myString.replace("\'", " "); //get rid of the '
        //returns a "6' 4""
        return myString;

    }

    private String stripHtmlToJustImgSrc(String html) {
        //expecting a long string of <img id="*" src="*"
        String[] myStrArr = html.split(" "); //splits by spaces
        String myStr = "";
        for (String str : myStrArr) {
            //get the first src="*"
            if (str.substring(0, 3).contains("src=")) {
                myStr = str;
            }
        }
        //remove the src= and the "
        myStr = myStr.replace("src=", "");
        myStr = myStr.replace("\"", "");

        return myStr;
    }

    @Override
    public Celeb insertSearchedCelebToTable(Celeb celeb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Celeb> populateCarousel() {
        List<Celeb> fullList = celebDao.listAllCelebs();
        List<Celeb> outList = new ArrayList<Celeb>();
        int rand;
        Random random = new Random();
        if (fullList.size() > 8) {
            for (Celeb celeb : fullList) {
                rand = random.nextInt(3) + 1;
                if (rand % 2 == 0) {
                    outList.add(celeb);
                }
            }
            return outList;
        } else {
            return fullList;
        }
    }

    @Override
    public String inchesToFeetInches(int inches) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int cmToInches(int cm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Visitor> getAllVisitors() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Visitor getVisitorById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Visitor addVisitor(Visitor visit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Visitor editVisitor(Visitor visit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeVisitorById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Celeb> listAllCelebs() {
        return celebDao.listAllCelebs();
    }

    @Override
    public Celeb getCelebById(int id) throws CelebNotFoundException{
        Celeb celeb = celebDao.getCelebById(id);
        if(celeb == null)
        {
            throw new CelebNotFoundException("invalid id");
        }
        return celeb;
    }

    @Override
    public Celeb addCeleb(Celeb celeb) {
        if (celeb == null) {
            //celeb not found!!
        }
        return celebDao.addCeleb(celeb);
    }

    @Override
    public Celeb editCeleb(Celeb celeb) {
        
        if(celeb.getTotalInches() < 1)
        {
            celeb.setTotalInches(2);
        }
        
        return celebDao.editCeleb(celeb);
    }

    @Override
    public void removeCelebById(int id) {
        celebDao.removeCelebById(id);
    }

}
