package edu.nju.stories.service.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import edu.nju.stories.service.MailService;
import org.springframework.beans.factory.annotation.Value;

public class MailServiceImpl implements MailService {




    @Override
    public void sendEmail(String email, String subject, String htmlBody) {

    }
}
