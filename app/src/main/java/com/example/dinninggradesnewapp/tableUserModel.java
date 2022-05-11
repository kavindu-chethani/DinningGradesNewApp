package com.example.dinninggradesnewapp;


public class tableUserModel {
        String name,contactno,nic,email;


        public tableUserModel(){}

        public tableUserModel(String name, String contactno, String nic, String email) {
            this.name = name;
            this.contactno = contactno;
            this.nic = nic;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContactno() {
            return contactno;
        }

        public void setContactno(String contactno) {
            this.contactno = contactno;
        }

        public String getNic() {
            return nic;
        }

        public void setNic(String nic) {
            this.nic = nic;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

