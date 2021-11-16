package Model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Card {
    private List<FoodBean> cardFoodList;
    private int totalCount;
    private BigDecimal totalCost;

    public Card() {
        cardFoodList = new ArrayList<FoodBean>();
        totalCount = 0;
        totalCost = BigDecimal.valueOf(0, 0);
    }

    public List<FoodBean> getCard() {
        return cardFoodList;
    }

    public void addToCard(FoodBean fb) {
        Iterator iterator = cardFoodList.iterator();
        while (iterator.hasNext()) {
            FoodBean fbitem = (FoodBean) iterator.next();
            if (fbitem.getFoodId() == fb.getFoodId()) {
                iterator.remove();
            }
        }
        cardFoodList.add(fb);
    }

    public int getTotalCount() {
        int total = 0;
        for (int i = 0; i < cardFoodList.size(); i++) {
            total += cardFoodList.get(i).getCount();
        }
        return total;
    }

    public BigDecimal getTotalCost() {
        BigDecimal total = BigDecimal.valueOf(0);

        Iterator iterator = cardFoodList.iterator();
        while (iterator.hasNext()) {
            FoodBean ftItem = (FoodBean) iterator.next();
            total = total.add(ftItem.getPrice().
                    multiply(BigDecimal.valueOf(ftItem.getCount())));
        }
        return total;
    }

}
