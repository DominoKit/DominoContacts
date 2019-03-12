package org.dominokit.samples.contacts.model.shared;

public class Name {

    private String title;
    private String first;
    private String last;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String asString(){
        return capitalizeFirst(title)+". "+capitalizeFirst(first)+" "+capitalizeFirst(last);
    }

    private String capitalizeFirst(String input){
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
}
