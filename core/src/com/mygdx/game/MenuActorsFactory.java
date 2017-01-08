package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class MenuActorsFactory {

    private Integer width;
    private Integer height;
    private ArrayList<Actor> actors;
    private final Stage stage;
    private final Table table;
    private final int space;

    public MenuActorsFactory(Stage stage) {
        this(stage,500,60,3);
    }

    public MenuActorsFactory(Stage stage, Integer width, Integer height, int space) {
        this.width = width;
        this.height = height;
        actors = new ArrayList<Actor>();
        this.stage = stage;
        this.table = new Table();
        this.table.center();
        this.stage.addActor(this.table);
        this.table.setColor(Color.RED);
        this.space = space;
    }

    public void addActor(Actor actor, EventListener listener) {
        this.addActor(actor, listener, this.width, this.height);
    }

    public void addActor(Actor actor, EventListener listener, Integer customWidth, Integer customHeight) {
        customWidth = (customWidth == null) ? this.width : customWidth;
        customHeight = (customHeight == null) ? this.height : customHeight;
        if(listener!=null) actor.addListener(listener);
        this.actors.add(actor);
        this.table.add(actor).pad(this.space).width(customWidth).height(customHeight).center();
        this.table.row();
        this.updateView();
    }

    private void updateView() {
        this.table.pack();
        this.table.setY((Gdx.graphics.getHeight()-this.table.getHeight())/2);
        this.table.setX((Gdx.graphics.getWidth()-this.table.getWidth())/2);
    }

    public ArrayList<Actor> getActors() {
        return actors;
    }

    public void act(float delta) {
        for(int i=0 ; i<this.actors.size() ; ++i) {
            this.actors.get(i).act(delta);
        }
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }


}