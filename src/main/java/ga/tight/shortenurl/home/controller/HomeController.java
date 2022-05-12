package ga.tight.shortenurl.home.controller;

import ga.tight.shortenurl.home.dto.RequestRegisterUrlForm;
import ga.tight.shortenurl.shorten.dto.response.ResponseRegisterShorten;
import ga.tight.shortenurl.shorten.service.ShortenRegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    private final ShortenRegisterService shortenRegisterService;

    public HomeController(ShortenRegisterService shortenRegisterService) {
        this.shortenRegisterService = shortenRegisterService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("/home/shorten-urls")
    public String post(
            HttpServletRequest request,
            RequestRegisterUrlForm form,
            Model model)
    {
        ResponseRegisterShorten shorten = shortenRegisterService.register(form.getUrl());

        String baseUrl = request.getRequestURL()
                .toString()
                .replace("/home/shorten-urls", "")
                .replace("http://", "https://");
        String url =  baseUrl + "/" + shorten.getTag();
        model.addAttribute("url",url);
        return "home";
    }
}
