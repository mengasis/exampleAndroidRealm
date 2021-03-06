package com.mengasis.manuel.exampleandroidrealm.Services;

import com.mengasis.manuel.exampleandroidrealm.Models.Hero;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by manuel on 19-04-17.
 */

public class HeroService {

    private Realm realm;

    public HeroService(Realm realm) {
        this.realm = realm;
    }

    //CREATE
    public void createHero(String name){

        realm.beginTransaction();
        int lastId;

        if(!realm.isEmpty()){
            lastId = realm.where(Hero.class).max("id").intValue();
        }
        else{
            lastId = 0;
        }

        int nextID = lastId + 1;

        Hero hero = realm.createObject(Hero.class, nextID);

        hero.setName(name);

        realm.commitTransaction();

    }

    //READ
    public Hero[] getHeroes(){

        RealmResults<Hero> results = realm.where(Hero.class).findAll();

        return results.toArray(new Hero[results.size()]);
    }

    public Hero getHero(int id){
        Hero hero = realm.where(Hero.class).equalTo("id",id).findFirst();

        return hero;
    }

    //UPDATE

    public void updateHero(Hero hero, String name){

        realm.beginTransaction();
        hero.setName(name);
        realm.commitTransaction();

    }


    //DELETE
    public void deleteHero(int id){

        Hero hero = getHero(id);

        realm.beginTransaction();
        hero.deleteFromRealm();
        realm.commitTransaction();
    }
}
