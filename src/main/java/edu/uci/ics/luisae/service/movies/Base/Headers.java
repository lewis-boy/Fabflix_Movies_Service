package edu.uci.ics.luisae.service.movies.Base;

public class Headers {
    private String email;
    private String session_id;
    private String transaction_id;
    public Headers(){}
    public Headers(String email, String session_id, String transaction_id){
        this.email = email;
        this.session_id = session_id;
        this.transaction_id = transaction_id;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
