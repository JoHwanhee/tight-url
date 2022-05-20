package ga.tight.shortenurl.home.controller;

import ga.tight.shortenurl.home.dto.RequestRegisterUrlForm;
import ga.tight.shortenurl.shorten.domain.url.ShortenUrl;
import ga.tight.shortenurl.shorten.dto.request.RegisterShortenDto;
import ga.tight.shortenurl.shorten.dto.response.ResponseQueryShorten;
import ga.tight.shortenurl.shorten.dto.response.ResponseRegisterShortenDto;
import ga.tight.shortenurl.shorten.service.ShortenQueryService;
import ga.tight.shortenurl.shorten.service.ShortenRegisterService;
import ga.tight.shortenurl.shorten.service.StatisticsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
@Slf4j
public class HomeController {

    private final ShortenRegisterService shortenRegisterService;
    private final StatisticsService statisticsService;
    private final ShortenQueryService shortenQueryService;
    private final ModelMapper modelMapper;

    @GetMapping("/{tag}")
    public RedirectView redirectUrl(
            @PathVariable("tag") String tag
    ) {
        log.info(tag);
        try
        {
            ShortenUrl url = shortenQueryService.findByTag(tag);
            RedirectView redirectView = new RedirectView(url.getRedirectUrl());
            redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);

            statisticsService.increase(url);
            return redirectView;
        }
        catch (Exception e) {
            log.error(e.getMessage(), e.getCause());
            RedirectView redirectView = new RedirectView("/");
            redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
            return redirectView;
        }
    }


    @PostMapping("/home/shorten-urls")
    public ModelAndView post(
            HttpServletRequest request,
            RequestRegisterUrlForm form,
            RedirectAttributes re
            )
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/");

        if(form.getUrl().equals("")) {
            return mav;
        }

        ShortenUrl shorten = shortenRegisterService.register(modelMapper.map(form, RegisterShortenDto.class));
        ResponseRegisterShortenDto dto = ResponseRegisterShortenDto.of(shorten);
        String baseUrl = request.getRequestURL().toString();
        re.addFlashAttribute("url", dto.mergeUrl(baseUrl));

        return mav;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

}
