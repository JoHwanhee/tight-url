package ga.tight.shortenurl.shorten.controller;

import ga.tight.shortenurl.shorten.domain.ShortenUrl;
import ga.tight.shortenurl.shorten.dto.request.CreateShorten;
import ga.tight.shortenurl.shorten.dto.response.ResponseQueryShorten;
import ga.tight.shortenurl.shorten.dto.response.ResponseRegisterShorten;
import ga.tight.shortenurl.shorten.service.ShortenQueryService;
import ga.tight.shortenurl.shorten.service.ShortenRegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.websocket.server.PathParam;

@Controller
public class ShortenController {

    private final ShortenRegisterService shortenRegisterService;
    private final ShortenQueryService shortenQueryService;

    public ShortenController(ShortenRegisterService shortenRegisterService,
                             ShortenQueryService shortenQueryService) {
        this.shortenRegisterService = shortenRegisterService;
        this.shortenQueryService = shortenQueryService;
    }

    @GetMapping("/{tag}")
    public RedirectView redirectUrl(
            @PathVariable("tag") String tag
    ) {
        ResponseQueryShorten url = shortenQueryService.findByTag(tag);
        RedirectView redirectView = new RedirectView(url.getFullUrl());
        redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return redirectView;
    }

    @PostMapping("/api/shorten-urls")
    @ResponseBody
    public ResponseRegisterShorten postUrls(
            @RequestBody() CreateShorten body
    ) {
        return shortenRegisterService.register(body.getUrl());
    }
}
