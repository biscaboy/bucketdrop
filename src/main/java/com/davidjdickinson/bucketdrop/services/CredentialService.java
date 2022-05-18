package com.davidjdickinson.bucketdrop.services;

import com.davidjdickinson.bucketdrop.mapper.CredentialMapper;
import com.davidjdickinson.bucketdrop.model.Credential;
import com.davidjdickinson.bucketdrop.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CredentialService implements CloudStorageService {

    private CredentialMapper credentialMapper;
    private EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public boolean updateCredential(Credential credential){
        credential = encryptPassword(credential);
        return this.credentialMapper.update(credential) == 1;
    }

    public boolean createCredential(Credential credential) {
        credential = encryptPassword(credential);
        return credentialMapper.insert(credential) == 1;
    }

    public Credential getCredential(Integer id) {
        return credentialMapper.getCredential(id);
    }

    public ArrayList<Credential> getCredentials(User user){
        return credentialMapper.getCredentials(user);
    }

    public boolean deleteCrediential(Credential crediential){
        return credentialMapper.delete(crediential) == 1;
    }

    public String decryptPassword(Credential c) {
        return encryptionService.decryptValue(c.getPassword(), c.getKey());
    }

    private Credential encryptPassword(Credential c) {
        if (c.getKey() == null) {
            c.setKey(encryptionService.generateKey());
        }
        String encryptedPassword = encryptionService.encryptValue(c.getPassword(), c.getKey());
        c.setPassword(encryptedPassword);
        return c;
    }

    @Override
    public String getCollectionName() {
        return "credentials";
    }
}
