package com.example.deliveryapp;

public class Edit {
    private String ID;
   private String DeliverAddress;
   private Integer contact;
     private Integer qty;

    public Edit() {
    }

public String getID(){
        return ID;
}
public void setID(String id){
        ID=id;
}
    public String getDeliverAddress() {
        return DeliverAddress;
    }

    public void setDeliverAddress(String deliverAddress) {
        DeliverAddress = deliverAddress;
    }

    public Integer getContact() {
        return contact;
    }

    public void setContact(Integer contact) {
        this.contact = contact;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}