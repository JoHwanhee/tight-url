package ga.tight.shortenurl.shorten.usecase;

import ga.tight.shortenurl.shorten.domain.url.ShortenUrl;
import ga.tight.shortenurl.shorten.domain.url.Tag;
import ga.tight.shortenurl.shorten.service.ShortenQueryService;
import ga.tight.shortenurl.shorten.service.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

@Service
@AllArgsConstructor
public class ShortenQueryUseCase {

    private final ShortenQueryService queryService;
    private final StatisticsService statisticsService;

    public ShortenUrl queryAndIncrease(Tag tag) {
        ShortenUrl url = queryService.findByTag(tag);

        statisticsService.increase(url);

        return url;
    }
}
