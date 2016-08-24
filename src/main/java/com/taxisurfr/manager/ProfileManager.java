package com.taxisurfr.manager;

import com.taxisurfr.domain.Profile;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class ProfileManager extends AbstractDao<Profile>{

    @Inject
    Logger logger;

    public ProfileManager() {
        super(Profile.class);;

    }


    public Profile getProfile() {
        List<Profile> all = findAll();
        for (Profile profil : findAll()){
                return profil;
        }
        return null;
    }
}
