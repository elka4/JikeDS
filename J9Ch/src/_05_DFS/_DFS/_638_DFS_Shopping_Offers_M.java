package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _638_DFS_Shopping_Offers_M {

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int result = Integer.MAX_VALUE;
        //apply each offer to the needs, and recurse
        for(int i = 0; i < special.size(); i++) {
            List<Integer> offer = special.get(i);
            boolean invalidOffer = false;
            for(int j = 0; j < needs.size(); j++) { // subtract offer items from needs
                int remain = needs.get(j) - offer.get(j);
                needs.set(j, remain);
                if(!invalidOffer && remain < 0) invalidOffer = true; // if offer has more items than needs
            }
            if(!invalidOffer) { //if valid offer, add offer price and recurse remaining needs
                result = Math.min(result, shoppingOffers(price, special, needs) + offer.get(needs.size()));
            }
            for(int j = 0; j < needs.size(); j++) { // reset the needs
                int remain = needs.get(j) + offer.get(j);
                needs.set(j, remain);
            }
        }
        // choose b/w offer and non offer
        int nonOfferPrice = 0;
        for(int i = 0; i < needs.size(); i++) {
            nonOfferPrice += price.get(i) * needs.get(i);
        }
        return Math.min(result, nonOfferPrice);
    }


    public int shoppingOffers2(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int result = Integer.MAX_VALUE;
        //apply each offer to the needs, and recurse
        for(int i = 0; i < special.size(); i++) {
            List<Integer> offer = special.get(i);
            boolean invalidOffer = false;
            int offerCount = Integer.MAX_VALUE; // number of times offer can be applied
            for(int j = 0; j < needs.size(); j++) { // pre-compute number of times offer can be called
                int remain = needs.get(j) - offer.get(j);
                if(!invalidOffer && remain < 0) invalidOffer = true; // if offer has more items than needs
                if(offer.get(j) > 0)
                    offerCount = Math.min(offerCount, needs.get(j)/offer.get(j));
            }
            for(int j = 0; j < needs.size(); j++) { // subtract offer items from needs
                int remain = needs.get(j) - offer.get(j) * offerCount;
                needs.set(j, remain);
            }
            if(!invalidOffer) { //if valid offer, add offer price and recurse remaining needs
                result = Math.min(result, shoppingOffers2(price, special, needs) + (offerCount * offer.get(needs.size())));
            }

            for(int j = 0; j < needs.size(); j++) { // reset the needs
                int remain = needs.get(j) + offer.get(j) * offerCount;
                needs.set(j, remain);
            }
        }

        // choose b/w offer and non offer
        int nonOfferPrice = 0;
        for(int i = 0; i < needs.size(); i++) {
            nonOfferPrice += price.get(i) * needs.get(i);
        }
        return Math.min(result, nonOfferPrice);
    }


    public int shoppingOffers3(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int result = Integer.MAX_VALUE;
        int k = needs.size();
        for(int i=0;i<special.size();i++){
            List<Integer> spe = special.get(i);
            int isValidSpe = -1;

            for(int j=0;j<k;j++){
                if(needs.get(j)<spe.get(j)){ isValidSpe = j; break;}
                needs.set(j,needs.get(j)-spe.get(j));
            }
            if(isValidSpe==-1){
                result = Math.min(result,spe.get(spe.size()-1)+shoppingOffers3(price,special,needs));
                isValidSpe = k;
            }

            for(int j=0;j<isValidSpe;j++){
                needs.set(j,needs.get(j)+spe.get(j));
            }
        }
        int noSpePri = 0;
        for(int i=0;i<k;i++)
            noSpePri += needs.get(i)*price.get(i);

        return Math.min(noSpePri,result);
    }
//----------------------------------------------------------------------------




//----------------------------------------------------------------------------






}
/*

 */