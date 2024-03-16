package com.toyproject.shortener.dto.form;

import lombok.Getter;

import java.util.List;

@Getter
public class CreateUrlForm {
    private List<String> urls;
}
