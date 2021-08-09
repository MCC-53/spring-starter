/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas.com.security.models;

/**
 *
 * @author putug
 */
public class EmailToSend {
    private String to;
    private String subject;
    private String text;

    public EmailToSend(String to, String subject, String text) {
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    public EmailToSend() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
