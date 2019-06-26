package com.licenta.project.business.recommandation_system;

import com.licenta.project.business.dto.ArticleDTO;
import com.licenta.project.business.dto.UserDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Recommender {


    public double predictRating(ArrayList<UserDTO> allUsers , UserDTO user, String articleId){
        ArrayList<UserDTO> neighborhood = createNeighborhood(allUsers, user);
        double prediction = createPrediction(neighborhood, articleId);
        return prediction;
    }


    private double createPrediction(ArrayList<UserDTO> neighborhood, String articleId){
        double sumOfRatings = 0;
        double totoalNumberOfArticles = 0;

        for(UserDTO user: neighborhood){
            HashMap<String, Integer> userRatings = user.getArticleRatings();
            if(userRatings.containsKey(articleId)){
                sumOfRatings += userRatings.get(articleId);
                totoalNumberOfArticles++;
            }
        }
        return (sumOfRatings / totoalNumberOfArticles);
    }


    private ArrayList<UserDTO> createNeighborhood(List<UserDTO> allUsers, UserDTO activeUser){

        ArrayList<UserDTO> neighborhood = new ArrayList<>();

        for(UserDTO otherUser: allUsers){
            if(!otherUser.getId().equals(activeUser.getId())){
                double similarity = calculateSimilarity(activeUser, otherUser);
                if(similarity < 1){
                    neighborhood.add(otherUser);
                }
            }
        }
        return neighborhood;
    }

    private double calculateSimilarity(UserDTO activeUser, UserDTO otherUser) {
        int articlesInCommon = 0;
        double differenceSum = 0;

        for(Map.Entry<String, Integer> h1 : activeUser.getArticleRatings().entrySet()){
            for(Map.Entry<String, Integer> h2 : otherUser.getArticleRatings().entrySet()){
                String a1 = h1.getKey();
                String a2 = h2.getKey();

                if(a1.equals(a2)){
                    articlesInCommon++;
                    differenceSum += Math.abs(h1.getValue() - h2.getValue());
                }
            }
        }
        if(articlesInCommon > 0) return (differenceSum / articlesInCommon);
        return Integer.MAX_VALUE; //user does not match so is filtered out
    }

}
