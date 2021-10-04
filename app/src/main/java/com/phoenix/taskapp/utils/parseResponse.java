package com.phoenix.taskapp.utils;

import android.content.Context;

import com.phoenix.taskapp.classes.IndexClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class parseResponse {

    public String parseJSON(Context context) {
        String response = null;
        InputStream stream = null;
        try {
            stream = context.getAssets().open("response.json");
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            response = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }

    public HashMap<String, List<String>> parseCollection(Context context) throws JSONException {
        HashMap<String, List<String>> hashMap = new HashMap<>();
        JSONObject jsonObject = new JSONObject(parseJSON(context));
        JSONObject resultObject = jsonObject.getJSONObject("result");
        JSONObject collectionObject = resultObject.getJSONObject("collections");

        JSONArray smartArray = collectionObject.getJSONArray("smart");
        for(int i = 0; i < smartArray.length(); i++)
        {
            JSONObject outputObject = smartArray.getJSONObject(i);
            String label = outputObject.getString("label");
            JSONArray outputArray = outputObject.getJSONArray("courses");
            List<String> courseIds = new ArrayList<>();

            for(int j = 0; j < outputArray.length(); j++)
            {

                String courseId = outputArray.get(j).toString();
                courseIds.add(courseId);
            }

            hashMap.put(label, courseIds);
        }

        return hashMap;
    }

    public List<IndexClass> parseIndex(Context context) throws JSONException {
        List<IndexClass> indexData = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(parseJSON(context));
        JSONObject resultObject = jsonObject.getJSONObject("result");

        JSONArray indexArray = resultObject.getJSONArray("index");
        for(int i = 0; i < indexArray.length(); i++)
        {
            JSONObject indexObject = indexArray.getJSONObject(i);

            List<String> styleList = new ArrayList<>();
            List<String> skillList = new ArrayList<>();
            List<String> seriesList = new ArrayList<>();
            List<String> curriculumList = new ArrayList<>();

            JSONArray styleArray = indexObject.getJSONArray("style_tags");
            JSONArray skillArray = indexObject.getJSONArray("skill_tags");
            JSONArray seriesArray = indexObject.getJSONArray("series_tags");
            JSONArray curriculumArray = indexObject.getJSONArray("curriculum_tags");

            for(int style = 0; style < styleArray.length(); style++)
            {
                String styleType = styleArray.getString(style);
                styleList.add(styleType);
            }

            for(int skill = 0; skill < skillArray.length(); skill++)
            {
                String skillType = skillArray.getString(skill);
                skillList.add(skillType);
            }

            for(int series = 0; series < seriesArray.length(); series++)
            {
                String seriesType = seriesArray.getString(series);
                seriesList.add(seriesType);
            }

            for(int curriculum = 0; curriculum < curriculumArray.length(); curriculum++)
            {
                String curriculumType = curriculumArray.getString(curriculum);
                curriculumList.add(curriculumType);
            }

            IndexClass indexClass = new IndexClass(indexObject.getString("downloadid"),
                    indexObject.getString("cd_downloads"),
                    indexObject.getString("id"),
                    indexObject.getString("title"),
                    indexObject.getString("status"),
                    indexObject.getString("release_date"),
                    indexObject.getString("authorid"),
                    indexObject.getString("video_count"),
                    styleList,
                    skillList,
                    seriesList,
                    curriculumList,
                    indexObject.getString("educator"),
                    indexObject.getString("owned"),
                    indexObject.getString("sale"),
                    indexObject.getString("purchase_order"),
                    indexObject.getString("watched"),
                    indexObject.getString("progress_tracking"));

            indexData.add(indexClass);
        }
        return indexData;
    }
}
