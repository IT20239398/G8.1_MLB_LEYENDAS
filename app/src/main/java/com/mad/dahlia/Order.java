package com.mad.dahlia;

public class Order {
    String Name, Address, Email, contactNo, Type;
    String code, color, price, quantity;
    Double Charge, Discount, Subtotal, TotalAmount;
    Credit_Card card = new Credit_Card();

    public Order() {
    }

    public void setName(String name) {
        Name = name;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setCharge(Double charge) {
        Charge = charge;
    }

    public void setDiscount(Double discount) {
        Discount = discount;
    }

    public void setSubtotal(Double subtotal) {
        Subtotal = subtotal;
    }

    public void setTotalAmount(Double totalAmount) {
        TotalAmount = totalAmount;
    }

    public void setCard(Credit_Card card) {
        this.card = card;
    }

    public String getName() {
        return Name;
    }

    public String getAddress() {
        return Address;
    }

    public String getEmail() {
        return Email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getType() {
        return Type;
    }

    public String getCode() {
        return code;
    }

    public String getColor() {
        return color;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Double getCharge() {
        return Charge;
    }

    public Double getDiscount() {
        return Discount;
    }

    public Double getSubtotal() {
        return Subtotal;
    }

    public Double getTotalAmount() {
        return TotalAmount;
    }

    public Credit_Card getCard() {
        return card;
    }
}
