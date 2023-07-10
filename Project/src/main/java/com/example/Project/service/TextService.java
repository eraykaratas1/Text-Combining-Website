package com.example.Project.service;


import com.example.Project.model.Text;
import org.bson.json.JsonObject;

import java.util.List;

public interface TextService{

    List<Text> getShowTexts();
    Text SaveText(Text text);





}