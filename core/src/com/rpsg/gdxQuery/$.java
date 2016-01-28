package com.rpsg.gdxQuery;

import java.util.Collection;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class $ {
	
	public static GdxQuery add (Object... a){
		return new GdxQuery(a);
	}
	
	public static Texture getTexture (Drawable d){
		if(d instanceof TextureRegionDrawable)
			return ((TextureRegionDrawable)d).getRegion().getTexture();
		return null;
	}
	
	public static GdxQuery image (String filePath){
		return image(new Texture(Gdx.files.internal(filePath)));
	}
	
	public static GdxQuery image (String filePath,AssetManager manager){
		if(!manager.isLoaded(filePath)){
			manager.load(filePath, Texture.class);
			while(manager.update());
		}
		return image((Texture)manager.get(filePath));
	}
	
	public static GdxQuery image (Texture texture){
		return new GdxQuery(new Image(texture));
	}
	
	public static GdxFrame frame(){
		return new GdxFrame();
	}
	
	public static GdxFrame add(GdxQuery query,GdxQueryRunnable runnable){
		return new GdxFrame().add(query, runnable);
	}
	
	public static <T> void removeIfIf(boolean flag,Iterable<T> c,RemoveTest<T> test){
		if(flag)
			removeIf(c,test);
	}
	
	public static <T> void removeIf(Iterable<T> c,RemoveTest<T> test){
		Iterator<T> it=c.iterator();
		while(it.hasNext()){
			T obj=it.next();
			if(test.test(obj))
				it.remove();
		}
	}
	
	public static <T> void each(Iterable<T> c,CustomRunnable<T> test){
		Iterator<T> it=c.iterator();
		while(it.hasNext())
			test.run(it.next());
	}

	
}