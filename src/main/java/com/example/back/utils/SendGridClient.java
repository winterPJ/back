package com.example.back.utils;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SendGridClient {

    @Value("${sendgrid.api.key}")
    private String SENDGRID_API_KEY;
    @Value("${sendgrid.template.id}")
    private String TEMPLATE_ID;

    public boolean sendEmail(String toEmail, String auth_code)  {
        try {
            SendGrid sg = new SendGrid(SENDGRID_API_KEY);
            Request request = new Request();

            Email from = new Email("mongeulproject@gmail.com", "MONG");
            Email to = new Email(toEmail);
            Mail mail = new Mail();
            mail.setFrom(from);
            mail.setTemplateId(TEMPLATE_ID);

            Personalization personalization = new Personalization();
            personalization.addTo(to);
            personalization.addDynamicTemplateData("auth_code", auth_code);
            mail.addPersonalization(personalization);

            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);

            return response.getStatusCode() == 202;
        }catch (IOException e) {
            return false;
        }
    }
}
