package ga.tight.shortenurl.home.controller;

import ga.tight.shortenurl.home.dto.RequestRegisterUrlForm;
import ga.tight.shortenurl.shorten.domain.url.ShortenUrl;
import ga.tight.shortenurl.shorten.domain.url.Tag;
import ga.tight.shortenurl.shorten.dto.request.RegisterShortenDto;
import ga.tight.shortenurl.shorten.dto.response.ResponseQueryShorten;
import ga.tight.shortenurl.shorten.dto.response.ResponseRegisterShortenDto;
import ga.tight.shortenurl.shorten.service.ShortenQueryService;
import ga.tight.shortenurl.shorten.service.ShortenRegisterService;
import ga.tight.shortenurl.shorten.service.StatisticsService;
import ga.tight.shortenurl.shorten.usecase.ShortenQueryUseCase;
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
    private final ShortenQueryUseCase shortenQueryUseCase;
    private final ModelMapper modelMapper;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/{tag}")
    public RedirectView redirectUrl(
            @PathVariable("tag") String requestTag
    ) {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/");
        redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);

        try
        {
            Tag tag = Tag.of(requestTag);
            ShortenUrl url = shortenQueryUseCase.queryAndIncrease(tag);
            redirectView.setUrl(url.getRedirectUrl());
        }
        catch (Exception e) {

        }

        return redirectView;
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
}
