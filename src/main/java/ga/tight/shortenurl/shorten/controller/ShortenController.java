package ga.tight.shortenurl.shorten.controller;

import ga.tight.shortenurl.shorten.dto.request.RequestRegisterShortenDto;
import ga.tight.shortenurl.shorten.dto.response.ResponseQueryShorten;
import ga.tight.shortenurl.shorten.dto.response.ResponseRegisterShortenDto;
import ga.tight.shortenurl.shorten.service.ShortenQueryService;
import ga.tight.shortenurl.shorten.service.ShortenRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Slf4j
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
        try
        {
            ResponseQueryShorten url = shortenQueryService.findByTag(tag);
            RedirectView redirectView = new RedirectView(url.getFullUrl());
            redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
            return redirectView;
        }
        catch (Exception e) {
            log.error(e.getMessage(), e.getCause());
            RedirectView redirectView = new RedirectView("/");
            redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
            return redirectView;
        }
    }

    @PostMapping("/api/shorten-urls")
    @ResponseBody
    public ResponseRegisterShortenDto postUrls(
            @RequestBody() RequestRegisterShortenDto body
    ) {
        return shortenRegisterService.register(body);
    }
}
