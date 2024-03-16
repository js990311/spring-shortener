package com.toyproject.shortener.controller;


import com.toyproject.shortener.dto.form.CreateUrlForm;
import com.toyproject.shortener.dto.response.ResponseStatus;
import com.toyproject.shortener.dto.response.ResponseWrapper;
import com.toyproject.shortener.dto.response.ShortUrlResponse;
import com.toyproject.shortener.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CreateShortUrlController {

    private final UrlService urlService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("urlForm", new CreateUrlForm());
        return "index";
    }

    @PostMapping("/create")
    @ResponseBody
    public ResponseWrapper<List<ShortUrlResponse>> createShortUrl(@RequestBody CreateUrlForm url){
        List<ShortUrlResponse> urlResponses = urlService.createUrls(url.getUrls());
        ResponseWrapper<List<ShortUrlResponse>> response = new ResponseWrapper<>(ResponseStatus.Success, urlResponses);
        return response;
    }
}
