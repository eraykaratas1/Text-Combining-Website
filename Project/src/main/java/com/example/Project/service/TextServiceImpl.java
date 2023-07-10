package com.example.Project.service;

import com.example.Project.model.Text;
import com.example.Project.repository.TextRepository;
import com.example.Project.service.TextService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TextServiceImpl implements TextService{

    private final TextRepository textRepository;

    @Override
    public List<Text> getShowTexts() {
        return textRepository.findAll();
    }

    @Override
    public Text SaveText(Text text) {

        return textRepository.save(text);
    }

}