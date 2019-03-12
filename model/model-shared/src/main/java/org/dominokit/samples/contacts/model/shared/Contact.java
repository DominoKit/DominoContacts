package org.dominokit.samples.contacts.model.shared;

import org.dominokit.domino.api.shared.request.RequestBean;
import org.dominokit.domino.api.shared.request.ResponseBean;
import org.dominokit.jacksonapt.annotation.JSONMapper;

import java.util.HashSet;
import java.util.Set;

@JSONMapper
public class Contact implements ResponseBean, RequestBean {

    private String _id;
    private Gender gender;
    private Name name;
    private String email;
    private String phone;
    private Picture picture;
    private boolean favourite;
    private Set<String> groups = new HashSet<>();

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public Set<String> getGroups() {
        return groups;
    }

    public void setGroups(Set<String> groups) {
        this.groups = groups;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "gender=" + gender +
                ", name=" + name +
                ", favourite=" + favourite +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", picture=" + picture +
                '}';
    }
}
