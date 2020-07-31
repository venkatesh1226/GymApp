package com.example.gymapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Utils {
  static Context context;
    public Utils(Context context) {
        this.context=context;
    }

    static ArrayList<Name> allTraining;
   static ArrayList<Training> myTraining=new ArrayList<>();
   static ArrayList<String> dayList;
    public static ArrayList<Name> initAllTraining(){
        if(null==allTraining)
            allTraining=new ArrayList<Name>();
        if(Utils.allTraining.size()>0) allTraining.clear();
        String str=" Skipping is a full body workout which uses your abdominals to stabilise the body, legs for jumping, shoulders and arms for turning the rope. Skipping \"improves coordination, stamina and focus,\" says Virgin Active Master trainer, Dorota Maslewska. And that's good for all aspects of life.";
        allTraining.
                add(new Name("https://media.istockphoto.com/photos/best-cardio-workout-ever-picture-id494754904","Skipping","Skipping is used to do warmup before doing any excercises"+str));
       str="Jogging is a form of trotting or running at a slow or leisurely pace. The main intention is to increase fitness without stress. Jogging is a \"high-impact\" exercise that places strain on the body, notably the joints of the knee.";
        allTraining.add(new Name("https://cdn.britannica.com/11/145611-050-1FF37FF3/Jogging.jpg","Jogging",str));
        str="push-up is an exercise done laying with face, palms and toes facing down, keeping legs and back straight, extending arms straight to push body up and back down again. An example of a push-up is a great exercise that works the chest, shoulder and arm muscles.";
        allTraining.add(new Name("https://assets.gqindia.com/photos/5cee7eb00379a73d25177759/16:9/w_1920%2cc_limit/Pushup.jpg","PushUps",str));
        str="A pull-up is an upper-body strength exercise. The pull-up is a closed-chain movement where the body is suspended by the hands and pulls up. As this happens, the elbows flex and the shoulders adduct and extend to bring the elbows to the torso.";
        allTraining.add(new Name("https://www.muscleandfitness.com/wp-content/uploads/2018/06/pullup-1-1109.jpg?w=940&h=529&crop=1","PullUps",str));
       str="A squat is a strength exercise in which the trainee lowers their hips from a standing position and then stands back up. During the descent of a squat, the hip and knee joints flex while the ankle joint dorsiflexes; conversely the hip and knee joints extend and the ankle joint plantarflexes when standing up.";
        allTraining.add(new Name("https://3vnqw32fta3x1ysij926ljs3-wpengine.netdna-ssl.com/wp-content/uploads/2017/03/Squat.jpg","Squats",str));
       str="The biceps is a muscle on the front part of the upper arm. The biceps includes a “short head” and a “long head” that work as a single muscle. The biceps is attached to the arm bones by tough connective tissues called tendons.";
        allTraining.add(new Name("https://www.bodybuilding.com/images/2017/june/the-back-and-biceps-workout-that-went-global-header-v2-830x467.jpg","BiCeps",str));
        str="Stand up and face away from the bench, grabbing it with both hands at shoulder-width. Keeping your legs straight and extended in front of you, slowly lower your body until your arms and forearms are at 90-degree angles. Push through at the triceps as you lift back to the starting position. Repeat.";
        allTraining.add(new Name("https://www.thetrendspotter.net/wp-content/uploads/2020/03/Best-Triceps-Workouts.jpg","Triceps",str));
       str="Grasp the bar with your thumbs on the outside of your closed fist, overhand grip, with arms slightly wider than shoulder-width apart. The angle of the upper arms should be at about 45 degrees to the body. Remove the barbell from the rack and lock the elbows out before lowering the bar to the chest at the nipple line.";
        allTraining.add(new Name("https://nl7if2hjk9a2r1cql2qih3id-wpengine.netdna-ssl.com/wp-content/uploads/article-ath-benchpress.jpg","BenchPress",str));

    return allTraining;
    }
    public static void addMyTraining(Training training){

            myTraining.add(training);

            shared();

    }

    public static void updateMyTrainingAcomplish(Training training){
        for(int i=0;i<myTraining.size();i++){
            if(myTraining.get(i)==training)
                myTraining.get(i).setAcomplished(true);
        }
        shared();

    }
    public static void deleteMyTraining(Training training){
        for(int i=0;i<myTraining.size();i++){
            if(myTraining.get(i)==training)
               myTraining.remove(training);
        }
        shared();
    }

    public static void shared(){
        SharedPreferences mprefs=context.getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        SharedPreferences.Editor editor=mprefs.edit();
        Gson gson=new Gson();
        String json=gson.toJson(myTraining);
        editor.putString("MYTRAINING",json);
        editor.commit();

    }
    public static ArrayList<Training> getMyTraining(){
        SharedPreferences mprefs=context.getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        Gson gson=new Gson();
        String json=mprefs.getString("MYTRAINING",null);
        Type type=new TypeToken<ArrayList<Training>>(){}.getType();
        ArrayList<Training> train=gson.fromJson(json,type);
        if(train!=null)
            myTraining=train;
        return myTraining;
    }
    public static ArrayList<String> getDayList(){
        dayList=new ArrayList<>();
        if(dayList.size()!=0)
            dayList.clear();
        dayList.add("Monday");
        dayList.add("TuesDay");
        dayList.add("WednesDay");
        dayList.add("ThursDay");
        dayList.add("FriDay");
        dayList.add("SaturDay");
        dayList.add("SunDay");
        return dayList;
    }


}
