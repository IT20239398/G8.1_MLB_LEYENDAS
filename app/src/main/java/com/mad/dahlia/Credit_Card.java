package com.mad.dahlia;

public class Credit_Card {
    String cardNo, expire;
    int cvc;

    public Credit_Card() {

    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }

    public String getCardNo() {
        return cardNo;
    }

    public String getExpire() {
        return expire;
    }

    public int getCvc() {
        return cvc;
    }
}
