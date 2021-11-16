package Model;

public class Shop {
    private String shopName;
    private String shopDesc;
    private int pic;
    public Shop(){

    }

    public  Shop(String shopName, String shopDesc, int pic) {
        this.shopName = shopName;
        this.shopDesc = shopDesc;
        this.pic = pic;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopDesc() {
        return shopDesc;
    }

    public void setShopDesc(String shopDesc) {
        this.shopDesc = shopDesc;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "shopName='" + shopName + '\'' +
                ", shopDesc='" + shopDesc + '\'' +
                ", pic=" + pic +
                '}';
    }
}
