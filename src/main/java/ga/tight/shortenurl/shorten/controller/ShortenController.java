package ga.tight.shortenurl.shorten.controller;

import ga.tight.shortenurl.shorten.domain.url.ShortenUrl;
import ga.tight.shortenurl.shorten.dto.request.RegisterShortenDto;
import ga.tight.shortenurl.shorten.dto.response.ResponseRegisterShortenDto;
import ga.tight.shortenurl.shorten.service.ShortenRegisterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class ShortenController {
    private final ShortenRegisterService shortenRegisterService;

    @PostMapping("/api/shorten-urls")
    @ResponseBody
    public ResponseRegisterShortenDto postUrls(
            @RequestBody() RegisterShortenDto body
    ) {
        ShortenUrl shortenUrl = shortenRegisterService.register(body);
        return ResponseRegisterShortenDto.of(shortenUrl);
    }
}
