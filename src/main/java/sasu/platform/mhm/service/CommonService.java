package sasu.platform.mhm.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface CommonService {
    Map<String,byte[]> verificationCode();

    Map<String, String> login(String username, String password, String verificationCode, String key);
}
