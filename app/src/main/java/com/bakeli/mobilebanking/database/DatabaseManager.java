package com.bakeli.mobilebanking.database;

import com.bakeli.mobilebanking.models.Account;
import com.bakeli.mobilebanking.models.User;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Lamine Dieng on 12/06/2017.
 */

public class DatabaseManager {
    Realm realm = Realm.getDefaultInstance();
    //Realm realm;

    //Ajout d'un nouveau user
    public void addUser(String login, String password, String name, String nom) {
        realm.beginTransaction();
        User u = realm.createObject(User.class, login+password);
        //UUID.randomUUID().toString()
        //u.setId(login+password);
        u.setLogin(login);
        u.setPassword(password);
        u.setName(name);
        u.setNom(nom);
        realm.commitTransaction();

    }

    public void addAccount(String type, String solde, String id) {
        realm.beginTransaction();
        Account a = realm.createObject(Account.class, id);
        //u.setId(UUID.randomUUID().toString());
       //a.setId(id);
        a.setSolde(solde);
        a.setType(type);
        realm.commitTransaction();

    }

    //Authentification
    public Boolean login(String login1, String password1)
    {
        Boolean found = false;

        User user = realm.where(User.class).equalTo("login", login1).equalTo("password", password1).findFirst();
        if(user!=null)
        {
            found = true;
        }
        return found;
    }

    //Liste des users
    public RealmResults<User> getAllUsers() {
        RealmResults<User> results = realm.where(User.class).findAll();
        return results;
    }

    //Liste des users
    public RealmResults<Account> getAllAccounts() {
        RealmResults<Account> result2 = realm.where(Account.class).findAll();
        return result2;
    }
}
