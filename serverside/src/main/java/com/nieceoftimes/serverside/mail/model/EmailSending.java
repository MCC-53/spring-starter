package com.nieceoftimes.serverside.mail.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailSending {
    private String emailReceiver;
    private String emailSubject;
    private String emailText;
}
