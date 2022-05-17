package ga.tight.shortenurl.shorten.service;

import ga.tight.shortenurl.shorten.domain.url.ShortenUrl;
import ga.tight.shortenurl.shorten.dto.response.ResponseQueryShorten;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShortenQueryServiceTest {

    @Autowired private ShortenQueryService shortenQueryService;

    @Test
    void selectByName() {
        List<ResponseQueryShorten> shortenUrlList = shortenQueryService.findByUserId(0L);

        shortenUrlList.forEach(url -> System.out.println(url.getFullUrl()));

    }
}